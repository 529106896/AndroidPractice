package com.example.chapter5.util;

import java.util.Random;

public class RandomUtil {
    // 获得n位数字字符串
    public static String getRandomNumberString(int n) {
        Random random = new Random();

        StringBuilder sb = new StringBuilder();
        while (sb.length() != n) {
            sb.append(String.valueOf(random.nextInt(9)));
        }

        return sb.toString();
    }
}
