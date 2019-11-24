package com.example.aesm;

import android.app.AlertDialog;
import android.app.AppComponentFactory;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public class Manual_control extends AppCompatActivity implements View.OnClickListener {
    private final static int REQUEST_ENABLE_BT = 1;
    private BtlistAdapter adapter;
    private List<Btlist> btlists = new ArrayList<>();
    private String MY_UUID = "00001101-0000-1000-8000-00805F9B34FB";
    private static String TARGET_DEVICE_NAME = "HC05";
    private static String TAG = "蓝牙调试";
    private static OutputStream mOutputStream;
    public static String ap = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_control);
        androidx.appcompat.widget.Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        final BluetoothDevice device = getPairedDevices();
        //注册广播接收器
        //接受蓝牙发现
        IntentFilter filterFound = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        registerReceiver(mReceiver,filterFound);

        IntentFilter filterstart = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_STARTED);
        registerReceiver(mReceiver,filterstart);

        IntentFilter filterFinish = new IntentFilter(BluetoothAdapter.ACTION_DISCOVERY_FINISHED);
        registerReceiver(mReceiver,filterFinish);

        adapter = new BtlistAdapter(Manual_control.this,R.layout.bluetooth_settings,btlists);
        final ListView listView = (ListView) findViewById(R.id.列表);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                     Btlist btlist = btlists.get(position);

                AlertDialog.Builder builder = new AlertDialog.Builder(Manual_control.this);
                builder.setTitle("蓝牙连接");
                builder.setMessage("请您确定是否要连接该蓝牙");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG,"开始线程连接...");
                       Intent intent_move = new Intent(Manual_control.this,MoveActivity.class);
                       startActivity(intent_move);
                        mBluetoothAdapter.cancelDiscovery();
                        new Thread(new ClientThread(device)).start();

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

                builder.show();
            }
        });
    }
    //更新同一个socket下的输出流
    public static void fresh_data(){
        final BluetoothDevice device = getPairedDevices();
        if(ap != null ){
            sendDataToServer(ClientThread.socket);
        }
    }
    //广播接收蓝牙设备
    private BroadcastReceiver mReceiver = new BroadcastReceiver(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(BluetoothAdapter.ACTION_DISCOVERY_STARTED.equals(action)){
                Toast.makeText(Manual_control.this,"开始扫描",Toast.LENGTH_SHORT).show();
            }
            if (BluetoothDevice.ACTION_FOUND.equals(action)){
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (device != null){
                    //添加到listview的Adapter
                    String name = device.getName();
                    if (name == null)
                    {
                        name = "null";
                    }
                    String address = device.getAddress();
                    adapter.add(new Btlist(name,address));
                    adapter.notifyDataSetChanged();
                }
            }
            if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)){
                Toast.makeText(Manual_control.this,"扫描完成",Toast.LENGTH_SHORT).show();
            }
        }
    };
    private static class ClientThread extends Thread{
        private BluetoothDevice device;
        private static BluetoothSocket socket;

        private ClientThread(BluetoothDevice device){
            this.device =device;
        }
        @Override
        public void run(){
            Log.d(TAG,"连接服务端...");
            
            try{
                Log.d(TAG,"连接服务端...111");
                socket =device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
                Log.d(TAG,"连接服务端.122322..");

                    
                socket.connect();

                Log.d(TAG,"连接建立...");
                Log.d(TAG,"开始传输数据");
                sendDataToServer(socket);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
    public static void sendDataToServer(BluetoothSocket socket){

        try{

            mOutputStream =  socket.getOutputStream();
            Log.d(TAG,"文件发送成功1");
            Log.d(TAG,ap);
            mOutputStream.write(ap.getBytes());
            Log.d(TAG,ap);
            mOutputStream.flush();
            Log.d(TAG,"文件发送成功3");
      //      mOutputStream.close();
            Log.d(TAG,"文件发送成功");
            ap =null;
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static BluetoothDevice getPairedDevices(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();
        if (pairedDevices != null && pairedDevices.size()>0){
            for(BluetoothDevice device: pairedDevices){
                Log.d("蓝牙配对",device.getName()+":"+device.getAddress());
                if (TextUtils.equals(TARGET_DEVICE_NAME,device.getName())){
                    return device;
                }
            }
        }return null;
    }
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.Init:
                init();
                break;
            case R.id.Discovery:
                discovery();
                break;
            case R.id.Enable_Discovery:
                enable_discovery();
                break;
             default:
                 break;
        }return true;
    }

    //初始化蓝牙设备
    private void init() {
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Toast.makeText(Manual_control.this, "该设备不支持蓝牙", Toast.LENGTH_SHORT).show();
            //不支持蓝牙，退出
            return;
        }
        //弹出对话框，让用户开启蓝牙
        if (!mBluetoothAdapter.isEnabled()) {
            Toast.makeText(Manual_control.this, "请打开蓝牙", Toast.LENGTH_SHORT).show();
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }
    //启动蓝牙发现
    private void discovery(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            init();
        }
        mBluetoothAdapter.startDiscovery();
    }
    //蓝牙可见
    //时间阈值0-3600s
    //一般设置为120s
    private void enable_discovery(){
        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
        //第二个参数可设置时间为0-3600s,若不在此区间则会被自动设定为120s
        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,120);
        startActivity(discoverableIntent);
    }

    @Override
    public void onClick(View v) {

    }
}
