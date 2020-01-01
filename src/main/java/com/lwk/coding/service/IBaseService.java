package com.lwk.coding.service;

import java.util.List;

/**
 * @author kai
 * @date 2020-01-01 23:32
 */
public interface IBaseService<T> {
    /**
     * 查询所有对象
     * @return
     */
    public List<T> getAllObj();

    /**
     * 保存当前对象
     * @param t
     * @return
     */
    public boolean saveObj(T t);

    /**
     * 删除当前对象
     * @param id
     * @return
     */
    public boolean deleteObjById(Integer id);

    /**
     * 更新当前对象
     * @param t
     * @return
     */
    public boolean updateObj(T t);
}
