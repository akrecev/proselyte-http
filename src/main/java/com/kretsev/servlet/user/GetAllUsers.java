package com.kretsev.servlet.user;

import com.kretsev.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import static com.kretsev.context.ApplicationContext.getUserService;

public class GetAllUsers extends HttpServlet {
    private final static String CONTENT_TYPE = "text/html";
    private final static String DOC_TYPE = "<!DOCTYPE html>";
    private final static String TITLE = "Get All Users";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        List<User> users = getUserService().getAll();

        writer.println(DOC_TYPE + "\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + TITLE + "</title>\n" +
                "</head>\n" +
                "<body>\n");
        for (User user : users) {
            writer.println("<p>" + user + "</p>");
        }
        writer.println("\n</body>" +
                "\n</html>");

        resp.setStatus(resp.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    }
}
