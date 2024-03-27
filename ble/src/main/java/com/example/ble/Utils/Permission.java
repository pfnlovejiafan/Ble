package com.example.ble.Utils;

/**
 * 项目名称：MyBle
 * 创建人： PANZERS
 * 创建时间：2024/3/2510:49
 * 修改人：PANZERS
 * 修改时间：2024/3/2510:49
 * 修改备注：权限申请蓝牙
 */
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permission {
    //需要申请权限的数组
    private String[] permissions = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.BLUETOOTH_ADMIN,
            Manifest.permission.BLUETOOTH_CONNECT,
            Manifest.permission.BLUETOOTH,
            Manifest.permission.BLUETOOTH_SCAN,
    };
    //保存真正需要去申请的权限
    private List<String> permissionList = new ArrayList<>();

    public static int RequestCode = 100;

    public void checkPermissions(Activity activity) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            //需要申请权限的数组
             String[] permissions = {
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_ADVERTISE,
                    Manifest.permission.BLUETOOTH_CONNECT,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
            };
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(activity,permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permissions[i]);
                }
            }
            //有需要去动态申请的权限
            if (permissionList.size() > 0) {
                requestPermission(activity);
            }
        }else /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) */{
            for (int i = 0; i < permissions.length; i++) {
                if (ContextCompat.checkSelfPermission(activity,permissions[i]) != PackageManager.PERMISSION_GRANTED) {
                    permissionList.add(permissions[i]);
                }
            }
            //有需要去动态申请的权限
            if (permissionList.size() > 0) {
                requestPermission(activity);
            }
        }
    }
    //去申请的权限
    public void requestPermission(Activity activity) {
        ActivityCompat.requestPermissions(activity,permissionList.toArray(new String[permissionList.size()]),RequestCode);
    }

}
