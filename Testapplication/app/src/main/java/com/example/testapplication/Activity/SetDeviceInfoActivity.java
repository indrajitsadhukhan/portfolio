package com.example.testapplication.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.widget.SwitchCompat;

import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;
import com.jstyle.blesdk1963.model.MyDeviceInfo;
import com.jstyle.blesdk1963.model.MyPersonalInfo;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class SetDeviceInfoActivity extends BaseActivity {
    @BindView(R.id.edittimeformat)
    RadioGroup edittimeformat;

    @BindView(R.id.editscreenbrightness)
    EditText editscreenbrightness;
    @BindView(R.id.editdialinterface)
    EditText editdialinterface;
    @BindView(R.id.editbaseheartrate)
    EditText editbaseheartrate;

    @BindView(R.id.socialdistance)
    SwitchCompat socialdistance;
    @BindView(R.id.chineeseEnglish)
    SwitchCompat chineeseEnglish;
    @BindView(R.id.farenheitcent)
    SwitchCompat farenheitcent;

    @BindView(R.id.brightScreen)
    SwitchCompat brightScreen;
    @BindView(R.id.nightmode)
    SwitchCompat nightmode;
    @BindView(R.id.horizontalscreen)
    SwitchCompat horizontalscreen;

    MyDeviceInfo myDeviceInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_device_info);
        ButterKnife.bind(this);
    }
    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Map<String, String> maps = getData(map);
        switch (dataType) {
            case BleConst.SetDeviceInfo:
                showToast("Device Info Updated");
                break;
        }
    }
    @OnClick({R.id.submitdeviceinfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submitdeviceinfo:
                submit();
                break;
        }
    }

    private void submit() {
        try {
            myDeviceInfo = new MyDeviceInfo();
            myDeviceInfo.setIs12Hour(edittimeformat.getCheckedRadioButtonId()==R.id.format12h);
            myDeviceInfo.setScreenBrightness(Integer.parseInt(editscreenbrightness.getText().toString()));
            myDeviceInfo.setDialinterface(Integer.parseInt(editdialinterface.getText().toString()));
            myDeviceInfo.setBaseheart(Integer.parseInt(editbaseheartrate.getText().toString()));
            myDeviceInfo.setSocial_distance_switch(socialdistance.isChecked());
            myDeviceInfo.setChinese_English_switch(chineeseEnglish.isChecked());
            myDeviceInfo.setFahrenheit_or_centigrade(farenheitcent.isChecked());
            myDeviceInfo.setNight_mode(nightmode.isChecked());
            myDeviceInfo.setBright_screen(brightScreen.isChecked());
            myDeviceInfo.setHorizontalScreen(horizontalscreen.isChecked());

            sendValue(BleSDK.SetDeviceInfo(myDeviceInfo));
        } catch (Exception e) {
            showToast("Fill all the fields correctly");
        }
    }


}