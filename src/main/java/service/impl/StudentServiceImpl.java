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
        return dao.selectAll();
    }

    /**
     * todo
     *
     * @param stu
     * @return
     */
    @Override
    public List<Student> getStudentsByConditions(Student stu) {
        return dao.selectByConditions(stu);
    }

    @Override
    public boolean addStudents(Student stu) {
        // 至少需要有姓名
        if (stu.getName() == null || stu.getName().isEmpty()) {
            return false;
        }
        return dao.insert(stu) > 0;
    }

    /**
     * todo
     * @param stu
     * @return
     */
    @Override
    public boolean updateStudents(Student stu) {
        return false;
    }

    @Override
    public int deleteStudents(int recordId) {
        // 判断存在性
        if (dao.selectById(recordId) == null) {
            return NOTEXISTERROR;
        }
        return dao.delete(recordId);
    }
}
