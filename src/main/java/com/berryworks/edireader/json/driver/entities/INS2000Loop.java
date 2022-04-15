package com.berryworks.edireader.json.driver.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class INS2000Loop{
	@JsonProperty("INS") 
    public String iNS;
    @JsonProperty("INS_01_description") 
    public String iNS_01_description;
    @JsonProperty("INS_01") 
    public String iNS_01;
    @JsonProperty("INS_01_code_Y") 
    public String iNS_01_code_Y;
    @JsonProperty("INS_02_description") 
    public String iNS_02_description;
    @JsonProperty("INS_02") 
    public String iNS_02;
    @JsonProperty("INS_02_code_18") 
    public String iNS_02_code_18;
    @JsonProperty("INS_03_description") 
    public String iNS_03_description;
    @JsonProperty("INS_03") 
    public String iNS_03;
    @JsonProperty("INS_03_code_021") 
    public String iNS_03_code_021;
    @JsonProperty("INS_04_description") 
    public String iNS_04_description;
    @JsonProperty("INS_04") 
    public String iNS_04;
    @JsonProperty("INS_04_code_AI") 
    public String iNS_04_code_AI;
    @JsonProperty("INS_05_description") 
    public String iNS_05_description;
    @JsonProperty("INS_05") 
    public String iNS_05;
    @JsonProperty("INS_05_code_A") 
    public String iNS_05_code_A;
    @JsonProperty("INS_08_description") 
    public String iNS_08_description;
    @JsonProperty("INS_08") 
    public String iNS_08;
    @JsonProperty("INS_08_code_FT") 
    public String iNS_08_code_FT;
    @JsonProperty("INS_10_description") 
    public String iNS_10_description;
    @JsonProperty("INS_10") 
    public String iNS_10;
    @JsonProperty("INS_10_code_N") 
    public String iNS_10_code_N;
    @JsonProperty("REF") 
    public String rEF;
    @JsonProperty("REF_01_description") 
    public String rEF_01_description;
    @JsonProperty("REF_01") 
    public String rEF_01;
    @JsonProperty("REF_01_code_0F") 
    public String rEF_01_code_0F;
    @JsonProperty("REF_02_description") 
    public String rEF_02_description;
    @JsonProperty("REF_02") 
    public String rEF_02;
    @JsonProperty("REF_01_code_3H") 
    public String rEF_01_code_3H;
    @JsonProperty("DTP") 
    public String dTP;
    @JsonProperty("DTP_01_description") 
    public String dTP_01_description;
    @JsonProperty("DTP_01") 
    public String dTP_01;
    @JsonProperty("DTP_01_code_300") 
    public String dTP_01_code_300;
    @JsonProperty("DTP_02_description") 
    public String dTP_02_description;
    @JsonProperty("DTP_02") 
    public String dTP_02;
    @JsonProperty("DTP_02_code_D8") 
    public String dTP_02_code_D8;
    @JsonProperty("DTP_03_description") 
    public String dTP_03_description;
    @JsonProperty("DTP_03") 
    public String dTP_03;
    @JsonProperty("DTP_01_code_473") 
    public String dTP_01_code_473;
    @JsonProperty("2100_loop") 
    public List<NM12100Loop> _2100_loop;
    @JsonProperty("2300_loop") 
    public List<HD2300Loop> _2300_loop;
    
    
	public List<HD2300Loop> get_2300_loop() {
		return _2300_loop;
	}
	public void set_2300_loop(List<HD2300Loop> _2300_loop) {
		this._2300_loop = _2300_loop;
	}
	public String getiNS() {
		return iNS;
	}
	public void setiNS(String iNS) {
		this.iNS = iNS;
	}
	public String getiNS_01_description() {
		return iNS_01_description;
	}
	public void setiNS_01_description(String iNS_01_description) {
		this.iNS_01_description = iNS_01_description;
	}
	public String getiNS_01() {
		return iNS_01;
	}
	public void setiNS_01(String iNS_01) {
		this.iNS_01 = iNS_01;
	}
	public String getiNS_01_code_Y() {
		return iNS_01_code_Y;
	}
	public void setiNS_01_code_Y(String iNS_01_code_Y) {
		this.iNS_01_code_Y = iNS_01_code_Y;
	}
	public String getiNS_02_description() {
		return iNS_02_description;
	}
	public void setiNS_02_description(String iNS_02_description) {
		this.iNS_02_description = iNS_02_description;
	}
	public String getiNS_02() {
		return iNS_02;
	}
	public void setiNS_02(String iNS_02) {
		this.iNS_02 = iNS_02;
	}
	public String getiNS_02_code_18() {
		return iNS_02_code_18;
	}
	public void setiNS_02_code_18(String iNS_02_code_18) {
		this.iNS_02_code_18 = iNS_02_code_18;
	}
	public String getiNS_03_description() {
		return iNS_03_description;
	}
	public void setiNS_03_description(String iNS_03_description) {
		this.iNS_03_description = iNS_03_description;
	}
	public String getiNS_03() {
		return iNS_03;
	}
	public void setiNS_03(String iNS_03) {
		this.iNS_03 = iNS_03;
	}
	public String getiNS_03_code_021() {
		return iNS_03_code_021;
	}
	public void setiNS_03_code_021(String iNS_03_code_021) {
		this.iNS_03_code_021 = iNS_03_code_021;
	}
	public String getiNS_04_description() {
		return iNS_04_description;
	}
	public void setiNS_04_description(String iNS_04_description) {
		this.iNS_04_description = iNS_04_description;
	}
	public String getiNS_04() {
		return iNS_04;
	}
	public void setiNS_04(String iNS_04) {
		this.iNS_04 = iNS_04;
	}
	public String getiNS_04_code_AI() {
		return iNS_04_code_AI;
	}
	public void setiNS_04_code_AI(String iNS_04_code_AI) {
		this.iNS_04_code_AI = iNS_04_code_AI;
	}
	public String getiNS_05_description() {
		return iNS_05_description;
	}
	public void setiNS_05_description(String iNS_05_description) {
		this.iNS_05_description = iNS_05_description;
	}
	public String getiNS_05() {
		return iNS_05;
	}
	public void setiNS_05(String iNS_05) {
		this.iNS_05 = iNS_05;
	}
	public String getiNS_05_code_A() {
		return iNS_05_code_A;
	}
	public void setiNS_05_code_A(String iNS_05_code_A) {
		this.iNS_05_code_A = iNS_05_code_A;
	}
	public String getiNS_08_description() {
		return iNS_08_description;
	}
	public void setiNS_08_description(String iNS_08_description) {
		this.iNS_08_description = iNS_08_description;
	}
	public String getiNS_08() {
		return iNS_08;
	}
	public void setiNS_08(String iNS_08) {
		this.iNS_08 = iNS_08;
	}
	public String getiNS_08_code_FT() {
		return iNS_08_code_FT;
	}
	public void setiNS_08_code_FT(String iNS_08_code_FT) {
		this.iNS_08_code_FT = iNS_08_code_FT;
	}
	public String getiNS_10_description() {
		return iNS_10_description;
	}
	public void setiNS_10_description(String iNS_10_description) {
		this.iNS_10_description = iNS_10_description;
	}
	public String getiNS_10() {
		return iNS_10;
	}
	public void setiNS_10(String iNS_10) {
		this.iNS_10 = iNS_10;
	}
	public String getiNS_10_code_N() {
		return iNS_10_code_N;
	}
	public void setiNS_10_code_N(String iNS_10_code_N) {
		this.iNS_10_code_N = iNS_10_code_N;
	}
	public String getrEF() {
		return rEF;
	}
	public void setrEF(String rEF) {
		this.rEF = rEF;
	}
	public String getrEF_01_description() {
		return rEF_01_description;
	}
	public void setrEF_01_description(String rEF_01_description) {
		this.rEF_01_description = rEF_01_description;
	}
	public String getrEF_01() {
		return rEF_01;
	}
	public void setrEF_01(String rEF_01) {
		this.rEF_01 = rEF_01;
	}
	public String getrEF_01_code_0F() {
		return rEF_01_code_0F;
	}
	public void setrEF_01_code_0F(String rEF_01_code_0F) {
		this.rEF_01_code_0F = rEF_01_code_0F;
	}
	public String getrEF_02_description() {
		return rEF_02_description;
	}
	public void setrEF_02_description(String rEF_02_description) {
		this.rEF_02_description = rEF_02_description;
	}
	public String getrEF_02() {
		return rEF_02;
	}
	public void setrEF_02(String rEF_02) {
		this.rEF_02 = rEF_02;
	}
	public String getrEF_01_code_3H() {
		return rEF_01_code_3H;
	}
	public void setrEF_01_code_3H(String rEF_01_code_3H) {
		this.rEF_01_code_3H = rEF_01_code_3H;
	}
	public String getdTP() {
		return dTP;
	}
	public void setdTP(String dTP) {
		this.dTP = dTP;
	}
	public String getdTP_01_description() {
		return dTP_01_description;
	}
	public void setdTP_01_description(String dTP_01_description) {
		this.dTP_01_description = dTP_01_description;
	}
	public String getdTP_01() {
		return dTP_01;
	}
	public void setdTP_01(String dTP_01) {
		this.dTP_01 = dTP_01;
	}
	public String getdTP_01_code_300() {
		return dTP_01_code_300;
	}
	public void setdTP_01_code_300(String dTP_01_code_300) {
		this.dTP_01_code_300 = dTP_01_code_300;
	}
	public String getdTP_02_description() {
		return dTP_02_description;
	}
	public void setdTP_02_description(String dTP_02_description) {
		this.dTP_02_description = dTP_02_description;
	}
	public String getdTP_02() {
		return dTP_02;
	}
	public void setdTP_02(String dTP_02) {
		this.dTP_02 = dTP_02;
	}
	public String getdTP_02_code_D8() {
		return dTP_02_code_D8;
	}
	public void setdTP_02_code_D8(String dTP_02_code_D8) {
		this.dTP_02_code_D8 = dTP_02_code_D8;
	}
	public String getdTP_03_description() {
		return dTP_03_description;
	}
	public void setdTP_03_description(String dTP_03_description) {
		this.dTP_03_description = dTP_03_description;
	}
	public String getdTP_03() {
		return dTP_03;
	}
	public void setdTP_03(String dTP_03) {
		this.dTP_03 = dTP_03;
	}
	public String getdTP_01_code_473() {
		return dTP_01_code_473;
	}
	public void setdTP_01_code_473(String dTP_01_code_473) {
		this.dTP_01_code_473 = dTP_01_code_473;
	}
	public List<NM12100Loop> get_2100_loop() {
		return _2100_loop;
	}
	public void set_2100_loop(List<NM12100Loop> _2100_loop) {
		this._2100_loop = _2100_loop;
	}
    
    
    
    
}

