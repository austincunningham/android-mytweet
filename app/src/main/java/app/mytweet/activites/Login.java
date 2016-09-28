package app.mytweet.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.mytweet.R;

/**
 * Created by austin on 27/09/2016.
 */
public class Login extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void LoginPressed (View view)
    {
        startActivity (new Intent(this, MyTweet.class));
        //Toast toast = Toast.makeText(this, "Login Pressed!", Toast.LENGTH_SHORT);
        //toast.show();
    }
}
