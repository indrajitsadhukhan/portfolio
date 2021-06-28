package com.example.collegeadmin.notice;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.collegeadmin.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class DeleteNotice extends AppCompatActivity {

    RecyclerView deleteNoticeRecycler;
    ProgressBar progressBar;
    ArrayList<NoticeData> list;
    NoticeAdapter noticeAdapter;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_notice);
        deleteNoticeRecycler=findViewById(R.id.deleteNoticeRecycler);
        progressBar=findViewById(R.id.progressBar);
        list=new ArrayList<>();
        reference= FirebaseDatabase.getInstance().getReference().child("Notice");
        deleteNoticeRecycler.setLayoutManager(new LinearLayoutManager(this));
        deleteNoticeRecycler.setHasFixedSize(true);
        getNotice();
    }

    private void getNotice() {
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    NoticeData data=snapshot.getValue(NoticeData.class);
                    list.add(data);
                }
                noticeAdapter=new NoticeAdapter(DeleteNotice.this,list);
                noticeAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                list.clear();
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    NoticeData data=snapshot.getValue(NoticeData.class);
                    list.add(data);
                }
                noticeAdapter=new NoticeAdapter(DeleteNotice.this,list);
                deleteNoticeRecycler.setAdapter(noticeAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(DeleteNotice.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateNotice() {


    }
}