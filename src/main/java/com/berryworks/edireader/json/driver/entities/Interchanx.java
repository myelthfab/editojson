package com.berryworks.edireader.json.driver.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Interchanx{
    @JsonProperty("ISA") 
    public String iSA;
    @JsonProperty("ISA_01_AuthorizationQualifier") 
    public String iSA_01_AuthorizationQualifier;
    @JsonProperty("ISA_02_AuthorizationInformation") 
    public String iSA_02_AuthorizationInformation;
    @JsonProperty("ISA_03_SecurityQualifier") 
    public String iSA_03_SecurityQualifier;
    @JsonProperty("ISA_04_SecurityInformation") 
    public String iSA_04_SecurityInformation;
    @JsonProperty("ISA_05_SenderQualifier") 
    public String iSA_05_SenderQualifier;
    @JsonProperty("ISA_06_SenderId") 
    public String iSA_06_SenderId;
    @JsonProperty("ISA_07_ReceiverQualifier") 
    public String iSA_07_ReceiverQualifier;
    @JsonProperty("ISA_08_ReceiverId") 
    public String iSA_08_ReceiverId;
    @JsonProperty("ISA_09_Date") 
    public String iSA_09_Date;
    @JsonProperty("ISA_10_Time") 
    public String iSA_10_Time;
    @JsonProperty("ISA_11_RepetitionSeparator") 
    public String iSA_11_RepetitionSeparator;
    @JsonProperty("ISA_12_Version") 
    public String iSA_12_Version;
    @JsonProperty("ISA_13_InterchangeControlNumber") 
    public String iSA_13_InterchangeControlNumber;
    @JsonProperty("ISA_14_AcknowledgmentRequested") 
    public String iSA_14_AcknowledgmentRequested;
    @JsonProperty("ISA_15_TestIndicator") 
    public String iSA_15_TestIndicator;
    public List<FunctionalGroup> functional_groups;
	public String getiSA() {
		return iSA;
	}
	public void setiSA(String iSA) {
		this.iSA = iSA;
	}
	public String getiSA_01_AuthorizationQualifier() {
		return iSA_01_AuthorizationQualifier;
	}
	public void setiSA_01_AuthorizationQualifier(String iSA_01_AuthorizationQualifier) {
		this.iSA_01_AuthorizationQualifier = iSA_01_AuthorizationQualifier;
	}
	public String getiSA_02_AuthorizationInformation() {
		return iSA_02_AuthorizationInformation;
	}
	public void setiSA_02_AuthorizationInformation(String iSA_02_AuthorizationInformation) {
		this.iSA_02_AuthorizationInformation = iSA_02_AuthorizationInformation;
	}
	public String getiSA_03_SecurityQualifier() {
		return iSA_03_SecurityQualifier;
	}
	public void setiSA_03_SecurityQualifier(String iSA_03_SecurityQualifier) {
		this.iSA_03_SecurityQualifier = iSA_03_SecurityQualifier;
	}
	public String getiSA_04_SecurityInformation() {
		return iSA_04_SecurityInformation;
	}
	public void setiSA_04_SecurityInformation(String iSA_04_SecurityInformation) {
		this.iSA_04_SecurityInformation = iSA_04_SecurityInformation;
	}
	public String getiSA_05_SenderQualifier() {
		return iSA_05_SenderQualifier;
	}
	public void setiSA_05_SenderQualifier(String iSA_05_SenderQualifier) {
		this.iSA_05_SenderQualifier = iSA_05_SenderQualifier;
	}
	public String getiSA_06_SenderId() {
		return iSA_06_SenderId;
	}
	public void setiSA_06_SenderId(String iSA_06_SenderId) {
		this.iSA_06_SenderId = iSA_06_SenderId;
	}
	public String getiSA_07_ReceiverQualifier() {
		return iSA_07_ReceiverQualifier;
	}
	public void setiSA_07_ReceiverQualifier(String iSA_07_ReceiverQualifier) {
		this.iSA_07_ReceiverQualifier = iSA_07_ReceiverQualifier;
	}
	public String getiSA_08_ReceiverId() {
		return iSA_08_ReceiverId;
	}
	public void setiSA_08_ReceiverId(String iSA_08_ReceiverId) {
		this.iSA_08_ReceiverId = iSA_08_ReceiverId;
	}
	public String getiSA_09_Date() {
		return iSA_09_Date;
	}
	public void setiSA_09_Date(String iSA_09_Date) {
		this.iSA_09_Date = iSA_09_Date;
	}
	public String getiSA_10_Time() {
		return iSA_10_Time;
	}
	public void setiSA_10_Time(String iSA_10_Time) {
		this.iSA_10_Time = iSA_10_Time;
	}
	public String getiSA_11_RepetitionSeparator() {
		return iSA_11_RepetitionSeparator;
	}
	public void setiSA_11_RepetitionSeparator(String iSA_11_RepetitionSeparator) {
		this.iSA_11_RepetitionSeparator = iSA_11_RepetitionSeparator;
	}
	public String getiSA_12_Version() {
		return iSA_12_Version;
	}
	public void setiSA_12_Version(String iSA_12_Version) {
		this.iSA_12_Version = iSA_12_Version;
	}
	public String getiSA_13_InterchangeControlNumber() {
		return iSA_13_InterchangeControlNumber;
	}
	public void setiSA_13_InterchangeControlNumber(String iSA_13_InterchangeControlNumber) {
		this.iSA_13_InterchangeControlNumber = iSA_13_InterchangeControlNumber;
	}
	public String getiSA_14_AcknowledgmentRequested() {
		return iSA_14_AcknowledgmentRequested;
	}
	public void setiSA_14_AcknowledgmentRequested(String iSA_14_AcknowledgmentRequested) {
		this.iSA_14_AcknowledgmentRequested = iSA_14_AcknowledgmentRequested;
	}
	public String getiSA_15_TestIndicator() {
		return iSA_15_TestIndicator;
	}
	public void setiSA_15_TestIndicator(String iSA_15_TestIndicator) {
		this.iSA_15_TestIndicator = iSA_15_TestIndicator;
	}
	public List<FunctionalGroup> getFunctional_groups() {
		return functional_groups;
	}
	public void setFunctional_groups(List<FunctionalGroup> functional_groups) {
		this.functional_groups = functional_groups;
	}
    
}