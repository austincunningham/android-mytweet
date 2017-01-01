package app.mytweet.models;

import java.util.List;

/**
 * Created by austin on 28/09/2016.
 */
public class User {
    public String firstName;
    public String lastName;
    public String email;
    public String password;
    public List<String> following;
    public String _id;

    public User(String firstName, String lastName, String email, String password, List<String> following ){
        //this._id = _id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.following = following;
    }
}
