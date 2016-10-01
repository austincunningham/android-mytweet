package app.mytweet.models;

import java.util.Date;
import java.util.Random;

/**
 * Created by austin on 30/09/2016.
 */
public class Tweet {

    public int count;
    public long date;
    public Long id;

    private String tweet;

    public void Tweet(){
        id = new Random().nextLong();
        date = new Date().getTime();
    }

    public void setTweet(String tweet){
        this.tweet = tweet;
    }

    public String getTweet() { return tweet;}

    public String getDateString() {
        return "Tweet Date:" + dateString();
    }

    private String dateString() {
        String dateFormat = "EEE d MMM yyyy H:mm";
        return android.text.format.DateFormat.format(dateFormat, date).toString();
    }

}
