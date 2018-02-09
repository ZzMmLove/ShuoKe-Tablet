package cn.gdgst.shuoke.tablet.base.baserx;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * RxJava的线程调度管理
 * Created by Administrator on 2/6 0006.
 */

public class RxSchedulers {

    public static <T> Observable.Transformer<T, T> io_main() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable.subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

}
