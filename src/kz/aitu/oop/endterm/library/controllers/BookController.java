package kz.aitu.oop.endterm.library.controllers;

import kz.aitu.oop.endterm.library.entities.Book;
import kz.aitu.oop.endterm.library.repositories.interfaces.IBookRepository;

import java.util.List;

public class BookController {
    private final IBookRepository bookRepository;

    public BookController(IBookRepository repository) {
        this.bookRepository = repository;
    }

    public String addBook(String title, String author, int lending_period) {
        Book book = new Book(title, author, lending_period);

        boolean created = bookRepository.addBook(book);

        return (created ? "Book was added!" : "Book adding was failed!");
    }


    public String getBook(String title, String author) {
        Book book = bookRepository.getBook(title, author);

        return (book == null ? "Book was not found!" : book.toString());
    }

    public String getAllBooks() {
        List<Book> books = bookRepository.getAllBooks();

        //handling array
        String allBooks = "";
        for(int i=0;i<books.size();i++) {
            allBooks = allBooks + (i+1) + ". " + books.get(i).toString() + "\n";
        }

        return allBooks;
    }

    public String removeBook(String title, String author_name) {
        Book book = new Book(title, author_name);
        boolean removed =false == bookRepository.removeBook(book);

        return (removed ? "Book was removed" : "Book removal was failed");
    }
}
