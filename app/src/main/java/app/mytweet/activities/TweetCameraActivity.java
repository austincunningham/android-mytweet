package app.mytweet.activities;


import app.mytweet.R;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.ActivityCompat;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.content.Intent;
import android.graphics.Bitmap;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Toast;

import java.util.UUID;

import static app.mytweet.android.helpers.FileIOHelper.writeBitmap;
/**
 * Created by austin on 03/01/2017.
 */

public class TweetCameraActivity extends AppCompatActivity implements View.OnClickListener
{
    private Button    savePhoto;
    private Button    takePhoto;
    private ImageView tweetImage;
    private static  final int CAMERA_RESULT = 5;
    public static final String EXTRA_PHOTO_FILENAME = "app.mytweet.photo.filename";
    private Bitmap tweetPhoto;
    public static final int CAMERA_PERMISSION = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tweet_photo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tweetImage = (ImageView) findViewById(R.id.tweetImage);
        savePhoto = (Button)findViewById(R.id.savePhoto);
        takePhoto = (Button)findViewById(R.id.takePhoto);

        savePhoto.setEnabled(false);
        savePhoto.setOnClickListener(this);
        takePhoto.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.takePhoto     : onTakePhotoClicked(v);
                break;

            case R.id.savePhoto     : onPictureTaken(tweetPhoto);
                break;
        }
    }

    public void onTakePhotoClicked(View v)
    {
        // Check for presence of device camera. If not present advise user and quit method.
        PackageManager pm = getPackageManager();
        if (!pm.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            Toast.makeText(this, "Camera app not present on this device", Toast.LENGTH_SHORT).show();
            return;
        }
        // The device has a camera app ... so use it.
        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent,CAMERA_RESULT);
        savePhoto.setEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case TweetCameraActivity.CAMERA_RESULT:
                if (data != null) {
                    processImage(data);
                } else {
                    Toast.makeText(this, "Camera failure: check simulated camera present emulator advanced settings",
                            Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    private void onPictureTaken(Bitmap data)
    {
        String filename = UUID.randomUUID().toString() + ".png";
        if(writeBitmap(this, filename, data) == true)
        {
            Intent intent = new Intent();
            intent.putExtra(EXTRA_PHOTO_FILENAME, filename);
            setResult(Activity.RESULT_OK, intent);
        }
        else
        {
            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }

    private void processImage(Intent data)
    {
        tweetPhoto = (Bitmap) data.getExtras().get("data");
        if(tweetPhoto == null)
        {
            Toast.makeText(this, "Attempt to take photo did not succeed", Toast.LENGTH_SHORT).show();
        }
        tweetImage.setImageBitmap(tweetPhoto);
    }
}