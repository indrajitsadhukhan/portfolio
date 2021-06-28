package com.example.EmployeeAdmin;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

import java.io.File;

public class UploadPDF extends AppCompatActivity {

    CardView addPDF;
    int REQ=1;
    EditText PDFTitle;
    Button uploadPDFbtn;
    DatabaseReference databaseReference,databaseReference1;
    StorageReference storageReference;
    String downloadUrl;
    ProgressDialog progressDialog;
    Uri PDFData;
    TextView pdfTextView;
    String pdfName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_p_d_f);
        addPDF=findViewById(R.id.addPDF);
        PDFTitle=findViewById(R.id.PDFTitle);
        uploadPDFbtn=findViewById(R.id.uploadPDFbtn);
        databaseReference1= FirebaseDatabase.getInstance().getReference();
        storageReference= FirebaseStorage.getInstance().getReference();
        progressDialog=new ProgressDialog(this);
        addPDF.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        pdfTextView=findViewById(R.id.pdfTextView);
        uploadPDFbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(PDFTitle.getText().toString().isEmpty())
                {
                    PDFTitle.setError("Blank Field");
                    PDFTitle.requestFocus();
                }
                else if(PDFData==null)
                {
                    Toast.makeText(UploadPDF.this, "Please upload a file", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    upLoadPDF();
                }
            }
        });
    }

    private void upLoadPDF() {
        progressDialog.setTitle("Please Wait");
        progressDialog.setMessage("Uploading...");
        progressDialog.show();
        StorageReference reference=storageReference.child("pdf/"+pdfName+"-"+System.currentTimeMillis()+".pdf");
        reference.putFile(PDFData).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> uriTask=taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete())
                {

                }
                Uri uri =uriTask.getResult();
                uploadData(String.valueOf(uri));
            }
        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();
                Toast.makeText(UploadPDF.this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uploadData(String downloadUrl) {
        System.out.println("OK");

        databaseReference=databaseReference1;
       final String uniqueKey=databaseReference.child("pdf").push().getKey();


       EbookData data=new EbookData(PDFTitle.getText().toString(),downloadUrl);

//
//        HashMap data=new HashMap();
//        data.put("pdfTitle",PDFTitle.getText().toString());
//        data.put("pdfUrl",downloadUrl);


        databaseReference.child("pdf").child(uniqueKey).setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                progressDialog.dismiss();
                Toast.makeText(UploadPDF.this, "PDF Uploaded successfully", Toast.LENGTH_SHORT).show();
                PDFTitle.setText("");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                progressDialog.dismiss();

                Toast.makeText(UploadPDF.this, "PDF Failed to upload", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void openGallery() {
        Intent intent=new Intent();
//        intent.setType("image/*|application/pdf");
        String[] mimeTypes ={"image/*","application/pdf","application/msword","application/vnd.ms-powerpoint","application/vnd.ms-excel","text/plain"};
        intent.setType(mimeTypes.length == 1 ? mimeTypes[0] : "*/*");
        if (mimeTypes.length > 0) {
            intent.putExtra(Intent.EXTRA_MIME_TYPES, mimeTypes);
        }

        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select PDF File"),REQ);

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==REQ && resultCode==RESULT_OK)
        {
            PDFData=data.getData();
            if(PDFData.toString().startsWith("content://"))
            {
                Cursor cursor=null;
                try {
                    cursor=UploadPDF.this.getContentResolver().query(PDFData,null,null,null,null);
                    if(cursor!=null && cursor.moveToFirst())
                    {
                        pdfName=cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            else if(PDFData.toString().startsWith("file://"))
            {
                pdfName=new File(PDFData.toString()).getName();
            }
            pdfTextView.setText(pdfName);
        }
    }
}