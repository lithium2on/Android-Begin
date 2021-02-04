package com.example.naverlogin.signup_login_list;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naverlogin.R;
import com.example.naverlogin.signup_login_list.Task.SignupInsert;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";
    Button registerBtn, registerCancelBtn;
    EditText idet, pwet, emet;
    String result, result2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sll_signup);

        setTitle("ORACLE");

        registerBtn = (Button)findViewById(R.id.register_btn);
        registerCancelBtn = (Button)findViewById(R.id.registerCancel_btn);
        idet = (EditText)findViewById(R.id.register_id);
        pwet = (EditText)findViewById(R.id.register_pw);
        emet = (EditText)findViewById(R.id.register_email);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.w("register","회원가입 하는중");
                try {
                    String id = idet.getText().toString();
                    String pw = pwet.getText().toString();
                    String email = emet.getText().toString();
                    Log.w("앱에서 보낸값",id+", "+pw+", "+email);

                   SignupInsert task = new SignupInsert();
                    result = task.execute(id, pw, email).get();
                    Log.w("받은값",result);
                } catch (Exception e) {
                    Log.i("DBtest", ".....ERROR.....!");
                }
//                if(result2.equals("1")) {
//                    Log.d(TAG, "onClick: 삽입 성공");
//                    finish();
//                } else {
//                    Log.d(TAG, "onClick: 삽입 실패");
//                    finish();
//                }
            }
        });

        //취소 버튼
        registerCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}