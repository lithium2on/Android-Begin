package com.example.naverlogin.signup_login_list;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naverlogin.R;
import com.example.naverlogin.signup_login_list.DTO.MemberDTO;
import com.example.naverlogin.signup_login_list.Task.LoginSelect;

import org.json.JSONObject;

import java.util.concurrent.ExecutionException;


public class MainLoginActivity
        extends AppCompatActivity {
    private static final String TAG = "MainLoginActivity";
    //    public static final int sub = 1001; /*다른 액티비티를 띄우기 위한 요청코드(상수)*/
    public static MemberDTO loginDTO = null;
    String str;
    Button loginBtn;
    Button registerBtn;
    EditText idet, pwet;
    TextView reet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sll_login_main);

//        setTitle("ORACLE");

        loginBtn = (Button) findViewById(R.id.login_btn);
        registerBtn = (Button) findViewById(R.id.register_btn);
        idet = (EditText) findViewById(R.id.register_id);
        pwet = (EditText) findViewById(R.id.register_pw);
        reet = (TextView) findViewById(R.id.result);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("login", "로그인 하는중");
                if (idet.getText().toString().length() != 0 &&
                        pwet.getText().toString().length() != 0) {  //아이디와 비밀번호의 길이가 0이 아니면
                    String id = idet.getText().toString();
                    String passwd = pwet.getText().toString();
                    Log.w("앱에서 보낸값", id + ", " + passwd);

                    LoginSelect loginSelect = new LoginSelect();
                    try {
                        str = loginSelect.execute(id, passwd).get();
//                        loginSelect.execute(id, passwd).get();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    reet.setText(str);
                    JSONObject obj = null;
                    try {
                        Log.d(TAG, "onClick: str:" + str);
                        obj = new JSONObject(str);
                        Log.d(TAG, "onClick: obj:" + obj);
                        final String message = obj.getString("message");
                        reet.setText(message);
                        Log.d(TAG, "onClick: message:" + message);
                        Log.d(TAG, "onClick: obj.id:" + obj.getString("id"));

                        if (!obj.getString("id").equals("")) {  //로그인 정보가 있으면, 서브1 화면을 띄움
                            Log.d(TAG, "onClick: id:" + obj.getString("id"));
                            Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                            startActivity(intent);//액티비티 띄우기

                        } else { //로그인 정보가 맞지 않으면 토스트창 띄우고 id, pw칸 지우고 id칸에 포커스
                            Toast.makeText(MainLoginActivity.this, "아이디나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
                            idet.setText("");
                            pwet.setText("");
                            idet.requestFocus();
                        }
                    } catch (Exception e) {
                        Log.i("DBtest", ".....ERROR.....!");
                    }
                } else {
                    reet.setText("아이디를 입력해 주세요");
                    idet.requestFocus();
                    Log.d(TAG, "아이디를 입력해 주세요");
                }
            }
        });


        registerBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivity(intent);//액티비티 띄우기
            }
        });
    }
}

//    if (loginDTO != null) {  //로그인 정보가 있으면, 서브1 화면을 띄움
//                        Log.d(TAG, "onClick: id:" + loginDTO.getId());
//
//                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
//                        startActivity(intent);
//                    } else { //로그인 정보가 맞지 않으면 토스트창 띄우고 id, pw칸 지우고 id칸에 포커스
//                        Toast.makeText(MainLoginActivity.this, "아이디나 비밀번호가 틀렸습니다.", Toast.LENGTH_SHORT).show();
//                        idet.setText("");
//                        pwet.setText("");
//                        idet.requestFocus();
//                    }
//
//
//                    //      Log.w("서버에서 보낸값", str);
//
//                } else {
//                    reet.setText("아이디를 입력해 주세요");
//                    idet.requestFocus();
//                    Log.d(TAG, "아이디를 입력해 주세요");
//                }
//                Log.w("login", "로그인 하는중");
//                try {
//                    String result;
//                    String id = idet.getText().toString();
//                    String pw = pwet.getText().toString();
//                    Log.w("앱에서 보낸값", id + "," + pw);
//
//                    Re2 task = new Re2();
//                    result = task.execute(id, pw).get();
//                    Log.w("받은값", result);
