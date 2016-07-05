package com.edu.zum.easyapp.api;

import com.edu.zum.easyapp.model.ResultModel;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by lwh on 2016/6/27.
 */
public class ApiManager {
    public interface Callback<T> {
        void onError(Throwable e);

        void onSuccess(T result);
    }

    public static <T> void execute(Observable<T> observable, final Callback<T> callback) {
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<T>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
                callback.onError(e);
            }

            @Override
            public void onNext(T result) {
                callback.onSuccess(result);
            }
        });
    }

    public static void createUser(String type,int page,Callback<ResultModel> callback) {
        execute(RetrofitService.getInstance().getGoods(type,page), callback);
    }
}
