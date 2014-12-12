package com.zhixin.flymeTools.app;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.Iterator;
import java.util.List;

/**
 * Created by zhixin on 2014/12/11.
 */
public  class AppListUtil {
    public static boolean isSystemApp(PackageInfo pInfo) {
        return ((pInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0);
    }
    public static AppItemAdapter getAppItemAdapter(Context context,boolean includeSys){
        final PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> pinfo = packageManager.getInstalledPackages(0);
        AppItemAdapter  mAdapter = new AppItemAdapter(context);
        if (pinfo != null) {
            Iterator<PackageInfo> iter = pinfo.iterator();
            while (iter.hasNext()) {
                PackageInfo info = iter.next();
                boolean isSysApp=isSystemApp(info);
                if (!isSysApp|| includeSys) {
                    AppItem item = new AppItem(
                            info.packageName
                            , info.applicationInfo.loadLabel(packageManager).toString()
                            ,isSysApp, info.applicationInfo.loadIcon(packageManager)
                    );
                    mAdapter.addItem(item);
                }
            }
        }
        return  mAdapter;
    }
}