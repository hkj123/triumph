package com.springboot.mybatis.controller;

import com.google.common.collect.Lists;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.springboot.mybatis.utils.Common;
import com.springboot.mybatis.utils.HttpClientUtil;
import com.springboot.mybatis.utils.PEMFormatter;
import com.springboot.mybatis.utils.SSLClient;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.net.ssl.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.*;

@RestController   //注意模板 需要这个
@RequestMapping("/http")

public class HttpsController extends BaseController {

    public static void main(String[] args) {

        String certString = Common.cert;
        String keyString = Common.key;
        String caString = Common.caString;
        try {
            certString = PEMFormatter.stripPEM(certString);

            keyString = PEMFormatter.stripPEM(keyString);

            if (caString != null) {
                caString = PEMFormatter.stripPEM(caString);
            }
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        String jsonString;
        try {
            //******************获取token*************************
//            String url = "https://qloudpdp.pditdap.service.sd/auth/realms/openbankingrs256/protocol/openid-connect/token";
//            Map<String, String> headerBody = new HashMap<>();
//            headerBody.put("Content-Type", "application/x-www-form-urlencoded");
//
//            Map<String, String> entityBody = new HashMap<>();
//            entityBody.put("grant_type", "client_credentials");
//            entityBody.put("client_id", "client1-mtls-RS256-PS256");
//            entityBody.put("scope", "accounts");
//
//            String httpOrgCreateTestRtn = doPost(url, headerBody, entityBody, "utf-8",certString,keyString,caString);
            //******************获取token*************************

            //******************根据code获取token*************************
            String url = "https://qloudpdp.pditdap.service.sd/auth/realms/openbankingrs256/protocol/openid-connect/token";
            Map<String, String> headerBody = new HashMap<>();
            headerBody.put("Content-Type", "application/x-www-form-urlencoded");

            Map<String, String> entityBody = new HashMap<>();
            entityBody.put("grant_type", "authorization_code");
            entityBody.put("code", "01a69b6f-6fe1-4930-8025-21064c94970d.9b58a8bf-7dbe-42b7-920b-f4b09b884430.941ec92e-e322-497b-bc3d-35b9d494e67e");
            entityBody.put("redirect_uri", "https://obptest.pditdap.service.sd/test/a/test/callback");
            entityBody.put("client_id", "client1-mtls-RS256-PS256");

            String httpOrgCreateTestRtn = doPost(url, headerBody, entityBody, "utf-8",certString,keyString,caString);
            //******************获取token*************************

        } catch (Exception e) {
        }
    }

//    public static RestTemplate createRestTemplate() throws UnrecoverableKeyException, KeyManagementException, CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, KeyStoreException, IOException {
//        HttpClient httpClient = createHttpClient();
//
////        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
//
////        restTemplate.getInterceptors().add(new LoggingRequestInterceptor(getMessage(), log, env.getObject("mutual_tls_authentication")));
//
//        return restTemplate;
//    }

    public static HttpClient createHttpClient(String cert1,String key1,String caString1) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, KeyStoreException, IOException, UnrecoverableKeyException, KeyManagementException {
        HttpClientBuilder builder = HttpClientBuilder.create()
                .useSystemProperties();

        KeyManager[] km = null;

            // TODO: move this to an extractor?
//            String clientCert = cert1;
////            String clientKey = key1;
////            String clientCa = caString1;
        String certString = Common.cert;
        String keyString = Common.key;
        String caString = Common.caString;
        Security.addProvider(new BouncyCastleProvider());
            byte[] certBytes = Base64.getDecoder().decode(certString);
            byte[] keyBytes = Base64.getDecoder().decode(keyString);

            X509Certificate cert = generateCertificateFromDER(certBytes);
            RSAPrivateKey key = generatePrivateKeyFromDER(keyBytes);

            ArrayList<X509Certificate> chain = Lists.newArrayList(cert);
            if (caString != null) {
                byte[] caBytes = Base64.getDecoder().decode(caString);
                chain.addAll(generateCertificateChainFromDER(caBytes));
            }

            KeyStore keystore = KeyStore.getInstance("JKS");
            keystore.load(null);
            keystore.setCertificateEntry("cert-alias", cert);
            keystore.setKeyEntry("key-alias", key, "changeit".toCharArray(), chain.toArray(new Certificate[chain.size()]));

            KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            keyManagerFactory.init(keystore, "changeit".toCharArray());

            km = keyManagerFactory.getKeyManagers();


        TrustManager[] trustAllCerts = new TrustManager[] {
                new X509TrustManager() {

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }
                }
        };

        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(km, trustAllCerts, new java.security.SecureRandom());

        builder.setSSLContext(sc);

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sc,
                new String[] { "TLSv1.2" },
                null,
                NoopHostnameVerifier.INSTANCE);

        builder.setSSLSocketFactory(sslConnectionSocketFactory);

        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                .register("https", sslConnectionSocketFactory)
                .register("http", new PlainConnectionSocketFactory())
                .build();

        HttpClientConnectionManager ccm = new BasicHttpClientConnectionManager(registry);
        builder.setConnectionManager(ccm);

        HttpClient httpClient = builder.build();
        return httpClient;
    }

    public static X509Certificate generateCertificateFromDER(byte[] certBytes) throws CertificateException {
        CertificateFactory factory = CertificateFactory.getInstance("X.509");

        return (X509Certificate) factory.generateCertificate(new ByteArrayInputStream(certBytes));
    }

    public static RSAPrivateKey generatePrivateKeyFromDER(byte[] keyBytes) throws InvalidKeySpecException, NoSuchAlgorithmException {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory factory = KeyFactory.getInstance("RSA");

        return (RSAPrivateKey) factory.generatePrivate(spec);
    }

    public static List<X509Certificate> generateCertificateChainFromDER(byte[] chainBytes) throws CertificateException {
        CertificateFactory factory = CertificateFactory.getInstance("X.509");

        ArrayList<X509Certificate> chain = new ArrayList<>();
        ByteArrayInputStream in = new ByteArrayInputStream(chainBytes);
        while (in.available() > 0) {
            chain.add((X509Certificate) factory.generateCertificate(in));
        }

        return chain;
    }

    public static String doPost(String url, Map<String, String> headerBody, Map<String, String> entityBody, String charset,String cert,String key,String ca) {
        HttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = createHttpClient(cert,key,ca);

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
                    System.out.println("hukaijia*************"+result);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

}
