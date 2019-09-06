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
 * Restful Web Services 接口返回的虚拟交换机端口流量的详细信息实体类。
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

    /** 虚拟交换机名称。 **/
    private String vsName;
    
    /** 虚拟端口名称。 **/
    private String vPortName;
    
    /** 虚拟端口状态。 **/
    private Integer state;

    /** 虚拟机名称。 **/
    private String vmName;
    
    /** 虚拟机显示名称。 **/
    private String title;

    /** 虚拟机MAC地址。 **/
    private String vmMac;
    
    /** 接收报文数。*/
	private Long rxPkts;
	
	/** 接收字节数。 */
	private Long rxBytes;
	/** 接收错包数。	 */
	private Long rxErrs;
	
	/** 发送报文数。 */
	private Long txPkts;
	/** 发送字节数。 	 */
	private Long txBytes;
	/** 发送错包数。  */
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
