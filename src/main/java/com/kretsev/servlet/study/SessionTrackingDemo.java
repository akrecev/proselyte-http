package com.kretsev.servlet.study;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class SessionTrackingDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String message = "";
        String sessionId = session.getId();
        Date sessionCreationDate = new Date(session.getCreationTime());
        Date lastSessionAccess = new Date(session.getLastAccessedTime());
        String userId = "userId";

        if (session.isNew()) {
            message = "Welcome to this page";
        } else {
            message = "Glad to see you again";
        }

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();
        String title = "Session Tracking Demo";
        String docType = "<!DOCTYPE html>";

        writer.println(docType + "\n" +
                "<html\n>" +
                "<head>\n" +
                "<title>" + title + "</title>\n" +
                "</head>" +
                "<body>\n" +
                "<h1>Session Details</h1>" + "Session ID:" + sessionId +
                "<br/>" +
                "Created: " + sessionCreationDate +
                "<br/>" +
                "Last Accessed Date: " + lastSessionAccess +
                "<br/>" +
                "User ID: " + userId +
                "<br/>" +
                "Message: " + message +
                "</body>\n" +
                "</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
