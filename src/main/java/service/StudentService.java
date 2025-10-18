package service;

import java.util.*;

import model.Student;

public interface StudentService {
    List<Student> getAllStudents();
    List<Student> getStudentsByConditions(Student stu);
    boolean addStudents(Student stu);
    boolean updateStudents(Student stu);
    int deleteStudents(int recordId);
}
