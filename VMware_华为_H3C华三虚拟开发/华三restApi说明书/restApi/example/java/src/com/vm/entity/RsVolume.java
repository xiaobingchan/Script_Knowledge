package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * ����REST�ӿ�����XMLת��
 * @author j08968
 */
@XmlRootElement(name = "volAddParameter")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsVolume implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7125943669692590733L;

	/** ����id */
	private Long hostId;
	/** �洢������ */
	private String spName;
	/** �洢�ر��� */
	private String spTitle;
	/**�洢������ */
	private String volName;
	/**������� */
	private Long capacity;
	/**��ʽ */
	private String format;
	/**���������ļ� */
	private String baseFile;
	
	private String encryptPw;
	
	public Long getHostId() {
		return hostId;
	}
	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}
	public String getSpName() {
		return spName;
	}
	public void setSpName(String spName) {
		this.spName = spName;
	}
	public String getVolName() {
		return volName;
	}
	public void setVolName(String volName) {
		this.volName = volName;
	}
	public Long getCapacity() {
		return capacity;
	}
	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getBaseFile() {
		return baseFile;
	}
	public void setBaseFile(String baseFile) {
		this.baseFile = baseFile;
	}
	public String getSpTitle() {
		return spTitle;
	}
	public void setSpTitle(String spTitle) {
		this.spTitle = spTitle;
	}
	public String getEncryptPw() {
		return encryptPw;
	}
	public void setEncryptPw(String encryptPw) {
		this.encryptPw = encryptPw;
	}
	
}
