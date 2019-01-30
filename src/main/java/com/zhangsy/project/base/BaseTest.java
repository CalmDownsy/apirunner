package com.zhangsy.project.base;

import com.zhangsy.project.client.RestClient;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Auther: zhangsy
 * @Date: 2019/1/16 13:12
 * @Description: 测试基类
 */
public class BaseTest {

    public Properties properties;

    public BaseTest() {
        properties = new Properties();
        ClassLoader classLoader = BaseTest.class.getClassLoader();
        InputStream is = classLoader.getResourceAsStream("config.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
