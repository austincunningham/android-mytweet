package app.mytweet.app;

import android.app.Application;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import app.mytweet.activities.TweetListActivity;
import app.mytweet.models.Portfolio;
import app.mytweet.models.User;
import app.mytweet.retrofit.MyTweetServiceProxy;
import app.mytweet.retrofit.MyTweetServiceProxyOpen;
import app.mytweet.retrofit.RetrofitServiceFactory;
import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

import static app.mytweet.android.helpers.LogHelpers.info;

/**
 * Created by austin on 28/09/2016.
 */
public class MyTweetApp extends Application {

    //public String service_url = "http://35.160.157.123:4000";
    public String service_url = "http://10.0.2.2:4000";   // Standard Emulator IP Address
    public static MyTweetServiceProxy myTweetService;
    public MyTweetServiceProxyOpen myTweetServiceOpen;
    public static boolean valid = false ;

    public List<User> users = new ArrayList<User>();
    public Portfolio portfolio;
    //private static final String FILENAME = "portfolio.json";
    protected static MyTweetApp app;
    public static User currentUser;


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

        Gson gson = new GsonBuilder().create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(service_url)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //myTweetService = retrofit.create(MyTweetServiceProxy.class);
        myTweetServiceOpen = RetrofitServiceFactory.createService(MyTweetServiceProxyOpen.class);
        sendBroadcast(new Intent("app.mytweet.receivers.SEND_BROADCAST"));

    }

    public static MyTweetApp getApp() {
        return app;
    }
}
