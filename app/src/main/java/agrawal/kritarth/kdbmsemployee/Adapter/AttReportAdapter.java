package agrawal.kritarth.kdbmsemployee.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import agrawal.kritarth.kdbmsemployee.R;
import agrawal.kritarth.kdbmsemployee.model.Attendance;


public class AttReportAdapter extends RecyclerView.Adapter<AttReportAdapter.ViewHolder> {

    private Context mContext;
    private List<Attendance> mAttendance;
    private String Pdays,Adays,PpDays,AaDays;
    private int PD=0,AD=0;
    public AttReportAdapter(Context mContext, List<Attendance> mAttendance,String Pdays, String Adays){
        this.mAttendance = mAttendance;
        this.mContext = mContext;
        this.Pdays = Pdays;
        this.Adays = Adays;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.att_report_list, parent, false);
        return new AttReportAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        PD=0;AD=0;
        final Attendance attendance = mAttendance.get(position);
        holder.employeeName.setText(attendance.getEmpname());
        holder.employeeCounter_Report.setText(attendance.getCounter());
        holder.employeePhone_Report.setText("+91 "+attendance.getEmpid());
        if(attendance.getAttendance().equals("Present")){
            PD++;
        }
        else{
            AD++;
        }
        PpDays = Integer.toString(PD);
        AaDays = Integer.toString(AD);



        holder.PresentDays.setText(PpDays);
        holder.AbsentDays.setText(AaDays);
    }

    @Override
    public int getItemCount() {
        return mAttendance.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView employeeName,employeeCounter_Report,employeePhone_Report,PresentDays,AbsentDays;
        public TextView task;
        public ViewHolder(View itemView) {
            super(itemView);
            employeeName =itemView.findViewById(R.id.empName_Report);
            employeeCounter_Report = itemView.findViewById(R.id.employeeCounter_Report);
            employeePhone_Report = itemView.findViewById(R.id.employeePhone_Report);
            PresentDays = itemView.findViewById(R.id.PresentDays);
            AbsentDays = itemView.findViewById(R.id.AbsentDays);

        }
    }



}