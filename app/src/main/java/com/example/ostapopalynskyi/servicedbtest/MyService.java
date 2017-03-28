package com.example.ostapopalynskyi.servicedbtest;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.example.ostapopalynskyi.servicedbtest.model.vo.AccelerometerMetricsVO;
import com.example.ostapopalynskyi.servicedbtest.model.managers.AccelerometerMetricsDbManager;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyService extends Service implements SensorEventListener {
    public static final int NOTIFICATION_ID = 1;
    public static final String STOP_SERVICE_ACTION = "com.opalynskyi.action";
    private BroadcastReceiver stopServiceReceiver;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private AccelerometerMetricsDbManager metricsManager;
    private List<AccelerometerMetricsVO> metricsList;
    public static final int BATCH_SIZE = 100;

    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("TAG1", "Service created");
        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.REPORTING_MODE_ON_CHANGE);
        metricsList = new ArrayList<>();
        metricsManager = AccelerometerMetricsDbManager.instance();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("TAG1", "Service started");
        startForeground(NOTIFICATION_ID, createNotification());
        registerStopServiceBroadcastReceiver();
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        if (metricsList.size() > 0) {
            metricsManager.saveDataAsync(metricsList);
        }
        unregisterReceiver(stopServiceReceiver);
        mSensorManager.unregisterListener(this);
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        AccelerometerMetricsVO metric = new AccelerometerMetricsVO(event);
        if (metricsList.size() < BATCH_SIZE) {
            metricsList.add(metric);
        } else {
            List<AccelerometerMetricsVO> tempList = new ArrayList<>(metricsList.size());
            tempList.addAll(metricsList);
            Collections.copy(tempList, metricsList);
            metricsManager.saveDataAsync(tempList);
            metricsList.clear();
        }
//
//        Log.d("TAG1", "Result" + event.sensor.getName() + "" + event.values[0]);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private Notification createNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("Accelerometer recorder")
                .setContentText("Accelerometer data is saved to db")
                .setContentIntent(createMainActivityPendingIntent())
                .addAction(R.drawable.ic_cancel_red_500_24dp, "Stop service", createCloseServicePendingIntent());
        return builder.build();
    }

    private PendingIntent createMainActivityPendingIntent() {
        Intent intent = new Intent(this, MainActivity.class);
        return PendingIntent.getActivity(this, 0, intent, 0);
    }

    private PendingIntent createCloseServicePendingIntent() {
        Intent intent = new Intent();
        intent.setAction(STOP_SERVICE_ACTION);
        return PendingIntent.getBroadcast(this, 0, intent, 0);
    }

    private void registerStopServiceBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(MyService.STOP_SERVICE_ACTION);
        stopServiceReceiver = new StopServiceReceiver();
        registerReceiver(stopServiceReceiver, intentFilter);
    }
}
