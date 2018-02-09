package cn.gdgst.shuoke.tablet.base.baseui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cn.gdgst.shuoke.tablet.R;
import cn.gdgst.shuoke.tablet.base.baserx.RxManager;
import cn.gdgst.shuoke.tablet.utils.TUtil;
import cn.gdgst.shuoke.tablet.utils.ToastUitl;
import cn.gdgst.shuoke.tablet.widget.LoadingDialog;

/**
 *
 * Created by Administrator on 2/7 0007.
 */

public abstract class BaseFragment<P extends BasePresenter, M extends BaseModel> extends Fragment {

    protected View rootView;

    public P mPresenter;
    public M mModel;
    public RxManager mRxManager;
    public Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null)
            rootView = inflater.inflate(getLayoutId(), container, false);
        mRxManager = new RxManager();
        unbinder = ButterKnife.bind(this, rootView);
        //通过创建进来的泛型T在TUtil中创建NewsMainModel的对象
        mPresenter = TUtil.getT(this, 0);
        //通过创建进来的泛型T在TUtil中创建NewsMainModel的对象
        mModel= TUtil.getT(this,1);
        if (mPresenter != null){
            mPresenter.mContext = this.getActivity();
        }
        initPresenter();
        initView();
        return rootView;
    }

    protected abstract void initView();

    protected abstract void initPresenter();

    protected abstract int getLayoutId();

    /**
     * 开启加载进度条
     */
    public void startProgressDialog() {
        LoadingDialog.showDialogForLoading(getActivity());
    }

    /**
     * 开启加载进度条
     * @param msg
     */
    public void startProgressDialog(String msg) {
        LoadingDialog.showDialogForLoading(getActivity(), msg, true);
    }

    /**
     * 停止加载进度条
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
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
        if (mPresenter != null)
            mPresenter.onDestroy();
        mRxManager.clear();
    }
}
