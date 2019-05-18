package com.example.loginscreen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(onClickLoginButton);

    }

    private View.OnClickListener onClickLoginButton = new View.OnClickListener(){
        final int MAXATTEMPT = 3;
        private int attempt = 0;
        @Override
        public void onClick(View view){
            EditText userNameEditText = findViewById(R.id.usernameEditText);
            EditText passwordEditText = findViewById(R.id.passwordEditText);
            TextView errorTextView = findViewById(R.id.errorTextView);

            String userName = userNameEditText.getText().toString();
            String password = passwordEditText.getText().toString();

            LoginManager loginManager = new LoginManager(userName, password);

            if(loginManager.hasValidCredentials()){
                errorTextView.setVisibility(View.INVISIBLE);
                attempt = 0;
            }else{
                attempt++;
                errorTextView.setText(getString(R.string.error_text));
                errorTextView.append("Number of remaining attempts(");
                errorTextView.append(Integer.toString(MAXATTEMPT - attempt));
                errorTextView.append(String.valueOf(')'));
                errorTextView.setVisibility(View.VISIBLE);
            }

            if(attempt == MAXATTEMPT){
                Button loginButton = findViewById(R.id.loginButton);
                loginButton.setOnClickListener(onClickLoginButton);
                loginButton.setEnabled(false);
            }
        }
    };
}
