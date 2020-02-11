package com.lwk.coding.entity.exception;

import lombok.*;

/**
 * 自定义异常类
 * @author kai
 * @date 2020-02-11 18:55
 */
@Getter
@AllArgsConstructor
public class WebException extends RuntimeException {
    private String code;
}
