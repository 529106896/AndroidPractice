package com.example.chapter5;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chapter5.util.RandomUtil;
import com.example.chapter5.util.ViewUtil;

public class LoginMainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, View.OnFocusChangeListener {

    // 选择登录方式的RadioGroup
    private RadioGroup selectLoginMethodRadioGroup;
    // 输入电话号码的EditText
    private EditText inputPhoneNumber;
    // 输入密码的EditText
    private EditText inputPassword;
    // 忘记密码按钮
    private Button forgetPasswordButton;
    // 密码登录的LinearLayout
    private LinearLayout loginByPasswordLinerLayout;
    // 验证码登录的LinearLayout
    private LinearLayout loginByValidCodeLinerLayout;
    // 输入验证码的EditText
    private EditText inputValidCode;
    // 获得验证码按钮
    private Button getValidCodeButton;
    // 是否记住密码的CheckBox
    private CheckBox isRememberPassword;
    // 登录按钮
    private Button loginButton;
    // 检查电话号码的TextView
    private TextView checkPhoneNumber;

    // 正确的密码
    private String correctPassword = "阿米诺斯！一个里拉米！";

    // 修改后的密码
    private String newPassword;

    // 活动结果启动器
    private ActivityResultLauncher launcher;

    // 验证码
    private String validCode = "哎呀米诺，奥利安费！";

    // 是否登录成功
    private boolean isLoginSuccess;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);

        // 绑定组件
        selectLoginMethodRadioGroup = findViewById(R.id.selectLoginMethodRadioGroup);
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber);
        inputPassword = findViewById(R.id.inputPassword);
        forgetPasswordButton = findViewById(R.id.forgetPasswordButton);
        loginByPasswordLinerLayout = findViewById(R.id.loginByPasswordLinerLayout);
        loginByValidCodeLinerLayout = findViewById(R.id.loginByValidCodeLinerLayout);
        inputValidCode = findViewById(R.id.inputValidCode);
        getValidCodeButton = findViewById(R.id.getValidCodeButton);
        isRememberPassword = findViewById(R.id.isRememberPassword);
        loginButton = findViewById(R.id.loginButton);
        checkPhoneNumber = findViewById(R.id.checkPhoneNumber);

        // 为按钮绑定Click监听器
        forgetPasswordButton.setOnClickListener(this);
        getValidCodeButton.setOnClickListener(this);
        loginButton.setOnClickListener(this);

        // 为登录方式RadioGroup绑定监听器
        selectLoginMethodRadioGroup.setOnCheckedChangeListener(this);

        // 监控EditText的文本长度，达到最大长度之后隐藏输入法
        inputPhoneNumber.addTextChangedListener(new HideTextWatcher(inputPhoneNumber));
        inputPassword.addTextChangedListener(new HideTextWatcher(inputPassword));
        inputValidCode.addTextChangedListener(new HideTextWatcher(inputValidCode));

        // 设置登录是否成功
        isLoginSuccess = false;

        // 接收修改后的新密码
        launcher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
           if (result.getResultCode() == RESULT_OK && result.getData() != null) {
               Intent resultIntent = result.getData();
               newPassword = resultIntent.getStringExtra("newPassword");
               correctPassword = newPassword;
           }
        });
    }

    @Override
    public void onClick(View v) {
        // 存在出错信息，不能提交
        if (checkPhoneNumber.getVisibility() == View.VISIBLE) {
            Toast.makeText(this, "请先修正信息！", Toast.LENGTH_SHORT).show();
            return;
        }
        String phoneNumber = inputPhoneNumber.getText().toString();

        // 没输入电话号码的情况下什么都不能干
        if (phoneNumber.isBlank()) {
            Toast.makeText(this, "电话号码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        }

        // 点击了忘记密码按钮，就把电话号码作为信息传送过去
        if (v.getId() == R.id.forgetPasswordButton) {
            Intent intent = new Intent(this, LoginForgetActivity.class);
            intent.putExtra("phoneNumber", phoneNumber);
            launcher.launch(intent);
//            startActivity(intent);
        }

        // 点击获取验证码
        if (v.getId() == R.id.getValidCodeButton) {
            // 用对话框来展示验证码
            AlertDialog.Builder builder = new AlertDialog.Builder(LoginMainActivity.this);
            builder.setTitle("请记住验证码：");
            validCode = RandomUtil.getRandomNumberString(6);
            String msg = String.format("手机号%s，本次的验证码是%s，请输入验证码", phoneNumber, validCode);
            builder.setMessage(msg);
            builder.setPositiveButton("好的", null);
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

        // 点击登录按钮
        if (v.getId() == R.id.loginButton) {
            // 选择的是密码登录
            if (selectLoginMethodRadioGroup.getCheckedRadioButtonId() == R.id.loginByPasswordButton) {
                String curInputPassword = inputPassword.getText().toString();
                if (!curInputPassword.equals(correctPassword)) {
                    Toast.makeText(this, "密码错误！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    isLoginSuccess = true;
                }
            } else if (selectLoginMethodRadioGroup.getCheckedRadioButtonId() == R.id.loginByValidButton) {
                String curInputValidCode = inputValidCode.getText().toString();
                if (!curInputValidCode.equals(validCode)) {
                    Toast.makeText(this, "验证码错误！", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    isLoginSuccess = true;
                }
            }
            if (isLoginSuccess == true) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("登录成功！");
                builder.setMessage(String.format("手机号%s登录成功！", phoneNumber));
                builder.setPositiveButton("好的", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(LoginMainActivity.this, LoginSuccessActivity.class);
                        intent.putExtra("phoneNumber", phoneNumber);
                        // 登录成功后不允许再返回登录页面
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });

                AlertDialog alertDialog = builder.create();
                builder.show();
            }
        }
    }

    // 监听登录方式RadioGroup，选择了一种登录方式之后，把另一种隐藏
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.loginByPasswordButton) {
            loginByPasswordLinerLayout.setVisibility(View.VISIBLE);
            loginByValidCodeLinerLayout.setVisibility(View.GONE);
            isRememberPassword.setVisibility(View.VISIBLE);
        } else if (checkedId == R.id.loginByValidButton) {
            loginByPasswordLinerLayout.setVisibility(View.GONE);
            loginByValidCodeLinerLayout.setVisibility(View.VISIBLE);
            isRememberPassword.setVisibility(View.GONE);
        }
    }

    // 监听电话号码输入框的焦点变化
    @Override
    public void onFocusChange(View v, boolean hasFocus) {

    }

    // 当返回登录页面时，自动清空密码输入框
    @Override
    protected void onRestart() {
        super.onRestart();
        inputPassword.setText("");
    }

    // 监控输入框，到达最大长度之后隐藏输入框
    private class HideTextWatcher implements TextWatcher {
        // 监控的输入框
        private final EditText editText;
        // 输入框的最长长度
        private final int maxLength;

        // 构造方法
        public HideTextWatcher(EditText editText) {
            this.editText = editText;
            this.maxLength = ViewUtil.getMaxLength(editText);
        }

        public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
        public void onTextChanged(CharSequence s, int start, int before, int count) {}

        // 监控文本变化
        public void afterTextChanged(Editable s) {
            String str = s.toString();

            // 如果指定输入框的文本达到指定长度
            if (str.length() >= maxLength) {
                ViewUtil.hideOneInputMethod(LoginMainActivity.this, editText);
            }

            // 检查电话号码位数是否合法
            if (editText.getId() == R.id.inputPhoneNumber) {
                if (str.length() < maxLength) {
                    checkPhoneNumber.setVisibility(View.VISIBLE);
                    checkPhoneNumber.setText("电话号码位数不足11位！");
                } else {
                    checkPhoneNumber.setVisibility(View.GONE);
                }
            }
        }
    }
}