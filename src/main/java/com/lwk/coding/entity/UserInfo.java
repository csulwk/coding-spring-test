package com.lwk.coding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author kai
 * @date 2019-12-29 23:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfo {
    private Integer uiId;
    private String uiName;
}
