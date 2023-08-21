package com.kretsev;

import com.kretsev.repository.EventRepository;
import com.kretsev.repository.FileRepository;
import com.kretsev.repository.UserRepository;
import com.kretsev.repository.hibernate.HibernateEventRepositoryImpl;
import com.kretsev.repository.hibernate.HibernateFileRepositoryImpl;
import com.kretsev.repository.hibernate.HibernateUserRepositoryImpl;

public class RunApplication {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        UserRepository userRepository = new HibernateUserRepositoryImpl();
        FileRepository fileRepository = new HibernateFileRepositoryImpl();
        EventRepository eventRepository = new HibernateEventRepositoryImpl();

        System.out.println(userRepository.findAll().size());
        System.out.println(fileRepository.findAll().size());
        System.out.println(eventRepository.findAll().size());
    }


}