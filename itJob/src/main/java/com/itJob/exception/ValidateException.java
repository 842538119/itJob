package com.itJob.exception;

/**
 * @Description: 校验异常 类
 * @Author: LRJ
 * @Date: 2019/11/28 14:24
 */
public class ValidateException extends RuntimeException {
    private ValidateExceptionEnums validateExceptionEnums;
    private String errorDetail;

    public ValidateException(ValidateExceptionEnums validateExceptionEnums,String errorDetail){
        this.errorDetail=errorDetail;
        this.validateExceptionEnums=validateExceptionEnums;
    }

    public ValidateException(ValidateExceptionEnums validateExceptionEnums){
        this.validateExceptionEnums=validateExceptionEnums;
    }

    public ValidateExceptionEnums getExceptionEnums() {
        return validateExceptionEnums;
    }

    public String getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(String errorDetail) {
        this.errorDetail = errorDetail;
    }
}
