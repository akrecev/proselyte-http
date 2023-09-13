package com.kretsev.controller;

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
import static com.kretsev.utility.GsonUtils.getGSON;
import static com.kretsev.utility.GsonUtils.getApplicationJson;

public class UserRestControllerV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getApplicationJson());
        PrintWriter writer = resp.getWriter();
        String jsonResult;
        String parameter = req.getParameter("id");

        if (parameter != null && !parameter.isBlank()) {
            Integer id = Integer.parseInt(parameter);
            User user = getUserService().getById(id);
            jsonResult = getGSON().toJson(user);
        } else {
            List<User> users = getUserService().getAll();
            jsonResult = getGSON().toJson(users);
        }
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getApplicationJson());
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = getGSON().fromJson(requestBody, User.class);
        user = getUserService().create(user);
        String jsonResult = getGSON().toJson(user);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getApplicationJson());
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        User user = getGSON().fromJson(requestBody, User.class);
        user = getUserService().update(user);
        String jsonResult = getGSON().toJson(user);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getApplicationJson());
        Integer fileId = Integer.parseInt(req.getParameter("id"));
        getUserService().deleteById(fileId);
    }
}
