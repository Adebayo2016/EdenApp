package com.eden;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

     /*   int[] drawables = {R.drawable.highbiscus,R.drawable.img2,R.drawable.img3,R.drawable.img4,R.drawable.img5,R.drawable.img6};

        LinearLayout layout = (LinearLayout) findViewById(R.id.lay1);
        for (int i = 0; i < 6; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setId(i);
            imageView.setMaxWidth(20);
            imageView.setMinimumWidth(20);

            imageView.setPadding(20, 10, 10, 10);
            imageView.setImageBitmap(BitmapFactory.decodeResource(
                    getResources(),
                    drawables[i]));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            layout.addView(imageView);
        }

      */

    }

    public void best_tree(View view) {
        Intent i= new Intent (this, sign_up.class);
        startActivity(i);


    }
}