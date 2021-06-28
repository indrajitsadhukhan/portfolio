package com.example.testapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.model.MySedentaryReminder;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetExercisePeriodActivity extends BaseActivity {

    @BindView(R.id.startexercise)
    TimePicker startexercise;
    @BindView(R.id.endexercise)
    TimePicker endexercise;
    @BindView(R.id.timeintervalex)
    EditText timeinterval;
    @BindView(R.id.leaststepsex)
    EditText leaststepsex;
    
    @BindView(R.id.sunday)
    CheckBox suncheck;
    @BindView(R.id.monday)
    CheckBox moncheck;
    @BindView(R.id.tuesday)
    CheckBox tuecheck;
    @BindView(R.id.wednesday)
    CheckBox wedcheck;
    @BindView(R.id.thursday)
    CheckBox thurscheck;
    @BindView(R.id.friday)
    CheckBox fricheck;
    @BindView(R.id.saturday)
    CheckBox satcheck;
    @BindView(R.id.submitex)
    Button submitex;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_exercise_period);
        ButterKnife.bind(this);
        startexercise.setIs24HourView(true);
        endexercise.setIs24HourView(true);

    }
    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        switch (dataType) {
            case BleConst.SetSedentaryReminder:
                showToast("Exercise Period Updated");
                break;
        }
    }

    @OnClick({R.id.submitex})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submitex:
                submit();
                break;
        }
    }

    private void submit() {
        MySedentaryReminder mySedentaryReminder=new MySedentaryReminder();
        mySedentaryReminder.setStartHour(startexercise.getCurrentHour());
        mySedentaryReminder.setStartMinute(startexercise.getCurrentMinute());
        mySedentaryReminder.setEndHour(endexercise.getCurrentHour());
        mySedentaryReminder.setEndMinute(endexercise.getCurrentMinute());
        mySedentaryReminder.setIntervalTime(Integer.parseInt(timeinterval.getText().toString()));
        mySedentaryReminder.setLeastStep(Integer.parseInt(leaststepsex.getText().toString()));
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
        mySedentaryReminder.setWeek(x);
        sendValue(BleSDK.SetSedentaryReminder(mySedentaryReminder));
    }

}