package com.applikable.Schools.LogIn;

import org.apache.http.conn.ssl.SSLSocketFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.*;
import java.security.cert.X509Certificate;

public class MySSLSocketFactory extends SSLSocketFactory {
    SSLContext sslContext = SSLContext.getInstance("TLS");

    public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
        super(truststore);

        TrustManager tm = new X509TrustManager() {
            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }

            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }
        };

        sslContext.init(null, new TrustManager[]{tm}, null);
    }

    public MySSLSocketFactory(SSLContext context) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, UnrecoverableKeyException {
        super(null);
        sslContext = context;
    }

    @Override
    public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
        SSLSocket mySocket = (SSLSocket) sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        mySocket.setEnabledProtocols(new String[]{"SSLv3"});
        return mySocket;
    }

    @Override
    public Socket createSocket() throws IOException {
        return sslContext.getSocketFactory().createSocket();
    }
}

