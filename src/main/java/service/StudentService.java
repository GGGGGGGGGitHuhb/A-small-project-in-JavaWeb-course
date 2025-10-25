package service;

import java.util.*;

import model.Student;

public interface StudentService {
    List<Student> getAllStudents();
    Student getStudentById(int recordeId);
    List<Student> getStudentsByConditions(Student stu);
    boolean addStudent(Student stu);
    boolean updateStudent(Student stu);
    boolean deleteStudent(int recordId);
}
