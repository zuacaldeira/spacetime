package org.spacetime.backend.db;


import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.spacetime.backend.db.math.PredecessorRelationship;
import org.spacetime.backend.db.math.operations.AdditionRelationship;
import org.spacetime.backend.db.math.operations.DivisionRelationship;
import org.spacetime.backend.db.math.operations.MultiplicationRelationship;
import org.spacetime.backend.db.math.operations.SubtractionRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeAdditionRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeDivisionRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeMultiplicationRelationship;
import org.spacetime.backend.db.math.operations_alternative.AlternativeSubtractionRelationship;

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



    public static String getAlternativeAdditionRelationship(AlternativeAdditionRelationship addition) {
        return
            "MATCH (a:Operands)-[r:AlternativeAdditionRelationship]->(c:NumberNode) " +
            "WHERE a.left.value = " + addition.getOperands().getLeft().getValue() +
                " AND c.value = " + addition.getResult().getValue() +
                " AND a.right.value = " + addition.getOperands().getRight().getValue() +
                " RETURN r";

    }

    public static String getAlternativeSubtractionRelationship(AlternativeSubtractionRelationship subtraction) {
        return
                "MATCH (a:Operands)-[r:AlternativeSubtractionRelationship]->(c:NumberNode) " +
                        "WHERE a.left.value = " + subtraction.getOperands().getLeft().getValue() +
                        " AND c.value = " + subtraction.getResult().getValue() +
                        " AND a.right.value = " + subtraction.getOperands().getRight().getValue() +
                        " RETURN r";
    }

    public static String getAlternativeMultiplicationRelationship(AlternativeMultiplicationRelationship multiplication) {
        return
                "MATCH (a:Operands)-[r:AlternativeMultiplicationRelationship]->(c:NumberNode) " +
                        "WHERE a.left.value = " + multiplication.getOperands().getLeft().getValue() +
                        " AND c.value = " + multiplication.getResult().getValue() +
                        " AND a.right.value = " + multiplication.getOperands().getRight().getValue() +
                        " RETURN r";
    }

    public static String getAlternativeDivisionRelationship(AlternativeDivisionRelationship division) {
        return
                "MATCH (a:Operands)-[r:AlternativeDivisionRelationship]->(c:NumberNode) " +
                        "WHERE a.left.value = " + division.getOperands().getLeft().getValue() +
                        " AND c.value = " + division.getResult().getValue() +
                        " AND a.right.value = " + division.getOperands().getRight().getValue() +
                        " RETURN r";
    }



    public static String getPredecessorRelationship(PredecessorRelationship predecessor) {
        return
                "MATCH (a:NumberNode)-[r:PredecessorRelationship]->(c:NumberNode) " +
                        "WHERE a.value = " + predecessor.getPredecessor().getValue() +
                        " AND c.value = " + predecessor.getSuccessor().getValue() +
                        " RETURN r";
    }

    public static String getAllAdditionsQuery() {
        return "MATCH p=(a)-[r:`+`]->(c) RETURN r";
    }

    public static String createPrimeNode() {
        return "CREATE (p:PrimeNode) RETURN p";
    }

    public static String loadFromNumbersQuery() {
        String PERIODIC_COMMIT = "USING PERIODIC COMMIT 1000\n";
        String LOAD_CSV = "LOAD CSV\n";
        String HEADER = "WITH HEADERS FROM 'https://raw.githubusercontent.com/zuacaldeira/spacetime/master/spacetime-backend/numbers.csv' AS line\n";
        String CREATE_NODES = "MERGE (n:NumberNode { value: toInt(line.value)})\n";
        String RETURN = "RETURN n\n";
        String query = PERIODIC_COMMIT + LOAD_CSV + HEADER + CREATE_NODES + RETURN;
        System.out.println("Query: " + query);
        return query;
    }

    public static String createSuccessors() {
        String query = "MATCH (n:NumberNode), (m:NumberNode) WHERE m.value=n.value+1 MERGE (n)-[:PredecessorRelationship]->(m)";
        return query;
    }

    public static String createOperands() {
        String query =
            "MATCH (n:NumberNode), (m:NumberNode) " +
            "MERGE (o:Operands), (n)-[:LEFT]->(o), (m)-[:RIGHT]->(o)";
        return query;
    }

    public static String createMultiplications() {
        String query = "MATCH (p:NumberNode), (n)-[:LEFT]->(o), (m)-[:RIGHT]->(o)  WHERE p.value=n.value*m.value  MERGE (o)-[r:Multiplication]->(p)";
        return query;
    }
}
