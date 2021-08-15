package com.example.datepickerdialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
Button b;
int y,m,d; static final int d_id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Calendar cal= Calendar.getInstance();
        y=cal.get(Calendar.YEAR);   m=cal.get(Calendar.MONTH);   d=cal.get(Calendar.DAY_OF_MONTH);
        showDialogonClick();
    }
    public void showDialogonClick(){
        b=(Button)findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(d_id);
            }});
    }@Override
    protected Dialog onCreateDialog(int id){
        if(id==d_id)
            return new DatePickerDialog(this,dpl,y,m,d);
        return null; }
    private DatePickerDialog.OnDateSetListener dpl=new DatePickerDialog.OnDateSetListener(){@Override
        public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            y=year;m=month+1;d=day;
            Toast.makeText(MainActivity.this,y+"/"+m+"/"+d,Toast.LENGTH_LONG).show(); }};}