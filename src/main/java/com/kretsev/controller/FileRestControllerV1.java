package com.kretsev.controller;

import com.google.gson.Gson;
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

public class FileRestControllerV1 extends HttpServlet {
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
            File file = getFileService().getById(id);
            jsonResult = GSON.toJson(file);
        } else {
            List<File> files = getFileService().getAll();
            jsonResult = GSON.toJson(files);
        }
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        File file = GSON.fromJson(requestBody, File.class);
        file = getFileService().create(file);
        String jsonResult = GSON.toJson(file);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        File file = GSON.fromJson(requestBody, File.class);
        file = getFileService().update(file);
        String jsonResult = GSON.toJson(file);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        Integer fileId = Integer.parseInt(req.getParameter("id"));
        getFileService().deleteById(fileId);
    }
}
