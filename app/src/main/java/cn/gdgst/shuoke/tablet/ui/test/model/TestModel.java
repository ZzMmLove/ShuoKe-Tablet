package cn.gdgst.shuoke.tablet.ui.test.model;

import cn.gdgst.shuoke.tablet.api.Api;
import cn.gdgst.shuoke.tablet.api.ApiConstants;
import cn.gdgst.shuoke.tablet.api.HostType;
import cn.gdgst.shuoke.tablet.base.baserx.RxSchedulers;
import cn.gdgst.shuoke.tablet.bean.HttpResult;
import cn.gdgst.shuoke.tablet.bean.User;
import cn.gdgst.shuoke.tablet.ui.test.contract.TestContract;
import rx.Observable;

/**
 * Created by Administrator on 2/7 0007.
 */

public class TestModel implements TestContract.Model {
    @Override
    public Observable<HttpResult<User>> getUserData(String tokon, String mob, String password) {
        return Api.getApiService(HostType.SHUO_KE)
                .getUserData(tokon, mob, password)
                .compose(RxSchedulers.<HttpResult<User>>io_main());
    }
}
