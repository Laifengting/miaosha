package com.lft.miaosha.common.util;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class Name:      ValidatorUtil
 * Package Name:    com.lft.miaosha.common.util
 * <p>
 * Function: 		校验工具
 * Date:            2021-05-15 15:08
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class ValidatorUtil {
    
    /**
     * 校验手机号码
     * @param mobileNumber
     * @return
     */
    public static Boolean isMobile(String mobileNumber) {
        // 手机号码正则 11 位数 以 13 - 19 开头
        Pattern MOBILE_PATTERN = Pattern.compile("^(?:(?:\\+|00)86)?1[3-9]\\d{9}$");
        // 如果号码为空，返回 false
        if (StringUtils.isEmpty(mobileNumber)) {
            return false;
        }
        Matcher matcher = MOBILE_PATTERN.matcher(mobileNumber);
        return matcher.matches();
    }
}
