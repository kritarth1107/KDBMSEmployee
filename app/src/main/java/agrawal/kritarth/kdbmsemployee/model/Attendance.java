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



    String empname;
    String counter;
    String date,day,month,year;

    public Attendance(String empid, String attendance, String empname, String counter, String date, String day, String month, String year) {
        this.empid = empid;
        this.attendance = attendance;
        this.empname = empname;
        this.counter = counter;
        this.date = date;
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Attendance(){}

}
