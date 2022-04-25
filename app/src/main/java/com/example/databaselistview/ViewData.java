package com.example.databaselistview;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewData extends AppCompatActivity {
    ListView listView;
    DBHelper myDB;
    private Object ListView=listView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView=findViewById(R.id.list_item);
        myDB=new DBHelper(this);

        ArrayList<String> theList = new ArrayList<>();
        Cursor data=myDB.getdata();

        if(data.getCount()==0)
        {
            Toast.makeText(ViewData.this, "Database is empty", Toast.LENGTH_SHORT).show();
        }
        else
        {
            while(data.moveToNext())
            {
                theList.add("Name :"+data.getString(0)+"\n");
                theList.add("Sur Name :"+data.getString(1)+"\n");
                theList.add("Contact :"+data.getString(2)+"\n");
                theList.add("Date of Birth :"+data.getString(3)+"\n");
                theList.add("Department :"+data.getString(4)+"\n");
                theList.add("Institute :"+data.getString(5)+"\n");

                ListAdapter listAdapter=new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,theList);
                listView.setAdapter(listAdapter);
            }
        }
    }
}
