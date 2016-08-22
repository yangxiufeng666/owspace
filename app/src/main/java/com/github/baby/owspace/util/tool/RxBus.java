package com.github.baby.owspace.util.tool;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by Mr.Yangxiufeng
 * DATE 2016/8/22
 * owspace
 */
public class RxBus {
    private static volatile RxBus instance;

    private final Subject<Object,Object> bus;

    private RxBus(){
        bus = new SerializedSubject<>(PublishSubject.create());
    }

    public static RxBus getInstance() {
        if (instance == null){
            synchronized (RxBus.class){
                if (instance == null){
                    instance = new RxBus();
                }
            }
        }
        return instance;
    }
    public void postEvent(Object event){
        bus.onNext(event);
    }
    public <T>Observable<T> toObservable(Class<T> eventype){
        return bus.ofType(eventype);
    }
}
