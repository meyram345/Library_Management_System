package kz.aitu.oop.endterm.library.repositories.interfaces;

import kz.aitu.oop.endterm.library.entities.Book;

import java.util.List;
import java.util.UUID;

public interface IBookRepository {
    boolean addBook(Book book);
    boolean removeBook(Book book);
    List<Book> getAllBooks();
    Book getBook(String title, String author);
}