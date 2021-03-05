package kz.aitu.oop.endterm.library.repositories.interfaces;

import kz.aitu.oop.endterm.library.entities.Lending;

import java.util.List;

public interface ILendingRepository {
    boolean addLending(Lending lending);
    boolean removeLending(Lending lending);
    Lending getLending(String lending_uuid);
    List<Lending> getAllLendings();
}
