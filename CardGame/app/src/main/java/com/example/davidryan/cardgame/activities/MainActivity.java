package com.example.davidryan.cardgame.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.davidryan.cardgame.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onDeckButtonPressed(View button) {
        Intent intent = new Intent(this, DeckActivity.class);
        startActivity(intent);
    }

}
