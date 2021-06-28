package com.example.testapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;
import com.jstyle.blesdk1963.model.MyAutomaticHRMonitoring;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetHeartRateActivity extends BaseActivity {
    @BindView(R.id.starttimepicker)
    TimePicker starttimepicker;
    @BindView(R.id.endtimepicker)
    TimePicker endtimepicker;
    @BindView(R.id.radioGroup_autoHeart)
    RadioGroup radioGroup_autoHeart;

    @BindView(R.id.edittimeinterval)
    EditText timeinterval;
    @BindView(R.id.suncheck)
    CheckBox suncheck;
    @BindView(R.id.moncheck)
    CheckBox moncheck;
    @BindView(R.id.tuecheck)
    CheckBox tuecheck;
    @BindView(R.id.wedcheck)
    CheckBox wedcheck;
    @BindView(R.id.thurscheck)
    CheckBox thurscheck;
    @BindView(R.id.fricheck)
    CheckBox fricheck;
    @BindView(R.id.satcheck)
    CheckBox satcheck;
    @BindView(R.id.submitheartrate)
    Button submitheartrate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_heart_rate);
        ButterKnife.bind(this);
        starttimepicker.setIs24HourView(true);
        endtimepicker.setIs24HourView(true);

    }

    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        switch (dataType) {
            case BleConst.SetAutomaticHRMonitoring:
                showToast("Automatic Heart Rate Monitoring changed successfully");
                break;
        }
    }

    @OnClick({R.id.submitheartrate})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submitheartrate:
                submit();
                break;
        }
    }

    private int getWorkModel(){
        int id=0;
        switch (radioGroup_autoHeart.getCheckedRadioButtonId()){
            case R.id.radio_autoheart_disable:
                id=0;
                break;
            case R.id.radio_autoheart_enable:
                id=1;
                break;
            case R.id.radio_autoheart_interval:
                id=2;
                break;
        }
        return id;
    }

    private void submit() {
        MyAutomaticHRMonitoring myAutomaticHRMonitoring = new MyAutomaticHRMonitoring();
        myAutomaticHRMonitoring.setStartHour(starttimepicker.getCurrentHour());
        myAutomaticHRMonitoring.setStartMinute(starttimepicker.getCurrentMinute());
        myAutomaticHRMonitoring.setEndHour(endtimepicker.getCurrentHour());
        myAutomaticHRMonitoring.setEndMinute(endtimepicker.getCurrentMinute());
        myAutomaticHRMonitoring.setTime(Integer.parseInt(timeinterval.getText().toString()));
        int x=0;
        if(moncheck.isChecked()) {
            x += 1;
        }
        if(tuecheck.isChecked()) {
            x += Math.pow(2,1);
        }
        if(wedcheck.isChecked()) {
            x += Math.pow(2,2);
        }
        if(thurscheck.isChecked()) {
            x += Math.pow(2,3);
        }
        if(fricheck.isChecked()) {
            x += Math.pow(2,4);
        }if(satcheck.isChecked()) {
            x += Math.pow(2,5);
        }
        if(suncheck.isChecked()) {
            x += Math.pow(2,6);
        }
        myAutomaticHRMonitoring.setWeek(x);
        myAutomaticHRMonitoring.setOpen(getWorkModel());
sendValue(BleSDK.SetAutomaticHRMonitoring(myAutomaticHRMonitoring));
    }

}