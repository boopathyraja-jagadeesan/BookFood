package com.doodleblue.dining.util;

import android.app.Application;
import android.content.Context;




public class AppController extends Application {

    public static final String TAG = AppController.class.getSimpleName();
    private static AppController mInstance;
    private static Context context;



    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public static Context getContext() {
        return AppController.context;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        AppController.context = getApplicationContext();

    }

}
