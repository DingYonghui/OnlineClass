package com.example.jack.myapplication.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.util.LruCache;
import android.widget.ImageView;

import com.example.jack.myapplication.R;
import com.example.jack.myapplication.consts.MyHttpURL;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 用于下载、显示图片
 * Created by ding on 2015/10/26.
 */
public class ImageLoader {

    //缓存
    private LruCache<String, Bitmap> mCaches;
    private String bitmapFileName ;
    //构造方法
    public ImageLoader() {
        //获取最大可用内存
        int maxMemory = (int) Runtime.getRuntime().maxMemory();
        int cacheSize = maxMemory / 4;
        mCaches = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                //在每次存入缓存时调用，存入图片大小
                return value.getByteCount();
            }
        };
    }

    //将图片写入缓存
    public void addBitmapToCache(String bitmapFileName, Bitmap bitmap) {
        //先判断bitmap是否为空
        if (getBitmapFromCache(bitmapFileName) == null) {
            mCaches.put(bitmapFileName, bitmap);
        }
    }

    //根据url获得缓存的图片
    public Bitmap getBitmapFromCache(String bitmapFileName) {
        if(bitmapFileName!=null){
            return mCaches.get(bitmapFileName);
        }else {
            return null ;
        }
    }

    /**
     * 通过url获得图片
     *
     * @param  urlString
     * @return
     */
    public Bitmap getBitmapFromURL(String urlString) {

//        if(urlString.equals(MyHttpURL.IP+null)||urlString.equals(MyHttpURL.IP)){
//         return null ;
//        }

        Bitmap bitmap;
        InputStream is = null;
        URL url;
        try {
            url = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            Log.i("getBitmapFromURL参数url为：",urlString);

//            Log.i("状态码：",connection.getResponseCode()+"");

            //如果连接网络失败
            //if(connection.getResponseCode() == 404){
           //     return null ;
           // }else {
                Log.i("状态码：",connection.getResponseCode()+"");
                InputStream ins = connection.getInputStream();

                //缓冲流
                is = new BufferedInputStream(ins);

                //将is转化为bitmap
                bitmap = BitmapFactory.decodeStream(is);
                //添加网络中下载的bitmap到缓存和本地sd卡
                addBitmapToCache(urlString,bitmap);
                addBitmapToFile(urlString,bitmap);
                //关闭连接
                connection.disconnect();
                return bitmap;
          //  }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    File dir = null;
    /**
     * 将图片文件写入文件
     * @param url
     * @param bitmap
     */
    public void addBitmapToFile(String url,Bitmap bitmap){
        //Environment.getExternalStorageState()方法用于获取SDCard的状态，如果手机装有SDCard，
        //并且可以进行读写，那么方法返回的状态等于Environment.MEDIA_MOUNTED
        if(Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            FileOutputStream fos = null ;
            //创建根目录文件对象
            if(dir == null){
                dir = new File("/mnt/sdcard/test/");
            }

            //如果根目录文件对象不存在
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File bitmapFile = null ;
            //创建图片文件对象
            if(bitmapFile == null){
                bitmapFile = new File("/mnt/sdcard/test/" +
                        url.substring(url.lastIndexOf("/") + 1));
            }
            //如果图片文件对象不存在，则创建新的文件
            if (!bitmapFile.exists()) {
                try {
                    bitmapFile.createNewFile();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            try {
                if(fos == null ){
                    //得到文件输出流
                    fos = new FileOutputStream(bitmapFile);
                }
                //将图片文件写进文件中
                bitmap.compress(Bitmap.CompressFormat.PNG , 100 , fos);
                fos.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * 通过AsyncTask显示图片：从缓存中找--->从本地文件中找--->从网络中找
     * @param imageView
     * @param imageUrl
     */
    public void showImageByAsyncTask(ImageView imageView, String imageUrl) {

        int i = 0 ;
        //根据url构建图片文件名
        bitmapFileName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1);
        //从缓存中取名为url的图片
        Bitmap bitmap = getBitmapFromCache(bitmapFileName);
        Log.i("提示：","从缓存中得到的图片"+bitmap);
        //如果缓存中没有，则先从本地文件中找
        if (bitmap == null) {

            //Log.i("提示：","SD卡中图片的文件名"+bitmap);
            //创建根目录文件对象
            if(dir == null){
                dir = new File("/mnt/sdcard/test/");
            }
            //得到文件列表
            File[] cacheFiles = dir.listFiles();
           // Log.i("提示：","文件列表为："+cacheFiles);
            Log.i("提示：","文件列表的大小："+cacheFiles.length);

            if (cacheFiles != null) {

                //逐一查找
                for (; i < cacheFiles.length; i++) {
                    //如果文件列表中存在与图片文件名相同的文件，则跳出for循环
                    if (bitmapFileName.equals(cacheFiles[i].getName())) {
                        break;//跳出for循环
                    }
                }

                //如果i < cacheFiles.length，说明存在图片文件
                if (i < cacheFiles.length) {
                    imageView.setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/test/" + bitmapFileName));
                    Log.i("提示：", "显示的图片从SD卡中来");
                    //将SD卡的图片放到缓存中
                    //addBitmapToCache(bitmapFileName, BitmapFactory.decodeFile("/mnt/sdcard/test/" + bitmapFileName));
                    return;//跳出方法
                }else{
                    //如果文件列表都没有，则从网络中找
                    LessonsIconAsyncTask task = new LessonsIconAsyncTask(imageView);
                    //执行task
                    task.execute(imageUrl);
                }

            }

            //文件夹都不存在，则图片也不存在
            else{
                //如果文件列表都没有，则从网络中找
                LessonsIconAsyncTask task1 = new LessonsIconAsyncTask(imageView);
                //执行task
                task1.execute(imageUrl);
            }

        } else {
            //缓存中存在，则直接显示图片
            imageView.setImageBitmap(bitmap);
            Log.i("提示：","显示的图片从缓存中来");
        }
    }

    /**
     * 自定义的AsyncTask，传进的类型、返回过程、返回的类型
     */
    private class LessonsIconAsyncTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView mImageView ;
        //构造方法
        private LessonsIconAsyncTask(ImageView imageView) {
            mImageView = imageView ;
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //图片url
            String url = params[0];
            //从网络中获取图片
            Bitmap bitmap = getBitmapFromURL(url);
            //如果从网络中获得的图片不为空
            if (bitmap != null) {  //  1、将图片写入缓存 2、将图片写入文件
                //将图片以url为标志写入缓存
                addBitmapToCache(bitmapFileName, bitmap);
                //将图片写入文件
                addBitmapToFile(bitmapFileName, bitmap);
            }
            return bitmap;
        }

        //上面doInBackground返回的bitmap在这里处理
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //如果该imageView不为空且bitmap不为空
            if (mImageView != null && bitmap != null) {
                //imageView显示图片
                mImageView.setImageBitmap(bitmap);
                Log.i("提示：", "显示的图片从网络中来");
            }else {
                mImageView.setImageResource(R.mipmap.no);
                Log.i("提示：", "显示的图片为默认图片");
            }
        }
    }

}
