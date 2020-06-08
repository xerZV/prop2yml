package com.outliers.prop2yml;

import picocli.CommandLine;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import static picocli.CommandLine.Command;
import static picocli.CommandLine.Option;

enum Mode {
    P2Y, Y2P
}

@Command(name = "convert", mixinStandardHelpOptions = true,
        description = "Convert properties to yml and yml to properties")
public class Yml2PropApplication implements Callable<Integer> {

    @Option(names = "-m", description = "Enum values: ${COMPLETION-CANDIDATES}", defaultValue = "P2Y", showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private Mode mode = Mode.P2Y;

    @Option(names = {"-f", "--file"}, paramLabel = "source-file", description = "source file", required = true)
    private File sourceFile;

    @Option(names = "-d", description = "flag to delete source file after the converting", showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private boolean deleteSourceFile;

    @Option(names = {"-ph", "--print-help"}, description = "Help", showDefaultValue = CommandLine.Help.Visibility.ALWAYS)
    private boolean help;

    // this example implements Callable, so parsing, error handling and handling user
    // requests for usage help or version help can be done with one line of code.
    public static void main(String... args) {
        System.exit(new CommandLine(new Yml2PropApplication()).execute(args));
    }

    @Override
    public Integer call() throws IOException { // your business logic goes here...
        if (this.help)
            CommandLine.usage(new Yml2PropApplication(), System.out);

        switch (mode) {
            case Y2P:
                break;
            case P2Y:
            default:
                PropertiesConverter.getProp2YmlConverter().convert(this.sourceFile);
                break;
        }

        return 0;
    }

    public boolean isDeleteSourceFile() {
        return deleteSourceFile;
    }

    public File getSourceFile() {
        return sourceFile;
    }
}
