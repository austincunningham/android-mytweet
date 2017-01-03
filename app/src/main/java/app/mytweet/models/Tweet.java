package app.mytweet.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by austin on 30/09/2016.
 */
public class Tweet {

    public int count;
    public long date;
    public Long id;
    public String _id;
    public String message;
    public String name;
    public String tweeter;
    public String photo;


    private String tweet;
    private static final String JSON_ID             = "id"            ;
    private static final String JSON_TWEETCONENT    = "message"   ;
    private static final String JSON_DATE           = "date"          ;


    public Tweet(){
        message = "";
        tweeter = "";
        name = "";
        //id = new Random().nextLong();
        id = unsignedLong();
        date = new Date().getTime();
        photo = "photo";
    }

    public Long unsignedLong(){
        long rndVal = 0;
        do{
            rndVal = new Random().nextLong();
        } while (rndVal <= 0);
        return rndVal;
    }

    public Tweet(JSONObject json) throws JSONException
    {
        id = json.getLong(JSON_ID);
        message = json.getString(JSON_TWEETCONENT);
        date = json.getLong(JSON_DATE);
    }

    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, Long.toString(id));
        json.put(JSON_TWEETCONENT, message);
        json.put(JSON_DATE, date);
        return json;
    }

    public void setTweet(String tweetContent){
        this.message = tweetContent;
    }

    public String getTweet() { return message;}

    public String getDateString() {
        return "Tweet Date:" + dateString();
    }

    private String dateString() {
        String dateFormat = "EEE d MMM yyyy H:mm";
        return android.text.format.DateFormat.format(dateFormat, date).toString();
    }

}
