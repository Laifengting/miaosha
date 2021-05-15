package com.lft.miaosha.common.util;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * Class Name:      Md5Util
 * Package Name:    com.lft.miaosha.common.util
 * <p>
 * Function: 		A {@code Md5Util} object With Some FUNCTION.
 * Date:            2021-05-15 13:41
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class Md5Util {
    
    private static final String salt = "1a2b3c4d";
    
    public static void main(String[] args) {
        String string = "123456"; // d3b1294a61a07da9b49b6e22b2cbd7f9
        System.out.println(inputPassToFormPass(string));
        System.out.println(formPassToDbPass(inputPassToFormPass(string), "1a2b3c4d5e6f7g8h9i"));
        System.out.println(inputPassToDbPass(string, "1a2b3c4d5e6f7g8h9i"));
    }
    
    /**
     * 明文密码加固定盐转成表单密码
     * @param inputPass
     * @return
     */
    public static String inputPassToFormPass(String inputPass) {
        String string = "" + salt.charAt(0) + salt.charAt(2) + inputPass + salt.charAt(5) + salt.charAt(4);
        return md5(string);
    }
    
    /**
     * 表单密码加随机盐转成数据库保存密码
     * @param formPass
     * @param saltDb
     * @return
     */
    public static String formPassToDbPass(String formPass, String saltDb) {
        String string = "" + salt.charAt(0) + salt.charAt(2) + formPass + salt.charAt(5) + salt.charAt(4);
        return md5(string);
    }
    
    /**
     * 公共的明文密码转数据库保存密码
     * @param inputPass
     * @param saltDb
     * @return
     */
    public static String inputPassToDbPass(String inputPass, String saltDb) {
        String formPass = inputPassToFormPass(inputPass);
        String dbPass = formPassToDbPass(formPass, saltDb);
        return dbPass;
    }
    
    /**
     * MD5 转化
     * @param src
     * @return
     */
    public static String md5(String src) {
        return DigestUtils.md5Hex(src);
    }
}
