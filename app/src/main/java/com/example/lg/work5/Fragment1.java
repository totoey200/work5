package com.example.lg.work5;

import android.app.Fragment;
import android.content.DialogInterface;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Fragment1 extends Fragment {
    View fragview;
    Button apple,grape,kiwi,grapefruit,order, edit, reset;
    TextView t_name, time, spa, piz, mem, price;
    AlertDialog order_dialog;
    ArrayList<Data> data = new ArrayList<Data>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        fragview = inflater.inflate(R.layout.activity_fragment1,container,false);
        init();
        return fragview;
    }
    void init(){
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
        data_init();
        btn_init();
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
        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                summon_dialog(v,true);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                summon_dialog(v,false);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                data.remove(nowbtn(t_name));
                data.add(nowbtn(t_name),new Data(t_name.getText().toString(),null,0,0,null,0));
                time.setText("");
                spa.setText("");
                piz.setText("");
                mem.setText("");
                price.setText("");
                edit.setEnabled(false);
                reset.setEnabled(false);
                changetext(false,nowbtn(t_name));
            }
        });
    }

    void summon_dialog(final View v,boolean status){
        View dlgview = View.inflate(getActivity(),R.layout.mdialog,null);
        final EditText spa_input = (EditText)dlgview.findViewById(R.id.spa_input);
        final EditText piz_input = (EditText)dlgview.findViewById(R.id.piz_input);
        final RadioButton non = (RadioButton)dlgview.findViewById(R.id.no_mem);
        final RadioButton normal = (RadioButton)dlgview.findViewById(R.id.nor_mem);
        RadioButton vip = (RadioButton)dlgview.findViewById(R.id.vip_mem);
        piz_input.setText(piz.getText().toString());
        spa_input.setText(spa.getText().toString());
        if(mem.getText().toString().equals("멤버십 없음")){
            non.setChecked(true);
        }
        else if(mem.getText().toString().equals("기본 멤버십")){
            normal.setChecked(true);
        }
        else if(mem.getText().toString().equals("VIP 멤버십")){
            vip.setChecked(true);
        }
        AlertDialog.Builder order_builder = new AlertDialog.Builder(getActivity());
        order_builder.setNegativeButton("취소",null);
        order_builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int spa_num = getinput(spa_input);
                        int piz_num = getinput(piz_input);
                        DateFormat df = new SimpleDateFormat("yyyyMMdd HH:mm");
                        String nowTime = df.format(Calendar.getInstance().getTime());
                        Data data = new Data(t_name.getText().toString(), nowTime,spa_num,piz_num
                                ,mem_name(non,normal),cal_price(non,normal,spa_num,piz_num));
                        set_data(t_name.getText().toString(),data);
                        Snackbar.make(v,"정보가 입력되었습니다.",Snackbar.LENGTH_SHORT).show();
                        changetext(true,nowbtn(t_name));
                        edit.setEnabled(true);
                        reset.setEnabled(true);
                    }
                });
                order_builder.setTitle(t_name.getText());
        order_builder.setView(dlgview);
        order_dialog = order_builder.create();
        order_dialog.show();
        enable(spa_input,piz_input,order_dialog);
        if(status)
            ((AlertDialog) order_dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
    }

    void tableClick(int a){
        t_name.setText(data.get(a).getName());
        time.setText("");
        spa.setText("");
        piz.setText("");
        mem.setText("");
        price.setText("");
        if(data.get(a).getTime()==null){
            order.setEnabled(true);
            edit.setEnabled(false);
            reset.setEnabled(false);
            Toast.makeText(getActivity(),"비어있는 테이블입니다.",Toast.LENGTH_SHORT).show();
        }
        else{
            edit.setEnabled(true);
            reset.setEnabled(true);
            t_name.setText(data.get(a).getName());
            time.setText(data.get(a).getTime());
            Log.d("set",Integer.toString(data.get(a).getSpa()));
            spa.setText(Integer.toString(data.get(a).getSpa()));
            piz.setText(Integer.toString(data.get(a).getPiz()));
            mem.setText(data.get(a).getMem());
            price.setText(Integer.toString(data.get(a).getPrice()));
        }
    }

    String mem_name(RadioButton non,RadioButton normal){
        if(non.isChecked()){
            return "멤버십 없음";
        }
        else if(normal.isChecked()){
            return "기본 멤버십";
        }
        else {
            return "VIP 멤버십";
        }
    }
    int cal_price(RadioButton non, RadioButton normal,int a,int b){
        int result = 10000*a+12000*b;
        if(mem_name(non,normal).equals("멤버십 없음")){
            return result;
        }
        else if(mem_name(non,normal).equals("기본 멤버십")){
            result*=0.9;
            return result;
        }
        else if(mem_name(non,normal).equals("VIP 멤버십")){
            result*=0.7;
            return result;
        }
        return 0;
    }
    int getinput(EditText editText){
        int result = Integer.parseInt(editText.getText().toString());
        return result;
    }
    void set_data(String name,Data sample){
        int index = nowbtn(t_name);
        data.remove(index);
        data.add(index,sample);
        time.setText(sample.getTime());
        spa.setText(Integer.toString(sample.getSpa()));
        piz.setText(Integer.toString(sample.getPiz()));
        mem.setText(sample.getMem());
        price.setText(Integer.toString(sample.getPrice()));
    }
    void enable(final EditText edit1, final EditText edit2, final AlertDialog dialog){
        final boolean check[]=  {false,false};
        edit1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(edit1.getText().toString().equals(""))
                    check[0]=false;
                else
                    check[0]=true;
                if(edit2.getText().toString().equals("")){
                    check[1]=false;
                }
                else
                    check[1]=true;
                if(check[0]&&check[1]){
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
                else {
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edit1.getText().toString().equals(""))
                    check[0]=false;
                else
                    check[0]=true;
                if(edit2.getText().toString().equals("")){
                    check[1]=false;
                }
                else
                    check[1]=true;
                if(check[0]&&check[1]){
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
                else {
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        });
        edit2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if(edit2.getText().toString().equals("")){
                    check[1]=false;
                }
                else
                    check[1]=true;
                if(check[0]&&check[1]){
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
                else {
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(edit2.getText().toString().equals("")){
                    check[1]=false;
                }
                else
                    check[1]=true;
                if(check[0]&&check[1]){
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(true);
                }
                else {
                    ((AlertDialog) dialog).getButton(AlertDialog.BUTTON_POSITIVE).setEnabled(false);
                }
            }
        });

    }
    void changetext(boolean state,int index){
        if(state){
            if(index==0){
                apple.setText("사과 Table");
            }
            else if(index == 1){
                grape.setText("포도 Table");
            }
            else if(index == 2){
                kiwi.setText("키위 Table");
            }
            else if(index == 3){
                grapefruit.setText("자몽 Table");
            }
        }
        else{
            if(index==0){
                apple.setText("사과 Table(비어있음)");
            }
            else if(index == 1){
                grape.setText("포도 Table(비어있음)");
            }
            else if(index == 2){
                kiwi.setText("키위 Table(비어있음)");
            }
            else if(index == 3){
                grapefruit.setText("자몽 Table(비어있음)");
            }
        }
    }
    int nowbtn(TextView tv){
        switch (tv.getText().toString()){
            case "사과 Table":
                return 0;
            case "포도 Table":
                return 1;
            case "키위 Table":
                return 2;
            case "자몽 Table":
                return 3;
        }
        return 0;
    }
}
