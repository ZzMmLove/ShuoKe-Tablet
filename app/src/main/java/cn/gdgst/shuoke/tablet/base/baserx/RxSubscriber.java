package cn.gdgst.shuoke.tablet.base.baserx;

import android.app.Activity;
import android.content.Context;

import cn.gdgst.shuoke.tablet.R;
import cn.gdgst.shuoke.tablet.app.AppApplication;
import cn.gdgst.shuoke.tablet.utils.NetWorkUtils;
import cn.gdgst.shuoke.tablet.widget.LoadingDialog;
import rx.Subscriber;
import rx.exceptions.Exceptions;

/**
 * 订阅的封装
 * Created by Administrator on 2/6 0006.
 */

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;

    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog= true;
    }
    public void hideDialog() {
        this.showDialog= true;
    }

    public RxSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog=showDialog;
    }

    public RxSubscriber(Context context) {
        this(context, AppApplication.getAppContext().getString(R.string.loading),true);
    }

    public RxSubscriber(Context context,boolean showDialog) {
        this(context, AppApplication.getAppContext().getString(R.string.loading),showDialog);
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog){
            LoadingDialog.showDialogForLoading((Activity) mContext, msg, true);
        }
    }

    @Override
    public void onCompleted() {
        if (showDialog){
            LoadingDialog.cancelDialogForLoading();
        }
    }

    @Override
    public void onError(Throwable e) {
        if (showDialog){
            LoadingDialog.cancelDialogForLoading();
        }
        if (!NetWorkUtils.isNetConnected(AppApplication.getAppContext())){
            _onError(AppApplication.getAppResources().getString(R.string.no_net));
        }else if (e instanceof Exception){
            _onError(e.getMessage());
        }else {
            _onError(AppApplication.getAppResources().getString(R.string.net_error));
        }
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    protected abstract void _onNext(T t);

    protected abstract void _onError(String message);

}
