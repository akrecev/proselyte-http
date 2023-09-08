package com.kretsev.controller;

import com.google.gson.Gson;
import com.kretsev.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static com.kretsev.context.ApplicationContext.getUserService;

public class UserRestControllerV1 extends HttpServlet {
    private final Gson GSON = new Gson();
    private final static String CONTENT_TYPE = "application/json";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        String jsonResult;
        String parameter = req.getParameter("id");

        if (parameter != null && !parameter.isBlank()) {
            Integer id = Integer.parseInt(parameter);
            User user = getUserService().getById(id);
            jsonResult = GSON.toJson(user);
        } else {
            List<User> users = getUserService().getAll();
            jsonResult = GSON.toJson(users);
        }
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = GSON.fromJson(requestBody, User.class);
        user = getUserService().create(user);
        String jsonResult = GSON.toJson(user);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = GSON.fromJson(requestBody, User.class);
        user = getUserService().update(user);
        String jsonResult = GSON.toJson(user);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        Integer fileId = Integer.parseInt(req.getParameter("id"));
        getUserService().deleteById(fileId);
    }
}
