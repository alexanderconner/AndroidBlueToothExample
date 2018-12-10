package com.astralbody888.alexanderconner.bluetoothexample;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    BluetoothAdapter BA;
    Button turnBluetoothOffButton;
    Button findDevicesButton;
    Button viewPairedDevicesButton;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager mLayoutManager;
    PairedDevicesRecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        turnBluetoothOffButton = (Button) findViewById(R.id.turnoffbluetoothbutton);
        turnBluetoothOffButton.setOnClickListener(turnOffBluetoothHandler);
        findDevicesButton = (Button) findViewById(R.id.findDiscoverableDevicesButton);
        findDevicesButton.setOnClickListener(findDiscoverableDevicesHandler);
        viewPairedDevicesButton = (Button) findViewById(R.id.viewPairedDevicesButton);
        viewPairedDevicesButton.setOnClickListener(viewPairedDevicesHandler);

        recyclerView = (RecyclerView) findViewById(R.id.pairedDevicesRecycler);
        recyclerView.setHasFixedSize(true);
        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);

        BA = BluetoothAdapter.getDefaultAdapter();

        if( BA.isEnabled()) {
            Toast.makeText(this, "BlueTooth is on", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivity(intent);

            if( BA.isEnabled()) {
                Toast.makeText(this, "BlueTooth has been turned on", Toast.LENGTH_SHORT).show();
            }
        }
    }


    View.OnClickListener turnOffBluetoothHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            BA.disable();

//            if (BA.isEnabled()) {
//                Toast.makeText(MainActivity.this, "Bluetooth could not be disabled", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(MainActivity.this, "Bluetooth Turned off.", Toast.LENGTH_SHORT).show();
//            }
                Toast.makeText(MainActivity.this, "Bluetooth Turned off.", Toast.LENGTH_SHORT).show();

        }
    };

    View.OnClickListener findDiscoverableDevicesHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent I = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivity(I);
        }
    };

    View.OnClickListener viewPairedDevicesHandler = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            Set<BluetoothDevice> pairedDevices = BA.getBondedDevices();
            BluetoothDevice[] devices = pairedDevices.toArray(new BluetoothDevice[pairedDevices.size()]);

//            int numDevices = pairedDevices.size();
//            String[] mDataSet = new String[numDevices];

//            int i =0;
//            for (BluetoothDevice device : pairedDevices) {
//                //pairedDevicesArrayList.add(device.getName());
//                mDataSet[i] = device.getName();
//                i++;
//            }
            recyclerViewAdapter = new PairedDevicesRecyclerViewAdapter(devices, getApplicationContext());
            recyclerView.setAdapter(recyclerViewAdapter);
        }
    };
}
