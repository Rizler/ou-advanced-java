package maman14_1;

import java.util.Iterator;
import java.util.TreeMap;

public class AssociationTable<E extends Comparable<E>, T> {
    TreeMap<E, T> table;

    public AssociationTable() {
        table = new TreeMap<E, T>();
    }

    public AssociationTable(E[] keys, T[] values) throws IllegalArgumentException {
        if (keys.length != values.length) {
            throw new IllegalArgumentException("Keys and values arrays must be of the same length");
        }
        table = new TreeMap<E, T>();
        for (int i = 0; i < keys.length; i++) {
            table.put(keys[i], values[i]);
        }
    }

    public void add(E key, T value) {
        table.put(key, value);
    }

    public T get(E key) {
        return table.get(key);
    }

    public boolean contains(E key) {
        return table.containsKey(key);
    }

    public boolean remove(E key) {
        return (table.remove(key) != null);
    }

    public int size() {
        return table.size();
    }

    public Iterator<E> keyIterator() {
        return table.keySet().iterator();
    }
}
