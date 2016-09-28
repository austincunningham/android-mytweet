package app.mytweet.app;

import android.app.Application;
import android.util.Log;

/**
 * Created by austin on 28/09/2016.
 */
public class MyTweetApp extends Application {
    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("MyTweetApp", "MyTweet App Started");
    }
}
