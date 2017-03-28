package com.example.ostapopalynskyi.servicedbtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btnStart).setOnClickListener(this);
        findViewById(R.id.btnStop).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnStart:
                startService();
                break;
            case R.id.btnStop:
                stopService();
                break;

        }
    }

    private void startService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);

    }

    private void stopService() {
        Intent intent = new Intent(this, MyService.class);
        stopService(intent);
    }


}
