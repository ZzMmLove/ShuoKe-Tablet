package cn.gdgst.shuoke.tablet.api;

import android.util.SparseArray;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import cn.gdgst.shuoke.tablet.app.AppApplication;
import cn.gdgst.shuoke.tablet.utils.NetWorkUtils;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 封装配置Retrofit
 * Created by Administrator on 2/7 0007.
 */

public class Api {
    //读超时长，单位：毫秒
    public static final int READ_TIME_OUT = 7676;
    //连接时长，单位：毫秒
    public static final int CONNECT_TIME_OUT = 7676;
    public Retrofit retrofit;
    public ApiService apiService;

    private static SparseArray<Api> sRetrofitManager = new SparseArray<>(HostType.TYPE_COUNT);

    /*************************缓存设置*********************/
/*
   1. noCache 不使用缓存，全部走网络

    2. noStore 不使用缓存，也不存储缓存

    3. onlyIfCached 只使用缓存

    4. maxAge 设置最大失效时间，失效则不使用 需要服务器配合

    5. maxStale 设置最大失效时间，失效则不使用 需要服务器配合 感觉这两个类似 还没怎么弄清楚，清楚的同学欢迎留言

    6. minFresh 设置有效时间，依旧如上

    7. FORCE_NETWORK 只走网络

    8. FORCE_CACHE 只走缓存*/

    /**
     * 设缓存有效期为两天
     */
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;
    /**
     * 查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
     * max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
     */
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;
    /**
     * 查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
     * (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
     */
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    private Api(int hostType){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        //缓存
        File cacheFile = new File(AppApplication.getAppContext().getCacheDir(), "cache");
        Cache cache = new Cache(cacheFile, 1024 * 1024 * 100);//100MB
        //增加头部信息
        Interceptor headerInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request buider = chain.request().newBuilder()
                        .addHeader("Content-Type", "application/json; charset=UTF-8")
                        .build();
                return chain.proceed(buider);
            }
        };
        //构建OKHttp客户端
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .readTimeout(READ_TIME_OUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIME_OUT, TimeUnit.MILLISECONDS)
                .addInterceptor(headerInterceptor)
                .addInterceptor(httpLoggingInterceptor)
                .cache(cache)
                .addInterceptor(cacheInterceptor)
                .addNetworkInterceptor(cacheInterceptor)
                .build();

        /**Gson解析**/
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(ApiConstants.getHost(hostType))
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);

    }

    public static ApiService getApiService(int hostype){
        Api api = sRetrofitManager.get(hostype);
            if (api == null){
                api = new Api(hostype);
                sRetrofitManager.put(hostype, api);
            }
        return api.apiService;
    }

    /**云端响应头拦截器，用来配置缓存策略**/
    private final Interceptor cacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetWorkUtils.isNetConnected(AppApplication.getAppContext())){
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
            }
            Response originalResponse = chain.proceed(request);
            if (NetWorkUtils.isNetConnected(AppApplication.getAppContext())){
                String cacheString = request.cacheControl().toString();
                return originalResponse.newBuilder()
                        .header("Cache-Control", cacheString)
                        .removeHeader("Pragma")
                        .build();
            }else {
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
}
