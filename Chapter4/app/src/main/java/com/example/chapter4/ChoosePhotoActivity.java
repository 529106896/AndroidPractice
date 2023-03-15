package com.example.chapter4;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chapter4.util.BitmapUtil;

public class ChoosePhotoActivity extends AppCompatActivity {

    ImageView imageView;
    TextView imagePathTextView;
    Button chooseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_photo);

        imageView = findViewById(R.id.imageView);
        imagePathTextView = findViewById(R.id.imagePathTextView);

        ActivityResultLauncher activityResultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(), uri -> {
           if (uri != null) {
               imagePathTextView.setText(uri.toString());
               Bitmap bitmap = BitmapUtil.getAutoZoomImage(this, uri);
               imageView.setImageBitmap(bitmap);
           }
        });

        findViewById(R.id.chooseButton).setOnClickListener(v -> activityResultLauncher.launch("image/*"));
    }
}