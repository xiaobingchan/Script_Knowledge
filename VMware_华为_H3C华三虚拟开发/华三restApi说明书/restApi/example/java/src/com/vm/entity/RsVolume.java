package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 用于REST接口增加XML转换
 * @author j08968
 */
@XmlRootElement(name = "volAddParameter")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsVolume implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7125943669692590733L;

	/** 主机id */
	private Long hostId;
	/** 存储池名称 */
	private String spName;
	/** 存储池别名 */
	private String spTitle;
	/**存储卷名称 */
	private String volName;
	/**最大容量 */
	private Long capacity;
	/**格式 */
	private String format;
	/**基础镜像文件 */
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
