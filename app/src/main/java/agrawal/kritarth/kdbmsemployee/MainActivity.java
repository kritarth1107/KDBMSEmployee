package agrawal.kritarth.kdbmsemployee;

import android.content.Intent;
import androidx.annotation.NonNull;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import agrawal.kritarth.kdbmsemployee.Adapter.EmployeeAdapter;
import agrawal.kritarth.kdbmsemployee.model.Employee;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    FloatingActionButton fab,fab2;
    private RecyclerView recyclerView;
    ShimmerFrameLayout shimmerFrameLayout;
    private DrawerLayout drawer;
    private EmployeeAdapter employeeAdapter;
    private List<Employee> mEmployee;
    CardView AttendanceCV, ReportCV, TaskCV;
    String date,day,month,year;
    TextView notfounderror;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        notfounderror = findViewById(R.id.notfounderror);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //Date Setup
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        date = df.format(Calendar.getInstance().getTime());
        DateFormat df1 = new SimpleDateFormat("dd");
        day = df1.format(Calendar.getInstance().getTime());
        DateFormat df2 = new SimpleDateFormat("MMM");
        month = df2.format(Calendar.getInstance().getTime());
        DateFormat df3 = new SimpleDateFormat("yyyy");
        year = df3.format(Calendar.getInstance().getTime());

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
                                        String empName = employee.getName();
                                        String empCounter = employee.getCounter();
                                        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Attendance");
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("empid", empId);
                                        hashMap.put("empname", empName);
                                        hashMap.put("counter", empCounter);
                                        hashMap.put("attendance", "NotSet");
                                        hashMap.put("date",date);
                                        hashMap.put("day",day);
                                        hashMap.put("month",month);
                                        hashMap.put("year",year);

                                        reference2.child(date).child("Attendance").push().setValue(hashMap);

                                        final DatabaseReference rf=FirebaseDatabase.getInstance().getReference("Attendance-Report").child("2019");
                                        rf.addListenerForSingleValueEvent(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                if(dataSnapshot.hasChild("Aug")){}
                                                else{
                                                    Employee employee = dataSnapshot.getValue(Employee.class);
                                                    String empId = employee.getMobile();
                                                    String empName = employee.getName();
                                                    String empCounter = employee.getCounter();
                                                    HashMap<String, Object> hashMap = new HashMap<>();
                                                    hashMap.put("empid",empId);
                                                    hashMap.put("empname",empName);
                                                    hashMap.put("empcounter",empCounter);
                                                    hashMap.put("present",0);
                                                    hashMap.put("absent",0);
                                                    rf.child("Aug").child("Report").push().setValue(hashMap);
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });

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

        TaskCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,TaskAssignActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });
        ReportCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReportActivity.class);
                intent.putExtra("date",date);
                startActivity(intent);
            }
        });

    }

    private void readEmployees(){
        final int[] i = {0};
        Query reference = FirebaseDatabase.getInstance().getReference().child("Employee").orderByChild("counter");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mEmployee = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Employee s = dataSnapshot1.getValue(Employee.class);
                    mEmployee.add(s);
                    i[0]++;
                }
                if(i[0]==0){
                    notfounderror.setVisibility(View.VISIBLE);
                    shimmerFrameLayout.stopShimmerAnimation();
                    shimmerFrameLayout.setVisibility(View.GONE);
                }
                else{
                    notfounderror.setVisibility(View.GONE);
                    employeeAdapter = new EmployeeAdapter(MainActivity.this, mEmployee);
                    recyclerView.setAdapter(employeeAdapter);
                    shimmerFrameLayout.stopShimmerAnimation();
                    shimmerFrameLayout.setVisibility(View.GONE);
                }



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
            case R.id.nav_Attendance:
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
                                        String empName = employee.getName();
                                        String empCounter = employee.getCounter();
                                        DatabaseReference reference2 = FirebaseDatabase.getInstance().getReference("Attendance");
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("empid", empId);
                                        hashMap.put("empname", empName);
                                        hashMap.put("counter", empCounter);
                                        hashMap.put("attendance", "NotSet");
                                        hashMap.put("date",date);
                                        hashMap.put("day",day);
                                        hashMap.put("month",month);
                                        hashMap.put("year",year);

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
                break;
            case R.id.nav_assignTask:
                Intent intent1 = new Intent(MainActivity.this,TaskAssignActivity.class);
                startActivity(intent1);
                break;
                case R.id.nav_Feedback:
                    Intent intent2 = new Intent(MainActivity.this,FeedbackActivity.class);
                    startActivity(intent2);
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
