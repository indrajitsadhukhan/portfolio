package com.example.testapplication.Activity;

import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.example.testapplication.Ble.BleManager;
import com.example.testapplication.Ble.BleService;
import com.example.testapplication.R;
import com.example.testapplication.Util.BleData;
import com.example.testapplication.Util.RxBus;
import com.jstyle.blesdk1963.Util.BleSDK;
import com.jstyle.blesdk1963.constant.BleConst;
import com.jstyle.blesdk1963.constant.DeviceKey;
import com.jstyle.blesdk1963.model.MyDeviceTime;

import java.util.Calendar;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity{

    private String address;
    private static final String TAG = "MainActivity";
    ProgressDialog progressDialog;
    RecyclerView mainRecyclerview;
    String[] options;
    private Disposable subscription;
    @BindView(R.id.BT_CONNECT)
    Button btConnect;
    @BindView(R.id.batterylevel)
    TextView batteryLevel;
    @BindView(R.id.devicetime)
    TextView deviceTime;
    @BindView(R.id.devicenametxt)
    TextView devicenametxt;
    @BindView(R.id.recordtemp)
    CheckBox recordtemp;
    boolean isStartReal=false;
    @BindView(R.id.mainstartbtn)
    Button mainstartbtn;
    @BindView(R.id.caloriestable)
    TextView caloriestable;
    @BindView(R.id.stepstable)
    TextView stepstable;
    @BindView(R.id.heartratetable)
    TextView heartratetable;
    @BindView(R.id.activetable)
    TextView activetable;
    @BindView(R.id.timetable)
    TextView timetable;
    @BindView(R.id.distancetable)
    TextView distancetable;
    @BindView(R.id.temptable)
    TextView temptable;




    public static int phoneDataLength = 200;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        init();
        connectDevice();
        recordtemp.setOnCheckedChangeListener((buttonView, isChecked) -> sendValue(BleSDK.RealTimeStep(isStartReal,isChecked)));
    }

    private void init() {
        subscription = RxBus.getInstance().toObservable(BleData.class).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BleData>() {
            @Override
            public void accept(BleData bleData) {
                String action = bleData.getAction();
                if (action.equals(BleService.ACTION_GATT_onDescriptorWrite))
                {
                    btConnect.setEnabled(false);
                    disMissDialog();
                }
                else if (action.equals(BleService.ACTION_GATT_DISCONNECTED))
                {
                    btConnect.setEnabled(true);
                    disMissDialog();
                }
            }
        });

        Thread t = new Thread() {
            @Override
            public void run() {
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        sendValue(BleSDK.GetDeviceBatteryLevel());
                        sendValue(BleSDK.GetDeviceTime());
                        sendValue(BleSDK.GetDeviceName());
                        if (!BleManager.getInstance().isConnected()) {
                            init();
                        }
                    }
                }, 4000);
            }
        };
        t.run();

    }
    @OnClick({R.id.setdevicetime,R.id.stepdata,R.id.exercisedata,R.id.totaldata,R.id.heartdata,R.id.mainstartbtn,R.id.myprofile,R.id.deviceinfo,R.id.BT_CONNECT,R.id.setdevicename,R.id.targetsteps,R.id.restorefactory,R.id.mcusoftreset,R.id.transmitmotor,R.id.reminder,R.id.alarm,R.id.heartrate,R.id.exerciseperiod})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.setdevicetime:
                SetDeviceTimeMain();
                break;
            case R.id.myprofile:
                startActivity(new Intent(this, GetProfileActivity.class));
                break;
            case R.id.deviceinfo:
                startActivity(new Intent(this, GetDeviceInfoActivity.class));
                break;
            case R.id.BT_CONNECT:
                init();
                connectDevice();
                break;
            case R.id.setdevicename:
                startActivity(new Intent(this, SetDeviceNameActivity.class));
                break;
            case R.id.targetsteps:
                startActivity(new Intent(this, SetTargetStepsActivity.class));
                break;
            case R.id.restorefactory:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Restore Factory Settings");
                builder.setMessage("Are you sure you want to Restore Factory Settings ?");
                builder.setPositiveButton("Yes", (dialog, id) -> sendValue(BleSDK.Reset()));
                builder.setNegativeButton("No", null);
                builder.show();
                break;
            case R.id.mcusoftreset:
                 builder = new AlertDialog.Builder(this);
                builder.setTitle("Restore Factory Settings");
                builder.setMessage("Are you sure you want to Restore Factory Settings ?");
                builder.setPositiveButton("Yes", (dialog, id) -> sendValue(BleSDK.MCUReset()));
                builder.setNegativeButton("No", null);
                builder.show();
                break;
            case R.id.transmitmotor:
                startActivity(new Intent(this, SetTransmitMotorVibrationActivity.class));
                break;
            case R.id.reminder:
                startActivity(new Intent(this, ThirdPartyReminderActivity.class));
                break;
            case R.id.alarm:
                startActivity(new Intent(this, AlarmListActivity.class));
                break;
            case R.id.exerciseperiod:
                startActivity(new Intent(this, GetExercisePeriodActivity.class));
                break;
            case R.id.heartrate:
                startActivity(new Intent(this, GetHeartRateActivity.class));
                break;
            case R.id.mainstartbtn:
                isStartReal = !isStartReal;
                mainstartbtn.setText(isStartReal ? "Stop" : "Start");
                sendValue(BleSDK.RealTimeStep(isStartReal,recordtemp.isChecked()));
                break;
            case R.id.stepdata:
                startActivity(new Intent(this, StepDataActivity.class));
                break;
            case R.id.heartdata:
                startActivity(new Intent(this, HeartDataActivity.class));
                break;
            case R.id.exercisedata:
                startActivity(new Intent(this, ExerciseDataActivity.class));
                break;
            case R.id.totaldata:
                startActivity(new Intent(this, TotalDataActivity.class));
                break;
        }
    }

    private void disMissDialog() {
        if (progressDialog != null && progressDialog.isShowing()) progressDialog.dismiss();
    }

    private void connectDevice() {
        address = getIntent().getStringExtra("address");
        if (TextUtils.isEmpty(address)) {
            Log.i(TAG, "onCreate: address null ");
            return;
        }
        Log.i(TAG, "onCreate: ");
        BleManager.getInstance().connectDevice(address);
        showConnectDialog();
    }

    private void showConnectDialog() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage(getString(R.string.connectting));
        if (!progressDialog.isShowing())
            progressDialog.show();
    }
    private void SetDeviceTimeMain() {

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int min = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);
        MyDeviceTime setTime = new MyDeviceTime();
        setTime.setYear(year);
        setTime.setMonth(month);
        setTime.setDay(day);
        setTime.setHour(hour);
        setTime.setMinute(min);
        setTime.setSecond(second);
        sendValue(BleSDK.SetDeviceTime(setTime));
    }
    @Override
    public void dataCallback(Map<String, Object> map) {
        super.dataCallback(map);
        String dataType = getDataType(map);
        Log.e("info", map.toString());

        switch (dataType) {
            case BleConst.RealTimeStep:
                Map<String, String> maps = getData(map);
                String step = maps.get(DeviceKey.Step);
                String cal = maps.get(DeviceKey.Calories);
                String distance = maps.get(DeviceKey.Distance);
                String time = maps.get(DeviceKey.ExerciseMinutes);
                String ActiveTime = maps.get(DeviceKey.ActiveMinutes);
                String heart = maps.get(DeviceKey.HeartRate);
                String TEMP= maps.get(DeviceKey.TempData);
                caloriestable.setText(cal);
                stepstable.setText(step);
                distancetable.setText(distance);
                timetable.setText(time);
                heartratetable.setText(heart);
                activetable.setText(ActiveTime);
                temptable.setText(TEMP);
                break;
            case BleConst.GetDeviceBatteryLevel:
                maps = getData(map);
                String battery = maps.get(DeviceKey.BatteryLevel);
                batteryLevel.setText("Battery: " + battery + "%");
                break;
            case BleConst.GetDeviceName:
                 maps = getData(map);
                devicenametxt.setText(maps.get(DeviceKey.DeviceName));
                break;
            case BleConst.FindMobilePhoneMode:
                showDialogInfo(BleConst.FindMobilePhoneMode);
                break;
            case BleConst.RejectTelMode:
                showDialogInfo(BleConst.RejectTelMode);
                break;
            case BleConst.TelMode:
                showDialogInfo(BleConst.TelMode);
                break;
            case BleConst.BackHomeView:
                showToast(map.toString());
                break;
            case BleConst.Sos:
                showToast(map.toString());
                break;
            case BleConst.GetDeviceTime:
                maps = getData(map);
                deviceTime.setText(maps.get(DeviceKey.DeviceTime));
                break;
            case BleConst.SetDeviceTime:
                showDialogInfo("Device Time Set successful");
                break;
            case BleConst.CMD_Reset:
                showToast("Factory Settings Restored");
                break;
            case BleConst.CMD_MCUReset:
                showToast("Soft Reset Successfull");
                break;

        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unsubscribe();
        if (BleManager.getInstance().isConnected()) BleManager.getInstance().disconnectDevice();
    }

    private void unsubscribe() {
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
            Log.i(TAG, "unSubscribe: ");
        }
    }
}