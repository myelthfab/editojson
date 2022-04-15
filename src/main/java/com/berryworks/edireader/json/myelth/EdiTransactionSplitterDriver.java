package com.berryworks.edireader.json.myelth;

import com.berryworks.ediwriter.splitter.transaction.EdiTransactionSplitter;
import com.berryworks.ediwriter.splitter.transaction.SplittingConfiguration;
import com.berryworks.ediwriter.splitter.transaction.SplittingConfigurationBuilder;
import org.xml.sax.SAXException;

import java.io.*;
import java.nio.charset.StandardCharsets;

import static java.lang.String.format;

// TODO: support the splitting configuration from a file specified as a command line argument
// TODO: support recover option, to continue splitting if possible after a recoverable error in the EDI input
// TODO: support a means to capture EDI output as a series of Strings instead of a series of files

public class EdiTransactionSplitterDriver implements Runnable {
    private File ediFile, splittingConfigurationFile;
    private String filenamePattern;
    private boolean recover;

    public EdiTransactionSplitterDriver() {
        // Set defaults for options
        recover = true;
    }

    @Override
    public void run() {

        // Make sure the EDI input is available
        if (ediFile == null || !ediFile.exists() || !ediFile.canRead()) {
            throw new RuntimeException("An EDI input file must be provided");
        }

        // A template is needed to tell the splitter what syntax characters (delimiters, terminators, ...)
        // to use in the generated EDI output. A template is simply a preview of a sample EDI file with the desired
        // syntax characters. By "preview", we mean that only the first two segments are needed, ISA and GS.
        // e read the first portion of our EDI input and use that as the template, so that the EDI output will have
        // the same syntax characteristics as the original input.
        // It would be easy to add other options.
        String templateString = establishTemplate(ediFile);

        // Use a default naming pattern for the output files if one is not provided.
        // Here is a summary of how the naming patten works: it is simply the name for an output file, with a series
        // of zeros to represent where a sequence number will be substituted.
        // For example, if the pattern is "abc0000.edi" and 3 output files are generated, the splitter will produce
        // output files abc0001.edi, abc0002.edi, and abc0003.edi (assuming that none of these files already exist).
        // The sequence number is used to create new filenames without overwriting existing files, so if the splitter
        // is executed again with the same input file, 3 more output files will be created: abc0004.edi, abc0005,edi,
        // and abc0006.edi.
        if (filenamePattern == null) {
            filenamePattern = "Split-0000.edi";
        }

        // Build the splitting configuration, either from a provided file or using a default.
        // This says that we want to split 834s so that each instance of an INS loop goes into its own output file.
        SplittingConfiguration splittingConfiguration;
        if (splittingConfigurationFile == null) {
            splittingConfiguration = new SplittingConfigurationBuilder().parse(new StringReader("" +
                    "document 834\n" +
                    "    split INS\n" +
                    "    keep all\n"));
        } else {
            try (Reader reader = new FileReader(splittingConfigurationFile)) {
                splittingConfiguration = new SplittingConfigurationBuilder().parse(reader);
            } catch (IOException e) {
                throw new RuntimeException(
                        format("Unable to build splitting configuration using file %s",
                                splittingConfigurationFile.getName()),
                        e);
            }
        }

        try (Reader input = new InputStreamReader(new FileInputStream(ediFile), StandardCharsets.ISO_8859_1)) {

            EdiTransactionSplitter splitter = new EdiTransactionSplitter(input, templateString, filenamePattern);
            
            splitter.setSplittingConfiguration(splittingConfiguration);
            splitter.run();

        } catch (IOException | SAXException e) {
            throw new RuntimeException("Failure in splitting process", e);
        }
    }

    public void setEdiFile(File ediFile) {
        this.ediFile = ediFile;
    }

    public void setFilenamePattern(String filenamePattern) {
        this.filenamePattern = filenamePattern;
    }

    public void setSplittingConfigurationFile(File splittingConfigurationFile) {
        this.splittingConfigurationFile = splittingConfigurationFile;
    }

    public static void main(String[] args) {

        if (args.length < 1) {
            badArgs();
            return;
        }

        final EdiTransactionSplitterDriver driver = new EdiTransactionSplitterDriver();

        // Args beginning with "--" are treated as options.
        // The first arg not beginning with "--" is the name of the input file.

        for (String arg : args) {
            if (arg.startsWith("--")) {
                // --option=value
                final String[] split = arg.split("=");
                if (split.length != 2) {
                    badArgs();
                    return;
                }
                String optionName = split[0];
                String optionValue = split[1];
                switch (optionName) {
                    case "--recover":
                        driver.setRecover("yes".equalsIgnoreCase(optionValue));
                        break;
                    case "--namingPattern":
                        driver.setFilenamePattern(optionValue);
                        break;
                    default:
                        badArgs();
                        return;
                }
            } else if ("help".equals(arg) && args.length == 1) {
                logUsage();
                return;
            } else {
                driver.setEdiFile(new File(arg));
            }
        }

        try {
            driver.run();
        } catch (Exception e) {
            throw new RuntimeException("Internal error", e);
        }
    }

    private static void badArgs() {
        System.err.println("Invalid command line arguments");
        logUsage();
    }

    private static void logUsage() {
        log();
        log("Command line arguments:   ediInputFile  [options]");
        log();
        log("options");
        log("  ", "--namingPattern=pattern", ":", "pattern for naming EDi output files");
        log("  ", "--recover={yes|no}", ":", "if yes, ignore any recoverable EDI errors (default is no)");
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

    public void setRecover(boolean recover) {
        this.recover = recover;
    }

    protected static String establishTemplate(File templateFile) {

        try (Reader templateReader = new FileReader(templateFile)) {
            final int bufferSize = 300;
            final char[] buffer = new char[bufferSize];

            int offset = 0;
            int needed = bufferSize;
            while (needed > 0) {
                try {
                    int n = templateReader.read(buffer, offset, needed);
                    if (n < 0)
                        throw new RuntimeException("Unexpected end of data reading EDI input");
                    needed -= n;
                    offset += n;
                } catch (IOException e) {
                    throw new RuntimeException("Unable to create template for generating EDI", e);
                }
            }
            return String.valueOf(buffer);
        } catch (IOException e) {
            throw new RuntimeException(format("Unable to establish template from file %s", templateFile.getName()), e);
        }
    }

}
