package kz.aitu.oop.endterm.library.repositories.interfaces;

import kz.aitu.oop.endterm.library.entities.Book;

import java.util.List;
import java.util.UUID;

public interface IBookRepository {
    boolean addBook(Book book);
    boolean removeBook(Book book);
    Book getBook(UUID book_uuid);
    //int getLendingPeriod(String title, String author);
    List<Book> getBooks();

    Book getBookByTitleAndAuthor(String title, String author);
}