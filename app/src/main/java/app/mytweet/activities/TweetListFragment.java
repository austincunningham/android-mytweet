package app.mytweet.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.Portfolio;
import app.mytweet.models.Tweet;
import android.support.v4.app.ListFragment;

import static  app.mytweet.android.helpers.IntentHelper.startActivityWithData;
import static  app.mytweet.android.helpers.IntentHelper.startActivityWithDataForResult;

/**
 * Created by ictskills on 10/10/16.
 */
public class TweetListFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private ArrayList<Tweet> tweets;
    private ListView listView;
    private Portfolio portfolio;
    private TweetAdapter adapter;
    MyTweetApp app;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        getActivity().setTitle(R.string.app_name);

        app = MyTweetApp.getApp();
        portfolio = app.portfolio;
        adapter = new TweetAdapter(getActivity(), portfolio.tweets);
        setListAdapter(adapter);

        /*setTitle(R.string.app_name);
        setContentView(R.layout.activity_tweetlist);

        listView = (ListView)findViewById(R.id.tweetList);

        MyTweetApp app = (MyTweetApp)getApplication();

        adapter = new TweetAdapter(this, portfolio.tweets);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);*/
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tweet tweet = adapter.getItem(position);
        startActivityWithData(getActivity(), MyTweetFragment.class, "TWEET_ID", tweet.id);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Tweet twe = ((TweetAdapter) getListAdapter()).getItem(position);
        Intent i = new Intent(getActivity(), MyTweetActivity.class);
        i.putExtra(MyTweetFragment.EXTRA_TWEET_ID, twe.id);
        startActivityForResult(i, 0);
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //adapter.notifyDataSetChanged();
        ((TweetAdapter)getListAdapter()).notifyDataSetChanged();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
       // MenuInflater menuInflater = getMenuInflater();
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.tweetlist, menu);
        //return true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = super.onCreateView(inflater, parent, savedInstanceState);
        return v;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_item_new_tweet: Tweet tweet = new Tweet();
                    portfolio.addTweet(tweet);
                    Intent i = new Intent(getActivity(), MyTweetActivity.class);
                    i.putExtra(MyTweetFragment.EXTRA_TWEET_ID, tweet.id);
                    startActivityForResult(i, 0);
                    return true;
            default: return super.onOptionsItemSelected(item);
        }
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