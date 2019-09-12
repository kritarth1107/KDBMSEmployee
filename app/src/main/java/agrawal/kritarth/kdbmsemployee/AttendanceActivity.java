package agrawal.kritarth.kdbmsemployee;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import agrawal.kritarth.kdbmsemployee.Adapter.AttendanceAdapter;
import agrawal.kritarth.kdbmsemployee.model.Attendance;

public class AttendanceActivity extends AppCompatActivity {
    String date;
    TextView dateText;
    RecyclerView RV;
    private AttendanceAdapter attendanceAdapter;
    private List<Attendance> mAtt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendance);
        Intent i = getIntent();
        date = i.getStringExtra("date");
        dateText = findViewById(R.id.dateText);
        dateText.setText(date);

        RV = findViewById(R.id.attRV);
        RV.setLayoutManager(new LinearLayoutManager(this));
        readATtEmp();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // and this
                finish();
            }
        });
    }
    private void readATtEmp(){
        Query reference = FirebaseDatabase.getInstance().getReference("Attendance").child(date).child("Attendance").orderByChild("counter");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mAtt = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Attendance s = dataSnapshot1.getValue(Attendance.class);
                    if(s.getAttendance().equals("NotSet")){
                        mAtt.add(s);
                    }

                }
                attendanceAdapter = new AttendanceAdapter(AttendanceActivity.this, mAtt);
                RV.setAdapter(attendanceAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(AttendanceActivity.this, "Oopps", Toast.LENGTH_LONG);

            }
        });

    }
}
