package me.jokey.mvp.application;

import android.app.Application;
import android.content.Context;

/**
 * Created by wz on 2017/6/20 15:27.
 * BaseApplication
 */


public class BaseApplication extends Application{

    public static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }

    public static Context getContext(){
        return mContext;
    }
}
