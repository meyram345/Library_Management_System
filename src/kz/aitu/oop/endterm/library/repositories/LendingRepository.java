package kz.aitu.oop.endterm.library.repositories;

import kz.aitu.oop.endterm.library.controllers.BookController;
import kz.aitu.oop.endterm.library.data.interfaces.IDB;
import kz.aitu.oop.endterm.library.entities.Lending;
import kz.aitu.oop.endterm.library.repositories.interfaces.IBookRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.ILendingRepository;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LendingRepository implements ILendingRepository {
    private final IDB db;
    private BookRepository bookRepository;

    public LendingRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addLending(Lending lending) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql ="INSERT INTO book_lendings(lending_id, borrowed_date, due_date, lending_status, book_id, " +
                    "student_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setObject(1, lending.getLending_uuid());
            st.setDate(2, lending.convertToDate(lending.getBorrowed_date()));
            st.setDate(3,
                    lending.calculateDueDate(lending.getBorrowed_date(), 10));
            st.setString(4, lending.getLending_status());
            st.setObject(5, lending.getBook_id());
            st.setObject(6, lending.getStudent_id());

            boolean executed = false==st.execute();
            return executed;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }



    @Override
    public Lending getLending(UUID lendingId) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT book_lendings.lending_id, students.name, lending_status, books.title, books.author, " +
                    "borrowed_date, due_date, books.book_id, students.student_id FROM book_lendings\n" +
                    "INNER JOIN books ON book_lendings.book_id = books.book_id\n" +
                    "INNER JOIN students ON book_lendings.student_id = students.student_id WHERE book_lendings.lending_id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setObject(1, lendingId);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Lending lending = new Lending((UUID) rs.getObject("lending_id"),
                        rs.getString("name"),
                        rs.getString("lending_status"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("borrowed_date"),
                        rs.getString("due_date"),
                        (UUID) rs.getObject("book_id"),
                        (UUID) rs.getObject("student_id"));

                return lending;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public boolean removeLending(Lending lending) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM book_lendings WHERE lending_id = " + "\'" + lending.getLending_uuid() + "\'";
            PreparedStatement st = con.prepareStatement(sql);

            boolean executed = st.execute();
            return executed;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public List<Lending> getAllLendings() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT book_lendings.lending_id, students.name, lending_status, books.title, books.author, " +
                    "borrowed_date, due_date, students.student_id FROM book_lendings\n" +
                    "INNER JOIN books ON book_lendings.book_id = books.book_id\n" +
                    "INNER JOIN students ON book_lendings.student_id = students.student_id";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Lending> lendings = new ArrayList<>();
            while (rs.next()) {
                Lending lending = new Lending((UUID) rs.getObject("lending_id"),
                        rs.getString("name"),
                        rs.getString("lending status"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("borrowed_date"),
                        rs.getString("due_date"),
                        (UUID) rs.getObject("book_id"),
                        (UUID) rs.getObject("students_id"));

                lendings.add(lending);
            }

            return lendings;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
