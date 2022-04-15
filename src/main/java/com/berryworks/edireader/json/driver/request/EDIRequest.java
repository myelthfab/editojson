package com.berryworks.edireader.json.driver.request;

import java.util.List;

public class EDIRequest {
	
    private String userId;
    private String tokenId;
    private String queueName;
    private String dlQueueName;
    private List<String> ediTransactions;
    
    private String sponsorName;
    private String payer;
    private String tpaBrokerName;
    private String senderId;
    private String receiverId;
    private String groupNumber;
    private String actionCode;
    private String receivedDate;
    private String receivedTime;
    private String appSenderCode;
    private String appReceiverCode;
    private String referenceIdentifier;
    private String timeCode;
    private String actionCodeDesc;
    private String timesec;
    private String fileRecieveDate;
    private String controlNumber;
    private String sponsorIdentifier;
    private String payerIdentifier;
    
    
    
    public String getPayerIdentifier() {
		return payerIdentifier;
	}
	public void setPayerIdentifier(String payerIdentifier) {
		this.payerIdentifier = payerIdentifier;
	}
	public String getSponsorIdentifier() {
		return sponsorIdentifier;
	}
	public void setSponsorIdentifier(String sponsorIdentifier) {
		this.sponsorIdentifier = sponsorIdentifier;
	}
	public String getControlNumber() {
		return controlNumber;
	}
	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
	}
	public String getFileRecieveDate() {
		return fileRecieveDate;
	}
	public void setFileRecieveDate(String fileRecieveDate) {
		this.fileRecieveDate = fileRecieveDate;
	}
	private List<Member> members;
    private String masterPolicyNumber;
    
    
    
    public String getMasterPolicyNumber() {
		return masterPolicyNumber;
	}
	public void setMasterPolicyNumber(String masterPolicyNumber) {
		this.masterPolicyNumber = masterPolicyNumber;
	}
	public String getReferenceIdentifier() {
		return referenceIdentifier;
	}
	public void setReferenceIdentifier(String referenceIdentifier) {
		this.referenceIdentifier = referenceIdentifier;
	}
	public String getTimeCode() {
		return timeCode;
	}
	public void setTimeCode(String timeCode) {
		this.timeCode = timeCode;
	}
	public String getActionCodeDesc() {
		return actionCodeDesc;
	}
	public void setActionCodeDesc(String actionCodeDesc) {
		this.actionCodeDesc = actionCodeDesc;
	}
	public String getTimesec() {
		return timesec;
	}
	public void setTimesec(String timesec) {
		this.timesec = timesec;
	}
	public String getAppSenderCode() {
		return appSenderCode;
	}
	public void setAppSenderCode(String appSenderCode) {
		this.appSenderCode = appSenderCode;
	}
	public String getAppReceiverCode() {
		return appReceiverCode;
	}
	public void setAppReceiverCode(String appReceiverCode) {
		this.appReceiverCode = appReceiverCode;
	}
	
    
    
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public String getReceivedTime() {
		return receivedTime;
	}
	public void setReceivedTime(String receivedTime) {
		this.receivedTime = receivedTime;
	}
	public List<Member> getMembers() {
		return members;
	}
	public void setMembers(List<Member> members) {
		this.members = members;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getTokenId() {
		return tokenId;
	}
	public void setTokenId(String tokenId) {
		this.tokenId = tokenId;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getDlQueueName() {
		return dlQueueName;
	}
	public void setDlQueueName(String dlQueueName) {
		this.dlQueueName = dlQueueName;
	}
	public List<String> getEdiTransactions() {
		return ediTransactions;
	}
	public void setEdiTransactions(List<String> ediTransactions) {
		this.ediTransactions = ediTransactions;
	}
	public String getSponsorName() {
		return sponsorName;
	}
	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}
	public String getPayer() {
		return payer;
	}
	public void setPayer(String payer) {
		this.payer = payer;
	}
	public String getTpaBrokerName() {
		return tpaBrokerName;
	}
	public void setTpaBrokerName(String tpaBrokerName) {
		this.tpaBrokerName = tpaBrokerName;
	}
	public String getSenderId() {
		return senderId;
	}
	public void setSenderId(String senderId) {
		this.senderId = senderId;
	}
	public String getReceiverId() {
		return receiverId;
	}
	public void setReceiverId(String receiverId) {
		this.receiverId = receiverId;
	}
	public String getGroupNumber() {
		return groupNumber;
	}
	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}
	public String getActionCode() {
		return actionCode;
	}
	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}
    
}