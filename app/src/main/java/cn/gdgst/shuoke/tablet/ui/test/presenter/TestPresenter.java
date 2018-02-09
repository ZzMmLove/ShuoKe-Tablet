package cn.gdgst.shuoke.tablet.ui.test.presenter;

import cn.gdgst.shuoke.tablet.base.baserx.RxSubscriber;
import cn.gdgst.shuoke.tablet.bean.HttpResult;
import cn.gdgst.shuoke.tablet.bean.User;
import cn.gdgst.shuoke.tablet.ui.test.contract.TestContract;
import cn.gdgst.shuoke.tablet.utils.ToastUitl;

/**
 *
 * Created by Administrator on 2/7 0007.
 */

public class TestPresenter extends TestContract.Presenter {
    @Override
    public void getUserData(String token, String mob, String password) {
        mRxManager.add(mModel.getUserData(token, mob, password)
        .subscribe(new RxSubscriber<HttpResult<User>>(mContext, true) {

            @Override
            public void onStart() {
                super.onStart();
                mView.showLoading("正在登陆……");
            }

            @Override
            protected void _onNext(HttpResult<User> userHttpResult) {
                if (userHttpResult.getError_code() == 0){
                    if (userHttpResult.getData() != null){
                        mView.returnUserData(userHttpResult);
                        mView.stopLoading();
                    }
                }else {
                    ToastUitl.show(userHttpResult.getMessage(), 0);
                    mView.stopLoading();
                }
            }

            @Override
            protected void _onError(String message) {
                mView.showErrorTip(message);
            }
        }));
    }
}
