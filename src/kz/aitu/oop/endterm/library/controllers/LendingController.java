package kz.aitu.oop.endterm.library.controllers;

import kz.aitu.oop.endterm.library.entities.Lending;
import kz.aitu.oop.endterm.library.repositories.interfaces.IBookRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.ILendingRepository;
import kz.aitu.oop.endterm.library.repositories.interfaces.IStudentRepository;

import java.util.List;
import java.util.UUID;

public class LendingController {
    private final ILendingRepository lendingRepository;
    private final IBookRepository bookRepository;
    private final IStudentRepository studentRepository;


    public LendingController(ILendingRepository lendingRepository, IBookRepository bookRepository,
                             IStudentRepository studentRepository) {
        this.lendingRepository = lendingRepository;
        this.bookRepository = bookRepository;
        this.studentRepository = studentRepository;
    }

    public String createLending(String lending_status,
                                String borrowed_date, UUID book_id, UUID student_id) {
        Lending lending = new Lending(lending_status, borrowed_date, book_id, student_id);
        boolean created = false == lendingRepository.addLending(lending);
        return (created? "Lending was created!" : "Lending creation was failed!");
    }

    public String getLending(UUID lendingId) {
        Lending lending = lendingRepository.getLending(lendingId);

        return (lending == null ? "Lending was not found!" : "lending status: " + lending.getLending_status() +
                ", student:" + lending.getStudent_name() + ", book: " + lending.getTitle());
    }

    public String getAllLendings() {
        List<Lending> lendings = lendingRepository.getAllLendings();

        return lendings.toString(); //change toString
    }

    public String removeLending(UUID lending_uuid) {
        Lending lending = new Lending(lending_uuid);

        boolean removed = false == lendingRepository.removeLending(lending);

        return (removed ? "Lending was removed" : "Lending removal was failed");
    }
}
