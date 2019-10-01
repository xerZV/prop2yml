package com.outliers.prop2yml;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

/**
 * Tests for PropertiesConverter
 *
 * @author Nikolay Simitchiyski
 */
public class PropertiesConverterTests {
    private static String PATH_SOURCE;
    private static String PATH_EXPECTED;
    private static String PATH_RESULT;

    @BeforeClass
    public static void beforeClass() {
        String resourcePath = new StringBuilder().append("src").append(File.separatorChar)
                .append("test").append(File.separatorChar)
                .append("resources").append(File.separatorChar).toString();

        PATH_SOURCE = resourcePath + "application.properties";

        PATH_EXPECTED = resourcePath + "application-expected.yml";

        PATH_RESULT = resourcePath + "application.yml";

        File resultFile = new File(PATH_RESULT);

        if (resultFile.exists())
            resultFile.delete();
    }

    @Test
    public void testYml2PropConverter() throws IOException {
        PropertiesConverter converter = PropertiesConverter.getProp2YmlConverter();
        converter.convert(new File(PATH_SOURCE));

        assertEquals(Files.readAllLines(Paths.get(PATH_EXPECTED)), Files.readAllLines(Paths.get(PATH_RESULT)));
    }
}
