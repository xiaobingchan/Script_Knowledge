package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services 接口返回的共享文件系统Lun信息实体类。
 * @author j08968
 * 
 */
@XmlRootElement(name="rsFsLunInfo")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsFsLunInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** the ID */
	private Long id;
	
    /** 服务器IP地址**/
    private String targetHost;
    
    /** 服务器IP地址2**/
    private String targetHost2;

	/** NAA**/
    private String naa;
    
    /** naaName**/
    private String naaName;
    
    private String model;
	
	/** LUN**/
    private String lun;
	
	private Long capacity;
	
	private Long shareFileSystemId;
	
	private String hba;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTargetHost() {
		return targetHost;
	}

	public void setTargetHost(String targetHost) {
		this.targetHost = targetHost;
	}

	public String getNaa() {
		return naa;
	}

	public void setNaa(String naa) {
		this.naa = naa;
	}

	public String getLun() {
		return lun;
	}

	public void setLun(String lun) {
		this.lun = lun;
	}

	public Long getShareFileSystemId() {
		return shareFileSystemId;
	}

	public void setShareFileSystemId(Long shareFileSystemId) {
		this.shareFileSystemId = shareFileSystemId;
	}

	public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	public String getNaaName() {
		return naaName;
	}

	public void setNaaName(String naaName) {
		this.naaName = naaName;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	public String getTargetHost2() {
		return targetHost2;
	}

	public void setTargetHost2(String targetHost2) {
		this.targetHost2 = targetHost2;
	}

	public String getHba() {
		return hba;
	}

	public void setHba(String hba) {
		this.hba = hba;
	}
	
}