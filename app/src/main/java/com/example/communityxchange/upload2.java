package com.example.communityxchange;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.DateFormat;
import java.util.Calendar;

public class upload2 extends AppCompatActivity {

    ImageView uploadImage;
    Button saveButton;
    EditText uploadBusName, uploadDesc, uploadContact, uploadOwner;
    String imageURL;
    Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload2);

        uploadImage = findViewById(R.id.uploadImage);
        saveButton = findViewById(R.id.saveButton);
        uploadBusName = findViewById(R.id.uploadBusinessName);
        uploadDesc = findViewById(R.id.uploadDescription);
        uploadContact = findViewById(R.id.uploadContactDetails);
        uploadOwner = findViewById(R.id.uploadBusinessOwner);

        //some firebase activity??
        ActivityResultLauncher<Intent>activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if(result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            uploadImage.setImageURI(uri);
                        } else
                        {
                            Toast.makeText(upload2.this, "No Image Selected", Toast.LENGTH_SHORT);
                        }
                    }
                }
        );

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pending
                saveData();
            }
        });
    }

    public void saveData(){
        //null check for the image
        if (uri == null) {
            // Handle the case where no image has been selected
            Toast.makeText(this, "No Image Selected", Toast.LENGTH_SHORT).show();
            return;
        }
        StorageReference storageReference = FirebaseStorage.getInstance().getReference()
                .child("Business" +
                        " Images").child(uri.getLastPathSegment());

        AlertDialog.Builder builder = new AlertDialog.Builder(upload2.this);
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        AlertDialog dialog = builder.create();
        dialog.show();

        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
                while (!uriTask.isComplete());
                Uri urlImage = uriTask.getResult();
                imageURL = urlImage.toString();
                //add uploadData() method
                uploadData();
                dialog.dismiss();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                dialog.dismiss();
            }
        });
    }
    public void uploadData(){
        //String title = uploadTopic().getText().toString();
        String name = uploadBusName.getText().toString();
        String desc = uploadDesc.getText().toString();
        String contact = uploadContact.getText().toString();
        String owner = uploadOwner.getText().toString();

        //null checks for the data fields
        /*if (name.isEmpty() || desc.isEmpty() || contact.isEmpty() || owner.isEmpty()) {
            // Handle the case where any of the fields are empty
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }*/

        //for when business name is empty
        if (name.isEmpty()) {
            // Handle the case where any of the fields are empty
            Toast.makeText(this, "Please fill in Business " +
                    "Name", Toast.LENGTH_SHORT).show();
            return;
        }

        //for when business description is empty
        if (desc.isEmpty()) {
            // Handle the case where any of the fields are empty
            Toast.makeText(this, "Please fill in Business Description", Toast.LENGTH_SHORT).show();
            return;
        }

        //for when business owner contact details is empty
        if (contact.isEmpty()) {
            // Handle the case where any of the fields are empty
            Toast.makeText(this, "Please fill in contact details", Toast.LENGTH_SHORT).show();
            return;
        }

        //for when business owner field is empty
        if (owner.isEmpty()) {
            // Handle the case where any of the fields are empty
            Toast.makeText(this, "Please fill the Business Owner", Toast.LENGTH_SHORT).show();
            return;
        }

        DataClass dataClass = new DataClass(name, desc, contact,owner, imageURL);

        //Change the child from name (businessName) to currentDate --> the date will be used to accomodate updates
        String currentDate = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

        //Firebase
        FirebaseDatabase.getInstance().getReference("Businesses").child(currentDate)
                .setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(upload2.this,"Saved",Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(upload2.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}