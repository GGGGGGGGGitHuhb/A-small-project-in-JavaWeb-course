package service.impl;

import java.sql.SQLException;
import java.util.*;

import dao.StudentDAO;
import dao.impl.StudentDAOImpl;
import model.Student;
import service.StudentService;

/**
 * 业务层负责：
 * 调用 DAO
 * 数据校检
 * 业务判断
 * 事务
 * <p>
 * 不负责：
 * 写 SQL
 * 接触 Connection
 */

public class StudentServiceImpl implements StudentService {
    private final StudentDAO dao = new StudentDAOImpl();

    @Override
    public List<Student> getAllStudents() {
        try {
            return dao.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Student getStudentById(int recordeId) {
        if (recordeId <= 0) {
            return null;
        }

        try {
            return dao.selectById(recordeId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getStudentsByConditions(Student stu) {
        // 判断数据合法性
        if (stu == null) {
            return Collections.emptyList(); // 返回空集
        }

        boolean allEmpty = (stu.getName() == null || stu.getName().isEmpty()) &&
                           (stu.getGender() == null || stu.getGender().isEmpty()) &&
                           (stu.getAge() == null) &&
                           (stu.getWeight() == null) &&
                           (stu.getHeight() == null);
        if (allEmpty) {
            return Collections.emptyList();
        }

        try {
            return dao.selectByConditions(stu);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean addStudent(Student stu) {
        // 基本判断
        if (stu == null) {
            return false;
        }

        // 业务判断
        if (stu.getName() == null || stu.getName().isEmpty()) {
            return false;
        }
        if (stu.getAge() < 0 || stu.getAge() > 150) {
            return false;
        }
        if (stu.getWeight() < 20 || stu.getWeight() > 300) {
            return false;
        }
        if (stu.getHeight() < 100 || stu.getHeight() > 270) {
            return false;
        }

        try {
            return dao.insert(stu) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean updateStudent(Student stu) {
        if (stu == null || stu.getId() == null) {
            return false;
        }

        try {
            return dao.update(stu) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean deleteStudent(int recordId) {
        if (recordId <= 0) {
            return false;
        }

        try {
            return dao.delete(recordId) > 0;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
