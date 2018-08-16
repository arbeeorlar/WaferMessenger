package com.app.waffer.messenger;

/**
 * Created by Abeeb.Isola on 6/30/2016.
 */

    import java.io.IOException;
    import java.net.Socket;
    import java.net.UnknownHostException;
    import java.security.KeyManagementException;
    import java.security.KeyStore;
    import java.security.KeyStoreException;
    import java.security.NoSuchAlgorithmException;
    import java.security.SecureRandom;
    import java.security.UnrecoverableKeyException;
    import java.security.cert.CertificateException;
    import java.security.cert.X509Certificate;

    import javax.net.ssl.HostnameVerifier;
    import javax.net.ssl.HttpsURLConnection;
    import javax.net.ssl.SSLContext;
    import javax.net.ssl.SSLSession;
    import javax.net.ssl.TrustManager;
    import javax.net.ssl.X509TrustManager;

    import org.apache.http.client.HttpClient;
    import org.apache.http.conn.ssl.SSLSocketFactory;
    public class MySSLCertificate extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");
        protected static final String TAG = "NukeSSLCerts";

        public MySSLCertificate(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(truststore);

            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }




        /**
         * Enables https connections
         */
        public static void nuke() {
            try {
                TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
                    public X509Certificate[] getAcceptedIssuers() {
                        X509Certificate[] myTrustedAnchors = new X509Certificate[0];
                        return myTrustedAnchors;
                    }

                    @Override
                    public void checkClientTrusted(X509Certificate[] certs, String authType) {
                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] certs, String authType) {
                    }
                } };

                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, trustAllCerts, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String arg0, SSLSession arg1) {
                        return true;
                    }
                });
            } catch (Exception e) {
                e.toString();
            }
        }

    }



