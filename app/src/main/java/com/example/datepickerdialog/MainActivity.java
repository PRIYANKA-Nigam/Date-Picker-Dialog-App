package com.example.datepickerdialog;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
Button b;
    TextView textView; ListView listView;  ArrayList<String> arrayList;
int y,m,d; static final int d_id=0;static String s=""; ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      //  textView=(TextView)findViewById(R.id.textView2);
        listView=(ListView)findViewById(R.id.ll);
        Intent intent=getIntent(); Bundle bundle=intent.getBundleExtra("bun");
        int n1=bundle.getInt("1",0);
        int n2=bundle.getInt("2",0);
        int n3=bundle.getInt("3",0);
      //  int a=intent.getIntExtra("1",0); int b=intent.getIntExtra("2",0); int c=intent.getIntExtra("3",0);
       String s="";
       s=n1+"/"+n2+"/"+n3;
        HashSet<String> hashSet=new HashSet<>();
        hashSet.add(s);
        arrayList=new ArrayList<>(hashSet);
      adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(adapter);;adapter.notifyDataSetChanged();

      //  textView.setText(s);
        final Calendar cal= Calendar.getInstance();
        y=cal.get(Calendar.YEAR);   m=cal.get(Calendar.MONTH);   d=cal.get(Calendar.DAY_OF_MONTH);
        showDialogonClick();
        loadData();
    }

    private void loadData() {
        SharedPreferences sh = getSharedPreferences("com.example.datepickerdialog", MODE_PRIVATE);
        Set<String> set = sh.getStringSet("notes", new HashSet<String>());
        for (String i : set) {
            arrayList.add(i);
            adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList);
            listView.setAdapter(adapter);
        }
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
            s+=" "+d+"/"+m+"/"+y+" ";
            Toast.makeText(MainActivity.this,y+"/"+m+"/"+d,Toast.LENGTH_LONG).show();

    }};

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu_save,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId()==R.id.save){
           SharedPreferences sh=getApplicationContext().getSharedPreferences("com.example.datepickerdialog",MODE_PRIVATE);
            HashSet<String> set=new HashSet<>(arrayList);
            sh.edit().putStringSet("notes",set).apply();
            Toast.makeText(this,"Data Saved",Toast.LENGTH_LONG).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}