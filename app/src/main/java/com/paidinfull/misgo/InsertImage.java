package com.paidinfull.misgo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class InsertImage extends AppCompatActivity {

    int PICK_IMAGE_REQUEST=100;
    Uri imageFilePath;
    Bitmap imageToStore;
    ImageView imageView7;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_image);

        imageView7 = findViewById(R.id.imageView7);
        imageView7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chooseImage();
            }
        });

    }
    public void chooseImage() {
        try {
            Intent imageIntent = new Intent();
            imageIntent.setAction(Intent.ACTION_GET_CONTENT);
            imageIntent.setType("image/*");
            startActivityForResult(imageIntent, PICK_IMAGE_REQUEST);

            Toast.makeText(getApplicationContext(), "select your logo", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

        }
        }
        @Override
        public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);

            try {

                if(requestCode==PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data != null && data.getData()!=null)
                {
                    imageFilePath= data.getData();
                    imageToStore= MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), imageFilePath );


                    imageView7.setImageBitmap(imageToStore);
                    Toast.makeText(getApplicationContext(), "c'est clean", Toast.LENGTH_SHORT).show();


                }else{
                    Toast.makeText(getApplicationContext(),"c'est ouf comme même hein", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }

    }
}