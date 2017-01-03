package app.mytweet.activities;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.Portfolio;
import app.mytweet.models.Tweet;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import static app.mytweet.android.helpers.CameraHelper.showPhoto;

public class TweetGalleryActivity extends AppCompatActivity
{

    public static   final String  EXTRA_PHOTO_FILENAME = "org.wit.myrent.photo.filename";
    private ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_gallery);
        photoView = (ImageView) findViewById(R.id.tweetGalleryImage);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        showPicture();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home  : onBackPressed();
                return true;
            default                 : return super.onOptionsItemSelected(item);
        }
    }

    private void showPicture()
    {
        Long tweId = (Long)getIntent().getSerializableExtra(MyTweetFragment.EXTRA_TWEET_ID);
        MyTweetApp app = (MyTweetApp) getApplication();
        Portfolio portfolio = app.portfolio;
        Tweet tweet = portfolio.getTweet(tweId);
        showPhoto(this, tweet,  photoView);
    }
}