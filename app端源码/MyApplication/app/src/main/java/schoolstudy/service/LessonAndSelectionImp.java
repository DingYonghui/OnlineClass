package schoolstudy.service;

import android.os.Environment;
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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import schoolstudy.view.entity.Course;
import schoolstudy.view.entity.Part;
import schoolstudy.view.entity.Section;
import schoolstudy.view.entity.Static;

/**
 * Created by blackdog on 2015/10/28.
 */
public class LessonAndSelectionImp implements LessonAndSelection {

    /**
     * 这个类用于获得系别的课程信息
     * @param
     * @throws Exception
     */
    public List<Course> getLesson(String department) throws Exception {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params,100000);            //设置连接超时时长
        HttpConnectionParams.setSoTimeout(params, 200000);                    //设置响应超时
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http",new PlainSocketFactory(),80));
        ClientConnectionManager manager = new SingleClientConnManager(params,registry);
        HttpClient client = new DefaultHttpClient(manager,params);
        HttpPost post = new HttpPost(Static.GETLESSONS);
        List<NameValuePair> data = new ArrayList<NameValuePair>();
        data.add(new BasicNameValuePair("fk_DId", department));
        //Log.i("提示：进行网络访问时的dId为：",department);
        post.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));
        HttpResponse response = client.execute(post);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new ServiceException("服务器返回失败");
        }
        String content = EntityUtils.toString(response.getEntity(),"utf-8");
        if(content == null){
            return null;
        }
        JSONArray array = new JSONArray(content);
        List<Course> courses = new ArrayList<>();
        for(int i = 0;i<array.length();i++){
            JSONObject obj = (JSONObject)array.get(i);;
            String title = obj.getString("teacher");
            String teacher = obj.getString("content");
            String id = obj.getString("id");
            String imageUri = null;
            if(!obj.getString("imagePath").equals("http://"+Static.IP+":8080null")) {
                    Log.i("blackdog",obj.getString("imagePath"));
                   imageUri = saveImage(id, obj.getString("imagePath"));
            }
            Course course = new Course();
            course.setId(id);
            course.setTitle(title);
            course.setImageUri(imageUri);
            course.setLecturer(teacher);
            courses.add(course);
        }
        Log.i("提示：进行网络访后得到的课程为：",courses.toString());
        return courses;
    }

    /**
     * 这个方法是用于获得章节的数据:通过课程Id
     * @param lessonId   课程的Id
     * @return
     * @throws Exception
     */
    public List<Section> getSectionAndPart(String lessonId) throws Exception {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params,100000);            //设置连接超时时长
        HttpConnectionParams.setSoTimeout(params, 200000);                    //设置响应超时
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http",new PlainSocketFactory(),80));
        ClientConnectionManager manager = new SingleClientConnManager(params,registry);
        HttpClient client = new DefaultHttpClient(manager,params);
        HttpPost post = new HttpPost(Static.GETSECTIONANDPARTS);
        List<NameValuePair> data = new ArrayList<NameValuePair>();
        data.add(new BasicNameValuePair("lessonId", lessonId));
        post.setEntity(new UrlEncodedFormEntity(data, HTTP.UTF_8));
        HttpResponse response = client.execute(post);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new ServiceException("服务器返回失败");
        }
        String content = EntityUtils.toString(response.getEntity(),"utf-8");
        if(content == null){
            return null;
        }
        JSONArray array = new JSONArray(content);
        List<Section> sections = new ArrayList<>();
        for(int i = 0;i<array.length();i++){
            Section section = new Section();
            JSONObject object = (JSONObject) array.get(i);
            section.setName(object.getString("SName"));
            JSONArray str = (JSONArray) object.get("parts");
            JSONArray array1 = new JSONArray(str.toString());
            List<Part> parts = new ArrayList<>();
            for(int j = 0;j<array1.length();j++){
                Part part = new Part();
                JSONObject object1 = (JSONObject) array1.get(j);
                part.setName(object1.getString("PName"));
                part.setId(object1.getString("pk_PId"));
                part.setVideoUri(object1.getString("PVideoPath"));
                parts.add(part);
            }
            section.setCount(parts);
            sections.add(section);
        }
        return sections;
    }

    /*
    这个类适用于保存图片并且返回图片在手机的位置
     */
    private String saveImage(String id,String uri) throws IOException, ServiceException {
        HttpParams params = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(params,10000);            //设置连接超时时长
        HttpConnectionParams.setSoTimeout(params, 20000);                    //设置响应超时
        SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http",new PlainSocketFactory(),80));
        ClientConnectionManager manager = new SingleClientConnManager(params,registry);
        HttpClient client = new DefaultHttpClient(manager,params);
        HttpPost post = new HttpPost(uri);
        HttpResponse response = client.execute(post);
        if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK){
            throw new ServiceException("服务器返回失败");
        }
        InputStream in = response.getEntity().getContent();

        String path = Environment.getExternalStorageDirectory()+File.separator+"lessonssss"+File.separator+id+".jpg";
        String path1 = Environment.getExternalStorageDirectory()+File.separator+"lessonssss";
        File file1 = new File(path1);
        if(!file1.exists()){
            file1.mkdirs();
        }
        Log.i("blackdog",path);
        File file = new File(path);
        if(file.exists()){
            return path;
        }else{
            file.createNewFile();
        }
        OutputStream outputStream = new FileOutputStream(file);
        byte[] buffer = new byte[1024*10];
        int read = -1;
        while((read = in.read(buffer))!=-1){
            outputStream.write(buffer,0,read);
        }
        in.close();;
        outputStream.close();
      return path;
    };
}
