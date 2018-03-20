package com.dokgo.junkiproj.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dokgo.junkiproj.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by JawsGeun on 2018-01-30.
 */

public class DetailActivity extends AppCompatActivity {

    private ImageView img,img1;
    private Intent it;
    private TextView nameTitle;
    private String id;
    private TextView birth,phone,address,option,memo;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        birth = (TextView) findViewById(R.id.birth);
        phone = (TextView) findViewById(R.id.phone);
        address = (TextView) findViewById(R.id.address);
        option = (TextView) findViewById(R.id.option);
        memo = (TextView) findViewById(R.id.memo);
        it = getIntent();
        String serverFlag = it.getStringExtra("serverFlag");
        id = it.getStringExtra("id");

        nameTitle = findViewById(R.id.toolbar_title2);

        img = (ImageView)findViewById(R.id.modifyImg);
        img.setImageResource(R.drawable.t3);
        img1 = (ImageView)findViewById(R.id.toolbar_img);
        if(id.equals("1")) img1.setImageResource(R.drawable.s2);
        if(id.equals("2")) img1.setImageResource(R.drawable.s1);
        if(id.equals("3")) img1.setImageResource(R.drawable.s3);
        if(id.equals("4")) img1.setImageResource(R.drawable.s4);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, ModifyActivity.class);
                intent.putExtra("id",id);
                intent.putExtra("name",nameTitle.getText().toString());
                intent.putExtra("birth",birth.getText().toString());
                intent.putExtra("phone",phone.getText().toString());
                intent.putExtra("address",address.getText().toString());
                intent.putExtra("option",option.getText().toString());
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"수정하기",Toast.LENGTH_SHORT).show();
                finish();
            }
        });
        if(serverFlag == null) {
            Task task = new Task();
            task.execute("http://ec2-18-220-255-40.us-east-2.compute.amazonaws.com/senior.php");
        }else{
            nameTitle.setText(it.getStringExtra("name"));
            birth.setText(it.getStringExtra("birth"));
            phone.setText(it.getStringExtra("phone"));
            address.setText(it.getStringExtra("address"));
            option.setText(it.getStringExtra("option"));
            memo.setText(it.getStringExtra("memo"));
        }
    }


    // json 파싱

    private class Task extends AsyncTask<String,Void,Void> {
        String str="null";
        String test = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                URL url = new URL("http://ec2-18-220-255-40.us-east-2.compute.amazonaws.com/senior.php");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestMethod("POST"); // URL 요청에 대한 메소드 설정 : POST.
                conn.setRequestProperty("Accept-Charset", "UTF-8"); // Accept-Charset 설정.
                conn.setRequestProperty("Context_Type", "application/x-www-form-urlencoded;cahrset=UTF-8");
                if(conn.getResponseCode() == conn.HTTP_OK){
                    Log.e("http","ok");
                    InputStreamReader tmp = new InputStreamReader(conn.getInputStream(),"UTF-8");
                    BufferedReader br = new BufferedReader(tmp);
                    StringBuffer buffer = new StringBuffer();

                    while ((str = br.readLine()) != null) {
                        test += str;

                    }
                    br.close();
                    tmp.close();


                }


            }catch (MalformedURLException e){
                return null;
            }catch (IOException e){
                return null;
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            parse(test);
        }

    }



    public void parse(String test){

        try {

            JSONObject test1 =  new JSONObject(test);

            JSONArray array = test1.getJSONArray("독거노인정보");
            for(int i=0; i<array.length(); i++){
                JSONObject temp = (JSONObject)array.get(i);

                if(temp.get("senumber").toString().equals(id)){

                    nameTitle.setText(temp.get("sename").toString());
                    birth.setText(temp.get("birth").toString());
                    phone.setText(temp.get("sephonenum").toString());
                    address.setText(temp.get("seaddress").toString());
                    option.setText(temp.get("seOption").toString());
                    //e5.setText(temp.get("senumber").toString());

                    break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }




    }









    @Override
    public void onBackPressed() {
        Intent intent = new Intent(DetailActivity.this,ListActivity.class);
        startActivity(intent);
        finish();
    }
}
