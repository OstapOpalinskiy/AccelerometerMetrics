package com.example.ostapopalynskyi.servicedbtest.model.database.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import com.example.ostapopalynskyi.servicedbtest.model.database.DatabaseFacade;
import com.example.ostapopalynskyi.servicedbtest.model.vo.AccelerometerMetricsVO;

import java.util.List;

/**
 * Created by ostap.opalynskyi on 22.03.2017.
 */

public class AccelerometerDao extends AbstractDao<AccelerometerMetricsVO>{

    public static final String TABLE_NAME = "accelerometer_metrics";
    public static final String DATE_COLUMN = "date_created";
    public static final String ACTION_NAME_COLUMN = "actionName";
    public static final String ACTION_VALUE_COLUMN = "actionValue";

    public void saveMetricsToDb(AccelerometerMetricsVO metrics) {
        ContentValues cv = new ContentValues();
        cv.put(DATE_COLUMN, metrics.getDate());
        cv.put(ACTION_NAME_COLUMN, metrics.getActionName());
        cv.put(ACTION_VALUE_COLUMN, metrics.getActionValue());
        DatabaseFacade facade = DatabaseFacade.instance();
        facade.open();
        facade.save(TABLE_NAME, cv);
        facade.close();
    }

    @Override
    public void batchSaveDataToDB(List<AccelerometerMetricsVO> listOfMetrics) {
        DatabaseFacade facade = DatabaseFacade.instance();
        facade.open();
        facade.beginTransactions();
        int count = 0;
        for (AccelerometerMetricsVO metric : listOfMetrics) {
            saveMetricsToDb(metric);
            count++;
        }
        Log.d("TAG1", "Save metrics: " + count);
        facade.setTransactionSuccesfull();
        facade.endTransactions();
        facade.close();
    }

    public int getMetricsCount() {
        DatabaseFacade facade = DatabaseFacade.instance();
        Cursor cursor;
        facade.open();
        cursor = facade.getRowsCount(TABLE_NAME);
        int count = cursor.getCount();
        facade.close();
        return count;
    }
}
