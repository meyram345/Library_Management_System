package kz.aitu.oop.endterm.library.controllers;

import kz.aitu.oop.endterm.library.entities.Student;
import kz.aitu.oop.endterm.library.repositories.interfaces.IStudentRepository;

import java.util.List;
import java.util.UUID;

public class StudentController {
    private final IStudentRepository studentRepository;

    public StudentController(IStudentRepository repo) {
        this.studentRepository = repo;
    }

    public String createStudent(String name) {
        Student student = new Student(name);

        boolean created = studentRepository.createStudent(student);

        return (created ? "Student was created!" : "Student creation was failed!");
    }

    public String getStudent(UUID uuid) {
        Student student = studentRepository.getStudent(uuid);

        return (student == null ? "Student was not found!" : student.toString());
    }

    public String getAllStudents() {
        List<Student> students = studentRepository.getAllStudents();

        return students.toString();
    }

    public String removeStudent(String name) {
        Student student = new Student(name);

        boolean removed = studentRepository.removeStudent(student);
        return (removed? "Student was removed" : "Student removal was failed");
    }
}
