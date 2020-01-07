package com.lwk.coding.constant;

/**
 * @author kai
 * @date 2019-12-29 22:13
 */
public enum RetMsg {
    SUCCESS(CodeSet.CODE_S000, "返回成功"),
    RET_E101(CodeSet.CODE_E101, "输入参数为空"),
    RET_E102(CodeSet.CODE_E102, "响应错误2"),
    RET_E103(CodeSet.CODE_E103, "响应错误3"),
    RET_E104(CodeSet.CODE_E104, "响应错误4"),
    RET_E999(CodeSet.CODE_E999, "系统异常");

    private String code;
    private String msg;

    RetMsg(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static RetMsg getByRetCode(String code) {
        for (RetMsg retMsg : RetMsg.values()) {
            if (code.equals(retMsg.getCode())) {
                return retMsg;
            }
        }
        return RetMsg.RET_E999;
    }
}
