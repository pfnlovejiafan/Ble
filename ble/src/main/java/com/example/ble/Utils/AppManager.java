package com.example.ble.Utils;
import android.util.Log;

import com.example.ble.bean.BaseActivity;

import java.util.Stack;

/**
 * AppManager: 用于对活动进行管理。该模块仅限 base 包内使用。
 * 该模块为单一实例，您需要调用 AppManager.get() 获取实例后再调用方法。
 * <p>
 * 为确保应用管理器正常工作，请新建一个继承 Activity 的抽象类 BaseActivity，
 * 然后重写 BaseActivity 类的 onCreate() 和 onDestroy() 方法。
 * 请给 BaseActivity 类的 onCreate() 方法添加如下代码：
 * AppManager.get().addActivity(this);
 * 请给 BaseActivity 类的 onDestroy() 方法添加如下代码：
 * AppManager.get().removeActivity(this);
 * 最后，确保本 APP 内的所有活动类均继承于 BaseActivity 类。
 */
public class AppManager {
    private static final AppManager MANAGER = new AppManager();
    private Stack<BaseActivity> mStack;

    private AppManager() {
        // 将作用域关键字设置为 private 以隐藏该类的构造器。
        mStack = new Stack<>();
    } // AppManager() (Class Constructor)

    /**
     * get(): 获得 AppManager 类的单例。
     *
     * @return 该类的单例 MANAGER。
     */
    public static AppManager get() {
        return MANAGER;
    } // get()

    /**
     * addActivity(): 向堆栈中添加一个活动对象。
     *
     * @param activity 要添加的活动对象。
     */
    public void addActivity(BaseActivity activity) {
        mStack.add(activity);
        Log.i("AppManager", "[+] Created: " + activity.getClass().getName());
    } // addActivity()

    /**
     * removeActivity(): 从堆栈中移除一个活动对象。
     *
     * @param activity 要移除的活动对象。
     */
    public void removeActivity(BaseActivity activity) {
        mStack.remove(activity);
        Log.i("AppManager", "<-> Removed: " + activity.getClass().getName());
    } // removeActivity()

    /**
     * finishAllExcept(): 除一个特定活动外，结束堆栈中其余所有活动。
     * 结束活动时会触发 BaseActivity 类的 onDestroy()方法，
     * 堆栈中的活动对象会同步移除。
     *
     * @param cls 要保留的活动的类名（xxxActivity.class）
     */
    public void finishAllExcept(Class<?> cls) {
        int i, len;
        BaseActivity[] activities;

        // 结束活动时会调用活动的 onDestroy() 方法，堆栈的内容会实时改变
        // 为避免因此引起的引用错误，先将堆栈的内容复制到一个临时数组里
        activities = mStack.toArray(new BaseActivity[0]);
        len = activities.length;
        for (i = 0; i < len; ++i) {
            if (activities[i].getClass() != cls) {
                // 从数组里引用活动对象并结束，堆栈内容的改变不影响数组
                activities[i].finish();
            } // if (activities[i].getClass() != cls)
        } // for (i = 0; i < len; ++i)
    } // finishAllExcept()

    /**
     * finishAllActivities(): 结束堆栈中的所有活动。
     * 结束活动时会触发 BaseActivity 类的 onDestroy()方法，
     * 堆栈中的活动对象会同步移除。
     */
    public void finishAllActivities() {
        int i, len;
        BaseActivity[] activities;

        // 结束活动时会调用活动的 onDestroy() 方法，堆栈的内容会实时改变
        // 为避免因此引起的引用错误，先将堆栈的内容复制到一个临时数组里
        activities = mStack.toArray(new BaseActivity[0]);
        len = activities.length;
        for (i = 0; i < len; ++i) {
            // 从数组里引用活动对象并结束，堆栈内容的改变不影响数组
            activities[i].finish();
        } // for (i = 0; i < len; ++i)
    } // finishAllActivities()
} // AppManager Class

// E.O.F
