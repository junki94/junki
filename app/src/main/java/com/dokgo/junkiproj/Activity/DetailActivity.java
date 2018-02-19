package com.dokgo.junkiproj.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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

    private ImageView img;
    private Intent it;
    private TextView nameTitle;
    private String id;
    private EditText e1,e2,e3,e4,e5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        e1 = (EditText)findViewById(R.id.birth);
        e2 = (EditText)findViewById(R.id.phone);
        e3 = (EditText)findViewById(R.id.address);
        e4 = (EditText)findViewById(R.id.option);
        e5 = (EditText)findViewById(R.id.memo);
        it = getIntent();
        id = it.getStringExtra("id");

        nameTitle = findViewById(R.id.toolbar_title2);

        img = (ImageView)findViewById(R.id.modifyImg);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DetailActivity.this, ModifyActivity.class);
                intent.putExtra("name",id);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"수정하기",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        Task task = new Task();
        task.execute("http://ec2-18-220-255-40.us-east-2.compute.amazonaws.com/senior.php");
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
                    e1.setText(temp.get("senumber").toString());
                    e2.setText(temp.get("sephonenum").toString());
                    e3.setText(temp.get("seaddress").toString());
                    e4.setText(temp.get("seOption").toString());
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
