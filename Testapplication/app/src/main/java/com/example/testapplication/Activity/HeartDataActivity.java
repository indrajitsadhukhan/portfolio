package com.example.testapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HeartDataActivity extends BaseActivity {

    @BindView(R.id.radio_history)
    RadioButton radioHistory;
    @BindView(R.id.radio_once)
    RadioButton radioOnce;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;
    @BindView(R.id.bt_readData)
    Button btReadData;
    @BindView(R.id.bt_DeleteData)
    Button btDeleteData;
    @BindView(R.id.RecyclerView_heartData)
    RecyclerView RecyclerViewHeartData;
    private HeartRateDataAdapter heartRateDataAdapter;
    int ModeStart=0;
    int ModeContinue=2;
    int ModeDelete=0x99;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_data);
        ButterKnife.bind(this);
        init();
    }
    private void init() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerViewHeartData.setLayoutManager(linearLayoutManager);
        heartRateDataAdapter = new HeartRateDataAdapter();
        RecyclerViewHeartData.setAdapter(heartRateDataAdapter);
    }

    @OnClick({R.id.bt_readData, R.id.bt_DeleteData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_readData:
                list.clear();
                dataCount=0;
                getHeartHistoryData(ModeStart);
                break;
            case R.id.bt_DeleteData:

                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Restore Factory Settings");
                builder.setMessage("Are you sure you want to clear all Heart Rate Data ?");
                builder.setPositiveButton("Yes", (dialog, id) -> getHeartHistoryData(ModeDelete));
                builder.setNegativeButton("No", null);
                builder.show();
                break;
        }
    }
    List<Map<String,String>> list=new ArrayList<>();
    int dataCount=0;
    private static final String TAG = "HeartRateInfoActivity";
    @Override
    public void dataCallback(Map<String, Object> maps) {
        super.dataCallback(maps);
        String dataType= getDataType(maps);
        boolean finish=getEnd(maps);
        switch (dataType){
            case BleConst.GetDynamicHR:
                dataCount++;
                list.addAll((List<Map<String,String>>)maps.get(DeviceKey.Data));
                if(finish){
                    heartRateDataAdapter.setData(list,HeartRateDataAdapter.GET_HEART_DATA);
                }
                if(dataCount==50){
                    dataCount=0;
                    if(finish){
                        heartRateDataAdapter.setData(list,HeartRateDataAdapter.GET_HEART_DATA);
                    }else{
                        getHeartHistoryData(ModeContinue);
                    }
                }

                break;
            case BleConst.GetStaticHR:
                dataCount++;
                list.addAll((List<Map<String,String>>)maps.get(DeviceKey.Data));
                if(finish){
                    heartRateDataAdapter.setData(list,HeartRateDataAdapter.GET_ONCE_HEARTDATA);
                }
                if(dataCount==50){
                    dataCount=0;
                    if(finish){
                        heartRateDataAdapter.setData(list,HeartRateDataAdapter.GET_ONCE_HEARTDATA);
                    }else{
                        getHeartHistoryData(ModeContinue);
                    }
                }
                break;
        }
    }


    private void getHeartHistoryData(int mode){
        sendValue(radioGroup1.getCheckedRadioButtonId()==R.id.radio_history?
                BleSDK.GetDynamicHRWithMode(mode):BleSDK.GetStaticHRWithMode(mode));

    }
}