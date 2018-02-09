package cn.gdgst.shuoke.tablet.base.baseui;

/**
 * MVP模式的基类
 * Created by Administrator on 2/7 0007.
 */

public interface BaseView {

    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);

}
