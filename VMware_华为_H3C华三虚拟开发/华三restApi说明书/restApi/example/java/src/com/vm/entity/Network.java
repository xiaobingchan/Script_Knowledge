package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name = "network")
public class Network implements Serializable{
	private static final long serialVersionUID = -6206421390687742409L;
	private String mac;
	private String vsName;
	private Integer vlan;
	private Boolean isLimitInBound;
	private Long inAvgBandwidth;
	private Long inPeakBandwidth;
	private Long inBurst;
	private Boolean isLimitOutBound;
	private Long outAvgBandwidth;
	private Long outPeakBandwidth;
	private Long outBurst;
	private String mode;
	private String key;
	private String value;
	public String getMac() {
		return mac;
	}
	public void setMac(String mac) {
		this.mac = mac;
	}
	public String getVsName() {
		return vsName;
	}
	public void setVsName(String vsName) {
		this.vsName = vsName;
	}
	public Integer getVlan() {
		return vlan;
	}
	public void setVlan(Integer vlan) {
		this.vlan = vlan;
	}
	public Boolean getIsLimitInBound() {
		return isLimitInBound;
	}
	public void setIsLimitInBound(Boolean isLimitInBound) {
		this.isLimitInBound = isLimitInBound;
	}
	public Long getInAvgBandwidth() {
		return inAvgBandwidth;
	}
	public void setInAvgBandwidth(Long inAvgBandwidth) {
		this.inAvgBandwidth = inAvgBandwidth;
	}
	public Long getInPeakBandwidth() {
		return inPeakBandwidth;
	}
	public void setInPeakBandwidth(Long inPeakBandwidth) {
		this.inPeakBandwidth = inPeakBandwidth;
	}
	public Long getInBurst() {
		return inBurst;
	}
	public void setInBurst(Long inBurst) {
		this.inBurst = inBurst;
	}
	public Boolean getIsLimitOutBound() {
		return isLimitOutBound;
	}
	public void setIsLimitOutBound(Boolean isLimitOutBound) {
		this.isLimitOutBound = isLimitOutBound;
	}
	public Long getOutAvgBandwidth() {
		return outAvgBandwidth;
	}
	public void setOutAvgBandwidth(Long outAvgBandwidth) {
		this.outAvgBandwidth = outAvgBandwidth;
	}
	public Long getOutPeakBandwidth() {
		return outPeakBandwidth;
	}
	public void setOutPeakBandwidth(Long outPeakBandwidth) {
		this.outPeakBandwidth = outPeakBandwidth;
	}
	public Long getOutBurst() {
		return outBurst;
	}
	public void setOutBurst(Long outBurst) {
		this.outBurst = outBurst;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
