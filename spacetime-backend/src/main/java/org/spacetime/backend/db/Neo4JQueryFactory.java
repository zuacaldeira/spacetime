package org.spacetime.backend.db;


import org.spacetime.backend.db.math.AdditionRelationship;
import org.spacetime.backend.db.math.DivisionRelationship;
import org.spacetime.backend.db.math.MultiplicationRelationship;
import org.spacetime.backend.db.math.SubtractionRelationship;

/**
 * Created by zua on 26/10/16.
 */
public class Neo4JQueryFactory {

    private static final String MATCH = "MATCH";
    private static final String SPACE = " ";
    private static final String WHERE = "WHERE";
    private static final String NUMBER_NODE = "(n:NumberNode)";
    private static final String WHERE_COND = "n.value = ";

    public static String getNumberNodeWithValue(int i) {
        String query =
                "MATCH (n:NumberNode) " +
                "WHERE n.value = " + i + " " +
                "RETURN n";
        return query;
    }

    public static String cleanDB() {
        return
            "MATCH (n) " +
            "OPTIONAL MATCH (n)-[r]-() " +
            "DELETE n,r";
    }

    public static String getAdditionRelationship(AdditionRelationship addition) {
        return
            "MATCH (a:NumberNode)-[r:AdditionRelationship]->(c:NumberNode) " +
            "WHERE a.value = " + addition.getLeft().getValue() +
                " AND c.value = " + addition.getResult().getValue() +
                " AND r.right.value = " + addition.getRight().getValue() +
                " RETURN r";

    }

    public static String getSubtractionRelationship(SubtractionRelationship subtraction) {
        return
                "MATCH (a:NumberNode)-[r:SubtractionRelationship]->(c:NumberNode) " +
                        "WHERE a.value = " + subtraction.getLeft().getValue() +
                        " AND c.value = " + subtraction.getResult().getValue() +
                        " AND r.right.value = " + subtraction.getRight().getValue() +
                        " RETURN r";
    }

    public static String getMultiplicationRelationship(MultiplicationRelationship multiplication) {
        return
                "MATCH (a:NumberNode)-[r:MultiplicationRelationship]->(c:NumberNode) " +
                        "WHERE a.value = " + multiplication.getLeft().getValue() +
                        " AND c.value = " + multiplication.getResult().getValue() +
                        " AND r.right.value = " + multiplication.getRight().getValue() +
                        " RETURN r";
    }

    public static String getDivisionRelationship(DivisionRelationship division) {
        return
                "MATCH (a:NumberNode)-[r:DivisionRelationship]->(c:NumberNode) " +
                        "WHERE a.value = " + division.getLeft().getValue() +
                        " AND c.value = " + division.getResult().getValue() +
                        " AND r.right.value = " + division.getRight().getValue() +
                        " RETURN r";
    }
}
