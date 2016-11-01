package org.spacetime.backend.db.math.utils;

import org.spacetime.backend.db.math.operations_alternative.AlternativeOperationRelationship;

/**
 * Created by zua on 27/10/16.
 */
public class UserTutor<T extends AlternativeOperationRelationship> extends Tutor<T> {
    private final String username;

    public UserTutor(Class<T> aClass, String username) {
        super(aClass);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
