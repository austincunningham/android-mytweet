package app.mytweet.app;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.mytweet.models.User;

/**
 * Created by austin on 28/09/2016.
 */
public class MyTweetApp extends Application {

    public List<User> users = new ArrayList<User>();

    public void newUser(User user){
        users.add(user);
        Log.v("MyTweetApp", "new user added");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        Log.v("MyTweetApp", "MyTweet App Started");
    }

    public boolean validUser (String email, String password){
        for(User user : users){
            if (user.email.equals(email) && user.password.equals(password)){
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
