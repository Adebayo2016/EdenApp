package com.eden;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.github.barteksc.pdfviewer.PDFView;

public class OrnamentalCropView extends AppCompatActivity {

    PDFView pdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ornamental_tree);


        Intent intent=getIntent();
        String receiving=intent.getStringExtra("send");

        pdf = (PDFView)findViewById(R.id.pdf);
        pdf.fromAsset(receiving).load();

    }

    public void GetTree(View view) {

        Toast.makeText(this, "Coming Soon", Toast.LENGTH_SHORT).show();
    }
}
