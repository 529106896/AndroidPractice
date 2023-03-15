package com.example.chapter3.util;

import android.content.Context;

public class Utils {
    // 根据手机分辨率，从 dp 单位转换为 px 单位
    public static int dip2px(Context context, float dpValue) {
        // 获取当前手机的像素密度，即 dp/dip，一个 dp 对应几个 px
        float scale = context.getResources().getDisplayMetrics().density;
        // 用 dp 值乘上密度，再四舍五入取整即可
        return (int) (dpValue * scale + 0.5f);
    }
}
