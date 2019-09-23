package com.springboot.mybatis.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.*;

public class HttpsClientUtil {

    //    "Content-Type", "application/x-www-form-urlencoded"
    public static String doPost(String url, Map<String, String> headerBody, Map<String, String> entityBody, String charset) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = new SSLClient();

//            httpClient = new DefaultHttpClient();    http
            httpPost = new HttpPost(url);
            if (Objects.nonNull(headerBody)) {
                Iterator<Map.Entry<String, String>> item = headerBody.entrySet().iterator();
                while (item.hasNext()) {
                    Map.Entry<String, String> it = item.next();
                    httpPost.addHeader(it.getKey(), it.getValue());
                }
            }

            List<NameValuePair> nameValuePairs = new ArrayList<>();
            if (Objects.nonNull(entityBody)) {
                Iterator<Map.Entry<String, String>> item = entityBody.entrySet().iterator();
                List<NameValuePair> list = new ArrayList<NameValuePair>();
                while (item.hasNext()) {
                    Map.Entry<String, String> it = item.next();
                    nameValuePairs.add(new BasicNameValuePair(it.getKey(), it.getValue()));
                }
            }

            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, charset));
            HttpResponse response = httpClient.execute(httpPost);
            if (response != null) {
                HttpEntity resEntity = response.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }
}
