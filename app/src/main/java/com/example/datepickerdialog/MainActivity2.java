package com.example.datepickerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashSet;

public class MainActivity2 extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
TextView textView; ArrayList<String> arrayList;HashSet<String> set=new HashSet<>();
ListView listView; Button button;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Bundle bundle=new Bundle();
        bundle.putInt("1",year);bundle.putInt("2",month+1);bundle.putInt("3",dayOfMonth);
      Intent intent=new Intent(this,MainActivity.class);
     intent.putExtra("bun",bundle);
      startActivity(intent);
    }
    public void ShowPicker(View view){
        DatePickerDialog datePickerDialog=new DatePickerDialog(this,MainActivity2.this,2016,12,01);
        datePickerDialog.show();
    }
}