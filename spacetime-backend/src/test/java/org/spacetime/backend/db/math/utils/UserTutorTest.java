package org.spacetime.backend.db.math.utils;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.pattern.Patterns;
import akka.testkit.JavaTestKit;

import akka.util.Timeout;
import org.spacetime.backend.db.math.operations_alternative.*;
import org.testng.annotations.Test;
import scala.concurrent.Await;
import scala.concurrent.Future;
import scala.concurrent.duration.FiniteDuration;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by zua on 27/10/16.
 */
public class UserTutorTest extends AbstractActorTest {

    private static final String USERNAME = "zuacaldeira@gmail.com";
    private int numberOfProblems = 0;

    @Test
    public void loadAdditionsTest() throws Exception {
        ActorSystem system = getActorSystem();
        new JavaTestKit(system) {
            {
                ActorRef additionActor = system.actorOf(Props.create(AdditionLoaderActor.class));
                Timeout t = new Timeout(new FiniteDuration(5, TimeUnit.MINUTES));
                Future<Object> futureAdditions = Patterns.ask(additionActor,
                        new LoadProblemsMessage<AlternativeAdditionRelationship>(AlternativeAdditionRelationship.class),
                        t);
                Object resultA = Await.result(futureAdditions, t.duration());
                System.out.println("Partial Data " + resultA);
            }
        };
    }

    @Test
    public void loadSubtractionsTest() throws Exception {
        ActorSystem system = getActorSystem();
        new JavaTestKit(system) {
            {
                Timeout t = new Timeout(new FiniteDuration(5, TimeUnit.MINUTES));
                ActorRef subtractionActor = system.actorOf(Props.create(SubtractionLoaderActor.class));
                Future<Object> futureSubtractions = Patterns.ask(subtractionActor,
                        new LoadProblemsMessage<AlternativeSubtractionRelationship>(AlternativeSubtractionRelationship.class),
                        t);
                Object resultA = Await.result(futureSubtractions, t.duration());
                System.out.println("Partial Data " + resultA);
            }
        };
    }

    @Test
    public void loadMultiplicationsTest() throws Exception {
        ActorSystem system = getActorSystem();
        new JavaTestKit(system) {
            {
                Timeout t = new Timeout(new FiniteDuration(5, TimeUnit.MINUTES));
                ActorRef multiplicationActor = system.actorOf(Props.create(MultiplicationLoaderActor.class));
                Future<Object> futureMultiplications = Patterns.ask(multiplicationActor,
                        new LoadProblemsMessage<AlternativeMultiplicationRelationship>(AlternativeMultiplicationRelationship.class),
                        t);
                Object resultA = Await.result(futureMultiplications, t.duration());
                System.out.println("Partial Data " + resultA);
            }
        };
    }

    @Test
    public void loadDivisionsTest() throws Exception {
        ActorSystem system = getActorSystem();
        new JavaTestKit(system) {
            {
                Timeout t = new Timeout(new FiniteDuration(5, TimeUnit.MINUTES));
                ActorRef divisionActor = system.actorOf(Props.create(DivisionLoaderActor.class));
                Future<Object> futureDivisions = Patterns.ask(divisionActor,
                        new LoadProblemsMessage<AlternativeDivisionRelationship>(AlternativeDivisionRelationship.class),
                        t);
                Object resultA = Await.result(futureDivisions, t.duration());
                System.out.println("Partial Data " + resultA);
            }
        };
    }

    private void createDivisionTutor() {
        UserTutor divisionTutor = new UserTutor<AlternativeDivisionRelationship>(AlternativeDivisionRelationship.class, USERNAME);
        Iterator<AlternativeDivisionRelationship> it = divisionTutor.iterator();
        numberOfProblems += divisionTutor.getProblems().size();
        System.out.println("Tutor Initiated with " + divisionTutor.getProblems().size() + " problems.");
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    private void createMultiplicationTutor() {
        UserTutor multiplicationTutor = new UserTutor<AlternativeMultiplicationRelationship>(AlternativeMultiplicationRelationship.class, USERNAME);
        Iterator<AlternativeMultiplicationRelationship> it = multiplicationTutor.iterator();
        numberOfProblems += multiplicationTutor.getProblems().size();
        System.out.println("Tutor Initiated with " + multiplicationTutor.getProblems().size() + " problems.");
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    private void createSubtractionTutor() {
        UserTutor subtractionTutor = new UserTutor<AlternativeSubtractionRelationship>(AlternativeSubtractionRelationship.class, USERNAME);
        Iterator<AlternativeSubtractionRelationship> it = subtractionTutor.iterator();
        numberOfProblems += subtractionTutor.getProblems().size();
        System.out.println("Tutor Initiated with " + subtractionTutor.getProblems().size() + " problems.");
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }

    private void createAdditionTutor() {
        UserTutor addditionTutor = new UserTutor<AlternativeAdditionRelationship>(AlternativeAdditionRelationship.class, USERNAME);
        Iterator<AlternativeAdditionRelationship> it = addditionTutor.iterator();
        numberOfProblems += addditionTutor.getProblems().size();
        System.out.println("Tutor Initiated with " + addditionTutor.getProblems().size() + " problems.");
        while(it.hasNext()) {
            System.out.println(it.next());
        }
    }
}