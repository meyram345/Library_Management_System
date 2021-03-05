package kz.aitu.oop.endterm.library.repositories.interfaces;

import kz.aitu.oop.endterm.library.entities.Book;

import java.util.List;

public interface IBookRepository {
    boolean addBook(Book book);
    boolean removeBook(Book book);
    Book getBook(String book_uuid);
    List<Book> getBooks();
}