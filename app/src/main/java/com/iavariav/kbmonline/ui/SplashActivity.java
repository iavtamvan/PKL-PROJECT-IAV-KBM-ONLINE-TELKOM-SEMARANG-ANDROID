package com.iavariav.kbmonline.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.iavariav.kbmonline.R;
import com.iavariav.kbmonline.helper.Config;
import com.iavariav.kbmonline.ui.atasan.activity.AtasanActivity;
import com.iavariav.kbmonline.ui.login.LoginActivity;
import com.iavariav.kbmonline.ui.user.UserActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
                String username = sp.getString(Config.SHARED_PREF_NAMA_LENGKAP, "");
                String rule = sp.getString(Config.SHARED_PREF_RULE, "");

                // TODO jika belum masuk ke LoginActivity
                if (username.equalsIgnoreCase("") || TextUtils.isEmpty(username)){
                    finishAffinity();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                // TODO jika sudah nantinya akan masuk ke Home
                else {
                    if (rule.contains("pimpinan")){
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), AtasanActivity.class));
                    } else if (rule.contains("user")){
//                        Toast.makeText(SplashActivity.this, "rule : " + rule, Toast.LENGTH_SHORT).show();
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), UserActivity.class));
                    }

                }
            }
        }, 2000);
    }
}
