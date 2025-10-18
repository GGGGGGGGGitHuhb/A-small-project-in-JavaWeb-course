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
    private static final int NOT_EXIST_ERROR = -1;

    @Override
    public List<Student> getAllStudents() {
        try {
            return dao.selectAll();
        } catch (SQLException e) {
            throw new RuntimeException("查询学生列表失败", e);
        }
    }

    @Override
    public Student getStudentsById(int recordeId) {
        try {
            return dao.selectById(recordeId);
        } catch (SQLException e) {
            throw new RuntimeException("查询学生失败", e);
        }
    }

    @Override
    public List<Student> getStudentsByConditions(Student stu) {
        try {
            return dao.selectByConditions(stu);
        } catch (SQLException e) {
            throw new RuntimeException("按条件查询失败", e);
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
        } catch (SQLException e) {
            throw new RuntimeException("添加学生失败", e);
        }
    }

    @Override
    public boolean updateStudent(Student stu) {
        // 检查id是否存在
        if (stu.getId() == null) {
            return false;
        }
        try {
            // 检查是否有记录
            Student exist = dao.selectById(stu.getId());
            if (exist == null) {
                return false;
            }
            return dao.update(stu);
        } catch (SQLException e) {
            throw new RuntimeException("修改学生失败", e);
        }
    }

    @Override
    public int deleteStudent(int recordId) {
        try {
            // 判断存在性
            Student exist = dao.selectById(recordId);
            if (exist == null) {
                return NOT_EXIST_ERROR;
            }
            return dao.delete(recordId);
        } catch (SQLException e) {
            throw new RuntimeException("删除学生失败", e);
        }
    }
}
