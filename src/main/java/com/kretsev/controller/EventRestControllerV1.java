package com.kretsev.controller;

import com.google.gson.Gson;
import com.kretsev.model.Event;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static com.kretsev.context.ApplicationContext.getEventService;

public class EventRestControllerV1 extends HttpServlet {
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
            Event event = getEventService().getById(id);
            jsonResult = GSON.toJson(event);
        } else {
            List<Event> events = getEventService().getAll();
            jsonResult = GSON.toJson(events);
        }
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Event event = GSON.fromJson(requestBody, Event.class);
        event = getEventService().create(event);
        String jsonResult = GSON.toJson(event);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Event event = GSON.fromJson(requestBody, Event.class);
        event = getEventService().update(event);
        String jsonResult = GSON.toJson(event);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        Integer fileId = Integer.parseInt(req.getParameter("id"));
        getEventService().deleteById(fileId);
    }
}
