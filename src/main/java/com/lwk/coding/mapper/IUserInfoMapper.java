package com.lwk.coding.mapper;

import com.lwk.coding.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通过使用@MapperScan可以指定要扫描的Mapper类的包的路径,
 * 每个Mapper接口中不用再单独配置@Mapper
 * @author kai
 * @date 2020-01-01 21:57
 */
@Repository("userInfoMapper")
public interface IUserInfoMapper {

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
