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

public class GetDeviceInfoActivity extends BaseActivity {

    @BindView(R.id.handlesign)
    TextView handlesign;
    @BindView(R.id.wriston)
    TextView wriston;
    @BindView(R.id.brightness)
    TextView brightness;
    @BindView(R.id.baseheartrate)
    TextView baseheartrate;
    @BindView(R.id.screenorientation)
    TextView screenorientation;
    @BindView(R.id.distanceunit)
    TextView distanceunit;
    @BindView(R.id.dialinterface)
    TextView dialinterface;
    @BindView(R.id.timeformat)
    TextView timeformat;
    @BindView(R.id.editDeviceInfo)
    Button editDeviceInfo;
    @BindView(R.id.macaddresstxt)
    TextView macaddresstxt;
    @BindView(R.id.softwareversion)
    TextView softwareversion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_info);
        ButterKnife.bind(this);
        sendValue(BleSDK.GetDeviceInfo());
        sendValue(BleSDK.GetDeviceVersion());
        sendValue(BleSDK.GetDeviceMacAddress());
    }

    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Log.e("info", map.toString());
        Map<String, String> maps = getData(map);
        switch (dataType) {
            case BleConst.GetDeviceInfo:
                if (maps.get(DeviceKey.LeftOrRight)=="1")
                    handlesign.setText("Right Hand");
                else
                    handlesign.setText("Left Hand");

                wriston.setText(maps.get(DeviceKey.WristOn));
                brightness.setText(maps.get(DeviceKey.ScreenBrightness));
                baseheartrate.setText(maps.get(DeviceKey.KBaseHeart));
                if (maps.get(DeviceKey.isHorizontalScreen)=="0") {
                    screenorientation.setText("Vertical");
                } else {
                    screenorientation.setText("Horizontal");
                }
//                if(maps.get(DeviceKey.DistanceUnit)=="1")
                distanceunit.setText(maps.get(DeviceKey.DistanceUnit));
                dialinterface.setText(maps.get(DeviceKey.Dialinterface));

                if(maps.get(DeviceKey.TimeUnit).equals("0"))
                timeformat.setText("24 Hour");
                else{
                    timeformat.setText("12 Hour");
                }
                break;
            case BleConst.GetDeviceVersion:
                softwareversion.setText(maps.get(DeviceKey.DeviceVersion));
                break;
            case BleConst.GetDeviceMacAddress:
                macaddresstxt.setText(maps.get(DeviceKey.MacAddress));
                break;
        }
    }
    @OnClick({R.id.editDeviceInfo})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editDeviceInfo:
                Intent intent = new Intent(this, SetDeviceInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}