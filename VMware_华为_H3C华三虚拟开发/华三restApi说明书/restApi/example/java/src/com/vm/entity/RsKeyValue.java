package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "keyValue")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsKeyValue implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**参数名称。*/
	private String key;
	
	/**参数值。*/
	private String value;
	
	/** 参数值别名。*/
	private String alias;

	public RsKeyValue() {
		super();
	}

	public RsKeyValue(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
	
}
