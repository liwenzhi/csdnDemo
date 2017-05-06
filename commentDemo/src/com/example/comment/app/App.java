package com.example.comment.app;

import android.app.Application;

/**
 * Created with IntelliJ IDEA.
 * User: wanpu_oyf
 * Date: 17-4-17
 * Time: 下午8:30
 * To change this template use File | Settings | File Templates.
 */
public class App extends Application {
   static App app = new App();

    public static App getInstance() {
        return app;
    }
}
