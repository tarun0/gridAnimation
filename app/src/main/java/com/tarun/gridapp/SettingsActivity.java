package com.tarun.gridapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {

        EditTextPreference animationSpeed;
        EditTextPreference columnCount;
        EditTextPreference spacing;

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

        }

        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            spacing = (EditTextPreference)  getPreferenceManager().findPreference("spacing");
            spacing.setText(String.valueOf(Constants.spacing));
            spacing.setSummary(String.valueOf(Constants.spacing));

            animationSpeed = (EditTextPreference)  getPreferenceManager().findPreference("speed");
            animationSpeed.setText(String.valueOf(Constants.animationSpeed));
            animationSpeed.setSummary(String.valueOf(Constants.animationSpeed));

            columnCount = (EditTextPreference)  getPreferenceManager().findPreference("column");
            columnCount.setText(String.valueOf(Constants.columnCount));
            columnCount.setSummary(String.valueOf(Constants.columnCount));

            spacing.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    MainActivity.hasMovedFromMainActivity = true;
                    Constants.spacing = Integer.parseInt((String)newValue);
                    return true;
                }
            });

            animationSpeed.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    MainActivity.hasMovedFromMainActivity = true;
                    Constants.animationSpeed = Integer.parseInt((String)newValue);
                    return true;
                }
            });

            columnCount.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    MainActivity.hasMovedFromMainActivity = true;
                    Constants.columnCount = Integer.parseInt((String)newValue);
                    return true;
                }
            });

            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }
}