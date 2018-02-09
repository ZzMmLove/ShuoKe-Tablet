package cn.gdgst.shuoke.tablet.base.baseui;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.gdgst.shuoke.tablet.R;
import cn.gdgst.shuoke.tablet.app.AppMananger;
import cn.gdgst.shuoke.tablet.base.baserx.RxManager;
import cn.gdgst.shuoke.tablet.utils.StatusBarCompat;
import cn.gdgst.shuoke.tablet.utils.TUtil;
import cn.gdgst.shuoke.tablet.utils.ToastUitl;
import cn.gdgst.shuoke.tablet.widget.LoadingDialog;

/**
 *
 * Created by Administrator on 2/6 0006.
 */

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {

    public P mPresenter;
    public M mModel;
    public Context mContext;
    public RxManager mRxManager;
    private Unbinder unbinder;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setViewModel();
        mRxManager = new RxManager();
        setContentView(getLayoutId());
        unbinder = ButterKnife.bind(this);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        if (mPresenter != null){
            mPresenter.mContext = this;
        }
        this.initPresenter();
        this.initView();
    }

    protected abstract void initView();

    protected abstract void initPresenter();

    private void setViewModel() {
        AppMananger.getAppManager().addActivity(this);
        // 无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 设置横屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    }

    public abstract int getLayoutId();

    /**
     * 沉浸状态栏（4.4以上系统有效）
     */
    protected void SetTranslanteBar(){
        StatusBarCompat.translucentStatusBar(this);
    }

    /**
     * 开启浮动加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(this);
    }

    /**
     * 开启浮动加载进度条
     *
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(this, msg, true);
    }

    /**
     * 停止浮动加载进度条
     */
    public void stopProgressDialog() {
        LoadingDialog.cancelDialogForLoading();
    }

    /**
     * 短暂显示Toast提示(来自String)
     **/
    public void showShortToast(String text) {
        ToastUitl.showShort(text);
    }

    /**
     * 短暂显示Toast提示(id)
     **/
    public void showShortToast(int resId) {
        ToastUitl.showShort(resId);
    }

    /**
     * 长时间显示Toast提示(来自res)
     **/
    public void showLongToast(int resId) {
        ToastUitl.showLong(resId);
    }

    /**
     * 长时间显示Toast提示(来自String)
     **/
    public void showLongToast(String text) {
        ToastUitl.showLong(text);
    }

    /**
     * 带图片的toast
     * @param text
     * @param res
     */
    public void showToastWithImg(String text,int res) {
        ToastUitl.showToastWithImg(text,res);
    }
    /**
     * 网络访问错误提醒
     */
    public void showNetErrorTip() {
        ToastUitl.showToastWithImg(getText(R.string.net_error).toString(),R.drawable.ic_wifi_off);
    }

    public void showNetErrorTip(String error) {
        ToastUitl.showToastWithImg(error,R.drawable.ic_wifi_off);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
        unbinder.unbind();
        AppMananger.getAppManager().finishActivity(this);
    }
}
