package com.astralbody888.alexanderconner.bluetoothexample;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Set;

public class PairedDevicesRecyclerViewAdapter extends RecyclerView.Adapter<PairedDevicesRecyclerViewAdapter.MyViewHolder> {

    private BluetoothDevice[] mDataset;
    private Context context;


    // Provide a suitable constructor (depends on the kind of dataset)
    public PairedDevicesRecyclerViewAdapter(BluetoothDevice[] devices, Context context) {
        this.mDataset = devices;
        this.context = context;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public PairedDevicesRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // create a new view
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyclerview_row, parent, false);
        //Log.i("Paired Devices: ", "The view inflated: " + v.findViewById(R.id.textView).getText().toString());
        MyViewHolder vh = new MyViewHolder(v, this.context);

        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Log.i("Paired Devices", mDataset[position].getName());
        holder.mTextView.setText(mDataset[position].getName());
        holder.bluetoothDevice = mDataset[position];
    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView mTextView;
        public BluetoothDevice bluetoothDevice;
        private Context rowContext;

        public MyViewHolder(View v, Context context) {
            super(v);
            mTextView = v.findViewById(R.id.deviceNameTextView);
            this.rowContext = context;

            v.setOnClickListener(onClickRowListener);
        }

        View.OnClickListener onClickRowListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(rowContext, "Tapped device: " + bluetoothDevice.getName(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(rowContext, BlueToothDeviceActivity.class);
                intent.putExtra("Device_Name", bluetoothDevice.getName());
                intent.putExtra("Device_Address", bluetoothDevice.getAddress());
                intent.putExtra("Device_Info", bluetoothDevice.getBluetoothClass().toString());
                rowContext.startActivity(intent);
            }
        };
    }

}
