package com.example.collegeadmin.faculty;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.collegeadmin.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class UpdateTeacher extends AppCompatActivity {

    ImageView updateTeacherImage;
    EditText updateTeacherName,updateTeacherEmail,updateTeacherPost;
    Button updateTeacherBtn,updateDeleteBtn;
    String name,email,post,image;
    int REQ=1;
    Bitmap bitmap=null;
    StorageReference storageReference,storageReference1;
    String downloadUrl,uniqueKey,category;
    ProgressDialog progressDialog;
    DatabaseReference reference,reference1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_teacher);
        name=getIntent().getStringExtra("name");
        email=getIntent().getStringExtra("email");
        post=getIntent().getStringExtra("post");
        image=getIntent().getStringExtra("image");
         uniqueKey=getIntent().getStringExtra("key");
         category=getIntent().getStringExtra("category");

        updateTeacherImage=findViewById(R.id.updateTeacherImage);
        updateTeacherName=findViewById(R.id.updateTeacherName);
        updateTeacherEmail=findViewById(R.id.updateTeacherEmail);
        updateTeacherPost=findViewById(R.id.updateTeacherPost);
        updateTeacherBtn=findViewById(R.id.updateTeacherBtn);
        updateDeleteBtn=findViewById(R.id.updateDeleteBtn);
        progressDialog=new ProgressDialog(this);

        reference1=FirebaseDatabase.getInstance().getReference().child("Teachers");
        storageReference1= FirebaseStorage.getInstance().getReference().child("Teachers");
        try {
            Picasso.get().load(image).into(updateTeacherImage);
        } catch (Exception e) {
            e.printStackTrace();
        }

        updateTeacherEmail.setText(email);
        updateTeacherName.setText(name);
        updateTeacherPost.setText(post);
        updateTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                openGallery();
            }
        });

        updateTeacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                name=updateTeacherName.getText().toString();
                email=updateTeacherEmail.getText().toString();
                post=updateTeacherPost.getText().toString();
                checkValidation();
            }
        });
        updateDeleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                deleteData();
            }
        });
    }

    private void deleteData() {
        reference=reference1;
        reference.child(category).child(uniqueKey).removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Toast.makeText(UpdateTeacher.this, "Teacher deleted succesfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateTeacher.this,UpdateFaculty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void checkValidation() {
        if(name.isEmpty())
        {
            updateTeacherName.setError("Empty");
            updateTeacherName.requestFocus();
        }
        else if(email.isEmpty())
        {
            updateTeacherEmail.setError("Empty");
            updateTeacherEmail.requestFocus();
        }
        else if(post.isEmpty())
        {
            updateTeacherPost.setError("Empty");
            updateTeacherPost.requestFocus();
        }
        else if(bitmap==null)
        {
            updateData(image);
        }
        else
        {
            uploadImage();
        }



    }

    private void updateData(String s) {
        HashMap hashMap=new HashMap();
        hashMap.put("name",name);
        hashMap.put("email",email);
        hashMap.put("post",post);
        hashMap.put("image",s);
        reference=reference1;
        reference.child(category).child(uniqueKey).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener() {
            @Override
            public void onSuccess(Object o) {
                Toast.makeText(UpdateTeacher.this, "Teacher Updated Successfully", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(UpdateTeacher.this,UpdateFaculty.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);


            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(UpdateTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });


    }
    private void uploadImage() {
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] final_img=baos.toByteArray();
        final StorageReference filePath;
        storageReference=storageReference1;
        filePath=storageReference.child(final_img+".jpg");
        final UploadTask uploadTask=filePath.putBytes(final_img);
        uploadTask.addOnCompleteListener(UpdateTeacher.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if(task.isSuccessful())
                {
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {

                                    downloadUrl = String.valueOf(uri);
                                        updateData(downloadUrl);
                                }
                            });
                        }
                    });
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(UpdateTeacher.this,"Oops!! Something went wrong",Toast.LENGTH_LONG).show();
                }

            }
        });
    }



    private void openGallery() {
        Intent pickImage=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(pickImage,REQ);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK)
        {
            Uri uri=data.getData();
            try {
                bitmap=MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
            updateTeacherImage.setImageBitmap(bitmap);
        }
    }
}