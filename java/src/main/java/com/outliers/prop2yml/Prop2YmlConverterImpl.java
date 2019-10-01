package com.outliers.prop2yml;

import com.outliers.prop2yml.trie.Node;
import com.outliers.prop2yml.trie.Trie;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Implementation for properties to yml file converter
 *
 * @author Nikolay Simitchiyski
 */
public class Prop2YmlConverterImpl implements Prop2YmlConverter {
    private static final String NEW_LINE = "\n";
    private static final char PUNCTUATION_COLON = ':';
    private static Prop2YmlConverterImpl INSTANCE = null;
    private static String EMPTY_STRING = "";
    private static String WHITESPACE = " ";

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
        String ymlSource = convertTrieToYmlStringSource(trie);

        //TODO Decide what the result should be
        // This is for test purpose only
        Files.writeString(new File(file.getParent() + File.separator + "application.yml").toPath(), ymlSource);
    }

    private String convertTrieToYmlStringSource(Trie trie) {
        return convertTrieToYmlStringSource(trie.getRoot(), 0);
    }

    private String convertTrieToYmlStringSource(Node node, int level) {
        StringBuilder sb = new StringBuilder();

        if (level > 0) { // skip the root node, because its empty
            sb.append(generateWhitespaces(level - 1)).append(node.getKey()).append(PUNCTUATION_COLON);

            if (node.isLeaf()) { // if the node is leaf have to append whitespace before the value
                sb.append(' ').append(node.getValue()).append(NEW_LINE);
                return sb.toString();
            } else {
                sb.append(NEW_LINE);
            }
        }

        node.getChildren().values()
                .forEach(nd -> sb.append(convertTrieToYmlStringSource(nd, level + 1)));

        return sb.toString();
    }

    /**
     * Generate String with N (times) whitespaces
     *
     * @param times how many whitespaces to contain
     * @return
     */
    private String generateWhitespaces(int times) {
        return String.join(EMPTY_STRING, Collections.nCopies(times, WHITESPACE));
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
