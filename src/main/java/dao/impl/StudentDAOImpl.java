package dao.impl;

import dao.StudentDAO;
import util.DBUtil;
import model.Student;
import util.UpdateResult;
import java.util.*;
import java.sql.*;

/**
 * DAO负责：
 * 执行 SQL，与数据库交互
 * <p>
 * 不负责：
 * 处理逻辑判断，如数据是否合法等，交给业务层
 */

public class StudentDAOImpl implements StudentDAO {
    @Override
    public List<Student> selectAll() throws SQLException {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM stu_info";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(getOneRow(rs));
            }
            return list;
        }
    }

    @Override
    public Student selectById(int id) throws SQLException {
        String sql = "SELECT * FROM stu_info WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return getOneRow(rs);
                }
            }
        }
        return null;
    }

    @Override
    public List<Student> selectByConditions(Student stu) throws SQLException {
        List<Student> list = new ArrayList<>(); // 存放查询结果
        try (Connection conn = DBUtil.getConnection()) {
            UpdateResult ur = buildUpdateFields(stu);
            List<String> updates = ur.getUpdates();
            List<Object> params = ur.getParams();
            String sql = "SELECT * FROM stu_info WHERE " + String.join(" AND ", updates);
            PreparedStatement ps = conn.prepareStatement(sql);

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(getOneRow(rs));
            }
            return list;
        }
    }

    public int insert(Student stu) throws SQLException {
        String sql = "INSERT INTO stu_info (name, gender, age, weight, height) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, stu.getName());
            ps.setString(2, stu.getGender());
            ps.setInt(3, stu.getAge());
            ps.setDouble(4, stu.getWeight());
            ps.setDouble(5, stu.getHeight());
            return ps.executeUpdate();
        }
    }

    public int delete(Integer recordId) throws SQLException {
        String sql = "DELETE FROM stu_info WHERE id = ?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, recordId);
            return ps.executeUpdate();
        }
    }

    public boolean update(Student stu) throws SQLException {
        try (Connection conn = DBUtil.getConnection()) {
            UpdateResult ur = buildUpdateFields(stu);
            List<String> updates = ur.getUpdates();
            List<Object> params = ur.getParams();

            // 拼接sql
            String sql = "UPDATE stu_info SET " + String.join(", ", updates) + " WHERE id = ?";
            params.add(stu.getId());

            PreparedStatement ps = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }
            return ps.executeUpdate();
        }
    }

    private Student getOneRow(ResultSet rs) throws SQLException {
        Student stu = new Student();
        stu.setId(rs.getInt("id"));
        stu.setName(rs.getString("name"));
        stu.setGender(rs.getString("gender"));
        stu.setAge(rs.getInt("age"));
        stu.setWeight(rs.getDouble("weight"));
        stu.setHeight(rs.getDouble("height"));
        return stu;
    }
    
    private UpdateResult buildUpdateFields(Student stu) {
        List<String> updates = new ArrayList<>();
        List<Object> params = new ArrayList<>();

        if (stu.getName() != null) {
            updates.add("name = ?");
            params.add(stu.getName());
        }
        if (stu.getGender() != null) {
            updates.add("gender = ?");
            params.add(stu.getGender());
        }
        if (stu.getAge() != null) {
            updates.add("age = ?");
            params.add(stu.getAge());
        }
        if (stu.getWeight() != null) {
            updates.add("weight = ?");
            params.add(stu.getWeight());
        }
        if (stu.getHeight() != null) {
            updates.add("height = ?");
            params.add(stu.getHeight());
        }

        return new UpdateResult(updates, params);
    }
}
