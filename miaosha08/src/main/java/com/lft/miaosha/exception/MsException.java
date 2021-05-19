package com.lft.miaosha.exception;

import com.lft.miaosha.common.exception.ExceptionCode;

/**
 * Class Name:      MiaoshaGlobalException
 * Package Name:    com.lft.miaosha.exception
 * <p>
 * Function: 		A {@code MiaoshaGlobalException} object With Some FUNCTION.
 * Date:            2021-05-15 23:32
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class MsException extends RuntimeException {
    private static final long serialVersionUID = 1L;
    
    private ExceptionCode exceptionCode;
    
    public MsException(ExceptionCode exceptionCode) {
        super(exceptionCode.getMessage());
        this.exceptionCode = exceptionCode;
    }
    
    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }
}
