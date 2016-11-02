package org.spacetime.backend.services;

import org.spacetime.backend.db.relationships.OperationRelationship;

/**
 * Created by zua on 27/10/16.
 */
public class UserTutor<T extends OperationRelationship> extends Tutor<T> {
    private final String username;

    public UserTutor(Class<T> aClass, String username) {
        super(aClass);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
