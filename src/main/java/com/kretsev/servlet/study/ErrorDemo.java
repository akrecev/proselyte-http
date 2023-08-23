package com.kretsev.servlet.study;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class ErrorDemo extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer code = (Integer) request.getAttribute("javax.servlet.error.status_code");
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Error handling";
        String docType = "<!DOCTYPE html>";

        writer.println(docType + "\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + title + "</title>" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Error information</h1>\n" +
                "Code: " + code + "\n" +
                "</body>" +
                "</html>"
        );
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
