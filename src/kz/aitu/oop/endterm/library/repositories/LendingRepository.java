package kz.aitu.oop.endterm.library.repositories;

import kz.aitu.oop.endterm.library.data.interfaces.IDB;
import kz.aitu.oop.endterm.library.entities.Lending;
import kz.aitu.oop.endterm.library.repositories.interfaces.ILendingRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LendingRepository implements ILendingRepository {
    private final IDB db;

    public LendingRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean addLending(Lending lending) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql ="INSERT INTO Lending_lendings(id, borrowed_date, due_date, lending_status, lending_id, " +
                    "student_id) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, lending.getLending_uuid());

            boolean executed = false==st.execute();
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
    public Lending getLending(String student_name) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT book_lendings.id, students.name, lending_status, books.title, books.author, " +
                    "borrowed_date, due_date, students.id FROM book_lendings\n" +
                    "INNER JOIN books ON book_lendings.book_id = books.id\n" +
                    "INNER JOIN students ON book_lendings.student_id = students.id WHERE student.name = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, student_name);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Lending lending = new Lending(rs.getString("id"),
                        rs.getString("students.name"),
                        rs.getString("lending status"),
                        rs.getString("books.title"),
                        rs.getString("books.author"),
                        rs.getString("borrowed_date"),
                        rs.getString("due_date"),
                        rs.getString("students.id"));

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
            String sql = "DELETE FROM book_lendings WHERE id = " + "\'" + lending.getLending_uuid() + "\'";
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
            String sql = "SELECT book_lendings.id, students.name, lending_status, books.title, books.author, " +
                    "borrowed_date, due_date, students.id FROM book_lendings\n" +
                    "INNER JOIN books ON book_lendings.book_id = books.id\n" +
                    "INNER JOIN students ON book_lendings.student_id = students.id";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Lending> lendings = new ArrayList<>();
            while (rs.next()) {
                Lending lending = new Lending(rs.getString("id"),
                        rs.getString("students.name"),
                        rs.getString("lending status"),
                        rs.getString("books.title"),
                        rs.getString("books.author"),
                        rs.getString("borrowed_date"),
                        rs.getString("due_date"),
                        rs.getString("students.id"));

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
