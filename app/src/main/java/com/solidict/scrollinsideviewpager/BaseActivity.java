package com.solidict.scrollinsideviewpager;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by cihankaptan on 05/11/14.
 */
public class BaseActivity extends Activity {
    BaseApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        app = (BaseApplication) getApplication();
    }


    protected void initializeScreenMeasure(DisplayMetrics dm) {
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        app.screenWidth = dm.widthPixels;
        app.screenHeight = dm.heightPixels;
        app.screenDenstiny = dm.densityDpi;
    }
}
