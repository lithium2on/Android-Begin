package com.example.naverlogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naverlogin.signup_login_list.MainLoginActivity;


public class MainActivity extends AppCompatActivity {

    public static final int sub = 1001; /*다른 액티비티를 띄우기 위한 요청코드(상수)*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button buttonNaver = (Button)findViewById(R.id.buttonNaver); /*페이지 전환버튼*/

        buttonNaver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),NaverActivity.class);
                startActivityForResult(intent,sub);//액티비티 띄우기
            }
        });

        Button buttonGps = (Button)findViewById(R.id.buttonGps); /*페이지 전환버튼*/

        buttonGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),GpsActivity.class);
                startActivityForResult(intent,sub);//액티비티 띄우기
            }
        });

        Button buttonSignup = (Button)findViewById(R.id.buttonSignup); /*페이지 전환버튼*/

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivityForResult(intent,sub);//액티비티 띄우기
            }
        });

        Button buttonSll = (Button)findViewById(R.id.buttonSll); /*페이지 전환버튼*/

        buttonSll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainLoginActivity.class);
                startActivityForResult(intent,sub);//액티비티 띄우기
            }
        });





//        Button buttonSignupSpring = (Button)findViewById(R.id.buttonSignupSpring); /*페이지 전환버튼*/
//
//        buttonSignupSpring.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), com.example.naverlogin.project2.MainActivity.class);
//                startActivityForResult(intent,sub);//액티비티 띄우기
//            }
//        });
//
//        Button buttonSigninSma = (Button)findViewById(R.id.buttonSigninSma); /*페이지 전환버튼*/
//
//        buttonSigninSma.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), com.example.naverlogin.spring_mvc_android.SignInActivity.class);
//                startActivityForResult(intent,sub);//액티비티 띄우기
//            }
//        });

    }
}