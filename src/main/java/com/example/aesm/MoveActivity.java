package com.example.aesm;

import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.OutputStream;
import java.util.UUID;

import static com.example.aesm.Data_settings.up_data;

public class MoveActivity extends AppCompatActivity implements View.OnClickListener {
    private int flag = 0;
    private String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private String TARGET_DEVICE_NAME = "HC05";
    private String TAG = "蓝牙调试";
    private OutputStream mOutputStream;
    private int qe = 1;

    public static String ap = "168";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_move);
        Button start_stop = (Button) findViewById(R.id.start_stop_button);
        Button up = (Button)findViewById(R.id.up_button);
        Button down = (Button) findViewById(R.id.down_button);
        Button left = (Button) findViewById(R.id.left_button);
        Button right = (Button) findViewById(R.id.right_button);
        Button reset = (Button) findViewById(R.id.reset_button);
        Button control_data = (Button) findViewById(R.id.control_data);
        Button back = (Button) findViewById(R.id.back);
        start_stop.setOnClickListener(this);
        up.setOnClickListener(this);
        down.setOnClickListener(this);
        left.setOnClickListener(this);
        right.setOnClickListener(this);
        reset.setOnClickListener(this);
        control_data.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.up_button:
               Manual_control.ap = Data_settings.up_data;
               Manual_control.fresh_data();
                Toast.makeText(MoveActivity.this, up_data,Toast.LENGTH_SHORT).show();
                break;
            case R.id.down_button:
                Manual_control.ap = Data_settings.down_data;
                Manual_control.fresh_data();
                Toast.makeText(MoveActivity.this,Data_settings.down_data,Toast.LENGTH_SHORT).show();
                break;
            case R.id.left_button:
                Manual_control.ap = Data_settings.left_data;
                Manual_control.fresh_data();
                Toast.makeText(MoveActivity.this,Data_settings.left_data,Toast.LENGTH_SHORT).show();
                break;
            case R.id.right_button:
                Manual_control.ap = Data_settings.right_data;
                Manual_control.fresh_data();
                Toast.makeText(MoveActivity.this,Data_settings.right_data,Toast.LENGTH_SHORT).show();
                break;
            case R.id.start_stop_button:
                switch (flag){
                    case 0:
                        v.setActivated(true);
                        Manual_control.ap = Data_settings.start_stop_data;
                        Manual_control.fresh_data();
                        Toast.makeText(MoveActivity.this,Data_settings.start_stop_data,Toast.LENGTH_SHORT).show();
                        flag = 1;
                        break;
                    case 1:
                        v.setActivated(false);
                        Manual_control.ap = "1111";
                        Manual_control.fresh_data();
                        Toast.makeText(MoveActivity.this,"1111",Toast.LENGTH_SHORT).show();
                        flag = 0;
                        break;
                }
                break;
            case R.id.reset_button:
                Manual_control.ap = Data_settings.reset_data;
                Manual_control.fresh_data();
                Toast.makeText(MoveActivity.this,Data_settings.reset_data,Toast.LENGTH_SHORT).show();
                break;
            case R.id.control_data:
                Intent date_setting = new Intent(MoveActivity.this,Data_settings.class);
                startActivity(date_setting);
                break;
            case R.id.back:
                finish();
        }
    }



}
