package app.mytweet.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import app.mytweet.activities.TweetListFragment;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.Tweet;

import java.io.IOException;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class RefreshService extends IntentService implements Callback<List<Tweet>>
{
    private String tag = "MyTweet";
    public Intent localIntent;
    MyTweetApp app;
    public RefreshService()
    {
        super("RefreshService");
        app = MyTweetApp.getApp();
    }

    @Override
    protected void onHandleIntent(Intent intent)
    {
        localIntent = new Intent(TweetListFragment.BROADCAST_ACTION);
        Call<List<Tweet>> call = app.myTweetService.getAllTweets();
        call.enqueue(this);
        /*Call<List<Tweet>> call = (Call<List<Tweet>>) app.myTweetService.getAllTweets();
        try
        {
            Response<List<Tweet>> response = call.execute();
            app.portfolio.refreshTweet(response.body());
            LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
        }
        catch (IOException e)
        {

        }*/
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        Log.i(tag, "RefreshService instance destroyed");
    }

    @Override
    public void onResponse(Response<List<Tweet>> response, Retrofit retrofit) {
        app.portfolio.refreshTweet(response.body());
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }

    @Override
    public void onFailure(Throwable t) {

    }
}