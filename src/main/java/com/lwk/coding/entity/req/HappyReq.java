package com.lwk.coding.entity.req;

import com.lwk.coding.constant.CodeSet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

/**
 * @author kai
 * @date 2021-02-19 18:44
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class HappyReq {
    @NotBlank(message = CodeSet.CODE_E105)
    @Length(message = CodeSet.CODE_E105, max = 64)
    private String name;

    @NotBlank(message = CodeSet.CODE_E105)
    @Length(message = CodeSet.CODE_E105, max = 512)
    private String message;
}
