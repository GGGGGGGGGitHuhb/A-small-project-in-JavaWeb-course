package dao;

import model.Student;
import java.sql.SQLException;
import java.util.*;

public interface StudentDAO {
    List<Student> selectAll() throws SQLException;
    List<Student> selectById(int id) throws SQLException;
    List<Student> selectByConditions(Student stu) throws SQLException;
    int insert(Student stu) throws SQLException;
    int update(Student stu) throws SQLException;
    int delete(Integer recordId) throws SQLException;
}