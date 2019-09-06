package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "authKeys")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsAuthKeys  implements Serializable {
	private static final long serialVersionUID = 5553546342798321729L;
	private Long domainId;
	private String authKeys;
	public Long getDomainId() {
		return domainId;
	}
	public void setDomainId(Long domainId) {
		this.domainId = domainId;
	}
	public String getAuthKeys() {
		return authKeys;
	}
	public void setAuthKeys(String authKeys) {
		this.authKeys = authKeys;
	}
	
}
