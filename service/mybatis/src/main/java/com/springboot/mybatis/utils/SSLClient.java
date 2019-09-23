package com.springboot.mybatis.utils;

import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.*;

import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;


public class SSLClient extends DefaultHttpClient {
    public SSLClient() throws Exception {
        super();
        SSLContext sslContext = SSLContext.getInstance("TLS");
        X509TrustManager tm = new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };
        sslContext.init(null, new TrustManager[]{tm}, null);
        SSLSocketFactory ssf = new SSLSocketFactory(sslContext,SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
        ClientConnectionManager ccm = this.getConnectionManager();
        SchemeRegistry sr = ccm.getSchemeRegistry();
        sr.register(new Scheme("https", 443, ssf));
        //创建SSLContext对象，并使用我们指定的信任管理器初始化
//        KeyStore clientStore  = KeyStore.getInstance(KeyStore.getDefaultType());
//        InputStream instream = Thread.currentThread().getContextClassLoader().getResourceAsStream("https-keystore.jks");
//        String keyStorePwd = "obp@qloudfin.com";
//        try {
//            clientStore.load(instream, keyStorePwd.toCharArray());
//        } finally {
////            instream.close();
//        }
//        SSLContext sslCtx = SSLContext.getInstance("TLS");
//        KeyManagerFactory kmfactory = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//        kmfactory.init(clientStore, keyStorePwd != null ? keyStorePwd.toCharArray() : null);
//        KeyManager[] keymanagers = kmfactory.getKeyManagers();
//        sslCtx.init(keymanagers, new TrustManager[]{tm}, null);
//        SSLConnectionSocketFactory sslConnectionFactory = new SSLConnectionSocketFactory(sslCtx);
//        Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create().register("https", sslConnectionFactory).register("http", new PlainConnectionSocketFactory()).build();
//        PoolingHttpClientConnectionManager pcm = new PoolingHttpClientConnectionManager(registry);
//        HttpClientBuilder hcb = HttpClientBuilder.create();
//        hcb.setConnectionManager(pcm);
//        CloseableHttpClient httpClient = hcb.build();

    }
}
