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
 * Restful Web Services �ӿڷ��ص����⽻�����˿���������ϸ��Ϣʵ���ࡣ
 *
 * @author z01500
 */
@XmlRootElement(name="trafficInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsVPortTrafficInfo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3104721303797101168L;

    /** ���⽻�������ơ� **/
    private String vsName;
    
    /** ����˿����ơ� **/
    private String vPortName;
    
    /** ����˿�״̬�� **/
    private Integer state;

    /** ��������ơ� **/
    private String vmName;
    
    /** �������ʾ���ơ� **/
    private String title;

    /** �����MAC��ַ�� **/
    private String vmMac;
    
    /** ���ձ�������*/
	private Long rxPkts;
	
	/** �����ֽ����� */
	private Long rxBytes;
	/** ���մ������	 */
	private Long rxErrs;
	
	/** ���ͱ������� */
	private Long txPkts;
	/** �����ֽ����� 	 */
	private Long txBytes;
	/** ���ʹ������  */
	private Long txErrs;
	
	public String getVsName() {
		return vsName;
	}
	public void setVsName(String vsName) {
		this.vsName = vsName;
	}
	public String getVmName() {
		return vmName;
	}
	public void setVmName(String vmName) {
		this.vmName = vmName;
	}
	
	public String getVPortName() {
		return vPortName;
	}
	public void setVPortName(String vPortName) {
		this.vPortName = vPortName;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getRxPkts() {
		return rxPkts;
	}
	public void setRxPkts(Long rxPkts) {
		this.rxPkts = rxPkts;
	}
	public Long getRxBytes() {
		return rxBytes;
	}
	public void setRxBytes(Long rxBytes) {
		this.rxBytes = rxBytes;
	}
	public Long getRxErrs() {
		return rxErrs;
	}
	public void setRxErrs(Long rxErrs) {
		this.rxErrs = rxErrs;
	}
	public Long getTxPkts() {
		return txPkts;
	}
	public void setTxPkts(Long txPkts) {
		this.txPkts = txPkts;
	}
	public Long getTxBytes() {
		return txBytes;
	}
	public void setTxBytes(Long txBytes) {
		this.txBytes = txBytes;
	}
	public Long getTxErrs() {
		return txErrs;
	}
	public void setTxErrs(Long txErrs) {
		this.txErrs = txErrs;
	}
	public String getVmMac() {
		return vmMac;
	}
	public void setVmMac(String vmMac) {
		this.vmMac = vmMac;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}

	
}
