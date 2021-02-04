package com.example.naverlogin.signup_login_list;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.naverlogin.R;
import com.example.naverlogin.signup_login_list.Task.DetailSelect;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

public class ListDetailActivity extends AppCompatActivity {
    private static final String TAG = "ListDetailActivity";

    TextView tv_title, tv_body;
    String str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sll_list_detail);

        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_body = (TextView) findViewById(R.id.tv_body);

        String title = "";
        String body = "";

        Bundle extras = getIntent().getExtras();

        title = extras.getString("title");
        body = extras.getString("body");

        DetailSelect detailSelect = new DetailSelect();
        try {
            str = detailSelect.execute(title, body).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "onClick: str:" + str);
        JSONObject obj = null;
        try {
            obj = new JSONObject(str);
            final String title2 = obj.getString("title");
            final String body2 = obj.getString("body");
            tv_title.setText(title2);
            tv_body.setText(body2);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
