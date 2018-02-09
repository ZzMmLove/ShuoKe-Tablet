package cn.gdgst.shuoke.tablet.ui.test.contract;

import cn.gdgst.shuoke.tablet.base.baseui.BaseModel;
import cn.gdgst.shuoke.tablet.base.baseui.BasePresenter;
import cn.gdgst.shuoke.tablet.base.baseui.BaseView;
import cn.gdgst.shuoke.tablet.bean.HttpResult;
import cn.gdgst.shuoke.tablet.bean.User;
import rx.Observable;

/**
 * Created by Administrator on 2/7 0007.
 */

public interface TestContract {

    interface Model extends BaseModel{
        Observable<HttpResult<User>> getUserData(String tokon, String mob, String password);
    }

    interface View extends BaseView{
        void returnUserData(HttpResult<User> userHttpResult);
    }

    abstract class Presenter extends BasePresenter<View, Model>{
        public abstract void getUserData(String token, String mob, String password);
    }

}
