package app.mytweet.models;

import static app.mytweet.android.helpers.LogHelpers.info;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.util.Log;

import app.mytweet.sqlite.DbHelper;

public class Portfolio
{
    public ArrayList<Tweet> tweets;
    public DbHelper dbHelper;

    public Portfolio(Context context) {
        try {
            dbHelper = new DbHelper(context);
            tweets = (ArrayList<Tweet>) dbHelper.selectTweets();
        }
        catch (Exception e) {
            info(this, "Error loading tweets: " + e.getMessage());
            tweets = new ArrayList<Tweet>();
        }
    }

    /**
     * Obtain the entire database of tweets
     *
     * @return All the tweet in the database as an ArrayList
     */
    public ArrayList<Tweet> selectTweets() {
        return (ArrayList<Tweet>) dbHelper.selectTweets();
    }

    /**
     * Add incoming tweet to both local and database storage
     *
     * @param tweet The tweet object to be added to local and database storage.
     */
    public void addTweet(Tweet tweet) {
        tweets.add(tweet);
        dbHelper.addTweet(tweet);
    }

    /**
     * Obtain specified tweet from local list and return.
     *
     * @param id The Long id identifier of the tweet sought.
     * @return The specified tweet if it exists.
     */
    public Tweet getTweet(Long id) {
        Log.i(this.getClass().getSimpleName(), "Long id id: " + id);

        for (Tweet twe : tweets) {
            if (id.equals(twe.id)) {
                return twe;
            }
        }
        info(this, "failed to find tweet. returning first element array to avoid crash");
        return null;
    }

    /**
     * Delete Tweet object from local and remote storage
     *
     * @param tweet Tweet object for deletion.
     */
    public void deleteTweet(Tweet tweet) {
        dbHelper.deleteTweet(tweet);
        tweets.remove(tweet);
    }

    public void deleteAllTweet() {
        dbHelper.deleteAllTweet();
        tweets.clear();
    }

    public void updateTweet(Tweet tweet ) {
        dbHelper.updateTweet(tweet);
        updateLocalTweets(tweet);

    }

    /**
     * Clear local and sqlite tweets and refresh with incoming list.
     * @param tweets List tweet objects
     */
    public void refreshTweet(List<Tweet> tweets)
    {
        dbHelper.deleteAllTweet();
        this.tweets.clear();

        dbHelper.addTweets(tweets);

        for (int i = 0; i < tweets.size(); i += 1) {
            this.tweets.add(tweets.get(i));
        }
    }

    /**
     * Search the list of tweets for argument tweet
     * If found replace it with argument tweet.
     * If not found just add the argument tweet.
     *
     * @param tweet The tweet object with which the list of Tweets to be updated.
     */
    private void updateLocalTweets(Tweet tweet) {
        for (int i = 0; i < tweets.size(); i += 1) {
            Tweet r = tweets.get(i);
            if (r.id.equals(tweet.id)) {
                tweets.remove(i);
                tweets.add(tweet);
                return;
            }
        }
    }
}