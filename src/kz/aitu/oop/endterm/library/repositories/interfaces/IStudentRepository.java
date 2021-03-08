package kz.aitu.oop.endterm.library.repositories.interfaces;

import kz.aitu.oop.endterm.library.entities.Student;

import java.util.List;
import java.util.UUID;

public interface IStudentRepository {
    boolean createStudent(Student student);
    boolean removeStudent(Student student);
    Student getStudent(UUID student_uuid);
    Student getStudentByName(String name);
    List<Student> getAllStudents();
}
