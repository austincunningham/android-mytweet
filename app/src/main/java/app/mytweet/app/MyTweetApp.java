package app.mytweet.app;

import android.app.Application;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import app.mytweet.models.Portfolio;
import app.mytweet.models.User;
import static app.mytweet.android.helpers.LogHelpers.info;

/**
 * Created by austin on 28/09/2016.
 */
public class MyTweetApp extends Application {

    public List<User> users = new ArrayList<User>();
    public Portfolio portfolio;
    //private static final String FILENAME = "portfolio.json";
    protected static MyTweetApp app;


    public void newUser(User user){
        users.add(user);
        Log.v("MyTweetApp", "new user added");
    }

    @Override
    public void onCreate(){
        super.onCreate();
        info("MyTweetApp", "MyTweetFragment App Started");
        portfolio = new Portfolio(getApplicationContext());
        //PortfolioSerializer serializer = new PortfolioSerializer(this, FILENAME);
        //portfolio = new Portfolio(serializer);
        info(this, "MyRent app launched");
        app = this;

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

    public static MyTweetApp getApp() {
        return app;
    }
}
