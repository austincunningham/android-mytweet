package app.mytweet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Welcome extends AppCompatActivity {

    private Button welcomeLogin;
    private Button welcomeSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        welcomeLogin = (Button) findViewById(R.id.welcomeLogin);
        welcomeSignup = (Button) findViewById(R.id.welcomeSignup);
    }

    public void welcomeLoginPressed (View view)
    {
        startActivity (new Intent(this, Login.class));
        //Toast toast = Toast.makeText(this, "Login Pressed!", Toast.LENGTH_SHORT);
        //toast.show();
    }
    public void welcomeSignupPressed (View view)
    {
        startActivity (new Intent(this, SignUp.class));
        //Toast toast = Toast.makeText(this, "Sign Up Pressed!", Toast.LENGTH_SHORT);
        //toast.show();
    }
}
