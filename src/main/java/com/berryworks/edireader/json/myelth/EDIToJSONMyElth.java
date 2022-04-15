package com.berryworks.edireader.json.myelth;
import static com.berryworks.ediwriter.splitter.transaction.EdiTransactionSplitter.DEFAULT_SPLITTING_RULES;

import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

import com.berryworks.edireader.json.driver.entities.FunctionalGroup;
import com.berryworks.edireader.json.driver.entities.HD2300Loop;
import com.berryworks.edireader.json.driver.entities.INS2000Loop;
import com.berryworks.edireader.json.driver.entities.Interchanx;
import com.berryworks.edireader.json.driver.entities.N11000Loop;
import com.berryworks.edireader.json.driver.entities.NM12100Loop;
import com.berryworks.edireader.json.driver.entities.Root;
import com.berryworks.edireader.json.driver.entities.Segment;
import com.berryworks.edireader.json.driver.entities.Transaction;
import com.berryworks.edireader.json.driver.request.Address;
import com.berryworks.edireader.json.driver.request.EDIRequest;
import com.berryworks.edireader.json.driver.request.Eligibility;
import com.berryworks.edireader.json.driver.request.Enrollee;
import com.berryworks.edireader.json.driver.request.Member;
import com.berryworks.edireader.json.driver.request.MyElthContract;
import com.berryworks.edireader.json.driver.request.Person;
import com.berryworks.edireader.json.fromedi.EdiToJson;
import com.berryworks.ediwriter.splitter.transaction.EdiTransactionSplitter;
import com.berryworks.ediwriter.splitter.transaction.SplittingConfigurationBuilder;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class EDIToJSONMyElth {
	
    private EdiTransactionSplitter ediTransactionSplitter;
    private List<String> result;

	public static void main(String[] args) {
		String filePath = "/Users/prakashsingh/eclipse-workspace/scottmarchreleasesplit/testoracle.edi";
		new EDIToJSONMyElth().convertEDIToJSON(filePath);
	}
	
	
	public List<MyElthContract> convertEDIToJSON(String filePath) {
		
		List<MyElthContract> contracts = new ArrayList<MyElthContract>();
//		String filePath = "/Users/prakashsingh/eclipse-workspace/scottmarchreleasesplit/testoracle.edi";
    	String content = "";
    	 
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
//            System.out.println(content);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
        ediTransactionSplitter = new EdiTransactionSplitter();
        
        try {
			result = ediTransactionSplitter.split(content);
		} catch (IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		EdiToJson ediToJson = new EdiToJson();
        ediToJson.setFormatting(true);
        ediToJson.setAnnotated(true);
        ediToJson.setSummarize(false);
        if (true) {
            ediToJson.setRecover();
        }
        
        int counter = 0;
        int subscriber = 0;
        int dependent = 0;
        int unknown = 0;
        int noaction = 0;
        int noRelationship = 0;
        Map<String, Integer> action = new HashMap<String, Integer>();
        Map<String, Integer> enrollmentAction = new HashMap<String, Integer>();
        Map<String, Integer> relationship = new HashMap<String, Integer>();
        Map<String, Integer> groupnumber = new HashMap<String, Integer>();
        Map<String, Integer> medicaidProviderId = new HashMap<String, Integer>();
        for (String strbyte : result) {
				counter++;
		try {
		ediToJson = new EdiToJson();
        ediToJson.setFormatting(true);
        ediToJson.setAnnotated(true);
        ediToJson.setSummarize(false);
		 if (true) {
	            ediToJson.setRecover();
	        }
        String memberJson = ediToJson.asJson(strbyte);
        System.out.println("memberJson::: " + counter);
        System.out.println(memberJson);
        System.out.println("memberJson JSON DONS::: "+counter);
        MyElthContract contract = convertJSONToJavaObject(memberJson);
        contracts.add(contract);
        
        
        if(contract != null) {
//        	if(contract.getSubscriber() != null) {
//        		
//        	} else if (contract.getDependents() != null && !contract.getDependents().isEmpty()) {
//        		
//        	} else {
//        		
//        	}
//        	if(contract.getEdiMember().getSubscriberOrDependent() != null && contract.getEdiMember().getSubscriberOrDependent().contains("S")){
//        		subscriber++;
//        	} else if(contract.getEdiMember().getSubscriberOrDependent() != null && 
//        			contract.getEdiMember().getSubscriberOrDependent().contains("D")){
//        		dependent++;
//        	} else {
//        		unknown++;
//        	}
//        	if(contract.getActionCode() != null) {
//        		if(action.containsKey(contract.getActionCode())) {
//        			action.put(contract.getActionCode(), action.get(contract.getActionCode())+1);
//        		} else {
//        			action.put(contract.getActionCode(), 1);
//        		}
//        	} 
//        	
//        	if(contract.getEdiMember().getEnrollmentAction() != null) {
//        		if(enrollmentAction.containsKey(contract.getEdiMember().getEnrollmentAction())) {
//        			enrollmentAction.put(contract.getEdiMember().getEnrollmentAction(), enrollmentAction.get(contract.getEdiMember().getEnrollmentAction())+1);
//        		} else {
//        			enrollmentAction.put(contract.getEdiMember().getEnrollmentAction(), 1);
//        		}
//        	} else {
//        		noaction++;
//        	}
//        	if(contract.getMedicaidProviderID() != null) {
//        		if(medicaidProviderId.containsKey(contract.getMedicaidProviderID())) {
//        			medicaidProviderId.put(contract.getMedicaidProviderID(), medicaidProviderId.get(contract.getMedicaidProviderID())+1);
//        		} else {
//        			medicaidProviderId.put(contract.getMedicaidProviderID(), 1);
//        		}
//        	}
//        	
//        	if(contract.getGroupNumber() != null) {
//        		if(groupnumber.containsKey(contract.getGroupNumber())) {
//        			groupnumber.put(contract.getGroupNumber(), groupnumber.get(contract.getGroupNumber())+1);
//        		} else {
//        			groupnumber.put(contract.getGroupNumber(), 1);
//        		}
//        	}
//        	
//        	
//        	if(contract.getEdiMember().getRelationshipCode() != null) {
//        		if(relationship.containsKey(contract.getEdiMember().getRelationshipCode())) {
//        			relationship.put(contract.getEdiMember().getRelationshipCode(), relationship.get(contract.getEdiMember().getRelationshipCode())+1);
//        		} else {
//        			relationship.put(contract.getEdiMember().getRelationshipCode(), 1);
//        		}
//        	} else {
//        		noRelationship++;
//        	}
        }
        System.out.println("converted from JSON to Java Object:: ");
        }catch(Throwable e) {
        	System.out.println(e);
        	e.printStackTrace();
        }
        }
    	 System.out.println("all done :: "+counter +" | "+subscriber + " | "+dependent + " | "+unknown + " | "+noaction + " | "+noRelationship);
    	 System.out.println(action);
    	 System.out.println(enrollmentAction);
    	 System.out.println(relationship);
    	 System.out.println(groupnumber);
    	 System.out.println(medicaidProviderId);
        System.out.println("Total no of edis: "+result.size());
        
        return contracts;
        
        /**
         * 
         * 1. Split EDI to smaller EDI
         * 2. convert edi to json 
         * 3. convert json to java
         * 4. post java object to mongodb
         * 5. post json to OHI
         * 6. extract a roster
         * 7. 
         * 
         * 
         * 
         */
        
//        String strbyte = "ISA*03*          *00*          *ZZ*DDCA78795      *ZZ*942411167      *190419*0932*^*00501*000000397*0*P*:~GS*BE*680393304*161264154*20190419*0932*397*X*005010X220A1~ST*834*0397*005010X220A1~BGN*00*20190419441*20190419*093241*PT***4~REF*38*CA78795~DTP*007*D8*20190419~N1*P5*WESTERN HEALTH ADVANTAGE*FI*68-0393304~N1*IN*Metlife*FI*161264154~INS*N*19*030**A~REF*0F*WH000138754~REF*1L*CA1861400001~REF*DX*103792~REF*23*WH000138754002~NM1*IL*1*KELLY*COLE*M***34*607061375~PER*IP**TE*9169921218~N3*1040 SKI PARK CT~N4*RIO LINDA*CA*95673~DMG*D8*19980429*M~HD*030**DEN~DTP*348*D8*20180101~REF*CE*18614WH0000001~SE*20*0397~GE*1*397~IEA*1*000000397~";
      	 
        
//        List<EDISyntaxException> ediExceptions = ediToJson.getEdiSyntaxExceptions();
        
//        String memberINS = "ISA*03*          *00*          *ZZ*DDCA78795      *ZZ*942411167      *190419*0932*^*00501*000000397*0*P*:"
//        		+ "~GS*BE*680393304*161264154*20190419*0932*397*X*005010X220A1"
//        		+ "~ST*834*0397*005010X220A1"
//        		+ "~BGN*00*20190419441*20190419*093241*PT***4"
//        		+ "~REF*38*CA78795~DTP*007*D8*20190419~N1*P5*WESTERN HEALTH ADVANTAGE*FI*68-0393304~N1*IN*Metlife*FI*161264154"
//        		+ "~INS*Y*18*030**A***FT~REF*0F*WH000008352"
//        		+ "~REF*1L*CA7879500001~REF*DX*021474~REF*23*WH000008352000~NM1*IL*1*HUCKINS*JEFFREY*A***34*547745167~PER*IP**TE*5306815785~N3*36245 COUNTY ROAD 24"
//        		+ "~N4*WOODLAND*CA*95695~DMG*D8*19610915*M~HD*030**DEN~DTP*348*D8*20170101~REF*CE*78795WH0000001~SE*20*0397~GE*1*397~IEA*1*000000397~";
//        
//        String memberSai = "ISA*00*          *00*          *ZZ*ORDHS          *ZZ*MB888880       *130312*0206*!*00501*000000201*0*P*:~\n" + 
//        		"\n" + 
//        		"GS*BE*ORDHS*MB888880*20130312*020630*301*X*005010X220A1~\n" + 
//        		"\n" + 
//        		"ST*834*40001*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40001~\n" + 
//        		"\n" + 
//        		"ST*834*40002*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40002~\n" + 
//        		"GE*2*301~\n" + 
//        		"\n" + 
//        		"GS*BE*ORDHS*MB888880*20130312*020630*302*X*005010X220A1~\n" + 
//        		"\n" + 
//        		"ST*834*40003*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40003~\n" + 
//        		"\n" + 
//        		"ST*834*40004*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40004~\n" + 
//        		"GE*2*302~\n" + 
//        		"IEA*2*000000201~\n" + 
//        		"\n" + 
//        		"ISA*00*          *00*          *ZZ*ORDHS          *ZZ*MB888880       *130312*0206*!*00501*000000202*0*P*:~\n" + 
//        		"\n" + 
//        		"GS*BE*ORDHS*MB888880*20130312*020630*303*X*005010X220A1~\n" + 
//        		"\n" + 
//        		"ST*834*40005*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40005~\n" + 
//        		"\n" + 
//        		"ST*834*40006*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40006~\n" + 
//        		"GE*2*303~\n" + 
//        		"\n" + 
//        		"GS*BE*ORDHS*MB888880*20130312*020630*304*X*005010X220A1~\n" + 
//        		"\n" + 
//        		"ST*834*40007*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40007~\n" + 
//        		"\n" + 
//        		"ST*834*40008*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40008~\n" + 
//        		"GE*2*304~\n" + 
//        		"IEA*2*000000202~\n" + 
//        		"\n" + 
//        		"";
//        String memberSai2 = "ISA*00*          *00*          *ZZ*CMSFFM         *ZZ*510228088      *180503*1630*^*00501*415104017*1*T*:~\n" + 
//        		"GS*BE*DE0*26018DE0010004*20190913*1630*0452400*X*005010X220A1~\n" + 
//        		"ST*834*0001*005010X220A1~\n" + 
//        		"BGN*00*0001*20190913*111713*ET***2~\n" + 
//        		"QTY*TO*1~\n" + 
//        		"QTY*DT*0~\n" + 
//        		"N1*P5*sriram Kola*FI*384794082~\n" + 
//        		"N1*IN*DELTA DENTAL INSURANCE COMPANY*FI*510228088~\n" + 
//        		"INS*Y*18*021*EC*A***AC~\n" + 
//        		"REF*0F*120355397~\n" + 
//        		"REF*17*120355397~\n" + 
//        		"REF*6O*DE00077199998~\n" + 
//        		"DTP*356*D8*20210101~\n" + 
//        		"NM1*IL*1*BISHOP*ANTHONY****34*875439~\n" + 
//        		"PER*IP**TE*2006649555*AP*1005545675*EM*8908662231~\n" + 
//        		"N3*1 Pioneer Street~\n" + 
//        		"N4*Bellevue*WA*980040000**CY*10001~\n" + 
//        		"DMG*D8*19330706*M*M*:RET:2106-3~\n" + 
//        		"HLH*T~\n" + 
//        		"HD*021**DEN~\n" + 
//        		"DTP*348*D8*20210101~\n" + 
//        		"DTP*349*D8*20211231~\n" + 
//        		"REF*1L*739456~\n" + 
//        		"REF*CE*739456~\n" + 
//        		"LS*2700~\n" + 
//        		"LX*1~\n" + 
//        		"N1*75*APTC AMT~\n" + 
//        		"REF*9V*6.00~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*2~\n" + 
//        		"N1*75*PRE AMT 1~\n" + 
//        		"REF*9X*6~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*3~\n" + 
//        		"N1*75*PRE AMT TOT~\n" + 
//        		"REF*9X*6~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*4~\n" + 
//        		"N1*75*TOT RES AMT~\n" + 
//        		"REF*9V*5~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*5~\n" + 
//        		"N1*75*RATING AREA~\n" + 
//        		"REF*9X*R-FL003~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*6~\n" + 
//        		"N1*75*SOURCE EXCHANGE ID~\n" + 
//        		"REF*17*120355397~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*7~\n" + 
//        		"N1*75*REQUEST SUBMIT TIMESTAMP~\n" + 
//        		"REF*17*120355397~\n" + 
//        		"LE*2700~\n" + 
//        		"SE*52*0001~\n" + 
//        		"GE*1*0452400~\n" + 
//        		"IEA*1*415104017~~\n" + 
//        		"";
//        String memberJson;
//		try {
//			memberJson = ediToJson.asJson(memberSai2);
//			System.out.println("JSON OUTPUT "+memberJson);
//			convertJSONToJavaObject(memberJson);
//		} catch (IOException | SAXException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	}
	
public List<MyElthContract> convertEDIToJSONClaim(String filePath) {
		
		List<MyElthContract> contracts = new ArrayList<MyElthContract>();
//		String filePath = "/Users/prakashsingh/eclipse-workspace/scottmarchreleasesplit/testoracle.edi";
    	String content = "";
    	 
        try
        {
            content = new String ( Files.readAllBytes( Paths.get(filePath) ) );
//            System.out.println(content);
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
        ediTransactionSplitter = new EdiTransactionSplitter();
        
//        String DEFAULT_SPLITTING_RULES_claims = "\"document 837\\n    split INS\\n    keep all\\n\"";
////        DEFAULT_SPLITTING_RULES_claims = "\"document 837D\\n    split INS\\n    keep all\\n\"";
//        ediTransactionSplitter.setSplittingConfiguration(
//        new SplittingConfigurationBuilder().parse(new StringReader(DEFAULT_SPLITTING_RULES_claims)));
       
        try {
			result = ediTransactionSplitter.split(content);
		} catch (IOException | SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		EdiToJson ediToJson = new EdiToJson();
        ediToJson.setFormatting(true);
        ediToJson.setAnnotated(true);
        ediToJson.setSummarize(false);
        if (true) {
            ediToJson.setRecover();
        }
        
        int counter = 0;
        int subscriber = 0;
        int dependent = 0;
        int unknown = 0;
        int noaction = 0;
        int noRelationship = 0;
        Map<String, Integer> action = new HashMap<String, Integer>();
        Map<String, Integer> enrollmentAction = new HashMap<String, Integer>();
        Map<String, Integer> relationship = new HashMap<String, Integer>();
        Map<String, Integer> groupnumber = new HashMap<String, Integer>();
        Map<String, Integer> medicaidProviderId = new HashMap<String, Integer>();
        for (String strbyte : result) {
				counter++;
		try {
		ediToJson = new EdiToJson();
        ediToJson.setFormatting(true);
        ediToJson.setAnnotated(true);
        ediToJson.setSummarize(false);
		 if (true) {
	            ediToJson.setRecover();
	        }
        String memberJson = ediToJson.asJson(strbyte);
        System.out.println("memberJson::: " + counter);
        System.out.println(memberJson);
        System.out.println("memberJson JSON DONS::: "+counter);
        MyElthContract contract = convertJSONToJavaObject(memberJson);
        contracts.add(contract);
        
        
        if(contract != null) {
//        	if(contract.getSubscriber() != null) {
//        		
//        	} else if (contract.getDependents() != null && !contract.getDependents().isEmpty()) {
//        		
//        	} else {
//        		
//        	}
//        	if(contract.getEdiMember().getSubscriberOrDependent() != null && contract.getEdiMember().getSubscriberOrDependent().contains("S")){
//        		subscriber++;
//        	} else if(contract.getEdiMember().getSubscriberOrDependent() != null && 
//        			contract.getEdiMember().getSubscriberOrDependent().contains("D")){
//        		dependent++;
//        	} else {
//        		unknown++;
//        	}
//        	if(contract.getActionCode() != null) {
//        		if(action.containsKey(contract.getActionCode())) {
//        			action.put(contract.getActionCode(), action.get(contract.getActionCode())+1);
//        		} else {
//        			action.put(contract.getActionCode(), 1);
//        		}
//        	} 
//        	
//        	if(contract.getEdiMember().getEnrollmentAction() != null) {
//        		if(enrollmentAction.containsKey(contract.getEdiMember().getEnrollmentAction())) {
//        			enrollmentAction.put(contract.getEdiMember().getEnrollmentAction(), enrollmentAction.get(contract.getEdiMember().getEnrollmentAction())+1);
//        		} else {
//        			enrollmentAction.put(contract.getEdiMember().getEnrollmentAction(), 1);
//        		}
//        	} else {
//        		noaction++;
//        	}
//        	if(contract.getMedicaidProviderID() != null) {
//        		if(medicaidProviderId.containsKey(contract.getMedicaidProviderID())) {
//        			medicaidProviderId.put(contract.getMedicaidProviderID(), medicaidProviderId.get(contract.getMedicaidProviderID())+1);
//        		} else {
//        			medicaidProviderId.put(contract.getMedicaidProviderID(), 1);
//        		}
//        	}
//        	
//        	if(contract.getGroupNumber() != null) {
//        		if(groupnumber.containsKey(contract.getGroupNumber())) {
//        			groupnumber.put(contract.getGroupNumber(), groupnumber.get(contract.getGroupNumber())+1);
//        		} else {
//        			groupnumber.put(contract.getGroupNumber(), 1);
//        		}
//        	}
//        	
//        	
//        	if(contract.getEdiMember().getRelationshipCode() != null) {
//        		if(relationship.containsKey(contract.getEdiMember().getRelationshipCode())) {
//        			relationship.put(contract.getEdiMember().getRelationshipCode(), relationship.get(contract.getEdiMember().getRelationshipCode())+1);
//        		} else {
//        			relationship.put(contract.getEdiMember().getRelationshipCode(), 1);
//        		}
//        	} else {
//        		noRelationship++;
//        	}
        }
        System.out.println("converted from JSON to Java Object:: ");
        }catch(Throwable e) {
        	System.out.println(e);
        	e.printStackTrace();
        }
        }
    	 System.out.println("all done :: "+counter +" | "+subscriber + " | "+dependent + " | "+unknown + " | "+noaction + " | "+noRelationship);
    	 System.out.println(action);
    	 System.out.println(enrollmentAction);
    	 System.out.println(relationship);
    	 System.out.println(groupnumber);
    	 System.out.println(medicaidProviderId);
        System.out.println("Total no of edis: "+result.size());
        
        return contracts;
        
        /**
         * 
         * 1. Split EDI to smaller EDI
         * 2. convert edi to json 
         * 3. convert json to java
         * 4. post java object to mongodb
         * 5. post json to OHI
         * 6. extract a roster
         * 7. 
         * 
         * 
         * 
         */
        
//        String strbyte = "ISA*03*          *00*          *ZZ*DDCA78795      *ZZ*942411167      *190419*0932*^*00501*000000397*0*P*:~GS*BE*680393304*161264154*20190419*0932*397*X*005010X220A1~ST*834*0397*005010X220A1~BGN*00*20190419441*20190419*093241*PT***4~REF*38*CA78795~DTP*007*D8*20190419~N1*P5*WESTERN HEALTH ADVANTAGE*FI*68-0393304~N1*IN*Metlife*FI*161264154~INS*N*19*030**A~REF*0F*WH000138754~REF*1L*CA1861400001~REF*DX*103792~REF*23*WH000138754002~NM1*IL*1*KELLY*COLE*M***34*607061375~PER*IP**TE*9169921218~N3*1040 SKI PARK CT~N4*RIO LINDA*CA*95673~DMG*D8*19980429*M~HD*030**DEN~DTP*348*D8*20180101~REF*CE*18614WH0000001~SE*20*0397~GE*1*397~IEA*1*000000397~";
      	 
        
//        List<EDISyntaxException> ediExceptions = ediToJson.getEdiSyntaxExceptions();
        
//        String memberINS = "ISA*03*          *00*          *ZZ*DDCA78795      *ZZ*942411167      *190419*0932*^*00501*000000397*0*P*:"
//        		+ "~GS*BE*680393304*161264154*20190419*0932*397*X*005010X220A1"
//        		+ "~ST*834*0397*005010X220A1"
//        		+ "~BGN*00*20190419441*20190419*093241*PT***4"
//        		+ "~REF*38*CA78795~DTP*007*D8*20190419~N1*P5*WESTERN HEALTH ADVANTAGE*FI*68-0393304~N1*IN*Metlife*FI*161264154"
//        		+ "~INS*Y*18*030**A***FT~REF*0F*WH000008352"
//        		+ "~REF*1L*CA7879500001~REF*DX*021474~REF*23*WH000008352000~NM1*IL*1*HUCKINS*JEFFREY*A***34*547745167~PER*IP**TE*5306815785~N3*36245 COUNTY ROAD 24"
//        		+ "~N4*WOODLAND*CA*95695~DMG*D8*19610915*M~HD*030**DEN~DTP*348*D8*20170101~REF*CE*78795WH0000001~SE*20*0397~GE*1*397~IEA*1*000000397~";
//        
//        String memberSai = "ISA*00*          *00*          *ZZ*ORDHS          *ZZ*MB888880       *130312*0206*!*00501*000000201*0*P*:~\n" + 
//        		"\n" + 
//        		"GS*BE*ORDHS*MB888880*20130312*020630*301*X*005010X220A1~\n" + 
//        		"\n" + 
//        		"ST*834*40001*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40001~\n" + 
//        		"\n" + 
//        		"ST*834*40002*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40002~\n" + 
//        		"GE*2*301~\n" + 
//        		"\n" + 
//        		"GS*BE*ORDHS*MB888880*20130312*020630*302*X*005010X220A1~\n" + 
//        		"\n" + 
//        		"ST*834*40003*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40003~\n" + 
//        		"\n" + 
//        		"ST*834*40004*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40004~\n" + 
//        		"GE*2*302~\n" + 
//        		"IEA*2*000000201~\n" + 
//        		"\n" + 
//        		"ISA*00*          *00*          *ZZ*ORDHS          *ZZ*MB888880       *130312*0206*!*00501*000000202*0*P*:~\n" + 
//        		"\n" + 
//        		"GS*BE*ORDHS*MB888880*20130312*020630*303*X*005010X220A1~\n" + 
//        		"\n" + 
//        		"ST*834*40005*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40005~\n" + 
//        		"\n" + 
//        		"ST*834*40006*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40006~\n" + 
//        		"GE*2*303~\n" + 
//        		"\n" + 
//        		"GS*BE*ORDHS*MB888880*20130312*020630*304*X*005010X220A1~\n" + 
//        		"\n" + 
//        		"ST*834*40007*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40007~\n" + 
//        		"\n" + 
//        		"ST*834*40008*005010X220A1~\n" + 
//        		"BGN*00*0158420020130310001*20130312*0206*PT***2~\n" + 
//        		"SE*3*40008~\n" + 
//        		"GE*2*304~\n" + 
//        		"IEA*2*000000202~\n" + 
//        		"\n" + 
//        		"";
//        String memberSai2 = "ISA*00*          *00*          *ZZ*CMSFFM         *ZZ*510228088      *180503*1630*^*00501*415104017*1*T*:~\n" + 
//        		"GS*BE*DE0*26018DE0010004*20190913*1630*0452400*X*005010X220A1~\n" + 
//        		"ST*834*0001*005010X220A1~\n" + 
//        		"BGN*00*0001*20190913*111713*ET***2~\n" + 
//        		"QTY*TO*1~\n" + 
//        		"QTY*DT*0~\n" + 
//        		"N1*P5*sriram Kola*FI*384794082~\n" + 
//        		"N1*IN*DELTA DENTAL INSURANCE COMPANY*FI*510228088~\n" + 
//        		"INS*Y*18*021*EC*A***AC~\n" + 
//        		"REF*0F*120355397~\n" + 
//        		"REF*17*120355397~\n" + 
//        		"REF*6O*DE00077199998~\n" + 
//        		"DTP*356*D8*20210101~\n" + 
//        		"NM1*IL*1*BISHOP*ANTHONY****34*875439~\n" + 
//        		"PER*IP**TE*2006649555*AP*1005545675*EM*8908662231~\n" + 
//        		"N3*1 Pioneer Street~\n" + 
//        		"N4*Bellevue*WA*980040000**CY*10001~\n" + 
//        		"DMG*D8*19330706*M*M*:RET:2106-3~\n" + 
//        		"HLH*T~\n" + 
//        		"HD*021**DEN~\n" + 
//        		"DTP*348*D8*20210101~\n" + 
//        		"DTP*349*D8*20211231~\n" + 
//        		"REF*1L*739456~\n" + 
//        		"REF*CE*739456~\n" + 
//        		"LS*2700~\n" + 
//        		"LX*1~\n" + 
//        		"N1*75*APTC AMT~\n" + 
//        		"REF*9V*6.00~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*2~\n" + 
//        		"N1*75*PRE AMT 1~\n" + 
//        		"REF*9X*6~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*3~\n" + 
//        		"N1*75*PRE AMT TOT~\n" + 
//        		"REF*9X*6~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*4~\n" + 
//        		"N1*75*TOT RES AMT~\n" + 
//        		"REF*9V*5~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*5~\n" + 
//        		"N1*75*RATING AREA~\n" + 
//        		"REF*9X*R-FL003~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*6~\n" + 
//        		"N1*75*SOURCE EXCHANGE ID~\n" + 
//        		"REF*17*120355397~\n" + 
//        		"DTP*007*D8*20210101~\n" + 
//        		"LX*7~\n" + 
//        		"N1*75*REQUEST SUBMIT TIMESTAMP~\n" + 
//        		"REF*17*120355397~\n" + 
//        		"LE*2700~\n" + 
//        		"SE*52*0001~\n" + 
//        		"GE*1*0452400~\n" + 
//        		"IEA*1*415104017~~\n" + 
//        		"";
//        String memberJson;
//		try {
//			memberJson = ediToJson.asJson(memberSai2);
//			System.out.println("JSON OUTPUT "+memberJson);
//			convertJSONToJavaObject(memberJson);
//		} catch (IOException | SAXException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
	}
	
	
	public String convertMyElthContractToJson(MyElthContract contract) {
		try {
			ObjectMapper mapper = new ObjectMapper();
            // Java objects to JSON string - compact-print
            String jsonString = mapper.writeValueAsString(contract);

            System.out.println(jsonString);

            // Java objects to JSON string - pretty-print
            String jsonInString2 = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(contract);

            System.out.println(jsonInString2);
            return jsonInString2;

        } catch (IOException e) {
            e.printStackTrace();
        }
		System.out.println("myelth Contract::: ");
		return null;
	}
	
	public MyElthContract convertJSONToJavaObject(String memberJson) {
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

	    //JSON file to Java object
	    try {
			Root obj = mapper.readValue(memberJson, Root.class);
			System.out.println(obj);
			EDIRequest ediRequest = transform(obj);
			System.out.println(ediRequest);
		
			MyElthContract contract = parseEDIRequestToMyContract(ediRequest, obj, memberJson);
			return contract;
	    } catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    return null;
	}
	
	public MyElthContract parseEDIRequestToMyContract (EDIRequest request, Root root, String memberJson) {
		MyElthContract contract = new MyElthContract();
		Member mem = request.getMembers().get(0);
		contract.setContractId(mem.getSubscriberIdentifier()); 
		Map<String, String> alternateIds =  new HashMap<String, String>();
		alternateIds.put("SUPP", mem.getSubscriberIdentifier());
//		AO – Active Oversees
//		AU – Active Military
//		FT – Full Time
//		L1 – Leave of Absence
//		PT – Part Time
//		RT – Retired
//		TE – Terminated
		contract.setEmployementStatus(mem.getIns_08());
		contract.setSubscriberId(mem.getSubscriberIdentifier());
		contract.setDivisionNumber(null);
		contract.setGroupName(null);
		contract.setGroupNumber(request.getGroupNumber());
		contract.setResponsibleParty(null);
		contract.setActionCode(request.getActionCode());
		contract.setSenderId(request.getSenderId());
		contract.setReceiverId(request.getReceiverId());
		contract.setControlNumber(request.getControlNumber());
		contract.setSponsorName(request.getSponsorName());
		contract.setMedicaidProviderID(request.getMasterPolicyNumber());
		
		Enrollee ediMember = new Enrollee();
		//18 - self
//		18 - Self (subscriber)
//		01 - Spouse
//		19 - Child
//		53 - Life Partner
//		05 - Grandchild
//		07 - Nephew/Niece
//		09 - Adopted Child
//		10 - Foster Child
//		11 - Son/Daughter In law
//		15 - Ward
//		17 - Stepchild
//		23 - Sponsored Dependant
//		24 - Dependant of Dependant
//		25 - Ex Spouse
//		38 - Collateral Dependant
		
		
		ediMember.setRelationshipCode(mem.getRelationshipCode());
		if(mem.getRelationshipCode() != null && mem.getRelationshipCode().contains("18")) {
			ediMember.setRelationshipCodeDesc("Self");
		} else if(mem.getRelationshipCode() != null && mem.getRelationshipCode().contains("01")) {
			ediMember.setRelationshipCodeDesc("Spouse");
		}else if(mem.getRelationshipCode() != null && mem.getRelationshipCode().contains("19")) {
			ediMember.setRelationshipCodeDesc("Child");
		}else if(mem.getRelationshipCode() != null && mem.getRelationshipCode().contains("11")) {
			ediMember.setRelationshipCodeDesc("Son");
		}else if(mem.getRelationshipCode() != null && mem.getRelationshipCode().contains("24")) {
			ediMember.setRelationshipCodeDesc("Dependent");
		}
		ediMember.setEnrollmentAction(mem.getIns_03());
		ediMember.setSubscriberOrDependent(mem.getSubscriberOrDependent());
		ediMember.setSubscriberId(mem.getSubscriberIdentifier());
		ediMember.setCaseId(mem.getCaseNumber());
		alternateIds.put("CASE", mem.getCaseNumber());
		ediMember.setAlternateId(mem.getAlternateId());
		alternateIds.put("ALT", mem.getAlternateId());
		
//		001 = Change
//				021 = Addition
//				024 = Cancellation or Termination
//				 030 = Audit or Compare
		
		
		contract.setMaintenanceCode(mem.getMaintainenceTypeCode());  //001 - 
		if(mem.getMaintainenceTypeCode() != null && mem.getMaintainenceTypeCode().contains("001")) {
			contract.setMaintenanceCodeDesc("Change");
		}else if(mem.getMaintainenceTypeCode() != null && mem.getMaintainenceTypeCode().contains("021")) {
			contract.setMaintenanceCodeDesc("Addition");
		}else if(mem.getMaintainenceTypeCode() != null && mem.getMaintainenceTypeCode().contains("024")) {
			contract.setMaintenanceCodeDesc("Termination");
		}else if(mem.getMaintainenceTypeCode() != null && mem.getMaintainenceTypeCode().contains("030")) {
			contract.setMaintenanceCodeDesc("Audit");
		}
		Person person = new Person();
		person.setDateOfBirth(convertStringToDate(mem.getDateOfBirth()));
		person.setEmailId(mem.getEmailId());
		person.setEthnicity(mem.getEthnicity());
		person.setFirstName(mem.getFirstName());
		person.setGenderCode(mem.getGenderCode());
		person.setLanguage(mem.getLanguage());
		person.setLastName(mem.getLastName());
		person.setMaritalStatusCode(mem.getMaritalStatusCode());
		person.setMiddleName(mem.getMiddleName());
		person.setPhoneNumber(mem.getPhoneNumber());
		person.setSsn(mem.getSsn());
		alternateIds.put("SSN", mem.getSsn());

		List<Address> addresses = new ArrayList<Address>();
		Address address = new Address();
		address.setAddressLine1(mem.getAddressLine1());
		address.setAddressLine2(mem.getAddressLine2());
		address.setCity(mem.getCity());
		address.setState(mem.getState());
		address.setZipCode(mem.getZipCode());
		address.setCountry(mem.getCountry());
		
		addresses.add(address);
		person.setAddresses(addresses);
		ediMember.setPerson(person);
		
		List<Eligibility> eligibilities = new ArrayList<Eligibility>();
		Eligibility elig = new Eligibility();
		elig.setContractTypeCode(null);
		elig.setDivisionNumber(null);
		
//		
//		300 = Enrollment Signature Date. The re- determination date contains any re- determination of eligibility for assistance (i.e. food stamps, 
//				Temporary Assistance for Needy Families (TANF), and Medicaid). 
//				Re-determination is a process conducted by the CDJFS to review a consumer's eligibility. 
//				The re- determination is scheduled based on federally mandated timelines for each type of assistance or is scheduled when any type of change 
//				in the household financial status occurs.473 = Medicaid Begin 
//				474 = Medicaid EndThe qualifiers 473 and 474 are used for reporting the member's eligibility effective date and end date associated with that specific PMP. 
//				Therefore, the eligibility effective and end dates apply to that specific PMP’s membership/coverage only.
//			
		if(mem.getMedicaidTypeCode() != null && mem.getMedicaidTypeCode().contains("473")) {
			elig.setEffectiveDate(convertStringToDate(mem.getMedicaidBeginDate()));
			elig.setEndDate(convertStringToDate(mem.getMedicaidEndDate()));
			contract.setCoverageReviewDate(convertStringToDate(mem.getEnrollmentSignatureDate()));
		} else if(mem.getMedicaidTypeCode() != null && mem.getMedicaidTypeCode().contains("356")) {
			elig.setEffectiveDate(convertStringToDate(mem.getMedicaidBeginDate()));
			elig.setEndDate(convertStringToDate(mem.getMedicaidEndDate()));
			contract.setCoverageReviewDate(convertStringToDate(mem.getEnrollmentSignatureDate()));
		} else  {
		elig.setEffectiveDate(convertStringToDate(mem.getEligibilityEffectiveDate()));
		elig.setEndDate(convertStringToDate(mem.getEligibilityEndDate()));
		}
		if(mem.getDtp_01_value() != null && mem.getDtp_01_value().contains("348")) {
			contract.setBenefitBeginDate(convertStringToDate(mem.getBenefitBeginDate()));
			contract.setBenefitEndDate(convertStringToDate(mem.getBenefitEndDate()));
			contract.setCoverageReviewDate(convertStringToDate(mem.getEnrollmentSignatureDate()));
		} 
		
		elig.setGroupNumber(mem.getGroupPolicyNumber());
		elig.setNetworkId(null);
		elig.setPlan(null);
		elig.setPlanState(null);
		elig.setProduct(mem.getProduct());
		elig.setProviderId(null);
		
		eligibilities.add(elig);
		ediMember.setEligibilities(eligibilities);
		
		if(mem.getRelationshipCode() != null && mem.getRelationshipCode().contains("18")) {
			ediMember.setRelationshipCodeDesc("Self");
			contract.setSubscriber(ediMember);
		} else {
			if(contract.getDependents() == null) {
				contract.setDependents(new ArrayList<Enrollee>());
			}
			contract.getDependents().add(ediMember);
		}
		
		contract.setAlternateIds(alternateIds);
		return contract;
		
	}
	private Date convertStringToDate(String stdDate) {
		if(stdDate == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date convertedCurrentDate;
		try {
			convertedCurrentDate = sdf.parse(stdDate);
			return convertedCurrentDate;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static EDIRequest transform(Root root) {
		 EDIRequest ediRequest = new EDIRequest();
		 if(root != null) {
			 if(root != null && root.getInterchanges().size() > 0) {
				 List<Interchanx> interchanges = root.getInterchanges();
				 for (Interchanx interchanx : interchanges) {
					ediRequest.setReceiverId(interchanx.getiSA_08_ReceiverId());
					ediRequest.setSenderId(interchanx.getiSA_06_SenderId());
					ediRequest.setReceivedDate(interchanx.getiSA_09_Date());
					ediRequest.setReceivedTime(interchanx.getiSA_10_Time());
					ediRequest.setControlNumber(interchanx.getiSA_13_InterchangeControlNumber());
					List<FunctionalGroup> functionalGroups = interchanx.getFunctional_groups();
					for (FunctionalGroup fg : functionalGroups) {
						System.out.println(fg.getgS_06_GroupControlNumber());
						System.out.println(fg.getgS_08_Version());
						ediRequest.setAppReceiverCode(fg.getgS_03_ApplicationReceiverCode());
						ediRequest.setAppSenderCode(fg.getgS_02_ApplicationSenderCode());
						List<Transaction> transactions = fg.getTransactions();
						for (Transaction trans : transactions) {
							System.out.println(trans.getsT_01_TransactionSetIdentifierCode());
							System.out.println(trans.getsT_03_ImplementationConventionReference());
							List<Segment> segments = trans.getSegments();
							List<Member> members = new ArrayList<Member>();
							for (Segment segment : segments) {
								
								if(segment.getbGN_02() != null) {
								ediRequest.setReferenceIdentifier(segment.getbGN_02());
								ediRequest.setTimeCode(segment.getbGN_04());
								ediRequest.setActionCode(segment.getbGN_08());
//								ediRequest.setActionCodeDesc(segment.getbGN_08_code_4());
								ediRequest.setMasterPolicyNumber(segment.getrEF_02());
								
								}
								if(segment.getrEF_01() != null && segment.getrEF_01().contains("38")) {
//									ediRequest.setGroupNumber(segment.getrEF_02());
									ediRequest.setMasterPolicyNumber(segment.getrEF_02());
								}
								if(segment.getdTP_02() != null && segment.getdTP_02().contains("D8")) {
									ediRequest.setFileRecieveDate(segment.getdTP_03());
								}
								
								List<N11000Loop> n11000s = segment.get_1000_loop();
								
								if(n11000s != null) {
								for (N11000Loop n11000A : n11000s) {
									if(n11000A.getN1_01() != null && n11000A.getN1_01().contains("P5")) {
									ediRequest.setSponsorName(n11000A.getN1_02());
									if(n11000A.getN1_03() != null && n11000A.getN1_03().contains("FI")) {
									ediRequest.setSponsorIdentifier(n11000A.getN1_04());
									}
									}
									
									if(n11000A.getN1_01() != null && n11000A.getN1_01().contains("IN")) {
										ediRequest.setPayer(n11000A.getN1_02());
										if(n11000A.getN1_03() != null && n11000A.getN1_03().contains("FI")) {
										ediRequest.setPayerIdentifier(n11000A.getN1_04());
										}
										}
									
								}
								}
//								List<N11000ALoop> n11000As = segment.getN11000ALoop();
//								
//								if(n11000As != null) {
//								for (N11000ALoop n11000A : n11000As) {
//									ediRequest.setSponsorName(n11000A.getN1_02());
//								}
//								}
//								
//								List<N11000BLoop> n11000Bs = segment.getN11000BLoop();
//								if(n11000Bs != null) {
//								for (N11000BLoop n11000B : n11000Bs) {
//									ediRequest.setPayer(n11000B.getN1_02());
//								}
//								}
								
								
								List<INS2000Loop> ins2000s = segment.get_2000_loop();
								if(ins2000s != null) {
								
								Member mem = new Member();
								for (INS2000Loop ins2000 : ins2000s) {
									
									
									if(ins2000.getiNS() != null && ins2000.getiNS().contains("Insured")) {
										mem.setIns_01(ins2000.getiNS_01());
										if(ins2000.getiNS_01().contains("N")) {
										mem.setSubscriberOrDependent("D");
										} else {
											mem.setIns_01_code(ins2000.getiNS_01_code_Y());
											mem.setSubscriberOrDependent("S");
										}
										mem.setIns_02(ins2000.getiNS_02());
										mem.setRelationshipCode(ins2000.getiNS_02());
										mem.setMaintainenceTypeCode(ins2000.getiNS_03());
										mem.setIns_03(ins2000.getiNS_03());
										mem.setIns_08(ins2000.getiNS_08());
									}
									if(mem.getReferences() == null) {
									Map<String, String> references = new HashMap<String, String>();
									if(ins2000.getrEF() != null && ins2000.getrEF().contains("Reference")) {
										references.put(ins2000.getrEF_01(), ins2000.getrEF_02());
										mem.setReferences(references);
										if(ins2000.getrEF_01() != null) {
										if(ins2000.getrEF_01().contains("0F")) {
											mem.setSubscriberIdentifier(ins2000.getrEF_02());
										}
										if(ins2000.getrEF_01().contains("17")) {
											mem.setAlternateId(ins2000.getrEF_02());
										}
										if(ins2000.getrEF_01().contains("3H")) {
											mem.setCaseNumber(ins2000.getrEF_02());
										}
										}
									}
									
									} else {
										if(ins2000.getrEF() != null && ins2000.getrEF().contains("Reference")) {
										mem.getReferences().put(ins2000.getrEF_01(), ins2000.getrEF_02());
										if(ins2000.getrEF_01() != null) {
										if(ins2000.getrEF_01().contains("OF")) {
											mem.setSubscriberIdentifier(ins2000.getrEF_02());
										}
										if(ins2000.getrEF_01().contains("17")) {
											mem.setAlternateId(ins2000.getrEF_02());
										}
										if(ins2000.getrEF_01().contains("3H")) {
											mem.setCaseNumber(ins2000.getrEF_02());
										}
										}}
									}
									if(ins2000.getdTP_01() != null && ins2000.getdTP_01().contains("300")) {
										mem.setEnrollmentDateTypeCode(ins2000.getdTP_01());
										mem.setEnrollmentSignatureDate(ins2000.getdTP_03());
									}
									if(ins2000.getdTP_01() != null && ins2000.getdTP_01().contains("473")) {
										mem.setMedicaidTypeCode(ins2000.getdTP_01());
										mem.setMedicaidBeginDate(ins2000.getdTP_03());
									}
									if(ins2000.getdTP_01() != null && ins2000.getdTP_01().contains("474")) {
										mem.setMedicaidEndDate(ins2000.getdTP_03());
									}
									if(ins2000.getdTP_01() != null && ins2000.getdTP_01().contains("356")) {
										mem.setMedicaidTypeCode(ins2000.getdTP_01());
										mem.setMedicaidBeginDate(ins2000.getdTP_03());
									}
									if(ins2000.getdTP_01() != null && ins2000.getdTP_01().contains("357")) {
										mem.setMedicaidEndDate(ins2000.getdTP_03());
									}
									
									
									List<NM12100Loop> nm2100As = ins2000.get_2100_loop();
									if(nm2100As != null) {
									for (NM12100Loop nm2100A : nm2100As) {
										if(nm2100A.getnM1_03() != null) {
											mem.setLastName(nm2100A.getnM1_03());
										}
										if(nm2100A.getnM1_04() != null) {
											mem.setFirstName(nm2100A.getnM1_04());
										}
										if(nm2100A.getnM1_05() != null) {
											mem.setMiddleName(nm2100A.getnM1_05());
										}
										if(nm2100A.getnM1_08() != null && nm2100A.getnM1_08().contains("34")) {
											mem.setSsn(nm2100A.getnM1_09());
										}
										if(nm2100A.getpER_03() != null) {
											mem.setPer_03(nm2100A.getpER_03());
											mem.setCommunicationType(nm2100A.getpER_03());
											mem.setPhoneNumber(nm2100A.getpER_04());
										}
										if(nm2100A.getN3_01() != null) {
											mem.setAddressLine1(nm2100A.getN3_01());
										}
										if(nm2100A.getN4_01() != null) {
											mem.setCity(nm2100A.getN4_01());
										}
										if(nm2100A.getN4_02() != null) {
											mem.setState(nm2100A.getN4_02());
										}
										if(nm2100A.getN4_03() != null) {
											mem.setZipCode(nm2100A.getN4_03());
										}
										if(nm2100A.getdMG_01() != null) {
											mem.setDmg_01_value(nm2100A.getdMG_01());
										}
										if(nm2100A.getdMG_02() != null) {
											mem.setDateOfBirth(nm2100A.getdMG_02());
										}
										if(nm2100A.getdMG_03() != null) {
											mem.setGenderCode(nm2100A.getdMG_03());
										}
										if(nm2100A.getlUI_01() != null && nm2100A.getlUI_01().contains("LE")) {
											mem.setLanguage(nm2100A.getlUI_02());
										}
										List<HD2300Loop> hd2300s = nm2100A.get_2300_loop();
										if(hd2300s != null) {
										for (HD2300Loop hd2300 : hd2300s) {
											if(hd2300.gethD_01() != null) {
												mem.setHd_01_value(hd2300.gethD_01());
												mem.setHd_03_value(hd2300.gethD_03());
												mem.setProduct(hd2300.gethD_03());
											}
											if(hd2300.getdTP_01() != null && hd2300.getdTP_01().contains("348")) {
												mem.setDtp_01_value(hd2300.getdTP_01());
												mem.setDtp_02_value(hd2300.getdTP_02());
												mem.setBenefitBeginDate(hd2300.getdTP_03());
											}
											if(hd2300.getdTP_01() != null && hd2300.getdTP_01().contains("349")) {
												mem.setDtp_01_value(hd2300.getdTP_01());
												mem.setDtp_02_value(hd2300.getdTP_02());
												mem.setBenefitEndDate(hd2300.getdTP_03());
											}
											if(hd2300.getrEF_01() != null) {
												mem.setHd_ref_01_value(hd2300.getrEF_01());
												mem.setHd_ref_02_value(hd2300.getrEF_02());
												ediRequest.setGroupNumber(hd2300.getrEF_02());
											}
											
										}}
									}
									
									}
									
								}
								members.add(mem);
								ediRequest.setMembers(members);
								}
							}
						}
						
					}
				}
			 }
		 }
		 
		 return ediRequest;
	    	
	 }
}
