package kz.aitu.oop.endterm.library.controllers;

import kz.aitu.oop.endterm.library.entities.Book;
import kz.aitu.oop.endterm.library.repositories.BookRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.IBookRepository;

import java.util.List;
import java.util.UUID;

public class BookController {
    private final IBookRepository bookRepository;

    public BookController(IBookRepository repository) {
        this.bookRepository = repository;
    }

    public String addBook(String title, String author, int total_amount, int lending_period) {
        Book book = new Book(title, author, total_amount, lending_period);

        boolean created = bookRepository.addBook(book);

        return (created ? "Book was added!" : "Book adding was failed!");
    }

    public String getBook(UUID uuid) {
        Book book = bookRepository.getBook(uuid);

        return (book == null ? "Book was not found!" : book.getTitle() + ", author:  " + book.getAuthor());
    }

    public String getAllBooks() {
        List<Book> books = bookRepository.getBooks();

        return books.toString();
    }

    public String removeBook(String title, String author_name) {
        Book book = new Book(title, author_name);
        boolean removed =false == bookRepository.removeBook(book);

        return (removed ? "Book was removed" : "Book removal was failed");
    }
}
