package com.example.EmployeeAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

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
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

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

public class UploadImage extends AppCompatActivity {

    Spinner image_category;
    CardView addGalleryImage;
    Button uploadImageBtn;
    ImageView galleryImageView;
    String category;
    int REQ=1;
    DatabaseReference reference,reference1;
    StorageReference storageReference,storageReference1;
    String downloadUrl;
    Bitmap bitmap;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_image);
        image_category=findViewById(R.id.image_category);
        addGalleryImage=findViewById(R.id.addGalleryImage);
        uploadImageBtn=findViewById(R.id.uploadImageBtn);
        reference1= FirebaseDatabase.getInstance().getReference().child("gallery");
        storageReference1= FirebaseStorage.getInstance().getReference().child("gallery");
        galleryImageView=findViewById(R.id.galleryImageView);
        progressDialog=new ProgressDialog(this);

        String[] items=new String[]{"Select Category","Convocation","Independence Day","Other Events"};
        image_category.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,items));

        image_category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                category=image_category.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        addGalleryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        uploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (bitmap == null)
                    Toast.makeText(UploadImage.this, "Please Upload an image", Toast.LENGTH_SHORT).show();
                else if (category.equals("Select Category")) {
                    Toast.makeText(UploadImage.this, "Please Select Image Category", Toast.LENGTH_SHORT).show();

                }
                else
                {
                    progressDialog.setMessage("Uploading");
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
        uploadTask.addOnCompleteListener(UploadImage.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
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
                                    progressDialog.dismiss();
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
                    Toast.makeText(UploadImage.this,"Oops!! Something went wrong",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void upLoadData() {
        reference=reference1;
        reference=reference.child(category);
        final String uniqueKey=reference.push().getKey();
        reference.child(uniqueKey).setValue(downloadUrl).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                progressDialog.dismiss();
                Toast.makeText(UploadImage.this, "Image Uploaded Successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(UploadImage.this, "Something went wrong", Toast.LENGTH_SHORT).show();
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

            galleryImageView.setImageBitmap(bitmap);
        }
    }
}