package com.example.naverlogin.signup_login_list.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.naverlogin.signup_login_list.Adapter.SimpleTextAdapter;
import com.example.naverlogin.signup_login_list.Common.CommonMethod;
import com.example.naverlogin.signup_login_list.DTO.ListDTO;
import com.google.gson.stream.JsonReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ListSelect extends AsyncTask<Void, Void, String> {

    private String sendMsg, receiveMsg, str;

    ArrayList<ListDTO> list;
    SimpleTextAdapter adapter;

    public ListSelect(ArrayList<ListDTO> list, SimpleTextAdapter adapter) {
        this.list = list;
        this.adapter = adapter;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //프로그레스바 보이게
    }

    @Override
    protected String doInBackground(Void... voids) {
        try {
            // 접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
            URL url = new URL(CommonMethod.ipConfig + "android2/list");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            // 전송할 데이터. GET 방식으로 작성
            sendMsg = "android";

            osw.write(sendMsg);
            osw.flush();
            Log.i("통신 결과1", conn.getResponseCode() + "에러");
            // 받은 데이터 처리
            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                JsonReader reader = new JsonReader(tmp);
                try {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        list.add(readMessage(reader));
                    }
                    reader.endArray();
                } finally {
                    reader.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        return receiveMsg;
    }


    public ListDTO readMessage(JsonReader reader) throws IOException {
        String title = "", body = "";

        reader.beginObject();
        while (reader.hasNext()) {
            String readStr = reader.nextName();
            if (readStr.equals("title")) {
                title = reader.nextString();
            } else if (readStr.equals("body")) {
                body = reader.nextString();

            } else {
                reader.skipValue();
            }
        }

        reader.endObject();
        Log.d("listselect2:myitem2", title + "," + body);
        return new ListDTO(title, body);
    }
}



//package com.example.naverlogin.signup_login_list.Task;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.example.naverlogin.signup_login_list.Adapter.SimpleTextAdapter;
//import com.example.naverlogin.signup_login_list.Common.CommonMethod;
//import com.example.naverlogin.signup_login_list.DTO.ListDTO;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class ListSelect extends AsyncTask<Void, Void, String> {
//
//    private String sendMsg, receiveMsg, str;
//
//    ArrayList<ListDTO> list;
//    SimpleTextAdapter adapter;
//
//    public ListSelect(ArrayList<ListDTO> list, SimpleTextAdapter adapter) {
//        this.list = list;
//        this.adapter = adapter;
//    }
//
//
//
//    @Override
//    protected void onPreExecute() {
//        super.onPreExecute();
//
//        //프로그레스바 보이게
//    }
//
//    @Override
//    protected String doInBackground(Void... voids) {
//        try {
//            // 접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
//            URL url = new URL(CommonMethod.ipConfig + "android2/list");
//
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setRequestMethod("POST");
//            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
//
//            // 전송할 데이터. GET 방식으로 작성
//            sendMsg = "android";
//
//            osw.write(sendMsg);
//            osw.flush();
//            Log.i("통신 결과1", conn.getResponseCode() + "에러");
//            // 받은 데이터 처리
//            if (conn.getResponseCode() == conn.HTTP_OK) {
//                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
//                BufferedReader reader = new BufferedReader(tmp);
//                StringBuffer buffer = new StringBuffer();
//                while ((str = reader.readLine()) != null) {
//                    buffer.append(str);
//                }
//                Log.i("통신 결과2", conn.getResponseCode() + "에러");
//                receiveMsg = buffer.toString();
//                Log.i("receiveMsg : ", receiveMsg);
//
//
//
//                reader.close();
//            } else {
//                Log.i("통신 결과3", conn.getResponseCode() + "에러");
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return receiveMsg;
//    }
//
//
//}