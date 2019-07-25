package agrawal.kritarth.kdbmsemployee.model;

public class Attendance {
    String empid,attendance;

 public Attendance(){}
    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getAttendance() {
        return attendance;
    }

    public void setAttendance(String attendance) {
        this.attendance = attendance;
    }

    public Attendance(String empid, String attendance) {
        this.empid = empid;
        this.attendance = attendance;
    }
}
