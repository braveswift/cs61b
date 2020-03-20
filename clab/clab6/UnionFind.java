import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnionFind {

    private int[] parentArray;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */
    public UnionFind(int n) {
        parentArray = new int[n];
        Arrays.fill(parentArray, -1);
    }

    /* Throws an exception if v1 is not a valid index. */
    private void validate(int vertex) {
        if (vertex >= parentArray.length || vertex < 0) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */
    public int sizeOf(int v1) {
        int root = find(v1);
        return -parent(root);
    }



    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */
    public int parent(int v1) {
        return parentArray[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */
    public boolean connected(int v1, int v2) {
        return (find(v1) == find(v2));
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid 
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a 
       vertex with itself or vertices that are already connected should not 
       change the sets but may alter the internal structure of the data. */
    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        if (!connected(v1,v2)) {
            int root1 = find(v1);
            int root2 = find(v2);
            int size1 = sizeOf(root1);
            int size2 = sizeOf(root2);
            if (size1 <= size2) {
                parentArray[root1] = root2;
                parentArray[root2] = - (size1 + size2);
            } else {
                parentArray[root2] = root1;
                parentArray[root1] = - (size1 + size2);
            }

        }
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */
    public int find(int vertex) {
        while (parentArray[vertex] >= 0) {
            vertex = parentArray[vertex];
        }
        return vertex;
    }

}