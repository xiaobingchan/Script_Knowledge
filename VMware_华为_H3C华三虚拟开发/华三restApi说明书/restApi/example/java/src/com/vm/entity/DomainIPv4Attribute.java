package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * �����IPv4��������
 * @author root
 *
 */
@XmlRootElement(name = "ipv4Attribute")
@XmlAccessorType(XmlAccessType.FIELD)
public class DomainIPv4Attribute implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	*������Ψһ��ַMAC
	*/
    private String mac;
    /**
     * �����󶨵�IP��Ϣ������һ���������IP�����
     */
    @XmlElement(name = "ipv4")
    private List<IPv4Config> ipv4s;

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public List<IPv4Config> getIpv4s() {
		return ipv4s;
	}

	public void setIpv4(List<IPv4Config> ipv4s) {
		this.ipv4s = ipv4s;
	}
    
}
