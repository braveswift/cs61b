import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    Pair<List<Bear>, List<Bed>> pair;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        pair = sort(bears, beds);
    }

    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return pair.first();
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return pair.second();
    }


    private Bed getRandom(List<Bed> beds) {
        int index = (int) (Math.random() * beds.size());
        return beds.get(index);
    }


    private Pair<List<Bear>, List<Bed>> sort(List<Bear> bears, List<Bed> beds) {
        if (bears.size() == 1 || beds.isEmpty()) {
            return new Pair<>(bears, beds);
        }

        Bed pivotBed = getRandom(beds);
        List<Bear> leftBear = new ArrayList<>();
        List<Bear> equalBear = new ArrayList<>();
        List<Bear> rightBear = new ArrayList<>();
        partition(bears, pivotBed, leftBear, equalBear, rightBear);

        List<Bed> leftBed = new ArrayList<>();
        List<Bed> equalBed = new ArrayList<>();
        List<Bed> rightBed = new ArrayList<>();
        partition(beds, equalBear.get(0), leftBed, equalBed, rightBed);

        Pair<List<Bear>, List<Bed>> sortLeftPair = sort(leftBear, leftBed);
        Pair<List<Bear>, List<Bed>> sortRightPair = sort(rightBear, rightBed);
        List<Bear> sortLeftBear =  sortLeftPair.first();
        List<Bed> sortLeftBed = sortLeftPair.second();
        List<Bear> sortRightBear = sortRightPair.first();
        List<Bed> sortRightBed = sortRightPair.second();

        return new Pair<>(joinList(sortLeftBear, equalBear, sortRightBear),
                joinList2(sortLeftBed, equalBed, sortRightBed));

    }


    private void partition(List<Bear> bears, Bed bed, List<Bear> leftBear, List<Bear> equalBear, List<Bear> rightBear) {
        for (Bear bear : bears) {
            if (bear.compareTo(bed) < 0) {
                leftBear.add(bear);
            } else if (bear.compareTo(bed) == 0) {
                equalBear.add(bear);
            } else {
                rightBear.add(bear);
            }
        }
    }

    private void partition(List<Bed> beds, Bear bear, List<Bed> leftBed, List<Bed> equalBed, List<Bed> rightBed) {
        for (Bed bed : beds) {
            if (bed.compareTo(bear) < 0) {
                leftBed.add(bed);
            } else if (bed.compareTo(bear) == 0) {
                equalBed.add(bed);
            } else {
                rightBed.add(bed);
            }
        }
    }

    private List<Bear> joinList(List<Bear> list1, List<Bear> list2, List<Bear> list3) {
        List<Bear> list = new LinkedList<>();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        return list;
    }

    private List<Bed> joinList2(List<Bed> list1, List<Bed> list2, List<Bed> list3) {
        List<Bed> list = new LinkedList<>();
        list.addAll(list1);
        list.addAll(list2);
        list.addAll(list3);
        return list;
    }

}
