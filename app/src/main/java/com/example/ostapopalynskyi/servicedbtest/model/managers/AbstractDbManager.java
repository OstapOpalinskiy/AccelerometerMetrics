package com.example.ostapopalynskyi.servicedbtest.model.managers;

import java.util.List;

/**
 * Created by ostap.opalynskyi on 28.03.2017.
 */

public abstract class AbstractDbManager<ClassToManage> {

    public abstract void saveDataAsync(List<ClassToManage> list);
}
