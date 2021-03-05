package kz.aitu.oop.endterm.library.controllers;

import kz.aitu.oop.endterm.library.entities.Student;
import kz.aitu.oop.endterm.library.repositories.interfaces.IStudentRepository;

import java.util.List;

public class StudentController {
    private final IStudentRepository bookRepository;

    public StudentController(IStudentRepository repo) {
        this.bookRepository = repo;
    }

    public String createStudent(String name) {
        Student student = new Student(name);

        boolean created = bookRepository.createStudent(student);

        return (created ? "Student was created!" : "Student creation was failed!");
    }

    public String getStudent(String uuid) {
        Student student = bookRepository.getStudent(uuid);

        return (student == null ? "Student was not found!" : student.toString());
    }

    public String getAllStudents() {
        List<Student> students = bookRepository.getAllStudents();

        return students.toString();
    }
}
