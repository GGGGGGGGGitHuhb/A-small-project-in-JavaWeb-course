package service.impl;

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
    private final int NOTEXISTERROR = -1;

    @Override
    public List<Student> getAllStudents() {
        try {
            return dao.selectAll();
        } catch (java.sql.SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * todo
     *
     * @param stu
     * @return
     */
    @Override
    public List<Student> getStudentsByConditions(Student stu) {
        try {
            return dao.selectByConditions(stu);
        } catch (java.sql.SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    @Override
    public boolean addStudent(Student stu) {
        // 至少需要有姓名
        if (stu.getName() == null || stu.getName().isEmpty()) {
            return false;
        }
        try {
            return dao.insert(stu) > 0;
        } catch (java.sql.SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    /**
     * todo
     * @param stu
     * @return
     */
    @Override
    public boolean updateStudent(Student stu) {
        return false;
    }

    @Override
    public int deleteStudent(int recordId) {
        // 判断存在性
        try {
            if (dao.selectById(recordId) == null) {
                return NOTEXISTERROR;
            }
        } catch (java.sql.SQLException throwables) {
            throw new RuntimeException(throwables);
        }
        try {
            return dao.delete(recordId);
        } catch (java.sql.SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}
