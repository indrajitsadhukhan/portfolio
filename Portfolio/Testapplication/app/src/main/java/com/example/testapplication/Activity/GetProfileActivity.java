package com.example.testapplication.Activity;
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

public class GetProfileActivity extends BaseActivity {

    @BindView(R.id.age)
    TextView age;
    @BindView(R.id.height)
    TextView height;
    @BindView(R.id.weight)
    TextView weight;
    @BindView(R.id.steplength)
    TextView steplength;
    @BindView(R.id.gender)
    TextView gender;
    @BindView(R.id.editPersonalDetails)
    Button editPersonalDetails;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_profile);
        ButterKnife.bind(this);
    }

    private void fetchPersonalDetails() {
        sendValue(BleSDK.GetPersonalInfo());
    }

    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Log.e("info", map.toString());
        Map<String, String> maps = getData(map);
        switch (dataType)
        {
            case BleConst.GetPersonalInfo:
                age.setText(maps.get(DeviceKey.Age));
                height.setText(maps.get(DeviceKey.Height)+" cm");
                weight.setText(maps.get(DeviceKey.Weight)+" kg");
                steplength.setText(maps.get(DeviceKey.Step));
                String gender_txt="Female";
                if(maps.get(DeviceKey.Gender).equals("1"))
                {
                    gender_txt="Male";
                }
                gender.setText(gender_txt);
                break;
        }
    }

    @OnClick({R.id.editPersonalDetails})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.editPersonalDetails:
                Intent intent=new Intent(this,SetProfileActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        fetchPersonalDetails();
    }
}