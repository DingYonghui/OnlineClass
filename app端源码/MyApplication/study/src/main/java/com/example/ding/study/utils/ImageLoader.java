package com.example.ding.study.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;
import android.util.LruCache;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.ding.study.R;
import com.example.ding.study.adapter.LessonListAdapter;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * 用于下载、显示图片
 * Created by ding on 2015/10/26.
 */
public class ImageLoader {

    //显示课程图片的控件
    private ImageView mImageView;
    //课程图片的url
    private String mUrl;
    //缓存
    private LruCache<String, Bitmap> mCaches;
    //
    private ListView mListView;
    //LessonsAsyncTask  自定义AsyncTask的集合
    private Set<LessonsAsyncTask> mTask;


    //构造方法
    public ImageLoader(ListView listView) {

        mListView = listView;
        mTask = new HashSet<>();

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
    public void addBitmapToCache(String url, Bitmap bitmap) {
        if (getBitmapFromCache(url) == null) {
            mCaches.put(url, bitmap);
        }
    }

    //将图片写入缓存
    public Bitmap getBitmapFromCache(String url) {
        return mCaches.get(url);
    }

    /**
     * 通过url获得图片
     *
     * @param urlString
     * @return
     */
    public Bitmap getBitmapFromURL(String urlString) {
        Bitmap bitmap;
        InputStream is = null;
        URL url;
        try {
            url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            //缓冲流
            is = new BufferedInputStream(connection.getInputStream());
            //将is转化为bitmap
            bitmap = BitmapFactory.decodeStream(is);
            //关闭连接
            connection.disconnect();

            //模拟网速过慢
            //Thread.sleep(3000);

            return bitmap;
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

    public void showImageByAsyncTask(ImageView imageView, final String url) {
        //从缓存中取名为url的图片
        Bitmap bitmap = getBitmapFromCache(url);
        //如果缓存中没有，则必须从网络中下载
        if (bitmap == null) {

            /**
             * 查找本地文件是否有该图片
             */
            String bitmapName = url.substring(url.lastIndexOf("/") + 1);
            File cacheDir = new File("/mnt/sdcard/test/");
            File[] cacheFiles = cacheDir.listFiles();
            int i = 0;
            if (null != cacheFiles) {
                for (; i < cacheFiles.length; i++) {
                    if (bitmapName.equals(cacheFiles[i].getName())) {
                        break;
                    }
                }

                if (i < cacheFiles.length) {
                    imageView.setImageBitmap(BitmapFactory.decodeFile("/mnt/sdcard/test/" + bitmapName));
                    return;
                }
            }

            //如果文件中也没有，则先设为默认图片即可
            imageView.setImageResource(R.mipmap.ic_launcher);
        } else {
            imageView.setImageBitmap(bitmap);
        }
    }

    /**
     * 加载从start到end的图片，加载显示图片的操作放在了这里
     *
     * @param start
     * @param end
     */
    public void loadImage(int start, int end) {

        for (int i = start; i < end; i++) {
            //图片的url标志
            String url = LessonListAdapter.URLS[i];
            //从缓存中取名为url的图片
            Bitmap bitmap = getBitmapFromCache(url);
            //如果缓存中存在图片
            if (bitmap == null) {
                //如果缓存中没有，则启动一个LessonsAsyncTask从网络中下载
                LessonsAsyncTask task = new LessonsAsyncTask(url);
                //执行task
                task.execute(url);
                //将task存放入mTask
                mTask.add(task);
            }
            //如果缓存中存在图片，则通过tag取得imageView
            else {
                //通过tag取得imageView
                ImageView imageView = (ImageView) mListView.findViewWithTag(url);
                //设置图片
                imageView.setImageBitmap(bitmap);
            }
        }
    }

    //取消所有任务
    public void CancelAllTask() {
        //当mTask不为空，即已经初始化
        if (mTask != null) {
            for (LessonsAsyncTask task : mTask) {
                task.cancel(false);
            }
        }

    }

    /**
     * 自定义的AsyncTask，传进的类型、返回过程、返回的类型
     */

    private class LessonsAsyncTask extends AsyncTask<String, Void, Bitmap> {
        //图片url
        private String mUrl;

        //      private ImageView mImageView ;
        //构造方法
        private LessonsAsyncTask(String url) {
            mUrl = url;
            //            mImageView = imageView ;因为imageView可用mListView获得，故可注释掉
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //图片url
            String url = params[0];
            //从网络中获取图片
            Bitmap bitmap = getBitmapFromURL(url);
            //如果从网络中获得的图片不为空
            if (bitmap != null) {
                //将图片以url为标志写入缓存
                addBitmapToCache(url, bitmap);

                /**
                 * 将图片写入文件
                 */
                //创建根目录文件对象
                File dir = new File("/mnt/sdcard/test/");
                //如果根目录文件对象不存在，？？？
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                //创建图片文件对象
                File bitmapFile = new File("/mnt/sdcard/test/" +
                        url.substring(url.lastIndexOf("/") + 1));
                //如果图片文件对象，则创建新的文件
                if (!bitmapFile.exists()) {
                    try {
                        bitmapFile.createNewFile();
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                //得到文件输出流
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream(bitmapFile);
                    //将图片文件写进文件中
                    bitmap.compress(Bitmap.CompressFormat.PNG,
                            100, fos);
                    fos.close();
                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }


            }
            return bitmap;
        }

        //上面doInBackground返回的bitmap在这里处理
        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            //通过tag获得显示图片的imageView
            ImageView imageView = (ImageView) mListView.findViewWithTag(mUrl);

            //如果该imageView不为空且bitmap不为空
            if (imageView != null && bitmap != null) {
                imageView.setImageBitmap(bitmap);
            }

            //把该任务去掉
            mTask.remove(this);

//            //优化
//            if(mImageView.getTag().equals(mUrl)){
//                mImageView.setImageBitmap(bitmap);
//            }

        }
    }


}
