package com.myelth.app;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.myelth.app.model.EDIRequest;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MainControllerTest {
	// bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testEDITransactions() throws Exception {
    	
    	String json = "{\n" + 
    			"  \"interchanges\": [\n" + 
    			"    {\n" + 
    			"      \"ISA_01_AuthorizationQualifier\": \"01\",\n" + 
    			"      \"ISA_02_AuthorizationInformation\": \"0000000000\",\n" + 
    			"      \"ISA_03_SecurityQualifier\": \"01\",\n" + 
    			"      \"ISA_04_SecurityInformation\": \"0000000000\",\n" + 
    			"      \"ISA_05_SenderQualifier\": \"ZZ\",\n" + 
    			"      \"ISA_06_SenderId\": \"ABCDEFGHIJKLMNO\",\n" + 
    			"      \"ISA_07_ReceiverQualifier\": \"ZZ\",\n" + 
    			"      \"ISA_08_ReceiverId\": \"123456789012345\",\n" + 
    			"      \"ISA_09_Date\": \"101127\",\n" + 
    			"      \"ISA_10_Time\": \"1719\",\n" + 
    			"      \"ISA_11_StandardsId\": \"U\",\n" + 
    			"      \"ISA_12_Version\": \"00400\",\n" + 
    			"      \"ISA_13_InterchangeControlNumber\": \"000003438\",\n" + 
    			"      \"ISA_14_AcknowledgmentRequested\": \"0\",\n" + 
    			"      \"ISA_15_TestIndicator\": \"P\",\n" + 
    			"      \"functional_groups\": [\n" + 
    			"        {\n" + 
    			"          \"GS_01_FunctionalIdentifierCode\": \"PO\",\n" + 
    			"          \"GS_02_ApplicationSenderCode\": \"4405197800\",\n" + 
    			"          \"GS_03_ApplicationReceiverCode\": \"999999999\",\n" + 
    			"          \"GS_04_Date\": \"20101127\",\n" + 
    			"          \"GS_05_Time\": \"1719\",\n" + 
    			"          \"GS_06_GroupControlNumber\": \"1421\",\n" + 
    			"          \"GS_07_ResponsibleAgencyCode\": \"X\",\n" + 
    			"          \"GS_08_Version\": \"004010VICS\",\n" + 
    			"          \"transactions\": [\n" + 
    			"            {\n" + 
    			"              \"ST_01_TransactionSetIdentifierCode\": \"834\",\n" + 
    			"              \"ST_02_TransactionSetControlNumber\": \"0179\",\n" + 
    			"              \"segments\": [\n" + 
    			"                {\n" + 
    			"                  \"BGN_01\": \"00\",\n" + 
    			"                  \"BGN_02\": \"1\",\n" + 
    			"                  \"BGN_03\": \"20050315\",\n" + 
    			"                  \"BGN_04\": \"110650\",\n" + 
    			"                  \"BGN_08\": \"4\"\n" + 
    			"                },\n" + 
    			"                {\n" + 
    			"                  \"REF_01\": \"38\",\n" + 
    			"                  \"REF_02\": \"SAMPLE_POLICY_NUMBER\"\n" + 
    			"                },\n" + 
    			"                {\n" + 
    			"                  \"DTP_01\": \"303\",\n" + 
    			"                  \"DTP_02\": \"D8\",\n" + 
    			"                  \"DTP_03\": \"20080321\"\n" + 
    			"                },\n" + 
    			"                {\n" + 
    			"                  \"N1-1000A_loop\": [\n" + 
    			"                    {\n" + 
    			"                      \"N1_01\": \"P5\",\n" + 
    			"                      \"N1_02\": \"COMPAN_NAME\",\n" + 
    			"                      \"N1_03\": \"FI\",\n" + 
    			"                      \"N1_04\": \"000000000\"\n" + 
    			"                    }\n" + 
    			"                  ]\n" + 
    			"                },\n" + 
    			"                {\n" + 
    			"                  \"INS-2000_loop\": [\n" + 
    			"                    {\n" + 
    			"                      \"INS_01\": \"Y\",\n" + 
    			"                      \"INS_02\": \"18\",\n" + 
    			"                      \"INS_03\": \"030\",\n" + 
    			"                      \"INS_04\": \"20\",\n" + 
    			"                      \"INS_05\": \"A\"\n" + 
    			"                    },\n" + 
    			"                    {\n" + 
    			"                      \"REF_01\": \"0F\",\n" + 
    			"                      \"REF_02\": \"SUBSCRIBER_NUMBER\"\n" + 
    			"                    },\n" + 
    			"                    {\n" + 
    			"                      \"NM1-2100A_loop\": [\n" + 
    			"                        {\n" + 
    			"                          \"NM1_01\": \"IL\",\n" + 
    			"                          \"NM1_02\": \"1\",\n" + 
    			"                          \"NM1_03\": \"JOHN DOE\",\n" + 
    			"                          \"NM1_04\": \"R\",\n" + 
    			"                          \"NM1_07\": \"34\",\n" + 
    			"                          \"NM1_08\": \"1\",\n" + 
    			"                          \"NM1_09\": \"0000000\"\n" + 
    			"                        },\n" + 
    			"                        {\n" + 
    			"                          \"PER_01\": \"IP\",\n" + 
    			"                          \"PER_03\": \"HP\",\n" + 
    			"                          \"PER_04\": \"2138051111\"\n" + 
    			"                        },\n" + 
    			"                        {\n" + 
    			"                          \"N3_01\": \"123 SAMPLE RD\"\n" + 
    			"                        },\n" + 
    			"                        {\n" + 
    			"                          \"N4_01\": \"CITY\",\n" + 
    			"                          \"N4_02\": \"ST\",\n" + 
    			"                          \"N4_03\": \"12345\"\n" + 
    			"                        },\n" + 
    			"                        {\n" + 
    			"                          \"DMG_01\": \"D8\",\n" + 
    			"                          \"DMG_02\": \"19690101\",\n" + 
    			"                          \"DMG_03\": \"F\"\n" + 
    			"                        }\n" + 
    			"                      ]\n" + 
    			"                    },\n" + 
    			"                    {\n" + 
    			"                      \"HD-2300_loop\": [\n" + 
    			"                        {\n" + 
    			"                          \"HD_01\": \"030\"\n" + 
    			"                        },\n" + 
    			"                        {\n" + 
    			"                          \"DTP_01\": \"348\",\n" + 
    			"                          \"DTP_02\": \"D8\",\n" + 
    			"                          \"DTP_03\": \"20080101\"\n" + 
    			"                        },\n" + 
    			"                        {\n" + 
    			"                          \"REF_01\": \"1L\",\n" + 
    			"                          \"REF_02\": \"INDIV_POLICY_NO\"\n" + 
    			"                        }\n" + 
    			"                      ]\n" + 
    			"                    }\n" + 
    			"                  ]\n" + 
    			"                }\n" + 
    			"              ]\n" + 
    			"            }\n" + 
    			"          ]\n" + 
    			"        }\n" + 
    			"      ]\n" + 
    			"    }\n" + 
    			"  ]\n" + 
    			"}";
    	
    	JSONObject requestJson = new JSONObject(json);
    	
    	JSONArray interChanges = requestJson.getJSONArray("interchanges");
    	
//    	for(int i=0; i<interChanges.length(); i++) {
//    	   JSONObject dataObj = interChanges.getJSONObject(i);
//    	   String id = dataObj.getString("ISA_01_AuthorizationQualifier");
//    	   JSONArray functionGroups = dataObj.getJSONArray("functional_groups");
//    	   for(int j=0; j<functionGroups.length(); j++) {
//    		   JSONObject dataObjf = functionGroups.getJSONObject(j);
//    		   System.out.println(dataObjf.getString("GS_01_FunctionalIdentifierCode"));
//    		   System.out.println(dataObjf.getString("GS_02_ApplicationSenderCode"));
//    		   
//    		   JSONArray transactions = dataObj.getJSONArray("transactions");
//				
//        	   for(int k=0; k<transactions.length(); k++) {
//        		   
//        	   }
//    	   }
//    	   //Similarly you can extract for other fields.
//    	}
    	
    	
    	if (requestJson.has("interchanges")) {
			System.out.println(requestJson.getString("interchanges"));
		}

        ResponseEntity<String> response = restTemplate.getForEntity(
            new URL("http://localhost:"+ port + "/").toString(), String.class);
//        assertEquals("Hello Controller", response.getBody());
        
        String uri = "http://localhost:8888//blockchain-api/v1/queue/";
//          String uri = "http://34.232.245.38:8888/blockchain-api/v1/queue";
        
//        final String baseUrl = "http://localhost:"+port+"/employees/";
//        URI uri = new URI(baseUrl);
//        Employee employee = new Employee(null, "Adam", "Gilly", "test@email.com");
//         
        
        //create queue and dlqueue
        
        String queueName = "ediEnrollment1232local";
        String dlQueueName = "dlediEnrollment1232local";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true"); 
        headers.set("authorization","something");
// 
//        HttpEntity<Employee> request = new HttpEntity<>(employee, headers);
        HttpEntity<String> request = new HttpEntity<>(queueName, headers);
//         
        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        System.out.println("Queuename Response: "+result.getBody());
        
        //submit ediEnrollments
        EDIRequest ediRequest = new EDIRequest();
        ediRequest.dlQueueName=dlQueueName;
        String ediJson = "{\"id\":null,\"policyId\":\"12311212121\",\"subscriber\":{\"id\":null,\"enrolleeID\":\"121212121\",\"memberID\":\"987987987901\",\"personID\":\"1231234352345245245\",\"firstName\":\"John\",\"lastName\":\"Smith\",\"emailID\":\"john.smith@something.com\",\"mobile\":\"891-121-1212\",\"country\":\"USA\",\"state\":\"CA\",\"city\":\"Fremont\",\"zip\":\"94538\",\"gender\":\"Male\",\"dateOfBirth\":\"03/03/1986\",\"groupNumber\":\"72003\",\"payerID\":\"payer101\",\"employerID\":\"CA7200301\",\"relationshipStatus\":\"Self\",\"isRemoved\":false,\"date\":null,\"eligibilityEffectiveDate\":1577865600000,\"eligibilityEndDate\":1609401600000,\"plan\":\"DHMO\",\"networkId\":\"DELTACARE\",\"planState\":\"CA\"},\"dependents\":[{\"id\":null,\"enrolleeID\":\"121212121\",\"memberID\":\"987987987902\",\"personID\":\"1231234352345245246\",\"firstName\":\"Carol\",\"lastName\":\"Smith\",\"emailID\":\"carol.smith@something.com\",\"mobile\":\"891-121-1213\",\"country\":\"USA\",\"state\":\"CA\",\"city\":\"Fremont\",\"zip\":\"94538\",\"gender\":\"Female\",\"dateOfBirth\":\"04/03/1987\",\"groupNumber\":\"72003\",\"payerID\":\"payer101\",\"employerID\":\"CA7200301\",\"relationshipStatus\":\"Spouse\",\"isRemoved\":false,\"date\":null,\"eligibilityEffectiveDate\":1577865600000,\"eligibilityEndDate\":1609401600000,\"plan\":\"DHMO\",\"networkId\":\"DELTACARE\",\"planState\":\"CA\"},{\"id\":null,\"enrolleeID\":\"121212121\",\"memberID\":\"987987987903\",\"personID\":\"1231234352345245248\",\"firstName\":\"Roy\",\"lastName\":\"Smith\",\"emailID\":\"roy.smith@something.com\",\"mobile\":\"891-121-1212\",\"country\":\"USA\",\"state\":\"CA\",\"city\":\"Fremont\",\"zip\":\"94538\",\"gender\":\"Male\",\"dateOfBirth\":\"06/03/2006\",\"groupNumber\":\"72003\",\"payerID\":\"payer101\",\"employerID\":\"CA7200301\",\"relationshipStatus\":\"Child\",\"isRemoved\":false,\"date\":null,\"eligibilityEffectiveDate\":1577865600000,\"eligibilityEndDate\":1609401600000,\"plan\":\"DHMO\",\"networkId\":\"DELTACARE\",\"planState\":\"CA\"}],\"groupId\":\"72003\",\"divisionId\":\"10001\",\"receivedDate\":1577865600000,\"coverageEffDate\":1577865600000,\"coverageEndDate\":1609401600000,\"coverageType\":\"Subscriber + Family\"}\n" + 
        		"";
        List<String> edis = new ArrayList<String>();
        edis.add(ediJson);
        ediRequest.queueName=queueName;
        ediRequest.tokenId="12121";
        ediRequest.userId="pk1231";
        ediRequest.ediTransactions = edis;
        uri = "http://34.232.245.38:8888/v1/ediEnrollment/";
        uri = "http://localhost:8888/blockchain-api/v1/ediEnrollment/";
        
        HttpEntity<EDIRequest> requestEdi = new HttpEntity<>(ediRequest, headers);
        ResponseEntity<String> resultEdi = this.restTemplate.postForEntity(uri, requestEdi, String.class);
        System.out.println("Submit ediEnrollments Response: "+resultEdi.getBody());
        //process EDI Enrollments - read from queue
        //enrollments
        
        uri = "http://localhost:8888/blockchain-api/v1/enrollments/";
        MultiValueMap<String, String> map= new LinkedMultiValueMap<String, String>();
        EDIRequest ediRequestp = new EDIRequest();
        ediRequest.dlQueueName=dlQueueName;
        ediRequestp.queueName=queueName;
        ediRequestp.tokenId="12121";
        ediRequestp.userId="pk1231";
        ediRequest.ediTransactions = edis;
        
        HttpEntity<EDIRequest> requestProcessEdi = new HttpEntity<>(ediRequestp, headers);
        ResponseEntity<String> resultProcessEdi = this.restTemplate.postForEntity(uri, requestProcessEdi, String.class);
        System.out.println("process ediEnrollments Response: "+resultProcessEdi.getBody());
    }

}
