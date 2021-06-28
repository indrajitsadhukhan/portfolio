package com.example.testapplication.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.Adapters.ClockAdapter;
import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;
import com.jstyle.blesdk1963.model.Clock;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmListActivity extends BaseActivity implements ClockAdapter.onClockItemClickListener {
    int ModeStart = 0;
    int ModeContinue = 2;
    int ModeDelete = 0x99;

    @BindView(R.id.RecyclerView_alarm)
    RecyclerView RecyclerViewAlarm;
    @BindArray(R.array.weekarray)
    String[] weekArray;
    @BindView(R.id.bt_add)
    Button btAdd;
    private ClockAdapter clockAdapter;

    public static final String KEY_CLOCK_LIST = "CLOCK_LIST";
    private int REQUEST_CLOCK = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_set);
        ButterKnife.bind(this);
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerViewAlarm.setLayoutManager(linearLayoutManager);
        clockAdapter = new ClockAdapter(this);
        RecyclerViewAlarm.setAdapter(clockAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerViewAlarm.addItemDecoration(dividerItemDecoration);
       sendValue(BleSDK.GetAlarmClock(ModeStart));
    }


    @Override
    public void onItemClick(Clock clock) {
        Intent intent = new Intent(this, AlarmSetActivity.class);
        intent.putExtra("clockid", clock.getNumber());
        intent.putExtra(KEY_CLOCK_LIST, (Serializable) clockAdapter.getClockList());
        startActivityForResult(intent, REQUEST_CLOCK);
    }

    @Override
    public void onDelete(Clock clock) {
        updateClock(clockAdapter.getClockList());
    }

    @Override
    public void onUpdate(List<Clock> clockList) {
        updateClock(clockList);
    }

    @Override
    public void dataCallback(Map<String, Object> maps) {
        super.dataCallback(maps);
        String dataType = getDataType(maps);
        switch (dataType) {
            case BleConst.GetAlarmClock:
                List<Map<String, String>> mapValue = ((List<Map<String, String>>) maps.get(DeviceKey.Data));
                for (Map<String, String> map : mapValue) {
                    String hour = String.format("%02d", Integer.valueOf(map.get(DeviceKey.ClockTime)));
                    String min = String.format("%02d", Integer.valueOf(map.get(DeviceKey.KAlarmMinter)));
                    String content = map.get(DeviceKey.KAlarmContent);
                    int clockType = Integer.parseInt(map.get(DeviceKey.ClockType));
                    int id = Integer.parseInt(map.get(DeviceKey.KAlarmId));
                    int enable = Integer.parseInt(map.get(DeviceKey.OpenOrClose));
                    String week = map.get(DeviceKey.Week);
                    final Clock clock = new Clock();
                    clock.setContent(content);
                    clock.setNumber(id);
                    clock.setEnable(enable == 1);
                    clock.setHour(Integer.parseInt(hour));
                    clock.setMinute(Integer.parseInt(min));
                    clock.setType(clockType);
                    byte weekByte = 0;
                    String[] weekString = week.split("-");
                    for (int i = 0; i < 7; i++) {
                        String weekEnable = weekString[i];
                        if (weekEnable.equals("1")) {
                            weekByte += Math.pow(2, i);
                        }
                    }
                    clock.setWeek(weekByte);
                    clockAdapter.setData(weekArray, clock);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CLOCK && resultCode == RESULT_OK) {
            clockAdapter.clear();
            sendValue(BleSDK.GetAlarmClock(ModeStart));
        }
    }

    private void updateClock(List<Clock> clockList) {

        for (int i = 0; i < clockList.size(); i++) {
            Clock clock = clockList.get(i);
            clock.setNumber(i);
        }
        byte[] value = clockList.size() == 0 ? BleSDK.deleteAllClock() : BleSDK.setClockData(clockList);
        int maxLength = MainActivity.phoneDataLength;
        if (value.length > maxLength) {
            int size = maxLength / 38;
            int length = size * 38;
            int count = value.length % length == 0 ? value.length / length : value.length / length + 1;
            for (int i = 0; i < count; i++) {
                int end = length * (i + 1);
                int endLength = length;
                if (end >= value.length) endLength = value.length - length * i;
                byte[] data = new byte[endLength];
                System.arraycopy(value, length * i, data, 0, endLength);
                offerData(data);
            }
            offerData();
        } else {
            sendValue(value);
        }
    }

    @OnClick({R.id.bt_add, R.id.bt_edit})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.bt_add:
                List<Clock> list=clockAdapter.getClockList();
                if(list.size()==10){
                    showToast("No more alarm clock");
                    return;
                }
                Intent intent = new Intent(this, AlarmSetActivity.class);
                intent.putExtra(KEY_CLOCK_LIST, (Serializable) clockAdapter.getClockList());
                startActivityForResult(intent, REQUEST_CLOCK);
                break;
            case R.id.bt_edit:
                clockAdapter.enableDelete();
                break;
        }

    }
}
