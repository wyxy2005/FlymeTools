package com.zhixin.flymeTools.key;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import com.zhixin.flymeTools.R;

/**
 * Created by ZXW on 2014/12/12.
 */
public class SettingFragement extends PreferenceFragment {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.key_settting);
    }
}