package com.zhixin.flymeTools.app;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.*;
import com.zhixin.flymeTools.R;
import com.zhixin.flymeTools.Util.ColorUtil;
import com.zhixin.flymeTools.Util.ConvertUtil;
import com.zhixin.flymeTools.controls.ColorPickerPreference;

/**
 * Created by ZXW on 2014/12/12.
 */
public  class SettingFragement extends PreferenceFragment {
    private String packgeName;

    public String getPackgeName() {
        return packgeName;
    }

    public void setPackgeName(String packgeName) {
        this.packgeName = packgeName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        if (packgeName!=null){
            this.getPreferenceManager().setSharedPreferencesName(packgeName + "_setting");
        }
        SharedPreferences sharedPreferences=this.getPreferenceManager().getSharedPreferences();
        addPreferencesFromResource(R.xml.app_settting);
        String preference_app_name = getResources().getString(R.string.preference_app_name);
        String preference_smartbar_type = getResources().getString(R.string.preference_smartbar_type);
        String preference_replace_app_name=getResources().getString(R.string.preference_replace_app_name);
        String preference_replace_smartbar=getResources().getString(R.string.preference_replace_smartbar);
        String preference_smartbar_color=getResources().getString(R.string.preference_smartbar_color);
        final EditTextPreference editTextPreference = (EditTextPreference) findPreference(preference_app_name);
        final ListPreference listPreference = (ListPreference) findPreference(preference_smartbar_type);
        final CheckBoxPreference replace_app_checkBox=(CheckBoxPreference) findPreference(preference_replace_app_name);
        final CheckBoxPreference replace_smartbar_checkBox=(CheckBoxPreference) findPreference(preference_replace_smartbar);
        final ColorPickerPreference colorPickerPreference=(ColorPickerPreference) findPreference(preference_smartbar_color);
        if (colorPickerPreference!=null){
            colorPickerPreference.setTitle(ColorUtil.toHexEncoding(sharedPreferences.getInt(preference_smartbar_color, 0)));
        }
        if(!sharedPreferences.getBoolean(preference_replace_app_name,false)){
            editTextPreference.setEnabled(false);
        }
        if(!sharedPreferences.getBoolean(preference_replace_smartbar,false)){
            listPreference.setEnabled(false);
        }
        //修改Smartbar类型
        String smart_type = sharedPreferences.getString(preference_smartbar_type, null);
        colorPickerPreference.setEnabled(smart_type.equals("3"));
        int index = listPreference.findIndexOfValue(String.valueOf(smart_type));
        if (index != -1) {
            CharSequence[] entries = listPreference.getEntries();
            listPreference.setTitle(entries[index]);
        }
        //设置修改的应用名称
        String app_name = sharedPreferences.getString(preference_app_name, null);
        if (app_name != null && editTextPreference!=null) {
            editTextPreference.setTitle(app_name);
        }
        if (replace_app_checkBox != null) {
            replace_app_checkBox.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    editTextPreference.setEnabled((Boolean)newValue);
                    return true;
                }
            });
        }
        if (replace_smartbar_checkBox != null) {
            replace_smartbar_checkBox.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    listPreference.setEnabled((Boolean) newValue);
                    return true;
                }
            });
        }
        if (editTextPreference != null) {
            editTextPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
                @Override
                public boolean onPreferenceChange(Preference preference, Object newValue) {
                    editTextPreference.setTitle(newValue.toString());
                    return true;
                }
            });
        }
        listPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int index = listPreference.findIndexOfValue(newValue.toString());
                CharSequence[] entries = listPreference.getEntries();
                listPreference.setTitle(entries[index]);
                colorPickerPreference.setEnabled(newValue.toString().equals("3"));
                return true;
            }
        });
        colorPickerPreference.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int color= ConvertUtil.string2Int(newValue.toString(),0);
                colorPickerPreference.setTitle(ColorUtil.toHexEncoding(color));
                return true;
            }
        });
    }
}