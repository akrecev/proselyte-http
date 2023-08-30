package com.kretsev.context;

import com.kretsev.repository.hibernate.HibernateEventRepositoryImpl;
import com.kretsev.repository.hibernate.HibernateFileRepositoryImpl;
import com.kretsev.repository.hibernate.HibernateUserRepositoryImpl;
import com.kretsev.service.EventService;
import com.kretsev.service.FileService;
import com.kretsev.service.UserService;
import com.kretsev.service.impl.EventServiceImpl;
import com.kretsev.service.impl.FileServiceImpl;
import com.kretsev.service.impl.UserServiceImpl;

public class ApplicationContext {
    private static UserService userService;
    private static FileService fileService;
    private static EventService eventService;

    public static synchronized UserService getUserService() {
        if (userService == null) {
            userService = new UserServiceImpl(new HibernateUserRepositoryImpl());
        }

        return userService;
    }

    public static synchronized FileService getFileService() {
        if (fileService == null) {
            fileService = new FileServiceImpl(new HibernateFileRepositoryImpl());
        }

        return fileService;
    }

    public static synchronized EventService getEventService() {
        if (eventService == null) {
            eventService = new EventServiceImpl(new HibernateEventRepositoryImpl());
        }

        return eventService;
    }

}
