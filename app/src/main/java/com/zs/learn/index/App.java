package com.zs.learn.index;

import android.app.Application;

import com.chenenyu.router.Router;

import org.litepal.LitePal;

/**
 * Created by shao on 2017/7/7.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Router.initialize(this);
        LitePal.initialize(this);
    }
}
