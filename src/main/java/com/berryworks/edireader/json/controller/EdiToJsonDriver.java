package com.berryworks.edireader.json.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.List;

import com.berryworks.edireader.EDISyntaxException;
import com.berryworks.edireader.json.driver.entities.Root;
import com.berryworks.edireader.json.driver.request.EDIRequest;
import com.berryworks.edireader.json.fromedi.EdiToJson;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EdiToJsonDriver implements Runnable {
    private File ediFile, jsonFile, errorFile;
    private boolean summarize, annotate, format, recover;

    public EdiToJsonDriver() {
        // Set defaults for options
        summarize = false;
        annotate = false;
        format = true;
        recover = false;
    }

    public void setInputFile(File ediFile) {
        this.ediFile = ediFile;
    }

    public void setOutputFile(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    private void setErrorFile(File errorFile) {
        this.errorFile = errorFile;
        if (errorFile != null) {
            // If errors are to be written to an errors file, then the recover option must be on.
            recover = true;
        }
    }

    @Override
    public void run() {
        final EdiToJson ediToJson = new EdiToJson();
        ediToJson.setFormatting(format);
        ediToJson.setAnnotated(annotate);
        ediToJson.setSummarize(summarize);
        if (recover) {
            ediToJson.setRecover();
        }
        List<EDISyntaxException> ediExceptions = ediToJson.getEdiSyntaxExceptions();
        try (Reader reader = new BufferedReader(ediFile == null ? new InputStreamReader(System.in) : new FileReader(ediFile));
             Writer writer = new BufferedWriter(jsonFile == null ? new OutputStreamWriter(System.out) : new FileWriter(jsonFile))) {
            ediToJson.asJson(reader, writer);
            
            String json = ediToJson.asJson(reader);
        	System.out.println("path after json convertion: " + json);
        	
        	ObjectMapper mapper = new ObjectMapper();
        	System.out.println("pbefore root convertn: " + json);
//        	Map<String,Object> map = mapper.readValue(json, Map.class);
        	Root root = mapper.readValue(json, Root.class);
        	EDIRequest ediRequest = JsonToJava.transform(root);
            
//            JSONParser parser = new JSONParser();
//    	    Object object = parser.parse(Stringtojson);
    	    
//    	    //convert Object to JSONObject
//    	    JSONObject jsonObject3 = (JSONObject)object;
//    	    try {
//    	        FileWriter file = new FileWriter("F:\\myjson.json");
//    	        file.write(jsonObject3.toJSONString());
//    	        file.close();
//    	        } catch (IOException e) {                                        
//    	        e.printStackTrace();
//    	        }
        } catch (EDISyntaxException e) {
            ediExceptions.add(e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
        if (errorFile != null) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(errorFile))) {
                if (ediExceptions != null && ediExceptions.size() > 0) {
                    for (EDISyntaxException se : ediExceptions) {
                        writer.write(se.getMessage());
                        writer.newLine();
                    }
                } else {
                    writer.write("No EDI errors detected");
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new RuntimeException("Cannot write EDI errors to file: " + errorFile.getAbsolutePath(), e);
            }
        }
    }

    public static void main(String[] args) {

        final EdiToJsonDriver driver = new EdiToJsonDriver();

        // Args beginning with "--" are treated as options.
        // The first arg not beginning with "--" is the name of the input file.
        // The next arg not beginning with "--" is the name of the output file.
        boolean establishedInputFile = false;
        boolean establishedOutputFile = false;
        for (String arg : args) {
            if (arg.startsWith("--")) {
                // --option=value
                final String[] split = arg.split("=");
                if (split.length != 2) {
                    badArgs();
                    return;
                }
                String optionName = split[0];
                String yesOrNo = split[1];
                switch (optionName) {
                    case "--summarize":
                        driver.setSummarize("yes".equalsIgnoreCase(yesOrNo));
                        break;
                    case "--annotate":
                        driver.setAnnotate("yes".equalsIgnoreCase(yesOrNo));
                        break;
                    case "--format":
                        driver.setFormat("yes".equalsIgnoreCase(yesOrNo));
                        break;
                    case "--recover":
                        driver.setRecover("yes".equalsIgnoreCase(yesOrNo));
                        break;
                    default:
                        badArgs();
                        return;
                }
            } else if ("help".equals(arg) && args.length == 1) {
                logUsage();
                return;
            } else {
                // inputFileName, outputFileName, or errorFileName
                if (establishedOutputFile) {
                    driver.setErrorFile(new File(arg));
                } else if (establishedInputFile) {
                    driver.setOutputFile(new File(arg));
                    establishedOutputFile = true;
                } else {
                    driver.setInputFile(new File(arg));
                    establishedInputFile = true;
                }
            }
        }

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
        log("Usage Summary");
        log("=============");
        log();
        log("Read EDI from an input file, write JSON to an output file");
        log("  java -jar <jarFileName>  <ediInputFile>  <jsonOutputFile>  <options>");
        log();
        log("Read EDI from an input file, write JSON to stdout");
        log("  java -jar <jarFileName>  <ediInputFile>  <options>");
        log();
        log("Read EDI from stdin, write JSON to stdout");
        log("  java -jar <jarFileName>  <options>");
        log();
        log("Display this usage summary");
        log("  java -jar <jarFileName>  help");
        log();
        log();
        log("options");
        log("  ", "--summarize={yes|no}", ":", "if yes, omit segment-level detail (default is no)");
        log("  ", "--annotate={yes|no}", ":", "if yes, include descriptive \"annotations\" (default is no)");
        log("  ", "--format={yes|no}", ":", "if yes, format JSON output (default is yes)");
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

    private void setSummarize(boolean summarize) {
        this.summarize = summarize;
    }

    private void setAnnotate(boolean annotate) {
        this.annotate = annotate;
    }

    private void setFormat(boolean format) {
        this.format = format;
    }

    public void setRecover(boolean recover) {
        this.recover = recover;
    }
}
