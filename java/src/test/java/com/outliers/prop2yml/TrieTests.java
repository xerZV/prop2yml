package com.outliers.prop2yml;

import com.outliers.prop2yml.trie.Node;
import com.outliers.prop2yml.trie.Trie;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Tests for Trie
 *
 * @author Nikolay Simitchiyski
 */
public class TrieTests {
    private static final String EXPECTED_YML_RESULT = "app:\n" +
            " message: This is the primary app prop for ${spring.application.name}\n" +
            "spring:\n" +
            " application:\n" +
            "  name: Prop2Yml\n" +
            " data:\n" +
            "  rest:\n" +
            "   basePath: /api\n" +
            " profiles:\n" +
            "  active: dev\n" +
            " devtools:\n" +
            "  livereload:\n" +
            "   enabled: true\n" +
            "  restart:\n" +
            "   exclude: static/**,public/**\n" +
            "  remote:\n" +
            "   restart:\n" +
            "    enabled: true\n" +
            " thymeleaf:\n" +
            "  cache: false\n" +
            "  prefix: classpath:/templates/\n" +
            "  suffix: .html\n" +
            "  enabled: true\n" +
            "server:\n" +
            " port: 7777\n" +
            " servlet:\n" +
            "  session:\n" +
            "   timeout: 90m\n" +
            "version: 0.0.1\n";

    private static String PATH;

    @BeforeClass
    public static void beforeClass() {
        PATH = new StringBuilder().append("src").append(File.separatorChar)
                .append("test").append(File.separatorChar)
                .append("resources").append(File.separatorChar)
                .append("application.properties").toString();
    }

    @Test
    public void testTrie() {
        Trie trie = Trie.stringTrie();
        trie.insert("com.prop2yml.test", "Ivan");
        trie.insert("peter", "Peter");

        assertEquals(2, trie.size());
        assertTrue(trie.search("com.prop2yml.test"));
        assertEquals("Ivan", trie.get("com.prop2yml.test"));
        assertEquals("Peter", trie.get("peter"));
    }

    @Test
    public void testPropToTrie() throws IOException {
        File file = new File(PATH);
        Map<String, String> props = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)
                .filter(s -> !s.isEmpty() && !s.startsWith("#") && s.contains("="))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));

        Trie trie = Trie.stringTrie();
        props.forEach(trie::insert);

        assertEquals(14, trie.size());
        assertEquals("7777", trie.get("server.port"));
    }

    @Test
    public void testPropToTrieWithOfMethod() throws IOException {
        File file = new File(PATH);
        Map<String, String> props = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)
                .filter(s -> !s.isEmpty() && !s.startsWith("#") && s.contains("="))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));

        Trie trie = Trie.stringTrie().of(props);

        assertEquals(14, trie.size());
        assertEquals("7777", trie.get("server.port"));
    }

    @Test
    public void testToYml() throws IOException {
        File file = new File(PATH);
        Map<String, String> props = Files.lines(Paths.get(file.getAbsolutePath()), StandardCharsets.UTF_8)
                .filter(s -> !s.isEmpty() && !s.startsWith("#") && s.contains("="))
                .map(s -> s.split("=", 2))
                .collect(Collectors.toMap(entry -> entry[0], entry -> entry[1]));

        Trie trie = Trie.stringTrie().of(props);

        assertEquals(EXPECTED_YML_RESULT, tmp(trie.getRoot(), 0));
    }

    private String tmp(Node node, int level) {
        StringBuilder sb = new StringBuilder();
        if (level > 0) {
            sb.append(generateWhitespaces(level - 1)).append(node.getKey()).append(':');
            if (node.isLeaf()) {
                sb.append(' ').append(node.getValue()).append("\n");
                return sb.toString();
            } else {
                sb.append("\n");
            }
        }

        node.getChildren().values().forEach(nd -> sb.append(tmp(nd, level + 1)));

        return sb.toString();
    }

    private String generateWhitespaces(int times) {
        return String.join("", Collections.nCopies(times, " "));
    }

    @Test
    public void readFile() {
        File file = new File(PATH);
        String absolutePath = file.getAbsolutePath();

        System.out.println(absolutePath);
        assertTrue(absolutePath.contains(PATH));
    }
}
