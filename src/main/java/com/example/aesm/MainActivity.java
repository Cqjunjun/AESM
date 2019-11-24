package com.example.aesm;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.aesm.Animation_change;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public ImageView mimageView;
    public Button Xbutton;
    public Button Ybutton;
    public Button Zbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Xbutton = findViewById(R.id.button);
        Ybutton = findViewById(R.id.button1);
        Zbutton = findViewById(R.id.button2);
        Animation_change.animatorSet1(Xbutton);
        Animation_change.animatorSet2(Ybutton);
        Animation_change.animatorSet3(Zbutton);
        Xbutton.setOnClickListener((View.OnClickListener) this);
        Ybutton.setOnClickListener((View.OnClickListener) this);
        Zbutton.setOnClickListener((View.OnClickListener) this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.button :
                Intent McIntent = new Intent(MainActivity.this,Manual_control.class);
                startActivity(McIntent);
                break;
            case R.id.button1:
                break;
            case R.id.button2:
                Intent detail_intent = new Intent(MainActivity.this,DetailActivity.class);
                startActivity(detail_intent);
                break;
        }
    }
}

