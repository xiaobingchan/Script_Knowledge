/*
 * Copyright (c) 2007-2014, Hangzhou H3C Technologies Co., Ltd. All rights reserved.
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
 * Restful Web Services �ӿڷ��ص����������ʵ���ࡣ
 *
 * @author z01500
 */
@XmlRootElement(name="pNIC")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsPhysicalNIC implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5732555124697253492L;

    /** �������ơ� **/
    private String name;
    
    /** ������ **/
    private String description;

    /** ״̬��ȡֵ��  1-���0-�����**/
    private Integer status;
    
    /** IP��ַ�� **/
    private String address;
    
    /** �������롣 **/
    private String netmask;
    
    /** MAC��ַ��**/
    private String macAddr;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    
}
