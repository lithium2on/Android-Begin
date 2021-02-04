package com.example.naverlogin.signup_login_list.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.example.naverlogin.signup_login_list.Common.CommonMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class LoginSelect extends AsyncTask<String, Void, String> {
    private static final String TAG = "LoginSelect";

    private String sendMsg, receiveMsg, str;

  


    @Override
    protected String doInBackground(String... strings) {
        try {


            // 접속할 서버 주소 (이클립스에서 android.jsp 실행시 웹브라우저 주소)
            URL url = new URL(CommonMethod.ipConfig + "android2/login");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestMethod("POST");
            OutputStreamWriter osw = new OutputStreamWriter(conn.getOutputStream());

            // 전송할 데이터. GET 방식으로 작성
            sendMsg = "id=" + strings[0] + "&passwd=" + strings[1];
            Log.i("보내기전 데이터", sendMsg);
            osw.write(sendMsg);
            osw.flush();
            Log.i("통신 결과1", conn.getResponseCode() + "에러");
            // 받은 데이터 처리
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

//    public MemberDTO readMessage(InputStreamReader tmp) throws IOException {
//        JsonReader reader = new JsonReader(tmp);
//
//        String id = "", email = "";
//
//        //비밀번호는 가져올 필요가 없고, 보안을 위해 가져오지 않는다.
//        reader.beginObject();
//        while (reader.hasNext()) {
//            String readStr = reader.nextName();
//            if (readStr.equals("id")) {
//                id = reader.nextString();
//            } else if (readStr.equals("email")) {
//                email = reader.nextString();
//            } else {
//                reader.skipValue();
//            }
//        }
//        reader.endObject();
//        Log.d(TAG, id + "," + email);
//        return new MemberDTO(id, email);
//    }
}