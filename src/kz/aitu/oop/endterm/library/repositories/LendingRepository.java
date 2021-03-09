package kz.aitu.oop.endterm.library.repositories;

import kz.aitu.oop.endterm.library.data.interfaces.IDB;
import kz.aitu.oop.endterm.library.entities.Lending;
import kz.aitu.oop.endterm.library.repositories.interfaces.IBookRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.ILendingRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.IStudentRepository;

import java.sql.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class LendingRepository implements ILendingRepository {
    private final IDB db;

    //import book and student repositories in order to get access to their columns
    private IBookRepository bookRepository;
    private IStudentRepository studentRepository;

    public LendingRepository(IDB db, IBookRepository bookRepository, IStudentRepository studentRepository) {
        this.db = db;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }



    @Override
    public boolean addLending(Lending lending) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql ="INSERT INTO book_lendings(lending_id, borrowed_date, due_date, book_id, " +
                    "student_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setObject(1, lending.getLending_uuid());
            //converting String date to sqlDate
            st.setDate(2, lending.convertToDate(lending.getBorrowed_date()));

            //calculating due_date = borrowed_date + lending_period
            st.setDate(3,
                    lending.calculateDueDate(lending.getBorrowed_date(),
                            bookRepository.getBook(lending.getTitle(),
                                    lending.getAuthor()).getLending_period()));

            //getting access to book id using title and author with help of bookRepository
            st.setObject(4, UUID.fromString(bookRepository.getBook(lending.getTitle(),
                    lending.getAuthor()).getBook_id()));

            //getting access to student id using name with help of studentRepository
            st.setObject(5, UUID.fromString(studentRepository.getStudent(lending.getStudent_name()).getStudent_id()));

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
    public Lending getLending(String student, String title, String author) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT book_lendings.lending_id, students.name, books.title, books.author, " +
                    "borrowed_date, due_date, books.book_id, students.student_id FROM book_lendings\n" +
                    "INNER JOIN books ON book_lendings.book_id = books.book_id\n" +
                    "INNER JOIN students ON book_lendings.student_id = students.student_id WHERE students.name = ? AND " +
                    "books.title = ? AND books.author = ?";
            PreparedStatement st = con.prepareStatement(sql);

            //getting access to the variables of book and student using repository methods
            st.setString(1, studentRepository.getStudent(student).getName());
            st.setString(2, bookRepository.getBook(title, author).getTitle());
            st.setString(3, bookRepository.getBook(title, author).getAuthor());

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Lending lending = new Lending((UUID) rs.getObject("lending_id"),
                        rs.getString("name"),
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
            //getting access to the IDs of book and student using repository methods
            String sql = "DELETE FROM book_lendings WHERE student_id = " + "\'" +
                    UUID.fromString(studentRepository.getStudent(lending.getStudent_name()).getStudent_id()) + "\'" +
                    "AND book_id = '" + UUID.fromString(bookRepository.getBook(lending.getTitle(),
                    lending.getAuthor()).getBook_id()) + "\'";//
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
            String sql = "SELECT book_lendings.lending_id, students.name, books.title, books.author, " +
                    "borrowed_date, due_date, books.book_id, students.student_id FROM book_lendings\n" +
                    "INNER JOIN books ON book_lendings.book_id = books.book_id\n" +
                    "INNER JOIN students ON book_lendings.student_id = students.student_id";
            Statement st = con.createStatement();


            ResultSet rs = st.executeQuery(sql);
            List<Lending> lendings = new ArrayList<>();
            while (rs.next()) {
                Lending lending = new Lending((UUID) rs.getObject("lending_id"),
                        rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("borrowed_date"),
                        rs.getString("due_date"),
                        (UUID) rs.getObject("book_id"),
                        (UUID) rs.getObject("student_id"));

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

    //getting lendings of some student
    @Override
    public List<Lending> getLendingsOfStudent(String name) {
        Connection con = null;
        try {
            con = db.getConnection();

            String sql=("SELECT book_lendings.lending_id, students.name, books.title, books.author, " +
                    "borrowed_date, due_date, students.student_id FROM book_lendings\n" +
                    "INNER JOIN books ON book_lendings.book_id = books.book_id\n" +
                    "INNER JOIN students ON book_lendings.student_id = students.student_id WHERE students.name = ?");
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, name);

            ResultSet rs = st.executeQuery();
            List<Lending> lendings = new ArrayList<>();
            while (rs.next()) {
                Lending lending = new Lending(rs.getString("name"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getString("borrowed_date"),
                        rs.getString("due_date"));

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
