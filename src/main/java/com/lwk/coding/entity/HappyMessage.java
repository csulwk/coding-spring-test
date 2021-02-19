package com.lwk.coding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author kai
 * @date 2021-02-19 18:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HappyMessage {
    private Integer hmId;
    private String hmName;
    private String hmMessage;
    private String createTime;
    private String updateTime;
    private String updateBy;
}
