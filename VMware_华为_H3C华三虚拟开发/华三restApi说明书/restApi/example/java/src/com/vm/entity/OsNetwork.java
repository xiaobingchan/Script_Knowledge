package com.vm.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "osNetwork")
@XmlAccessorType(XmlAccessType.FIELD)
public class OsNetwork {

	private String name;

	private String hardwareAddress;

	private List<IpAddress> ipAddresses;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getHardwareAddress() {
		return hardwareAddress;
	}
	public void setHardwareAddress(String hardwareAddress) {
		this.hardwareAddress = hardwareAddress;
	}
	public List<IpAddress> getIpAddresses() {
		return ipAddresses;
	}
	public void setIpAddresses(List<IpAddress> ipAddresses) {
		this.ipAddresses = ipAddresses;
	}
	
}
