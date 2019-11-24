package com.example.aesm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data_settings extends AppCompatActivity implements View.OnClickListener {

    public static String up_data;
    public static String down_data;
    public static String left_data;
    public static String right_data;
    public static String start_stop_data;
    public static String reset_data;

    private EditText up_edit;
    private EditText down_edit;
    private EditText left_edit;
    private EditText right_edit;
    private EditText start_stop_edit;
    private EditText reset_edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_settings);
        up_edit = (EditText) findViewById(R.id.up_edittext);
        down_edit = (EditText) findViewById(R.id.down_edittext);
        left_edit = (EditText) findViewById(R.id.left_edittext);
        right_edit = (EditText) findViewById(R.id.right_edittext);
        start_stop_edit = (EditText) findViewById(R.id.start_stop_edittext);
        reset_edit = (EditText) findViewById(R.id.reset_edittext);
        Button sure_button = (Button) findViewById(R.id.sure_button);
        Button back1_button = (Button) findViewById(R.id.back1_button);
        sure_button.setOnClickListener(this);
        back1_button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sure_button:
                if (up_edit.getText().toString() != null){
                    up_data = up_edit.getText().toString();
                }else {
                    up_data = "null";
                }
                if (down_edit.getText().toString() != null){
                    down_data = down_edit.getText().toString();
                }else {
                    down_data = "null";
                }
                if (left_edit.getText().toString() != null){
                    left_data = left_edit.getText().toString();
                }else {
                    left_data = "null";
                }
                if (right_edit.getText().toString() != null){
                    right_data = right_edit.getText().toString();
                }else {
                    right_data = "null";
                }
                if (start_stop_edit.getText().toString() != null){
                    start_stop_data = start_stop_edit.getText().toString();
                }else {
                    start_stop_data = "null";
                }
                if (reset_edit.getText().toString() != null){
                    reset_data = reset_edit.getText().toString();
                }else {
                    reset_data = "null";
                }
                finish();
                break;
            case R.id.back1_button:
                finish();
                break;
        }
    }
}
