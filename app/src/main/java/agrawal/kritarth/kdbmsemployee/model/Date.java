package agrawal.kritarth.kdbmsemployee.model;

public class Date {
    String date,day,month,year;
    public Date(){}

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

    public Date(String date, String day, String month, String year) {
        this.date = date;
        this.day = day;
        this.month = month;
        this.year = year;
    }
}
