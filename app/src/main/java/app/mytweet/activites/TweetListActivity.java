package app.mytweet.activites;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

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
    private TweetAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTitle(R.string.app_name);
        setContentView(R.layout.activity_tweetlist);
        MyTweetApp app = (MyTweetApp)getApplication();
        portfolio = app.portfolio;

        adapter = new TweetAdapter(this, portfolio.tweets);
        listView.setAdapter(adapter);
    }
}

class TweetAdapter extends ArrayAdapter<Tweet>{
    private Context context;

    public TweetAdapter (Context context, ArrayList<Tweet>tweets){
        super(context,0,tweets);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if(convertView == null ){
            convertView = inflater.inflate(R.layout.list_item_tweet, null);
        }
        Tweet twe = getItem(position);

        TextView tweetContent =(TextView)convertView.findViewById(R.id.tweet_list_item_tweetContent);
        tweetContent.setText(twe.tweetContent);

        /*TextView dateTextView = (TextView) convertView.findViewById(R.id.tweet_list_item_dateTextView);
        dateTextView.setText(twe.getDateString());

        CheckBox rentedCheckBox = (CheckBox) convertView.findViewById(R.id.tweet_list_item_istweeted);
        rentedCheckBox.setChecked(twe.tweet);*/

        return convertView;
    }
}