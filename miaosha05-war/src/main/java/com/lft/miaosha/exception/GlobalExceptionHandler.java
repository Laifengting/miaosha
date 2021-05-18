package com.lft.miaosha.exception;

import com.lft.miaosha.common.exception.ExceptionCode;
import com.lft.miaosha.common.result.R;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Class Name:      GlobalExceptionHandler
 * Package Name:    com.lft.miaosha.exception
 * <p>
 * Function: 		A {@code GlobalExceptionHandler} object With Some FUNCTION.
 * Date:            2021-05-15 23:16
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {
    
    /**
     * 最精准匹配
     * @param e
     * @return
     */
    @ExceptionHandler (value = Exception.class)
    public R exceptionHandler(Exception e) {
        return R.ERROR().code(ExceptionCode.EXCEPTION.getCode()).message(ExceptionCode.EXCEPTION.getMessage());
    }
    
    @ExceptionHandler (value = MsException.class)
    public R msExceptionHandler(MsException e) {
        return R.ERROR().code(e.getExceptionCode().getCode()).message(e.getExceptionCode().getMessage());
    }
    
    @ExceptionHandler (value = NullPointerException.class)
    public R nullPointerExceptionHandler(NullPointerException e) {
        return R.ERROR().code(ExceptionCode.NULL_POINTER_EXCEPTION.getCode()).message(ExceptionCode.NULL_POINTER_EXCEPTION.getMessage());
    }
    
    @ExceptionHandler (value = IllegalArgumentException.class)
    public R illegalArgumentExceptionHandler(IllegalArgumentException e) {
        return R.ERROR().code(ExceptionCode.ILLEGAL_ARGUMENT_EXCEPTION.getCode())
                .message(ExceptionCode.ILLEGAL_ARGUMENT_EXCEPTION.getMessage());
    }
    
    @ExceptionHandler (value = BindException.class)
    public R bindExceptionHandler(BindException e) {
        return R.ERROR().code(ExceptionCode.BIND_EXCEPTION.getCode()).message(ExceptionCode.BIND_EXCEPTION.getMessage());
    }
    
    @ExceptionHandler (value = ArithmeticException.class)
    public R arithmeticExceptionHandler(ArithmeticException e) {
        return R.ERROR().code(ExceptionCode.ARITHMETIC_EXCEPTION.getCode()).message(ExceptionCode.ARITHMETIC_EXCEPTION.getMessage());
    }
    
    @ExceptionHandler (value = ArrayIndexOutOfBoundsException.class)
    public R arrayIndexOutOfBoundsException(ArrayIndexOutOfBoundsException e) {
        return R.ERROR().code(ExceptionCode.ARITHMETIC_EXCEPTION.getCode()).message(ExceptionCode.ARITHMETIC_EXCEPTION.getMessage());
    }
}
