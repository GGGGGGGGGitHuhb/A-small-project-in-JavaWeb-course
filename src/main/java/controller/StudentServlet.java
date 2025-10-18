package controller;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Student;
import service.*;

import jakarta.servlet.*;
import service.impl.StudentServiceImpl;

import java.io.IOException;
import java.util.*;

/**
 * Controller 负责：
 * 接收请求
 * 调用 Service
 * 跳转页面
 * 不负责：
 * 显示页面
 * 操作 SQL
 * 检查数据合法性
 */

@WebServlet("/students")
public class StudentServlet extends HttpServlet {

    private final StudentService service = new StudentServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用业务层获取数据
        List<Student> students = service.getAllStudents();

        // 结果存入请求作用域
        req.setAttribute("students", students); // 这里的 students 是什么

        // 转发到 JSP 页面
        req.getRequestDispatcher("/jsp/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收表单数据
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add" -> handleAdd(req, resp);
            case "update" -> handleUpdate(req, resp);
            case "delete" -> handleDelete(req, resp);
            default -> list(req, resp);
        }
    }

    private void handleAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取字段数据
        Student stu = extractStudent(req);

        // 调用 Service 层添加学生
        boolean success = service.addStudent(stu);

        // 根据结果跳转页面
        if (success) {
            // 重定向回学生列表
            resp.sendRedirect(req.getContextPath() + "/students");
        } else {
            // 转发回表单页面并提示
            req.setAttribute("error", "添加失败，请检查输入");
            req.getRequestDispatcher("/jsp/add.jsp").forward(req, resp);
        }
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 获取字段数据
        Student stu = extractStudent(req);

        // 调用 Service 层修改学生
        boolean success = service.updateStudent(stu);

        // 根据结果跳转页面
        if (success) {
            // 重定向会学生列表
            resp.sendRedirect(req.getContextPath() + "/students");
        } else {
            // 转发回表单页面并提示
            req.setAttribute("error", "修改失败");
            req.getRequestDispatcher("/jsp/edit.jsp").forward(req, resp);
        }
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        int affectedRows = 0;
        affectedRows = service.deleteStudent(id);
        if (affectedRows >= 0) {
            resp.sendRedirect(req.getContextPath() + "/students");
        } else {
            req.setAttribute("error", "删除失败");
            req.getRequestDispatcher("/jsp/delete.jsp").forward(req, resp);
        }
    }

    private void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("students", service.getAllStudents());
        req.getRequestDispatcher("/jsp/list.jsp").forward(req, resp);
    }

    // 公共方法 取参并封装为对象
    private Student extractStudent(HttpServletRequest req) {
        // 获取字段数据
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        int age = Integer.parseInt(req.getParameter("age"));
        double weight = Double.parseDouble(req.getParameter("weight"));
        double height = Double.parseDouble(req.getParameter("height"));

        // 封装为 Student 对象
        return new Student(name, gender, age, weight, height);
    }
}
