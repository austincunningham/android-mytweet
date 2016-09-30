package app.mytweet.activites;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.User;

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
        EditText firstName =(EditText) findViewById(R.id.firstName);
        EditText lastName = (EditText) findViewById(R.id.lastName);
        EditText email = (EditText) findViewById(R.id.email);
        EditText password = (EditText) findViewById(R.id.password);

        User user = new User(firstName.getText().toString(),lastName.getText().toString(),
                email.getText().toString(),password.getText().toString());

        MyTweetApp app = (MyTweetApp)getApplication();
        app.newUser(user);

        startActivity (new Intent(this, Login.class));
        //Toast toast = Toast.makeText(this, "Login Pressed!", Toast.LENGTH_SHORT);
        //toast.show();
    }
}
