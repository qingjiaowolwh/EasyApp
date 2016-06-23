package com.edu.zum.easyapp.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by asus on 2016/3/20.
 */
public class RxBus {

    private static RxBus instance = null;
    private final Subject<Object, Object> rxBus = new SerializedSubject<>(PublishSubject.create());

    public RxBus() {

    }

    public static RxBus getInstance() {
        if (null == instance) {
            instance = new RxBus();
        }
        return instance;
    }

    public void send(Object o) {
        rxBus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return rxBus;
    }
}