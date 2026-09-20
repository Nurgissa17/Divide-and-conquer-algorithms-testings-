import java.util.Arrays;
import java.util.Random;

public class TestAlgorithms{
    static Random random = new Random();
    public static void main(String[] args){
        testSorts();
        testSelect();
        testClosest();
        System.out.println("All tests passed");
    }
    public static void testSorts(){
        int[][] arrays = {
                {5, 2, 8, 1, 4},
                {1, 2, 3, 4, 5},
                {5, 4, 3, 2, 1},
                {3, 3, 1, 3, 2},
                {},
                {5}
        };
        for(int[] a : arrays){
            int[] expected = a.clone();
            Arrays.sort(expected);
            int[] mergeA = a.clone();
            MergeSorter.sort(mergeA);
            if(!Arrays.equals(mergeA, expected)){
                throw new RuntimeException("MergeSort failed");
            }
            int[] quickA = a.clone();
            QuickSorter.sort(quickA);
            if(!Arrays.equals(quickA, expected)){
                throw new RuntimeException("QuickSort failed");
            }
        }
        System.out.println("Sorting tests passed");
    }
    public static void testSelect(){
        for(int test = 0; test < 100; test++){
            int[] a = new int[100];
            for(int i = 0; i < a.length; i++){
                a[i] = random.nextInt(1000);
            }
            int k = random.nextInt(a.length);
            int[] expected = a.clone();
            Arrays.sort(expected);
            int[] selectA = a.clone();
            int result = DeterministicSelector.select(selectA, k);
            if(result != expected[k]){
                throw new RuntimeException("Deterministic Select failed");
            }
        }
        System.out.println("Select tests passed");
    }
    public static void testClosest(){
        Point[] points = new Point[1000];
        for(int i = 0; i < points.length; i++){
            points[i] = new Point(
                    random.nextDouble() * 10000,
                    random.nextDouble() * 10000
            );
        }
        double fast = ClosestPairSolver.solve(points);
        double brute = ClosestPairSolver.bruteForce(points);
        if(Math.abs(fast - brute) > 0.000001){
            throw new RuntimeException("Closest Pair failed");
        }
        System.out.println("Closest Pair test passed");
    }
}