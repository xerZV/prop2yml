package com.outliers.prop2yml;

import java.io.File;
import java.io.IOException;

/**
 * Implementation for application.yml to application.properties converter
 *
 * @author Nikolay Simitchiyski
 */
public class Ymp2PropConverterImpl implements Yml2PropConverter {

    private static Ymp2PropConverterImpl INSTANCE = null;

    private Ymp2PropConverterImpl() {
    }

    static Ymp2PropConverterImpl getInstance() {
        if (INSTANCE == null)
            INSTANCE = new Ymp2PropConverterImpl();

        return INSTANCE;
    }

    @Override
    public void convert(File file) throws IOException {
        //TODO IMPL HERE
    }
}
