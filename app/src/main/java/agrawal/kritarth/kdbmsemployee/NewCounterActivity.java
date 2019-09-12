package agrawal.kritarth.kdbmsemployee;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;

public class NewCounterActivity extends AppCompatActivity {
    MaterialEditText counter_name;
    Button btn_counter;
    ProgressBar NewProgressBar2;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_counter);
        NewProgressBar2 = findViewById(R.id.NewProgressBar2);
        NewProgressBar2.setVisibility(View.GONE);
        counter_name = findViewById(R.id.counter_name);
        btn_counter = findViewById(R.id.btn_counter);
        btn_counter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = counter_name.getText().toString().trim();
                if(title.isEmpty()){
                    btn_counter.setError("Fill Counter Title");
                    Toast.makeText(NewCounterActivity.this, "All Fields Are Required", Toast.LENGTH_SHORT).show();
                }
                else{
                    NewProgressBar2.setVisibility(View.VISIBLE);
                    btn_counter.setVisibility(View.GONE);
                    addCounter(title);

                }
            }
        });

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
    public void addCounter(String title){
        reference = FirebaseDatabase.getInstance().getReference("Counter");

        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("counter", title);

        reference.push().setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){

                    counter_name.setText("");

                    Toast.makeText(NewCounterActivity.this, "Counter Added Succesfully", Toast.LENGTH_SHORT).show();
                    Intent intent =new Intent(NewCounterActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    btn_counter.setVisibility(View.VISIBLE);
                    NewProgressBar2.setVisibility(View.GONE);
                    Toast.makeText(NewCounterActivity.this, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
