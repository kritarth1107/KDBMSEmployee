package agrawal.kritarth.kdbmsemployee;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import agrawal.kritarth.kdbmsemployee.model.Employee;

public class NewEmployeeActivity extends AppCompatActivity {

    MaterialEditText emp_name, emp_mobile;
    Button btn_add;
    ProgressBar progressBar;
    FirebaseAuth auth;
    DatabaseReference reference,mDatabase;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_employee);

        //Get Elements By ID
        emp_mobile = findViewById(R.id.emp_mobile);
        emp_name = findViewById(R.id.emp_name);
        btn_add = findViewById(R.id.btn_add);
        progressBar = findViewById(R.id.NewProgressBar);


        //Firebase Auth
        auth = FirebaseAuth.getInstance();

        progressBar.setVisibility(View.GONE);
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Counter");
        spinner = findViewById(R.id.spinner);

        Query query = mDatabase.orderByChild("counter");
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final List<String> titleList = new ArrayList<String>();
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
                    String titlename = dataSnapshot1.child("counter").getValue(String.class);
                    titleList.add(titlename);
                }
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(NewEmployeeActivity.this, android.R.layout.simple_spinner_item, titleList);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(NewEmployeeActivity.this,databaseError.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = emp_name.getText().toString().trim();
                String mobile = emp_mobile.getText().toString().trim();

                String counter_s = spinner.getSelectedItem().toString().trim();
                if(name.isEmpty()){
                    emp_name.setError("Fill Name");
                    Toast.makeText(NewEmployeeActivity.this, "All fileds are required", Toast.LENGTH_SHORT).show();
                }
                else if(mobile.isEmpty()){
                    emp_mobile.setError("Fill Mobile");
                    Toast.makeText(NewEmployeeActivity.this, "All fileds are required", Toast.LENGTH_SHORT).show();

                }

                else{
                        btn_add.setVisibility(View.GONE);
                        progressBar.setVisibility(View.VISIBLE);
                        register(name,mobile,counter_s);
                }
            }
        });
    }

    //Register New Employee Function
    private void register(final String name, String mobile, String counter){

        reference = FirebaseDatabase.getInstance().getReference("Employee").child(mobile);

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("name", name);
        hashMap.put("mobile", mobile);
        hashMap.put("counter", counter);

        reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    emp_mobile.setText("");
                    emp_name.setText("");
                    Toast.makeText(NewEmployeeActivity.this, "Employee Added Succesfully", Toast.LENGTH_SHORT).show();
                    btn_add.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }
                else{
                    btn_add.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(NewEmployeeActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
