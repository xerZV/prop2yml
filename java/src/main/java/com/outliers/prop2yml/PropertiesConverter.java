package com.outliers.prop2yml;

import java.io.File;
import java.io.IOException;

/**
 * Base interface for Properties converter
 *
 * @author Nikolay Simitchiyski
 */
public interface PropertiesConverter {
    static Yml2PropConverter getYml2PropConverter() {
        return Ymp2PropConverterImpl.getInstance();
    }

    static Prop2YmlConverter getProp2YmlConverter() {
        return Prop2YmlConverterImpl.getInstance();
    }

    void convert(File file) throws IOException;
}
