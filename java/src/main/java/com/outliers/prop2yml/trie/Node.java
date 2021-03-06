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

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    void setValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
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

    public boolean isLeaf() {
        return isLeaf;
    }

    public Map<String, Node> getChildren() {
        return children;
    }

    public void setChildren(Map<String, Node> children) {
        this.children = children;
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
