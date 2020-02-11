package com.lwk.coding.aop;

import com.alibaba.fastjson.JSONObject;
import com.lwk.coding.constant.CodeSet;
import com.lwk.coding.constant.RetMsg;
import com.lwk.coding.entity.exception.WebException;
import com.lwk.coding.util.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

/**
 * 异常处理类
 * @author kai
 * @date 2020-02-11 18:17
 */
@RestControllerAdvice
@Slf4j
public class WebExceptionHandle {

    /**
     * 如果是已定义的异常，返回对应的错误信息；否则返回未定义异常信息
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public JSONObject exceptionMsg(Exception e) {
        if (e instanceof WebException) {
            //自定义异常处理
            WebException exp = (WebException) e;
            return ResultUtil.resp(RetMsg.getByRetCode(exp.getCode()));
        }else if (e instanceof MethodArgumentNotValidException) {
            // 参数校验异常处理
            MethodArgumentNotValidException exp = (MethodArgumentNotValidException) e;
            BindingResult result = exp.getBindingResult();
            String errorCode = CodeSet.CODE_E999;
            if (result.hasFieldErrors()) {
                FieldError fieldError = result.getFieldError();
                errorCode = fieldError.getDefaultMessage();
            }
            return ResultUtil.resp(RetMsg.getByRetCode(errorCode));
        }else {
            // 其他未定义异常处理
            e.printStackTrace();
            return ResultUtil.resp(RetMsg.ERROR);
        }
    }
}
