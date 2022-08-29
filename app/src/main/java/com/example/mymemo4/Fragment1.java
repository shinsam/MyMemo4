package com.example.mymemo4;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class Fragment1 extends Fragment {
    EditText editDiary;
    Button btnWrite;

    String fileName; //선택한 날짜를 이용해 저장할 파일 이름 ex) 2022_8_30.txt
    String str;     //쓰고, 읽은 내용을 임시 저장한 변수


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        DatePicker dp = view.findViewById(R.id.datePicker);
        editDiary = view.findViewById(R.id.editDiary);
        btnWrite = view.findViewById(R.id.btnWrite);


        //현재의 날짜 알아내기
        int year = dp.getYear();
        int month = dp.getMonth();
        int day = dp.getDayOfMonth();
        //아무것도 선택하지 않아도 fileName은 있어야한다.
        fileName = year +"_" + (month+1) +"_" +  day + ".txt";  //2022_8_22

        dp.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker datePicker, int year, int month, int day) {
                fileName = year +"_" + (month+1) +"_" +  day + ".txt";  //2022_8_22
                Log.d("TODAY" , fileName);

                str = readDiary(fileName);
                editDiary.setText(str);  //화면에 처리

            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //파일 쓰기 기능
                try {
                    //파일 오픈
                    FileOutputStream outFs = getContext().openFileOutput(fileName , Context.MODE_PRIVATE);
                    //쓴다.
                    str = editDiary.getText().toString();
                    outFs.write(str.getBytes());
                    outFs.close();//닫는다.
                    Toast.makeText(getContext(), fileName + "에 저장 하였습니다.", Toast.LENGTH_SHORT).show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        return view;
    }

    private String readDiary(String fileName) {
        //파일을 열고 읽고 닫아
        try {
            FileInputStream inFs = getContext().openFileInput(fileName);
            //바이트형의 버퍼(배열)을 선언
            byte[] txt = new byte[500];
            inFs.read(txt);
            inFs.close();
            //byte형으로 읽어온 일기(txt)를 string형의 str으로 변하여 리턴하자
            str = new String(txt);
        } catch (IOException e) {
            str="";
            editDiary.setHint("선택한 날의 일기 없음");
        }
        return str;
    }
}