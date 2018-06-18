package com.example.admin.newproj;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by ADMIN on 6/18/2018.
 */

public class Custom_adapter extends ArrayAdapter <String> {
    private final Activity Context;
    private final String[] firstname;
    private final String [] lastname;
    private final String [] email;
    private final String [] phone;
    private final String [] password;

    public  Custom_adapter(Activity Context,String[]firstname,String[]lastname,
                           String[] email,String[] phone, String []password){
        super(Context, R.layout.users_list, firstname);

        this.Context=Context;
        this.firstname=firstname;
        this.lastname=lastname;
        this.email=email;
        this.phone=phone;
        this.password=password;


    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater Inflator = Context.getLayoutInflater();
        View myView = Inflator.inflate(R.layout.users_list, null, true);

        TextView tv_myview_name = (TextView)
                myView.findViewById(R.id.tv_myview_name);
        tv_myview_name.setText(firstname[position]);

        TextView tv_myview_email = (TextView)
                myView.findViewById(R.id.tv_myview_email);
        tv_myview_name.setText(email[position]);

        TextView tv_myview_phone = (TextView)
                myView.findViewById(R.id.tv_myview_phone);
        tv_myview_name.setText(phone[position]);

        TextView tv_myview_password = (TextView)
                myView.findViewById(R.id.tv_myview_password);
        tv_myview_name.setText(password[position]);


        return  myView;

    }
}
