package com.rxJava;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class RxBus {
    private final Subject<Object> bus;
    private static final RxBus ourInstance = new RxBus();

    public static RxBus getInstance() {
        return ourInstance;
    }

    private RxBus() {
        bus = PublishSubject.create().toSerialized();
    }


    public void accept(Object object)
    {
        bus.onNext(object);
    }

    public void post(Object obj) {
        bus.onNext(obj);
    }

    public <T> Observable<T> toObservable(Class<T> tClass) {
        return bus.ofType(tClass);
    }

    public Observable<Object> toObservable() {
        return bus;
    }

    public boolean hasObservers() {
        return bus.hasObservers();
    }

}
