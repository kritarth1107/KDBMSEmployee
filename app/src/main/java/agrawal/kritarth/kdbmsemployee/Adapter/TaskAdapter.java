package agrawal.kritarth.kdbmsemployee.Adapter;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.HashMap;
import java.util.List;

import agrawal.kritarth.kdbmsemployee.EmpActivity;
import agrawal.kritarth.kdbmsemployee.R;
import agrawal.kritarth.kdbmsemployee.model.Employee;
import agrawal.kritarth.kdbmsemployee.model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private Context mContext;
    private List<Task> mTask;

    public TaskAdapter(Context mContext, List<Task> mTask){
        this.mTask = mTask;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.task_list, parent, false);
        return new TaskAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final Task task = mTask.get(position);
        holder.date.setText(task.getAssign_date());
        holder.task.setText(task.getTask());
        holder.target.setText(task.getTarget_days()+" Days");
        holder.CmpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Mark Completed");
                builder.setMessage("Are you sure you want to mark as Completed");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tasks");
                        /*String key = reference.push().getKey();
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("completed",true);
                        reference.child(key).updateChildren(hashMap);*/

                        ValueEventListener valueEventListener =  reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    Task task1 = snapshot.getValue(Task.class);
                                    String tassk = task1.getTask();
                                    if(holder.task.getText().equals(tassk)){
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("completed","true");
                                        snapshot.getRef().updateChildren(hashMap);

                                    }


                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }


                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });


        holder.NotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Mark Completed");
                builder.setMessage("Are you sure you want to mark as Not Completed");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tasks");
                        /*String key = reference.push().getKey();
                        HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("completed",true);
                        reference.child(key).updateChildren(hashMap);*/

                        ValueEventListener valueEventListener =  reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    Task task1 = snapshot.getValue(Task.class);
                                    String tassk = task1.getTask();
                                    if(holder.task.getText().equals(tassk)){
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("completed","false");
                                        snapshot.getRef().updateChildren(hashMap);

                                    }


                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }


                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mTask.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView date,target;
        public TextView task;
        public Button CmpButton,NotButton;
        public ViewHolder(View itemView) {
            super(itemView);
            date =itemView.findViewById(R.id.dateTV);
            task = itemView.findViewById(R.id.taskTextView);
            target = itemView.findViewById(R.id.TargetDaysTV);
            CmpButton = itemView.findViewById(R.id.CompletedButton);
            NotButton = itemView.findViewById(R.id.NotButton);

        }
    }



}