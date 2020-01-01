package com.lwk.coding.service;

import com.lwk.coding.entity.UserInfo;

/**
 * @author kai
 * @date 2020-01-01 23:48
 */
public interface IUserInfoService extends IBaseService<UserInfo> {
    /**
     * 根据用户名查询用户信息
     * @param name
     * @return
     */
    public UserInfo getObjByName(String name);
}
