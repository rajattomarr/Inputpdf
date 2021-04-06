package com.example.android.inputpdf;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText edttxt;
    Button genpdf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        genpdf = (Button) findViewById(R.id.genpdf);
        edttxt = findViewById(R.id.edttxt);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);
               createMyPDF();

    }


            private void createMyPDF() {
                edttxt.setOnClickListener(new View.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onClick(View v) {
                        PdfDocument myPdfDocument = null;
                        {
                            myPdfDocument = new PdfDocument();
                        }
                        PdfDocument.PageInfo myPageInfo = new PdfDocument.PageInfo.Builder(300, 600, 1).create();
                        PdfDocument.Page myPage = myPdfDocument.startPage(myPageInfo);

                        Paint myPaint = new Paint();
                        String myString = edttxt.getText().toString();
                        int x = 10, y = 25;

                        for (String line : myString.split("\n")) {
                            myPage.getCanvas().drawText(line, x, y, myPaint);
                            y += myPaint.descent() - myPaint.ascent();
                        }

                        myPdfDocument.finishPage(myPage);

                        String myFilePath = Environment.getExternalStorageDirectory().getPath() + "/HELLO.pdf";
                        File myFile = new File(myFilePath);
                        try {
                            myPdfDocument.writeTo(new FileOutputStream(myFile));
                        } catch (Exception e) {
                            e.printStackTrace();
                            edttxt.setText("ERROR");
                            Log.e("ERROR", "ERROR GENERATED");
                        }

                        myPdfDocument.close();

                    }



                });
        }
        }














