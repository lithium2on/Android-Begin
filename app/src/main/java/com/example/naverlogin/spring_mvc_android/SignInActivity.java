package com.example.naverlogin.spring_mvc_android;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.naverlogin.R;

import java.util.HashMap;
import java.util.Map;

public class SignInActivity extends AppCompatActivity {

    private EditText edtId, edtPwd, edtId2;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sma_sctivity_signin);

        edtId = findViewById(R.id.edt_id);
        edtPwd = findViewById(R.id.edt_pwd);
        edtId2 = findViewById(R.id.edt_id);

        Button btnSignIn = findViewById(R.id.btn_signin);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Map<String, String> map = new HashMap<>();
                map.put("PRO_ID", edtId.getText().toString());
                map.put("PRO_TITLE", edtPwd.getText().toString());
                map.put("PRO_BODY", edtId2.getText().toString());

                MapTask task = new MapTask();
                task.execute(map);
            }
        });
    }

    public class MapTask extends AsyncTask<Map, Integer, String> {

        //doInBackground 전에 동작
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //작업을 쓰레드로 처리
        @Override
        protected String doInBackground(Map... maps) {
            //HTTP 요청 준비
            HttpClient.Builder http = new HttpClient.Builder("POST",  Web.servletURL + "androidSignIn");

            //Parameter 전송
            http.addAllParameters(maps[0]);

            //HTTP 요청 전송
            HttpClient post = http.create();
            post.request();

            //응답 상태 코드
            int statusCode = post.getHttpStatusCode();

            //응답 본문
            String body = post.getBody(); //Spring의 Controller에서 반환한 값. JSON 형식
            return body;
        }

        /*
            doInBackground 후에 동작.
            String s : doInBackground에서 반환한 body
         */
        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            Log.d("JSON_RESULT", s);

            //JSON 형식의 데이터를 Class Object로 바꿔준다.
//            Gson gson = new Gson();
//            Users user = gson.fromJson(s, Users.class);
//
//            if(user != null && user.getEnabled() != 0) {
//                Toast.makeText(getApplicationContext(), "로그인", Toast.LENGTH_SHORT).show();
//            } else {
//                Toast.makeText(getApplicationContext(), "회원 정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show();
//            }
        }
    }
}