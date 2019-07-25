package agrawal.kritarth.kdbmsemployee.model;

public class Employee {
    public Employee(String name, String mobile, String counter) {
        this.name = name;
        this.mobile = mobile;
        this.counter = counter;
    }

    public Employee(){

    }

    private String name;
    private String mobile;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCounter() {
        return counter;
    }

    public void setCounter(String counter) {
        this.counter = counter;
    }

    private String counter;
}
