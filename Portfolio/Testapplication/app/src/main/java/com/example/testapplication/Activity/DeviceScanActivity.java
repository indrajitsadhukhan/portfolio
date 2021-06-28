package com.example.testapplication.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.text.TextUtils;
import android.util.ArraySet;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.testapplication.Adapters.BluetoothDeviceAdapter;
import com.example.testapplication.R;
import com.example.testapplication.Util.ResolveData;
import com.jstyle.blesdk1963.model.ExtendedBluetoothDevice;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class DeviceScanActivity extends AppCompatActivity {
    private static final int REQUEST_ENABLE_LOCATION = 2;
    private Button devScanButton;
    private RecyclerView devScanRecycler;
    private ProgressBar progressBar;
    private Boolean isScanning = true;
    private BluetoothAdapter bluetoothAdapter;
    private static final int REQUEST_ENABLE_BT = 1;
    private BluetoothManager bluetoothManager;
    private Handler mHandler;
    int filterRssi = -100;
    private List<ExtendedBluetoothDevice> extendedBluetoothDevices = new ArrayList<>();
    private BluetoothDeviceAdapter bluetoothDeviceAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_scan);
        bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        bluetoothAdapter = bluetoothManager.getAdapter();
        if (!getPackageManager().hasSystemFeature(
                PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, R.string.ble_not_supported, Toast.LENGTH_SHORT)
                    .show();
            finish();
        }
        mHandler = new Handler();

        bluetoothAdapter = bluetoothManager.getAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, R.string.error_bluetooth_not_supported,
                    Toast.LENGTH_SHORT).show();
            finish();
            return;
        }

        boolean permissionGranted = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if (permissionGranted) {

        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_ENABLE_LOCATION);

        }


        devScanButton = findViewById(R.id.devscanbtn);
        devScanRecycler = findViewById(R.id.devscanrecycler);
        progressBar = findViewById(R.id.devscanprogress);

        devScanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (progressBar.getVisibility() == View.GONE) {
                    progressBar.setVisibility(View.VISIBLE);
                    devScanButton.setText("Cancel");
                    scanLeDevice(true);
                } else {
                    progressBar.setVisibility(View.GONE);
                    devScanButton.setText("Scan");
                    scanLeDevice(false);
                }
            }
        });

        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        List<ExtendedBluetoothDevice> list = new ArrayList<>();
        for (BluetoothDevice device : devices) {
            ExtendedBluetoothDevice device1 = new ExtendedBluetoothDevice(device);
            list.add(device1);
        }

        bluetoothDeviceAdapter = new BluetoothDeviceAdapter(list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        devScanRecycler.setLayoutManager(linearLayoutManager);
        devScanRecycler.setAdapter(bluetoothDeviceAdapter);
        bluetoothDeviceAdapter.notifyDataSetChanged();
        bluetoothDeviceAdapter.notifyItemInserted(extendedBluetoothDevices.size());

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!bluetoothAdapter.isEnabled()) {
            //Seek permission for Bluetooth from user
            Intent enableBtIntent = new Intent(
                    BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
        scanLeDevice(true);
    }

    private void scanLeDevice(final boolean enable) {

        if (enable) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bluetoothAdapter.stopLeScan(scancallback);
                    isScanning = false;
                }
            }, 12000);

            bluetoothAdapter.startLeScan(scancallback);
            isScanning = true;
        } else {
            if (!isScanning) return;
            bluetoothAdapter.stopLeScan(scancallback);
            mHandler.removeCallbacksAndMessages(null);
            isScanning = false;

        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // User chose not to enable Bluetooth.
        if (requestCode == REQUEST_ENABLE_BT
                && resultCode == Activity.RESULT_CANCELED) {
            finish();
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull @org.jetbrains.annotations.NotNull String[] permissions, @NonNull @org.jetbrains.annotations.NotNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_ENABLE_LOCATION: {
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                } else {
                    Intent intent = new Intent();
                    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                    Uri uri = Uri.fromParts("package", DeviceScanActivity.this.getPackageName(),
                            null);
                    intent.setData(uri);
                    DeviceScanActivity.this.startActivity(intent);
                    Toast.makeText(this, "Please enable permission for Location", Toast.LENGTH_LONG).show();
                }
            }
        }

    }

    private BluetoothAdapter.LeScanCallback scancallback = new BluetoothAdapter.LeScanCallback() {

        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi,
                             final byte[] scanRecord) {
            runOnUiThread(new Runnable() {
                @RequiresApi(api = Build.VERSION_CODES.M)
                @Override
                public void run() {
                    System.out.println("DN");
                    String deviceName = device.getName();
                    if (TextUtils.isEmpty(deviceName)) {
                        deviceName = ResolveData.decodeDeviceName(scanRecord);
                    }
                    if (TextUtils.isEmpty(deviceName))
                        deviceName = getString(R.string.unknown_device);
                    ExtendedBluetoothDevice extendedBluetoothDevice = new ExtendedBluetoothDevice(device, deviceName, rssi);
                    System.out.println(extendedBluetoothDevice.name);

                    int flag=1;
                    for(int i=0;i<extendedBluetoothDevices.size();i++)
                    {
                        if(extendedBluetoothDevice.device.getAddress().equals(extendedBluetoothDevices.get(i).device.getAddress()))
                        {
                            flag=0;
                        }
                    }
                    if(flag==1) {

                            ArraySet<ExtendedBluetoothDevice> devices = new ArraySet<>();
                            devices.add(extendedBluetoothDevice);
                            bluetoothDeviceAdapter.addData(devices);
                            extendedBluetoothDevices.add(extendedBluetoothDevice);
                    }
                }
            });
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        scanLeDevice(false);
        bluetoothDeviceAdapter.clear();
    }
}