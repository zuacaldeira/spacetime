package org.spacetime.backend.utils;


import org.spacetime.backend.db.relationships.Successor;
import org.spacetime.backend.db.relationships.Addition;
import org.spacetime.backend.db.relationships.Division;
import org.spacetime.backend.db.relationships.Multiplication;
import org.spacetime.backend.db.relationships.Subtraction;

/**
 * Created by zua on 26/10/16.
 */
public class Neo4JQueryFactory {

    private static final String MATCH = "MATCH";
    private static final String SPACE = " ";
    private static final String WHERE = "WHERE";
    private static final String NUMBER_NODE = "(n:NumberNode)";
    private static final String WHERE_COND = "n.value = ";
    private static int N = 0;

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



    public static String getAlternativeAdditionRelationship(Addition addition) {
        return
            "MATCH (a:Operands)-[r:Addition]->(c:NumberNode) " +
            "WHERE a.left.value = " + addition.getOperands().getLeft().getValue() +
                " AND c.value = " + addition.getResult().getValue() +
                " AND a.right.value = " + addition.getOperands().getRight().getValue() +
                " RETURN r";

    }

    public static String getAlternativeSubtractionRelationship(Subtraction subtraction) {
        return
                "MATCH (a:Operands)-[r:Subtraction]->(c:NumberNode) " +
                        "WHERE a.left.value = " + subtraction.getOperands().getLeft().getValue() +
                        " AND c.value = " + subtraction.getResult().getValue() +
                        " AND a.right.value = " + subtraction.getOperands().getRight().getValue() +
                        " RETURN r";
    }

    public static String getAlternativeMultiplicationRelationship(Multiplication multiplication) {
        return
                "MATCH (a:Operands)-[r:Multiplication]->(c:NumberNode) " +
                        "WHERE a.left.value = " + multiplication.getOperands().getLeft().getValue() +
                        " AND c.value = " + multiplication.getResult().getValue() +
                        " AND a.right.value = " + multiplication.getOperands().getRight().getValue() +
                        " RETURN r";
    }

    public static String getAlternativeDivisionRelationship(Division division) {
        return
                "MATCH (a:Operands)-[r:Division]->(c:NumberNode) " +
                        "WHERE a.left.value = " + division.getOperands().getLeft().getValue() +
                        " AND c.value = " + division.getResult().getValue() +
                        " AND a.right.value = " + division.getOperands().getRight().getValue() +
                        " RETURN r";
    }



    public static String getPredecessorRelationship(Successor predecessor) {
        return
                "MATCH (a:NumberNode)-[r:SUC_BY]->(c:NumberNode) " +
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

    public static String addUniqueConstraint() {
        return "CREATE CONSTRAINT ON (nn:NumberNode) ASSERT nn.value IS UNIQUE\n";
    }
    public static String deleteDatabase() {
        return
            "MATCH (n)\n" +
            "WITH n LIMIT 10000\n" +
            "OPTIONAL MATCH (n)-[r]->()\n" +
            "DELETE n,r";
    }
    public static String loadFromNumbersQuery(int n) {
        N = n;
        String PERIODIC_COMMIT = "USING PERIODIC COMMIT 1000\n";
        String LOAD_CSV = "LOAD CSV\n";
        String HEADER = "WITH HEADERS FROM 'https://raw.githubusercontent.com/zuacaldeira/spacetime/master/spacetime-backend/config/db/f"+N+".csv' AS line\n";
        String CREATE_NODES = "CREATE (n:NumberNode { value: toInt(line.value)})\n";
        String RETURN = "RETURN n\n";
        String query = PERIODIC_COMMIT + LOAD_CSV + HEADER + CREATE_NODES + RETURN;
        System.out.println("Query: " + query);
        return query;
    }

    public static String createSuccessors() {
        String query = "MATCH (n:NumberNode), (m:NumberNode) WHERE m.value=n.value+1 CREATE (n)-[:SUC_BY]->(m)";
        System.out.println("Query: " + query);
        return query;
    }

    public static String createMultiplications() {
        String query =
            "MATCH (n:NumberNode), (m:NumberNode), (p:NumberNode)\n" +
            "WHERE p.value = n.value*m.value <= " + N + "\n" +
            "MERGE (o:Operands{left: n.value, right: m.value})\n" +
            "CREATE (o)-[:Multiplication]->(p)";
        System.out.println("Query: " + query);
        return query;
    }

    public static String createAdditions() {
        String query =
                "MATCH (n:NumberNode), (m:NumberNode), (p:NumberNode)\n" +
                        "WHERE p.value = n.value+m.value <= " + N + "\n" +
                        "MERGE (o:Operands{left: n.value, right: m.value})\n" +
                        "MERGE (o)-[:Addition]->(p)";
        System.out.println("Query: " + query);
        return query;
    }

    public static String createDivisions() {
        String query =
                "MATCH (n:NumberNode), (m:NumberNode), (p:NumberNode)\n" +
                        "WHERE m.value <> 0 AND n.value%m.value = 0 AND  p.value = n.value/m.value \n" +
                        "MERGE (o:Operands{left: n.value, right: m.value})\n" +
                        "MERGE (o)-[:Division]->(p)";
        System.out.println("Query: " + query);
        return query;
    }

    public static String createPrimes() {
        String query =
                "MATCH (o:Operands)-[m:Multiplication]->(n:NumberNode)\n" +
                "WITH DISTINCT(n) as n, count(m) as NumberOfMultiplications\n" +
                "WHERE NumberOfMultiplications=2\n" +
                "CREATE (p:PrimeNode)\n" +
                "MERGE (n)-[:IS_PRIME]->(p)\n";
                System.out.println("Query: " + query);
        return query;
    }

    public static String createSubtractions() {
        String query =
                "MATCH (n:NumberNode), (m:NumberNode), (p:NumberNode)\n" +
                        "WHERE p.value = n.value-m.value >= " + 0 + "\n" +
                        "MERGE (o:Operands{left: n.value, right: m.value})\n" +
                        "MERGE (o)-[:Subtraction]->(p)";
        System.out.println("Query: " + query);
        return query;
    }







    public static String createMultiplicationsDisconnected() {
        String query =
                "MATCH (n:NumberNode), (m:NumberNode), (p:NumberNode)\n" +
                        "WHERE p.value = n.value*m.value <= " + N + "\n" +
                        "CREATE (o:Operands{left: n.value, right: m.value})\n" +
                        "MERGE (o)-[:Multiplication]->(p)";
        System.out.println("Query: " + query);
        return query;
    }

    public static String createAdditionsDisconnected() {
        String query =
                "MATCH (n:NumberNode), (m:NumberNode), (p:NumberNode)\n" +
                        "WHERE p.value = n.value+m.value <= " + N + "\n" +
                        "CREATE (o:Operands{left: n.value, right: m.value})\n" +
                        "MERGE (o)-[:Addition]->(p)";
        System.out.println("Query: " + query);
        return query;
    }

    public static String createDivisionsDisconnected() {
        String query =
                "MATCH (n:NumberNode), (m:NumberNode), (p:NumberNode)\n" +
                        "WHERE m.value <> 0 AND n.value%m.value = 0 AND  p.value = n.value/m.value \n" +
                        "CREATE (o:Operands{left: n.value, right: m.value})\n" +
                        "MERGE (o)-[:Division]->(p)";
        System.out.println("Query: " + query);
        return query;
    }

    public static String createSubtractionsDisconnected() {
        String query =
                "MATCH (n:NumberNode), (m:NumberNode), (p:NumberNode)\n" +
                        "WHERE p.value = n.value-m.value >= " + 0 + "\n" +
                        "CREATE (o:Operands{left: n.value, right: m.value})\n" +
                        "MERGE (o)-[:Subtraction]->(p)";
        System.out.println("Query: " + query);
        return query;
    }



}
