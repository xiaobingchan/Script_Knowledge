/*
 * Copyright (c) 2007-2010, Hangzhou H3C Technologies Co., Ltd. All rights reserved.
 * <http://www.h3c.com/>
 *------------------------------------------------------------------------------
 * Product     : 
 * Module Name :
 * Date Created: 
 * Creator     : z01500
 * Description :
 *
 *------------------------------------------------------------------------------
 * Modification History
 * DATE        NAME             DESCRIPTION
 *------------------------------------------------------------------------------
 * 
 *
 *------------------------------------------------------------------------------
 */

package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services �ӿڷ��ص������������Ϣʵ���ࡣ
 * 
 * @author z01500
 */
@XmlRootElement(name = "network")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainNetwork implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2123951666312182862L;

	/** ��¼ID�� */
	private Long id = null;

	/** �������ͣ�0�Ž� 1NAT 2��ʹ������ 3����ֱͨ����  4SR-IOV������ **/
	private Integer netType;

	/** ����MAC��ַ�� **/
	private String mac;

	/** ����IP��ַ�� **/
	private String ipAddr;

	/** �������롣 **/
    private String maskAddr;
    
    private String gateway;
    private String dns;
    private String secondDns;
	/** ���⽻����Id�� **/
	private Long vsId;

	/** ���⽻�������ơ� **/
	private String vsName;
	
	/** �����⽻����ת��ģʽ��0 : veb; 1: vepa; 2:��ͨ�� ; 3:�ֲ�ʽ**/
	private Integer vsMode;

	/** ������ģ�ͣ�ȡֵΪ:��rtl8139��virtio��e1000�� ȱʡΪrtl8139�� **/
	private String deviceModel;

	/** �ں˼��٣� ������������Ϊ virtoIoʱ��Ч 1:���٣�0���� */
	private Integer isKernelAccelerated;

	/** ��vlan id�� **/
	private Integer vlan;

	private boolean isLimitInBound;

	/** ���뷽��ƽ������ **/
	private Long inAvgBandwidth;

	/** ���뷽���ֵ���� **/
	private Long inPeakBandwidth;

	/** ���뷽��ͻ�������С�� **/
	private Long inBurst;

	private boolean isLimitOutBound;

	/** ��������ƽ������ **/
	private Long outAvgBandwidth;

	/** ���������ֵ���� **/
	private Long outPeakBandwidth;

	/** ��������ͻ�������С�� **/
	private Long outBurst;

	/** �����������ת��ģʽ��**/
	private String mode;

	/** VSI����ID�� **/
	private String vsiMngId;

	/** VSI����ID�� **/
	private String vsiTypeId;

	/** VSI���Ͱ汾�� **/
	private String vsiTypeVer;

	/** VSI ID��ʽ�� **/
	private String vsiIdFormat;
	
	/** ������Դ�� **/
	private String vsiNetResId;

	/** VSI ID�� **/
	private String vsiId;

	/** �������ģ��ID */
	private Long profileId;
	
	/** �������ģ�����ơ�  */
	private String profileName;

	/** �Ƿ�������vport �� ������vport���Ը���Ϊ��ͨ�����⽻���� */
	private boolean virtualport;

    /** ��������ֱ������SR-IOVֱ���ĵ�ַ */
    private String address;
    
    /** vcf����ģ��UUID */
    private String profileUUID;
    /** vcf��ȫ����UUID */
    private String securityUUID;
    /** vcf������ԴUUID */
    private String netUUID;

	public String getVsiNetResId() {
		return vsiNetResId;
	}

	public void setVsiNetResId(String vsiNetResId) {
		this.vsiNetResId = vsiNetResId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getNetType() {
		return netType;
	}

	public void setNetType(Integer netType) {
		this.netType = netType;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getDeviceModel() {
		return deviceModel;
	}

	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}

	public void setKernelAccelerated(Integer isKernelAccelerated) {
		this.isKernelAccelerated = isKernelAccelerated;
	}

	public Integer getIsKernelAccelerated() {
		return isKernelAccelerated;
	}

	public Integer getVlan() {
		return vlan;
	}

	public void setVlan(Integer vlan) {
		this.vlan = vlan;
	}

	public boolean isLimitInBound() {
		return isLimitInBound;
	}

	public void setLimitInBound(boolean isLimitInBound) {
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

	public boolean isLimitOutBound() {
		return isLimitOutBound;
	}

	public void setLimitOutBound(boolean isLimitOutBound) {
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

	public String getVsiMngId() {
		return vsiMngId;
	}

	public void setVsiMngId(String vsiMngId) {
		this.vsiMngId = vsiMngId;
	}

	public String getVsiTypeId() {
		return vsiTypeId;
	}

	public void setVsiTypeId(String vsiTypeId) {
		this.vsiTypeId = vsiTypeId;
	}

	public String getVsiTypeVer() {
		return vsiTypeVer;
	}

	public void setVsiTypeVer(String vsiTypeVer) {
		this.vsiTypeVer = vsiTypeVer;
	}

	public String getVsiIdFormat() {
		return vsiIdFormat;
	}

	public void setVsiIdFormat(String vsiIdFormat) {
		this.vsiIdFormat = vsiIdFormat;
	}

	public String getVsiId() {
		return vsiId;
	}

	public void setVsiId(String vsiId) {
		this.vsiId = vsiId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getVsId() {
		return vsId;
	}

	public void setVsId(Long vsId) {
		this.vsId = vsId;
	}

	public String getVsName() {
		return vsName;
	}

	public void setVsName(String vsName) {
		this.vsName = vsName;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public String getMaskAddr() {
		return maskAddr;
	}

	public void setMaskAddr(String maskAddr) {
		this.maskAddr = maskAddr;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public String getDns() {
		return dns;
	}

	public void setDns(String dns) {
		this.dns = dns;
	}

	public String getSecondDns() {
		return secondDns;
	}

	public void setSecondDns(String secondDns) {
		this.secondDns = secondDns;
	}

	@Override
	public String toString() {
		return String.format("VmNetwork {id=%d, mac=%s}", id, mac);
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setVirtualport(boolean virtualport) {
		this.virtualport = virtualport;
	}

	public boolean isVirtualport() {
		return virtualport;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public String getAddress() {
		return address;
	}

	public String getProfileUUID() {
		return profileUUID;
	}

	public void setProfileUUID(String profileUUID) {
		this.profileUUID = profileUUID;
	}

	public String getSecurityUUID() {
		return securityUUID;
	}

	public void setSecurityUUID(String securityUUID) {
		this.securityUUID = securityUUID;
	}

	public String getNetUUID() {
		return netUUID;
	}

	public void setNetUUID(String netUUID) {
		this.netUUID = netUUID;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getVsMode() {
		return vsMode;
	}

	public void setVsMode(Integer vsMode) {
		this.vsMode = vsMode;
	}

}
