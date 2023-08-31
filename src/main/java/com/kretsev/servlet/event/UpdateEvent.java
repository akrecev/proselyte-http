package com.kretsev.servlet.event;

import com.kretsev.model.Event;
import com.kretsev.model.File;
import com.kretsev.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.kretsev.context.ApplicationContext.*;

public class UpdateEvent extends HttpServlet {
    private final static String CONTENT_TYPE = "text/html";
    private final static String DOC_TYPE = "<!DOCTYPE html>";
    private final static String TITLE = "Update Event";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();

        writer.println(DOC_TYPE + "\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + TITLE + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "Method GET not supported" +
                "\n</body>" +
                "\n</html>");

        resp.setStatus(resp.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();

        Integer eventId = Integer.parseInt(req.getParameter("eventId"));

        Integer userId = Integer.parseInt(req.getParameter("userId"));
        User user = getUserService().getById(userId);

        Integer fileId = Integer.parseInt(req.getParameter("fileId"));
        File file = getFileService().getById(fileId);


        Event event = Event.builder().id(eventId).user(user).file(file).build();
        event = getEventService().update(event);
        event = getEventService().getById(event.getId());
        //TODO при вызове .update без .getById не подгружается список ивентов
        // (session.merge(user) в HibernateEventRepositoryImpl не загружает вложенные сущности из БД)

        writer.println(DOC_TYPE + "\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + TITLE + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "Successfully updating:\n" + event.toString() +
                "\n</body>" +
                "\n</html>");

        resp.setStatus(resp.SC_OK);
    }
}
