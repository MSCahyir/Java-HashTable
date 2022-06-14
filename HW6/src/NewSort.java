public class NewSort<T extends Comparable<T>> {
    NewSort(T arr[]) {
        new_sort(arr, 0, arr.length - 1);
    }

    public static <T> int[] min_max_finder(T[] array, int head, int tail, int minIndex, int maxIndex) {
        int[] indexes = { minIndex, maxIndex };

        if (head >= tail)
            return indexes;
        // Find min and max values for exhange 
        else {
            if (head + 1 != tail) {
                if (((Comparable<T>) array[minIndex]).compareTo(array[head + 1]) > 0)
                    minIndex = head + 1;
                if (((Comparable<T>) array[maxIndex]).compareTo(array[head + 1]) < 0)
                    maxIndex = head + 1;
                return min_max_finder(array, head + 1, tail, minIndex, maxIndex);
            }
            return indexes;
        }
    }

    // Recursive helper 
    public static <T> int[] min_max_finder(T[] array, int head, int tail) {
        return min_max_finder(array, head, tail, head, head);
    }

    // After the find min and max swap it 
    public static <T> void swap(T[] arr, int oldData, int newData) {
        var temp = arr[oldData];
        arr[oldData] = arr[newData];
        arr[newData] = temp;
    }

    // Find min-max values and exchange it until middle size
    public static <T> T[] new_sort(T[] array, int head, int tail) {
        if (head >= tail)
            return array;
        else {
            int[] minMax;
            minMax = min_max_finder(array, head, tail);
            swap(array, head, minMax[0]);
            swap(array, tail, minMax[1]);
            return new_sort(array, head + 1, tail - 1);
        }
    }
}
