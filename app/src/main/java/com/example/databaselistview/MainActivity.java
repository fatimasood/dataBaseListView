package com.example.databaselistview;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText name, contact, dob,surName,depart,inst;
    Button insert,view,update,listv;
    DBHelper DB;

    ListView userlist;

    ArrayList<String> listItem;
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.Name);
        surName = findViewById(R.id.SurName);
        dob = findViewById(R.id.dob);
        depart = findViewById(R.id.Dept);
        inst = findViewById(R.id.ins);
        contact = findViewById(R.id.Contect);

        insert = findViewById(R.id.btnInsert);
        view = findViewById(R.id.btnShow);
        update = findViewById(R.id.btnupdate);
        listv = findViewById(R.id.listbtn);

        DB = new DBHelper(this);

        listItem = new ArrayList<>();

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String surNameTXT = surName.getText().toString();
                String depTXT = depart.getText().toString();
                String instTXT = inst.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkinsertdata = DB.insertuserdata(nameTXT,surNameTXT, contactTXT,depTXT, dobTXT,instTXT);
                if(checkinsertdata==true)
                    Toast.makeText(MainActivity.this, "New Entry Inserted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Inserted", Toast.LENGTH_SHORT).show();
            }
        });


        listv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ViewData.class);
                startActivity(intent);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String contactTXT = contact.getText().toString();
                String dobTXT = dob.getText().toString();

                Boolean checkupdatedata = DB.updateuserdata(nameTXT, contactTXT, dobTXT);
                if(checkupdatedata==true)
                    Toast.makeText(MainActivity.this, "Entry Updated", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "New Entry Not Updated", Toast.LENGTH_SHORT).show();
            }        });



       view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(0)+"\n");
                    buffer.append("Sur Name :"+res.getString(1)+"\n");
                    buffer.append("Contact :"+res.getString(2)+"\n");
                    buffer.append("Date of Birth :"+res.getString(3)+"\n");
                    buffer.append("Department :"+res.getString(4)+"\n");
                    buffer.append("Institute :"+res.getString(5)+"\n");
                }



                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }        });
    }



   /* private void getdata() {
            Cursor cursor=DB.getdata();
            if(cursor.getCount()==0){
                Toast.makeText(MainActivity.this, "No Entry Exists", Toast.LENGTH_SHORT).show();
            }
            else
            {
                while (cursor.moveToNext());
                listItem.add(cursor.getString(1));

                adapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,listItem);
                userlist.setAdapter(adapter);
            }
    }*/

}