package com.zhr.student.entity;

public class MyHashMap<K, V> {

    private final int default_length = 16;
    MyEntry[] entries = new MyEntry[default_length];

    public MyHashMap() {
        super();
    }

    public void put(K key, V value) {
        int index = key.hashCode() % default_length;
//        entries[index]
    }

    public V get(K key) {

        return null;
    }


    public final class MyEntry<K, V> {
        private K key;
        private V value;
        private MyEntry<K, V> next;

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public MyEntry<K, V> getNext() {
            return next;
        }

        public void setNext(MyEntry<K, V> next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        MyHashMap<String, String> map = new MyHashMap<>();
    }
}
