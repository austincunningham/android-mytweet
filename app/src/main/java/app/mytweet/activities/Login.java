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
import app.mytweet.models.Token;
import app.mytweet.models.User;
/*import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;*/

import app.mytweet.retrofit.MyTweetServiceProxy;
import app.mytweet.retrofit.RetrofitServiceFactory;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by austin on 27/09/2016.
 */
public class Login extends AppCompatActivity //implements Callback<Token>
{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void LoginPressed (View view) {
        MyTweetApp app = (MyTweetApp) getApplication();

        EditText email = (EditText) findViewById(R.id.emailLogin);
        EditText password = (EditText) findViewById(R.id.passwordLogin);

        User user = new User(" ", " ", email.getText().toString(), password.getText().toString(), null);

        if(app.validUser(user.email,user.password)){
            startActivity(new Intent(this, TweetListActivity.class));
        }
         /*Call<Token> call =(Call<Token>) app.myTweetServiceOpen.login(user);
        call.enqueue(this);*/
    }

   /* @Override
    public void onResponse(Call<Token> call, Response<Token> response) {
        Token auth = response.body();
        MyTweetApp.myTweetService = RetrofitServiceFactory.createService(MyTweetServiceProxy.class, auth.token);
        MyTweetApp.currentUser = auth.user;

        Log.v("authenticated", " "+MyTweetApp.currentUser);
        startActivity(new Intent(this, TweetListActivity.class));
    }

    @Override
    public void onFailure(Call<Token> call, Throwable t) {
        Log.e("authenticated", " "+t);
        Toast toast = Toast.makeText(this, "Login Pressed! Invalid Credentials", Toast.LENGTH_SHORT);
        toast.show();
    }*/
}
