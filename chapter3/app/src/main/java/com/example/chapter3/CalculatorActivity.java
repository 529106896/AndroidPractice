package com.example.chapter3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {

    // 用于log打印
    final static String TAG_NAME = "CalculatorActivity";

    // 操作符
    String operator = "";

    // 第一个操作符
    String firstNum = "";

    // 第二个操作数
    String secondNum = "";

    // 显示结果的TextView
    TextView tvResult;

    // 当前显示的文本内容
    String showText = "";

    // 当前的运算的结果
    String curResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        // 用于显示结果
        tvResult = findViewById(R.id.tv_result);

        tvResult.setMovementMethod(ScrollingMovementMethod.getInstance());

        // 为所有按钮绑定公共的Listener
        findViewById(R.id.btn_cancel).setOnClickListener(this);
        findViewById(R.id.btn_divide).setOnClickListener(this);
        findViewById(R.id.btn_multiply).setOnClickListener(this);
        findViewById(R.id.btn_clear).setOnClickListener(this);
        findViewById(R.id.btn_seven).setOnClickListener(this);
        findViewById(R.id.btn_eight).setOnClickListener(this);
        findViewById(R.id.btn_nine).setOnClickListener(this);
        findViewById(R.id.btn_four).setOnClickListener(this);
        findViewById(R.id.btn_five).setOnClickListener(this);
        findViewById(R.id.btn_six).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_one).setOnClickListener(this);
        findViewById(R.id.btn_plus).setOnClickListener(this);
        findViewById(R.id.btn_minus).setOnClickListener(this);
        findViewById(R.id.ibtn_sqrt).setOnClickListener(this);
        findViewById(R.id.btn_reciprocal).setOnClickListener(this);
        findViewById(R.id.btn_zero).setOnClickListener(this);
        findViewById(R.id.btn_dot).setOnClickListener(this);
        findViewById(R.id.btn_equal).setOnClickListener(this);
    }

    // 检查输入是否合法
    public boolean verify(View v) {
        // 点击的是撤销按钮
        if (v.getId() == R.id.btn_cancel) {
            // 当前未输入运算符，且第一个操作数也没有进行输入，表明当前没有可进行撤销的数字
            if (operator.equals("") && (firstNum.equals("") || firstNum.equals("0"))) {
                Toast.makeText(this, "Noting to cancel.", Toast.LENGTH_SHORT).show();
                return false;
            }
            // 当前有运算符，且第二个操作数没有进行输入，要撤销是撤销第二个运算数
//            if (!operator.equals("") && secondNum.equals("")) {
//                Toast.makeText(this, "Second operand is null. Noting to cancel.", Toast.LENGTH_SHORT).show();
//                return false;
//            }
        }

        // 点击的是等号
        if (v.getId() == R.id.btn_equal) {
            // 还没输入运算符
            if (operator.equals("")) {
                Toast.makeText(this, "请输入运算符", Toast.LENGTH_SHORT).show();
                return false;
            }
            // 两个操作数没有输入完整
            if (firstNum.equals("") || secondNum.equals("")) {
                Toast.makeText(this, "请确保操作数输入完毕", Toast.LENGTH_SHORT).show();
                return false;
            }
            // 除数为0
            if (operator.equals("÷") && Double.parseDouble(secondNum) == 0) {
                Toast.makeText(this, "除数不能为0", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // 点击的是加减乘除
        if (v.getId() == R.id.btn_plus || v.getId() == R.id.btn_minus || v.getId() == R.id.btn_divide || v.getId() == R.id.btn_multiply) {
            // 还没输入第一个操作数
            if (firstNum.equals("")) {
                Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
                return false;
            }
            // 已有操作符，用户试图输入重复的操作数
            if (!operator.equals("")) {
                Toast.makeText(this, "不允许出现重复的运算符", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // 点击的是开根号
        if (v.getId() == R.id.ibtn_sqrt) {
            // 没有输入底数
            if (firstNum.equals("")) {
                Toast.makeText(this, "请先输入底数", Toast.LENGTH_SHORT).show();
                return false;
            }
            // 底数小于0
            if (Double.parseDouble(firstNum) < 0) {
                Toast.makeText(this, "底数必须是非负数", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // 点击的是求倒数
        if (v.getId() == R.id.btn_reciprocal) {
            // 没有输入底数
            if (firstNum.equals("")) {
                Toast.makeText(this, "请先输入底数", Toast.LENGTH_SHORT).show();
                return false;
            }
            // 底数小于0
            if (Double.parseDouble(firstNum) == 0) {
                Toast.makeText(this, "不能对0求倒数", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        // 点击的是小数点
        if (v.getId() == R.id.btn_dot) {
            if (firstNum.equals("")) {
                Toast.makeText(this, "请先输入数字", Toast.LENGTH_SHORT).show();
                return false;
            }
            // 还没输入运算符，但第一个操作数中有小数点
            if (operator.equals("") && firstNum.contains(".")) {
                Toast.makeText(this, "一个数字不能有两个小数点", Toast.LENGTH_SHORT).show();
                return false;
            }
            // 已输入运算符，但第二个操作数中有小数点
            if (operator.equals("") && secondNum.contains(".")) {
                Toast.makeText(this, "一个数字不能有两个小数点", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        // 先检查当前输入是否合法，不合法直接return
        if (!verify(v)) {
            return;
        }
        // 已输入的字符串
        String inputText;

        // 根号单独处理，其余的直接读取按钮上的文字
        if (v.getId() == R.id.ibtn_sqrt) {
            inputText = "√";
        } else {
            inputText = ((TextView) v).getText().toString();
        }
        Log.d(TAG_NAME, String.format("输入：%s", inputText));

        // 点击的是清除按钮
        if (v.getId() == R.id.btn_clear) {
            clear();
        }
        // 点击的是撤销按钮
        else if (v.getId() == R.id.btn_cancel) {
            // 还没输入运算符，此时对第一个操作数进行逐位消除
            if (operator.equals("")) {
                // 第一个操作数只有一位，直接归零
                if (firstNum.length() == 1) {
                    firstNum = "0";
                // 其余情况逐位消除
                } else {
                    firstNum = firstNum.substring(0, firstNum.length() - 1);
                }
                refreshText(firstNum);
            // 已有操作符，对第二个操作数或操作符进行消除
            } else {
                // 有第二个操作数
                if (secondNum.length() == 1) {
                    secondNum = "";
                } else if (secondNum.length() > 1) {
                    secondNum = secondNum.substring(0, secondNum.length() - 1);
                // 没有第二个操作数，那就对操作符进行消除
                } else {
                    operator = "";
                }
                refreshText(showText.substring(0, showText.length() - 1));
            }
        }
        // 点击的是四则运算
        else if (v.getId() == R.id.btn_plus || v.getId() == R.id.btn_minus || v.getId() == R.id.btn_divide || v.getId() == R.id.btn_multiply) {
            operator = inputText;
            refreshText(showText + " " + operator);
        }
        // 点击的是等号
        else if (v.getId() == R.id.btn_equal) {
            double result = calculateFour();
            refreshOperate(String.valueOf(result));
            refreshText(showText + " = " + result);
        }
        // 点击的是开根号
        else if (v.getId() == R.id.ibtn_sqrt) {
            double result = Math.sqrt(Double.parseDouble(firstNum));
            refreshOperate(String.valueOf(result));
            refreshText("√" + firstNum + " = " + result);
        }
        // 点击的是求倒数
        else if (v.getId() == R.id.btn_reciprocal) {
            double result = 1.0 / Double.parseDouble(firstNum);
            refreshOperate(String.valueOf(result));
            refreshText("1/" + firstNum + " = " + result);
        }
        // 点击其他按钮（数字、小数点等）
        else {
            // 上次有过运算
            if (curResult.length() > 0 && operator.equals("")) {
                clear();
            }
            if (operator.equals("")) {
                firstNum = firstNum + inputText;
            } else {
                secondNum = secondNum + inputText;
            }
            if (showText.equals("0") && !inputText.equals(".")) {
                refreshText(inputText);
            } else {
                refreshText(showText + inputText);
            }
        }

    }

    // 清空
    public void clear() {
        refreshOperate("");
        refreshText("0");
    }

    // 刷新运算结果
    public void refreshOperate(String newResult) {
        curResult = newResult;
        firstNum = curResult;
        secondNum = "";
        operator = "";
    }

    // 刷新显示文本
    public void refreshText(String text) {
        showText = text;
        tvResult.setText(showText);
//        tvResult.append(text);
        int offset = tvResult.getLineCount() * tvResult.getLineHeight();
        if (offset > tvResult.getHeight()) {
            tvResult.scrollTo(0, offset - tvResult.getHeight());
        }
    }

    // 进行加减乘除四则运算
    public double calculateFour() {
        double result = 0;

        try {
            double first = Double.parseDouble(firstNum);
            double second = Double.parseDouble(secondNum);

            // 如果是加法
            if (operator.equals("+")) {
                result = first + second;
                Log.d(TAG_NAME, String.format("Add operation: %f + %f = %f", first, second, result));
            }
            // 如果是减法
            else if (operator.equals("-")) {
                result = first - second;
                Log.d(TAG_NAME, String.format("Subtract operation: %f - %f = %f", first, second, result));
            }
            // 如果是乘法
            else if (operator.equals("×")) {
                result = first * second;
                Log.d(TAG_NAME, String.format("Multiply operation: %f * %f = %f", first, second, result));
            }
            // 如果是除法
            else if (operator.equals("÷")) {
                result = first / second;
                Log.d(TAG_NAME, String.format("Divide operation: %f / %f = %f", first, second, result));
            }
            // 其他情况属于非法输入
            else {
                Log.e(TAG_NAME, "Illegal input occurs in 'calculateFour()'");
                throw new Exception("Illegal input");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }
}