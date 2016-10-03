package app.mytweet.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.text.Editable;
import android.widget.TextView;
import android.widget.Toast;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.Tweet;

/**
 * Created by austin on 28/09/2016.
 */
public class MyTweet extends AppCompatActivity implements TextWatcher {

    private EditText tweetText;
    private TextView characterCount;
    private Tweet tweet;
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

    public void tweetPressed (View view)
    {
        MyTweetApp app = (MyTweetApp)getApplication();

        tweetText = (EditText)findViewById(R.id.tweetText);
        startActivity(new Intent(this, MyTweet.class));
    }

    public void updateControls(Tweet tweet)
    {

        //characterCount.setText(' '+count);
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


            tweet.setTweet(s.toString());
            characterCount.setText(String.valueOf(140 - s.length()));
            Log.v("MyTweetApp", "character count: " + s.length() + " " + s.toString());

    }
}
