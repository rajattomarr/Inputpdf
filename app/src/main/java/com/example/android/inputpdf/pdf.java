package com.example.android.inputpdf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.github.barteksc.pdfviewer.PDFView;

import java.io.File;

public class pdf extends AppCompatActivity {
    PDFView pdfView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdf);
        pdfView=findViewById(R.id.pdfView);

        String path=getExternalFilesDir(null).toString()+"/HELLO.pdf";
        File file=new File(path);


        pdfView.fromFile(file)
                .swipeHorizontal(false)
                .enableDoubletap(true)
                .enableAnnotationRendering(false)
                .defaultPage(0)
                .password(null)
                .scrollHandle(null)
                .load();








    }
}