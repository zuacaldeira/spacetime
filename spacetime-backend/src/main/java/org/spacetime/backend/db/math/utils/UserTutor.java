package org.spacetime.backend.db.math.utils;

import org.spacetime.backend.db.math.operations.OperationRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeAdditionRelationship;

import java.util.List;

/**
 * Created by zua on 27/10/16.
 */
public class UserTutor extends Tutor<AlternativeAdditionRelationship> {
    private final String username;

    public UserTutor(String username) {
        super(AlternativeAdditionRelationship.class);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
