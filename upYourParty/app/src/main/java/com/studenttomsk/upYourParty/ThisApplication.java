package com.studenttomsk.upYourParty;

import android.app.Application;
import android.content.Context;

import androidx.multidex.MultiDex;

import com.vk.api.sdk.VK;
import com.vk.api.sdk.auth.VKAccessToken;

public class ThisApplication extends Application {


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        VK.initialize(this);
        MultiDex.install(base);
    }
}
