package agrawal.kritarth.kdbmsemployee.model;

public class Task {
    String assign_date,task,target_days;
    String completed;

    public String getTarget_days() {
        return target_days;
    }

    public void setTarget_days(String target_days) {
        this.target_days = target_days;
    }

    public String getAssign_date() {
        return assign_date;
    }

    public void setAssign_date(String assign_date) {
        this.assign_date = assign_date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getCompleted() {
        return completed;
    }

    public void setCompleted(String completed) {
        this.completed = completed;
    }

    public Task(String assign_date, String task, String completed,String target_days) {
        this.assign_date = assign_date;
        this.task = task;
        this.completed = completed;
        this.target_days= target_days;
    }

    public Task(){}
}
