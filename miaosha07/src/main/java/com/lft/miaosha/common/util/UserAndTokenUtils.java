package com.lft.miaosha.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lft.miaosha.entity.po.MiaoshaUser;

import java.io.File;
import java.io.RandomAccessFile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Name:      UserAndTokenUtils
 * Package Name:    com.lft.miaosha.common.util
 * <p>
 * Function: 		A {@code UserAndTokenUtils} object With Some FUNCTION.
 * Date:            2021-05-17 17:50
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class UserAndTokenUtils {
    
    /**
     * 生成用户
     * @param userCount
     * @return
     */
    private static List<MiaoshaUser> createUser(Integer userCount) {
        List<MiaoshaUser> users = new ArrayList<>(userCount);
        System.out.println("============ 准备开始创建用户 ============");
        // 循环创建用户
        for (int i = 0; i < userCount; i++) {
            MiaoshaUser user = new MiaoshaUser();
            user.setGmtCreated(new Date());
            user.setGmtLastLogin(new Date());
            user.setHead("/img/default.jpeg");
            user.setId(18900000000L + i);
            user.setLoginCount(1);
            user.setNickname("user" + i);
            user.setPassword(Md5Util.inputPassToDbPass("123456", user.getSalt()));
            user.setSalt(RandomSaltUtil.getRandomSalt());
            users.add(user);
        }
        System.out.println("============ 创建用户结束 ============");
        return users;
    }
    
    /**
     * 向数据库中批量插入用户 和 地址
     * @param users
     * @throws Exception
     */
    public static void insertIntoDb(List<MiaoshaUser> users) throws Exception {
        System.out.println("============ 准备开始插入用户到数据库 ============");
        String url = "jdbc:mysql://192.168.247.180:3306/miaosha?allowMultiQueries=true&rewriteBatchedStatements=true&useUnicode=true" +
                "&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false&nullCatalogMeansCurrent=true";
        String userName = "root";
        String password = "201314";
        String driverClass = "com.mysql.cj.jdbc.Driver";
        // 使用 JDBC工具类创建连接
        Connection conn = JDBCUtils.getConnection(url, driverClass, userName, password);
        
        String sql = "INSERT INTO miaosha_user(" +
                "id," +
                "nickname," +
                "password," +
                "salt," +
                "head," +
                "login_count) " +
                "VALUES(?,?,?,?,?,?)";
        String sql2 = "INSERT INTO address(name, user_id, address_detail) VALUES(?,?,?)";
        
        PreparedStatement pstmt = conn.prepareStatement(sql);
        PreparedStatement pstmt2 = conn.prepareStatement(sql2);
        for (int i = 0; i < users.size(); i++) {
            MiaoshaUser user = users.get(i);
            pstmt.setLong(1, user.getId());
            pstmt.setString(2, user.getNickname());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getSalt());
            pstmt.setString(5, user.getHead());
            pstmt.setInt(6, user.getLoginCount());
            pstmt.addBatch();
            
            pstmt2.setString(1, user.getNickname());
            pstmt2.setLong(2, user.getId());
            pstmt2.setString(3, "江苏省 南通市 通州区 西亭镇 西亭圆通速递 " + user.getNickname() + " 收");
            pstmt2.addBatch();
        }
        // 执行
        pstmt.executeBatch();
        pstmt2.executeBatch();
        // 关闭资源
        JDBCUtils.closeResource(conn, pstmt2);
        JDBCUtils.closeResource(conn, pstmt);
        System.out.println("============ 插入用户到数据库完成 ============");
        
    }
    
    /**
     * 请求登录页面生成 token 并创建生成 token.txt 文件
     * @param users
     * @throws Exception
     */
    public static void createTokenByLogin(List<MiaoshaUser> users) throws Exception {
        System.out.println("============ 准备开始创建Token ============");
        // 设置登录请求地址
        String urlString = "http://192.168.247.180:8080/login/do/login";
        
        // 设置生成 token 的文件位置
        File file = new File("D:/token.txt");
        // 文件如果存在，删除
        if (file.exists()) {
            file.delete();
        }
        // 创建随机访问文件对象,权限为读写
        RandomAccessFile raf = new RandomAccessFile(file, "rw");
        file.createNewFile();
        // 设置偏移量
        raf.seek(0);
        // 遍历每一个用户访问登录
        for (int i = 0; i < users.size(); i++) {
            MiaoshaUser user = users.get(i);
            
            // URL url = new URL(urlString);
            // HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            // conn.setRequestMethod("POST");
            // conn.setDoOutput(true);
            // OutputStream out = conn.getOutputStream();
            // String params = "mobile=" + user.getId() + "&password=" + Md5Util.inputPassToFormPass("123456");
            // out.write(params.getBytes());
            // out.flush();
            // InputStream inputStream = conn.getInputStream();
            // ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // byte[] buffer = new byte[2048];
            // int length = 0;
            // while ((length = inputStream.read(buffer)) >= 0) {
            //     baos.write(buffer, 0, length);
            // }
            // inputStream.close();
            // baos.close();
            // String response = new String(baos.toByteArray());
            
            Map<String, String> map = new HashMap<>();
            map.put("password", Md5Util.inputPassToFormPass("123456"));
            map.put("mobile", user.getId().toString());
            
            // System.out.println(map);
            
            // // 从 cookie 中获取 token
            // Map<String, Object> responseMap = HttpClientUtils.httpFormPost(urlString, map);
            // Headers headers = (Headers) responseMap.get("headers");
            // String setCookie = headers.get("Set-Cookie");
            // // System.out.println("setCookie: " + setCookie);
            // int tokenStart = setCookie.indexOf("=");
            // int tokenEnd = setCookie.indexOf(";");
            // String token = setCookie.substring(tokenStart + 1, tokenEnd);
            
            // 从响应体中获取 token
            Map<String, Object> responseMap = HttpClientUtils.httpFormPost(urlString, map);
            String response = (String) responseMap.get("postData");
            // System.out.println("response: " + response);
            
            JSONObject jsonObject = JSON.parseObject(response);
            
            String data = jsonObject.get("data").toString();
            JSONObject dataJson = JSON.parseObject(data);
            
            String token = dataJson.get("token").toString();
            // System.out.println("token: " + token);
            
            // System.out.println("Create token : " + user.getId());
            String row = user.getId() + "," + token;
            raf.seek(raf.length());
            raf.write(row.getBytes());
            raf.write("\r\n".getBytes());
            // System.out.println("Write to file " + user.getId());
        }
        raf.close();
        System.out.println("============ 创建 Token 结束 ============");
    }
    
    public static void main(String[] args) throws Exception {
        List<MiaoshaUser> users = createUser(5000);
        insertIntoDb(users);
        createTokenByLogin(users);
    }
}
