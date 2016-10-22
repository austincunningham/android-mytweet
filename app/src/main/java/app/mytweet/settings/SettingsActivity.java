package app.mytweet.settings;

/**
 * Created by austin on 22/10/2016.
 */


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;

import app.mytweet.R;

public class SettingsActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            SettingsFragment fragment = new SettingsFragment();
            getFragmentManager().beginTransaction()
                    .add(android.R.id.content, fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //what is residencelist
        getMenuInflater().inflate(R.menu.tweetlist, menu);
        return true;
    }

}