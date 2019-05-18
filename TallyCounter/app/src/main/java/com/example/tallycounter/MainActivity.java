package com.example.tallycounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView countTextView;
    private int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        count = 0;
        countTextView = findViewById(R.id.textView);
        Button countButton = findViewById(R.id.button1);
        countButton.setOnClickListener(onClickCountButton);
        Button resetButton = findViewById(R.id.button2);
        resetButton.setOnClickListener(onClickResetButton);
    }
    private View.OnClickListener onClickCountButton = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            count++;
            countTextView.setText(String.valueOf(count));
        }
    };
    private View.OnClickListener onClickResetButton = new View.OnClickListener()
    {
        @Override
        public void onClick(View v)
        {
            count = 0;
            countTextView.setText(String.valueOf(count));
        }
    };
}
