package com.example.admin.newproj;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class all_users extends AppCompatActivity {

    String [] firstname,lastname,email,phonenumber,password;
    ListView mylistview;
    SQLiteDatabase mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_users);

    mylistview=(ListView)findViewById(R.id.listview1);

        mylistview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String firstname = ((TextView) view.findViewById(R.id.tv_myview_name)).getText().toString();
                String email = ((TextView)view.findViewById(R.id.tv_myview_email)).getText().toString();
                String phone =((TextView)view.findViewById(R.id.tv_myview_phone)).getText().toString();
                String password = ((TextView)view.findViewById(R.id.tv_myview_phone)).getText().toString();

                Toast.makeText(getApplicationContext(),"You've clicked "+ firstname
                +" "+ email+ " "+ phone + "." +toString(),Toast.LENGTH_LONG).show();

            }

        });
        getusers();

        Custom_adapter obj = new Custom_adapter(this,firstname,lastname,email,phonenumber,password);
        mylistview.setAdapter(obj);


    }

    private void getusers(){
        try{
           mydb= openOrCreateDatabase("usersdb",
                    Context.MODE_PRIVATE, null);
            mydb.execSQL("Create table if not exists " +
                    "tbl_users " +
                    "(firstname varchar, " +
                    "lastname varchar, " +
                    "email varchar, " +
                    "phone varchar, " +
                    "password varchar);");
            Cursor cursor = mydb.rawQuery("SELECT * FROM usersdb", null);
            if (cursor.moveToFirst()) {
                firstname = new String[cursor.getCount()];
               // lastname = new String[cursor.getCount()];
                email = new String[cursor.getCount()];
                phonenumber = new String[cursor.getCount()];
                password = new String[cursor.getCount()];


                int i = 0;
                do {
                    firstname[i] = cursor.getString(0)
                            + " " + cursor.getString(1)
                            + " " + cursor.getString(2);
                    email[i] = cursor.getString(3);
                    phonenumber[i] = cursor.getString(4);
                    password[i] = cursor.getString(4);

                    i++;
                } while (cursor.moveToNext());



        }
        }
        catch (Exception e){
            Toast.makeText(getApplicationContext(),"Sorry an error occured" +toString(),Toast.LENGTH_LONG).show();
        }
    }

    public void Createpopupuser(String usernamex,final String phonex,String emailx){
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View promptview= LayoutInflater.inflate(R.layout.popupuser,null);

    }
}
