public class DeterministicSelector{
    private static int maxD;
    private static long recalls;
    public static int select(int[] a, int k){
        maxD = 0;
        recalls = 0;
        return select(a, 0, a.length - 1, k, 1);
    }
    private static int select(int[] a, int left, int right, int k, int depth){
        recalls++;
        if(depth > maxD){
            maxD = depth;
        }
        if(left == right){
            return a[left];
        }
        int pivot = median(a, left, right, depth);
        int[] bounds = partition(a, left, right, pivot);
        if(k < bounds[0]){
            return select(a, left, bounds[0] - 1, k, depth + 1);
        }
        if(k > bounds[1]){
            return select(a, bounds[1] + 1, right, k, depth + 1);
        }
        return a[k];
    }
    private static int median(int[] a, int left, int right, int depth){
        int size = right - left + 1;
        if(size <= 5){
            insertion(a, left, right);
            return a[left + size / 2];
        }
        int count = 0;
        for(int start = left; start <= right; start += 5){
            int end = Math.min(start + 4, right);
            insertion(a, start, end);
            int medianI = start + (end - start) / 2;
            swap(a, left + count, medianI);
            count++;
        }
        int middle = left + count / 2;
        return select(a, left, left + count - 1, middle, depth + 1);
    }
    private static int[] partition(int[] a, int left, int right, int pivot){
        int small = left;
        int i = left;
        int large = right;
        while(i <= large){
            if(a[i] < pivot){
                swap(a, small, i);
                small++;
                i++;
            }
            else if(a[i] > pivot){
                swap(a, i, large);
                large--;
            }
            else{
                i++;
            }
        }
        return new int[]{small, large};
    }
    private static void insertion(int[] a, int left, int right){
        for(int i = left + 1; i <= right; i++){
            int key = a[i];
            int j = i - 1;
            while(j >= left && a[j] > key){
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = key;
        }
    }
    private static void swap(int[] a, int b, int c){
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