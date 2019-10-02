package com.example.diu.assingment;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ticket extends AppCompatActivity {
    RadioGroup br,bn;
    EditText pn,SEAT;
    Button orderbutton;
    RadioButton rbr,rbn,ac,nonac;
    TextView D;
    String route,bus,phonenumberstring,timeString,d,taka,totaltaka,Seat;
    int distanceInt,time;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        D=findViewById(R.id.d);
        d=D.getText().toString();

        br=findViewById(R.id.busroute);
        bn=findViewById(R.id.busname);
        ac=findViewById(R.id.ac);
        nonac=findViewById(R.id.nonac);
        orderbutton=findViewById(R.id.order);
        pn=findViewById(R.id.pnumber);
       SEAT=findViewById(R.id.seat);




        orderbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String typeString,resulttaka;
                    //select bus route
                    int routeinfo=br.getCheckedRadioButtonId();
                    rbr=(RadioButton)findViewById(routeinfo);
                    route=rbr.getText().toString();
//                    Log.i("problem",route);
                    //bus name
                    int busname=bn.getCheckedRadioButtonId();
                    rbn=findViewById(busname);
                    bus=rbn.getText().toString();
 //                   Log.i("problem",bus);
                    phonenumberstring=pn.getText().toString();
 //                   Log.i("problem",phonenumberstring);

 //                   Log.i("problem",d);
                    distanceInt=Integer.parseInt(d);
                    time=(30*distanceInt)/5;
                    timeString=String.valueOf(time);
 //                   Log.i("problem",timeString);

                    Seat=SEAT.getText().toString();

                    if (ac.isChecked()){
                        taka="700";
//                        Log.i("problem",ac.getText().toString());
//                        Log.i("problem",Seat);
//                        Log.i("problem",taka);
//                        Log.i("problem",amount(taka));
                       nonac.setChecked(true);


 //                       Log.i("problem",resulttaka);

                        typeString=ac.getText().toString();
                        resulttaka=amount(taka);
                        Log.i("problem",typeString);
                        Log.i("problem",resulttaka);


                    }
                    else{
                        taka="500";
//                        Log.i("problem",nonac.getText().toString());
//                        Log.i("problem",Seat);
//                        Log.i("problem",taka);
                        ac.setChecked(true);
                        typeString=ac.getText().toString();

                        resulttaka=(String)amount(taka);
                        Log.i("problem",typeString);
                        Log.i("problem",resulttaka);

                    }
                    Intent it=new Intent(ticket.this,YourPurchase.class);
                    it.putExtra("userroot",route);
                    it.putExtra("bus",bus);
                    it.putExtra("phone",phonenumberstring);
                    it.putExtra("time",timeString);
                    it.putExtra("distance",d);
                    it.putExtra("type",typeString);
                    it.putExtra("seat",Seat);
                    it.putExtra("pay",taka);
                    it.putExtra("paytotal",resulttaka);
                    startActivity(it);


                }catch (Exception e){
                    Toast.makeText(ticket.this, "not work", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

    private String amount(String Taka) {
        int t=Integer.parseInt(Taka);
        int r=Integer.parseInt(Seat);
        int res=t*r;
        return totaltaka=String.valueOf(res);
    }
}
