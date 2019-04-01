package com.example.androidproject;

import android.app.Application;

public class MonAppli extends Application {

        public static String difficulte;

        @Override
        public void onCreate(){
            super.onCreate();
            difficulte = getString(R.string.radio_easy);
        }
}


