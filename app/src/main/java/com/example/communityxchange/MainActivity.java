package com.example.communityxchange;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast; //for toast messages

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //create realtime database instance
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //write message to database
        DatabaseReference myRef = database.getReference("message");
        myRef.setValue("This is a database testing").addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(MainActivity.this, "Message to Database written successfully.", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(MainActivity.this, "Error:Message to Database NOT written.", Toast.LENGTH_SHORT).show();
            }
        });
        //toast message


        //add data on database: create a map for data

        //open pop-up when clicking about
        ImageView arrow_imageview = (ImageView) findViewById(R.id.imageView_arrow);
        arrow_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent to open new activity
                Intent test_intent = new Intent(MainActivity.this, upload.class);
                startActivity(test_intent);
            }
        });

        //pop-out about message
        ImageView about_imageview = (ImageView) findViewById(R.id.imageView_about);
        about_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

        //button --> open test activity
        Button test_button = findViewById(R.id.test_button);
        test_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //create intent to open new activity
                Intent test_intent = new Intent(MainActivity.this, Test.class);
                startActivity(test_intent);
            }
        });
    }//end of onCreate

    //method for the dialog
    private void showDialog(){
        Dialog dialog = new Dialog(this, R.style.DialogStyle    );
        dialog.setContentView(R.layout.about_dialog);

        dialog.getWindow().setBackgroundDrawableResource(R.drawable.dialog_background);

        Button okBtn = dialog.findViewById(R.id.button_ok);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}//end of Class
