package agrawal.kritarth.kdbmsemployee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import agrawal.kritarth.kdbmsemployee.model.Employee;

public class EmpActivity extends AppCompatActivity {
    String EmpID;
    DatabaseReference reference;
    Toolbar toolbar;
    TextView EmpName,EmpPhone;
    ShimmerFrameLayout shimmerFrameLayout;

    private ViewPager mViewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp);



        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        //toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        Intent intent = getIntent();
        EmpID = intent.getStringExtra("empid");
        reference = FirebaseDatabase.getInstance().getReference("Employee").child(EmpID);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Employee employee = dataSnapshot.getValue(Employee.class);
                toolbar.setTitle(employee.getName());
                /*EmpName.setText(employee.getName());
                EmpPhone.setText(employee.getMobile());*/

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


}
