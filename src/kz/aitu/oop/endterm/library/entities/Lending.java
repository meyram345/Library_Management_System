package kz.aitu.oop.endterm.library.entities;

public class Lending extends Book{
    private String lending_uuid;
    private String borrowed_date;
    private String due_date;
    private String lending_status;
    private String student_id;
    private String student_name;

    public Lending() {}

    public Lending(String student_name, String lending_status, String title, String author, String borrowed_date,
                   String due_date, String student_id) {
        super(title, author);
        setBorrowed_date(borrowed_date);
        setStudent_name(student_name);
        setLending_status(lending_status);
    }

    public Lending(String lending_uuid, String student_name, String lending_status, String title, String author,
                   String borrowed_date, String due_date, String student_id) {
        super(title, author);
        setLending_uuid(lending_uuid);
        setBorrowed_date(borrowed_date);
        setStudent_name(student_name);
        setLending_status(lending_status);
        setDue_date(due_date);
    }

    public Lending(String lending_uuid) {
        setLending_uuid(lending_uuid);
    }


    public String getLending_uuid() {
        return lending_uuid;
    }


    public void setLending_uuid(String lending_uuid) {
        this.lending_uuid = String.valueOf(java.util.UUID.randomUUID());
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    @Override
    public String getAuthor() {
        return super.getAuthor();
    }

    @Override
    public String getBook_uuid() {
        return super.getBook_uuid();
    }

    @Override
    public String getTitle() {
        return super.getTitle();
    }

    public String getStudent_name() {
        return student_name;
    }

    public String getBorrowed_date() {
        return borrowed_date;
    }

    public String getDue_date() {
        return due_date;
    }

    public String getLending_status() {
        return lending_status;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setBorrowed_date(String borrowed_date) {
        this.borrowed_date = borrowed_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public void setLending_status(String lending_status) {
        this.lending_status = lending_status;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }
}
