package kz.aitu.oop.endterm.library.controllers;

import kz.aitu.oop.endterm.library.entities.Lending;
import kz.aitu.oop.endterm.library.repositories.interfaces.IBookRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.ILendingRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.IStudentRepository;

import java.util.List;

public class LendingController {
    private ILendingRepository lendingRepository;
    private IBookRepository bookRepository;
    private IStudentRepository studentRepository;


    public LendingController(ILendingRepository lendingRepository, IBookRepository bookRepository,
                             IStudentRepository studentRepository) {
        this.lendingRepository = lendingRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    public String createLending(String borrowed_date, String student_name, String book_title, String author) {
        Lending lending = new Lending(borrowed_date, student_name, book_title, author);
        boolean created = lendingRepository.addLending(lending);
        return (created? "Lending was created!" : "Lending creation was failed!");
    }

    public String getLending(String student, String title, String author) {
        Lending lending = lendingRepository.getLending(student, title, author);

        return (lending == null ? "Lending was not found!" : lending.toString());
    }

    public String getAllLendings() {
        List<Lending> lendings = lendingRepository.getAllLendings();

        //handling array
        String student_lendings = "";
        for(int i=0;i<lendings.size();i++) {
            student_lendings = student_lendings + (i + 1) + ". " + lendings.get(i).toString() + "\n";
        }

        return student_lendings;
    }


    //getting lendings of some student
    public String getLendingsOfStudent(String name) {
        List<Lending> lendings = lendingRepository.getLendingsOfStudent(name);
        String student_lendings="";
        //handling array
        for(int i=0;i<lendings.size();i++) {
            student_lendings = student_lendings + (i + 1) + ". " + lendings.get(i).toString() + "\n";
        }

        return student_lendings; //change toString
    }

    public String removeLending(String student_name, String book_title, String author) {
        Lending lending = new Lending(student_name, book_title, author);

        boolean removed = false == lendingRepository.removeLending(lending);

        return (removed ? "Lending was removed" : "Lending removal was failed");
    }
}
