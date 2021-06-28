package com.example.testapplication.Activity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.testapplication.Adapters.StepDataAdapter;
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

public class StepDataActivity extends BaseActivity {

    @BindView(R.id.radio_activity)
    RadioButton radioActivity;
    @BindView(R.id.radio_Sleep)
    RadioButton radioSleep;
    @BindView(R.id.radioGroup1)
    RadioGroup radioGroup1;
    @BindView(R.id.bt_readData)
    Button btReadData;
    @BindView(R.id.bt_DeleteData)
    Button btDeleteData;
    @BindView(R.id.RecyclerView_detailData)
    RecyclerView RecyclerViewDetailData;
    private StepDataAdapter stepDataAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step_data);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        RecyclerViewDetailData.setLayoutManager(linearLayoutManager);
        stepDataAdapter =new StepDataAdapter();
        RecyclerViewDetailData.setAdapter(stepDataAdapter);
    }
    int ModeStart=0;
    int ModeContinue=2;
    int ModeDelete=0x99;
    @OnClick({R.id.bt_readData, R.id.bt_DeleteData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_readData:
                list.clear();
                dataCount=0;
                getDetailData(ModeStart);
                break;
            case R.id.bt_DeleteData:
                list.clear();
                stepDataAdapter.Clear();
                getDetailData(ModeDelete);
                break;
        }
    }
    List<Map<String,String>> list=new ArrayList<>();
    int dataCount=0;
    private static final String TAG = "DetailDataActivity";
    @Override
    public void dataCallback(Map<String, Object> maps) {
        super.dataCallback(maps);
        String dataType= getDataType(maps);
        boolean finish=getEnd(maps);
        switch (dataType){
            case BleConst.GetDetailActivityData:
                list.addAll((List<Map<String,String>>)maps.get(DeviceKey.Data));
                dataCount++;
                if(finish){
                    disMissProgressDialog();
                    stepDataAdapter.setData(list, StepDataAdapter.GET_STEP_DETAIL);
                }
                if(dataCount==50){
                    dataCount=0;
                    if(finish){
                        disMissProgressDialog();
                        stepDataAdapter.setData(list, StepDataAdapter.GET_STEP_DETAIL);
                    }else{
                        getDetailData(ModeContinue);
                    }
                }

                break;
            case BleConst.GetDetailSleepData:
                list.addAll((List<Map<String,String>>)maps.get(DeviceKey.Data));
                dataCount++;
                if(finish){
                    disMissProgressDialog();
                    stepDataAdapter.setData(list, StepDataAdapter.GET_SLEEP_DETAIL);
                }
                if(dataCount==50){
                    dataCount=0;
                    if(finish){
                        disMissProgressDialog();
                        stepDataAdapter.setData(list, StepDataAdapter.GET_SLEEP_DETAIL);
                    }else{
                        getDetailData(ModeContinue);
                    }
                }
                break;

        }

    }
    private void getDetailData(int mode){
        sendValue(radioGroup1.getCheckedRadioButtonId()==R.id.radio_activity?
                BleSDK.GetDetailActivityDataWithMode(mode):BleSDK.GetDetailSleepDataWithMode(mode));

    }
}