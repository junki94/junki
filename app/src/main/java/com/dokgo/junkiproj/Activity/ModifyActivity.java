package com.dokgo.junkiproj.Activity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dokgo.junkiproj.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;


public class ModifyActivity extends AppCompatActivity{
    Button button;
    private Intent it;
    private TextView nameTitle;
    private EditText birth,phone,address,option,memo;
    private String id;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        it = getIntent();
        final String name = it.getStringExtra("name");
        final String m_birth = it.getStringExtra("birth");
        final String m_phone = it.getStringExtra("phone");
        final String m_address = it.getStringExtra("address");
        final String m_option = it.getStringExtra("option");
        final String m_memo = it.getStringExtra("memo");

        nameTitle = findViewById(R.id.toolbar_title2);
        birth = findViewById(R.id.modify_birth);
        phone = findViewById(R.id.modify_phone);
        address = findViewById(R.id.modify_address);
        option = findViewById(R.id.modify_option);
        memo = findViewById(R.id.modify_memo);
        id = it.getStringExtra("id");

        nameTitle.setText(name);
        birth.setText(m_birth);
        phone.setText(m_phone);
        address.setText(m_address);
        option.setText(m_option);
        memo.setText(m_memo);

        button = (Button)findViewById(R.id.modifybtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ModifyActivity.this, DetailActivity.class);
                //TODO 디비 업데이트 후 아이디값 디테일액티비티로 넘겨주기
                intent.putExtra("id",id);
                Task task = new Task();
//                task.execute("http://ec2-18-220-255-40.us-east-2.compute.amazonaws.com/zzz.php");
                task.execute("http://172.30.1.33:8080/test/put.do");

                startActivity(intent);
                Toast.makeText(getApplicationContext(),"정보 수정 완료",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

    }
    private class Task extends AsyncTask<String,Void,Void> {
        String str="null";
        String test = "";
        @Override
        protected Void doInBackground(String... strings) {
            OutputStream os = null;
            InputStream is = null;
            HttpURLConnection conn = null;
            ByteArrayOutputStream baos = null;
            String response="";

        //제이슨
            JSONObject jsonObject = new JSONObject();
            try{

                jsonObject.put("birth", birth.getText().toString());
                jsonObject.put("sephonenum", phone.getText().toString());
                jsonObject.put("seaddress", address.getText().toString());
                jsonObject.put("seoption", option.getText().toString());
                jsonObject.put("senumber",id);
                jsonObject.put("memo",memo.getText().toString());

                Log.e("jsonO",jsonObject.toString());


            }catch (JSONException e){
                e.printStackTrace();
            }//end json

            //보내기
            try {

//                URL url = new URL("http://ec2-18-220-255-40.us-east-2.compute.amazonaws.com/zzz.php");
                URL url = new URL("http://172.30.1.33:8080/test/put.do");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();


                httpURLConnection.setReadTimeout(5000);
                httpURLConnection.setConnectTimeout(5000);
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setRequestProperty("content-type", "application/json");
                httpURLConnection.setDoInput(true);
                httpURLConnection.connect();


                OutputStream outputStream = httpURLConnection.getOutputStream();
                outputStream.write(jsonObject.toString().getBytes("UTF-8"));
                Log.e("찍혔냐?",jsonObject.toString());
                outputStream.flush();
                outputStream.close();


                int responseStatusCode = httpURLConnection.getResponseCode();

                InputStream inputStream;
                if(responseStatusCode == HttpURLConnection.HTTP_OK) {
                    inputStream = httpURLConnection.getInputStream();
                }
                else{
                    inputStream = httpURLConnection.getErrorStream();
                }


                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                StringBuilder sb = new StringBuilder();
                String line = null;

                while((line = bufferedReader.readLine()) != null){
                    sb.append(line);
                }


                bufferedReader.close();


                return null;


            } catch (Exception e) {
                Log.e("찍혔냐?@@@","응응");

                return null;
            }

        }
    }

            @Override
    public void onBackPressed() {
        Intent intent = new Intent(ModifyActivity.this,DetailActivity.class);
        it = getIntent();
        intent.putExtra("id",id);
        startActivity(intent);
        finish();
    }
}
