package com.kretsev.controller;

import com.kretsev.model.File;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static com.kretsev.context.ApplicationContext.getFileService;
import static com.kretsev.utility.GsonUtils.getApplicationJson;
import static com.kretsev.utility.GsonUtils.getGSON;

public class FileRestControllerV1 extends HttpServlet {
    private static final int FILE_MAX_SIZE = 1024 * 1024;
    private static final int MEM_MAX_SIZE = 1024 * 1024;
    private static final String UPLOAD_DIRECTORY = "upload";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getApplicationJson());
        PrintWriter writer = resp.getWriter();
        String jsonResult;
        String parameter = req.getParameter("id");

        if (parameter != null && !parameter.isBlank()) {
            Integer id = Integer.parseInt(parameter);
            File file = getFileService().getById(id);
            jsonResult = getGSON().toJson(file);
        } else {
            List<File> files = getFileService().getAll();
            jsonResult = getGSON().toJson(files);
        }
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("").replaceAll("webapp", "resources") +
                java.io.File.separator + UPLOAD_DIRECTORY + java.io.File.separator;
        String fileName = null;

        if (ServletFileUpload.isMultipartContent(req)) {
            resp.setContentType(getApplicationJson());
            PrintWriter writer = resp.getWriter();

            DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
            diskFileItemFactory.setRepository(new java.io.File(uploadPath));
            diskFileItemFactory.setSizeThreshold(MEM_MAX_SIZE);

            ServletFileUpload upload = new ServletFileUpload(diskFileItemFactory);
            upload.setSizeMax(FILE_MAX_SIZE);

            try {
                List<FileItem> formItems = upload.parseRequest(req);
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            fileName = new java.io.File(item.getName()).getName();
                            String filePath = uploadPath + java.io.File.separator + fileName;
                            java.io.File storeFile = new java.io.File(filePath);
                            item.write(storeFile);
                            req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
                        }
                    }
                }
                File uploadedFile = File.builder()
                        .name(fileName)
                        .filePath(uploadPath.replace('\\', '/'))
                        .build();
                uploadedFile = getFileService().create(uploadedFile);

                writer.println(getGSON().toJson(uploadedFile));
                writer.flush();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getApplicationJson());
        PrintWriter writer = resp.getWriter();
        String requestBody = req.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
        File file = getGSON().fromJson(requestBody, File.class);
        file = getFileService().update(file);
        String jsonResult = getGSON().toJson(file);
        writer.println(jsonResult);
        writer.flush();
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType(getApplicationJson());
        Integer fileId = Integer.parseInt(req.getParameter("id"));
        getFileService().deleteById(fileId);
    }
}
