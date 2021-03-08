package kz.aitu.oop.endterm.library.repositories;

import kz.aitu.oop.endterm.library.data.interfaces.IDB;
import kz.aitu.oop.endterm.library.entities.Student;
import kz.aitu.oop.endterm.library.repositories.interfaces.IStudentRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentRepository implements IStudentRepository {
    private final IDB db;

    public StudentRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createStudent(Student Student) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "INSERT INTO Students(student_id, name) VALUES (?, ?)";
            PreparedStatement st = con.prepareStatement(sql);

            st.setObject(1, Student.getUuid());
            st.setString(2, Student.getName());

            boolean executed = st.execute();
            return executed;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }


    @Override
    public boolean removeStudent(Student Student) {
        Connection con = null;

        try {
            con = db.getConnection();
            String sql = "DELETE FROM Students WHERE name = " + "\'" + Student.getName() + "\'";
            PreparedStatement st = con.prepareStatement(sql);

            boolean executed = st.execute();
            return executed;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Student getStudent(UUID student_uuid) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT student_id,name FROM students WHERE student_id = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setObject(1, student_uuid);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Student student = new Student((UUID) rs.getObject("student_id"),
                        rs.getString("name"));

                return student;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }

    @Override
    public Student getStudentByName(String name) {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT student_id,name FROM students WHERE name = ?";
            PreparedStatement st = con.prepareStatement(sql);

            st.setObject(1, name);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                Student student = new Student((UUID) rs.getObject("student_id"),
                        rs.getString("name"));

                return student;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }


    @Override
    public List<Student> getAllStudents() {
        Connection con = null;
        try {
            con = db.getConnection();
            String sql = "SELECT student_id, name FROM students";
            Statement st = con.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Student> students = new ArrayList<>();
            while (rs.next()) {
                Student student = new Student((UUID) rs.getObject("student_id"),
                        rs.getString("name"));

                students.add(student);
            }

            return students;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return null;
    }
}
