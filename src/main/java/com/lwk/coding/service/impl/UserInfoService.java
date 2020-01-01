package com.lwk.coding.service.impl;

import com.lwk.coding.dao.IUserInfoDao;
import com.lwk.coding.entity.UserInfo;
import com.lwk.coding.service.IUserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author kai
 * @date 2020-01-01 23:49
 */
@Slf4j
@Service
public class UserInfoService extends BaseServiceImpl<UserInfo> implements IUserInfoService {

    @Autowired
    private IUserInfoDao userInfoDao;

    @Override
    public List<UserInfo> getAllObj() {
        return userInfoDao.getAllObj();
    }

    @Override
    public boolean saveObj(UserInfo o) {
        try {
            userInfoDao.saveObj(o);
            return true;
        } catch (Exception e) {
            log.error("UserInfoService.saveObj调用报错!", e);
        }
        return false;
    }

    @Override
    public boolean deleteObjById(Integer id) {
        try {
            userInfoDao.deleteObjById(id);
            return true;
        } catch (Exception e) {
            log.error("UserInfoService.deleteObjById调用报错!", e);
        }
        return false;
    }

    @Override
    public boolean updateObj(UserInfo o) {
        try {
            userInfoDao.updateObj(o);
            return true;
        } catch (Exception e) {
            log.error("UserInfoService.updateObj调用报错!", e);
        }
        return false;
    }

    @Override
    public UserInfo getObjByName(String name) {
        return userInfoDao.getObjByName(name);
    }
}
