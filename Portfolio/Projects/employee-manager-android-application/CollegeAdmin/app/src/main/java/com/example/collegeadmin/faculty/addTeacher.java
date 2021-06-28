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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
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

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class addTeacher extends AppCompatActivity {

    EditText addTeacherName, addTeacherEmail, addTeacherPost;
    Spinner addTeacherCategory;
    Button uploadteacherbtn;
    DatabaseReference reference,reference1;
    StorageReference storageReference,storageReference1;
    ImageView addTeacherImage;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    String[] items;
    String downloadUrl,category;
    String name,email,post,image;
    int REQ=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_teacher);
        progressDialog=new ProgressDialog(this);
        addTeacherName = findViewById(R.id.addTeacherName);


        addTeacherEmail = findViewById(R.id.addTeacherEmail);

        addTeacherPost = findViewById(R.id.addTeacherPost);

        addTeacherCategory = findViewById(R.id.addTeacherCategory);
        uploadteacherbtn = findViewById(R.id.uploadteacherbtn);
        addTeacherImage = findViewById(R.id.addTeacherImage);
        reference1= FirebaseDatabase.getInstance().getReference().child("Teachers");
        storageReference1= FirebaseStorage.getInstance().getReference().child("Teachers");
        items=new String[]{"Select Category","Computer Science","Mechanical","Physics","Chemistry"};
        addTeacherCategory.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items));

        addTeacherCategory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=addTeacherCategory.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addTeacherImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        uploadteacherbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap == null) {
                    Toast.makeText(addTeacher.this, "Please Upload an image", Toast.LENGTH_SHORT).show();
                openGallery();
                }
                else if (category.equals("Select Category")) {
                    Toast.makeText(addTeacher.this, "Please Select Image Category", Toast.LENGTH_SHORT).show();
                    addTeacherCategory.requestFocus();
                }
                else if(addTeacherName.getText().toString().isEmpty() )
                {
                    addTeacherName.setError("Empty");
                    addTeacherName.requestFocus();
                }
                else if(addTeacherPost.getText().toString().isEmpty())
                {
                    addTeacherPost.setError("Empty");
                    addTeacherPost.requestFocus();
                }
                else if(addTeacherEmail.getText().toString().isEmpty())
                {
                    addTeacherEmail.setError("Empty");
                    addTeacherEmail.requestFocus();
                }
                else {
                    name=addTeacherName.getText().toString();
                    email=addTeacherEmail.getText().toString();
                    post=addTeacherPost.getText().toString();
                    progressDialog.setMessage("Uploading...");
                    progressDialog.show();
                    uploadImage();

                }
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
        uploadTask.addOnCompleteListener(addTeacher.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                                    upLoadData();
                                }
                            });
                        }
                    });
                }
                else
                {
                    progressDialog.dismiss();
                    Toast.makeText(addTeacher.this,"Oops!! Something went wrong",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void upLoadData() {
        reference=reference1;
        reference=reference.child(category);
       String uniqueKey=reference.push().getKey();
        TeacherData teacherData=new TeacherData(name,email,post,downloadUrl,uniqueKey);
        reference.child(uniqueKey).setValue(teacherData).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                Toast.makeText(addTeacher.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(addTeacher.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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
            addTeacherImage.setImageBitmap(bitmap);
        }
    }
}