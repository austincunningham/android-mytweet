package app.mytweet.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.text.Editable;
import android.widget.TextView;
import android.widget.Toast;

import app.mytweet.R;
import app.mytweet.android.helpers.ContactHelper;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.Portfolio;
import app.mytweet.models.Tweet;

import static app.mytweet.android.helpers.ContactHelper.getContact;
import static app.mytweet.android.helpers.ContactHelper.sendEmail;
import static app.mytweet.android.helpers.IntentHelper.selectContact;
import android.content.Intent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Created by austin on 28/09/2016.
 */
public class MyTweet extends AppCompatActivity implements TextWatcher,
        CompoundButton.OnCheckedChangeListener, View.OnClickListener,
        DatePickerDialog.OnDateSetListener {

    private EditText tweetText;
    private TextView characterCount;
    private Tweet tweet;
    private Button dateButton;
    private Button tweetButton;
    private Button selectContact;
    private Button emailTweet;
    private static final int REQUEST_CONTACT = 1;
    private String emailAddress = "";
    private Portfolio portfolio;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mytweet);

        tweetButton = (Button)findViewById(R.id.tweetButton);
        dateButton = (Button)findViewById(R.id.registration_date);
        tweetText = (EditText)findViewById(R.id.tweetText);
        characterCount = (TextView)findViewById(R.id.characterCount);
        selectContact = (Button) findViewById(R.id.selectContact);
        emailTweet = (Button) findViewById(R.id.emailTweet);

        tweet = new Tweet();

        //date.setEnabled(false);

        tweetText.addTextChangedListener(this);

        if (tweet != null){
            updateControls(tweet);
        }

        MyTweetApp app = (MyTweetApp)getApplication();
        portfolio = app.portfolio;

        Long tweId = (Long) getIntent().getExtras().getSerializable("TWEET_ID");
        tweet = portfolio.getTweet(tweId);

        if (tweet != null)
        {
            updateControls(tweet);
        }
        dateButton.setOnClickListener(this);
    }

    public void tweetPressed (View view)
    {
        MyTweetApp app = (MyTweetApp)getApplication();

        tweetText = (EditText)findViewById(R.id.tweetText);
        startActivity(new Intent(this, MyTweet.class));
        Toast toast = Toast.makeText(this, "Tweet saved", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void updateControls(Tweet tweet)
    {
        tweetText.setText(tweet.tweetContent);
        dateButton.setText(tweet.getDateString());
        selectContact.setOnClickListener(this);
        emailTweet.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.selectContact : selectContact(this, REQUEST_CONTACT);
                break;
            case R.id.registration_date : Calendar c = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog (this, this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dpd.show();
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode)
        {
            case REQUEST_CONTACT:
                String name = ContactHelper.getContact(this, data);
                emailAddress = ContactHelper.getEmail(this, data);
                selectContact.setText(name + " : " + emailAddress);
                //residence.tenant = name;
                break;
            case R.id.emailTweet :
                sendEmail(this, emailAddress,"email from Tweet","email body");
                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {


    }
    @Override
    public void afterTextChanged(Editable s)
    {
        tweet.setTweet(s.toString());
        characterCount.setText(String.valueOf(140 - s.length()));
        Log.v("MyTweetApp", "character count: " + s.length() + " " + s.toString());
    }

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
        Date date = new GregorianCalendar(year, monthOfYear, dayOfMonth).getTime();
        tweet.date = date.getTime();
        dateButton.setText(tweet.getDateString());
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

    }

    @Override
    public void onPause()
    {
        super.onPause();
        portfolio.saveTweets();
    }
}
