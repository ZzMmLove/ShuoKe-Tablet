package cn.gdgst.shuoke.tablet.api;

import cn.gdgst.shuoke.tablet.bean.HttpResult;
import cn.gdgst.shuoke.tablet.bean.User;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 接口服务类
 * Created by Administrator on 2/7 0007.
 */

public interface ApiService {

    /**
     * 账号密码登录
     * @param token
     * @param mob
     * @param password
     * @return
     */
    //http://shuoke360.cn/api/login?token=gstshuoke360&mob=modi@163.com&password=123
    @GET("login")
    Observable<HttpResult<User>> getUserData(@Query("token") String token,
                                             @Query("mob") String mob,
                                             @Query("password") String password);

}
