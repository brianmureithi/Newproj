package com.example.admin.newproj;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.net.URI;

public class MainActivity extends AppCompatActivity {
    Button browse;
    Button call;
    Button capture;
    LinearLayout mylayout;
    ImageView myimageview;
    public static int CAMERA_REQUEST =1888;
    public static int RESULT_LOAD=1;
   String friendphone = "0798378646";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        this.getSupportActionBar().hide();

        browse=(Button) findViewById(R.id.browse);
        call=(Button) findViewById(R.id.call);
        capture=(Button)findViewById(R.id.capture);
        myimageview=(ImageView) findViewById(R.id.imageView);
        mylayout=(LinearLayout)findViewById(R.id.content_main);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.wave_scale);
        animation.setDuration(800);
        mylayout.startAnimation(animation);


        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent,RESULT_LOAD);
            }
        });

        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cameraIntent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_REQUEST);
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent= new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:" + friendphone));
                startActivity(callIntent);
            }
        });




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(MainActivity.this,dashboard.class);
                startActivity(myintent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
 try{
     if (requestCode==CAMERA_REQUEST){
         Bitmap photo = (Bitmap) data.getExtras().get("data");
         myimageview.setImageBitmap(photo);

     }
     else if (requestCode == RESULT_LOAD || requestCode == RESULT_OK || data != null){
         Uri selectedImage = data.getData();
         String[] filepath ={MediaStore.Images.Media.DATA};

         Cursor cursor = getContentResolver().query(selectedImage,filepath, null, null, null);
         cursor.moveToFirst();
         int columnIndex =cursor.getColumnIndex((filepath[0]));

         String decodableString =cursor.getString(columnIndex);
         cursor.close();
         myimageview.setImageBitmap(BitmapFactory.decodeFile(decodableString));
     }
     else{
         Toast.makeText(getApplicationContext(),"Please take an image first " + toString(),Toast.LENGTH_LONG).show();


     }
 }
 catch (Exception e){
     Toast.makeText(getApplicationContext(), " Aaargh something went terribly wrong ebu chill"+ toString(),Toast.LENGTH_SHORT).show();
 }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
