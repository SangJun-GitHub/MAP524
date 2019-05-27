package com.example.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class ResultActivity extends AppCompatActivity {
    private Button again;
    private Button exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_end_game);

        again = findViewById(R.id.Continue);
        again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToLevelActivity();
            }
        });

        exit = findViewById(R.id.exit);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.exit(0);
            }
        });


    }

    public void goToLevelActivity()
    {
        Intent intent = new Intent(this, LevelActivity.class);
        startActivity(intent);
    }
}
