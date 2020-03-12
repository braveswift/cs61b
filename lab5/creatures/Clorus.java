package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;

    public Clorus(double e) {
        super("clorus");
        energy = e;
    }

    public void move() {
        energy = energy - 0.03;
    }

    public void stay() {
        energy = energy - 0.01;
    }

    public Color color() {
        r = 34;
        g = 0;
        b = 231;
       return color(r, g, b);
    }

    public void attack(Creature c) {
        energy = energy + c.energy();
    }


    public Clorus replicate() {
        energy = energy / 2;
        Clorus babyClorus = new Clorus(energy);
        return babyClorus;
    }


    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyDirection = new ArrayDeque<>();
        Deque<Direction> plipDirection  = new ArrayDeque<>();
        boolean hasPlip = false;
        for (Direction d : neighbors.keySet()) {
            if (neighbors.get(d).name().equals("empty")) {
                emptyDirection.add(d);
                continue;
            }
            if (neighbors.get(d).name().equals("plip")) {
                plipDirection.add(d);
                hasPlip = true;
            }
        }

        if (emptyDirection.size() == 0) {
            stay();
            return new Action(Action.ActionType.STAY);
        }

        if (hasPlip == true) {
            Direction random = HugLifeUtils.randomEntry(plipDirection);
            attack((Creature) neighbors.get(random));
            return new Action(Action.ActionType.ATTACK, random);
        }

        Direction random = HugLifeUtils.randomEntry(emptyDirection);
        if (energy >= 1) {
            Clorus babyClorus = replicate();
            return new Action(Action.ActionType.REPLICATE, random);
        }

        return new Action(Action.ActionType.MOVE, random);

    }




}
