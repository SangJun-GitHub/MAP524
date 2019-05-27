package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class GameActivity extends AppCompatActivity {

    Button hint;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        hint = findViewById(R.id.hint);
        submit = findViewById(R.id.submit);

        hint.setOnClickListener(new View.OnClickListener() {
            final int MAXAATTEMPT = 5;
            private int attempt = 0;

            @Override
            public void onClick(View view) {
                hintActivityShow();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            final int MAXAATTEMPT = 5;
            private int attempt = 0;
            @Override
            public void onClick(View view){
                EditText answerTextView = findViewById(R.id.answer);
                TextView errorTextView = findViewById(R.id.errorTextView);

                String answer = answerTextView.getText().toString();
                GameManager gameManager = new GameManager(answer);

                if(gameManager.lengthChecker(answer)){
                    errorTextView.setText(getString(R.string.error_text));
                    errorTextView.append("Please enter 6 charters");
                    errorTextView.setVisibility(View.VISIBLE);
                }else {
                    if(!gameManager.checker(answer)){
                        if(attempt == MAXAATTEMPT){
                            goToResultActivity();
                        }else{
                            attempt++;
                            errorTextView.setText(getString(R.string.error_text));
                            errorTextView.append("Number of remaining attempts(");
                            errorTextView.append(Integer.toString(MAXAATTEMPT - attempt));
                            errorTextView.append(String.valueOf(')'));
                            errorTextView.setVisibility(View.VISIBLE);
                            answerTextView.setText(gameManager.getAnswer().toString());
                        }
                    }else{
                        goToResultActivity();
                    }
                }
            }
        });
    }

    public void goToResultActivity()
    {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    public void hintActivityShow()
    {
        Intent intent = new Intent(this, PopUpHint.class);
        startActivity(intent);
    }

}
