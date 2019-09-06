package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "rsResult")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsResult implements Serializable{

    /**
     * add description of field here
     */
    private static final long serialVersionUID = 1L;

    private int state;
    
    private int errorCode;
    
    private String data;
    
    private String message;

    public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
