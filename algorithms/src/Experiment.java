import java.io.File;
import java.io.PrintWriter;
import java.util.Random;

public class Experiment{
    static Random random = new Random();
    public static void run() throws Exception{
        File folder = new File("results");
        folder.mkdirs();
        PrintWriter writer = new PrintWriter("results/results.csv");
        writer.println("algorithm,inputType,n,timeNs,maxDepth,recalls");
        int[] sizes = {100, 1000, 10000};
        String[] types = {"Random", "Sorted", "Reverse", "Duplicates"};
        for(int n : sizes){
            for(String type : types){
                int[] a = createArray(n, type);
                int[] mergeA = a.clone();
                long start = System.nanoTime();
                MergeSorter.sort(mergeA);
                long time = System.nanoTime() - start;
                writer.println("mergesort," + type + "," + n + "," + time + "," + MergeSorter.getMaxD() + "," + MergeSorter.getRecalls());

                int[] quickA = a.clone();
                start = System.nanoTime();
                QuickSorter.sort(quickA);
                time =System.nanoTime() - start;
                writer.println("quicksort," + type + "," + n + "," + time + "," + QuickSorter.getMaxD() + "," + QuickSorter.getRecalls());

                int[] selectA = a.clone();
                int k = n / 2;
                start = System.nanoTime();
                DeterministicSelector.select(selectA, k);
                time =System.nanoTime() - start;
                writer.println("deterministicselect," + type + "," + n + "," + time + "," + DeterministicSelector.getMaxD() + "," + DeterministicSelector.getRecalls());
            }
            Point[] points = createPoints(n);
            long start = System.nanoTime();
            ClosestPairSolver.solve(points);
            long time = System.nanoTime() - start;
            writer.println("closestpair,randomPoints," + n + "," + time + "," + ClosestPairSolver.getMaxD() + "," + ClosestPairSolver.getRecalls());
        }
        writer.close();
    }
    public static int[] createArray(int n, String type){
        int[] a = new int[n];
        if(type.equals("Random")){
            for(int i = 0; i < n; i++){
                a[i] = random.nextInt(10000);
            }
        }
        if(type.equals("Sorted")){
            for(int i =0; i < n; i++){
                a[i] = i;
            }
        }
        if(type.equals("Reverse")){
            for(int i = 0; i < n; i++){
                a[i] = n - i;
            }
        }
        if(type.equals("Duplicates")){
            for(int i =0; i < n; i++){
                a[i] = random.nextInt(10);
            }
        }
        return a;
    }
    public static Point[] createPoints(int n){
        Point[] points = new Point[n];
        for(int i = 0; i < n; i++){
            points[i] = new Point(
                    random.nextDouble() * 10000,
                    random.nextDouble() * 10000
            );
        }
        return points;
    }
}