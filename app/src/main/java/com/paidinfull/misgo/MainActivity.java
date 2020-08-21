package com.paidinfull.misgo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView btv;
    FactureFragment fragmenta;
    ident_fragment fragment_i;

    Animation animCrossFadeOut;
    int PICK_IMAGE_REQUEST=100;
    Uri imageFilePath;
    Bitmap imageToStore;
    ImageView imageView, imageView5;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);

        btv = findViewById(R.id.btv);
        fragmenta = new FactureFragment();
        fragment_i = new ident_fragment();

        loadFragment(fragment_i);
        imageView= findViewById(R.id.imageView);


        btv.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {



                switch (item.getItemId()){
                    case R.id.profil:
                        loadFragment(fragment_i);
                        break;

                    case R.id.facture:
                        loadFragment(fragmenta);
                        break;

                    case R.id.calculette:
                        Toast.makeText(MainActivity.this, "Bientôt dispo inshAllah", Toast.LENGTH_LONG).show();
                        break;

                    case R.id.clients:
                        Toast.makeText(MainActivity.this, "Bientôt dispo inshAllah", Toast.LENGTH_LONG).show();
                        break;

                }
                return true;
            }
        });


        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                PackageManager.PERMISSION_GRANTED);


    }
    private void loadFragment(Fragment fragment) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.framelala, fragment);
        transaction.commit();

  }}