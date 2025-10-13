package dao;

import db.DBUtil;
import entity.Student;
import java.sql.*;
import java.util.*;

// 什么是 DAO
public class StudentDAO {
    // 查询所有学生
    public List<Map<String, Object>> getALLStudents() {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "SELECT * FROM stu_info";
        // 这几个参数中间为什么分号分隔，什么写法
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            // next() 方法的作用是什么
            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("name", rs.getString("name"));
                row.put("gender", rs.getString("gender"));
                row.put("age", rs.getInt("age"));
                row.put("weight", rs.getDouble("weight"));
                row.put("height", rs.getDouble("height"));
                list.add(row);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 按条件查询学生
    public List<Map<String, Object>> getStudents(Student stu) {
        List<Map<String, Object>> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM stu_info WHERE 1=1 ");
        try (Connection conn = DBUtil.getConnection()) {
            if (stu.getName() != null) sql.append("AND name = ?");
            if (stu.getGender() != null) sql.append("AND gender = ?");
            if (stu.getAge() != null) sql.append("AND age = ?");
            if (stu.getWeight() != null) sql.append("AND weight = ?");
            if (stu.getHeight() != null) sql.append("AND height = ?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            int index = 1;
            if (stu.getName() != null) ps.setString(index++, stu.getName());
            if (stu.getGender() != null) ps.setString(index++, stu.getGender());
            if (stu.getAge() != null) ps.setInt(index++, stu.getAge());
            if (stu.getWeight() != null) ps.setDouble(index++, stu.getWeight());
            if (stu.getHeight() != null) ps.setDouble(index++, stu.getHeight());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Map<String, Object> row = new HashMap<>();
                row.put("id", rs.getInt("id"));
                row.put("name", rs.getString("name"));
                row.put("gender", rs.getString("gender"));
                row.put("age", rs.getInt("age"));
                row.put("weight", rs.getDouble("weight"));
                row.put("height", rs.getDouble("height"));
                list.add(row);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 添加学生
    public boolean addStudent(String name, String gender, int age, double weight, double height) {
        String sql = "INSERT INTO stu_info (name, gender, age, weight, height) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            // 索引是从1开始吗，1就是第一个占位符?的地方吗
            ps.setString(1, name);
            ps.setString(2, gender);
            ps.setInt(3, age);
            ps.setDouble(4, weight);
            ps.setDouble(5, height);
            // 为什么 > 0
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 按条件删除学生
    public boolean delStudent(Student stu) {
        StringBuilder sql = new StringBuilder("DELETE FROM stu_info WHERE 1=1 ");
        int rows = 0;

        try (Connection conn = DBUtil.getConnection()) {
            // 先拼接sql
            if (stu.getName() != null) sql.append(" AND name = ?");
            if (stu.getGender() != null) sql.append(" AND gender = ?");
            if (stu.getAge() != null) sql.append(" AND age = ?");
            if (stu.getWeight() != null) sql.append(" AND weight = ?");
            if (stu.getHeight() != null) sql.append(" AND height = ?");

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            // 再动态绑定参数
            int index = 1;
            if (stu.getName() != null) ps.setString(index++, stu.getName());
            if (stu.getGender() != null) ps.setString(index++, stu.getGender());
            if (stu.getAge() != null) ps.setInt(index++, stu.getAge());
            if (stu.getWeight() != null) ps.setDouble(index++, stu.getWeight());
            if (stu.getHeight() != null) ps.setDouble(index++, stu.getHeight());

            // 防止误删整张表
            if (index == 1) {
                System.out.println("不允许删除整张表！");
                return false;
            }
            rows = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rows > 0;
    }


    // 按条件修改学生
    public boolean updateStudent(Student stu) {
        if (stu.getId() == null) {
            System.err.println("Id 不能为空！");
            return false;
        }

        List<Object> params = new ArrayList<>();
        List<String> updates = new ArrayList<>();
        int rows = 0;
        try (Connection conn = DBUtil.getConnection()) {
            // 动态拼接字段，同时绑定参数
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

            // 拼接sql
            StringBuilder sql = new StringBuilder("UPDATE stu_info SET ");
            sql.append(String.join(", ", updates));
            sql.append(" WHERE id = ?");
            params.add(stu.getId());

            PreparedStatement ps = conn.prepareStatement(sql.toString());

            for (int i = 0; i < params.size(); i++) {
                ps.setObject(i + 1, params.get(i));
            }

            rows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows > 0;
    }
}