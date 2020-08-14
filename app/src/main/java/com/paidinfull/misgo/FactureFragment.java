package com.paidinfull.misgo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Float.*;


public class FactureFragment extends Fragment {

    private static EditText soc, siret, tel, clicli, adres, date, tva, desig, qté, prix;
    private static Bitmap btm, scalebtm;
    private static int pagewidth = 1200;
    private static Date dateObject;
    private static DateFormat dateFormat;
    private static Button valider;
    private static Button buton;
    private static SocHandler lulu;
    private static ImageView imageView2;

    private MainActivity MainActivity;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root =(ConstraintLayout) inflater.inflate(R.layout.fragment_facture, container, false);


        lulu = new SocHandler(getContext());

        imageView2 = (ImageView) root.findViewById(R.id.imageView2);
        soc = (EditText) root.findViewById(R.id.editTextTextPersonName);
        siret = (EditText) root.findViewById(R.id.editTextTextPersonName2);
        tel = (EditText) root.findViewById(R.id.editTextTextPersonName3);
        clicli = (EditText) root.findViewById(R.id.editTextTextPersonName4);
        adres = (EditText) root.findViewById(R.id.editTextTextPersonName5);
        tva = (EditText) root.findViewById(R.id.editTextTextPersonName7);
        desig = (EditText) root.findViewById(R.id.editTextTextPersonName6);
        qté = (EditText) root.findViewById(R.id.editTextNumber);
        prix = (EditText) root.findViewById(R.id.editTextTextPersonName8);

        String re= String.valueOf(1);
        Cursor res = lulu.getData(re);
        if (res != null && res.moveToFirst()) {
            final String cos = res.getString(1);
            final String sro = res.getString(2);
            final String num = res.getString(3);
            res.close();


            //  aSwitch = (findViewById(R.id.switch1));


            //                      ******** TELECHARGER LE DOCUMENT  *******
            valider = (Button) root.findViewById(R.id.valider);
            valider.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                @Override
                public void onClick(View v) {

                /*final String nomsoc = soc.getText().toString();
                final String nsirte = siret.getText().toString();
                final String ntel = tel.getText().toString();
                final float taxe = parseFloat(tva.getText().toString()+"");*/
                    final String client = clicli.getText().toString();
                    final String adresse = adres.getText().toString();
                    final String objet = desig.getText().toString();
                    final double nombre = Double.parseDouble(tva.getText().toString());
                    final double tarif = Double.parseDouble(prix.getText().toString());

                    dateObject = new Date();

                    if (clicli.getText().toString().length() == 0 || tva.getText().toString().length() == 0 || adres.getText().toString().length() == 0 || desig.getText().toString().length() == 0 || qté.getText().toString().length() == 0 ||
                            prix.getText().toString().length() == 0) {
                        Toast.makeText(getActivity(), "renseignement manquant", Toast.LENGTH_LONG).show();
                    } else if (clicli.getText().toString().length() != 0 || adres.getText().toString().length() != 0 ||
                            tva.getText().toString().length() != 0 || desig.getText().toString().length() != 0 || qté.getText().toString().length() != 0 ||
                            prix.getText().toString().length() != 0) {
                        Toast.makeText(getActivity(), "Facture téléchargé", Toast.LENGTH_LONG).show();

                        //           **********  CREATION DU DOCUMENT  *************

                        PdfDocument doc = new PdfDocument();
                        final Paint paint = new Paint();
                        final Paint titlePaint = new Paint();
                        PdfDocument.PageInfo pageInfo = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
                        PdfDocument.Page page = doc.startPage(pageInfo);
                        final Canvas canvas = page.getCanvas();


                        //**********************TAILLE TEXTE ENCADRE *****************************
                        paint.setTextSize(30f);
                        //*********************** ENCADRER ****************************************
                        paint.setStyle(Paint.Style.STROKE);
                        paint.setStrokeWidth(2);
                        canvas.drawRect(20, 780, 1180, 860, paint);
                        paint.setTextAlign(Paint.Align.LEFT);
                        paint.setStyle(Paint.Style.FILL);
                        canvas.drawText("N°", 40, 830, paint);
                        canvas.drawText("désignation", 200, 830, paint);
                        canvas.drawText("Qté", 700, 830, paint);
                        canvas.drawText("Prix", 900, 830, paint);
                        canvas.drawText("Total", 1050, 830, paint);

                        //*************************** DATE **************************************
                        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                        canvas.drawText("Date: " + dateFormat.format(dateObject), 880, 640, paint);

                        //************************* Input user ********************************
                        canvas.drawText("Facturé à: " + client, 40, 640, paint);
                        canvas.drawText(adresse, 40, 670, paint);
                        canvas.drawText(cos, 840, 40, paint);
                        canvas.drawText("n°siret: " + sro, 840, 70, paint);
                        canvas.drawText("contact: " + num, 840, 100, paint);

                        //************************* Line encadré *************************************
                        canvas.drawLine(180, 790, 180, 840, paint);
                        canvas.drawLine(680, 790, 680, 840, paint);
                        canvas.drawLine(880, 790, 880, 840, paint);
                        canvas.drawLine(1030, 790, 1030, 840, paint);

                        //********************** TITRE***********************************************
                        titlePaint.setTextAlign(Paint.Align.CENTER);
                        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
                        titlePaint.setTextSize(70);
                        canvas.drawText("FACTURE", 1200 / 2, 270, titlePaint);
                        //********************** Commande ************************************************


                        final double total1 = nombre * tarif;
                        canvas.drawText(" 1.", 40, 950, paint);
                        canvas.drawText(objet, 200, 950, paint);
                        canvas.drawText(String.valueOf(nombre), 700, 950, paint);
                        canvas.drawText(tarif + " €", 900, 950, paint);

                        canvas.drawText(String.valueOf(total1), 1050, 950, paint);

                        paint.setColor(Color.BLACK);


                        //********* SORTIE DU DOCUMENT *******************
                        doc.finishPage(page);

                        File file = new File(Environment.getExternalStorageDirectory(),client + ".pdf");

                        try {
                            doc.writeTo(new FileOutputStream(file));

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        doc.close();



                                }

                }
            });

//return root;
        }
return root;
}}