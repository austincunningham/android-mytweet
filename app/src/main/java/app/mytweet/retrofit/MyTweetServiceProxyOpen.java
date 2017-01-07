package app.mytweet.retrofit;

import java.util.List;

import app.mytweet.models.Token;
import app.mytweet.models.Tweet;
import app.mytweet.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyTweetServiceProxyOpen {

    @POST("/api/users/login")
    Call<Token> login(@Body User user);

    @POST("/api/users/register")
    Call<User> register(@Body User user);

    @GET("/api/tweets")
    Call<List<Tweet>> getAllTweets();
}
