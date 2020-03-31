package bearmaps;

import java.util.LinkedList;
import java.util.List;

public class KDTree implements PointSet{
    private Node root;

    private class Node {
        private static final boolean isHorizontal = true;
        private Point point;
        private Node left;
        private Node right;

        public Node(Point point, Node left, Node right, boolean isHorizontal) {
            this.point = point;
            this.left = left;
            this.right = right;
        }
    }

    public KDTree(List<Point> points) {
        List<Point> myPoints = new LinkedList<>(points);

        for (Point point : myPoints) {
            root = put(point, root, true);
        }
    }

    private Node put(Point point, Node node, Boolean isHorizontal) {
        if (node == null) {
            return new Node(point, null, null, isHorizontal);
        }
        if (comparePoint(point, node.point, isHorizontal) < 0) {
            node.left = put(point, node.left, !isHorizontal);
        } else {
            node.right = put(point, node.right, !isHorizontal);
        }
        return node;
    }

    private int comparePoint(Point p1, Point p2, boolean isHorizontal) {
        if (isHorizontal) {
            return Double.compare(p1.getX(), p2.getX());
        } else {
            return Double.compare(p1.getY(), p2.getY());
        }
    }

    @Override
    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Node best = nearHelper(root, goal, root, true);
        return best.point;
    }

    private Node nearHelper(Node n, Point goal, Node best, boolean isHorizontal) {
        if (n == null) {
            return best;
        }
        if (Point.distance(n.point, goal) < Point.distance(best.point, goal)) {
            best = n;
        }
        Node goodSide;
        Node badSide;
        if (comparePoint(goal, n.point, isHorizontal) < 0) {
            goodSide = n.left;
            badSide = n.right;
        } else {
            goodSide = n.right;
            badSide = n.left;
        }
        best = nearHelper(goodSide, goal, best, !isHorizontal);
        Point bestBadPoint = bestBadPoint(n, goal, isHorizontal);
        if (Point.distance(goal, bestBadPoint) < Point.distance(goal, best.point)) {
            best = nearHelper(badSide, goal, best, !isHorizontal);
        }
        return best;
    }


    private Point bestBadPoint(Node n, Point goal, Boolean isHorizontal) {
        Point bestBadPoint;
        if (isHorizontal) {
            bestBadPoint = new Point(n.point.getX(), goal.getY());
        } else {
          bestBadPoint = new Point(goal.getX(), n.point.getY());
        }
        return bestBadPoint;
    }


    public static void main(String[] args) {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 5);
        Point p4 = new Point(3, 3);
        Point p5 = new Point(1, 5);
        Point p6 = new Point(4, 4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6));

    }


}
