package com.example.ding.study.http;


import com.example.ding.study.consts.MyHttpURL;

import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

/**
 * Created by ding on 2015/10/14.
 */
public class HttpUtils {

    public HttpUtils() {
        try {
            String path = MyHttpURL.LOGIN_URL;
            URL url = new URL(path);
            URLConnection rulConnection = url.openConnection();
            // 此处的urlConnection对象实际上是根据URL的
            // 请求协议(此处是http)生成的URLConnection类的子类HttpURLConnection,
            // 故此处最好将其转化为HttpURLConnection类型的对象,
            // 以便用到HttpURLConnection更多的API.如下:
            HttpURLConnection httpUrlConnection = (HttpURLConnection) rulConnection;

            /**
             *  HttpURLConnection对象参数问题 :
             */
            // 设置是否向httpUrlConnection输出，因为这个是post请求，参数要放在http正文内，因此需要设为true, 默认情况下是false;
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

            /**
             * 连接，从上述第2条中url.openConnection()至此的配置必须要在connect之前完成，
             */
            httpUrlConnection.connect();

            // HttpURLConnection连接:
            // 此处getOutputStream会隐含的进行connect(即：如同调用上面的connect()方法，
            // 所以在开发中不调用上述的connect()也可以)。
            OutputStream outStrm = httpUrlConnection.getOutputStream();

            // HttpURLConnection写数据与发送数据问题：
            // 现在通过输出流对象构建对象输出流对象，以实现输出可序列化的对象。
            ObjectOutputStream objOutputStrm = new ObjectOutputStream(outStrm);
            // 向对象输出流写出数据，这些数据将存到内存缓冲区中
            objOutputStrm.writeObject(new String("我是测试数据"));
            // 刷新对象输出流，将任何字节都写入潜在的流中（些处为ObjectOutputStream）
            objOutputStrm.flush();
            // 关闭流对象。此时，不能再向对象输出流写入任何数据，先前写入的数据存在于内存缓冲区中,
            // 在调用下边的getInputStream()函数时才把准备好的http请求正式发送到服务器
            objOutputStrm.close();

            // 调用HttpURLConnection连接对象的getInputStream()函数,
            // 将内存缓冲区中封装好的完整的HTTP请求电文发送到服务端。
            InputStream inStrm = httpUrlConnection.getInputStream(); // <===注意，实际发送请求的代码段就在这里

            // 上边的httpConn.getInputStream()方法已调用,本次HTTP请求已结束,下边向对象输出流的输出已无意义，
            // 既使对象输出流没有调用close()方法，下边的操作也不会向对象输出流写入任何数据.
            // 因此，要重新发送数据时需要重新创建连接、重新设参数、重新创建流对象、重新写数据、
            // 重新发送数据(至于是否不用重新这些操作需要再研究)
            objOutputStrm.writeObject(new String(""));
            httpUrlConnection.getInputStream();

        } catch (Exception e) {
        } finally {

        }


    }

    /**
     * 总结:
     --发送POST请求必须设置允许输出
     --不要使用缓存,容易出现问题.
     --在开始用HttpURLConnection对象的setRequestProperty()设置,就是生成HTML文件头.
     * @param requestUrl
     * @param data
     * @throws Exception
     */
    public void post(String requestUrl,List<Map<String,Object>> data) throws Exception{
        //1)创建URL对象
        URL realUrl = new URL(requestUrl);
        //2)通过HttpURLConnection对象,向网络地址发送请求
        HttpURLConnection conn = (HttpURLConnection) realUrl.openConnection();
        //3)设置容许输出:conn.setDoOutput(true);
        conn.setDoOutput(true);
        //4)设置不使用缓存:conn.setUseCaches(false);
        conn.setUseCaches(false);
        //5)设置使用POST的方式发送:
        conn.setRequestMethod("POST");
        //6)设置维持长连接:
        conn.setRequestProperty("Connection", "Keep-Alive");
        //7)设置文件字符集
        conn.setRequestProperty("Charset", "UTF-8");
        //8)设置文件长度
        conn.setRequestProperty("Content-Length", String.valueOf(data));
        // 9)设置文件类型
        conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");

        //10）设置HTTP请求头
        conn.setRequestProperty("Accept","image/gif," +
                        " image/jpeg, image/pjpeg," +
                        " image/pjpeg, " +
                        "application/x-shockwave-flash," +
                        " application/xaml+xml, " +
                        "application/vnd.ms-xpsdocument," +
                        " application/x-ms-xbap," +
                        " application/x-ms-application," +
                        " application/vnd.ms-excel," +
                        " application/vnd.ms-powerpoint, " +
                        "application/msword, */*");
        //设置语言
        conn.setRequestProperty("Accept-Language","zh-CN");

        //11)以流的方式输出.
    }

    /**
     *总结:
     --记得设置连接超时,如果网络不好,Android系统在超过默认时间会收回资源中断操作.
     --返回的响应码200,是成功.
     --在Android中对文件流的操作和JAVA SE上面是一样的.
     --在对大文件的操作时,要将文件写到SDCard上面,不要直接写到手机内存上.
     --操作大文件是,要一遍从网络上读,一遍要往SDCard上面写,减少手机内存的使用.这点很重要,面试经常会被问到.
     --对文件流操作完,要记得及时关闭.
     * @param path
     * @throws Exception
     */
    public void get(String path) throws Exception{

        //1)创建一个URL对象
        URL url = new URL(path);
        //2)利用HttpURLConnection对象从网络中获取网页数据
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //3)设置连接超时
        conn.setConnectTimeout(6*1000);
        //4)对响应码进行判断
        if (conn.getResponseCode() != 200)    //从Internet获取网页,发送请求,将网页以流的形式读回来
            throw new RuntimeException("请求url失败");
        //5)得到网络返回的输入流
        InputStream is = conn.getInputStream();
        //String result = readData(is, "UTF-8"); //文件流输入出文件用outStream.write
        conn.disconnect();
    }

}
