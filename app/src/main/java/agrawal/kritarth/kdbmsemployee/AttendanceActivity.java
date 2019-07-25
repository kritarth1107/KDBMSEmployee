package agrawal.kritarth.kdbmsemployee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import agrawal.kritarth.kdbmsemployee.Adapter.AttendanceAdapter;
import agrawal.kritarth.kdbmsemployee.Adapter.EmployeeAdapter;
import agrawal.kritarth.kdbmsemployee.model.Attendance;
import agrawal.kritarth.kdbmsemployee.model.Employee;

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
    }
    private void readATtEmp(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance").child(date).child("Attendance");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Attendance s = dataSnapshot1.getValue(Attendance.class);
                    mAtt.add(s);
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