package com.outliers.prop2yml.trie;

import java.util.Map;

/**
 * Prefix Trie interface
 *
 * @author Nikolay Simitchiyski
 */
public interface Trie {
    static StringTrie stringTrie() {
        return new StringTrie("");
    }

    void insert(String key, String value) throws IllegalArgumentException;

    boolean search(String key) throws IllegalArgumentException;

    String get(String key) throws IllegalArgumentException;

    int size();

    StringTrie of(Map<String, String> map);

    Node getRoot();
}
