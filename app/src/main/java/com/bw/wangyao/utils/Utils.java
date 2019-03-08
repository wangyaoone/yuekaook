package com.bw.wangyao.utils;

import android.util.Log;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @Auther: 12547
 * @Date: 2019/3/8 14:11:55
 * @Description:
 */
public class Utils {

         //只声明不创建
         private  static Utils utils=null;
         //构造函数
    public Utils() {
    }
      public  static   Utils utils(){
           if (utils==null){
                  //同步锁
                  synchronized (Utils.class){
                         if (utils==null){
                             Utils utils = new Utils();
                         }
                  }
           }
              return utils;
      }

      private static OkHttpClient okHttpClient=null;
      public synchronized  static OkHttpClient getokhttpclient(){
              if (okHttpClient==null){
     HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
                      @Override
                      public void log(String message) {
                        /*  Log.i("拦截器",message);*/
                      }
                  });
                   //设置模式
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                  okHttpClient=new OkHttpClient.Builder()
                          .addInterceptor(httpLoggingInterceptor)
                                     //创建运用拦截器
                          .addInterceptor(new Interceptor() {
                              @Override
                     public Response intercept(Chain chain) throws IOException {
                                  Request build = chain.request().newBuilder()
                                  //添加公共参数
                                  .addHeader("source","android")
                                  .build();
                                  return chain.proceed(build);
                              }
                          })
                          .build();
              }
              return okHttpClient;
      }

        //封装网络get请求
    public static  void doget(String url, Callback callback){
        OkHttpClient getokhttpclient = getokhttpclient();
        Request build = new Request.Builder().url(url).build();
        Call call = getokhttpclient.newCall(build);
           call.enqueue(callback);
    }

      //封装网络postq请求
    public  static  void dopost(String url, Map<String,String>map,Callback callback)  {
        OkHttpClient getokhttpclient = getokhttpclient();
        FormBody.Builder builder = new FormBody.Builder();
             //遍历map
            for (String key:map.keySet()){
                builder.add(key,map.get(key));
            }
        FormBody build = builder.build();
        Request build1 = new Request.Builder().url(url).post(build).build();
        Call call = getokhttpclient.newCall(build1);
            call.enqueue(callback);
    }

}
