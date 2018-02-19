package com.dokgo.junkiproj.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dokgo.junkiproj.R;

/**
 * Created by JawsGeun on 2018-01-30.
 */

public class SettingActivity extends AppCompatActivity{
    Button button;
    private SharedPreferences sh;
    private SharedPreferences.Editor ed;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        button = (Button)findViewById(R.id.logout);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                sh = getSharedPreferences("자동로그인",MODE_PRIVATE);
                ed = sh.edit();
                ed.putString("사용","N");
                ed.commit();
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"로그아웃",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(SettingActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
