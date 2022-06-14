
// Create a hashtable chaning with BST (Used Comparable and Iterable)
public class HashtableChain<K extends Comparable<? super K>, V> implements KWHashMap<K, V>  {
    protected static class Entry<K extends Comparable<? super K>, V> implements Comparable <Entry<K, V>>{
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
        public int compareTo(HashtableChain.Entry<K, V> o) {
            return 0;
        }
    }
    
    // Definations 
    private int numKeys;
    private int numDeletes;
    private static final int CAPACITY = 101;
    private static final double LOAD_THRESHOLD = 3.0;
    private BinarySearchTree<Entry<K, V>>[] table;
    private final Entry<K, V> DELETED = new Entry<>(null, null);

    // No paramater constructure 
    public HashtableChain() {
        table = new BinarySearchTree[CAPACITY];
    }

    // Get a value with key 
    @Override
    public V get(Object key) {
        int index = key.hashCode() % table.length;

        if (index < 0)
        index += table.length;

        if (index < 0)
            index += table.length;

        if (table[index] == null)
            return null;
        
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.getKey().equals(key))
                return nextItem.getValue();
        }

        return null;
    }

    // Put a value after hashing to table 
    public V put(K key, V value) {
        int index = key.hashCode() % table.length;
        
        if (index < 0)
        index += table.length;

        if (table[index] == null) {
            table[index] = new BinarySearchTree<Entry<K, V>>();
            table[index].insert(new Entry<K, V>(key, value));
        }
        else
            table[index].insert(new Entry<K, V>(key, value));

        numKeys++;

        // If data increase and need more space in array
        double loadFactor = (double) (numKeys + numDeletes) / table.length;
        if (loadFactor > LOAD_THRESHOLD)
            rehash();

        return null;
    }   

    // Remove a key from hash table 
    public V remove(Object key) {
        int index = key.hashCode() % table.length;

        if (index < 0)
            index += table.length;

        if (table[index] == null)
            return null;

        // After find make it DELETED
        for (Entry<K, V> nextItem : table[index]) {
            if (nextItem.getKey() == key) {
                table[index].insert(DELETED);
                nextItem = null;
                numKeys--;
                numDeletes++;
                return null;
            }
        }

        table[index] = null;

        return table[index].root.value.getValue();
    }

    // When need more space in array 
    private void rehash() {
        BinarySearchTree<Entry<K,V>>[] oldTable = table;
        table = new BinarySearchTree[10 * oldTable.length + 1];

        numKeys = 0;
        numDeletes = 0;
        for (int i = 0; i < oldTable.length; i++) {
            if ((oldTable[i] != null) && (oldTable[i].root.value != DELETED))
                put(oldTable[i].root.value.getKey(), oldTable[i].root.value.getValue());
        }
    }

    //Getter and setters
    public BinarySearchTree<Entry<K,V>>[] getTable() {
        return this.table;
    }

    public void setTable(BinarySearchTree<Entry<K,V>>[] table) {
        this.table = table;
    }

    public int getNumKeys() {
        return this.numKeys;
    }

    public void setNumKeys(int numKeys) {
        this.numKeys = numKeys;
    }

    public int getNumDeletes() {
        return this.numDeletes;
    }

    public void setNumDeletes(int numDeletes) {
        this.numDeletes = numDeletes;
    }

    public Entry<K,V> getDELETED() {
        return this.DELETED;
    }

    @Override
    public int size() {
        return this.getNumKeys();
    }

    @Override
    public boolean isEmpty() {
        if(getNumKeys() == 0)
            return false;
        return true ;
    }

    
}
