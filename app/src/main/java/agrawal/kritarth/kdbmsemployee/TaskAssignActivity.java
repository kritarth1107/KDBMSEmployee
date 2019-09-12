package agrawal.kritarth.kdbmsemployee;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskAssignActivity extends AppCompatActivity {
    Spinner spinner;
    DatabaseReference mDatabase,reference;
    Button assignTask;
    EditText taskET,target;
    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_assign);
        taskET = findViewById(R.id.taskET);
        mDatabase = FirebaseDatabase.getInstance().getReference().child("Employee");
        spinner = findViewById(R.id.taskSpinner);
        assignTask = findViewById(R.id.AssignButton);
        target = findViewById(R.id.targetET);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // and this
                finish();
            }
        });
        Intent i = getIntent();
        date = i.getStringExtra("date");

        Query query = mDatabase.orderByChild("counter");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String emp_name = dataSnapshot1.child("name").getValue(String.class);
                    String counter_name = dataSnapshot1.child("counter").getValue(String.class);
                    titleList.add(emp_name);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(TaskAssignActivity.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(TaskAssignActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });

        assignTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String taskk = taskET.getText().toString().trim();
                String emp = spinner.getSelectedItem().toString().trim();
                String taarget = target.getText().toString().trim();
                if(taskET.length()<5){taskET.setError("आगे बढ़ने से पहले कृपया इस फ़ील्ड को भरें");}
                else if(taarget.isEmpty()){
                    Toast.makeText(TaskAssignActivity.this, "आगे बढ़ने से पहले कृपया इस फ़ील्ड को भरें", Toast.LENGTH_SHORT).show();
                }
                else{
                    assigntask(taskk,emp,date,taarget);
                }

            }
        });
    }

    //Assign New Task Function
    private void assigntask(final String task, String emp, String date,String tg){

        reference = FirebaseDatabase.getInstance().getReference("Tasks");

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("task", task);
        hashMap.put("employee", emp);
        hashMap.put("assign_date", date);
        hashMap.put("target_days",tg);
        hashMap.put("completed","NotSet");

        reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    finish();
                    Toast.makeText(TaskAssignActivity.this, "Task Assigned Succesfully", Toast.LENGTH_SHORT).show();

                }
                else{
                    finish();
                    Toast.makeText(TaskAssignActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
