package com.example.chitieucanhan.Activity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chitieucanhan.R;
import com.example.chitieucanhan.maps;

public class GioithieuActivity extends AppCompatActivity {
    TextView call;
    ImageView imageView,imgmaps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gioithieu);
        call=findViewById(R.id.call);
        imageView=findViewById(R.id.callss);
        imgmaps = findViewById(R.id.imgmaps);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);
        String a=call.getText().toString();
       imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String aa="tel:"+a;
                Intent intent=new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse(aa));
                startActivity(intent);

            }
        });
       imgmaps.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(GioithieuActivity.this, maps.class);
               startActivity(intent);
           }
       });
    }
}