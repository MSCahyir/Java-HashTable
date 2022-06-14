public class QuickSort<T extends Comparable <T>> {
    QuickSort(T arr[]) {
        quickSort(arr, 0, arr.length - 1);
    }

    // Find middle and sort it 
    public static <T> void quickSort(T A[], int p, int r) {
        int q;
        if (p < r) {
            q = partition(A, p, r);
            quickSort(A, p, q - 1);
            quickSort(A, q + 1, r);
        }
    }

    // Find middle and sort after compare 
    public static <T> int partition(T A[], int p, int r) {
        T tmp;
        T x = A[r];
        int i = p - 1;

        for (int j = p; j <= r - 1; j++) {
            if (((Comparable<T>) A[j]).compareTo(x) < 0 ) {
                i++;
                tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        tmp = A[i + 1];
        A[i + 1] = A[r];
        A[r] = tmp;
        return i + 1;
    }
}
