package com.berryworks.edireader.json.driver.request;

import java.util.List;

public class Enrollee {
	
	private String enrolleeId;
	private String memberId;
	private Person person;
	private List<Eligibility> eligibilities;
	private String relationshipCode;
	private String relationshipCodeDesc;
	private String subscriberOrDependent;
	private String enrollmentAction;
	private String subscriberId;
	private String caseId;
	private String alternateId;
	
	
	
	public String getRelationshipCodeDesc() {
		return relationshipCodeDesc;
	}
	public void setRelationshipCodeDesc(String relationshipCodeDesc) {
		this.relationshipCodeDesc = relationshipCodeDesc;
	}
	public String getSubscriberId() {
		return subscriberId;
	}
	public void setSubscriberId(String subscriberId) {
		this.subscriberId = subscriberId;
	}
	public String getCaseId() {
		return caseId;
	}
	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}
	public String getAlternateId() {
		return alternateId;
	}
	public void setAlternateId(String alternateId) {
		this.alternateId = alternateId;
	}
	public String getEnrollmentAction() {
		return enrollmentAction;
	}
	public void setEnrollmentAction(String enrollmentAction) {
		this.enrollmentAction = enrollmentAction;
	}
	public String getSubscriberOrDependent() {
		return subscriberOrDependent;
	}
	public void setSubscriberOrDependent(String subscriberOrDependent) {
		this.subscriberOrDependent = subscriberOrDependent;
	}
	public String getEnrolleeId() {
		return enrolleeId;
	}
	public void setEnrolleeId(String enrolleeId) {
		this.enrolleeId = enrolleeId;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public Person getPerson() {
		return person;
	}
	public void setPerson(Person person) {
		this.person = person;
	}
	public List<Eligibility> getEligibilities() {
		return eligibilities;
	}
	public void setEligibilities(List<Eligibility> eligibilities) {
		this.eligibilities = eligibilities;
	}
	public String getRelationshipCode() {
		return relationshipCode;
	}
	public void setRelationshipCode(String relationshipCode) {
		this.relationshipCode = relationshipCode;
	}
	
	

}
