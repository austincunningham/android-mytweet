package app.mytweet.models;

import java.util.Date;
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
    public String tweetContent;

    private String tweet;
    private static final String JSON_ID             = "id"            ;
    private static final String JSON_TWEETCONENT    = "tweetContent"   ;
    private static final String JSON_DATE           = "date"          ;


    public Tweet(){
        tweetContent = "";
        id = new Random().nextLong();
        date = new Date().getTime();
    }

    public Tweet(JSONObject json) throws JSONException
    {
        id = json.getLong(JSON_ID);
        tweetContent = json.getString(JSON_TWEETCONENT);
        date = json.getLong(JSON_DATE);
    }

    public JSONObject toJSON() throws JSONException
    {
        JSONObject json = new JSONObject();
        json.put(JSON_ID, Long.toString(id));
        json.put(JSON_TWEETCONENT, tweetContent);
        json.put(JSON_DATE, date);
        return json;
    }

    public void setTweet(String tweetContent){
        this.tweetContent = tweetContent;
    }

    public String getTweet() { return tweetContent;}

    public String getDateString() {
        return "Tweet Date:" + dateString();
    }

    private String dateString() {
        String dateFormat = "EEE d MMM yyyy H:mm";
        return android.text.format.DateFormat.format(dateFormat, date).toString();
    }

}
