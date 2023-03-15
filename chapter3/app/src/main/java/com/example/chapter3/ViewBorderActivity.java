package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.chapter3.util.Utils;

public class ViewBorderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_border);

        // 获取 id 为 tv_code 的文本视图
        TextView tv_code = findViewById(R.id.tv_code);
        // 获取 tv_code 的布局参数
        ViewGroup.LayoutParams params = tv_code.getLayoutParams();
        // 修改布局参数中的宽度数值，我们想设置为 300dp，但 width 默认是 px 单位，所以我们把 300dp 转换为 px
        params.width = Utils.dip2px(this, 300);
        tv_code.setLayoutParams(params);
        // 使用 getString(R.xx.xx) 获取 xml 中的对应值
        Log.e(this.getClass().getName(), String.valueOf(getColor(R.color.test)));

        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) params;
        Log.d("不同的width", String.format(
                "LayoutParams的width: %d\nMarginLayoutParams的width: %d",
                params.width, marginLayoutParams.width));

        System.out.println(123456);
    }
}