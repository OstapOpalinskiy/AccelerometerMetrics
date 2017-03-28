package com.example.ostapopalynskyi.servicedbtest.model.vo;

import android.hardware.SensorEvent;

/**
 * Created by ostap.opalynskyi on 28.03.2017.
 */

public class AccelerometerMetricsVO {

    private String date;
    private String actionName;
    private String actionValue;

    public AccelerometerMetricsVO(SensorEvent event) {
        setDate(String.valueOf(event.timestamp));
        setActionName(event.sensor.getName());
        setActionValue(String.valueOf(event.values[0]));
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getActionValue() {
        return actionValue;
    }

    public void setActionValue(String actionValue) {
        this.actionValue = actionValue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
