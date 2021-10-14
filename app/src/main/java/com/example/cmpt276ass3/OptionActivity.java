package com.example.cmpt276ass3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class OptionActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
    }

    public static Intent newIntent(Context c) {
        Intent intent = new Intent(c, OptionActivity.class);
        return intent;
    }
}