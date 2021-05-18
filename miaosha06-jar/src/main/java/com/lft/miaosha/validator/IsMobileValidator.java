package com.lft.miaosha.validator;

import com.lft.miaosha.common.util.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Class Name:      IsMobileValidator
 * Package Name:    com.lft.miaosha.validator
 * <p>
 * Function: 		自定义校验器
 * Date:            2021-05-15 17:40
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class IsMobileValidator implements ConstraintValidator<IsMobile, String> {
    
    private boolean required = false;
    
    @Override
    public void initialize(IsMobile constraintAnnotation) {
        required = constraintAnnotation.required();
    }
    
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (required) {
            return ValidatorUtil.isMobile(value);
        } else {
            if (StringUtils.isEmpty(value)) {
                return true;
            } else {
                return ValidatorUtil.isMobile(value);
            }
        }
    }
}
