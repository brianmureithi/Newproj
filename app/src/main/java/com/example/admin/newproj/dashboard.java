package com.example.admin.newproj;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class dashboard extends AppCompatActivity {
    TextView mytextview;
    LinearLayout mylayout;
    EditText firstname;
    EditText lastname;
    EditText email;
    EditText phone;
    EditText password;
    RadioButton male;
    RadioButton female;
    Button login;
    FloatingActionButton save;
    SQLiteDatabase mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.getSupportActionBar().hide();

        Animation animation=AnimationUtils.loadAnimation(this,R.anim.wave_scale);
        animation.setDuration(600);
        mylayout.startAnimation(animation);

        mytextview=(TextView) findViewById(R.id.textView);
        mylayout=(LinearLayout)findViewById(R.id.activity_dashboard);
        firstname=(EditText)findViewById(R.id.edt_fname);
        lastname=(EditText)findViewById(R.id.edt_lname);
        email=(EditText)findViewById(R.id.edt_email);
        phone=(EditText)findViewById(R.id.edt_phone);
        password=(EditText)findViewById(R.id.edt_password);
        male=(RadioButton) findViewById(R.id.radio_male);
        female=(RadioButton) findViewById(R.id.radio_female);
        login=(Button) findViewById(R.id.btn_continue);
        save=(FloatingActionButton)findViewById(R.id.float_save);

        Animation animation1=AnimationUtils.loadAnimation(this,R.anim.wave_scale);
        animation1.setDuration(600);
        mytextview.startAnimation(animation1);



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_fname = firstname.getText().toString();
                edt_lname = lastname.getText().toString();
                edt_email = email.getText().toString();
                edt_phone = phone.getText().toString();
                edt_password = password.getText().toString();

                Toast.makeText(getApplicationContext(),"This is "+ edt_fname+ " "+ edt_lname
                        + toString(),Toast.LENGTH_LONG).show();
                firstname.setText("");
                lastname.setText("");
                email.setText("");
                phone.setText("");
                password.setText("");
                savedata(edt_fname,edt_lname,edt_email,edt_phone,edt_password);
            }
        });

    }

    public  void savedata(String edt_fname, String edt_lname, String edt_email, String edt_phone,
    String edt_password){
        try{
           mydb= openOrCreateDatabase("usersdb", Context.MODE_PRIVATE,null
           ) ;
            mydb.execSQL("Create table if not exists "+
            "tbl_users " +
                "(edt_fname varchar, " +
                    "edt_lname varchar, "+
                    "edt_email varchar, "+
                    "edt_phone vachar, "+
                    "edt_password varchar);"
            );

            mydb.execSQL("Insert into tbl_users "+
                    "values(" +
                    "'" +  edt_fname +"'," +
                    "'" + edt_lname +"'," +
                    "'" + edt_email +"'," +
                    "'" + edt_phone + "'," +
                    "'" + edt_password + "');");
        }
        catch (Exception e){

        }


    }
}
