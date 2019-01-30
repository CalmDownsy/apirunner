package com.zhangsy.project.testcase;

import com.alibaba.fastjson.JSON;
import com.zhangsy.project.base.BaseTest;
import com.zhangsy.project.bean.User;
import com.zhangsy.project.client.RestClient;
import com.zhangsy.project.constant.Constants;
import com.zhangsy.project.utils.TestUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Auther: zhangsy
 * @Date: 2019/1/16 14:04
 * @Description:
 */
public class ApiTest extends BaseTest {

    @Test(description = "test11111")
    public void test() throws IOException {
        Reporter.log("这个是成功的");
//        RestClient.doGet1(properties.getProperty("HOST") + "/api/users?page=2");

    }

    @Test(description = "test33333")
    public void test3() throws IOException {
//        RestClient.doGet1(properties.getProperty("HOST") + url);
        Reporter.log("这个是失败的");
        Assert.fail();
    }

    @Test(dataProvider = "postData")
    public void test2(String url, String name, String job) throws IOException {
        HashMap<String, String> headMap = new HashMap<String, String>();
        headMap.put("Content-Type", "application/json");
        User user = new User();
        user.setJob(name);
        user.setName(job);
        String s = JSON.toJSONString(user);
        CloseableHttpResponse httpResponse = RestClient.doPost(properties.getProperty("HOST") + url, s, headMap);
        Assert.assertEquals(httpResponse.getStatusLine().getStatusCode(), Constants.RESPONSE_STATUS_CODE_201);
    }

    @DataProvider(name = "postData")
    public Object[][] post() throws IOException {
        String exceldata = properties.getProperty("exceldata");
        return TestUtils.transformData(exceldata, 0);
    }

    @DataProvider(name = "getData")
    public Object[][] get() throws IOException {
        String exceldata = properties.getProperty("exceldata");
        return TestUtils.transformData(exceldata, 1);
    }
}
