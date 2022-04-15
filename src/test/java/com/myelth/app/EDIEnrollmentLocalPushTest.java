package com.myelth.app;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import com.berryworks.edireader.json.driver.request.MyElthContract;
import com.berryworks.edireader.json.myelth.EDIToJSONMyElth;
import com.myelth.app.model.EDIRequest;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EDIEnrollmentLocalPushTest {
	// bind the above RANDOM_PORT
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testEDITransactions() throws Exception {
    	String filePath = "/Users/prakashsingh/eclipse-workspace/scottmarchreleasesplit/testoracle.edi";
//    	String filePath = "/Users/prakashsingh/eclipse-workspace/scottmarchreleasesplit/testclaim.edi";
    	
    	EDIToJSONMyElth ediToJson = new EDIToJSONMyElth();
    	List<MyElthContract> contracts = ediToJson.convertEDIToJSON(filePath);
    	
    	for (MyElthContract myElthContract : contracts) {
			
	
    	String myElthJson = ediToJson.convertMyElthContractToJson(myElthContract);
        
        String queueName = "ediEnrollment1232local";
        String dlQueueName = "dlediEnrollment1232local";
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-COM-PERSIST", "true"); 
        headers.set("authorization","something");

        //submit ediEnrollments
        EDIRequest ediRequest = new EDIRequest();
        ediRequest.dlQueueName=dlQueueName;
        List<String> edis = new ArrayList<String>();
        edis.add(myElthJson);
        ediRequest.queueName=queueName;
        ediRequest.tokenId="12121";
        ediRequest.userId="pk1231";
        ediRequest.ediTransactions = edis;
        String uri = "http://localhost:8888/blockchain-api/v1/ediSubmitEnrollment/";
        
        HttpEntity<EDIRequest> requestEdi = new HttpEntity<>(ediRequest, headers);
        ResponseEntity<String> resultEdi = this.restTemplate.postForEntity(uri, requestEdi, String.class);
        System.out.println("Submit ediEnrollments Response: "+resultEdi.getBody());
        //process EDI Enrollments - read from queue
        //enrollments
        
    	}
    }

}
