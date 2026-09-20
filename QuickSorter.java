public class QuickSorter{
    private static int maxD;
    private static long recalls;
    public static void sort(int[] a){
        maxD = 0;
        recalls = 0;
        quicksort(a, 0, a.length - 1, 1);
    }
    public static void quicksort(int[] a, int left, int right, int depth){
        recalls++;
        if(depth > maxD){
            maxD = depth;
        }
        while(left < right){
            int p = partition(a, left, right);
            int leftsize = p - left;
            int rightsize = right - p;
            if(leftsize < rightsize){
                quicksort(a, left, p - 1, depth + 1);
                left = p + 1;
            }
            else{
                quicksort(a, p + 1, right, depth + 1);
                right = p - 1;
            }
        }
    }
    public static int partition(int[] a, int left, int right){
        int size = right - left + 1;
        int r = (int)(Math.random() * size);
        int pivotI = left + r;
        swap(a, pivotI, right);
        int pivot = a[right];
        int small = left - 1;
        for(int i = left; i < right; i++){
            if(a[i] <= pivot){
                small++;
                swap(a, small, i);
            }
        }
        swap(a, small + 1, right);
        return small + 1;
    }
    public static void swap(int[] a, int b, int c){
        int temp = a[b];
        a[b] = a[c];
        a[c] = temp;
    }
    public static int getMaxD(){
        return maxD;
    }
    public static long getRecalls(){
        return recalls;
    }
}