package com.kretsev.servlet.study;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

public class AutoRefreshDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setIntHeader("Refresh", 1);
        response.setContentType("text/html");

        Date currentDateTime = new Date();

        String docType = "<!DOCTYPE html>";
        String title = "Auto Refresh Demo";

        PrintWriter writer = response.getWriter();

        writer.println(docType + "\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + title + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Current date and time: </h1>" + currentDateTime + "\n" +
                "</body>\n" +
                "</html>");
    }
}
