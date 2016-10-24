package app.mytweet.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.text.Editable;
import android.widget.TextView;
import android.widget.Toast;
import android.view.MenuItem;

import app.mytweet.R;
import app.mytweet.android.helpers.ContactHelper;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.Portfolio;
import app.mytweet.models.Tweet;

import static app.mytweet.android.helpers.ContactHelper.sendEmail;
import static app.mytweet.android.helpers.IntentHelper.selectContact;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import android.app.DatePickerDialog;

import static app.mytweet.android.helpers.IntentHelper.navigateUp;
import android.Manifest;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;

import android.support.v13.app.FragmentCompat;
import android.support.v4.content.ContextCompat;
import android.Manifest;
import android.content.pm.PackageManager;

/**
 * Created by austin on 28/09/2016.
 */
public class MyTweetFragment extends Fragment implements TextWatcher,
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
    Intent data;
    String tweetContent;
    boolean readOnly = false;

    MyTweetApp app;
    public static final String EXTRA_TWEET_ID = "mytweet.TWEET_ID";



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        //Long tweId = (Long)getActivity().getIntent().getSerializableExtra(EXTRA_TWEET_ID);
        Long tweId = (Long)getArguments().getSerializable(EXTRA_TWEET_ID);

        app = MyTweetApp.getApp();
        portfolio = app.portfolio;
        tweet = portfolio.getTweet(tweId);


        /*setContentView(R.layout.activity_mytweet);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tweetButton = (Button)findViewById(R.id.tweetButton);
        dateButton = (Button)findViewById(R.id.registration_date);
        tweetText = (EditText)findViewById(R.id.tweetText);
        characterCount = (TextView)findViewById(R.id.characterCount);
        selectContact = (Button) findViewById(R.id.selectContact);
        emailTweet = (Button) findViewById(R.id.emailTweet);

        tweet = new Tweet();

        tweetText.addTextChangedListener(this);
        dateButton.setOnClickListener(this);
        emailTweet.setOnClickListener(this);
        MyTweetApp app = (MyTweetApp)getApplication();
        portfolio = app.portfolio;
        Long tweId = (Long) getIntent().getExtras().getSerializable("TWEET_ID");
        tweet = portfolio.getTweet(tweId);
        if (tweet != null)
        {
            updateControls(tweet);
        }*/

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState){
        super.onCreateView(inflater, parent, savedInstanceState);
        View v = inflater.inflate(R.layout.activity_mytweet, parent, false);

        addListeners(v);
        updateControls(tweet);
        return v;
    }

    private void addListeners(View v){
        tweetButton = (Button)v.findViewById(R.id.tweetButton);
        dateButton = (Button)v.findViewById(R.id.registration_date);
        tweetText = (EditText)v.findViewById(R.id.tweetText);
        characterCount = (TextView)v.findViewById(R.id.characterCount);
        selectContact = (Button)v.findViewById(R.id.selectContact);
        emailTweet = (Button)v.findViewById(R.id.emailTweet);

        tweetText.addTextChangedListener(this);
        dateButton.setOnClickListener(this);
        emailTweet.setOnClickListener(this);
        tweetButton.setOnClickListener(this);
        selectContact.setOnClickListener(this);
    }


    public boolean updateControls(Tweet tweet)
    {
        dateButton.setText(tweet.getDateString());
        tweetText.setText(tweet.tweetContent);
        if (tweet.tweetContent != "") {
            tweetText.setEnabled(false);
            readOnly = true;
            return readOnly;
        }
        return false;
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.selectContact :
                Intent i = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
                startActivityForResult(i,REQUEST_CONTACT);
                break;
            case R.id.emailTweet :
                if (emailAddress == null)emailAddress="";//guard against null pointer
                sendEmail(getActivity(),emailAddress, getString(R.string.tweet_report_subject), tweet.tweetContent);
                break;
            case R.id.registration_date :
                Calendar c = Calendar.getInstance();
                DatePickerDialog dpd = new DatePickerDialog (getActivity(), this, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                dpd.show();
                break;
            case R.id.tweetButton :
                if (tweetContent.length() > 140)
                {
                    Toast toast = Toast.makeText(getActivity(), "Character limit exceeded", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }else if (readOnly){
                    Toast toast = Toast.makeText(getActivity(), "Tweet is read only", Toast.LENGTH_SHORT);
                    toast.show();
                    break;
                }else
                {
                    tweet.setTweet(tweetContent);
                    portfolio.updateTweet(tweet);
                    Toast toast = Toast.makeText(getActivity(), "Tweet saved", Toast.LENGTH_SHORT);
                    toast.show();
                    startActivity(new Intent(getActivity(), TweetListActivity.class));
                    break;
                }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        switch (requestCode) {
            case REQUEST_CONTACT:
                this.data = data;
                checkContactsReadPermission();
                break;
        }
    }

    private void readContact() {
        String name = ContactHelper.getContact(getActivity(), data);
        emailAddress = ContactHelper.getEmail(getActivity(), data);
        selectContact.setText(name + " : " + emailAddress);
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
        tweetContent= s.toString();
        //tweet.setTweet(s.toString());//this sets the tweetcontent and appears to save it
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
        if (tweet.tweetContent == "")
        {
            portfolio.deleteTweet(tweet);
        }
        super.onPause();
        //portfolio.saveTweets();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                if (tweet.tweetContent == "")
                {
                    portfolio.deleteTweet(tweet);
                }
                navigateUp(getActivity());
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_CONTACT: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    readContact();
                }
                break;
            }
        }
    }
    private void checkContactsReadPermission() {
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.READ_CONTACTS) == PackageManager.PERMISSION_GRANTED) {

            readContact();
        }
        else {
            // Invoke callback to request user-granted permission
            FragmentCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.READ_CONTACTS},
                    REQUEST_CONTACT);
        }
    }
}
