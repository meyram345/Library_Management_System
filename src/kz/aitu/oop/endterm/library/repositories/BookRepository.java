package kz.aitu.oop.endterm.library.repositories;

import kz.aitu.oop.endterm.library.data.interfaces.IDB;
import kz.aitu.oop.endterm.library.entities.Book;
import kz.aitu.oop.endterm.library.repositories.interfaces.IBookRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookRepository implements IBookRepository {
    private final IDB db;

    public BookRepository(IDB db) {
        this.db = db;
    }

    //method for creating book
    @Override
    public boolean addBook(Book book) {
        Connection con = null;
        try{
            con = db.getConnection();
            String sql ="INSERT INTO books(book_id, title, author, lending_period) VALUES (?, ?, ?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setObject(1, book.getBook_uuid());
            st.setString(2, book.getTitle());
            st.setString(3, book.getAuthor());
            st.setInt(4, book.getLending_period());

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
    public Book getBook(String title, String author) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT book_id, title, author, lending_period FROM books WHERE title = ? AND author = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setString(1, title);
            st.setString(2, author);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Book book = new Book((UUID) rs.getObject("book_id"),
                        rs.getString("title"),
                        rs.getString("author"),
                        rs.getInt("lending_period"));

                return book;
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
    public boolean removeBook(Book book) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM books WHERE title = " + "\'" + book.getTitle() + "\'";
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
    public List<Book> getAllBooks() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT title, author FROM books";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Book> books = new ArrayList<>();
            while (rs.next()) {
                Book book = new Book(
                        rs.getString("title"),
                        rs.getString("author"));

                books.add(book);
            }

            return books;
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
