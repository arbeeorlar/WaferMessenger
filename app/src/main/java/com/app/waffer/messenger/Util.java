package com.app.waffer.messenger;

/**
 * Created by Abeeb.Isola on 8/15/2018.
 */


import android.annotation.SuppressLint;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import java.net.URI;
import java.security.KeyStore;


public class Util {


  static  String LOG_TAG = "WAFER_MESSENGER";
  static  String url  = "https://restcountries.eu/rest/v2/all";

    public static HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            MySSLCertificate sf = new MySSLCertificate(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            params.setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }


    public static boolean IsconnectionPresent(Context context) {
        ConnectivityManager cMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission")
        NetworkInfo netInfo = cMgr.getActiveNetworkInfo();
        if ((netInfo != null) && (netInfo.getState() != null)) {
            return netInfo.getState().equals(NetworkInfo.State.CONNECTED);
        }
        return false;
    }

    public static String GetCountries() {
        String result = null;
        try {
            HttpClient httpclient = getNewHttpClient();
            HttpGet request = new HttpGet();
            URI website = new URI(url);
            request.setURI(website);
            request.addHeader("Content-Type", "application/json");
            HttpResponse response = httpclient.execute(request);
            if (response != null) {
                result = EntityUtils.toString(response.getEntity());
                Log.i(LOG_TAG, result);
            }
        } catch (Exception exp) {
            exp.printStackTrace();
            result = null;
        }
        Log.i(LOG_TAG,result);
        return result;

    }


}
