package dk.au.au339038.bachelorprojekt.endelighjemmeapp.Other;

import android.app.Application;
import android.content.Context;

public class FHApplication extends Application {

    private static FHApplication instance; //the one application object for the application's life time

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;    //Android specifies that this is the first code to run in app, so should no be null from here on
    }

    //NB: Get Application context - ONLY for use outside UI (e.g. for repository)
    public static Context getAppContext(){
        return instance.getApplicationContext();
    }
}
