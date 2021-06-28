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

public class SetTransmitMotorVibrationActivity extends BaseActivity {
    @BindView(R.id.edittransmitmotor)
    EditText edittransmitmotor;
    @BindView(R.id.submittransmitmotor)
    Button submittransmitmotor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_transmit_motor_vibration);
        ButterKnife.bind(this);
    }

    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Map<String, String> maps = getData(map);
        switch (dataType) {
            case BleConst.SetMotorVibrationWithTimes:
                showToast("Transmit Vibration updated");
                break;
        }
    }
    @OnClick({R.id.submittransmitmotor})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.submittransmitmotor:
                try {
                    sendValue(BleSDK.MotorVibrationWithTimes(Integer.parseInt(edittransmitmotor.getText().toString())));
                    showToast("Transmit Vibration updated");
                }
                catch (Exception e)
                {
                    showToast("Fill the fields correctly (Value should be less than 5)");
                }

                break;
        }
    }
}