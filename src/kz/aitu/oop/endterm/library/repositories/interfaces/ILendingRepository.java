package kz.aitu.oop.endterm.library.repositories.interfaces;

import kz.aitu.oop.endterm.library.entities.Lending;

import java.util.List;
import java.util.UUID;

public interface ILendingRepository {
    boolean addLending(Lending lending);
    boolean removeLending(Lending lending);
    Lending getLending(UUID lending_id);
    List<Lending> getAllLendings();
}
