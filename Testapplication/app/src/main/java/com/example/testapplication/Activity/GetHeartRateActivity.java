package com.example.testapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetHeartRateActivity extends BaseActivity {
    @BindView(R.id.starttime)
    TextView starttime;
    @BindView(R.id.endtime)
    TextView endtime;
    @BindView(R.id.timeinterval)
    TextView timeinterval;
    @BindView(R.id.week)
    TextView week;
    @BindView(R.id.editheartrate)
    Button editheartrate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_rate);
        ButterKnife.bind(this);

    }

    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Map<String, String> maps = getData(map);
        switch (dataType) {
            case BleConst.GetAutomaticHRMonitoring:
                starttime.setText(maps.get(DeviceKey.StartTime)+maps.get(DeviceKey.KHeartStartMinter));
                endtime.setText(maps.get(DeviceKey.EndTime)+maps.get(DeviceKey.KHeartEndMinter));
                timeinterval.setText(maps.get(DeviceKey.IntervalTime));
                week.setText(findweeks(maps.get(DeviceKey.Weeks)));
                break;
        }
    }

    private String findweeks(String x) {
        String temp="";
        int y=0;
        String[] weeks = {"Mon","Tue","Wed","Thurs","Fri","Sat","Sun"};
        for(int i=0;i<x.length();i+=2)
        {
            if(x.charAt(i)=='1')
            {
               temp+= weeks[y];
               temp+=", ";
            }
            y++;
        }
        return temp;
    }

    @OnClick({R.id.editheartrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editheartrate:
                Intent intent = new Intent(this, SetHeartRateActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sendValue(BleSDK.GetAutomaticHRMonitoring());
    }
}