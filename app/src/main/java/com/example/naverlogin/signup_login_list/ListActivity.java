package com.example.naverlogin.signup_login_list;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.naverlogin.R;
import com.example.naverlogin.signup_login_list.Adapter.SimpleTextAdapter;
import com.example.naverlogin.signup_login_list.DTO.ListDTO;
import com.example.naverlogin.signup_login_list.Task.ListSelect;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ListActivity extends AppCompatActivity {
    private static final String TAG = "ListActivity";

    public static ListDTO selItem = null;
    Button button1, button2, button3, button4, button5, button6;
    RecyclerView recyclerView;
    SimpleTextAdapter adapter;
    ArrayList<ListDTO> list;

    ListSelect listSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sll_list_main);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);

        //리사이클러 뷰 시작
        recyclerView = findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        list = new ArrayList<>();
        adapter = new SimpleTextAdapter(this, list);

        recyclerView.setAdapter(adapter);


        listSelect = new ListSelect(list, adapter);
        listSelect.execute();


        SwipeRefreshLayout mSwipeRefreshLayout = findViewById(R.id.refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                adapter.removeAllItem();
                listSelect = new ListSelect(list, adapter);
                try {
                    listSelect.execute().get();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                recyclerView.getAdapter().notifyDataSetChanged();
                adapter.notifyDataSetChanged();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);

            }
        });


        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                recyclerView.smoothScrollToPosition(0);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ListInsertActivity.class);
                startActivity(intent);
            }
        });

    }

    //이미 화면이 있을때 받는 곳
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.d(TAG, "onNewIntent: 호출됨");

        //새로고침하면서 이미지가 겹치는 현상 없애기 위해...
        adapter.removeAllItem();


        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        try {
            if (intent != null) {
                listSelect = new ListSelect(list, adapter);
                listSelect.execute().get();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

//package com.example.naverlogin.signup_login_list;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Button;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.naverlogin.R;
//import com.example.naverlogin.signup_login_list.Adapter.MyRecyclerViewAdapter;
//import com.example.naverlogin.signup_login_list.DTO.MyItem;
//import com.example.naverlogin.signup_login_list.Task.ListSelect;
//
//import java.util.ArrayList;
//
//import static com.example.naverlogin.signup_login_list.Common.CommonMethod.isNetworkConnected;
//
//public class ListActivity extends AppCompatActivity {
//    private static final String TAG = "ListActivity";
//
//    public static MyItem selItem = null;
//    Button button1, button2, button3, button4, button5, button6;
//    RecyclerView recyclerView;
//    MyRecyclerViewAdapter adapter;
//    ArrayList<MyItem> myItemArrayList;
//
//    ListSelect listSelect;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.sll_list);
//        button1 = findViewById(R.id.button1);
//        button2 = findViewById(R.id.button2);
//        button3 = findViewById(R.id.button3);
//        button4 = findViewById(R.id.button4);
//        button5 = findViewById(R.id.button5);
//        button6 = findViewById(R.id.button6);
//
//        //리사이클러 뷰 시작
//        recyclerView = findViewById(R.id.recyclerView);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//
//        myItemArrayList = new ArrayList<>();
//        adapter = new MyRecyclerViewAdapter(this, myItemArrayList);
//
//        recyclerView.setAdapter(adapter);
//
//        if (isNetworkConnected(this) == true) {
//            listSelect = new ListSelect(myItemArrayList, adapter);
//            listSelect.execute();
//        } else {
//            Toast.makeText(this, "인터넷 연결 실패!", Toast.LENGTH_SHORT).show();
//        }
//
//        button6.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent= new Intent(getApplicationContext(), ListInsertActivity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//}
//
//
//
//
