package com.lwk.coding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author kai
 * @date 2019-12-29 23:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OrderInfo {
    private Integer oiId;
    private String oiUserId;
    private String oiGoodId;
    private String oiDesc;
}
