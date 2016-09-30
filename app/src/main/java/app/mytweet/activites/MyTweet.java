package app.mytweet.activites;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;

import app.mytweet.R;
import app.mytweet.models.Tweet;

/**
 * Created by austin on 28/09/2016.
 */
public class MyTweet extends AppCompatActivity implements TextWatcher {

    private EditText tweetText;
    private Tweet tweet;
    public int count = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytweet);

        tweetText = (EditText) findViewById(R.id.tweetText);
        tweet = new Tweet();

        tweetText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


    }
    @Override
    public void afterTextChanged(Editable s) {
        tweet.setTweet(s.toString());
        count++;
        Log.v("MyTweetApp", "character count: " + count);
    }
}
