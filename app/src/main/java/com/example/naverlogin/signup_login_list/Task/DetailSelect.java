package com.example.naverlogin.signup_login_list.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.naverlogin.signup_login_list.Common.CommonMethod;
import com.example.naverlogin.signup_login_list.DTO.ListDTO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class DetailSelect extends AsyncTask<String, Void, String> {

    private static final String TAG = "DetailSelect";
    String sendMsg, receiveMsg, str;
    ArrayList<ListDTO> list;


    @Override
    protected String doInBackground(String... strings) {

        try {
            URL ur1 = new URL(CommonMethod.ipConfig + "android2/listDetail");

            HttpURLConnection conn = (HttpURLConnection) ur1.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            sendMsg = "title=" + strings[0] + "&body=" + strings[1] ;

            osw.write(sendMsg);
            osw.flush();

            if (conn.getResponseCode() == conn.HTTP_OK) {
                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
                BufferedReader reader = new BufferedReader(tmp);
                StringBuffer buffer = new StringBuffer();
//                while ((str = reader.readLine()) != null) {
//                    buffer.append(str);
//                }
                while ((str = reader.readLine()) != null) {
                    buffer.append(str);
                }
                Log.i("통신 결과2", conn.getResponseCode() + "에러");
                receiveMsg = buffer.toString();
                Log.i("receiveMsg : ", receiveMsg);
                reader.close();

//                loginDTO = readMessage(tmp);
//                Log.d(TAG, "doInBackground: " + tmp);
//                Log.d(TAG, "doInBackground: " + loginDTO);
//                Log.d(TAG, "doInBackground: " + loginDTO.getId());

            } else {
                Log.i("통신 결과3", conn.getResponseCode() + "에러");
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return "LoginDTO select complete";
        return receiveMsg;

    }


    @Override
    protected void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.d(TAG, "onPostExecute: " + result);
    }
}

//package com.example.naverlogin.signup_login_list.Task;
//
//import android.os.AsyncTask;
//import android.util.Log;
//
//import com.example.naverlogin.signup_login_list.Common.CommonMethod;
//import com.example.naverlogin.signup_login_list.DTO.ListDTO;
//import com.google.gson.stream.JsonReader;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.OutputStreamWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.ArrayList;
//
//public class DetailSelect extends AsyncTask<String, Void, String> {
//
//    private static final String TAG = "DetailSelect";
//    String sendMsg, receiveMsg, result;
//    ArrayList<ListDTO> list;
//
//
//    @Override
//    protected String doInBackground(String... strings) {
//
//        try {
//            URL ur1 = new URL(CommonMethod.ipConfig + "android2/listDetail");
//
//            HttpURLConnection conn = (HttpURLConnection) ur1.openConnection();
//            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
//            conn.setRequestMethod("POST");
//            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());
//
//            sendMsg = "title=" + strings[0] + "&body=" + strings[1] ;
//
//            osw.write(sendMsg);
//            osw.flush();
//
//            if (conn.getResponseCode() == conn.HTTP_OK) {
//                InputStreamReader tmp = new InputStreamReader(conn.getInputStream(), "UTF-8");
//                JsonReader reader = new JsonReader(tmp);
//                try {
//                    reader.beginArray();
//                    while (reader.hasNext()) {
//                        list.add(readMessage(reader));
//                    }
//                    reader.endArray();
//                } finally {
//                    reader.close();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//        }
//        Log.d(TAG, "onClick: receiveMsg:" + receiveMsg);
//        return receiveMsg;
//
//    }
//
//
//    public ListDTO readMessage(JsonReader reader) throws IOException {
//        String title = "", body = "";
//
//        reader.beginObject();
//        while (reader.hasNext()) {
//            String readStr = reader.nextName();
//            if (readStr.equals("title")) {
//                title = reader.nextString();
//            } else if (readStr.equals("body")) {
//                body = reader.nextString();
//
//            } else {
//                reader.skipValue();
//            }
//        }
//
//        reader.endObject();
//        Log.d("DetailSelect:myitem", title + "," + body);
//        return new ListDTO(title, body);
//    }
//}