package bearmaps.hw4;

/**
 * Utility class that represents a weighted edge.
 * Created by hug.
 */
public class WeightedEdge<T> {
    private T v;
    private T w;
    private double weight;

    public WeightedEdge(T v, T w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }
    public T from() {
        return v;
    }
    public T to() {
        return w;
    }
    public double weight() {
        return weight;
    }
}
