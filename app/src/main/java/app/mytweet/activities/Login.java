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
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by austin on 27/09/2016.
 */
public class Login extends AppCompatActivity implements Callback<User>{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void LoginPressed (View view) {
        MyTweetApp app = (MyTweetApp) getApplication();

        EditText email = (EditText) findViewById(R.id.emailLogin);
        EditText password = (EditText) findViewById(R.id.passwordLogin);

        User user = new User(" ", " ", email.getText().toString(), password.getText().toString());
        Call<User> call = app.myTweetService.login(user);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Response<User> response, Retrofit retrofit)  {
        response.isSuccess();
        Log.v("authenticated", " "+MyTweetApp.valid);
        startActivity(new Intent(this, TweetListActivity.class));
    }

    @Override
    public void onFailure(Throwable t) {
        Log.e("authenticated", " "+t);
        Toast toast = Toast.makeText(this, "Login Pressed! Invalid Credentials", Toast.LENGTH_SHORT);
        toast.show();
    }

}
