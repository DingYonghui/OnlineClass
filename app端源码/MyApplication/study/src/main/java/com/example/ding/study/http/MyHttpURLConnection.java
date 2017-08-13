package com.example.ding.study.http;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by ding on 2015/10/19.
 */
public class MyHttpURLConnection  {

    public static HttpURLConnection  httpUrlConnection = null ;

    public static HttpURLConnection  getHttpURLConnection(String path) throws Exception{
        URL url = new URL(path);
        URLConnection rulConnection = url.openConnection();
        // 此处的urlConnection对象实际上是根据URL的
        // 请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection,
        // 故此处最好将其转化为HttpURLConnection类型的对象,
        // 以便用到HttpURLConnection更多的API.如下:
        httpUrlConnection = (HttpURLConnection) rulConnection;

        // 设置是否向httpUrlConnection输出， 默认情况下是false。因为这个是post请求，参数要放在http正文内，因此需要设为true。
        httpUrlConnection.setDoOutput(true);
        // 设置是否从httpUrlConnection读入，默认情况下是true;
        httpUrlConnection.setDoInput(true);
        // Post 请求不能使用缓存
        httpUrlConnection.setUseCaches(false);
        // 设定传送的内容类型是可序列化的java对象
        // (如果不设此项,在传送序列化对象时,当WEB服务默认的不是这种类型时可能抛java.io.EOFException)
        httpUrlConnection.setRequestProperty("Content-type", "application/x-java-serialized-object");
        // 设定请求的方法为"POST"，默认是GET
        httpUrlConnection.setRequestMethod("POST");
        //设置超时
        httpUrlConnection.setConnectTimeout(30000);
        httpUrlConnection.setReadTimeout(30000);

        //连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成。
        httpUrlConnection.connect();

        return httpUrlConnection ;
    }



}
