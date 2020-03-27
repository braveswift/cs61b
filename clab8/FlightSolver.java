import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {
    ArrayList<Flight> flights;

    public FlightSolver(ArrayList<Flight> flights) {
        this.flights = flights;
    }

    public int solve() {
        Comparator<Flight> byStartTime = (Flight f1, Flight f2) -> {
            return Integer.compare(f1.startTime(), f2.startTime());
        };
        Comparator<Flight> byEndTime = (Flight f1, Flight f2) -> {
            return Integer.compare(f1.endTime(), f2.endTime());
        };

        PriorityQueue<Flight> pqByStartTime = new PriorityQueue<>(byStartTime);
        PriorityQueue<Flight> pqByEndTime = new PriorityQueue<>(byEndTime);
        for (Flight flight : flights) {
            pqByStartTime.add(flight);
            pqByEndTime.add(flight);
        }

        int largestNum = 0;
        int onBoardNum = 0;


        while (pqByStartTime.peek() != null && pqByEndTime.peek() != null) {
            if (pqByStartTime.peek().startTime() < pqByEndTime.peek().endTime()) {
                onBoardNum = onBoardNum + pqByStartTime.poll().passengers();
                largestNum = Math.max(largestNum, onBoardNum);
            } else if (pqByStartTime.peek().startTime() > pqByEndTime.peek().endTime()) {
                onBoardNum = onBoardNum - pqByEndTime.poll().passengers();
            } else {
                onBoardNum = onBoardNum + pqByStartTime.poll().passengers();
            }
        }
        return largestNum;

    }

}
