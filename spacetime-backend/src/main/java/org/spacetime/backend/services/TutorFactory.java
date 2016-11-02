package org.spacetime.backend.services;

/**
 * Created by zua on 27/10/16.
 */
public class TutorFactory {

    private TutorFactory() {
    }

    public static UserTutor createTutorForUser(String username) {
        return null;//new UserTutor(username);
    }
}
