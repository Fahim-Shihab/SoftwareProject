package org.firebaseproject;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by shan on 2/17/18.
 */

public class FirebaseProject extends Application {

    public void onCreate(){
        super.onCreate();

        Firebase.setAndroidContext(this);
    }
}
