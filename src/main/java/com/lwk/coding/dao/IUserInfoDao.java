package com.lwk.coding.dao;

import com.lwk.coding.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author kai
 * @date 2020-01-01 21:57
 */
@Mapper
@Repository("userInfoDao")
public interface IUserInfoDao {

    /**
     * 根据用户名查询用户信息
     * @param name
     * @return
     */
    public UserInfo getObjByName(String name);

    /**
     * 查询当前所有的用户信息
     * @return
     */
    public List<UserInfo> getAllObj();

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveObj(UserInfo user);

    /**
     * 根据用户ID删除用户信息
     * @param id
     * @return
     */
    public int deleteObjById(Integer id);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public int updateObj(UserInfo user);
}
