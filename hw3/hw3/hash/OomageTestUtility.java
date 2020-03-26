package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        int numOfO = oomages.size();
        int[] bucketNumArray = new int[M];

        for (Oomage oomage : oomages) {
            int bucketNum = (oomage.hashCode() & 0x7FFFFFFF) % M;
            bucketNumArray[bucketNum] += 1;
        }

        for (int buckNum : bucketNumArray) {
            if (buckNum < (numOfO / 50) || buckNum > (numOfO / 2.4)) {
                return false;
            }
        }
        return true;
    }
}
