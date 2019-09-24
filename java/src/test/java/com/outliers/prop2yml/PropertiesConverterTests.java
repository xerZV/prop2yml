package com.outliers.prop2yml;

import com.outliers.prop2yml.PropertiesConverter;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * Tests for PropertiesConverter
 *
 * @author Nikolay Simitchiyski
 */
public class PropertiesConverterTests {
    private static String PATH;

    @BeforeClass
    public static void beforeClass() {
        PATH = new StringBuilder().append("src").append(File.separatorChar)
                .append("test").append(File.separatorChar)
                .append("resources").append(File.separatorChar)
                .append("application.properties").toString();
    }

    @Test
    public void testYml2PropConverter() throws IOException {
        PropertiesConverter converter = PropertiesConverter.getProp2YmlConverter();
        converter.convert(new File(PATH));
    }
}
