public class Driver {
    Driver() {
        // Create temp array for test
        Double doubleArrayWith100[] = new Double[100];
        Double doubleArrayWith1000[] = new Double[1000];
        Double doubleArrayWith10000[] = new Double[10000];

        // Create random double for test 
        for (int i = 0; i < 100; i++)
            doubleArrayWith100[i] = (Double) (32000 * Math.random());
        for (int i = 0; i < 1000; i++)
            doubleArrayWith1000[i] = (Double) (32000 * Math.random());
        for (int i = 0; i < 10000; i++)
            doubleArrayWith10000[i] = (Double) (32000 * Math.random());

        // Duration time for quickSort
        System.out.println();
        calculateQuickSortDuration(doubleArrayWith100);
        calculateQuickSortDuration(doubleArrayWith1000);
        calculateQuickSortDuration(doubleArrayWith10000);

        // Duration time for mergeSort
        System.out.println();
        calculateMergeSortDuration(doubleArrayWith100);
        calculateMergeSortDuration(doubleArrayWith1000);
        calculateMergeSortDuration(doubleArrayWith10000);

        // Duration time for newSort
        System.out.println();
        calculateNewSortDuration(doubleArrayWith100);
        calculateNewSortDuration(doubleArrayWith1000);
        calculateNewSortDuration(doubleArrayWith10000);

        // Duration time for Chaning With BST 
        System.out.println();
        calculateChaningWithBSTDuration(doubleArrayWith100);
        calculateChaningWithBSTDuration(doubleArrayWith1000);
        calculateChaningWithBSTDuration(doubleArrayWith10000);

        // Duration time for Hybrid Hash  
        System.out.println();
        calculateHybridHashDuration(doubleArrayWith100);
        calculateHybridHashDuration(doubleArrayWith1000);
        calculateHybridHashDuration(doubleArrayWith10000);
        
        // Put, Get, Remove manually test 
        HashtableChain<Integer, String> hashTable = new HashtableChain<Integer, String>();
        hashTable.put(1, "this");
        hashTable.put(4, "this1");
        hashTable.put(7, "this2");
        hashTable.put(14, "sefa");

        System.out.println(hashTable.get(1)); 
        System.out.println(hashTable.get(4));
        System.out.println(hashTable.get(7));
        System.out.println(hashTable.get(14));

        hashTable.remove(7);

    }

    public void calculateQuickSortDuration(Double array[]) {
        long startTime = System.nanoTime();
        QuickSort<Double> quickSort = new QuickSort<Double>(array);
        long endTime = System.nanoTime();

        long duration = ((endTime - startTime) / 100000);
        System.out.println(array.length + " Quick Sort Duration = " + duration);
    }

    public void calculateMergeSortDuration(Double array[]) {
        long startTime = System.nanoTime();
        MergeSort<Double> mergeSort = new MergeSort<Double>(array);
        long endTime = System.nanoTime();

        long duration = ((endTime - startTime) / 100000);
        System.out.println(array.length + " Merge Sort Duration = " + duration);
    }

    public void calculateNewSortDuration(Double array[]) {
        long startTime = System.nanoTime();
        NewSort<Double> newSort = new NewSort<Double>(array);
        long endTime = System.nanoTime();

        long duration = ((endTime - startTime) / 100000);
        System.out.println(array.length + " New Sort Duration = " + duration);
    }

    public void calculateChaningWithBSTDuration(Double array[]) {
        HashtableChain<Double, String> hashTable = new HashtableChain<Double, String>();
        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            hashTable.put(array[i], "Sefa " + array[i]);
        }

        long endTime = System.nanoTime();

        long duration = ((endTime - startTime) / 100000);
        System.out.println(array.length + " Chaning With BST Duration = " + duration);
    }

    public void calculateHybridHashDuration(Double array[]) {
        HybridHashTable<Double, String> hashTable = new HybridHashTable<Double, String>();
        long startTime = System.nanoTime();
        for (int i = 0; i < array.length; i++) {
            hashTable.put(array[i], "Sefa " + array[i]);
        }

        long endTime = System.nanoTime();

        long duration = ((endTime - startTime) / 100000);
        System.out.println(array.length + " Hybrid Hast Duration = " + duration);
    }
}
