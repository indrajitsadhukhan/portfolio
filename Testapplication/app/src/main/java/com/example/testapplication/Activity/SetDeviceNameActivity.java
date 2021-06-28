package com.example.testapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetDeviceNameActivity extends BaseActivity {

    @BindView(R.id.editdevicename)
    EditText editdevicename;
    @BindView(R.id.submitdevicename)
    Button submitdevicename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_device_name);
        ButterKnife.bind(this);
    }
    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Map<String, String> maps = getData(map);
        switch (dataType) {
            case BleConst.GetDeviceName:
                showToast("Device Name Updated");
                break;
        }
    }
    @OnClick({R.id.submitdevicename})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submitdevicename:
                sendValue(BleSDK.SetDeviceName(editdevicename.getText().toString()));
                showToast("Device Name Updated");
                break;
        }
    }
}