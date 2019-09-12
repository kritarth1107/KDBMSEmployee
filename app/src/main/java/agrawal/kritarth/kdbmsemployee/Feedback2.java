package agrawal.kritarth.kdbmsemployee;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

public class Feedback2 extends AppCompatActivity {
    Intent i;
    EditText dob,name,mobile;
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback2);
        i = getIntent();
        final String rating = i.getStringExtra("i");
        final String reason = i.getStringExtra("j");
        final String que1 = i.getStringExtra("p");
        final String que2 = i.getStringExtra("q");
        final String que3 = i.getStringExtra("r");
        dob = findViewById(R.id.bday);
        name = findViewById(R.id.cName);
        mobile = findViewById(R.id.cNumber);
        submit = findViewById(R.id.submit);
        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel(dob, myCalendar);
            }

        };
        dob.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(Feedback2.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String c_name = name.getText().toString().trim();
                final String c_mobile = mobile.getText().toString().trim();
                final String c_dob = dob.getText().toString().trim();

                if (c_name.isEmpty()) {
                    name.setError("Required");
                } else if (c_mobile.isEmpty()) {
                    mobile.setError("Required");
                } else if (c_dob.isEmpty()) {
                    dob.setError("Required");
                } else {
                    DatabaseReference reference = FirebaseDatabase.getInstance().getReference("feedback");

                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("name", c_name);
                    hashMap.put("mobile", "+91 " + c_mobile);
                    hashMap.put("dob", c_dob);
                    hashMap.put("aniversary", "00-abc-0000");
                    hashMap.put("rating", rating + "-" + reason + "-" + que1 + "-" + que2 + "-" + que3);


                    reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                startActivity(new Intent(Feedback2.this,FeedbackActivity.class));
                                overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out);
                                finish();
                                Toast.makeText(Feedback2.this, "Thank you for Rating us", Toast.LENGTH_SHORT).show();

                            } else {

                                Toast.makeText(Feedback2.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
            }
        });

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

    }

    private void updateLabel(EditText edittext,Calendar myCalendar) {
        String myFormat = "dd-MMM-YYYY"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));
    }

}
