package com.example.chapter5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter5.util.RandomUtil;
import com.example.chapter5.util.ViewUtil;


public class LoginForgetActivity extends AppCompatActivity implements View.OnClickListener, View.OnFocusChangeListener {

    // 输入新密码的EditText
    private EditText inputNewPassword;
    // 再次输入新密码的EditText
    private EditText affirmNewPassword;
    // 输入验证码的EditText
    private EditText inputValidCode;
    // 获得验证码的按钮
    private Button getValidCodeButton;
    // 确定按钮
    private Button affirmButton;
    // 检查新密码是否合法
    private TextView checkPasswordIsLegal;
    // 检查两次密码是否一致的TextView
    private TextView checkPasswordIsIdentical;

    // 电话号码
    private String phoneNumber;
    // 获取的验证码
    private String validCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_forget);

        // 绑定组件
        inputNewPassword = findViewById(R.id.inputNewPassword);
        affirmNewPassword = findViewById(R.id.affirmNewPassword);
        inputValidCode = findViewById(R.id.inputValidCode);
        getValidCodeButton = findViewById(R.id.getValidCodeButton);
        affirmButton = findViewById(R.id.affirmButton);
        checkPasswordIsLegal = findViewById(R.id.checkPasswordIsLegal);
        checkPasswordIsIdentical = findViewById(R.id.checkPasswordIsIdentical);

        // 为Button添加点击监听器
        getValidCodeButton.setOnClickListener(this);
        affirmButton.setOnClickListener(this);

        // 为EditText绑定长度监听器
        inputNewPassword.addTextChangedListener(new HideTextWatcher(inputNewPassword));
        affirmNewPassword.addTextChangedListener(new HideTextWatcher(affirmNewPassword));
        inputValidCode.addTextChangedListener(new HideTextWatcher(inputValidCode));

        // 为EditText绑定焦点变化监听器
        inputNewPassword.setOnFocusChangeListener(this);
        affirmNewPassword.setOnFocusChangeListener(this);
        inputValidCode.setOnFocusChangeListener(this);

        // 从主页面接收来的电话号码
        phoneNumber = getIntent().getStringExtra("phoneNumber");
//        Log.d("phoneNumber: ", phoneNumber);
    }

    // 监控焦点变化
    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    // 监控按钮点击
    @Override
    public void onClick(View v) {
//        Log.d("可见性：", String.valueOf(checkPasswordIsLegal.getVisibility()) + String.valueOf(checkPasswordIsIdentical.getVisibility()));
        // 存在错误信息，不能提交
        if (checkPasswordIsLegal.getVisibility() == View.VISIBLE || checkPasswordIsIdentical.getVisibility() == View.VISIBLE) {
            Toast.makeText(this, "请先修正错误信息！", Toast.LENGTH_SHORT).show();
            return;
        }
        // 按下获得验证码按钮
        if (v.getId() == R.id.getValidCodeButton) {
            // 用对话框来展示验证码
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginForgetActivity.this);
            builder.setTitle("请记住验证码：");
            validCode = RandomUtil.getRandomNumberString(6);
            String msg = String.format("手机号%s，本次的验证码是%s，请输入验证码", phoneNumber, validCode);
            builder.setMessage(msg);
            builder.setPositiveButton("好的", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }
        // 提交时，检查验证码输入
        if (v.getId() == R.id.affirmButton) {
            String inputValid = inputValidCode.getText().toString();
            if (!inputValid.equals(validCode)) {
                Toast.makeText(this, "验证码错误！", Toast.LENGTH_SHORT).show();
                return;
            } else {
                Toast.makeText(this, "密码修改成功！", Toast.LENGTH_SHORT).show();
                String newPassword = inputNewPassword.getText().toString();
                // 若修改成功，把新密码返回给上一页面
                Intent intent = new Intent();
                intent.putExtra("newPassword", newPassword);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
    }

    // 输入框长度监听，到达最大长度之后隐藏输入法，以及监控两次密码输入是否一致
    private class HideTextWatcher implements TextWatcher {
        // 监控的输入框
        private final EditText editText;
        // 监控的输入框的最大长度
        private final int maxLength;

        // 构造方法
        public HideTextWatcher(EditText editText) {
            this.editText = editText;
            this.maxLength = ViewUtil.getMaxLength(editText);
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        // 监控输入后的文本变化
        public void afterTextChanged(Editable s) {
            // 输入框内文字达到最大长度后自动隐藏输入法
            String str = s.toString();
            if (str.length() >= maxLength) {
                ViewUtil.hideOneInputMethod(LoginForgetActivity.this, editText);
            }

            // 密码长度必须是6位
            if (editText.getId() == R.id.inputNewPassword) {
                if (str.length() != maxLength) {
                    checkPasswordIsLegal.setText("密码长度不足8位！");
                    checkPasswordIsLegal.setVisibility(View.VISIBLE);
                } else {
                    checkPasswordIsLegal.setVisibility(View.GONE);
                }
            }

            // 两次密码必须一致
            if (editText.getId() == R.id.affirmNewPassword) {
                String newPassword = inputNewPassword.getText().toString();
                if (!str.equals(newPassword)) {
                    checkPasswordIsIdentical.setText("两次密码输入不一致！");
                    checkPasswordIsIdentical.setVisibility(View.VISIBLE);
                } else {
                    checkPasswordIsIdentical.setVisibility(View.GONE);
                }
            }
        }
    }
}