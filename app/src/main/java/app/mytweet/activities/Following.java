package app.mytweet.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.Tweet;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

import static app.mytweet.R.id.reportList;

/**
 * Created by austin on 01/01/2017.
 */

public class Following extends AppCompatActivity
{
    ListView listView;
    public static List<String> result = new ArrayList<>();
    public static List<Tweet> tweetList = new ArrayList<>();
    static final String[] numbers = new String[] {
            "Amount, Pay method",
            "10,     Direct",
            "100,    PayPal",
            "1000,   Direct",
            "10,     PayPal",
            "5000,   PayPal"};

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_following);
        final MyTweetApp app = (MyTweetApp) getApplication();

        result = MyTweetApp.currentUser.following;
        for(int i = 0 ; i< result.size(); i++) {
            Log.v("get_results", ""+ result.get(i));
            Call<List<Tweet>> call1 = app.myTweetService.getTweetsByUserId(result.get(i));
            call1.enqueue(new Callback<List<Tweet>>() {
                @Override
                public void onResponse(Response<List<Tweet>> response, Retrofit retrofit) {
                    response.body();
                    tweetList.addAll(response.body());
                    Log.v("Following_tweets", ""+tweetList.size());
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("Following_tweets", ""+t);
                }
            });

        }


        listView = (ListView) findViewById(reportList);
        ArrayAdapter<Tweet> adapter = new ArrayAdapter<Tweet>(this,  android.R.layout.simple_list_item_1, tweetList);
        listView.setAdapter(adapter);
    }
}
