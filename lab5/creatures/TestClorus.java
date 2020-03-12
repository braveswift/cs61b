package creatures;

import huglife.*;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.HashMap;

public class TestClorus {


    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus babyC = c.replicate();
        assertEquals(babyC.energy(), c.energy(), 0);
        assertNotEquals(c, babyC);


    }

    @Test
    public void testAttack() {
        Plip p = new Plip(0.5);
        Clorus c = new Clorus(1);
        c.attack(p);
        double expected = 1.5;
        double actual = c.energy();
        assertEquals(expected, actual, 0);


    }









    @Test
    public void testChooseAction() {
        //Rule 1

        Clorus newClorus = new Clorus(2);
        HashMap<Direction, Occupant> neighbors = new HashMap<Direction, Occupant>();
        neighbors.put(Direction.TOP, new Impassible());
        neighbors.put(Direction.BOTTOM, new Impassible());
        neighbors.put(Direction.LEFT, new Impassible());
        neighbors.put(Direction.RIGHT, new Impassible());

        Action actual = newClorus.chooseAction(neighbors);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        //Rule2
        newClorus = new Clorus(2);
        neighbors = new HashMap<Direction, Occupant>();
        neighbors.put(Direction.TOP, new Plip(1));
        neighbors.put(Direction.BOTTOM, new Empty());
        neighbors.put(Direction.LEFT, new Impassible());
        neighbors.put(Direction.RIGHT, new Impassible());

        actual = newClorus.chooseAction(neighbors);
        expected = new Action(Action.ActionType.ATTACK, Direction.TOP);
        assertEquals(expected, actual);


        //Rule3
        newClorus = new Clorus(2);
        neighbors = new HashMap<Direction, Occupant>();
        neighbors.put(Direction.TOP, new Impassible());
        neighbors.put(Direction.BOTTOM, new Empty());
        neighbors.put(Direction.LEFT, new Impassible());
        neighbors.put(Direction.RIGHT, new Impassible());

        actual = newClorus.chooseAction(neighbors);
        expected = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertEquals(expected, actual);

        //Rule4
        newClorus = new Clorus(0.9);
        neighbors = new HashMap<Direction, Occupant>();
        neighbors.put(Direction.TOP, new Impassible());
        neighbors.put(Direction.BOTTOM, new Empty());
        neighbors.put(Direction.LEFT, new Impassible());
        neighbors.put(Direction.RIGHT, new Impassible());

        actual = newClorus.chooseAction(neighbors);
        expected = new Action(Action.ActionType.MOVE, Direction.BOTTOM);
        assertEquals(expected, actual);

    }
}


