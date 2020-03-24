/**
 * Created by hug.
 */
public class ExperimentHelper {

    /** Returns the internal path length for an optimum binary search tree of
     *  size N. Examples:
     *  N = 1, OIPL: 0
     *  N = 2, OIPL: 1
     *  N = 3, OIPL: 2
     *  N = 4, OIPL: 4
     *  N = 5, OIPL: 6
     *  N = 6, OIPL: 8
     *  N = 7, OIPL: 10
     *  N = 8, OIPL: 13
     */
    public static int optimalIPL(int N) {
        if (N == 1) {
            return 0;
        }

        return optimalIPL(N - 1) + (int) Math.floor((Math.log(N) / Math.log(2)));
    }

    /** Returns the average depth for nodes in an optimal BST of
     *  size N.
     *  Examples:
     *  N = 1, OAD: 0
     *  N = 5, OAD: 1.2
     *  N = 8, OAD: 1.625
     * @return
     */
    public static double optimalAverageDepth(int N) {
        return (double) optimalIPL(N) / N;

    }


    public static void randomDeleteItem (BST<Integer> bst) {
        bst.deleteTakingSuccessor(bst.getRandomKey());
    }

    public static void randomDeleteItemPS (BST<Integer> bst) {
        bst.deleteTakingRandom(bst.getRandomKey());
    }

    public static void randomInsertItem (BST<Integer> bst, int N) {
        int insertItem = RandomGenerator.getRandomInt(2 * N);
        while (bst.contains(insertItem)) {
            insertItem = RandomGenerator.getRandomInt(2 * N);
        }
        bst.add(insertItem);
    }



    public static void main(String[] arg) {
        System.out.println(optimalAverageDepth(1));
    }
}
