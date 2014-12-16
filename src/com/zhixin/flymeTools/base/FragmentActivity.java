package com.zhixin.flymeTools.base;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ContextWrapper;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.view.View;
import com.zhixin.flymeTools.Util.ActivityUtil;
import com.zhixin.flymeTools.Util.FileUtil;
import com.zhixin.flymeTools.Util.ReflectionUtil;

import java.io.File;
import java.lang.reflect.Field;

/**
 * Created by ZXW on 2014/12/12.
 */
public class FragmentActivity extends Activity {
    public static void savePreToSDcard(Activity context) {
        try {
            // 获取ContextWrapper对象中的mBase变量。该变量保存了ContextImpl对象
            Field field = ContextWrapper.class.getDeclaredField("mBase");
            field.setAccessible(true);
            // 获取mBase变量
            Object obj = field.get(context);
            // 获取ContextImpl。mPreferencesDir变量，该变量保存了数据文件的保存路径
            field = obj.getClass().getDeclaredField("mPreferencesDir");
            field.setAccessible(true);
            // 创建自定义路径
            String packageName = context.getPackageName();
            File file = FileUtil.getgetSharedPreferencesRoot(packageName);
            if (!file.exists()) {
                file.mkdirs();
            }
            // 修改mPreferencesDir变量的值
            field.set(obj, file);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        savePreToSDcard(this);
        ActivityUtil.setStatusBarLit(this);
        ActivityUtil.setDarkBar(this, true);
        PreferenceFragment preferenceFragment = OnCreateFragment(savedInstanceState);
        if (preferenceFragment != null) {
            getFragmentManager().beginTransaction().replace(android.R.id.content, preferenceFragment).commit();
        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus){
            ActivityUtil.changeContextViewPadding(this,true);
        }
    }
    protected PreferenceFragment OnCreateFragment(Bundle savedInstanceState) {
        return null;
    }
}
