package com.kretsev.servlet.study;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class DatabaseDemo extends HttpServlet {
    private static final String JDBC_DRIVER = "org.h2.Driver";
    private static final String DATABASE_URL = "jdbc:h2:file:./db/http";
    private static final String DATABASE_USER = "user";
    private static final String DATABASE_PASSWORD = "password";
    private static final String GET_ALL_USERS_RECORDS = "SELECT * FROM USERS";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();

        String title = "Database Demo";
        String docType = "<!DOCTYPE html>";

        try {
            Class.forName(JDBC_DRIVER);
            Connection connection = DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_ALL_USERS_RECORDS);

            writer.println(docType + "\n" + "<html>\n<hear>\n<title>" + title + "</title>\n</head>\n<body>");
            writer.println("<h1>USERS DATA</h1>");
            writer.println("<br/>");
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");

                writer.println("ID: " + id);
                writer.println("NAME: " + name);
                writer.println("<br/>");
            }
            resultSet.close();
            statement.close();
            connection.close();
            writer.println("</body>\n</html>");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
