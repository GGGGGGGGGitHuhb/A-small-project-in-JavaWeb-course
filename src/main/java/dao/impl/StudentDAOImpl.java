package dao.impl;

import dao.StudentDAO;
import util.DBUtil;
import model.Student;
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
    public List<Student> selectAll() {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM stu_info";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                getOneRow(list, rs);
            }
        } catch (SQLException e) {
            System.err.println("数据库异常: " + e.getMessage());
        }
        return list;
    }

    @Override
    public List<Student> selectById(int id) {
        List<Student> list = new ArrayList<>();
        String sql = "SELECT * FROM stu_info WHERE id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ps.setInt(1, id);
            while (rs.next()) {
                getOneRow(list, rs);
            }

        } catch (SQLException e) {
            System.err.println("数据库异常: " + e.getMessage());
        }
    }

    @Override
    public List<Student> selectByConditions(Student stu) {
        List<Map<String, Object>> list = new ArrayList<>(); // 存放查询结果

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
                getOneRow(list, rs);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * 添加一条学生记录
     * todo: 未填的字段赋值为空或特殊值
     *
     * @param stu Student类的一个对象，包含记录的所有字段
     * @return 添加成功返回true，添加失败或出错返回false
     */
    public int insert(Student stu) {
        if (stu.getName() == null || stu.getName().trim().isEmpty()) {
            System.out.println("姓名不能为空");
            return false;
        }

        String sql = "INSERT INTO stu_info (name, gender, age, weight, height) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, stu.getName());
            ps.setString(2, stu.getGender());
            ps.setInt(3, stu.getAge());
            ps.setDouble(4, stu.getWeight());
            ps.setDouble(5, stu.getHeight());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 根据记录的主键 id 删除对应记录
     *
     * @param recordId 学生记录的唯一标识 id，即主键
     * @return affectedRows 返回影响的行数，删除失败或抛出错误返回 -1
     */
    public int delete(Integer recordId) {
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement("DELETE FROM stu_info WHERE id = ?")) {
            int affectedRows = 0;

            ps.setInt(1, recordId);
            affectedRows = ps.executeUpdate();

            return affectedRows;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    // 按条件修改学生
    public boolean update(Student stu) {
        if (stu.getId() == null) {
            System.err.println("Id 不能为空！");
            return false;
        }

        int rows = 0;
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

            rows = ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rows > 0;
    }


    private void getOneRow(List<Student> list, ResultSet rs) throws SQLException {
        Student stu = new Student();

        stu.setId(rs.getInt("id"));
        stu.setName(rs.getString("name"));
        stu.setGender(rs.getString("gender"));
        stu.setAge(rs.getInt("age"));
        stu.setWeight(rs.getDouble("weight"));
        stu.setHeight(rs.getDouble("height"));

        list.add(stu);
    }

    /**
     * 根据传入的学生对象，构建动态更新所需的字段与参数列表
     *
     * <p>
     * 本方法遍历 {@link Student} 对象的属性，
     * 对不为 null 的字段生成 SQL 片段，
     * 并将属性值加入参数列表，用于后续绑定
     *
     * @param stu 学生对象，包含需要更新的字段
     * @return 一个 {@link UpdateResult} 对象，包含更新字段列表 updates 和对应参数 params 的封装结果
     */
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
