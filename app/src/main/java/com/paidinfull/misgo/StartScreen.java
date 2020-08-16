package com.paidinfull.misgo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;

public class StartScreen extends AppCompatActivity {

    TextView nasseeha;
    protected boolean actif = true;
    protected int splashTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);

        nasseeha = findViewById(R.id.textView2);
        nasseeha.setText("« Dieu a prescrit l’Excellence en toute chose... » rapporté authentiquement par Muslim.");
        nasseeha.setTextColor(Color.WHITE);
        nasseeha.setBackgroundColor(Color.BLACK);
        nasseeha.setGravity(Gravity.CENTER);

        Thread splashThread = new Thread(){
            @Override
            public void run() {
                try {
                    int attente = 0;
                    while (actif && (attente < splashTime)){
                        sleep(100);
                        if(actif){
                            attente +=100;
                        }
                    }
                }catch (Exception e){
                    Toast.makeText(StartScreen.this, "erreur", Toast.LENGTH_SHORT).show();
                }
                finally {
                    startActivity(new Intent(StartScreen.this, MainActivity.class));
                }
            }

        };
        splashThread.start();

    }
}