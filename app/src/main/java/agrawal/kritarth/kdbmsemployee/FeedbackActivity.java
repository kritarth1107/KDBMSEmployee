package agrawal.kritarth.kdbmsemployee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hsalf.smilerating.BaseRating;
import com.hsalf.smilerating.SmileRating;
import com.katepratik.msg91api.MSG91;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener {

    LinearLayout RatingLL;
    int i=0,j=0,p=0,q=0,r=0;
    RelativeLayout Rating1,Rating2,Rating3,Rating4,Rating5,Rating6,Rating7,Rating8,Rating9,Rating10;
    LinearLayout Reason1,Reason2,Reason3,Reason4,Reason5,Reason6;
    RelativeLayout yes1,yes2,yes3,no1,no2,no3;
    RelativeLayout r1,r2,r3,r4,r5,r6,r7,r8,r9,r10;
    Button SubmitButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        RatingLL = findViewById(R.id.RatingLL);
        SubmitButton = findViewById(R.id.SubmitButton);
        SubmitButton.setOnClickListener(this);
        Rating1=findViewById(R.id.Rating1);
        Rating1.setOnClickListener(this);

        Rating2=findViewById(R.id.Rating2);
        Rating2.setOnClickListener(this);

        Rating3=findViewById(R.id.Rating3);
        Rating3.setOnClickListener(this);

        Rating4=findViewById(R.id.Rating4);
        Rating4.setOnClickListener(this);

        Rating5=findViewById(R.id.Rating5);
        Rating5.setOnClickListener(this);

        Rating6=findViewById(R.id.Rating6);
        Rating6.setOnClickListener(this);

        Rating7=findViewById(R.id.Rating7);
        Rating7.setOnClickListener(this);

        Rating8=findViewById(R.id.Rating8);
        Rating8.setOnClickListener(this);

        Rating9=findViewById(R.id.Rating9);
        Rating9.setOnClickListener(this);

        Rating10=findViewById(R.id.Rating10);
        Rating10.setOnClickListener(this);

        Reason1=findViewById(R.id.Reason1);
        Reason1.setOnClickListener(this);
        Reason2=findViewById(R.id.Reason2);
        Reason2.setOnClickListener(this);
        Reason3=findViewById(R.id.Reason3);
        Reason3.setOnClickListener(this);
        Reason4=findViewById(R.id.Reason4);
        Reason4.setOnClickListener(this);
        Reason5=findViewById(R.id.Reason5);
        Reason5.setOnClickListener(this);
        Reason6=findViewById(R.id.Reason6);
        Reason6.setOnClickListener(this);

        yes1 = findViewById(R.id.yes1);
        yes1.setOnClickListener(this);
        yes2 = findViewById(R.id.yes2);
        yes2.setOnClickListener(this);
        yes3 = findViewById(R.id.yes3);
        yes3.setOnClickListener(this);

        no1 = findViewById(R.id.no1);
        no1.setOnClickListener(this);
        no2 = findViewById(R.id.no2);
        no2.setOnClickListener(this);
        no3 = findViewById(R.id.no3);
        no3.setOnClickListener(this);

        r1=findViewById(R.id.r1);
        r2=findViewById(R.id.r2);
        r3=findViewById(R.id.r3);
        r4=findViewById(R.id.r4);
        r5=findViewById(R.id.r5);
        r6=findViewById(R.id.r6);
        r7=findViewById(R.id.r7);
        r8=findViewById(R.id.r8);
        r9=findViewById(R.id.r9);
        r10=findViewById(R.id.r10);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Rating1:
                if(i==0) {
                    i=1;
                    r1.setBackground(getResources().getDrawable(R.drawable.circle_green));
                }
                else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Rating2:
                if(i==0){
                    r2.setBackground(getResources().getDrawable(R.drawable.circle_green));
                    i=2;
                }else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.Rating3:
                if(i==0){
                    r3.setBackground(getResources().getDrawable(R.drawable.circle_green));
                    i=3;
                }else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Rating4:
                if(i==0){
                    r4.setBackground(getResources().getDrawable(R.drawable.circle_green));
                    i=4;
                }else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Rating5:
                if(i==0){
                    r5.setBackground(getResources().getDrawable(R.drawable.circle_green));
                    i=5;
                }else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Rating6:
                if(i==0){
                    r6.setBackground(getResources().getDrawable(R.drawable.circle_green));
                    i=6;
                }else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Rating7:
                if(i==0){
                    r7.setBackground(getResources().getDrawable(R.drawable.circle_green));
                    i=7;
                }else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Rating8:
                if(i==0){
                    r8.setBackground(getResources().getDrawable(R.drawable.circle_green));
                    i=8;
                }else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Rating9:
                if(i==0){
                    r9.setBackground(getResources().getDrawable(R.drawable.circle_green));
                    i=9;
                }else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.Rating10:
                if(i==0){
                    r10.setBackground(getResources().getDrawable(R.drawable.circle_green));
                    i=10;
                }else{
                    Toast.makeText(this, "Sorry! you already Rated us "+i, Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.Reason1:
                if(i==0){
                    Toast.makeText(this, "Please Rate us First 1-10", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(j==0){
                        j=1;
                        Reason1.setBackgroundColor(getResources().getColor(R.color.background));
                    }
                    else{
                        Toast.makeText(this, "Sorry! you already Selected", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.Reason2:
                 if(i==0){
                    Toast.makeText(this, "Please Rate us First 1-10", Toast.LENGTH_SHORT).show();
                }
                else{
                     if(j==0){
                         j=2;
                         Reason2.setBackgroundColor(getResources().getColor(R.color.background));
                     }
                     else{
                         Toast.makeText(this, "Sorry! you already Selected", Toast.LENGTH_SHORT).show();
                     }
                }
                break;
            case R.id.Reason3:
                 if(i==0){
                    Toast.makeText(this, "Please Rate us First 1-10", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(j==0){
                        j=3;
                        Reason3.setBackgroundColor(getResources().getColor(R.color.background));
                    }
                    else{
                        Toast.makeText(this, "Sorry! you already Selected", Toast.LENGTH_SHORT).show();
                    }

                }
                break;
            case R.id.Reason4:
                 if(i==0){
                    Toast.makeText(this, "Please Rate us First 1-10", Toast.LENGTH_SHORT).show();
                }
                else{
                     if(j==0){
                         j=4;
                         Reason4.setBackgroundColor(getResources().getColor(R.color.background));
                     }
                     else{
                         Toast.makeText(this, "Sorry! you already Selected", Toast.LENGTH_SHORT).show();
                     }
                }
                break;
            case R.id.Reason5:
                 if(i==0){
                    Toast.makeText(this, "Please Rate us First 1-10", Toast.LENGTH_SHORT).show();
                }
                else{
                     if(j==0){
                         j=5;
                         Reason5.setBackgroundColor(getResources().getColor(R.color.background));
                     }
                     else{
                         Toast.makeText(this, "Sorry! you already Selected", Toast.LENGTH_SHORT).show();
                     }
                }
                break;
            case R.id.Reason6:
                 if(i==0){
                    Toast.makeText(this, "Please Rate us First 1-10", Toast.LENGTH_SHORT).show();
                }
                else{
                     if(j==0){
                         j=6;
                         Reason6.setBackgroundColor(getResources().getColor(R.color.background));
                     }
                     else{
                         Toast.makeText(this, "Sorry! you already Selected", Toast.LENGTH_SHORT).show();
                     }
                }
                break;

            case R.id.yes1:
                if(j==0){
                    Toast.makeText(this, "Please select Reason for Rating", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(p==0){
                        yes1.setBackground(getResources().getDrawable(R.drawable.circle_green));
                        p=1;
                    }
                    else{
                        Toast.makeText(this, "Already Selected", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.no1:
                if(j==0){
                    Toast.makeText(this, "Please select Reason for Rating", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(p==0){
                        no1.setBackground(getResources().getDrawable(R.drawable.circle_red));
                        p=2;
                    }
                    else{
                        Toast.makeText(this, "Already Selected", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.yes2:
                if(j==0){
                    Toast.makeText(this, "Please select Reason for Rating", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(q==0){
                        yes2.setBackground(getResources().getDrawable(R.drawable.circle_green));
                        q=1;
                    }
                    else{
                        Toast.makeText(this, "Already Selected", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.no2:
                if(j==0){
                    Toast.makeText(this, "Please select Reason for Rating", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(q==0){
                        no2.setBackground(getResources().getDrawable(R.drawable.circle_red));
                        q=2;
                    }
                    else{
                        Toast.makeText(this, "Already Selected", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.yes3:
                if(j==0){
                    Toast.makeText(this, "Please select Reason for Rating", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(r==0){
                        yes3.setBackground(getResources().getDrawable(R.drawable.circle_green));
                        r=1;
                    }
                    else{
                        Toast.makeText(this, "Already Selected", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.no3:
                if(j==0){
                    Toast.makeText(this, "Please select Reason for Rating", Toast.LENGTH_SHORT).show();
                }
                else{

                    if(r==0){
                        no3.setBackground(getResources().getDrawable(R.drawable.circle_red));
                        r=2;
                    }
                    else{
                        Toast.makeText(this, "Already Selected", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.SubmitButton:
                if(i==0||j==0||p==0||q==0||r==0){
                    Toast.makeText(this, "All Fields Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    Intent intent = new Intent(FeedbackActivity.this,Feedback2.class);

                    intent.putExtra("i",String.valueOf(i));
                    intent.putExtra("j",String.valueOf(j));
                    intent.putExtra("p",String.valueOf(p));
                    intent.putExtra("q",String.valueOf(q));
                    intent.putExtra("r",String.valueOf(r));
                    startActivity(intent);
                    overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                    finish();
                }
                break;
        }
    }
}
