package com.kretsev.controller;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static com.kretsev.utility.GsonUtils.getMultipartFormData;

public class FileRestControllerV2 extends HttpServlet {
    private static final int MEM_MAX_SIZE = 1024 * 1024;
    private static final int FILE_MAX_SIZE = 1024 * 1024 * 5;
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 5 * 5;
    private static final String UPLOAD_DIRECTORY = "upload";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uploadPath = getServletContext().getRealPath("").replaceAll("webapp", "resources") +
                java.io.File.separator + UPLOAD_DIRECTORY +
                java.io.File.separator;

        if (ServletFileUpload.isMultipartContent(req)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(MEM_MAX_SIZE);
            factory.setRepository(new java.io.File(uploadPath));

            ServletFileUpload upload = new ServletFileUpload(factory);
            upload.setFileSizeMax(FILE_MAX_SIZE);
            upload.setSizeMax(MAX_REQUEST_SIZE);

            try {
                List<FileItem> formItems = upload.parseRequest(req);
                if (formItems != null && formItems.size() > 0) {
                    for (FileItem item : formItems) {
                        if (!item.isFormField()) {
                            String fileName = new java.io.File(item.getName()).getName();
                            String filePath = uploadPath + java.io.File.separator + fileName;
                            java.io.File storeFile = new java.io.File(filePath);
                            item.write(storeFile);
                            req.setAttribute("message", "File " + fileName + " has uploaded successfully!");
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
