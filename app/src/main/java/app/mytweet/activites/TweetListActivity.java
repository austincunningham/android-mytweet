package app.mytweet.activites;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.Portfolio;
import app.mytweet.models.Tweet;

/**
 * Created by ictskills on 10/10/16.
 */
public class TweetListActivity extends Activity {
    private ListView listView;
    private Portfolio portfolio;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_tweetlist);
        MyTweetApp app = (MyTweetApp)getApplication();
        portfolio = app.portfolio;
    }
}

class TweetAdapter extends ArrayAdapter<Tweet>{
    private Context context;

    public TweetAdapter (Context context, ArrayList<Tweet>tweets)
}