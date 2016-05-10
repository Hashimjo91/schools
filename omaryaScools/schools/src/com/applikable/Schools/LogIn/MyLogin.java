package com.applikable.Schools.LogIn;

import android.app.ProgressDialog;
import android.content.Context;
import android.preference.PreferenceManager;
import android.util.Log;
import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

/**
 * Created by Hashim on 13/12/2014.
 */
public class MyLogin {
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/36.0.1985.143 Safari/537.36";
    Context context;
    ProgressDialog dialog;
    HttpURLConnection conn;
    CookieHandler cHandler;
    String postParams;
    String postParams_sis;
    List<String> cookies;
    boolean wronUserError = false;
    boolean internetError = false;
    private String pageURL;
    private String uName;
    private String password;
    private List<Cookie> cookies1;
    private DefaultHttpClient httpClient;
    private String yearParams;

    public MyLogin(String pageURL, String uName, String password) {
        this.pageURL = pageURL;
        this.uName = uName;
        this.password = password;
    }

    public String LogIn(Context context) {
        this.context = context;
        String html_home = null;
        try {
            CookieHandler.setDefault(new CookieManager());

//

            html_home = getPageContentFirst("http://eduwave.elearning.jo/Eduwave/ElearningMe.aspx");
            Document document = Jsoup.parse(html_home);
            Elements element = document.select("body > form > input");
            String __VIEWSTATE = element.attr("value");
            postParams = "__VIEWSTATE=" + __VIEWSTATE + "&__EVENTTARGET=&__EVENTARGUMENT=&UserName=4006102762&Password=4006102762&ImageButton1.x=91&ImageButton1.y=8&txtvalue=&txtLanguage=1";
            html_home = sendLogInPost("http://eduwave.elearning.jo/Eduwave/ElearningMe.aspx?", postParams);
            html_home = getPageContentFirst("http://eduwave.elearning.jo/Eduwave/StudentHomeA.aspx");
        } catch (Exception e) {
        }

        return html_home;

    }

    public String loginjsoup(String user, String pass) {
        String newsurl = null;
        try {
            Connection.Response loginForm = Jsoup.connect("http://eduwave.elearning.jo/Eduwave/ElearningMe.aspx")
                    .method(Connection.Method.GET)
                    .timeout(0)
                    .execute();

            Document document = loginForm.parse();

            Elements element = document.select("body > form > input");
            String __VIEWSTATE = element.attr("value");

            document = Jsoup.connect("http://eduwave.elearning.jo/Eduwave/ElearningMe.aspx")
                    .data("__VIEWSTATE", __VIEWSTATE)
                    .data("__EVENTTARGET", "")
                    .data("__EVENTARGUMENT", "")
                    .data("UserName", user)
                    .data("Password", pass)
                    .data("ImageButton1.x", "47")
                    .data("ImageButton1.y", "15")
                    .data("txtvalue", "")
                    .data("txtLanguage", "1")
                    .cookies(loginForm.cookies())
                    .post();

// Elements element1 =document.select("body > form > input");
//    String __VIEWSTATE1=element.attr("value");
//    __VIEWSTATE1=Uri.encode(__VIEWSTATE1);
//    document=  Jsoup.connect("http://eduwave.elearning.jo/eduwave/eduwave_sms/studentmarks.aspx?__EVENTTARGET=MenuHeaderRedirect&__EVENTARGUMENT=eduwave_sms%2studentmarks.aspx&__VIEWSTATE="+__VIEWSTATE1+"&bWidth=0&names=&SearchControl1%3ASearchTextBox=")
            document = Jsoup.connect("http://eduwave.elearning.jo/eduwave/eduwave_sms/studentmarks.aspx")

                    .cookies(loginForm.cookies()).timeout(0)
                    .get();

            newsurl = document.toString();


        } catch (IOException e)

        {
            e.printStackTrace();
        }

        return newsurl;
    }


    public String login(String url, Context context) {
        String html_home;
        try {
            this.context = context;
            initHttpClient();
            html_home = getPage("http://eduwave.elearning.jo/Eduwave/ElearningMe.aspx", 10);
            Document document = Jsoup.parse(html_home);
            Elements element = document.select("body > form > input");
            String __VIEWSTATE = element.attr("value");
            postParams = "__VIEWSTATE=" + __VIEWSTATE + "&__EVENTTARGET=&__EVENTARGUMENT=&UserName=4006102762&Password=4006102762&ImageButton1.x=91&ImageButton1.y=8&txtvalue=&txtLanguage=1";
            getPage("http://eduwave.elearning.jo/Eduwave/ElearningMe.aspx?" + postParams, 10);
            return getPage(url, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }

    public String getPage(String url, int Type) throws Exception {
//		initHttpClient();
        CookieStore cookieStore = new BasicCookieStore();
        HttpContext localContext = new BasicHttpContext();
        localContext.setAttribute(ClientContext.COOKIE_STORE, cookieStore);
        HttpGet request = new HttpGet(url);
        request.setHeader("User-Agent", USER_AGENT);
        try {
            preSetCookie();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HttpResponse response = httpClient.execute(request);
        postSetCookie();
        BufferedReader rd = new BufferedReader(new InputStreamReader(response
                .getEntity().getContent(), "utf-8"));
        StringBuffer result = new StringBuffer();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
//		System.out.println(result.toString());
        // uncomment to print html data
        // longInfo(result.toString(), 10);
        return result.toString();
    }

    private void postSetCookie() {
        String serialized_cookies = "";
        if (cookies1 != null)
            for (Cookie cookie : cookies1) {
                serialized_cookies += cookie.getName() + ":"
                        + cookie.getValue() + ":" + cookie.getDomain() + ",";
            }

        PreferenceManager.getDefaultSharedPreferences(context).edit()
                .putString("cookie", serialized_cookies).commit();
    }

    private void preSetCookie() {

        String serialized_cookie = PreferenceManager
                .getDefaultSharedPreferences(context).getString("cookie", "");
        if (serialized_cookie == "") {
            return;
        }

        String[] parts = serialized_cookie.split(",");
        for (String scookie : parts) {
            String[] keyValueSets = scookie.split(":");
            if (keyValueSets.length < 2)
                continue;
            BasicClientCookie cookie = new BasicClientCookie(keyValueSets[0],
                    keyValueSets[1]);
            if (keyValueSets.length == 3)
                cookie.setDomain(keyValueSets[2]);
            httpClient.getCookieStore().addCookie(cookie);
        }
    }

    public void initHttpClient() throws Exception {
        this.httpClient = new DefaultHttpClient();
        this.httpClient = this.sslClient(httpClient);
    }

    private DefaultHttpClient sslClient(HttpClient client) {

        try {
            X509TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] xcs,
                                               String string) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] xcs,
                                               String string) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };
            SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(null, new TrustManager[]{tm}, null);
            SSLSocketFactory ssf = new MySSLSocketFactory(ctx);
            ssf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            ClientConnectionManager ccm = client.getConnectionManager();
            SchemeRegistry sr = ccm.getSchemeRegistry();
            sr.register(new Scheme("https", ssf, 443));

            return new DefaultHttpClient(ccm, client.getParams());
        } catch (Exception ex) {
            return null;
        }
    }


    private String sendLogInPost(String url, String postParams)
            throws IOException {
        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();
        conn.setUseCaches(false);
        conn.setRequestMethod("POST");

        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.115 Safari/537.36");
        conn.setRequestProperty("Host", "eduwave.elearning.jo");

        for (String cookie : this.cookies) {
            conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            Log.e("Cookie........", cookie.split(";", 1)[0]);

        }

        conn.setDoOutput(true);
        conn.setDoInput(true);
        // Send post request

        DataOutputStream wr = new DataOutputStream(conn.getOutputStream());

        wr.writeBytes(postParams);
        wr.flush();
        wr.close();

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + postParams);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine).toString();

        }
        Log.e("html:", response.toString());
        in.close();

        return response.toString();

    }


    public String getPageContentFirst(String url) throws Exception {
        URL obj = new URL(url);
        conn = (HttpURLConnection) obj.openConnection();
        conn.setRequestMethod("GET");
        conn.setUseCaches(false);
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/40.0.2214.115 Safari/537.36");
        conn.setRequestProperty("Host", "eduwave.elearning.jo");
        if (cookies != null)
            for (String cookie : this.cookies) {
                conn.addRequestProperty("Cookie", cookie.split(";", 1)[0]);
            }

        int responseCode = conn.getResponseCode();
        System.out.println("\nSending 'GET' request to URL : " + url);
        System.out.println("Response Code here : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(
                conn.getInputStream(), "UTF-8"));
        String inputLine;


        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);

            if (response.toString().contains("INVALID_REQUEST")) {
                wronUserError = true;
            }
        }

        in.close();

        // Get the response cookies
        setCookies(conn.getHeaderFields().get("Set-Cookie"));


        return response.toString();

    }


    public void setCookies(List<String> cookies) {
        this.cookies = cookies;
    }


}
