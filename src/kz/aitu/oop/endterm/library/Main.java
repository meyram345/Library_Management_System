package kz.aitu.oop.endterm.library;

import kz.aitu.oop.endterm.library.data.PostgresDB;
import kz.aitu.oop.endterm.library.data.interfaces.IDB;
import kz.aitu.oop.endterm.library.repositories.BookRepository;
import kz.aitu.oop.endterm.library.repositories.LendingRepository;
import kz.aitu.oop.endterm.library.repositories.StudentRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.IBookRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.ILendingRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.IStudentRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB();
        IBookRepository bookRepository = new BookRepository(db);
        IStudentRepository studentRepository = new StudentRepository(db);
        ILendingRepository lendingRepository = new LendingRepository(db);
        MyApplication app = new MyApplication(lendingRepository, bookRepository, studentRepository);
        app.start();
    }
}
