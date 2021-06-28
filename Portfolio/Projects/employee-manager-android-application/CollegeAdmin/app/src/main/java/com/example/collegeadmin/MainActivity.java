package com.example.collegeadmin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.collegeadmin.faculty.UpdateFaculty;
import com.example.collegeadmin.notice.AddNotice;
import com.example.collegeadmin.notice.DeleteNotice;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CardView upLoadNotice,addGallery,addEbook,faculty,deleteNotice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        upLoadNotice=findViewById(R.id.addNotice);
        upLoadNotice.setOnClickListener(this);
        addEbook=findViewById(R.id.addEbook);
        addEbook.setOnClickListener(this);
        addGallery=findViewById(R.id.addGallery);
        addGallery.setOnClickListener(this);
        faculty=findViewById(R.id.faculty);
        faculty.setOnClickListener(this);
        deleteNotice=findViewById(R.id.deleteNotice);
        deleteNotice.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId())
        {

            case R.id.addNotice:
                 intent=new Intent(MainActivity.this, AddNotice.class);
                startActivity(intent);
                break;
            case R.id.addGallery:
                intent=new Intent(MainActivity.this,UploadImage.class);
                startActivity(intent);
                break;
            case R.id.addEbook:
                intent=new Intent(MainActivity.this,UploadPDF.class);
                startActivity(intent);
                break;
            case R.id.faculty:
                intent=new Intent(MainActivity.this, UpdateFaculty.class);
                startActivity(intent);
                break;
            case R.id.deleteNotice:
                intent=new Intent(MainActivity.this, DeleteNotice.class);
                startActivity(intent);
                break;

        }
    }
}