package com.kretsev.utility;

import com.kretsev.model.Event;
import com.kretsev.model.File;
import com.kretsev.model.User;

import java.util.Collections;

public class TestUtils {
    private final static File testFile = File.builder()
            .id(10)
            .name("Test.txt")
            .filePath("D:/kretsev/study/dev/proselyte/2.4/proselyte-http/src/main/resources/upload/")
            .build();

    private final static User testUser = User.builder()
            .id(10)
            .name("Test User")
            .build();

    private final static Event testEvent = Event.builder()
            .id(10)
            .user(testUser)
            .file(testFile)
            .build();

    public static File getTestFile() {
        return testFile;
    }

    public static User getTestUser() {
        testUser.setEvents(Collections.singleton(testEvent));

        return testUser;
    }

    public static Event getTestEvent() {
        return testEvent;
    }
}
