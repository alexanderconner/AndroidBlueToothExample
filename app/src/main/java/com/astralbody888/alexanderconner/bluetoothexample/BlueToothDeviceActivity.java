package com.astralbody888.alexanderconner.bluetoothexample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

public class BlueToothDeviceActivity extends AppCompatActivity {

    TextView deviceNameTextView;
    TextView deviceAddressTextView;
    TextView deviceInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth_device);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        String deviceName = getIntent().getStringExtra("Device_Name");
        Log.i("Devices", deviceName);
        String deviceAddress = getIntent().getStringExtra("Device_Address");
        String deviceInfo = getIntent().getStringExtra("Device_Info");
        deviceNameTextView = (TextView) findViewById(R.id.deviceNametv);
        deviceNameTextView.setText(deviceName);
        deviceAddressTextView = (TextView) findViewById(R.id.deviceAddressTV);
        deviceAddressTextView.setText(deviceAddress);
        deviceInfoTextView = (TextView) findViewById(R.id.deviceInfoTV);
        deviceNameTextView.setText(deviceInfo);


    }

}
