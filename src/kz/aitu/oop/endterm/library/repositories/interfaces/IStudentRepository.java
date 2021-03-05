package kz.aitu.oop.endterm.library.repositories.interfaces;

import kz.aitu.oop.endterm.library.entities.Student;

import java.util.List;

public interface IStudentRepository {
    boolean createStudent(Student student);
    boolean removeStudent(Student student);
    Student getStudent(String student_uuid);
    List<Student> getAllStudents();
}
