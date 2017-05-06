package com.example.rxjava;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;

public class MyActivity extends Activity {

    /**
     * RXJava示例
     */
    String tag = "TAG";
    CompositeDisposable mDisposable;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //OnSubscribe
        Observable.just("Howdy!");
        observable.subscribe(observer); //建立订阅关系

        // mDisposable.add(disposable) ;
        Observable observable = Observable.just("Hello", "Hi", "Aloha");
        Disposable disposable = observable.subscribe(
                new Consumer<String>() {
                    @Override
                    public void accept(String integer) throws Exception {
                        //这里接收数据项
                        Log.e(tag, "disposable" + integer);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        //这里接收onError
                        Log.e(tag, "disposable" + "这里接收onError");
                    }
                }, new Action() {
                    @Override
                    public void run() throws Exception {
                        //这里接收onComplete。
                        Log.e(tag, "disposable" + "Action");
                    }
                }
        );

        //直接链式显示 ，发送数据，并接手处理
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onNext(3);
                emitter.onComplete();
            }
        }).subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(tag, "subscribe");
            }

            @Override
            public void onNext(Integer value) {
                Log.d(tag, "" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(tag, "error");
            }

            @Override
            public void onComplete() {
                Log.d(tag, "complete");
            }
        });


    }


    //被观察者 ,observable表示可观测的
    Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {

        @Override
        public void subscribe(ObservableEmitter<String> e) throws Exception {
            e.onNext("Hello World");
            e.onNext("Hello World22");
            e.onNext("Hello World23");
            e.onNext("Hello World24");
            e.onComplete();
        }
    });


    //观察者 observer
    Observer<String> observer = new Observer<String>() {
        //Disposable表示一次性的，onSubscribe方法是Rx2.0才有的，
        // 拿到 disposable的之后 ，可以对情况进判断后选中解除绑定
        private Disposable disposable;

        @Override
        public void onSubscribe(Disposable d) {
            disposable = d;
        }

        @Override
        public void onNext(String value) {

            if (value.equals("Hello World22")) {   // 解除订阅 ,解除绑定后面的数据就不会接收，并且onComplete就不会得到回调
                disposable.dispose();
            }
            Log.e(tag, value.toString());
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
            Log.e(tag, "onComplete");

        }
    };

}
