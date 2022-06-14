import java.util.Arrays;

public class HybridHashTable<K extends Comparable<? super K>, V> implements KWHashMap<K, V> {
    // Entry for hash table 
    protected static class Entry<K extends Comparable<? super K>, V> implements Comparable<Entry<K, V>> {
        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return this.key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return this.value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public int compareTo(HybridHashTable.Entry<K, V> o) {
            // TODO Auto-generated method stub
            return 0;
        }
    }

    private int numKeys;
    private int numDeletes;
    private int primeSize;
    private int[] nextIndexes;
    private static final int CAPACITY = 10;
    private BinarySearchTree<Entry<K, V>>[] table;
    private final Entry<K, V> DELETED = new Entry<>(null, null);

    public HybridHashTable() {
        table = new BinarySearchTree[CAPACITY];
        nextIndexes = new int[CAPACITY];
        Arrays.fill(nextIndexes, -1);
        this.primeSize = getPrime();
    }

    @Override
    public V get(Object key) {
        int hash1 = hash1((K) key);
        int hash2 = hash2((K) key);

        while (table[hash1] != null && !table[hash1].root.value.getKey().equals(key)) {
            if (table[nextIndexes[hash1]].root.value.getKey().equals(key))
                return table[nextIndexes[hash1]].root.value.getValue();

            hash1 += hash2;
            hash1 %= table.length;
        }
        return table[hash1].root.value.getValue();
    }

    // Add key to the hash table 
    @Override
    public V put(K key, V value) {
        boolean isCollision = false;
        int mainHash;

        // Double hassing 
        int hash1 = mainHash = hash1(key);
        int hash2 = hash2(key);

        int i = 0;

        // While find a empty space get mod 
        while (table[hash1] != null && !table[hash1].root.value.key.equals(key)) {
            i++;

            isCollision = true;
            hash1 += hash2;
            hash1 %= table.length;
            if(i > table.length)
            {
                hash1++;
                i = 0;
            }
        }

        // Insert the value
        table[hash1] = new BinarySearchTree<Entry<K, V>>();
        table[hash1].insert(new Entry<K, V>(key, value));

        // Store the key next key 
        if (isCollision)
            nextIndexes[mainHash] = hash1;

        numKeys++;

        if (numKeys == table.length)
            rehash();

        return null;
    }

    @Override
    public V remove(Object Key) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean isEmpty() {
        return numKeys == 0;
    }

    // Function to get prime number less than table size for myhash2 function
    public int getPrime() {
        for (int i = table.length - 1; i >= 1; i--) {
            int fact = 0;
            for (int j = 2; j <= (int) Math.sqrt(i); j++)
                if (i % j == 0)
                    fact++;
            if (fact == 0)
                return i;
        }
        return 3;
    }

    // If need more space create the array size * 10 
    private void rehash() {
        BinarySearchTree<Entry<K, V>>[] oldTable = table;
        table = new BinarySearchTree[10 * oldTable.length + 1];

        nextIndexes = new int[table.length];
        Arrays.fill(nextIndexes, -1);

        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null) && (oldTable[i].root.value != DELETED))
                put(oldTable[i].root.value.getKey(), oldTable[i].root.value.getValue());
        }
    }

    // hash1 function for double hashing 
    private int hash1(K x) {
        int hashVal = x.hashCode();
        hashVal %= table.length;
        if (hashVal < 0)
            hashVal += table.length;
        return hashVal;
    }

    // hash2 function for double hashing 
    private int hash2(K x) {
        int myhashVal2 = x.hashCode();
        myhashVal2 %= table.length;
        if (myhashVal2 < 0)
            myhashVal2 += table.length;
        return primeSize - myhashVal2 % primeSize;
    }

    @Override
    public int size() {
        return numKeys;
    }

}