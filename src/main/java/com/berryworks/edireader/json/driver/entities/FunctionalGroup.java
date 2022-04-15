package com.berryworks.edireader.json.driver.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FunctionalGroup{
    @JsonProperty("GS") 
    public String gS;
    @JsonProperty("GS_01_FunctionalIdentifierCode") 
    public String gS_01_FunctionalIdentifierCode;
    @JsonProperty("GS_02_ApplicationSenderCode") 
    public String gS_02_ApplicationSenderCode;
    @JsonProperty("GS_03_ApplicationReceiverCode") 
    public String gS_03_ApplicationReceiverCode;
    @JsonProperty("GS_04_Date") 
    public String gS_04_Date;
    @JsonProperty("GS_05_Time") 
    public String gS_05_Time;
    @JsonProperty("GS_06_GroupControlNumber") 
    public String gS_06_GroupControlNumber;
    @JsonProperty("GS_07_ResponsibleAgencyCode") 
    public String gS_07_ResponsibleAgencyCode;
    @JsonProperty("GS_08_Version") 
    public String gS_08_Version;
    public List<Transaction> transactions;
	public String getgS() {
		return gS;
	}
	public void setgS(String gS) {
		this.gS = gS;
	}
	public String getgS_01_FunctionalIdentifierCode() {
		return gS_01_FunctionalIdentifierCode;
	}
	public void setgS_01_FunctionalIdentifierCode(String gS_01_FunctionalIdentifierCode) {
		this.gS_01_FunctionalIdentifierCode = gS_01_FunctionalIdentifierCode;
	}
	public String getgS_02_ApplicationSenderCode() {
		return gS_02_ApplicationSenderCode;
	}
	public void setgS_02_ApplicationSenderCode(String gS_02_ApplicationSenderCode) {
		this.gS_02_ApplicationSenderCode = gS_02_ApplicationSenderCode;
	}
	public String getgS_03_ApplicationReceiverCode() {
		return gS_03_ApplicationReceiverCode;
	}
	public void setgS_03_ApplicationReceiverCode(String gS_03_ApplicationReceiverCode) {
		this.gS_03_ApplicationReceiverCode = gS_03_ApplicationReceiverCode;
	}
	public String getgS_04_Date() {
		return gS_04_Date;
	}
	public void setgS_04_Date(String gS_04_Date) {
		this.gS_04_Date = gS_04_Date;
	}
	public String getgS_05_Time() {
		return gS_05_Time;
	}
	public void setgS_05_Time(String gS_05_Time) {
		this.gS_05_Time = gS_05_Time;
	}
	public String getgS_06_GroupControlNumber() {
		return gS_06_GroupControlNumber;
	}
	public void setgS_06_GroupControlNumber(String gS_06_GroupControlNumber) {
		this.gS_06_GroupControlNumber = gS_06_GroupControlNumber;
	}
	public String getgS_07_ResponsibleAgencyCode() {
		return gS_07_ResponsibleAgencyCode;
	}
	public void setgS_07_ResponsibleAgencyCode(String gS_07_ResponsibleAgencyCode) {
		this.gS_07_ResponsibleAgencyCode = gS_07_ResponsibleAgencyCode;
	}
	public String getgS_08_Version() {
		return gS_08_Version;
	}
	public void setgS_08_Version(String gS_08_Version) {
		this.gS_08_Version = gS_08_Version;
	}
	public List<Transaction> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}