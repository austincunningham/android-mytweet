/**
 * Created by austin on 21/10/2016.
 */
package app.mytweet.activities;

import app.mytweet.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;


public class MyTweetActivity extends AppCompatActivity {
    ActionBar actionBar;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

        //actionBar = getSupportActionBar();

        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if (fragment == null)
        {
            fragment = new MyTweetFragment();
            manager.beginTransaction().add(R.id.fragmentContainer, fragment).commit();
        }
    }

}
