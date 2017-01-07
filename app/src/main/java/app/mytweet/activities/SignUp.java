package app.mytweet.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import app.mytweet.R;
import app.mytweet.app.MyTweetApp;
import app.mytweet.models.User;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * Created by austin on 27/09/2016.
 */
public class SignUp extends AppCompatActivity implements Callback<User> {

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
                email.getText().toString(),password.getText().toString(),null);

        MyTweetApp app = (MyTweetApp)getApplication();
        app.newUser(user);

        //startActivity (new Intent(this, Login.class));

        Call<User> call =(Call<User>) app.myTweetServiceOpen.register(user);
        call.enqueue(this);
        //Toast toast = Toast.makeText(this, "Login Pressed!", Toast.LENGTH_SHORT);
        //toast.show();
    }

/*    @Override
    public void onResponse(Response<User> response, Retrofit retrofit) {
        Toast toast = Toast.makeText(this, "Login", Toast.LENGTH_SHORT);
        toast.show();
        app.users.add(response.body());
        startActivity(new Intent(this, Login.class));
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("signup", ""+t);
        Toast toast = Toast.makeText(this, "MyTweet Service Unavailable. Try again later", Toast.LENGTH_LONG);
        toast.show();
        startActivity (new Intent(this, Welcome.class));
    }*/

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        Toast toast = Toast.makeText(this, "Login", Toast.LENGTH_SHORT);
        toast.show();
        app.users.add(response.body());
        startActivity(new Intent(this, Login.class));
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        Log.e("signup", ""+t);
        Toast toast = Toast.makeText(this, "MyTweet Service Unavailable. Try again later", Toast.LENGTH_LONG);
        toast.show();
        startActivity (new Intent(this, Welcome.class));
    }
}
