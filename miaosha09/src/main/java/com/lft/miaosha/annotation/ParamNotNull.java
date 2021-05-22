package com.lft.miaosha.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Class Name:      ParamNotNull
 * Package Name:    com.lft.miaosha.annotation
 * <p>
 * Function: 		A {@code ParamNotNull} object With Some FUNCTION.
 * Date:            2021-05-20 13:15
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
@Documented
// 用在参数上
@Target ({ElementType.PARAMETER})
// 注解保留到运行阶段
@Retention (RetentionPolicy.RUNTIME)
public @interface ParamNotNull {

}
