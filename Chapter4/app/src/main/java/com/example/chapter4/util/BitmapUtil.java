package com.example.chapter4.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.util.Log;

import java.io.InputStream;

public class BitmapUtil {
    final static String TAG = "BitmapUtil";

    // 获得旋转角度之后的位图对象
    public static Bitmap getRotateBitmap(Bitmap bitmap, float rotateDegree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(rotateDegree);

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, false);
    }

    // 获得比例缩放后的位图对象
    public static Bitmap getScaleBitmap(Bitmap bitmap, double scaleRatio) {
        int newWidth = (int) (bitmap.getWidth() * scaleRatio);
        int newHeight = (int) (bitmap.getHeight() * scaleRatio);

        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, false);
    }

    // 获得自动缩小后的位图对象
    public static Bitmap getAutoZoomImage(Context ctx, Uri uri) {
        Log.d(TAG, "getAutoZoomImage uri = " + uri.toString());
        Bitmap zoomBitmap = null;

        try {
            InputStream inputStream = ctx.getContentResolver().openInputStream(uri);
            Bitmap originBitmap = BitmapFactory.decodeStream(inputStream);
            int ratio = originBitmap.getWidth() / 2000 + 1;

            zoomBitmap = getScaleBitmap(originBitmap, 1.0 / ratio);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return zoomBitmap;
    }
}
