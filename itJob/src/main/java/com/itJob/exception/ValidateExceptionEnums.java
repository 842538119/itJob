package com.itJob.exception;

/**
 * @Description: 异常枚举 类
 * @Author: LRJ
 * @Date: 2019/11/28 14:29
 */
public enum ValidateExceptionEnums {
    USER_HAS_EXIST                  ("0001","该注册账号已存在"),
    USER_HAS_NO_EXIST                ("0002","您输入的账号不存在"),
    USER_IS_NO_PASSED              ("0003","账号未审核，请联系管理员"),
    USER_IS_DISABLED               ("0004","账号被冻结，请联系管理员"),
    USER_TYPE_IS_INCORRECT               ("0005","账号类型有误，请重新选择"),
    PASSWORD_IS_INCORRECT             ("0006","您输入的密码有误"),
    SERVER_OTHER_ERROR            ("1099","其他异常");//枚举类如果写方法的话，此处需要写分号

    private String ecode;
    private String emsg;

    ValidateExceptionEnums(String ecode, String emsg) {
        this.ecode = ecode;
        this.emsg = emsg;
    }

    public String getEcode() {
        return ecode;
    }

    public String getEmsg() {
        return emsg;
    }
}
