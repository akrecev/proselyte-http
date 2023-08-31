package com.kretsev.servlet.user;

import com.kretsev.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static com.kretsev.context.ApplicationContext.getUserService;

public class UpdateUser extends HttpServlet {
    private final static String CONTENT_TYPE = "text/html";
    private final static String DOC_TYPE = "<!DOCTYPE html>";
    private final static String TITLE = "Update User";

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

        Integer userId = Integer.parseInt(req.getParameter("id")) ;
        String userName = req.getParameter("name");
        User user = User.builder().id(userId).name(userName).build();
        user = getUserService().update(user);
        user = getUserService().getById(user.getId());
        //TODO при вызове .update без .getById не подгружается список ивентов
        // (session.merge(user) в HibernateUserRepositoryImpl не загружает вложенные сущности из БД)

        writer.println(DOC_TYPE + "\n" +
                "<html>\n" +
                "<head>\n" +
                "<title>" + TITLE + "</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "Successfully updating:\n" + user.toString() +
                "\n</body>" +
                "\n</html>");

        resp.setStatus(resp.SC_OK);
    }
}
