package com.example.myapplication;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Button addNoteButton;
    Button aboutMeButton;
    Button submitTextNote;
    Button DisPlayNoteButton;
    Button addButton;
    ImageView logo;
    ProgressBar progressBar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        addNoteButton = findViewById(R.id.button);
        addNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("Click");
                Intent aboutMe = new Intent(getApplicationContext(),AddNoteActivity.class);
                startActivity(aboutMe);
            }
        });
        aboutMeButton = findViewById(R.id.button2);
        aboutMeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent aboutMe = new Intent(getApplicationContext(), AboutmeActivity.class);
                startActivity(aboutMe);
            }
        });

        DisPlayNoteButton = findViewById(R.id.button7);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        logo=findViewById(R.id.imageView2);
        logo.setImageResource(R.drawable.monkey1);

        DisPlayNoteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(view.VISIBLE);
                new Thread(() -> {
                    try{
                        Thread.sleep(2000);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                    runOnUiThread(()->{
                        progressBar.setVisibility(View.GONE);
                        Intent DisplayNoteAct = new Intent(getApplicationContext(), DisplayNoteActivity.class);
                        startActivity(DisplayNoteAct);
                    });
                }).start();

            }
        });
    }
}