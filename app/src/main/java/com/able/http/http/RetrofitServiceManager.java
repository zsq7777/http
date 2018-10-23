package com.able.http.http;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitServiceManager {
    private static final int DEFAULT_TIME_OUT = 10;
    private static final int DEFAULT_READ_TIME_OUT = 20;
    private Retrofit retrofit;

    private RetrofitServiceManager() {
        //获取日志工具对象
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        //设置日志Level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        //httpClient对象
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        //连接超时时间
        httpClient.connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS);
        //读取超时时间
        httpClient.connectTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS);
        //添加日志拦截器
        httpClient.addInterceptor(logging);

        retrofit = new Retrofit.Builder()
                .baseUrl(ApiConfig.ROOT_URL_DIANXIN) //设置网络请求的Url地址
                .addConverterFactory(SimpleXmlConverterFactory.create()) //设置数据解析器
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                //添加拦截
                .client(httpClient.build())
                .build();
    }
    private static class SingletonHolder{
        private static final RetrofitServiceManager INSTANCE = new RetrofitServiceManager();
    }
    public static RetrofitServiceManager getInstance(){
        return SingletonHolder.INSTANCE;
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     * @return
     */
//    public <T> T create(Class<T> service){
//        return mRetrofit.create(service);
//    }
//    https://www.jianshu.com/p/811ba49d0748
//    https://blog.csdn.net/qq_20521573/article/details/70991850
}
