package com.example.communityxchange;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailBusName, detailContact;
    ImageView detailImage;
    //to delete data
    FloatingActionButton deleteButton;
    String key = "";
    String imageUrl = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailBusName = findViewById(R.id.detailBusName);
        detailContact = findViewById(R.id.detailContact);
        //to delete data
        deleteButton = findViewById(R.id.deleteButton);

        Bundle bundle  = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailBusName.setText(bundle.getString("BusinessName"));
            detailContact.setText(bundle.getString("ContactDetails"));

            //to delete data
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }
        //to delete data
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Businesses");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(DetailActivity.this, "Delete Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), upload.class));
                        finish();
                    }
                });
            }
        });
    }
}