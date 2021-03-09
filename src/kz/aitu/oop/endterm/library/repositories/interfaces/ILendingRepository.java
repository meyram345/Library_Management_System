package kz.aitu.oop.endterm.library.repositories.interfaces;

import kz.aitu.oop.endterm.library.entities.Lending;

import java.util.List;
import java.util.UUID;

public interface ILendingRepository {
    boolean addLending(Lending lending);
    boolean removeLending(Lending lending);
    Lending getLending(String student, String title, String author);
    List<Lending> getAllLendings();
    List<Lending> getLendingsOfStudent(String name);
}
