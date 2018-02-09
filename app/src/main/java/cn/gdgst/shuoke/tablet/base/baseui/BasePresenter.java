package cn.gdgst.shuoke.tablet.base.baseui;

import android.content.Context;

import cn.gdgst.shuoke.tablet.base.baserx.RxManager;

/**
 * Created by Administrator on 2/7 0007.
 */

public abstract class BasePresenter<V, M> {

    public Context mContext;
    public V mView;
    public M mModel;
    public RxManager mRxManager = new RxManager();

    /**
     *关联View层和Model层
     * @param v
     * @param m
     */
    public void setVM(V v, M m){
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }

    private void onStart() {

    }

    public void onDestroy(){
        mRxManager.clear();
    }

}
