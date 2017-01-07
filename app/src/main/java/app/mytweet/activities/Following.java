package app.mytweet.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.Tweet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import static app.mytweet.android.helpers.IntentHelper.navigateUp;
/**
 * Created by austin on 01/01/2017.
 */

public class Following extends AppCompatActivity
{
    ListView listView;
    public static List<String> result = new ArrayList<>();
    public static List<Tweet> tweetList = new ArrayList<>();
    private FollowingAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_following);
        final MyTweetApp app = (MyTweetApp) getApplication();

        result = MyTweetApp.currentUser.following;
        for(int i = 0 ; i< result.size(); i++) {
            Log.v("get_results", ""+ result.get(i));
            Call<List<Tweet>> call1 = (Call<List<Tweet>>)  app.myTweetService.getTweetsByUserId(result.get(i));
            call1.enqueue(new Callback <List<Tweet>>() {
                @Override
                public void onResponse(Call<List<Tweet>> call, Response<List<Tweet>> response) {
                    response.body();
                    tweetList.addAll(response.body());
                    Log.v("Following_tweets", ""+tweetList.size());
                }

                @Override
                public void onFailure(Call<List<Tweet>> call, Throwable t) {

                }

                /*@Override
                public void onResponse(Response<List<Tweet>> response, Retrofit retrofit) {
                    response.body();
                    tweetList.addAll(response.body());
                    Log.v("Following_tweets", ""+tweetList.size());
                }

                @Override
                public void onFailure(Throwable t) {
                    Log.e("Following_tweets", ""+t);
                }*/
            });

        }



        listView = (ListView) findViewById(R.id.followingList);
        adapter = new FollowingAdapter(this, tweetList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:  navigateUp(this);
                tweetList.clear();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}


class FollowingAdapter extends ArrayAdapter<Tweet> {
    private Context context;
    public List<Tweet> tweets;

    public FollowingAdapter(Context context, List<Tweet> tweets) {
        super(context, R.layout.activity_following, tweets);
        this.context = context;
        this.tweets = tweets;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_following, parent, false);
        Tweet tweet = tweets.get(position);
        TextView amountView = (TextView) view.findViewById(R.id.row_message);
        TextView methodView = (TextView) view.findViewById(R.id.row_email);
        TextView dateView = (TextView) view.findViewById(R.id.row_date);

        amountView.setText("" + tweet.message);
        methodView.setText(tweet.name);
        String dateFormat = "EEE d MMM yyyy H:mm";
        String date =android.text.format.DateFormat.format(dateFormat, tweet.date).toString();
        dateView.setText(date);

        return view;
    }

    @Override
    public int getCount() {
        return tweets.size();
    }
}