package com.paidinfull.misgo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.Activity.RESULT_OK;


public class ident_fragment extends Fragment {

EditText nomm, seret, bigo;
Button bubu;
SocHandler lulu;
TextView textView;
ImageView imageView, imageView5;
Button bibi;
int PICK_IMAGE_REQUEST=100;
Uri imageFilePath;
Bitmap imageToStore;
private static int RESULT_LOAD_IMAGE = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = (ConstraintLayout) inflater.inflate(R.layout.fragment_ident_fragment, container, false);


        try {
            imageView5 = (ImageView) root.findViewById(R.id.imageView5);
        } catch (Exception e) {
            Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        bibi = (Button) root.findViewById(R.id.button2);
        nomm = (EditText) root.findViewById(R.id.editTextTextPersonName);
        seret = (EditText) root.findViewById(R.id.editTextTextPersonName2);
        bigo = (EditText) root.findViewById(R.id.editTextTextPersonName3);
        lulu = new SocHandler(getContext());
        imageView = (ImageView) root.findViewById(R.id.imageView);
        textView = (TextView) root.findViewById(R.id.textView);
        String re = String.valueOf(1);
        Cursor res = lulu.getData(re);
        if (res != null && res.moveToFirst()) {
            final String cos = res.getString(1);
            final String sro = res.getString(2);
            final String num = res.getString(3);
            res.close();
            textView.setText("Société:" + cos + "\n" + "n°Siret: " + sro + "\n" + "Telephone pro: " + num);


        }
        bubu = (Button) root.findViewById(R.id.button);
        bubu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addData();
                String re = String.valueOf(1);
                Cursor res = lulu.getData(re);
                if (res != null && res.moveToFirst()) {
                    final String cos = res.getString(1);
                    final String sro = res.getString(2);
                    final String num = res.getString(3);
                    res.close();
                    textView.setText("Société:" + cos + "\n" + "n°Siret: " + sro + "\n" + "Telephone pro: " + num);
                }
            }
        });


        bibi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog al = new AlertDialog.Builder(getContext())
                        .setTitle("mise à jour")
                        .setIcon(R.drawable.delete)
                        .setMessage("modifier la fiche entreprise ?")
                        .setPositiveButton("valider ", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (nomm.getText().toString().length() == 0 || seret.getText().toString().length() == 0 || bigo.getText().toString().length() == 0) {
                                    Toast.makeText(getContext(), "Veuillez remplir avant les champs", Toast.LENGTH_SHORT).show();
                                } else {

                                    SocHandler lulu = new SocHandler(getContext());
                                    String lo = String.valueOf(1);
                                    lulu.udapteData(lo, nomm.getText().toString(), seret.getText().toString(), bigo.getText().toString());
                                    String re = String.valueOf(1);
                                    Cursor res = lulu.getData(re);
                                    if (res != null && res.moveToFirst()) {
                                        final String cos = res.getString(1);
                                        final String sro = res.getString(2);
                                        final String num = res.getString(3);
                                        res.close();
                                        textView.setText("Société:" + cos + "\n" + "n°Siret: " + sro + "\n" + "Telephone pro: " + num);
                                    }
                                    Toast.makeText(getContext(), "Ta fiche a été mise à jour", Toast.LENGTH_SHORT).show();
                                }


                            }
                        }).create();
                al.show();
            }
        });
        imageView5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), InsertImage.class);
                startActivity(i);

            }
        });


         return root;
    }
public void addData(){
        if(nomm.getText().toString().length()==0||seret.getText().toString().length()==0||bigo.getText().toString().length()==0){
            Toast.makeText(getContext(), "donnée(s) MANQUANTE(S) :-(", Toast.LENGTH_LONG);
        } else{
            lulu.insertData(nomm.getText().toString(), seret.getText().toString(), bigo.getText().toString());

            Toast.makeText(getContext(), "Donnée enregistrée :-)", Toast.LENGTH_SHORT).show();
        }

    }
}
