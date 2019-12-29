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
public class GoodInfo {
    private Integer giId;
    private String giName;
    private String giDesc;
}
