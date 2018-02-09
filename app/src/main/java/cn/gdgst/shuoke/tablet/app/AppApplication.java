package cn.gdgst.shuoke.tablet.app;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

/**
 *
 * Created by Administrator on 2/6 0006.
 */

public class AppApplication extends Application {
    private static AppApplication appApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        appApplication = this;
    }

    public static Context getAppContext(){
        return appApplication;
    }

    public  static Resources getAppResources(){
        return appApplication.getResources();
    }
}
