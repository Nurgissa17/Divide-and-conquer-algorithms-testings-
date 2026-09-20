import java.util.Arrays;
import java.util.Random;

public class TestAlgorithms {
    static Random random = new Random();
    public static void main(String[] args) {
        testSorts();
        testSelect();
        testClosest();
        System.out.println("all tests passed");
    }
    public static void testSorts() {
        int[] randomArray = new int[100];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = random.nextInt(1000);
        }
        int[][] arrays = {
                randomArray,
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {3, 3, 1, 3, 2},
                {},
                {5}
        };
        for (int[] a : arrays) {
            int[] expected = a.clone();
            Arrays.sort(expected);
            int[] mergeA = a.clone();
            MergeSorter.sort(mergeA);
            if (!Arrays.equals(mergeA, expected)) {
                throw new RuntimeException("mergesort failed");
            }
            int[] quickA = a.clone();
            QuickSorter.sort(quickA);
            if (!Arrays.equals(quickA, expected)) {
                throw new RuntimeException("quicksort failed");
            }
        }
        System.out.println("sorting tests passed");
    }
    public static void testSelect() {
        for (int test = 0; test < 100; test++) {
            int[] a = new int[100];
            for (int i = 0; i < a.length; i++) {
                a[i] = random.nextInt(1000);
            }
            int k = random.nextInt(a.length);
            int[] expected = a.clone();
            Arrays.sort(expected);
            int[] selectA = a.clone();
            int result = DeterministicSelector.select(selectA, k);
            if (result != expected[k]) {
                throw new RuntimeException("deterministic select failed");
            }
        }
        System.out.println("select tests passed");
    }
    public static void testClosest() {
        Point[] points = new Point[1000];
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point(
                    random.nextDouble() * 10000,
                    random.nextDouble() * 10000
            );
        }
        double fast = ClosestPairSolver.solve(points);
        double brute = ClosestPairSolver.bruteForce(points);
        if (Math.abs(fast - brute) > 0.000001) {
            throw new RuntimeException("closest pair failed");
        }
        System.out.println("closest pair test passed");
    }
}
