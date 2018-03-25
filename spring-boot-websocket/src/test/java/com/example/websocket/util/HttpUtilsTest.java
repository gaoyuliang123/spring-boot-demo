package com.example.websocket.util;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;
@RunWith(SpringRunner.class)
@SpringBootTest
public class HttpUtilsTest {
    @Test
    public void doGet() throws Exception {
        String host = "http://jisutqybmf.market.alicloudapi.com";
        String path = "/weather/query";
        String method = "GET";
        // 你自己的AppCode
        String appcode = "d0882aaf20b0401d842a632152814a8a";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("city", "安仁");
        querys.put("citycode", "citycode");
        querys.put("cityid", "cityid");
        querys.put("ip", "ip");
        querys.put("location", "location");


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doGet(host, path, method, headers, querys);
            System.out.println("============="+response.toString()+"=============");
            //获取response的body
            System.out.println("-------------"+EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void doPost() throws Exception {
    }

    @Test
    public void doPost1() throws Exception {
    }

    @Test
    public void doPost2() throws Exception {
    }

    @Test
    public void doPut() throws Exception {
    }

    @Test
    public void doPut1() throws Exception {
    }

    @Test
    public void doDelete() throws Exception {
    }

}