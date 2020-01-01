package com.lwk.coding.service.impl;

import com.lwk.coding.service.IBaseService;

import java.util.List;

/**
 * @author kai
 * @date 2020-01-01 23:45
 */
public class BaseServiceImpl<T> implements IBaseService<T> {

    @Override
    public List<T> getAllObj() {
        return null;
    }

    @Override
    public boolean saveObj(T o) {
        return false;
    }

    @Override
    public boolean deleteObjById(Integer id) {
        return false;
    }

    @Override
    public boolean updateObj(T o) {
        return false;
    }
}
