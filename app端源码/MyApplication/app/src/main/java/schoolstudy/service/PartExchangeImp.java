package schoolstudy.service;

import android.text.TextUtils;
import android.util.Log;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import schoolstudy.view.entity.PartExchange;
import schoolstudy.view.entity.Static;

/**
 * Created by liaoliao on 2015/11/29.
 */
public class PartExchangeImp {
    //上传评论
    public void doPartExchange(PartExchange partExchange) throws Exception{
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params,100000);            //设置连接超时时长
        HttpConnectionParams.setSoTimeout(params, 200000);                    //设置响应超时
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http",new PlainSocketFactory(),80));
        ClientConnectionManager manager = new SingleClientConnManager(params,registry);
        HttpClient client = new DefaultHttpClient(manager,params);
        HttpPost post = new HttpPost(Static.DOPAETEXCHANGE);
        List<NameValuePair> data = new ArrayList<NameValuePair>();
        data.add(new BasicNameValuePair("name", partExchange.getName()));
        data.add(new BasicNameValuePair("content", partExchange.getContent()));
        data.add(new BasicNameValuePair("fk_PId", partExchange.getPartId()));
        Log.e("blackdog",partExchange.getName()+partExchange.getContent()+partExchange.getPartId());
        post.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));
        HttpResponse response = client.execute(post);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new ServiceException("服务器返回失败");
        }
    }

    public List<PartExchange> getPartExchanges(String partId) throws Exception{
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params,100000);            //设置连接超时时长
        HttpConnectionParams.setSoTimeout(params, 200000);                    //设置响应超时
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http",new PlainSocketFactory(),80));
        ClientConnectionManager manager = new SingleClientConnManager(params,registry);
        HttpClient client = new DefaultHttpClient(manager,params);
        HttpPost post = new HttpPost(Static.GETPAETEXCHANGE);
        List<NameValuePair> data = new ArrayList<NameValuePair>();
        data.add(new BasicNameValuePair("fk_PId", partId));
        //Log.i("提示：进行网络访问时的dId为：",department);
        post.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));
        HttpResponse response = client.execute(post);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new ServiceException("服务器返回失败");
        } String content = EntityUtils.toString(response.getEntity(),"utf-8");
        List<PartExchange> list = new ArrayList<>();
        Log.e("blackdog",content);
        JSONArray array = new JSONArray(content);
        for(int i = 0;i<array.length();i++){
            JSONObject object = (JSONObject) array.get(i);
            PartExchange partExchange = new PartExchange();
            partExchange.setName(object.getString("name"));
            partExchange.setContent(object.getString("content"));
            list.add(partExchange);
        }
        return list;
    }
}
