import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;

public class ClosestPairSolver{
    private static int maxD;
    private static long recalls;
    public static double solve(Point[] points){
        maxD = 0;
        recalls = 0;
        if(points == null || points.length < 2){
            return Double.POSITIVE_INFINITY;
        }
        Point[] xSorted = points.clone();
        Point[] ySorted = points.clone();
        Arrays.sort(xSorted, Comparator.comparingDouble((Point p) -> p.x).thenComparingDouble(p -> p.y));
        Arrays.sort(ySorted, Comparator.comparingDouble((Point p) -> p.y).thenComparingDouble(p -> p.x));
        return closest(xSorted, ySorted, 1);
    }
    private static double closest(Point[] xSorted, Point[] ySorted, int depth){
        recalls++;
        if(depth > maxD){
            maxD = depth;
        }
        int n = xSorted.length;
        if(n <= 3){
            return bruteForce(xSorted);
        }
        int mid = n / 2;
        Point midPoint = xSorted[mid];
        Point[] leftX = Arrays.copyOfRange(xSorted, 0, mid);
        Point[] rightX = Arrays.copyOfRange(xSorted, mid, n);
        HashSet<Point> leftSet = new HashSet<>(Arrays.asList(leftX));
        Point[] leftY = new Point[leftX.length];
        Point[] rightY = new Point[rightX.length];
        int leftI = 0;
        int rightI = 0;
        for(Point p : ySorted){
            if(leftSet.contains(p)){
                leftY[leftI++] = p;
            }
            else{
                rightY[rightI++] = p;
            }
        }
        double leftD = closest(leftX, leftY, depth + 1);
        double rightD = closest(rightX, rightY, depth + 1);
        double d = Math.min(leftD, rightD);
        Point[] strip =new Point[n];
        int stripSize = 0;
        for(Point p : ySorted){
            if(Math.abs(p.x - midPoint.x) < d){
                strip[stripSize++] = p;
            }
        }
        for(int i = 0; i < stripSize; i++){
            for(int j = i + 1; j < stripSize && strip[j].y - strip[i].y < d; j++){
                double distance = distance(strip[i], strip[j]);
                if(distance < d){
                    d = distance;
                }
            }
        }
        return d;
    }
    public static double bruteForce(Point[] points){
        if(points == null || points.length < 2){
            return Double.POSITIVE_INFINITY;
        }
        double best = Double.POSITIVE_INFINITY;
        for(int i = 0; i < points.length; i++){
            for(int j = i + 1; j < points.length; j++){
                double distance = distance(points[i], points[j]);
                if(distance < best){
                    best = distance;
                }
            }
        }
        return best;
    }
    private static double distance(Point a, Point b){
        double dx = a.x - b.x;
        double dy = a.y - b.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
    public static int getMaxD(){
        return maxD;
    }
    public static long getRecalls(){
        return recalls;
    }
}