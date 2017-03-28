package com.example.ostapopalynskyi.servicedbtest.model.managers;

import android.os.AsyncTask;
import android.util.Log;

import com.example.ostapopalynskyi.servicedbtest.model.database.dao.AccelerometerDao;
import com.example.ostapopalynskyi.servicedbtest.model.vo.AccelerometerMetricsVO;

import java.util.List;

/**
 * Created by ostap.opalynskyi on 28.03.2017.
 */

public class AccelerometerMetricsDbManager extends AbstractDbManager<AccelerometerMetricsVO> {
    private static AccelerometerDao dao;
    private static AccelerometerMetricsDbManager instance;

    private AccelerometerMetricsDbManager() {
        dao = new AccelerometerDao();
    }

    public static synchronized AccelerometerMetricsDbManager instance() {
        if (instance == null) {
            instance = new AccelerometerMetricsDbManager();
        }
        return instance;
    }

    public void saveDataAsync(final List<AccelerometerMetricsVO> metricsToSave) {
        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                dao.batchSaveDataToDB(metricsToSave);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);
                Log.d("TAG1", "Saved events to Db: " + dao.getMetricsCount());
            }
        }.execute();
    }
}
