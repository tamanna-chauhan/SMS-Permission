package com.acadview.androidpermission;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

   private EditText Number,Message;
   private Button Send;

   private  static  final int REQUEST_CODE_SMS = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Number = findViewById(R.id.num);
        Send = findViewById(R.id.send);


        String[] permissions = {
                Manifest.permission.SEND_SMS

        };

        if(ActivityCompat.checkSelfPermission(MainActivity.this,permissions[0])!= PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(MainActivity.this,permissions,REQUEST_CODE_SMS);
        }

        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNo = Number.getText().toString().trim();


                Random r = new Random();
                int i1 = r.nextInt(9999 - 1111) + 1111;
                String OTP = i1 + "";

                SmsManager sms = SmsManager.getDefault();
                sms.sendTextMessage(phoneNo,null,OTP,null,null);




                Intent intent = new Intent(MainActivity.this,Main2Activity.class);
                intent.putExtra("OTP_Number",OTP);
                startActivity(intent);

            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_CODE_SMS){
            if(grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(MainActivity.this,"Permission Granted",Toast.LENGTH_LONG).show();
            }else{
                Toast.makeText(MainActivity.this,"Not Granted",Toast.LENGTH_LONG).show();
            }
        }
    }
}