/**
 * Created by austin on 21/10/2016.
 */
package app.mytweet.activities;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

import app.mytweet.models.Portfolio;
import app.mytweet.models.Tweet;
import app.mytweet.R;
import app.mytweet.app.MyTweetApp;

public class MyTweetPagerActivity extends AppCompatActivity
{
    private ViewPager viewPager;
    private ArrayList<Tweet>tweets;
    private Portfolio portfolio;
    private PagerAdapter pagerAdapter;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        viewPager = new ViewPager(this);
        viewPager.setId(R.id.viewPager);
        setContentView(viewPager);
        setTweetList();
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), tweets);
        viewPager.setAdapter(pagerAdapter);
        setCurrentItem();
    }

    private void setTweetList()
    {
        MyTweetApp app = (MyTweetApp) getApplication();
        portfolio = app.portfolio;
        tweets = portfolio.tweets;
    }

    /*
 * Ensure selected tweet is shown in details view
 */
    private void setCurrentItem()
    {
        Long tweId = (Long) getIntent().getSerializableExtra(MyTweetFragment.EXTRA_TWEET_ID);
        for (int i = 0; i < tweets.size(); i++) {
            if (tweets.get(i).id.equals(tweId)) {
                viewPager.setCurrentItem(i);
                break;
            }
        }
    }

    /**
     * Created by austin on 22/10/2016.
     */

    /* */
    class PagerAdapter extends FragmentStatePagerAdapter
    {
        private ArrayList<Tweet> tweets;

        public PagerAdapter(FragmentManager fm, ArrayList<Tweet> tweets)
        {
            super(fm);
            this.tweets = tweets;
        }

        @Override
        public int getCount()
        {
            return tweets.size();
        }

        @Override
        public Fragment getItem(int pos)
        {
            Tweet tweet = tweets.get(pos);
            Bundle args = new Bundle();
            args.putSerializable(MyTweetFragment.EXTRA_TWEET_ID, tweet.id);
            MyTweetFragment fragment = new MyTweetFragment();
            fragment.setArguments(args);
            return fragment;
        }
    }
}

