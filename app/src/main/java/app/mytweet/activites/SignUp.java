package app.mytweet.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;

/**
 * Created by austin on 27/09/2016.
 */
public class SignUp extends AppCompatActivity {

    private MyTweetApp app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        app = (MyTweetApp)getApplication();

    }
    public void SignUpPressed (View view)
    {
        startActivity (new Intent(this, Login.class));
        //Toast toast = Toast.makeText(this, "Login Pressed!", Toast.LENGTH_SHORT);
        //toast.show();
    }
}
