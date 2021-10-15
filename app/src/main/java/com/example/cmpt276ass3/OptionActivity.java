package com.example.cmpt276ass3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.cmpt276ass3.model.Settings;

public class OptionActivity extends AppCompatActivity {
    private Settings gameSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        gameSettings = Settings.getInstance();
        createRadioButtons();
    }

    private void createRadioButtons() {
        RadioGroup sizeGroup = (RadioGroup) findViewById(R.id.sizeSettings);

        String[] sizeOptions = getResources().getStringArray((R.array.row_by_column));

        for (int i = 0; i < sizeOptions.length; i++) {
            String sizeOption = sizeOptions[i];
            RadioButton rButton = new RadioButton(this);
            rButton.setText(sizeOption);

            rButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    switch(sizeOption) {
                        case("4 x 6") :
                            gameSettings.setNumberOfMines(4);
                            gameSettings.setNumberOfColumns(6);
                            break;
                        case("5 x 10") :
                            gameSettings.setNumberOfMines(5);
                            gameSettings.setNumberOfColumns(10);
                            break;
                        case("6 x 15") :
                            gameSettings.setNumberOfMines(6);
                            gameSettings.setNumberOfColumns(15);
                            break;
                    }

                    saveSizeChoice(sizeOption);
                }
            });
            sizeGroup.addView(rButton);

            //select default/on resume option;
            if (sizeOption == getSizeChoice(this)) {
                rButton.setChecked(true);
            }
        }
    }

    private void saveSizeChoice(String sizeOption) {
        SharedPreferences prefs = this.getSharedPreferences("SizePrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Size Choice", sizeOption);
        editor.apply();
    }

    public static String getSizeChoice(Context context) {
        SharedPreferences prefs = context.getSharedPreferences("SizePrefs", MODE_PRIVATE);
        String defaultSize = context.getResources().getString(R.string.default_row_by_column);
        return prefs.getString("Size Choice", defaultSize);
    }

    public static Intent newIntent(Context c) {
        Intent intent = new Intent(c, OptionActivity.class);
        return intent;

   }
}