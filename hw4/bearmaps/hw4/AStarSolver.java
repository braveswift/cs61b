package bearmaps.hw4;

import java.util.*;

import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;


public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private List<Vertex> solution;
    private double solutionWeight;
    private int numStatesExplored;
    private double explorationTime;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        solution = new ArrayList<>();
        solutionWeight = 0;

        HashMap<Vertex,Double> distTo  = new HashMap<>();
        distTo.put(start, 0.0);
        HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
        ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
        pq.add(start, 0);

        while (pq.size() != 0 && !pq.getSmallest().equals(end) && sw.elapsedTime() < timeout ) {
            Vertex curr = pq.removeSmallest();
            numStatesExplored += 1;
            if (sw.elapsedTime() > timeout) {
                outcome = SolverOutcome.TIMEOUT;
                explorationTime = sw.elapsedTime();
                return;
            }
            List<WeightedEdge<Vertex>> weightedEdges = input.neighbors(curr);
            for (WeightedEdge<Vertex> edge : weightedEdges) {
                Vertex to = edge.to();
                double poDistan = distTo.get(curr) + edge.weight();
                if (!distTo.containsKey(to)) {
                    distTo.put(to, poDistan);
                    edgeTo.put(to, curr);
                    pq.add(to, distTo.get(to) + input.estimatedDistanceToGoal(to, end));
                } else if (poDistan < distTo.get(to)) {
                    distTo.put(to, poDistan);
                    edgeTo.put(to, curr);
                    pq.changePriority(to, distTo.get(to) + input.estimatedDistanceToGoal(to, end));
                }
            }
        }

        if (sw.elapsedTime() >= timeout) {
            outcome = SolverOutcome.TIMEOUT;
            explorationTime = sw.elapsedTime();
        } else if (pq.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
            explorationTime = sw.elapsedTime();
        } else {
            outcome = SolverOutcome.SOLVED;
            solutionWeight = distTo.get(end);
            solution.add(end);
            Vertex target = end;
            while (!edgeTo.get(target).equals(start)) {
                solution.add(edgeTo.get(target));
                target = edgeTo.get(target);
            }
            solution.add(start);
            Collections.reverse(solution);
            explorationTime = sw.elapsedTime();
        }
    }

    public SolverOutcome outcome() {
        return outcome;
    }

    public List<Vertex> solution() {
        return solution;
    }

    public double solutionWeight() {
        return solutionWeight;
    }

    public int numStatesExplored() {
        return numStatesExplored;
    }

    public double explorationTime() {
        return explorationTime;
    }
}
