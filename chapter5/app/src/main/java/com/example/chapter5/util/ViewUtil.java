package com.example.chapter5.util;

import android.app.Activity;
import android.content.Context;
import android.text.InputFilter;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ViewUtil {
    public static int getMaxLength(EditText editText) {
        int maxLength = 0;
        try {
            // 先获取EditText的所有InputFilter
            InputFilter[] inputFilters = editText.getFilters();
            // 遍历所有InputFilter，找到长度相关的Filter
            for (InputFilter filter : inputFilters) {
                if (filter instanceof InputFilter.LengthFilter) {
                    // 找到之后获取最大长度属性
                    Field maxLengthField = filter.getClass().getDeclaredField("mMax");
                    // 破坏属性封装性
                    maxLengthField.setAccessible(true);
                    // 访问对应属性
                    maxLength = (int) maxLengthField.get(filter);

//                    Method getMax = filter.getClass().getDeclaredMethod("getMax", null);
//                    getMax.setAccessible(true);
//                    maxLength = (int) getMax.invoke(filter);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maxLength;
    }

    public static void hideOneInputMethod(Activity act, View v) {
        // 从系统服务中获取输入法管理器
        InputMethodManager inputMethodManager = (InputMethodManager) act.getSystemService(Context.INPUT_METHOD_SERVICE);
        // 关闭屏幕上的输入法软键盘
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
