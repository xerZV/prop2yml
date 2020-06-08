package com.outliers.prop2yml;

import junit.framework.TestCase;
import org.junit.Test;
import picocli.CommandLine;

import java.io.File;


public class Yml2PropApplicationTest extends TestCase {

    @Test
    public void test() {
        String[] args = {"-d", "--file", "source.properties"};
        Yml2PropApplication tar = new Yml2PropApplication();
        new CommandLine(tar).parseArgs(args);

        assertTrue(tar.isDeleteSourceFile());
        assertEquals(new File("source.properties"), tar.getSourceFile());
    }

    @Test
    public void testMain() {
        String[] args = {"-d", "--file", "source.properties"};
        Yml2PropApplication.main(args);
        ;
    }
}
