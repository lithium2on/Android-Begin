package com.example.naverlogin.signup_login_list.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.naverlogin.R;
import com.example.naverlogin.signup_login_list.DTO.ListDTO;
import com.example.naverlogin.signup_login_list.ListDetailActivity;

import java.util.ArrayList;

import static com.example.naverlogin.signup_login_list.ListActivity.selItem;

public class SimpleTextAdapter extends RecyclerView.Adapter<SimpleTextAdapter.ItemViewHolder> {
    private static final String TAG = "main MyRVAdapter";
    Context mContext;
    ArrayList<ListDTO> arrayList;

    public SimpleTextAdapter(Context mContext, ArrayList<ListDTO> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.sll_list_recyclerview_item, parent, false);

        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, final int position) {
        Log.d(TAG, "onBindViewHolder: " + position);

        ListDTO item = arrayList.get(position); //리스트의 인덱스를 가져와서
        holder.setItem(item); //화면을 만든다

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: " + position); //position이 여기서 바뀌면 안되므로 final 속성을 붙이라는 경고가 뜬다

                selItem = arrayList.get(position);
                Toast.makeText(mContext, selItem.getTitle(), Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(mContext.getApplicationContext(), ListDetailActivity.class);
                intent.putExtra("title", selItem.getTitle());
                intent.putExtra( "body", selItem.getBody());

                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    //리사이클러뷰 내용 모두 지우기
    public void removeAllItem() {
        arrayList.clear();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        //객체 선언
        LinearLayout parentLayout;
        TextView title, body;
        ImageView iv_image;



        //뷰 홀더 클래스
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            //객체 초기화
            parentLayout = itemView.findViewById(R.id.parentLayout);
            title = itemView.findViewById(R.id.textView1);
            body = itemView.findViewById(R.id.textView2);

        }

        public void setItem(ListDTO item) {
            title.setText(item.getTitle());
            body.setText(item.getBody());


//            Glide.with(itemView).load(item.getImage_path()).into(iv_image);
        }
    }
}