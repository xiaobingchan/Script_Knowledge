package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="storage")
public class Storage implements Serializable{
	private static final long serialVersionUID = -4664187819425616852L;
	private String deviceName;
	private String storeFile;
	private Long capacity;
	private String device;
	private String diskDevice;
	private String src;
	private String dest;
	private String pool;

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getStoreFile() {
		return storeFile;
	}

	public void setStoreFile(String storeFile) {
		this.storeFile = storeFile;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}

	public String getDiskDevice() {
		return diskDevice;
	}

	public void setDiskDevice(String diskDevice) {
		this.diskDevice = diskDevice;
	}

	
	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getPool() {
		return pool;
	}

	public void setPool(String pool) {
		this.pool = pool;
	}

	@Override
	public String toString() {
		return "Storage [deviceName=" + deviceName + ", storeFile=" + storeFile
				+ ", capacity=" + capacity + ", device=" + device
				+ ", diskDevice=" + diskDevice + "]";
	}
}
