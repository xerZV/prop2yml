package com.outliers.prop2yml.trie;

import java.util.ArrayList;

public class Node {
    public ArrayList<Node> children;
    private String key;
    private String value;
    private boolean isLeaf;

    Node(String key) {
        this.key = key;
        this.isLeaf = false;
        this.children = new ArrayList<Node>();
    }

    Node findChild(String value) {
        if (children != null) {
            for (Node n : children) {
                if (n.getKey().equals(value)) {
                    return n;
                }
            }
        }
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
        Node n = new Node(value);
        children.add(n);
        return n;
    }
}
