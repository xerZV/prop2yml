package com.outliers.prop2yml.trie;

import java.util.HashMap;
import java.util.Map;

public class Node {
    private Map<String, Node> children;
    private String key;
    private String value;
    private boolean isLeaf;

    Node(String key) {
        this.key = key;
        this.isLeaf = false;
        this.children = new HashMap<>();
    }

    Node findChild(String key) {
        if (children != null)
            return children.get(key);

        return null;
    }

    String getKey() {
        return key;
    }

    String getValue() {
        return value;
    }

    void setValue(String value) {
        this.value = value;
    }

    boolean isLeaf() {
        return isLeaf;
    }

    void setLeaf(boolean leaf) {
        this.isLeaf = leaf;
    }

    Node addChild(String value) {
        if (value == null || value.isEmpty())
            throw new IllegalArgumentException();

        Node newNode = new Node(value);
        children.put(value, newNode);

        return newNode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return getKey().equals(node.getKey());
    }

    @Override
    public int hashCode() {
        return getKey().hashCode();
    }
}
