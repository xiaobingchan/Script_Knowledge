package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services ���������ϵͳ������Ϣ
 *
 * @author y09696
 */
@XmlRootElement(name="vmOsInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDeployVmOsInfo implements Serializable {
	private static final long serialVersionUID = -7232294768779520882L;
	/** 0:���ٳ�ʼ�� ��1��ȫ����ʼ��*/
	private Integer initType = 0;
	/** �������*/
	private String sysName = null;
	/**��/������  1���� 2�������� */
	private Integer regOrGroupType = 2;
	/** ��/��������*/
	private String regOrGroup;
	private String loginAccount;
	private String loginPassword;
	private String localgroup;
	private String productKey;
	/** ϵͳIP*/
	private String sysIp = null;
	private String sysMask;
	private String sysGateway;
	private String sysdns;
	private String secondaryDns;
	/** �Ƿ����ð�IP*/
	private Boolean isBindIp = false;
	
	
	public String getSysName() {
		return sysName;
	}
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}
	public String getRegOrGroup() {
		return regOrGroup;
	}
	public void setRegOrGroup(String regOrGroup) {
		this.regOrGroup = regOrGroup;
	}
	public String getLoginAccount() {
		return loginAccount;
	}
	public void setLoginAccount(String loginAccount) {
		this.loginAccount = loginAccount;
	}
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getLocalgroup() {
		return localgroup;
	}
	public void setLocalgroup(String localgroup) {
		this.localgroup = localgroup;
	}
	public String getProductKey() {
		return productKey;
	}
	public void setProductKey(String productKey) {
		this.productKey = productKey;
	}
	public String getSysIp() {
		return sysIp;
	}
	public void setSysIp(String sysIp) {
		this.sysIp = sysIp;
	}
	public String getSysMask() {
		return sysMask;
	}
	public void setSysMask(String sysMask) {
		this.sysMask = sysMask;
	}
	public String getSysGateway() {
		return sysGateway;
	}
	public void setSysGateway(String sysGateway) {
		this.sysGateway = sysGateway;
	}
	public String getSysdns() {
		return sysdns;
	}
	public void setSysdns(String sysdns) {
		this.sysdns = sysdns;
	}
	public String getSecondaryDns() {
		return secondaryDns;
	}
	public void setSecondaryDns(String secondaryDns) {
		this.secondaryDns = secondaryDns;
	}
	public Boolean getIsBindIp() {
		return isBindIp;
	}
	public void setIsBindIp(Boolean isBindIp) {
		this.isBindIp = isBindIp;
	}
	public Integer getInitType() {
		return initType;
	}
	public void setInitType(Integer initType) {
		this.initType = initType;
	}
	public Integer getRegOrGroupType() {
		return regOrGroupType;
	}
	public void setRegOrGroupType(Integer regOrGroupType) {
		this.regOrGroupType = regOrGroupType;
	}
	
}
