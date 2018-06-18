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

import static com.example.admin.newproj.R.id.edt_email;
import static com.example.admin.newproj.R.id.edt_fname;
import static com.example.admin.newproj.R.id.edt_lname;
import static com.example.admin.newproj.R.id.edt_password;
import static com.example.admin.newproj.R.id.edt_phone;

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
    String edit_fname,edit_lname,edit_email,edit_phone,edit_password;
    Button login;
    FloatingActionButton save;
    SQLiteDatabase mydb;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        this.getSupportActionBar().hide();

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.wave_scale);
        animation.setDuration(600);
        mylayout.startAnimation(animation);

        mytextview = (TextView) findViewById(R.id.textView);
        mylayout = (LinearLayout) findViewById(R.id.activity_dashboard);
        firstname = (EditText) findViewById(edt_fname);
        lastname = (EditText) findViewById(edt_lname);
        email = (EditText) findViewById(edt_email);
        phone = (EditText) findViewById(edt_phone);
        password = (EditText) findViewById(edt_password);
        male = (RadioButton) findViewById(R.id.radio_male);
        female = (RadioButton) findViewById(R.id.radio_female);
        login = (Button) findViewById(R.id.btn_continue);
        save = (FloatingActionButton) findViewById(R.id.float_save);

        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.wave_scale);
        animation1.setDuration(600);
        mytextview.startAnimation(animation1);


        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


              edit_fname=firstname.getText().toString();
               edit_lname=lastname.getText().toString();
                edit_email=email.getText().toString();
                edit_phone = phone.getText().toString();
                edit_password = password.getText().toString();



                Toast.makeText(getApplicationContext(), "This is " + edt_fname + " " + edt_lname
                        + toString(), Toast.LENGTH_LONG).show();
                firstname.setText("");
                lastname.setText("");
                email.setText("");
                phone.setText("");
                password.setText("");
                savedata(edit_fname, edit_lname, edit_email, edit_phone, edit_password);
            }
        });

    }



    public  void savedata(String edit_fname, String edit_lname, String edit_email, String edit_phone,
    String edit_password){
        try{
           mydb= openOrCreateDatabase("usersdb", Context.MODE_PRIVATE,null
           ) ;
            mydb.execSQL("Create table if not exists "+
            "tbl_users " +
                "(edit_fname varchar, " +
                    "edit_lname varchar, "+
                    "edit_email varchar, "+
                    "edit_phone vachar, "+
                    "edit_password varchar);"
            );

            mydb.execSQL("Insert into tbl_users "+
                    "values(" +
                    "'" +  edit_fname +"'," +
                    "'" + edit_lname +"'," +
                    "'" + edit_email +"'," +
                    "'" + edit_phone + "'," +
                    "'" + edit_password + "');");
        }
        catch (Exception e){

        }


    }
}
