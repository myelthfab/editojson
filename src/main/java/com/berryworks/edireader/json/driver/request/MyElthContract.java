package com.berryworks.edireader.json.driver.request;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class MyElthContract {

	private String contractId;
	private String subscriberId;
	private String employementStatus;
	private String medicaidProviderID;
	private Enrollee subscriber;
	private List<Enrollee> dependents;
	private ResponsibleParty responsibleParty;
	private Enrollee ediMember;
	private String groupNumber;
	private String divisionNumber;
	private String groupName;
	private String actionCode;
	private String senderId;
	private String receiverId;
	private String controlNumber;
	private String sponsorName;
	 private String payerIdentifier;
	 private String payer;
	 private String maintenanceCode;
	 private String maintenanceCodeDesc;
	 private Date coverageReviewDate;
	private Date benefitBeginDate;
	private Date benefitEndDate;
	private Map<String, String> alternateIds;
	
	
	 

	public Map<String, String> getAlternateIds() {
		return alternateIds;
	}

	public void setAlternateIds(Map<String, String> alternateIds) {
		this.alternateIds = alternateIds;
	}

	public String getMaintenanceCodeDesc() {
		return maintenanceCodeDesc;
	}

	public void setMaintenanceCodeDesc(String maintenanceCodeDesc) {
		this.maintenanceCodeDesc = maintenanceCodeDesc;
	}

	public String getMedicaidProviderID() {
		return medicaidProviderID;
	}

	public void setMedicaidProviderID(String medicaidProviderID) {
		this.medicaidProviderID = medicaidProviderID;
	}

	public Date getBenefitBeginDate() {
		return benefitBeginDate;
	}

	public void setBenefitBeginDate(Date benefitBeginDate) {
		this.benefitBeginDate = benefitBeginDate;
	}

	public Date getBenefitEndDate() {
		return benefitEndDate;
	}

	public void setBenefitEndDate(Date benefitEndDate) {
		this.benefitEndDate = benefitEndDate;
	}

	public Date getCoverageReviewDate() {
		return coverageReviewDate;
	}

	public void setCoverageReviewDate(Date coverageReviewDate) {
		this.coverageReviewDate = coverageReviewDate;
	}

	public String getMaintenanceCode() {
		return maintenanceCode;
	}

	public void setMaintenanceCode(String maintenanceCode) {
		this.maintenanceCode = maintenanceCode;
	}

	public String getPayerIdentifier() {
		return payerIdentifier;
	}

	public void setPayerIdentifier(String payerIdentifier) {
		this.payerIdentifier = payerIdentifier;
	}

	public String getPayer() {
		return payer;
	}

	public void setPayer(String payer) {
		this.payer = payer;
	}

	private String sponsorIdentifier;

	public String getSponsorIdentifier() {
		return sponsorIdentifier;
	}

	public void setSponsorIdentifier(String sponsorIdentifier) {
		this.sponsorIdentifier = sponsorIdentifier;
	}

	public String getSponsorName() {
		return sponsorName;
	}

	public void setSponsorName(String sponsorName) {
		this.sponsorName = sponsorName;
	}

	public String getControlNumber() {
		return controlNumber;
	}

	public void setControlNumber(String controlNumber) {
		this.controlNumber = controlNumber;
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

	public String getActionCode() {
		return actionCode;
	}

	public void setActionCode(String actionCode) {
		this.actionCode = actionCode;
	}

	public String getGroupNumber() {
		return groupNumber;
	}

	public void setGroupNumber(String groupNumber) {
		this.groupNumber = groupNumber;
	}

	public String getDivisionNumber() {
		return divisionNumber;
	}

	public void setDivisionNumber(String divisionNumber) {
		this.divisionNumber = divisionNumber;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public Enrollee getEdiMember() {
		return ediMember;
	}

	public void setEdiMember(Enrollee ediMember) {
		this.ediMember = ediMember;
	}

	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getSubscriberId() {
		return subscriberId;
	}

	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}

	public String getEmployementStatus() {
		return employementStatus;
	}

	public void setEmployementStatus(String employementStatus) {
		this.employementStatus = employementStatus;
	}

	public Enrollee getSubscriber() {
		return subscriber;
	}

	public void setSubscriber(Enrollee subscriber) {
		this.subscriber = subscriber;
	}

	public List<Enrollee> getDependents() {
		return dependents;
	}

	public void setDependents(List<Enrollee> dependents) {
		this.dependents = dependents;
	}

	public ResponsibleParty getResponsibleParty() {
		return responsibleParty;
	}

	public void setResponsibleParty(ResponsibleParty responsibleParty) {
		this.responsibleParty = responsibleParty;
	}

}
