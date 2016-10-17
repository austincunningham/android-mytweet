package app.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;

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
        MyTweetApp app = (MyTweetApp)getApplication();

        EditText email = (EditText)findViewById(R.id.emailLogin);
        EditText password = (EditText)findViewById(R.id.passwordLogin);

        if (app.validUser(email.getText().toString(),password.getText().toString())) {
            startActivity(new Intent(this, TweetListActivity.class));
        } else {
            Toast toast = Toast.makeText(this, "Login Pressed! Invalid Credentials", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
}
