package dao;

import model.Student;
import java.util.*;

public interface StudentDAO {
    List<Student> selectAll();
    List<Student> selectById(int id);
    List<Student> selectByConditions(Student stu);
    int insert(Student stu);
    boolean update(Student stu);
    int delete(Integer recordId);
}