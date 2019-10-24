package playing.kolade.com.instagramclone4;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {


    EditText edtSignUpEmail, edtSignUpUserName, edtSignUpPassword;
    Button btnSignUp, btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setTitle("Sign Up Page");


        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpUserName = findViewById(R.id.edtSignUpUserName);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtSignUpPassword.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View v, int keyCode, KeyEvent event) {

                    if (keyCode==KeyEvent.KEYCODE_ENTER&&event.getAction()==KeyEvent.ACTION_DOWN){
                        onClick(btnSignUp);

                    }
                    return false;// This block is to make the Enter key to perform the function of btnSignUp
                }
        });

        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);

        btnSignUp.setOnClickListener(this);
        btnLogin.setOnClickListener(this);

        if(ParseUser.getCurrentUser()!=null){
            //ParseUser.logOut();
            transitionToSocialMedia();
        }
    }

    @Override
    public void onClick(View v) {

        switch(v.getId()) {

            case (R.id.btnSignUp):

                if (edtSignUpUserName.getText().toString().equals("")
                        ||edtSignUpPassword.getText().toString().equals("")
                        ||edtSignUpEmail.getText().toString().equals("")         ){

                    Toast.makeText(this, "Username, emal and password are required", Toast.LENGTH_SHORT).show();

                }

                else {

                    final ParseUser user = new ParseUser(); // This block is to sign up the user with the name, email and password

                    user.setUsername(edtSignUpUserName.getText().toString());
                    user.setEmail(edtSignUpEmail.getText().toString());
                    user.setPassword(edtSignUpPassword.getText().toString());

                    final ProgressDialog progressDialog = new ProgressDialog(this); //The process dialog is to show a progress graphic when data is being loaded
                    progressDialog.setMessage("Signing up " + edtSignUpUserName.getText().toString());
                    progressDialog.show();

                    user.signUpInBackground(new SignUpCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e == null) {
                                Toast.makeText(SignUpActivity.this, user.get("username") + " is signed up", Toast.LENGTH_LONG).show();
                                transitionToSocialMedia();
                            } else {
                                Toast.makeText(SignUpActivity.this, e.getMessage() + "", Toast.LENGTH_LONG).show();
                            }
                            progressDialog.dismiss();
                        }
                    });


                }


                break;

                case (R.id.btnLogin):

                    Intent intent = new Intent (SignUpActivity.this, LoginActivity.class);
                    startActivity(intent);

                break;

        }
    }


    public void layoutIsClicked (View view){

        try {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

            inputMethodManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        }

        public void transitionToSocialMedia (){

        Intent intent = new Intent(SignUpActivity.this, SocialMediaActivity.class);
        startActivity(intent);

        }

}
