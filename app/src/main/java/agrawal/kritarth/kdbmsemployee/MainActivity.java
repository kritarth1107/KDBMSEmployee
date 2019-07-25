package agrawal.kritarth.kdbmsemployee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import agrawal.kritarth.kdbmsemployee.Adapter.EmployeeAdapter;
import agrawal.kritarth.kdbmsemployee.model.Employee;

import static java.security.AccessController.getContext;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab,fab2;
    private RecyclerView recyclerView;
    ShimmerFrameLayout shimmerFrameLayout;
    private DrawerLayout drawer;
    private EmployeeAdapter employeeAdapter;
    private List<Employee> mEmployee;
    CardView AttendanceCV, ReportCV, TaskCV;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        date = df.format(Calendar.getInstance().getTime());

        AttendanceCV = findViewById(R.id.AttendanceCardView);
        ReportCV = findViewById(R.id.ReportCardView);
        TaskCV = findViewById(R.id.TaskCardView);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewEmployeeActivity.class);
                startActivity(intent);
            }
        });
        fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,NewCounterActivity.class);
                startActivity(intent);
            }
        });

        mEmployee = new ArrayList<>();
        readEmployees();

        shimmerFrameLayout = findViewById(R.id.shimmer_view_container);
        shimmerFrameLayout.startShimmerAnimation();

        AttendanceCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference("Attendance");
                rootRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        if (snapshot.hasChild(date)) {
                            // run some code
                            Intent intent = new Intent(MainActivity.this,AttendanceActivity.class);
                            intent.putExtra("date",date);
                            startActivity(intent);
                        }
                        else{
                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Employee");

                            reference.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                        Employee employee = snapshot.getValue(Employee.class);
                                        String empId = employee.getMobile();
                                        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Attendance");
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("empid", empId);
                                        hashMap.put("attendance", "NotSet");

                                        reference2.child(date).child("Attendance").push().setValue(hashMap);
                                    }

                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            Toast.makeText(MainActivity.this, "Data Created: Click Again", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }
        });

    }

    private void readEmployees(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Employee");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Employee s = dataSnapshot1.getValue(Employee.class);
                    mEmployee.add(s);
                }
                employeeAdapter = new EmployeeAdapter(MainActivity.this, mEmployee);
                recyclerView.setAdapter(employeeAdapter);
                shimmerFrameLayout.stopShimmerAnimation();
                shimmerFrameLayout.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "Oopps", Toast.LENGTH_LONG);

            }
        });

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_newEmp:
                Intent i =new Intent(MainActivity.this,NewEmployeeActivity.class);
                startActivity(i);
                break;
            case R.id.nav_newCounter:
                Intent intent =new Intent(MainActivity.this,NewCounterActivity.class);
                startActivity(intent);
                break;

        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }
}