/*
 * Copyright 2018 by BerryWorks Software, LLC. All rights reserved.
 *
 */

package com.berryworks.edireader.json.controller;

import com.berryworks.edireader.util.CommandLine;

import static com.berryworks.edireader.demo.EDItoXML.establishInput;
import static com.berryworks.edireader.splitter.SplitEdi.DEFAULT_NAMING_PATTERN;
import static com.berryworks.edireader.util.FixedLength.isPresent;

import com.berryworks.edireader.splitter.SplitEdi;

/**
 * Converts EDI input into a series of XML output files
 * such that each XML file corresponds to one document
 * from the EDI input. For X12, a transaction within a functional group
 * is considered to be a document. For EDIFACT, a message is considered
 * to be a document.
 * <p>
 * The tool can also be configured to place more then one
 * document into a given XML output file. For example, an input file
 * with 1000 documents is, by default, split into 1000 XML files each
 * containing one document. By configuration, 100 XML files each containing
 * 10 documents is also possible.
 */
public class SplitEdiApp {

    private static int countFromMostRecentExecution;

    public static void main(String[] args) {
        CommandLine commandLine = new CommandLine(args);
        String inputFileName = commandLine.getPosition(0);
        if (inputFileName == null) {
            badArgs();
        }

        final SplitEdi splitEdi = new SplitEdi();

        String outputFileNamePattern = commandLine.getOption("namingPattern");
        if (isPresent(outputFileNamePattern)) {
            splitEdi.setNamingPattern(outputFileNamePattern);
        } else {
            outputFileNamePattern = DEFAULT_NAMING_PATTERN;
        }

        int documentsPerFile = 1;
        String documentsPerFileAsString = commandLine.getOption("documentsPerFile");
        if (isPresent(documentsPerFileAsString)) {
            try {
                documentsPerFile = Integer.parseInt(documentsPerFileAsString);
                splitEdi.setDocumentsPerFile(documentsPerFile);
            } catch (NumberFormatException e) {
                badArgs();
            }
        }

        int segmentsPerFileLimit = 0;
        String segmentsPerFileAsString = commandLine.getOption("segmentsPerFile");
        if (isPresent(segmentsPerFileAsString)) {
            try {
                segmentsPerFileLimit = Integer.parseInt(segmentsPerFileAsString);
                splitEdi.setSegmentsPerFile(segmentsPerFileLimit);
            } catch (NumberFormatException e) {
                badArgs();
            }
        }

        final String xsdFilePath = commandLine.getOption("xsd");
        final String validateOption = commandLine.getOption("validate");
        boolean isValidate = false;
        if (isPresent(xsdFilePath)) {
            if (isPresent(validateOption)) {
                isValidate = "true".equals(validateOption);
            } else {
                isValidate = true;
            }
            splitEdi.setXsd(xsdFilePath, isValidate);
        } else {
            if ("true".equals(validateOption)) {
                System.err.println("An XSD must be provided for validation");
                badArgs();
            }
        }

        final String annotateOption = commandLine.getOption("annotate");
        boolean isAnnotate = false;
        if (isPresent(annotateOption)) {
            isAnnotate = "true".equals(annotateOption);
            splitEdi.setAnnotate(isAnnotate);
        }

        System.out.println("Starting " + SplitEdiApp.class.getSimpleName());
        System.out.println("Pattern for naming output files: " + outputFileNamePattern);
        System.out.println("Documents per file:              " + (documentsPerFile > 1 ? "up to " + documentsPerFile : "1"));
        System.out.println("Segments per file:               " + (segmentsPerFileLimit > 0 ? segmentsPerFileLimit + " EDI segments" : "unlimited"));
        System.out.println("XSD:                             " + (isPresent(xsdFilePath) ? xsdFilePath : "none"));
        System.out.println("Validation:                      " + (isValidate ? "via XSD" : "none"));
        System.out.println("Annotation:                      " + (isAnnotate ? "enabled" : "disabled"));

        splitEdi.run(establishInput(inputFileName));
        countFromMostRecentExecution = splitEdi.getCount();

        String s = System.getProperty("line.separator");
        System.out.print(s + "EDI input parsed into " + countFromMostRecentExecution + " XML output files" + s);
    }

    private static void badArgs() {
        final String newLine = System.getProperty("line.separator");

        final String usage = newLine + "Usage: " + newLine + newLine +
                "SplitEdi inputFile options..." + newLine + newLine +
                "Options:" + newLine +
                "  -namingPattern pattern   (default is SplitEdi-0000.xml)" + newLine +
                "  -documentsPerFile n      (default is 1)" + newLine +
                "  -segmentsPerFile n       (default is 1)" + newLine +
                "  -xsd path                (required for validation)" + newLine +
                "  -validate true|false     (default is true if XSD is provided, false otherwise)" + newLine +
                "  -annotate true|false     (default is false)" + newLine;
        System.err.println(usage);
        throw new RuntimeException("Missing or invalid command line arguments");
    }

    static int getCount() {
        return countFromMostRecentExecution;
    }
}
