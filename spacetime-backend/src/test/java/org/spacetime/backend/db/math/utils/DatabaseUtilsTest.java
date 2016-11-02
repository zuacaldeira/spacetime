package org.spacetime.backend.db.math.utils;

import org.spacetime.backend.utils.DatabaseUtils;
import org.testng.annotations.Test;

import java.util.List;

/**
 * Created by zua on 28/10/16.
 */
public class DatabaseUtilsTest {
    //@Test
    public void testLoadAllProblems() throws Exception {
        List problems = DatabaseUtils.loadAllProblems();
        System.out.println("#Problems = " + problems);
        problems.forEach(p -> {
            System.out.println(p);
        });
    }

}