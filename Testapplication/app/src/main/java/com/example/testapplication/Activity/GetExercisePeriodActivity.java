package com.example.testapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testapplication.R;
import com.example.testapplication.Util.Utils;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GetExercisePeriodActivity extends BaseActivity {
    @BindView(R.id.starttimeexercise)
    TextView starttimeexercise;
    @BindView(R.id.endtimeexercise)
    TextView endtimeexercise;
    @BindView(R.id.dayexercise)
    TextView dayexercise;
    @BindView(R.id.leaststeps)
    TextView leaststeps;
    @BindView(R.id.exerciseinterval)
    TextView exerciseinterval;
    @BindView(R.id.editExercise)
    Button editExercise;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_period);
        ButterKnife.bind(this);
    }
    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Log.e("info", map.toString());
        Map<String, String> maps = getData(map);
        switch (dataType) {
            case BleConst.GetSedentaryReminder:
                starttimeexercise.setText(maps.get(DeviceKey.StartTimeHour)+":"+maps.get(DeviceKey.StartTimeMin));
                endtimeexercise.setText(maps.get(DeviceKey.EndTimeHour)+":"+maps.get(DeviceKey.EndTimeMin));
                dayexercise.setText(Utils.findweeks(maps.get(DeviceKey.Week)));
                leaststeps.setText(maps.get(DeviceKey.LeastSteps));
                exerciseinterval.setText(maps.get(DeviceKey.IntervalTime));

                break;
        }
    }
    @OnClick({R.id.editExercise})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editExercise:
                startActivity(new Intent(this, SetExercisePeriodActivity.class));
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        sendValue(BleSDK.GetSedentaryReminder());
    }
}