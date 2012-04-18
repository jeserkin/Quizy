package com.quizy.manager;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;

public class Preferences extends PreferenceActivity
{
    @Override
    protected void onCreate( Bundle savedInstanceState )
    {
        super.onCreate( savedInstanceState );
        addPreferencesFromResource( R.xml.preferences );

        setTitle( "Quizy Preferences" );

        Preference pref = findPreference( "reset" );

        pref.setOnPreferenceClickListener( new OnPreferenceClickListener()
        {
            public boolean onPreferenceClick( Preference preference )
            {
                Editor edit = preference.getSharedPreferences().edit();

                edit.clear();
                edit.commit();

                startActivity( getIntent() );
                finish();

                return false;
            }
        });

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences( getApplicationContext() );

        sp.registerOnSharedPreferenceChangeListener( new OnSharedPreferenceChangeListener()
        {
            public void onSharedPreferenceChanged( SharedPreferences sharedPreferences, String key )
            {
                Editor edit = sharedPreferences.edit();

                edit.putBoolean( "changed",  true );
                edit.commit();
            }
        });
    }
}