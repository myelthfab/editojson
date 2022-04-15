package com.berryworks.edireader.json.controller;

import com.berryworks.edireader.json.toedi.JsonToEdi;

import java.io.*;


public class JsonToEdiDriver implements Runnable {
    private final File ediFile;
    private final File jsonFile;

    public JsonToEdiDriver(File jsonFile, File ediFile) {
        this.jsonFile = jsonFile;
        this.ediFile = ediFile;
    }

    @Override
    public void run() {
        final JsonToEdi jsonToEdi = new JsonToEdi();
        try (Reader reader = new FileReader(jsonFile); Writer writer = new FileWriter(ediFile)) {
            jsonToEdi.asEdi(reader, writer);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            badArgs();
            return;
        }
        // The first two args are the input and output filenames.
        final JsonToEdiDriver driver = new JsonToEdiDriver(new File(args[0]), new File(args[1]));
        try {
            driver.run();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    private static void badArgs() {
        System.err.println("Invalid command line arguments");
        logUsage();
    }

    private static void logUsage() {
        log();
        log("command-line arguments:  jsonInputFile  ediOutputFile  <options>");
        log();
        log("options");
        log("  ", "--terminator={printableCharacter|LF|CRLF}");
        log("       ", "Specifies the segment terminator");
        log("       ", "Default: $");
        log("       ", "Examples: --terminator=$ --terminator=LF --terminator=CRLF");
        log("  ", "--delimiter=printableCharacter");
        log("       ", "Specifies the field delimiter");
        log("       ", "Default: *");
        log("       ", "Examples: --delimiter=* --delimiter=+");
        log("  ", "--subDelimiter=printableCharacter");
        log("       ", "Specifies the sub-field delimiter within composite elements");
        log("       ", "Default: :");
        log("       ", "Examples: --subDelimiter=* --subDelimiter=+");
        log("  ", "--repetitionSeparator=printableCharacter");
        log("       ", "Specifies the separator between repeated elements");
        log("       ", "Default: :");
        log("       ", "Examples: --repetitionSeparator=* --repetitionSeparator=+");
        log("  ", "--terminatorSuffix={NONE|LF|CRLF}");
        log("       ", "Specifies line termination following every segment terminator");
        log("       ", "Default: LF");
        log("       ", "Examples: --terminatorSuffix=NONE --terminatorSuffix=LF --terminatorSuffix=CRLF");
        log();
    }

    private static void log(Object... items) {
        StringBuilder sb = new StringBuilder();
        for (Object item : items) {
            if (sb.length() > 0) {
                sb.append(" ");
            }
            sb.append(item.toString());
        }
        System.out.println(sb.toString());
    }
}

