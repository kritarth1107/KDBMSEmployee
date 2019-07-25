package agrawal.kritarth.kdbmsemployee.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import agrawal.kritarth.kdbmsemployee.EmpActivity;
import agrawal.kritarth.kdbmsemployee.MainActivity;
import agrawal.kritarth.kdbmsemployee.R;
import agrawal.kritarth.kdbmsemployee.model.Attendance;
import agrawal.kritarth.kdbmsemployee.model.Employee;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {

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
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Attendance attendance = mAttendance.get(position);
        holder.EmployeeName.setText(attendance.getEmpid());





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