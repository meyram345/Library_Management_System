package kz.aitu.oop.endterm.library.entities;

import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.UUID;

public class Lending {
    private UUID lending_uuid;
    private String borrowed_date;
    private String due_date;
    private UUID student_id;
    private String student_name;
    private UUID book_id;
    private String title;
    private String author;

    public Lending() {}

    public Lending(UUID lending_uuid, String student_name, String title, String author,
                   String borrowed_date, String due_date, UUID book_id, UUID student_id) {
        setAuthor(author);
        setTitle(title);
        setLending_uuid(lending_uuid);
        setBorrowed_date(borrowed_date);
        setStudent_name(student_name);
        setDue_date(due_date);
        setStudent_id(student_id);
        setBook_id(book_id);
    }

    public Lending(String name, String title, String author, String borrowed_date, String due_date) {
        setAuthor(author);
        setTitle(title);
        setBorrowed_date(borrowed_date);
        setDue_date(due_date);
        setStudent_name(name);
    }

    public Lending(String borrowed_date, String student_name, String book_title, String author) {
        setAuthor(author);
        setTitle(book_title);
        setStudent_name(student_name);
        setBorrowed_date(borrowed_date);
    }

    public Lending(String student_name, String book_title, String author) {
        setStudent_name(student_name);
        setTitle(book_title);
        setAuthor(author);
    }

    //converting String date to sql Date
    public java.sql.Date convertToDate(String sDate) throws ParseException {
        java.sql.Date dDate = Date.valueOf(sDate);
        return dDate;
    }

    //calculating due_date = borrowed_date + lending_period
    public java.sql.Date calculateDueDate(String sBorrowed_date, int lending_period) throws ParseException {
        //converting borrowed date to a Date format
        LocalDate localDate = LocalDate.parse(sBorrowed_date);

        LocalDate lDue_date = localDate.plusDays(lending_period);
        java.sql.Date due_date = java.sql.Date.valueOf(lDue_date);
        return due_date;
    }


    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public UUID getLending_uuid() {
         return UUID.randomUUID();
    }


    public void setLending_uuid(UUID lending_uuid) {
        this.lending_uuid = lending_uuid;
    }

    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

    public UUID getBook_id() {
        return book_id;
    }

    public void setBook_id(UUID book_id) {
        this.book_id = book_id;
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

    public UUID getStudent_id() {
        return student_id;
    }

    public void setBorrowed_date(String borrowed_date) {
        this.borrowed_date = borrowed_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }

    public void setStudent_id(UUID student_id) {
        this.student_id = student_id;
    }


    @Override
    public String toString() {
        return "Student name: " + getStudent_name() + "\nBook: " + getTitle() + "\nauthor: " + getAuthor() +
                "\nborrowed date: " + getBorrowed_date() + "\ndue date: " + getDue_date();
    }
}
