import org.junit.Test;

public class testUnionFind {


    public static void main(String[] args) {

        UnionFind ds = new UnionFind(5);
        ds.union(0, 1);
        ds.union(0, 2);
        ds.union(2, 3);
        System.out.println(ds.sizeOf(2));
        System.out.println(ds.parent(3));
        System.out.println(ds.parent(1));






    }

}
