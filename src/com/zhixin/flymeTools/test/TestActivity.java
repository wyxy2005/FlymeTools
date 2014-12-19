package com.zhixin.flymeTools.test;

import android.app.Activity;
import android.os.Bundle;
import com.zhixin.flymeTools.R;
import com.zhixin.flymeTools.Util.ActivityUtil;
import com.zhixin.flymeTools.hook.ActivityColorHook;


/**
 * Created by ZXW on 2014/12/17.
 */
public class TestActivity extends Activity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        ActivityUtil.setStatusBarLit(this);
        ActivityUtil.setDarkBar(this, true);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        if (hasFocus) {
            ActivityColorHook.showNotification(this,this.getResources());
        }
    }
}
