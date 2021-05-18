package com.lft.miaosha.common.util;

import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Class Name:      HttpClientUtils
 * Package Name:    com.lft.miaosha.common.util
 * <p>
 * Function: 		A {@code HttpClientUtils} object With Some FUNCTION.
 * Date:            2021-05-17 22:50
 * <p>
 * @author Laifengting / E-mail:laifengting@foxmail.com
 * @version 1.0.0
 * @since JDK 8
 */
public class HttpClientUtils {
    public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    
    Class clazz;
    private static Logger log = LoggerFactory.getLogger(HttpClientUtils.class);
    
    /**
     * get请求获取请求数据
     * @param url
     * @return
     */
    public static String httpGet(String url) {
        String getData;
        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            getData = response.body().string();
            
        } catch (Exception e) {
            log.info("【发送 GET 请求出现异常】！" + e.getMessage());
            return "-1";
        }
        return getData;
    }
    
    /**
     * post请求将请求数据放在请求体中获取请求数据
     * @param url
     * @param json json数据的生成方式（可选）；
     *             JSONObject json=new JSONObject();
     *             json.put("name","张三");
     *             json.put("sex","男");等
     *             json.toString()
     * @return
     */
    public static String httpPost(String url, String json) {
        String postData;
        OkHttpClient httpClient = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        try {
            Response response = httpClient.newCall(request).execute();
            postData = response.body().string();
        } catch (Exception e) {
            log.info("【发送 POST 请求出现异常】！" + e.getMessage());
            return "-1";
        }
        return postData;
    }
    
    /**
     * post请求 表单请求 在请求体中获取请求数据
     * @param url
     * @param json json数据的生成方式（可选）；
     *             JSONObject json=new JSONObject();
     *             json.put("name","张三");
     *             json.put("sex","男");等
     *             json.toString()
     * @return
     */
    public static Map<String, Object> httpFormPost(String url, Map<String, String> map) {
        String postData;
        // 构建连接
        OkHttpClient httpClient = new OkHttpClient();
        // 创建多媒体建造器
        MultipartBody.Builder builder = new MultipartBody.Builder();
        // 设置多媒体的类型为 表单
        builder.setType(MultipartBody.FORM);
        // 获取 map 中的键值对
        Set<Map.Entry<String, String>> entries = map.entrySet();
        // 遍历 entries
        for (Map.Entry<String, String> entry : entries) {
            // 在构建器中加入表单数据，key value
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        // 构造 表单体
        MultipartBody formBody = builder.build();
        // 创建请求对象
        Request request = new Request.Builder()
                // 请求地址
                .url(url)
                // 请求的表单体
                .post(formBody)
                .build();
        
        // 创建一个 Map 用于接受响应内容。
        Map<String, Object> responseMap = new HashMap<>();
        try {
            // 执行请求
            Response response = httpClient.newCall(request).execute();
            // 获取响应头信息
            Headers headers = response.headers();
            // 获取响应体信息
            postData = response.body().string();
            // 放入 map 中
            responseMap.put("headers", headers);
            responseMap.put("postData", postData);
        } catch (Exception e) {
            log.info("【发送 POST 请求出现异常】！" + e.getMessage());
            return new HashMap<>();
        }
        return responseMap;
    }
}
