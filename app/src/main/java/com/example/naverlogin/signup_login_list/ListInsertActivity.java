package com.example.naverlogin.signup_login_list;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naverlogin.R;
import com.example.naverlogin.signup_login_list.Task.ListInsert;

import java.util.concurrent.ExecutionException;


public class ListInsertActivity extends AppCompatActivity {
    EditText idEt, titleEt, priceEt, bodyEt;
    Button writeBtn, writeCancelBtn;

    @Override
    protected void onCreate(Bundle savedInstaceState) {
        super.onCreate(savedInstaceState);
        setContentView(R.layout.sll_list_insert);

        idEt = findViewById(R.id.write_id);
        titleEt = findViewById(R.id.write_title);
        priceEt = findViewById(R.id.write_price);
        bodyEt = findViewById(R.id.write_body);

        writeBtn = findViewById(R.id.write_btn);
        writeCancelBtn = findViewById(R.id.writeCancel_btn);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idEt.getText().toString().length() != 0 && titleEt.getText().toString().length() != 0 && priceEt.getText().toString().length() != 0 && bodyEt.getText().toString().length() != 0) {
                    String id = idEt.getText().toString();
                    String title = titleEt.getText().toString();
                    String price = priceEt.getText().toString();
                    String body = bodyEt.getText().toString();
                    ListInsert listInsert = new ListInsert();
                    try {
                        listInsert.execute(id, title, price, body).get();
                        Intent intent = new Intent(getApplicationContext(), ListActivity.class);
                        startActivity(intent);//액티비티 띄우기
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        });

        writeCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
