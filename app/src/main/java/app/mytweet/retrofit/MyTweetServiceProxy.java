package app.mytweet.retrofit;

/**
 * Created by austin on 16/12/2016.
 */

import java.util.List;

import app.mytweet.models.Tweet;
import app.mytweet.models.User;
import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.DELETE;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;

public interface MyTweetServiceProxy {

    @GET("/api/users")
    Call<List<User>> getAllUsers();

    //@GET("/api/users/{id}")
    //Call<User> getUser(@Path("id") String id);

    //@DELETE("/api/users/{id}")
    //Call<User> deleteUser(@Path("id") String id);

    @GET("/api/users/email/{email}")
    Call<User> findUserByEmail(@Path("email") String email);

    @POST("/api/users/androidLogin")
    Call<User> login(@Body User user);

    //@DELETE("/api/users/email/{email}")
    //Call<User> deleteUserByEmail(@Path("email") String email);

    @POST("/api/users/register")
    Call<User> register(@Body User user);

    //@DELETE("/api/users")
    //Call<List<User>> deleteAllUsers();

    //Tweet API

    @GET("/api/tweets")
    Call<List<Tweet>> getAllTweets();

    //@GET("/api/tweet/{id}")
    //Call<Tweet> getTweetById(@Path("id") String id);

    @GET("/api/tweets/{id}")
    Call<List<Tweet>> getTweetsByUserId(@Path("id") String id);

    //@GET("/api/tweets/email/{email}")
    //Call<List<Tweet>> getTweetsByUserEmail(@Path("email") String email);

    //@DELETE("/api/tweets/{id}")
    //Call<String> deleteTweetById(@Path("id") String id);

    //@DELETE("/api/tweets/email/{email}")
    //Call<String> deleteTweetByUserEmail(@Path("email") String id);

    //@DELETE("/api/tweets")
    //Call<List<Tweet>> deleteAllTweets();

    @POST("/api/tweet/{id}")
    Call<Tweet> newTweetByUserId(@Path("id") String id, @Body Tweet tweet);

    //@POST("/api/tweet/update/{id}")
    //Call<Tweet> updateTweet(@Path("id") String id, @Body Tweet tweet);

    @DELETE("/api/tweets/uuid/{uuid}")
    Call<Tweet> deleteTweetByUuid(@Path("uuid")Long uuid);

    @POST("/api/users/follow/{id}")
    Call<User> follow(@Path("id") String id, @Body User user);

    @POST("/api/users/unfollow/{id}")
    Call<User> unfollow(@Path("id") String id, @Body User user);

}
