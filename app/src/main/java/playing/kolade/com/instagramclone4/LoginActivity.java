package playing.kolade.com.instagramclone4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    EditText edtLoginUsername, edtLoginPassword;
    Button btnLoginLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Log in"); //Sets the title of the activity

        edtLoginUsername = findViewById(R.id.edtLoginUsername);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        edtLoginPassword.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if(keyCode ==KeyEvent.KEYCODE_ENTER && event.getAction()==KeyEvent.ACTION_DOWN)

                    onClick(btnLoginLogin); //This block is to make the Enter key perform te function of btnLogiLogin button

                return false;
            }
        });
        btnLoginLogin = findViewById(R.id.btnLoginLogin);

        btnLoginLogin.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null){
            ParseUser.logOut();
        }


    }

    @Override
    public void onClick(View v) {

        if (edtLoginUsername.getText().toString().equals("") || edtLoginPassword.getText().toString().equals("")) {
            Toast.makeText(this, "Email or password cannot be empty", Toast.LENGTH_SHORT).show();
        } else {

            ParseUser.logInInBackground(edtLoginUsername.getText().toString(), edtLoginPassword.getText().toString(), new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {

                    if (user != null && e == null) {
                        Toast.makeText(LoginActivity.this, edtLoginUsername.getText().toString() + " has logged in", Toast.LENGTH_LONG).show();
                        transitionToSocialMedia();
                    } else {
                        Toast.makeText(LoginActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }

                }
            });
        }
    }

    public void logInLayoutClicked (View view) { //This block is to make the keypad to disappear when the layout is clicked

        try {

            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void transitionToSocialMedia (){

        Intent intent = new Intent(LoginActivity.this, SocialMediaActivity.class);
        startActivity(intent);

    }
    }

