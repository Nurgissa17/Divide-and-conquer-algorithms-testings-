public class MergeSorter {
    private static final int cutoff = 4;
    private static int maxD;
    private static long recalls;
    public static void sort(int[] a) {
        maxD = 0;
        recalls = 0;
        int[] temp = new int[a.length];
        mergesort(a, temp, 0, a.length - 1, 1);
    }
    public static void mergesort(int[] a, int[] temp, int left, int right, int depth) {
        recalls++;
        if (depth > maxD) {
            maxD = depth;
        }
        if (right - left + 1 <= cutoff) {
            InsertionSort.insertionsort(a, left, right);
            return;
        }
        int mid = (left + right) / 2;
        mergesort(a, temp, left, mid, depth + 1);
        mergesort(a, temp, mid + 1, right, depth + 1);
        merge(a, temp, left, mid, right);
    }
    public static void merge(int[] a, int[] temp, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (a[i] <= a[j]) {
                temp[k] = a[i];
                i++;
            } else {
                temp[k] = a[j];
                j++;
            }
            k++;
        }
        while (i <= mid) {
            temp[k] = a[i];
            i++;
            k++;
        }
        while (j <= right) {
            temp[k] = a[j];
            j++;
            k++;
        }
        for (int d = left; d <= right; d++) {
            a[d] = temp[d];
        }
    }
    public static int getMaxD() {
        return maxD;
    }
    public static long getRecalls() {
        return recalls;
    }
}