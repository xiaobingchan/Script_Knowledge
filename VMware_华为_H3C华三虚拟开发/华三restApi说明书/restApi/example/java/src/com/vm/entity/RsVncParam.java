/*
 * Copyright (c) 2007-2010, Hangzhou H3C Technologies Co., Ltd. All rights reserved.
 * <http://www.h3c.com/>
 *------------------------------------------------------------------------------
 * Product     : 
 * Module Name :
 * Date Created: 
 * Creator     : z01500
 * Description :
 *
 *------------------------------------------------------------------------------
 * Modification History
 * DATE        NAME             DESCRIPTION
 *------------------------------------------------------------------------------
 * 
 *
 *------------------------------------------------------------------------------
 */

package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services 接口返回的VNC参数配置类。
 *
 * @author l10191
 */
@XmlRootElement(name="vnc")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsVncParam implements Serializable {
	
    
    /**
     * add description of field here
     */
    private static final long serialVersionUID = 1L;
    /** 控制台IP */
    private String ip;
    
    /** 控制台端口 */
    private Integer port;
    
    /** 控制台密码 */
    private String password;
    
    /** token */
    private String token;
    
    /** ip */
    private String proxyIp;
    
    private String status;
    
    private Boolean localCursor;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getProxyIp() {
		return proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getLocalCursor() {
		return localCursor;
	}

	public void setLocalCursor(Boolean localCursor) {
		this.localCursor = localCursor;
	}

}
