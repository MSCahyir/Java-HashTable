public class MergeSort<T extends Comparable<T>> {
    MergeSort(T arr[]) {
        sortAfterMerge(arr, 0, arr.length - 1);
    }

    public static <T> void merge(T arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays 
        T L[] = (T[]) new Object[n1];
        T R[] = (T[]) new Object[n2];

        /* Copy data to temp arrays */
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (((Comparable<T>) L[i]).compareTo(R[j]) < 0) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of left array
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of right array
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    public static <T> void sortAfterMerge(T arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second parts
            sortAfterMerge(arr, l, m);
            sortAfterMerge(arr, m + 1, r);

            // Merge the sorted parts
            merge(arr, l, m, r);
        }
    }
}
