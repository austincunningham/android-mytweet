package app.mytweet.models;

/**
 * Created by austin on 06/01/2017.
 */

public class Token {
    public boolean success;
    public String token;
    public User user;

    public Token(boolean success, String token)
    {
        this.success = success;
        this.token = token;
    }
}
