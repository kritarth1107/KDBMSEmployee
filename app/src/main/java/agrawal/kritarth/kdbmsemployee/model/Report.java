package agrawal.kritarth.kdbmsemployee.model;

public class Report {
    public Report(String empname, String empid, int present, int absent, int notset) {
        this.empname = empname;
        this.empid = empid;
        this.present = present;
        this.absent = absent;
        this.notset = notset;
    }
    public Report(){}

    String empname,empid;
    int present,absent,notset;

    public String getEmpname() {
        return empname;
    }

    public void setEmpname(String empname) {
        this.empname = empname;
    }

    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public int getPresent() {
        return present;
    }

    public void setPresent(int present) {
        this.present = present;
    }

    public int getAbsent() {
        return absent;
    }

    public void setAbsent(int absent) {
        this.absent = absent;
    }

    public int getNotset() {
        return notset;
    }

    public void setNotset(int notset) {
        this.notset = notset;
    }
}
