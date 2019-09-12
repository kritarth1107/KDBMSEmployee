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

import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import agrawal.kritarth.kdbmsemployee.Adapter.TaskAdapter;
import agrawal.kritarth.kdbmsemployee.model.Employee;
import agrawal.kritarth.kdbmsemployee.model.Task;

public class EmpActivity extends AppCompatActivity {
    String EmpID;
    DatabaseReference reference;
    Toolbar toolbar;
    String Emp,EmpPhone;
    ShimmerFrameLayout shimmerFrameLayout;
    RecyclerView RvTask;
    TaskAdapter taskAdapter;
    Task task;
    private List<Task> mTask;
    TextView EmpActivityEmpName;
    ShimmerFrameLayout mshimmerFrameLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp);
        RvTask = findViewById(R.id.RvTask);
        RvTask.setLayoutManager(new LinearLayoutManager(this));

        EmpActivityEmpName = findViewById(R.id.EmpActivityEmpName);
        Emp ="";
        mshimmerFrameLayout = findViewById(R.id.shimmer_view_container2);
        mshimmerFrameLayout.startShimmerAnimation();
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
                EmpActivityEmpName.setText(employee.getName()+" को सौंपे गए कार्यों की सूची");
                toolbar.setTitle(employee.getName());
                readTasks(employee.getName());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    private void readTasks(String EmpName){
        Query reference = FirebaseDatabase.getInstance().getReference().child("Tasks").orderByChild("employee").equalTo(EmpName);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mTask = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Task s = dataSnapshot1.getValue(Task.class);
                    if(s.getCompleted().equals("NotSet")){
                        mTask.add(s);
                    }

                }
                taskAdapter = new TaskAdapter(EmpActivity.this, mTask);
                RvTask.setAdapter(taskAdapter);
                mshimmerFrameLayout.stopShimmerAnimation();
                mshimmerFrameLayout.setVisibility(View.GONE);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(EmpActivity.this, "Oopps", Toast.LENGTH_LONG);

            }
        });

    }


}
