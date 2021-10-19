/**
 * The options activity allows the user to manipulate the dimensions of the field of cells
 * before starting a game, as well as initialize the # of cats to be found behind cells.
 * These options will be dynamically saved in between game startups.
 */

package com.example.cmpt276ass3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cmpt276ass3.model.Settings;

public class OptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Settings gameSettings;
    private static final String CURRENT_DIMENSION = "Current Dimension";
    private static final String CURRENT_CAT_COUNT = "Current CAT Count";
    private static final String PREFS_DIMENSION = "DimensionPref";
    private static final String PREFS_CAT_COUNT = "CatCountPref";
    private Button timesPlayed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        gameSettings = Settings.getInstance(this);

        createDropDownMenu();
        createRadioButtons();
        setupTimesPlayed();
    }

    private void setupTimesPlayed() {
        timesPlayed = findViewById(R.id.clearButton);
        timesPlayed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(OptionActivity.this, getString(R.string.clear_successful), Toast.LENGTH_SHORT).show();
                GameActivity.clearSharedPrefs(OptionActivity.this);
            }
        });
    }

    private void createRadioButtons() {
        RadioGroup dimensionGroup = (RadioGroup) findViewById(R.id.dimensionSettings);
        String[] dimensionOptions = getResources().getStringArray((R.array.row_by_column));


        for (int i = 0; i < dimensionOptions.length; i++) {
            String dimensionOption = dimensionOptions[i];
            RadioButton rButton = new RadioButton(this);
            rButton.setText(dimensionOption);
            rButton.setTextColor(ContextCompat.getColor(this, R.color.white));
            rButton.setPadding(0, 0, 360, 0);

            rButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveDimensionChoice(dimensionOption);
                    gameSettings.updateDimensions();
                }
            });

            dimensionGroup.addView(rButton);

            if (dimensionOption.equals(getDimensionChoice(this))) {
                rButton.setChecked(true);
            }

        }
    }

    //Code referenced from: https://developer.android.com/guide/topics/ui/controls/spinner#
    private void createDropDownMenu() {
        Spinner catSpinner = (Spinner) findViewById(R.id.catCountSettings);
        catSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.number_of_cats, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        catSpinner.setAdapter(adapter);

        int spinnerPosition = adapter.getPosition("" + getCatCountChoice(this));
        catSpinner.setSelection(spinnerPosition);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        int catCountOption = Integer.parseInt(parent.getItemAtPosition(pos).toString());
        saveCatCountChoice(catCountOption);
        gameSettings.updateCatCount();
    }

    public void onNothingSelected(AdapterView<?> parent) {}

    private void saveDimensionChoice(String dimensionOption) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_DIMENSION, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(CURRENT_DIMENSION, dimensionOption);
        editor.apply();
    }

    public static String getDimensionChoice(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_DIMENSION, MODE_PRIVATE);
        String defaultDimension = context.getResources().getString(R.string.default_row_by_column);
        return prefs.getString(CURRENT_DIMENSION, defaultDimension);
    }

    private void saveCatCountChoice(int catCountOption) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_CAT_COUNT, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(CURRENT_CAT_COUNT, catCountOption);
        editor.apply();
    }

    public static int getCatCountChoice(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_CAT_COUNT, MODE_PRIVATE);
        String defaultCatCount = context.getResources().getString(R.string.default_number_of_cats);
        return prefs.getInt(CURRENT_CAT_COUNT, Integer.parseInt(defaultCatCount));
    }

    public static Intent newIntent(Context c) {
        Intent intent = new Intent(c, OptionActivity.class);
        return intent;
   }
}