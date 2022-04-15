package com.myelth.app.model;

import java.util.List;

public class EDIRequest {
	
    public String userId;
    public String tokenId;
    public String queueName;
    public String dlQueueName;
    public List<String> ediTransactions;
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
    
    
}