package am.rate.com.rateamtest.view;

import android.app.Application;

import am.rate.com.rateamtest.di.component.ApplicationComponent;
import am.rate.com.rateamtest.di.component.DaggerApplicationComponent;
import am.rate.com.rateamtest.di.module.ApplicationModule;

public class RateApp extends Application {

    private ApplicationComponent mApplicationComponent;
    private static final String mBaseUrl = "http://rate.am/ws/mobile/v2/";

    @Override
    public void onCreate() {
        super.onCreate();
        initAppComponent();
    }

    private void initAppComponent() {
        this.mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this, mBaseUrl))
                .build();
    }


    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }

}
