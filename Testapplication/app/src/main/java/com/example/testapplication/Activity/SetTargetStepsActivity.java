package com.example.testapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetTargetStepsActivity extends BaseActivity {

    @BindView(R.id.targetstepstxt)
    TextView targetstepstxt;
    @BindView(R.id.edittargetsteps)
    EditText edittargetsteps;
    @BindView(R.id.targetstepsbtn)
    Button targetstepsbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_target_steps);
        ButterKnife.bind(this);
        sendValue(BleSDK.GetStepGoal());
    }
    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Map<String, String> maps = getData(map);
        switch (dataType) {
            case BleConst.SetStepGoal:
                showToast("Target Steps Updated");
                break;
            case BleConst.GetStepGoal:
                targetstepstxt.setText("Target Steps: "+maps.get(DeviceKey.StepGoal));
                break;
        }
    }
    @OnClick({R.id.targetstepsbtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.targetstepsbtn:
                try {
                    sendValue(BleSDK.SetStepGoal(Integer.parseInt(edittargetsteps.getText().toString())));
                }
                catch (Exception e)
                {
                    showToast("Fill the field correctly");
                }
                break;
        }
    }
}