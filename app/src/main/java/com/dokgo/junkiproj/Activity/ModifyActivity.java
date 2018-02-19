package com.dokgo.junkiproj.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dokgo.junkiproj.R;

/**
 * Created by JawsGeun on 2018-01-30.
 */

public class ModifyActivity extends AppCompatActivity{
    Button button;
    private Intent it;
    private TextView nameTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        it = getIntent();
        final String name = it.getStringExtra("name");
        nameTitle = findViewById(R.id.toolbar_title2);
        nameTitle.setText(name);
        button = (Button)findViewById(R.id.modifybtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifyActivity.this, DetailActivity.class);
                //TODO 디비 업데이트 후 아이디값 디테일액티비티로 넘겨주기
                intent.putExtra("name",name);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"정보 수정 완료",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ModifyActivity.this,DetailActivity.class);
        it = getIntent();
        final String name = it.getStringExtra("name");
        intent.putExtra("name",name);
        startActivity(intent);
        finish();
    }
}
