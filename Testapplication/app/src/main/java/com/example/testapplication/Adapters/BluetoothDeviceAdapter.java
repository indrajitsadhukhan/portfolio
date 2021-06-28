package com.example.testapplication.Adapters;


import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.ArraySet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.testapplication.Activity.MainActivity;
import com.example.testapplication.R;
import com.jstyle.blesdk1963.model.ExtendedBluetoothDevice;

import java.util.List;

public class BluetoothDeviceAdapter extends RecyclerView.Adapter<BluetoothDeviceAdapter.ViewHolder> {

    private List<ExtendedBluetoothDevice> devices;
    private int filterRssi = -100;


    public BluetoothDeviceAdapter(List<ExtendedBluetoothDevice> devicelist) {
        devices = devicelist;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView deviceName,macAddress,rssi;
        private final LinearLayout mainLayout;
        private Context context;
        public ViewHolder(View view) {
            super(view);


            deviceName=view.findViewById(R.id.devicename);
            macAddress = view.findViewById(R.id.macaddress);
            rssi =view.findViewById(R.id.rssi);
            mainLayout = view.findViewById(R.id.mainlayout);
            context=view.getContext();
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bluetooth_device_row, viewGroup, false);

        return new ViewHolder(view);
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        BluetoothDevice device = devices.get(position).device;
        if(devices.get(position).name!=null && devices.get(position).name.length()>0) {
            viewHolder.deviceName.setText(devices.get(position).name);
        }
        else {
            viewHolder.deviceName.setText("Unknown Device");
        }
      viewHolder.macAddress.setText(device.getAddress());
        int rssi = devices.get(position).rssi;
      viewHolder.rssi.setText("RSSI: "+String.valueOf(rssi));

        if (rssi == ExtendedBluetoothDevice.NO_RSSI) {
            viewHolder.rssi.setText("BONDED");
        }
        viewHolder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(viewHolder.context, MainActivity.class);
                intent.putExtra("address", device.getAddress());
                intent.putExtra("name", device.getName());
                viewHolder.context.startActivity(intent);
            }
        });

    }
    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return devices.size();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void addData(ArraySet<ExtendedBluetoothDevice> devices1) {
        int size = devices.size();
        for(int i=0;i<devices1.size();i++)
        {
            devices.add(devices1.valueAt(i));
        }
        int sizeNew =devices.size();
        notifyItemRangeChanged(size, sizeNew);
    }
    public void clear()
    {
        devices.clear();
    }


}

