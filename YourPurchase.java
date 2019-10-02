package com.example.diu.assingment;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.jar.Manifest;

public class YourPurchase extends AppCompatActivity {
    TextView ROUTE,TIME,DISTANCE,BUS,PHONE,TYPE,SEAT,PRIZE,Totalprize,cprize;
    String Route,Bus,Time,Distance,phone,type,seat,prize,totalprize;
    Button cv,call,sms;
    EditText message;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_your_purchase);

        ROUTE=findViewById(R.id.route);
        BUS=findViewById(R.id.bus);
        TIME=findViewById(R.id.time);
        DISTANCE=findViewById(R.id.distance);
        PHONE=findViewById(R.id.number);
        TYPE=findViewById(R.id.busType);
        SEAT=findViewById(R.id.seat);
        PRIZE=findViewById(R.id.p);
        Totalprize=findViewById(R.id.tp);
        cv=findViewById(R.id.convert);
        cprize=findViewById(R.id.pconvert);
        call=findViewById(R.id.callbutton);
        sms=findViewById(R.id.smsbutton);
        message=findViewById(R.id.sms);


        Intent it=getIntent();
         Route=it.getStringExtra("userroot");
         Bus=it.getStringExtra("bus");
         Time=it.getStringExtra("time");
         Distance=it.getStringExtra("distance");
         phone=it.getStringExtra("phone");
         type=it.getStringExtra("type");
         seat=it.getStringExtra("seat");
         prize=it.getStringExtra("pay");
         totalprize=it.getStringExtra("paytotal");




        ROUTE.setText("ROUTE :".concat(Route));
        BUS.setText("BUS : ".concat(Bus));
      DISTANCE.setText("DISTANCE :".concat(Distance).concat("KM"));
       TIME.setText("YOUR REACHED TIME : ".concat(Time).concat("min"));
       PHONE.setText("USER PHONE NO : ".concat(phone));
       TYPE.setText("BUS STATUS : ".concat(type));
       SEAT.setText("YOU SELECT SEAT :".concat(seat));
       PRIZE.setText("TICKET PRIZE : ".concat(prize).concat("per person"));
       Totalprize.setText("YOUR TOTAL PRIZE :".concat(totalprize));

       //convert button
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String TP = totalprize;
                    Log.i("problem", TP);
                    Double t = Double.parseDouble(TP);
                    Double result = t * 0.0120;
                    String r = String.valueOf(result);
                    cprize.setText("YOUR TOTAL PRIZE CONVERTED USD : ".concat(r).concat("$"));
                }catch (Exception e){
                    Log.i("problem","convert button check");

                }
            }
        });
        //end convert button
        //call button
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String no="tel:"+phone;
                Log.i("problem",no);
                Intent callIntent=new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(no));
                if (ActivityCompat.checkSelfPermission(YourPurchase.this, android.Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                    return;
                }
                startActivity(callIntent);
            }
        });
            //sms button work
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Log.i("problem",phone);
                   String SMS = message.getText().toString();
                    SmsManager sm = SmsManager.getDefault();
                    sm.sendTextMessage(phone, null, SMS, null, null);
//                    Intent msg=new Intent(Intent.ACTION_VIEW,Uri.parse("mobile : "+phoneno));
//                    msg.putExtra("sms",SMS);
//                    startActivity(msg);
                    Toast.makeText(YourPurchase.this, "sms send", Toast.LENGTH_LONG).show();
                }catch (Exception e){
                    Toast.makeText(YourPurchase.this, "sms send failed.please try again leter", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
