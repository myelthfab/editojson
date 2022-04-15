package com.berryworks.edireader.json.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.berryworks.edireader.json.driver.entities.FunctionalGroup;
import com.berryworks.edireader.json.driver.entities.HD2300Loop;
import com.berryworks.edireader.json.driver.entities.INS2000Loop;
import com.berryworks.edireader.json.driver.entities.Interchanx;
import com.berryworks.edireader.json.driver.entities.N11000ALoop;
import com.berryworks.edireader.json.driver.entities.N11000BLoop;
import com.berryworks.edireader.json.driver.entities.NM12100ALoop;
import com.berryworks.edireader.json.driver.entities.Root;
import com.berryworks.edireader.json.driver.entities.Segment;
import com.berryworks.edireader.json.driver.entities.Transaction;
import com.berryworks.edireader.json.driver.request.EDIRequest;
import com.berryworks.edireader.json.driver.request.Member;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonToJava {
	
	public static void main(String[] args) {
		
		
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		mapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

	    //JSON file to Java object
	    try {
			Root obj = mapper.readValue(new File("/Users/prakashsingh/eclipse-workspace/editojsonsam/src/main/resources/test2.json"), Root.class);
			System.out.println(obj);
			EDIRequest ediRequest = transform(obj);
			System.out.println(ediRequest);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	   
	  
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
								ediRequest.setTimeCode(segment.getbGN_05());
								ediRequest.setActionCode(segment.getbGN_04());
								ediRequest.setActionCodeDesc(segment.getbGN_08_code_4());
								ediRequest.setMasterPolicyNumber(segment.getrEF_02());
								}
								
								List<N11000ALoop> n11000As = segment.getN11000ALoop();
								
								if(n11000As != null) {
								for (N11000ALoop n11000A : n11000As) {
									ediRequest.setSponsorName(n11000A.getN1_02());
								}
								}
								
								List<N11000BLoop> n11000Bs = segment.getN11000BLoop();
								if(n11000Bs != null) {
								for (N11000BLoop n11000B : n11000Bs) {
									ediRequest.setPayer(n11000B.getN1_02());
								}
								}
								
								
								List<INS2000Loop> ins2000s = segment.getiNS2000Loop();
								if(ins2000s != null) {
								
								Member mem = new Member();
								for (INS2000Loop ins2000 : ins2000s) {
									
									
									if(ins2000.getiNS() != null && ins2000.getiNS().contains("Insured")) {
										mem.setIns_01(ins2000.getiNS_01());
										if(ins2000.getiNS_01().contains("N")) {
										mem.setIns_01_code(ins2000.getiNS_01_code_N());
										} else {
											mem.setIns_01_code(ins2000.getiNS_01_code_Y());
										}
										mem.setIns_02(ins2000.getiNS_02());
										mem.setIns_03(ins2000.getiNS_03());
										mem.setIns_08(ins2000.getiNS_08());
									}
									if(mem.getReferences() == null) {
									Map<String, String> references = new HashMap<String, String>();
									if(ins2000.getrEF() != null && ins2000.getrEF().contains("Reference")) {
										references.put(ins2000.getrEF_01(), ins2000.getrEF_02());
										mem.setReferences(references);
									}
									
									} else {
										if(ins2000.getrEF() != null && ins2000.getrEF().contains("Reference")) {
										mem.getReferences().put(ins2000.getrEF_01(), ins2000.getrEF_02());
										}
									}
									
									List<HD2300Loop> hd2300s = ins2000.gethD2300Loop();
									if(hd2300s != null) {
									for (HD2300Loop hd2300 : hd2300s) {
										if(hd2300.gethD_01() != null) {
											mem.setHd_01_value(hd2300.gethD_01());
											mem.setHd_03_value(hd2300.gethD_03());
										}
										if(hd2300.getdTP_01() != null) {
											mem.setDtp_01_value(hd2300.getdTP_01());
											mem.setDtp_02_value(hd2300.getdTP_02());
											mem.setEligibilityEffectiveDate(hd2300.getdTP_03());
										}
										if(hd2300.getrEF_01() != null) {
											mem.setHd_ref_01_value(hd2300.getrEF_01());
											mem.setHd_ref_02_value(hd2300.getrEF_02());
											
										}
										
									}}
									
									
									List<NM12100ALoop> nm2100As = ins2000.getnM12100ALoop();
									if(nm2100As != null) {
									for (NM12100ALoop nm2100A : nm2100As) {
										if(nm2100A.getnM1_03() != null) {
											mem.setLastName(nm2100A.getnM1_03());
										}
										if(nm2100A.getnM1_04() != null) {
											mem.setFirstName(nm2100A.getnM1_03());
										}
										if(nm2100A.getnM1_05() != null) {
											mem.setMiddleName(nm2100A.getnM1_05());
										}
										if(nm2100A.getnM1_08() != null && nm2100A.getnM1_08().contains("34")) {
											mem.setSsn(nm2100A.getnM1_09());
										}
										if(nm2100A.getpER_03() != null) {
											mem.setPer_03(nm2100A.getpER_03());
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
