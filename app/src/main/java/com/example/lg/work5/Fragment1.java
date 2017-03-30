package com.example.lg.work5;

import android.app.Fragment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    View fragview;
    Button apple,grape,kiwi,grapefruit,order, edit, reset;
    TextView t_name, time, spa, piz, mem, price;
    ArrayList<Data> data = new ArrayList<Data>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragview = inflater.inflate(R.layout.activity_fragment1,container,false);
        init();
        return fragview;
    }
    void init(){
        data_init();
        btn_init();
        apple = (Button)fragview.findViewById(R.id.apple);
        grape = (Button)fragview.findViewById(R.id.grape);
        kiwi = (Button)fragview.findViewById(R.id.kiwi);
        grapefruit = (Button)fragview.findViewById(R.id.grapefruit);
        order = (Button)fragview.findViewById(R.id.order);
        edit = (Button)fragview.findViewById(R.id.edit);
        reset = (Button)fragview.findViewById(R.id.reset);
        t_name = (TextView)fragview.findViewById(R.id.t_name);
        time = (TextView)fragview.findViewById(R.id.time);
        spa = (TextView)fragview.findViewById(R.id.spa);
        piz = (TextView)fragview.findViewById(R.id.piz);
        mem = (TextView)fragview.findViewById(R.id.member);
        price = (TextView)fragview.findViewById(R.id.price);
    }
    void data_init(){
        data.add(new Data("사과 Table",null,0,0,null,0));
        data.add(new Data("포도 Table",null,0,0,null,0));
        data.add(new Data("키위 Table",null,0,0,null,0));
        data.add(new Data("자몽 Table",null,0,0,null,0));
    }

    void btn_init(){
        apple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableClick(0);
            }
        });
        grape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableClick(1);
            }
        });
        kiwi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableClick(2);
            }
        });
        grapefruit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableClick(3);
            }
        });
    }

    void tableClick(int a){
        if(data.get(a).getTime()==null){
            edit.setEnabled(false);
            reset.setEnabled(false);
            Toast.makeText(getActivity(),"비어있는 테이블입니다.",Toast.LENGTH_SHORT).show();
        }
        else{
            edit.setEnabled(true);
            reset.setEnabled(true);
            t_name.setText(data.get(a).getName());
            time.setText(data.get(a).getTime());
            spa.setText(data.get(a).getSpa());
            piz.setText(data.get(a).getPiz());
            mem.setText(data.get(a).getMem());
            price.setText(data.get(a).getPrice());
        }
    }
}
