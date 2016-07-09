package com.xm.scoopsdemo;

import android.app.Application;
import android.preference.PreferenceManager;

import com.ftinc.scoop.Scoop;

/**
 * Created by Administrator on 2016/7/8.
 */
public class MainApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        // Initialize Scoop
        Scoop.waffleCone()
                .addFlavor("Default", R.style.Theme_Scoop, true)
                .addFlavor("Light", R.style.Theme_Scoop_Light)
                .addDayNightFlavor("DayNight", R.style.Theme_Scoop_DayNight)
                .addFlavor("Alternate 1", R.style.Theme_Scoop_Alt1)
                .addFlavor("Alternate 2", R.style.Theme_Scoop_Alt2)
                .addToppings(Toppings.getToppings())
                .setSharedPreferences(PreferenceManager.getDefaultSharedPreferences(this))
                .initialize();
    }
}
