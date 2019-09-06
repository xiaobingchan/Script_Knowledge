package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用于REST接口备份虚拟机XML转换
 * @author j08968
 */
@XmlRootElement(name = "vmBackUpParameter")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainForBackUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 85411368548088240L;
    
	/** 虚拟机id */
    private Long id;
    /** 备份模式：0：主机本地目录；1：远端服务器。 */
    private Integer storeMode = 0;
    /** 备份位置 */
    private String directory;
    /** 目的服务器IP地址 */
    private String targetAddr;
    /** 目的服务器用户名 */
    private String userName;
    /** 目的服务器密码 */
    private String password;
    /** 远端服务器类型：0：ftp方式，1：scp方式 */
    private Integer type;
    /** 是做全量备份还是增量备份标志位（0：全量，1：增量，2：差异备份）。 */
    private Integer backupType = 0;

	public Integer getBackupType() {
		return backupType;
	}
	public void setBackupType(Integer backupType) {
		this.backupType = backupType;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getStoreMode() {
		return storeMode;
	}
	public void setStoreMode(Integer storeMode) {
		this.storeMode = storeMode;
	}
	public String getDirectory() {
		return directory;
	}
	public void setDirectory(String directory) {
		this.directory = directory;
	}
	public String getTargetAddr() {
		return targetAddr;
	}
	public void setTargetAddr(String targetAddr) {
		this.targetAddr = targetAddr;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
}
