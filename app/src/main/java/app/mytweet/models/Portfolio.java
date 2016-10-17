package app.mytweet.models;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by ictskills on 10/10/16.
 */
public class Portfolio {
    public ArrayList<Tweet> tweets;

    public Portfolio(){
        tweets = new ArrayList<Tweet>();
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
        return null;
    }
}
