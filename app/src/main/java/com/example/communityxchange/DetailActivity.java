package com.example.communityxchange;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView detailDesc, detailBusName, detailContact;
    ImageView detailImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailBusName = findViewById(R.id.detailBusName);
        detailContact = findViewById(R.id.detailContact);

        Bundle bundle  = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailBusName.setText(bundle.getString("BusinessName"));
            detailContact.setText(bundle.getString("ContactDetails"));

            Glide.with(this).load(bundle.getString("Image")).into(detailImage);
        }

    }
}