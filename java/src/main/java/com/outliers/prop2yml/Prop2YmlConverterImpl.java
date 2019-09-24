package com.outliers.prop2yml;

import com.outliers.prop2yml.trie.Trie;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation for properties to yml file converter
 *
 * @author Nikolay Simitchiyski
 */
public class Prop2YmlConverterImpl implements Prop2YmlConverter {
    private static Prop2YmlConverterImpl INSTANCE = null;

    private Prop2YmlConverterImpl() {
    }

    static Prop2YmlConverterImpl getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Prop2YmlConverterImpl();

        return INSTANCE;
    }

    @Override
    public void convert(File file) throws IOException {
        Trie trie = Trie.stringTrie().of(buildPropertiesMap(file));
        //TODO implement trie to .yml file
    }

    /**
     * Builds a map with key value from input properties file.
     *
     * @param file application.properties file
     * @return map with key-value pairs from every line from the file
     * @throws IOException
     */
    private Map<String, String> buildPropertiesMap(File file) throws IOException {
        return Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)
                .filter(s -> !s.isEmpty() && !s.startsWith("#") && s.contains("="))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));
    }
}
