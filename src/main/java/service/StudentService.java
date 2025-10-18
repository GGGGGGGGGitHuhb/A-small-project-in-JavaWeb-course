package service;

import java.util.*;

import model.Student;

public interface StudentService {
    List<Student> getAllStudents();
    List<Student> getStudentsByConditions(Student stu);
    boolean addStudent(Student stu);
    boolean updateStudent(Student stu);
    int deleteStudent(int recordId);
}
