package com.example.ostapopalynskyi.servicedbtest.model.database.dao;

import java.util.List;

/**
 * Created by ostap.opalynskyi on 28.03.2017.
 */

public abstract class AbstractDao<ClassToManage> {

    public abstract void batchSaveDataToDB(List<ClassToManage> list);


}
