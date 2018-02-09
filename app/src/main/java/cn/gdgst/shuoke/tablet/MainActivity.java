package cn.gdgst.shuoke.tablet;

import android.os.Bundle;
import android.util.Log;

import cn.gdgst.shuoke.tablet.base.baseui.BaseActivity;
import cn.gdgst.shuoke.tablet.bean.HttpResult;
import cn.gdgst.shuoke.tablet.bean.User;
import cn.gdgst.shuoke.tablet.ui.test.contract.TestContract;
import cn.gdgst.shuoke.tablet.ui.test.model.TestModel;
import cn.gdgst.shuoke.tablet.ui.test.presenter.TestPresenter;
import cn.gdgst.shuoke.tablet.utils.StatusBarCompat;

public class MainActivity extends BaseActivity<TestPresenter, TestModel> implements TestContract.View{
    private static final String TAG = "MainActivity";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarCompat.fullScreen(this);
    }

    @Override
    protected void initView() {
        mPresenter.getUserData("gstshuoke360", "modi@163.com", "1");
    }

    @Override
    protected void initPresenter() {
        mPresenter.setVM(this, mModel);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void returnUserData(HttpResult<User> userHttpResult) {
        Log.e(TAG, userHttpResult.getData().toString());
    }

    @Override
    public void showLoading(String title) {

    }

    @Override
    public void stopLoading() {

    }

    @Override
    public void showErrorTip(String msg) {

    }
}
