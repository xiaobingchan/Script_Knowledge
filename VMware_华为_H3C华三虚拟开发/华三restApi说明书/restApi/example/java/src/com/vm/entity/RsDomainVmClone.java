package com.vm.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="vmCloneParameter")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainVmClone {
	private long id;
	private long targetHostId;
	private String domainName;
	private String cloneMode;
	private String cloneType;
	private String diskFormat;
	private String title;
	
	@XmlElement(name = "network")
	private List<Network> networks;
	
	@XmlElement(name = "storage")
	private List<Storage> storages;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getTargetHostId() {
		return targetHostId;
	}

	public void setTargetHostId(long targetHostId) {
		this.targetHostId = targetHostId;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getCloneMode() {
		return cloneMode;
	}

	public void setCloneMode(String cloneMode) {
		this.cloneMode = cloneMode;
	}

	public String getCloneType() {
		return cloneType;
	}

	public void setCloneType(String cloneType) {
		this.cloneType = cloneType;
	}

	public String getDiskFormat() {
		return diskFormat;
	}

	public void setDiskFormat(String diskFormat) {
		this.diskFormat = diskFormat;
	}

	public List<Network> getNetworks() {
		return networks;
	}

	public void setNetworks(List<Network> networks) {
		this.networks = networks;
	}

	public List<Storage> getStorages() {
		return storages;
	}

	public void setStorages(List<Storage> storages) {
		this.storages = storages;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
