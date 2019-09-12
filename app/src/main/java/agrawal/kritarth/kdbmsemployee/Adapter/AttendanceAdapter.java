package agrawal.kritarth.kdbmsemployee.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import agrawal.kritarth.kdbmsemployee.R;
import agrawal.kritarth.kdbmsemployee.model.Attendance;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {
    String date;
    private Context mContext;
    private List<Attendance> mAttendance;

    public AttendanceAdapter(Context mContext, List<Attendance> mAttendance){
        this.mAttendance = mAttendance;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.attendance_list_view, parent, false);
        return new AttendanceAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        date = df.format(Calendar.getInstance().getTime());
        final Attendance attendance = mAttendance.get(position);
        holder.EmployeeName.setText(attendance.getEmpname()/*+" ("+attendance.getCounter()+")"*/);
        holder.absent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Mark Absent");
                builder.setMessage("Are Your Sure YOu want to Mark "+attendance.getEmpname()+" As ABSENT");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance").child(date).child("Attendance");

                        ValueEventListener valueEventListener =  reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    final Attendance attendance1 = snapshot.getValue(Attendance.class);
                                    String empid = attendance1.getEmpname();
                                    if(holder.EmployeeName.getText().toString().equals(empid)){
                                        final HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("attendance","Absent");
                                        snapshot.getRef().updateChildren(hashMap);

                                    }


                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Attendance-Report");
                        final HashMap<String, Object> hashMap = new HashMap<>();
                        hashMap.put("empname","Santosh");
                        hashMap.put("absent",+1);
                        databaseReference.child("2019").child("Aug").child("Report").push().setValue(hashMap);

                    }



                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });
        holder.present.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Mark Present");
                builder.setMessage("Are Your Sure You want to Mark "+attendance.getEmpname()+" As PRESENT");
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }

                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Attendance").child(date).child("Attendance");


                        ValueEventListener valueEventListener =  reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                                    Attendance attendance1 = snapshot.getValue(Attendance.class);
                                    String empid = attendance1.getEmpname();
                                    if(holder.EmployeeName.getText().equals(empid)){
                                        HashMap<String, Object> hashMap = new HashMap<>();
                                        hashMap.put("attendance","Present");
                                        snapshot.getRef().updateChildren(hashMap);
                                    }


                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                        final DatabaseReference databaseReference=FirebaseDatabase.getInstance().getReference("Attendance-Report");
                          final HashMap<String, Object> hashMap = new HashMap<>();
                             hashMap.put("empname","Santosh");
                             hashMap.put("present",+1);
                             databaseReference.child("2019").child("Aug").child("Report").push().setValue(hashMap);


                    }


                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });




    }

    @Override
    public int getItemCount() {
        return mAttendance.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView EmployeeName;
        public Button present,absent;
        public ViewHolder(View itemView) {
            super(itemView);
            EmployeeName = itemView.findViewById(R.id.EmpNameAtt);
            present = itemView.findViewById(R.id.presentButton);
            absent = itemView.findViewById(R.id.absentButton);

        }
    }




}