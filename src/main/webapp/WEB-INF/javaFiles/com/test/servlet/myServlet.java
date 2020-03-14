package com.test.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;


public class myServlet extends javax.servlet.http.HttpServlet {
    private String message;
    @Override
    public void init() throws ServletException {
        message = "Hello world, this message is from servlet!";
    }
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse resp) throws javax.servlet.ServletException, IOException {
        //设置响应内容类型
        resp.setContentType("text/html");

        //设置逻辑实现
        PrintWriter out = resp.getWriter();
        out.println("<h1>" + message + "</h1>");
    }
}
