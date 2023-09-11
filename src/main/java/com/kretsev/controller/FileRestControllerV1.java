package com.kretsev.controller;

import com.kretsev.model.File;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static com.kretsev.context.ApplicationContext.getFileService;
import static com.kretsev.utility.GsonUtils.getGSON;
import static com.kretsev.utility.GsonUtils.getJsonContentType;

public class FileRestControllerV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getJsonContentType());
        PrintWriter writer = resp.getWriter();
        String jsonResult;
        String parameter = req.getParameter("id");

        if (parameter != null && !parameter.isBlank()) {
            Integer id = Integer.parseInt(parameter);
            File file = getFileService().getById(id);
            jsonResult = getGSON().toJson(file);
        } else {
            List<File> files = getFileService().getAll();
            jsonResult = getGSON().toJson(files);
        }
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getJsonContentType());
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        File file = getGSON().fromJson(requestBody, File.class);
        file = getFileService().create(file);
        String jsonResult = getGSON().toJson(file);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getJsonContentType());
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        File file = getGSON().fromJson(requestBody, File.class);
        file = getFileService().update(file);
        String jsonResult = getGSON().toJson(file);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getJsonContentType());
        Integer fileId = Integer.parseInt(req.getParameter("id"));
        getFileService().deleteById(fileId);
    }
}
