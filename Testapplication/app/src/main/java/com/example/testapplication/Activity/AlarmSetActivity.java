package com.example.testapplication.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.Adapters.WeekAdapter;
import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.Util.ResolveUtil;
import com.jstyle.blesdk1963.model.Clock;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlarmSetActivity extends BaseActivity {
    @BindView(R.id.timePicker_clock_set)
    TimePicker timePickerClockSet;
    @BindView(R.id.radio_normal)
    RadioButton radioNormal;
    @BindView(R.id.radio_Medicine)
    RadioButton radioMedicine;
    @BindView(R.id.radio_Drink)
    RadioButton radioDrink;
    @BindView(R.id.radioGroup_gender)
    RadioGroup radioGroupGender;
    @BindView(R.id.RecyclerView_alarm_set)
    RecyclerView RecyclerViewAlarmSet;
    @BindView(R.id.bt_clock_save)
    Button btClockSave;
    @BindArray(R.array.weekarray)
    String[] weekArray;
    @BindView(R.id.SwitchCompat)
    SwitchCompat switchCompat;
    private Clock clock;
    private WeekAdapter weekAdapter;
    private int clockId;
    private List<Clock> clockList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_set);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        clockId = getIntent().getIntExtra("clockid", -1);
        clockList = (List<Clock>) getIntent().getSerializableExtra(AlarmListActivity.KEY_CLOCK_LIST);
        timePickerClockSet.setIs24HourView(true);
        if (clockId != -1) {
            clock = clockList.get(clockId);
            int hour = clock.getHour();
            int min = clock.getMinute();
            int type = clock.getType();
            int week = clock.getWeek();
            switchCompat.setChecked(clock.isEnable());
            initGroup(type);
            initWeek(week);
            timePickerClockSet.setCurrentHour(hour);
            timePickerClockSet.setCurrentMinute(min);
        } else {
            initWeek(0);
            clock = new Clock();
            if (clockList == null) {
                clockList = new ArrayList<>();
                clock.setNumber(0);
            } else {
                clock.setNumber(clockList.size());
            }
            clockList.add(clock);
        }
    }
    private void initWeek(int week) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerViewAlarmSet.setLayoutManager(linearLayoutManager);
        int[] positions = new int[7];
        String weekString = ResolveUtil.getByteString((byte) week);
        String[] weekArrs = weekString.split("-");
        for (int i = 0; i < 7; i++) {
            if (weekArrs[i].equals("1")) {
                positions[i] = 1;
            }
        }
        weekAdapter = new WeekAdapter(weekArray, positions);
        RecyclerViewAlarmSet.setAdapter(weekAdapter);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        RecyclerViewAlarmSet.addItemDecoration(dividerItemDecoration);
    }
    private void initGroup(int type) {
        int id = R.id.radio_normal;
        switch (type) {
            case 1:
                id = R.id.radio_normal;
                break;
            case 2:
                id = R.id.radio_Medicine;
                break;
            case 3:
                id = R.id.radio_Drink;
                break;
        }
        radioGroupGender.check(id);
    }
    private int getClockType() {
        int id = 0;
        switch (radioGroupGender.getCheckedRadioButtonId()) {
            case R.id.radio_normal:
                id = 1;
                break;
            case R.id.radio_Medicine:
                id = 2;
                break;
            case R.id.radio_Drink:
                id = 3;
                break;
        }
        return id;
    }
    private int getCheckWeek() {
        int week = 0;
        int[] positions = weekAdapter.getCheckWeek();
        for (int i = 0; i < 7; i++) {
            if (positions[i] == 1) week += Math.pow(2, i);
        }
        return week;
    }
    @OnClick(R.id.bt_clock_save)
    public void onViewClicked() {
        int hour = timePickerClockSet.getCurrentHour();
        int min = timePickerClockSet.getCurrentMinute();
        int type = getClockType();
        int week = getCheckWeek();
        clock.setHour(hour);
        clock.setMinute(min);
        clock.setType(type);
        clock.setWeek((byte) week);
        clock.setEnable(switchCompat.isChecked());
        clock.setContent("");
        byte[] value = BleSDK.setClockData(clockList);
        int maxLength = MainActivity.phoneDataLength;
        if (value.length > maxLength) {
            int size = maxLength / 39;
            int length = size * 39;
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
        setResult(RESULT_OK);
    }

}
