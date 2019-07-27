package agrawal.kritarth.kdbmsemployee.model;

public class Attendance {
    String empid;
    String attendance;

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

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    public Attendance(String empid, String attendance, String empname, String counter) {
        this.empid = empid;
        this.attendance = attendance;
        this.empname = empname;
        this.counter = counter;
    }

    String empname;
    String counter;

 public Attendance(){}

}
