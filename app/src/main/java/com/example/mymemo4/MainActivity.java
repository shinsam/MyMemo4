package com.example.mymemo4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Fragment1 f1;
    Fragment2 f2;
    Fragment3 f3;

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);

        // 프래그먼트 생성
        f1 = new Fragment1();
        f2 = new Fragment2();
        f3 = new Fragment3();

        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.main_fragment, f1);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View v) {
        fragmentTransaction = fragmentManager.beginTransaction();
        switch ( v.getId()){
            case R.id.btn1:
                Log.d("클릭" , "버튼1");
                fragmentTransaction.replace(R.id.main_fragment, f1);
                break;
            case R.id.btn2:
                Log.d("클릭" , "버튼2");
                fragmentTransaction.replace(R.id.main_fragment , f2);
                break;
            case R.id.btn3:
                Log.d("클릭" , "버튼3");
                fragmentTransaction.replace(R.id.main_fragment , f3);
                break;
        }
        fragmentTransaction.commit();
    }
}