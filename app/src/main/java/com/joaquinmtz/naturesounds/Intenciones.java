package com.joaquinmtz.naturesounds;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

public class Intenciones extends AppCompatActivity {

    private Button btnNT, btnFb, btnInsta, btnChrome, btnMail;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intenciones);

        btnNT = findViewById(R.id.btnNatureSounds);
        btnFb = findViewById(R.id.btnFacebook);
        btnInsta = findViewById(R.id.btnInstagram);
        btnChrome = findViewById(R.id.btnChrome);
        btnMail = findViewById(R.id.btnMail);
    }
}
