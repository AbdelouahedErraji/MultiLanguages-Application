package com.example.introscreen;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button selectLanguage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        selectLanguage = findViewById(R.id.select_one);
        selectLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });

    }

    public void showDialog() {
        final AlertDialog.Builder mDialog = new AlertDialog.Builder(MainActivity.this);
        ImageView close, selectImg;
        TextView selectText;
        Button arBtn, engBtn, frBtn;
        final View mView = getLayoutInflater().inflate(R.layout.custom_dialog, null);
        close = mView.findViewById(R.id.close_from_dialog);
        arBtn =  mView.findViewById(R.id.arabic_btn);
        engBtn = mView.findViewById(R.id.english_btn);
        frBtn = mView.findViewById(R.id.french_btn);
        mDialog.setView(mView);
        final AlertDialog dialog = mDialog.create();
        dialog.setCanceledOnTouchOutside(false);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        arBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("ar");
                recreate();
                startActivity(new Intent(getApplicationContext(),NewActivity.class));
                finish();
                dialog.dismiss();
            }
        });

        engBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("eng");
                recreate();
                startActivity(new Intent(getApplicationContext(),NewActivity.class));
                finish();
                dialog.dismiss();
            }
        });

        frBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setLocale("fr");
                recreate();
                startActivity(new Intent(getApplicationContext(),NewActivity.class));
                finish();
                dialog.dismiss();
            }
        });

    }

    private void setLocale(String lang) {

        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration configuration = new Configuration();
        configuration.locale = locale;
        getBaseContext().getResources().updateConfiguration(configuration,getBaseContext().getResources().getDisplayMetrics());

        //save data to shared preferences
        SharedPreferences.Editor editor = getSharedPreferences("Settings",MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences pref = getSharedPreferences("Settings",MODE_PRIVATE);
        String language = pref.getString("My_Lang", "");
        setLocale(language);
    }

}