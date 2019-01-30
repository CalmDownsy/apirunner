package com.zhangsy.project.client;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: zhangsy
 * @Date: 2019/1/16 14:00
 * @Description: 封装HTTP请求
 */
public class RestClient {

    private static Logger logger = LoggerFactory.getLogger(RestClient.class);

    public static void doGet1(String requestUrl) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestUrl);
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
//        状态码
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        logger.info("status code: {}", statusCode);
        logger.info("================================================================");
//        响应内容
        String entityString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        JSONObject jsonObject = JSON.parseObject(entityString);
        logger.info("response json: {}", jsonObject);
        logger.info("================================================================");
//        响应头信息
        Header[] allHeaders = httpResponse.getAllHeaders();
//        将响应头转换成k-v
        HashMap<String, String> hashMap = new HashMap<String, String>();
        for (Header header : allHeaders) {
            hashMap.put(header.getName(), header.getValue());
        }
        logger.info("response header: {}", hashMap);
    }

    public static CloseableHttpResponse doGet(String requestUrl) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(requestUrl);
        return httpClient.execute(httpGet);
    }

    public static CloseableHttpResponse doPost(String requestUrl, String entityString, HashMap<String, String> headMap) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(requestUrl);
        httpPost.setEntity(new StringEntity(entityString));
        for (Map.Entry<String,String> entry : headMap.entrySet()) {
            httpPost.setHeader(entry.getKey(), entry.getValue());
        }
        return httpClient.execute(httpPost);
    }
}
