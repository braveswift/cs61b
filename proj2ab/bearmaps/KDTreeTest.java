package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {

    @Test
    public void testNearestDemo() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point actual = kd.nearest(0, 7);
        Point expected = new Point(1, 5);
        assertEquals(expected, actual);
    }

    @Test
    public void testWithRandom() {
        List<Point>  pointsList = randomPointList(100000);
        KDTree kd = new KDTree(pointsList);
        NaivePointSet nps = new NaivePointSet(pointsList);
        List<Point>  goalPointsList = randomPointList(1000);
        for (Point point : goalPointsList) {
            Point expexted = nps.nearest(point.getX(), point.getY());
            Point actual = kd.nearest(point.getX(), point.getY());
            assertEquals(expexted, actual);
        }
    }

    @Test
    public void testRunTime() {
        List<Point>  pointsList = randomPointList(100000);
        List<Point>  goalPointsList = randomPointList(1000);
        Stopwatch sw1 = new Stopwatch();
        KDTree kd = new KDTree(pointsList);
        for (Point point : goalPointsList) {
            kd.nearest(point.getX(), point.getY());
        }
        System.out.println("Total time elapsed for KDTree: " + sw1.elapsedTime() +  " seconds.");

        Stopwatch sw2 = new Stopwatch();
        NaivePointSet nps = new NaivePointSet(pointsList);
        for (Point point : goalPointsList) {
            nps.nearest(point.getX(), point.getY());
        }
        System.out.println("Total time elapsed for NaivePST: " + sw2.elapsedTime() +  " seconds.");
    }


    private List<Point> randomPointList(int N) {
        Random random = new Random(500);
        List<Point> pointsList = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            double x = random.nextDouble();
            double y = random.nextDouble();
            Point p = new Point(x, y);
            pointsList.add(p);
        }
        return pointsList;
    }



}
