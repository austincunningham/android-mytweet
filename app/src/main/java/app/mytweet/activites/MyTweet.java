package app.mytweet.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.widget.TextView;

import app.mytweet.R;
import app.mytweet.models.Tweet;

/**
 * Created by austin on 28/09/2016.
 */
public class MyTweet extends AppCompatActivity implements TextWatcher {

    private EditText tweetText;
    private TextView characterCount;
    private Tweet tweet;
    public int count = 140;
    private Button date;
    private Button tweetButton;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytweet);

        tweetButton = (Button)findViewById(R.id.tweetButton);
        date = (Button)findViewById(R.id.date);
        tweetText = (EditText)findViewById(R.id.tweetText);
        characterCount = (TextView)findViewById(R.id.characterCount);

        tweet = new Tweet();

        date.setEnabled(false);

        tweetText.addTextChangedListener(this);

        if (tweet != null){
            updateControls(tweet);
        }
    }

    public void updateControls(Tweet tweet)
    {
        //tweet.setText(tweet.tweet);
//        String value = count + " ";
//        characterCount.setText(value);
        date.setText(tweet.getDateString());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


    }
    @Override
    public void afterTextChanged(Editable s) {

        if (count >= 0){
            tweet.setTweet(s.toString());
            count--;
            Log.v("MyTweetApp", "character count: " + count);
        }
    }
}
