package com.lwk.coding.mapper;

import com.lwk.coding.entity.HappyMessage;
import com.lwk.coding.entity.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 通过使用@MapperScan可以指定要扫描的Mapper类的包的路径,
 * 每个Mapper接口中不用再单独配置@Mapper
 * @author kai
 * @date 2020-01-01 21:57
 */
@Repository("happyMessageMapper")
public interface IHappyMessageMapper {

    /**
     * 查询所有信息
     * @return
     */
    public List<HappyMessage> selectMessage();

    /**
     * 保存信息
     * @param message
     * @return
     */
    public int saveMessage(HappyMessage message);
}
