package agrawal.kritarth.kdbmsemployee.Adapter;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.util.List;

import agrawal.kritarth.kdbmsemployee.EmpActivity;
import agrawal.kritarth.kdbmsemployee.R;
import agrawal.kritarth.kdbmsemployee.model.Employee;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {

    private Context mContext;
    private List<Employee> mEmployee;

    public EmployeeAdapter(Context mContext, List<Employee> mEmployee){
        this.mEmployee = mEmployee;
        this.mContext = mContext;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.emp_list, parent, false);
        return new EmployeeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        final Employee employee = mEmployee.get(position);
        holder.EmployeeName.setText(employee.getName());
        holder.PhoneNumber.setText(employee.getCounter());

       holder.user_layout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, EmpActivity.class);
                intent.putExtra("empid", employee.getMobile());
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mEmployee.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

        public TextView EmployeeName;
        public TextView PhoneNumber;
        public LinearLayout user_layout2;
        public ViewHolder(View itemView) {
            super(itemView);
            user_layout2 =itemView.findViewById(R.id.user_layout2);
            EmployeeName = itemView.findViewById(R.id.EmployeeName);
            PhoneNumber = itemView.findViewById(R.id.PhoneNumber);

        }
    }



}