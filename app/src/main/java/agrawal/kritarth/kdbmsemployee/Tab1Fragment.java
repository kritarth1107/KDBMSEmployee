package agrawal.kritarth.kdbmsemployee;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import agrawal.kritarth.kdbmsemployee.Adapter.AttReportAdapter;
import agrawal.kritarth.kdbmsemployee.model.Attendance;

public class Tab1Fragment extends Fragment {
    private static final String TAG = "Tab1Fragment";
    RecyclerView reportRecyclerView;
    private Button btnTEST;
    private AttReportAdapter attReportAdapter;
    private List<Attendance> mAtt;
    String thisMonth;
    int pDays=0,Adays=0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1,container,false);
        reportRecyclerView = view.findViewById(R.id.reportRecyclerView);
        reportRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        //Date Setup
        DateFormat df2 = new SimpleDateFormat("MMM");
        thisMonth = df2.format(Calendar.getInstance().getTime());
        readAttReport();

        return view;
    }

    public void readAttReport(){

        Query reference = FirebaseDatabase.getInstance().getReference("Attendance").child("04-Aug-2019").child("Attendance");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                mAtt = new ArrayList<>();
                pDays=0;Adays=0;
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Attendance s = dataSnapshot1.getValue(Attendance.class);
                        if(s.getMonth().equals(thisMonth)){

                            mAtt.add(s);
                        }



                }
                String PpDays = Integer.toString(pDays);
                String AaDays = Integer.toString(Adays);
                attReportAdapter = new AttReportAdapter(getContext(), mAtt,PpDays,AaDays);
                reportRecyclerView.setAdapter(attReportAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "Oopps", Toast.LENGTH_LONG);

            }
        });
    }
}