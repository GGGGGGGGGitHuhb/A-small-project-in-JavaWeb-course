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

    /**
     * 处理 GET 请求，查询所有学生并显示列表
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 调用业务层获取数据
        List<Student> students = service.getAllStudents();

        // 结果存入请求作用域
        req.setAttribute("students", students); // 这里的 students 是什么

        // 转发到 JSP 页面
        req.getRequestDispatcher("/jsp.list.jsp").forward(req, resp);
    }

    /**
     * 处理 POST 请求：添加新学生
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 接收表单数据
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String gender = req.getParameter("gender");
        int age = Integer.parseInt(req.getParameter("age"));
        double weight = Double.parseDouble(req.getParameter("weight"));
        double height = Double.parseDouble(req.getParameter("height"));

        // 封装为 Student 对象
        Student stu = new Student();
        stu.setName(name);
        stu.setGender(gender);
        stu.setAge(age);
        stu.setWeight(weight);
        stu.setHeight(height);

        // 根据参数选择对应操作
        String action = req.getParameter("action");
        switch (action) {
            case "add": {

            }
        }

        // 调用 Service 层添加学生
        boolean success = service.addStudents(stu);

        // 根据结果跳转页面
        if (success) {
            // 重定向回学生列表
            resp.sendRedirect(req.getContextPath() + "/students");
        } else {
            // 转发回表单页面并提示
            req.setAttribute("error", "添加失败，请检查输入");
            req.getRequestDispatcher("jsp/add.jsp").forward(req, resp);
        }
    }
}
