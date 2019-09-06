package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ����REST�ӿڱ��������XMLת��
 * @author j08968
 */
@XmlRootElement(name = "vmBackUpParameter")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainForBackUp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 85411368548088240L;
    
	/** �����id */
    private Long id;
    /** ����ģʽ��0����������Ŀ¼��1��Զ�˷������� */
    private Integer storeMode = 0;
    /** ����λ�� */
    private String directory;
    /** Ŀ�ķ�����IP��ַ */
    private String targetAddr;
    /** Ŀ�ķ������û��� */
    private String userName;
    /** Ŀ�ķ��������� */
    private String password;
    /** Զ�˷��������ͣ�0��ftp��ʽ��1��scp��ʽ */
    private Integer type;
    /** ����ȫ�����ݻ����������ݱ�־λ��0��ȫ����1��������2�����챸�ݣ��� */
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
