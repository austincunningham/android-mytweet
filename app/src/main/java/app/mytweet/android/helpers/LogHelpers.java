package app.mytweet.android.helpers;

/**
 * Created by austin on 17/10/2016.
 */
import android.util.Log;

public class LogHelpers {
    public static void info(Object parent, String message) {
        Log.i(parent.getClass().getSimpleName(), message);
    }
}
