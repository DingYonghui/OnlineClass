package com.example.ding.study.http;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;

/**
 * Created by ding on 2015/10/19.
 */
public class MyHttpClient {

    //声明一个静态的HttpClient对象
    public static HttpClient client;

    /**
     *  实例化，并返回Httpclient的对象
     */
   public static HttpClient getClient() throws Exception{
       /**
        * client的参数设置
        * HttpParams 、 SchemeRegistry --->  ClientConnectionManager---> HttpClient
        */
       HttpParams params = new BasicHttpParams();
       HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);//通过params设置请求的字符集
       HttpConnectionParams.setConnectionTimeout(params, 3000);//设置客户端与服务器的超时时间--->ConnectionTimeoutException
       HttpConnectionParams.setSoTimeout(params,3000);//设置服务器响应的超时时间--->SocketTimeoutException

       SchemeRegistry schreg = new SchemeRegistry();
       schreg.register(new Scheme("http",PlainSocketFactory.getSocketFactory(),80));
       schreg.register(new Scheme("https", PlainSocketFactory.getSocketFactory(),433));

       //配置httpClient的参数设置
       ClientConnectionManager conman = new ThreadSafeClientConnManager(params,schreg);

       //创建HttpClient并设置参数
       client = new DefaultHttpClient(conman,params);
       return client;
   }
}
