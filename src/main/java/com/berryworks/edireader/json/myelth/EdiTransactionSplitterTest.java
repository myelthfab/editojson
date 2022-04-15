package com.berryworks.edireader.json.myelth;

import static com.berryworks.ediwriter.splitter.transaction.EdiTransactionSplitter.DEFAULT_SPLITTING_RULES;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.junit.Test;
import org.xml.sax.SAXException;

public class EdiTransactionSplitterTest {

    private EdiTransactionSplitter ediTransactionSplitter;
    private List<String> result;

    @Test
    public void canSplit834_intoStrings() throws IOException, SAXException {
    	
//    	String filePath = "/Users/prakashsingh/eclipse-workspace/scottmarchreleasesplit/sample.edi";
//    	String filePath = "/Users/prakashsingh/eclipse-workspace/editojsonsam/src/main/resources/test1.edi";
    	String filePath = "/Users/prakashsingh/eclipse-workspace/editojsonsam/src/main/resources/test.edi";
    	String content = "";
    	 
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
            System.out.println(content);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
        ediTransactionSplitter = new EdiTransactionSplitter();
        result = ediTransactionSplitter.split(content);
        System.out.println("Total no of edis: "+result.size());
        assertEquals(2, result.size());
        assertEquals(SPLIT_834_1, result.get(0));
    }

//    @Test
    public void canSplit834_intoFiles() throws IOException, SAXException {
        ediTransactionSplitter = new EdiTransactionSplitter(
                new StringReader(SAMPLE_834),
                SAMPLE_834,
                "temp-0000.split");
        ediTransactionSplitter.setSplittingConfiguration(
                new SplittingConfigurationBuilder().parse(new StringReader(DEFAULT_SPLITTING_RULES)));
        ediTransactionSplitter.run();
    }

    
    
    
    public static final String SAMPLE_834 = "ISA*00*          *00*          *ZZ*GroupName      *ZZ*Aultcare       *170427*1527*^*00501*000000001*0*P*:~\n" +
            "GS*BE*GroupName*AultCare*20170427*1530*0001*X*005010X220A1~\n" +
            "ST*834*0001*005010X220A1~\n" +
            "BGN*00*00001*20170427*15342212*ES***4~\n" +
            "N1*P5*GroupName*FI*GroupIdentificationCode~\n" +
            "N1*IN*AULTCARE*FI*34-1488123~\n" +
            // The 1st INS loop
            "INS*Y*18*030*07*A*A**FT~\n" +
            "REF*0F*000000000~\n" +
            "REF*1L*12345~\n" +
            "DTP*303*D8*20170228~\n" +
            "DTP*336*D8*20170101~\n" +
            "NM1*IL*1*LastNameA*FirstName*MiddleName***34*000000000~\n" +
            "PER*IP**HP*3301231234*EM*email@email.com~\n" +
            "N3*AddressLineA1*AddressLine2~\n" +
            "N4*City*OH*44669**CY*County~\n" +
            "DMG*D8*19740101*M*D~\n" +
            "NM1*31*1~\n" +
            "N3*MailAddressLine1*MailAddressLine2~\n" +
            "N4*MailCity*OH*44669~\n" +
            "HD*030**HLT*CoverageCodeId1*ECH~\n" +
            "DTP*348*D8*20170201~\n" +
            "REF*17*BenefitPlanId1~\n" +
            "REF*RB*RateCode1~\n" +
            "HD*030**HLT*CoverageCodeId2*EMP~\n" +
            "DTP*348*D8*20170301~\n" +
            "REF*17*BenefitPlanId2~\n" +
            "REF*RB*RateCode2~\n" +
            // The 2nd INS loop
            "INS*N*19*030*07*A~\n" +
            "REF*0F*000000000~\n" +
            "REF*1L*12345~\n" +
            "DTP*303*D8*20170228~\n" +
            "NM1*IL*1*LastNameB*FirstName*MiddleName***34*000000001~\n" +
            "PER*IP**HP*3301231234*EM*email@email.com~\n" +
            "N3*AddressLineB1*AddressLine2~\n" +
            "N4*City*OH*44669**CY*County~\n" +
            "DMG*D8*19990101*F~\n" +
            "NM1*31*1~\n" +
            "N3*MailAddressLine1*MailAddressLine2~\n" +
            "N4*MailCity*OH*44669~\n" +
            "HD*030**HLT*CoverageCodeId1*ECH~\n" +
            "DTP*348*D8*20170201~\n" +
            "REF*17*BenefitPlanId1~\n" +
            "REF*RB*RateCode1~\n" +
            "HD*030**HLT*CoverageCodeId2*ECH~\n" +
            "DTP*348*D8*20170201~\n" +
            "DTP*349*D8*20170228~\n" +
            "REF*17*BenefitPlanId2~\n" +
            "REF*RB*RateCode2~\n" +
            "SE*47*0001~\n" +
            "GE*1*0001~\n" +
            "IEA*1*000000001~\n";

    public static final String SPLIT_834_1 = "ISA*00*          *00*          *ZZ*GroupName      *ZZ*Aultcare       *170427*1527*^*00501*000000001*0*P*:~\n" +
            "GS*BE*GroupName*AultCare*20170427*1530*0001*X*005010X220A1~\n" +
            "ST*834*0001*005010X220A1~\n" +
            "BGN*00*00001*20170427*15342212*ES***4~\n" +
            "N1*P5*GroupName*FI*GroupIdentificationCode~\n" +
            "N1*IN*AULTCARE*FI*34-1488123~\n" +
            // The 1st INS loop
            "INS*Y*18*030*07*A*A**FT~\n" +
            "REF*0F*000000000~\n" +
            "REF*1L*12345~\n" +
            "DTP*303*D8*20170228~\n" +
            "DTP*336*D8*20170101~\n" +
            "NM1*IL*1*LastNameA*FirstName*MiddleName***34*000000000~\n" +
            "PER*IP**HP*3301231234*EM*email@email.com~\n" +
            "N3*AddressLineA1*AddressLine2~\n" +
            "N4*City*OH*44669**CY*County~\n" +
            "DMG*D8*19740101*M*D~\n" +
            "NM1*31*1~\n" +
            "N3*MailAddressLine1*MailAddressLine2~\n" +
            "N4*MailCity*OH*44669~\n" +
            "HD*030**HLT*CoverageCodeId1*ECH~\n" +
            "DTP*348*D8*20170201~\n" +
            "REF*17*BenefitPlanId1~\n" +
            "REF*RB*RateCode1~\n" +
            "HD*030**HLT*CoverageCodeId2*EMP~\n" +
            "DTP*348*D8*20170301~\n" +
            "REF*17*BenefitPlanId2~\n" +
            "REF*RB*RateCode2~\n" +
            "SE*26*0001~\n" +
            "GE*1*0001~\n" +
            "IEA*1*000000001~\n";

    public static final String SPLIT_834_2 = "ISA*00*          *00*          *ZZ*GroupName      *ZZ*Aultcare       *170427*1527*^*00501*000000001*0*P*:~\n" +
            "GS*BE*GroupName*AultCare*20170427*1530*0001*X*005010X220A1~\n" +
            "ST*834*0001*005010X220A1~\n" +
            "BGN*00*00001*20170427*15342212*ES***4~\n" +
            "N1*P5*GroupName*FI*GroupIdentificationCode~\n" +
            "N1*IN*AULTCARE*FI*34-1488123~\n" +
            // The 2nd INS loop
            "INS*N*19*030*07*A~\n" +
            "REF*0F*000000000~\n" +
            "REF*1L*12345~\n" +
            "DTP*303*D8*20170228~\n" +
            "NM1*IL*1*LastNameB*FirstName*MiddleName***34*000000001~\n" +
            "PER*IP**HP*3301231234*EM*email@email.com~\n" +
            "N3*AddressLineB1*AddressLine2~\n" +
            "N4*City*OH*44669**CY*County~\n" +
            "DMG*D8*19990101*F~\n" +
            "NM1*31*1~\n" +
            "N3*MailAddressLine1*MailAddressLine2~\n" +
            "N4*MailCity*OH*44669~\n" +
            "HD*030**HLT*CoverageCodeId1*ECH~\n" +
            "DTP*348*D8*20170201~\n" +
            "REF*17*BenefitPlanId1~\n" +
            "REF*RB*RateCode1~\n" +
            "HD*030**HLT*CoverageCodeId2*ECH~\n" +
            "DTP*348*D8*20170201~\n" +
            "DTP*349*D8*20170228~\n" +
            "REF*17*BenefitPlanId2~\n" +
            "REF*RB*RateCode2~\n" +
            "SE*26*0001~\n" +
            "GE*1*0001~\n" +
            "IEA*1*000000001~\n";

}
