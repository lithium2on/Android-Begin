package com.example.naverlogin.signup_login_list.Task;

import android.os.AsyncTask;

import com.example.naverlogin.signup_login_list.Common.CommonMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ListInsert extends AsyncTask<String, Void, String> {

    String sendMsg, str, result;

    @Override
    protected String doInBackground(String... strings) {

        try {
            URL ur1 = new URL(CommonMethod.ipConfig + "android2/listInsert");

            HttpURLConnection conn = (HttpURLConnection) ur1.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            sendMsg = "id=" + strings[0] + "&title=" + strings[1] + "&price=" + strings[2] + "&body=" + strings[3];

            osw.write(sendMsg);
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();

                // jsp에서 보낸 값을 받는 부분
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                result = buffer.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}

