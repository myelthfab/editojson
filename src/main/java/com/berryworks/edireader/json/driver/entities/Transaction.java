package com.berryworks.edireader.json.driver.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Transaction{
    @JsonProperty("834") 
    public String _834;
    @JsonProperty("ST") 
    public String sT;
    @JsonProperty("ST_01_TransactionSetIdentifierCode") 
    public String sT_01_TransactionSetIdentifierCode;
    @JsonProperty("ST_02_TransactionSetControlNumber") 
    public String sT_02_TransactionSetControlNumber;
    @JsonProperty("ST_03_ImplementationConventionReference") 
    public String sT_03_ImplementationConventionReference;
    public String get_834() {
		return _834;
	}
	public void set_834(String _834) {
		this._834 = _834;
	}
	public String getsT() {
		return sT;
	}
	public void setsT(String sT) {
		this.sT = sT;
	}
	public String getsT_01_TransactionSetIdentifierCode() {
		return sT_01_TransactionSetIdentifierCode;
	}
	public void setsT_01_TransactionSetIdentifierCode(String sT_01_TransactionSetIdentifierCode) {
		this.sT_01_TransactionSetIdentifierCode = sT_01_TransactionSetIdentifierCode;
	}
	public String getsT_02_TransactionSetControlNumber() {
		return sT_02_TransactionSetControlNumber;
	}
	public void setsT_02_TransactionSetControlNumber(String sT_02_TransactionSetControlNumber) {
		this.sT_02_TransactionSetControlNumber = sT_02_TransactionSetControlNumber;
	}
	public String getsT_03_ImplementationConventionReference() {
		return sT_03_ImplementationConventionReference;
	}
	public void setsT_03_ImplementationConventionReference(String sT_03_ImplementationConventionReference) {
		this.sT_03_ImplementationConventionReference = sT_03_ImplementationConventionReference;
	}
	public List<Segment> getSegments() {
		return segments;
	}
	public void setSegments(List<Segment> segments) {
		this.segments = segments;
	}
	public List<Segment> segments;
}