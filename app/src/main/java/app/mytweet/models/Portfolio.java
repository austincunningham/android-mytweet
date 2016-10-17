package app.mytweet.models;

import android.util.Log;

import java.util.ArrayList;
import static app.mytweet.android.helpers.LogHelpers.info;

/**
 * Created by ictskills on 10/10/16.
 */
public class Portfolio {
    public ArrayList<Tweet> tweets;
    private PortfolioSerializer serializer;

    public Portfolio(PortfolioSerializer serializer)
    {
        this.serializer = serializer;
        try
        {
            tweets = serializer.loadTweets();
        }
        catch (Exception e)
        {
            info(this, "Error loading residences: " + e.getMessage());
            tweets = new ArrayList<Tweet>();
        }
    }

    public boolean saveTweets()
    {
        try
        {
            serializer.saveTweets(tweets);
            info(this, "Tweets saved to file");
            return true;
        }
        catch (Exception e)
        {
            info(this, "Error saving tweet: " + e.getMessage());
            return false;
        }
    }

    public void addTweet(Tweet tweet){
        tweets.add(tweet);
    }

    public Tweet getTweet(Long id)  {
        Log.i(this.getClass().getSimpleName(),"Long parameter id" +id);

        for (Tweet twe : tweets){
            if (id.equals(twe.id)) {
                return twe;
            }
        }
        info(this, "Failed to find tweet. returning to first element in array to avoid crash");
        return null;
    }

    public void deleteTweet(Tweet tweet)
    {
        tweets.remove(tweet);
        saveTweets();
    }
}
