package com.example.cmpt276ass3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.cmpt276ass3.model.Settings;

public class OptionActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Settings gameSettings;
    private static final String CURRENT_DIMENSION = "Current Dimension";
    private static final String CURRENT_MINE_COUNT = "Current Mine Count";
    private static final String PREFS_DIMENSION = "DimensionPref";
    private static final String PREFS_MINE_COUNT = "MineCountPref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        gameSettings = Settings.getInstance(this);

        createDropDownMenu();
        createRadioButtons();

        String str = getDimensionChoice(this);
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
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
        Spinner mineSpinner = (Spinner) findViewById(R.id.mineCountSettings);
        mineSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.number_of_mines, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mineSpinner.setAdapter(adapter);

        int spinnerPosition = adapter.getPosition("" + getMineCountChoice(this));
        mineSpinner.setSelection(spinnerPosition);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        int mineCountOption = Integer.parseInt(parent.getItemAtPosition(pos).toString());
        saveMineCountChoice(mineCountOption);
        gameSettings.updateMineCount();
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

    private void saveMineCountChoice(int mineCountOption) {
        SharedPreferences prefs = this.getSharedPreferences(PREFS_MINE_COUNT, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(CURRENT_MINE_COUNT, mineCountOption);
        editor.apply();
    }

    public static int getMineCountChoice(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_MINE_COUNT, MODE_PRIVATE);
        String defaultMineCount = context.getResources().getString(R.string.default_number_of_mines);
        return prefs.getInt(CURRENT_MINE_COUNT, Integer.parseInt(defaultMineCount));
    }

    public static Intent newIntent(Context c) {
        Intent intent = new Intent(c, OptionActivity.class);
        return intent;
   }
}