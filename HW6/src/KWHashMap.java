// KWHashMap interface for using Hybrid and HashChain
interface KWHashMap<K, V> {
    V get(Object key);
    boolean isEmpty();
    V put(K key, V value);
    V remove(Object Key);
    int size();
}
