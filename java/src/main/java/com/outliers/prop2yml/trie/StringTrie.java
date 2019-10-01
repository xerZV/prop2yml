package com.outliers.prop2yml.trie;

import java.util.Map;

/**
 * String prefix Trie implementation
 *
 * @author Nikolay Simitchiyski
 */
public class StringTrie implements Trie {
    private static final String SPLIT_BY = "\\.";
    private Node root;
    private int size;

    StringTrie(String rootNodeValue) {
        root = new Node(rootNodeValue);
        size = 0;
    }

    @Override
    public void insert(String key, String value) {
        this.assertKeyNotNullAndNotEmpty(key);

        this.insert(key.split(SPLIT_BY), value);
    }

    private void insert(String[] keys, String value) {
        Node current = root;
        if (keys != null) {
            if (keys.length == 0) { // "empty value"
                current.setLeaf(true);
            } else {
                for (int i = 0; i < keys.length; i++) {
                    Node child = current.findChild(keys[i]);
                    if (child != null) {
                        current = child;
                    } else {
                        current = current.addChild(keys[i]);
                    }
                    if (i == keys.length - 1) {
                        if (!current.isLeaf()) {
                            current.setLeaf(true);
                            current.setValue(value);
                            size++;
                        }
                    }
                }
            }
        }
    }

    @Override
    public boolean search(String key) {
        return this.search(key.split(SPLIT_BY));
    }

    private boolean search(String[] values) {
        Node current = root;
        for (int i = 0; i < values.length; i++) {
            if (current.findChild(values[i]) == null) {
                return false;
            } else {
                current = current.findChild(values[i]);
            }
        }

        if (current.isLeaf()) {
            return true;
        } else {
            return false;
        }
    }

    private Node find(String[] values) {
        Node current = root;
        for (int i = 0; i < values.length; i++) {
            if (current.findChild(values[i]) == null) {
                return null;
            } else {
                current = current.findChild(values[i]);
            }
        }

        if (!current.isLeaf()) {
            return null;
        }

        return current;
    }

    @Override
    public String get(String key) {
        return get(key.split(SPLIT_BY));
    }

    private String get(String[] values) {
        Node current = root;
        for (int i = 0; i < values.length; i++) {
            if (current.findChild(values[i]) == null) {
                return null;
            } else {
                current = current.findChild(values[i]);
            }
        }

        if (!current.isLeaf()) {
            return null;
        }

        return current.getValue();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public StringTrie of(Map<String, String> map) {
        if (map == null)
            throw new IllegalArgumentException();

        map.forEach(this::insert);

        return this;
    }

    @Override
    public Node getRoot() {
        return this.root;
    }

    private void assertKeyNotNullAndNotEmpty(String key) {
        if (key == null || key.isEmpty())
            throw new IllegalArgumentException("The key cannot be null or empty");
    }

    public String toString() {
        //TODO impl toString correctly

        StringBuilder sb = new StringBuilder();
        sb.append("number entries: ");
        sb.append(size);

        return sb.toString();
    }
}
