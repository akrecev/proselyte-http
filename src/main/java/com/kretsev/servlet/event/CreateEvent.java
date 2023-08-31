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

public class CreateEvent extends HttpServlet {
    private final static String CONTENT_TYPE = "text/html";
    private final static String DOC_TYPE = "<!DOCTYPE html>";
    private final static String TITLE = "Create Event";

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

        Integer userId = Integer.parseInt(req.getParameter("userId"));
        User user = getUserService().getById(userId);

        Integer fileId = Integer.parseInt(req.getParameter("fileId"));
        File file = getFileService().getById(fileId);

        Event event = Event.builder().user(user).file(file).build();
        event = getEventService().create(event);

        writer.println(DOC_TYPE + "\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + TITLE + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "Successfully creating:\n" + event.toString() +
                "\n</body>" +
                "\n</html>");

        resp.setStatus(resp.SC_OK);
    }
}
