package com.kretsev.controller;

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
import static com.kretsev.utility.GsonUtils.getGSON;
import static com.kretsev.utility.GsonUtils.getJsonContentType;

public class EventRestControllerV1 extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getJsonContentType());
        PrintWriter writer = resp.getWriter();
        String jsonResult;
        String parameter = req.getParameter("id");

        if (parameter != null && !parameter.isBlank()) {
            Integer id = Integer.parseInt(parameter);
            Event event = getEventService().getById(id);
            jsonResult = getGSON().toJson(event);
        } else {
            List<Event> events = getEventService().getAll();
            jsonResult = getGSON().toJson(events);
        }
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getJsonContentType());
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Event event = getGSON().fromJson(requestBody, Event.class);
        event = getEventService().create(event);
        String jsonResult = getGSON().toJson(event);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getJsonContentType());
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        Event event = getGSON().fromJson(requestBody, Event.class);
        event = getEventService().update(event);
        String jsonResult = getGSON().toJson(event);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getJsonContentType());
        Integer fileId = Integer.parseInt(req.getParameter("id"));
        getEventService().deleteById(fileId);
    }
}
