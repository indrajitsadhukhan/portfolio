package com.example.testapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;
import com.jstyle.blesdk1963.model.MyPersonalInfo;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetProfileActivity extends BaseActivity {

    @BindView(R.id.agesetprofile)
    EditText agesetprofile;
    @BindView(R.id.heightsetprofile)
    EditText heightsetprofile;
    @BindView(R.id.weightsetprofile)
    EditText weightsetprofile;
    @BindView(R.id.steplengthsetprofile)
    EditText steplengthsetprofile;

    @BindView(R.id.gendersetprofile)
    RadioGroup gendersetprofile;

    @BindView(R.id.setprofilesubmit)
    Button setprofilesubmit;
    MyPersonalInfo myPersonalInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_profile);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        fetchPersonalDetails();
    }

    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Log.e("info", map.toString());
        Map<String, String> maps = getData(map);
        switch (dataType) {
            case BleConst.GetPersonalInfo:
                agesetprofile.setText(maps.get(DeviceKey.Age));
                heightsetprofile.setText(maps.get(DeviceKey.Height));
                weightsetprofile.setText(maps.get(DeviceKey.Weight));
                steplengthsetprofile.setText(maps.get(DeviceKey.Step));
                break;
            case BleConst.SetPersonalInfo:
                showToast("Profile Updated");
                break;

        }
    }

    private void fetchPersonalDetails() {
        sendValue(BleSDK.GetPersonalInfo());
    }

    @OnClick({R.id.setprofilesubmit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setprofilesubmit:
                submit();
                break;
        }
    }

    private void submit() {
        try {
            myPersonalInfo = new MyPersonalInfo();
            myPersonalInfo.setAge(Integer.parseInt(agesetprofile.getText().toString()));
            myPersonalInfo.setHeight(Integer.parseInt(heightsetprofile.getText().toString()));
            myPersonalInfo.setWeight(Integer.parseInt(weightsetprofile.getText().toString()));
            myPersonalInfo.setStepLength(Integer.parseInt(steplengthsetprofile.getText().toString()));
            int gender = 0;
            myPersonalInfo.setSex(gender);
            int genid=gendersetprofile.getCheckedRadioButtonId();
            RadioButton radioButton = (RadioButton) findViewById(genid);
            String genderstr=radioButton.getText().toString();
            if(genderstr.equals("Male"))
            {
                myPersonalInfo.setSex(1);
            }
            sendValue(BleSDK.SetPersonalInfo(myPersonalInfo));
        } catch (Exception e) {
            showToast("Fill all the fields correctly");
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPersonalDetails();
    }


}