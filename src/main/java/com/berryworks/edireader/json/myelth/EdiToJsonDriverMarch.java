package com.berryworks.edireader.json.myelth;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.xml.sax.SAXException;

import com.berryworks.edireader.EDISyntaxException;
import com.berryworks.edireader.json.fromedi.EdiToJson;
import com.berryworks.ediwriter.splitter.transaction.EdiTransactionSplitter;

public class EdiToJsonDriverMarch implements Runnable {
    private File ediFile, jsonFile, errorFile;
    private boolean summarize, annotate, format, recover;

    public EdiToJsonDriverMarch() {
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
        
        
        
        String strbyte = "ISA*03*          *00*          *ZZ*DDCA78795      *ZZ*942411167      *190419*0932*^*00501*000000397*0*P*:~GS*BE*680393304*161264154*20190419*0932*397*X*005010X220A1~ST*834*0397*005010X220A1~BGN*00*20190419441*20190419*093241*PT***4~REF*38*CA78795~DTP*007*D8*20190419~N1*P5*WESTERN HEALTH ADVANTAGE*FI*68-0393304~N1*IN*Metlife*FI*161264154~INS*N*19*030**A~REF*0F*WH000138754~REF*1L*CA1861400001~REF*DX*103792~REF*23*WH000138754002~NM1*IL*1*KELLY*COLE*M***34*607061375~PER*IP**TE*9169921218~N3*1040 SKI PARK CT~N4*RIO LINDA*CA*95673~DMG*D8*19980429*M~HD*030**DEN~DTP*348*D8*20180101~REF*CE*18614WH0000001~SE*20*0397~GE*1*397~IEA*1*000000397~";
      	 
       ;
       
       strbyte = "ISA*00*          *00*          *ZZ*DDNEIC00       *ZZ*941461312      *211020*0356*^*00501*921293001*0*P*:~\n" + 
       		"GS*HC*133052274*941461312*20211020*0356*921293001*X*005010X224A2~\n" + 
       		"ST*837*000000001*005010X22224A2~\n" + 
       		"BHT*0019*00*9211293001*20211020*0356*CH~\n" + 
       		"NM1*41*2*GENERAL CLAIMS INC*****46*133052274~\n" + 
       		"PER*IC*EMDEON DENTAL OPS*TE*8773940027~\n" + 
       		"NM1*40*2*PAYER PLAN*****46*941461312~\n" + 
       		"HL*1**20*1~\n" + 
       		"NM1*85*2*WILLIAM W. JOHNSON, D.*****XX*1276452145~\n" + 
       		"N3*56 JENNY LN SUITE A~\n" + 
       		"N4*SAN JOSE*CA*950769998~\n" + 
       		"REF*EI*454505175~\n" + 
       		"PER*IC*WILLIAM W JOHNSON D*TE*519238765~\n" + 
       		"HL*2*1*22*0~\n" + 
       		"SBR*P*18*5025010004******CI~\n" + 
       		"NM1*IL*1*JONATHAN*ROBERT****MI*573221234~\n" + 
       		"N3*735 OREGON ST~\n" + 
       		"N4*SAN JOSE*CA*950769998~\n" + 
       		"DMG*D8*19600727*M~\n" + 
       		"NM1*PR*2*CALIFORNIA STATE GOVERNMENT PROGRAM*****PI*CPPCA~\n" + 
       		"REF*G2*26517~\n" + 
       		"CLM*87806*211***11:B:1*Y*A*Y*Y~\n" + 
       		"DTP*472*D8*20211019~\n" + 
       		"DN2*1*M****JP~\n" + 
       		"DN2*5*M****JP~\n" + 
       		"DN2*12*M****JP~\n" + 
       		"DN2*16*M****JP~\n" + 
       		"DN2*17*M****JP~\n" + 
       		"DN2*21*M****JP~\n" + 
       		"DN2*28*M****JP~\n" + 
       		"DN2*32*M****JP~\n" + 
       		"REF*D9*X1504487500~\n" + 
       		"NTE*ADD*ENL 56 JENNY LN. SUITE/SAN JOSE/CA/95076~\n" + 
       		"NM1*82*1*JOHNSON*WILLIAM****XX*519238765~\n" + 
       		"PRV*PE*PXC*1223G0001X~\n" + 
       		"REF*G2*26517~\n" + 
       		"LX*1~\n" + 
       		"SV3*AD:D1110*97****1~\n" + 
       		"REF*6R*8780601~\n" + 
       		"LX*2~\n" + 
       		"SV3*AD:D0274*74****1~\n" + 
       		"LX*3~\n" + 
       		"SV3*AD:D0120*40****1~\n" + 
       		"REF*6R*8780603~\n" + 
       		"SE*43*000000001~\n" + 
       		"GE*1*921293001~\n" + 
       		"IEA*1*921293001~";
        
        List<EDISyntaxException> ediExceptions = ediToJson.getEdiSyntaxExceptions();
        String str;
		try {
			str = ediToJson.asJson(strbyte);
			System.out.println("JSON OUTPUT "+str);
		} catch (IOException | SAXException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
//        try (Reader reader = new BufferedReader(ediFile == null ? new InputStreamReader(System.in) : new FileReader(ediFile));
//             Writer writer = new BufferedWriter(jsonFile == null ? new OutputStreamWriter(System.out) : new FileWriter(jsonFile))) {
//            ediToJson.asJson(reader, writer);
//            
//           
//            
//        } catch (EDISyntaxException e) {
//            ediExceptions.add(e);
//        } catch (Exception e) {
//            throw new RuntimeException(e.getMessage());
//        }
//        if (errorFile != null) {
//            try (BufferedWriter writer = new BufferedWriter(new FileWriter(errorFile))) {
//                if (ediExceptions != null && ediExceptions.size() > 0) {
//                    for (EDISyntaxException se : ediExceptions) {
//                        writer.write(se.getMessage());
//                        writer.newLine();
//                    }
//                } else {
//                    writer.write("No EDI errors detected");
//                    writer.newLine();
//                }
//            } catch (IOException e) {
//                throw new RuntimeException("Cannot write EDI errors to file: " + errorFile.getAbsolutePath(), e);
//            }
//        }
    }

    public static void main(String[] args) {

        final EdiToJsonDriverMarch driver = new EdiToJsonDriverMarch();

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
