package com.example.testapplication.Activity;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.testapplication.Adapters.ExerciseDataAdapter;
import com.example.testapplication.R;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ExerciseDataActivity extends BaseActivity {

    @BindView(R.id.bt_getData)
    Button btGetData;
    @BindView(R.id.RecyclerView_exerciseHistory)
    RecyclerView RecyclerViewExerciseHistory;
    @BindView(R.id.bt_deleteData)
    Button btDeleteData;
    @BindArray(R.array.mode_name)
    String[]modeNames;
    int ModeStart=0;
    int ModeContinue=2;
    int ModeDelete=0x99;
    private List<Map<String, String>> list;
    private ExerciseDataAdapter exerciseDataAdapter;
    private int dataCount;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise_data);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        list=new ArrayList<>();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        RecyclerViewExerciseHistory.setLayoutManager(linearLayoutManager);
        exerciseDataAdapter = new ExerciseDataAdapter(modeNames);
        RecyclerViewExerciseHistory.setAdapter(exerciseDataAdapter);
    }

    @OnClick({R.id.bt_getData, R.id.bt_deleteData})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.bt_getData:
                list.clear();
                dataCount=0;
                getData(ModeStart);
                break;
            case R.id.bt_deleteData:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Restore Factory Settings");
                builder.setMessage("Are you sure you want to clear all Exercise Data ?");
                builder.setPositiveButton("Yes", (dialog, id) -> getData(ModeDelete));
                builder.setNegativeButton("No", null);
                builder.show();
                break;
        }
    }
    private void getData(int mode){
        sendValue(BleSDK.GetActivityModeDataWithMode(mode));
    }

    @Override
    public void dataCallback(Map<String, Object> maps) {
        super.dataCallback(maps);
        String dataType= getDataType(maps);
        boolean finish=getEnd(maps);
        switch (dataType){
            case BleConst.GetActivityModeData:
                list.addAll((List<Map<String,String>>)maps.get(DeviceKey.Data));
                dataCount++;
                if(finish){
                    dataCount=0;
                    disMissProgressDialog();
                    exerciseDataAdapter.setData(list);
                    for(int i=0;i<list.size();i++)
                    {
                        Log.e("LIST",list.get(i).toString());
                    }
                }
                if(dataCount==50){
                    dataCount=0;
                    if(finish){
                        disMissProgressDialog();
                        exerciseDataAdapter.setData(list);
                    }else{
                        getData(ModeContinue);
                    }
                }
                break;
        }
    }
}