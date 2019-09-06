package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用于REST接口克隆虚拟机XML转换
 * @author j08968
 */
@XmlRootElement(name = "store")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsStore implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8588591823757774989L;
	
	private String src;
	private String dest;
	private String pool;
	
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
}
