package com.example.anchala.androidsensors;

import android.annotation.TargetApi;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    TextView textview;
    private SensorManager sensormanager;
    List<Sensor> deviceSensors ;
    ArrayList<String> SensorNames = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview=findViewById(R.id.list_view);
        textview=findViewById(R.id.text);

        sensormanager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        deviceSensors = sensormanager.getSensorList(Sensor.TYPE_ALL);

        String size = String.valueOf(deviceSensors.size());
        Log.e("name", "size : "+size);
        Log.e("name", "value");
        for(int i=0; i<deviceSensors.size(); i++)
        {
            Log.e("name", "index : "+ i);
            String str= deviceSensors.get(i).getName();
            Log.e("name", "value : "+" "+str);
            SensorNames.add(i, str);
        }
        Log.e("name", "out");

//        final ArrayAdapter<Sensor> adapter = new ArrayAdapter<Sensor>(this, R.layout.aboutlist, R.id.text , deviceSensors);
//        listview.setAdapter(adapter);

        final ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.aboutlist, R.id.text , SensorNames);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String str = "onclick"+" "+i;
                Toast toast = Toast.makeText(getApplicationContext() ,str , Toast.LENGTH_LONG);
                toast.show();
                int delay = deviceSensors.get(i).getMaxDelay();
                String Vendor = deviceSensors.get(i).getVendor();
                int Version = deviceSensors.get(i).getVersion();

            }
        });


    }
}
