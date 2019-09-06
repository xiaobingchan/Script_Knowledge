package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ����REST�ӿڷ��������id������� ���������� 
 * 
 * @author mkf3563
 * 
 */
public @XmlRootElement(name = "vmnetio")
@XmlAccessorType(XmlAccessType.FIELD)
class VmNetIoStat implements Serializable {

	private static final long serialVersionUID = 1L;

	/** �����id */
	private String id;

	/** ���������� */
	private String rate;

    /** ʱ��� **/
    private String time;
	
	public VmNetIoStat() {
	}

	public VmNetIoStat(String id, String rate, String time) {
	    this.setId(id);
	    this.setRate(rate);
	    this.setTime(time);
	}

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getRate() {
        return rate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

	@Override
	public String toString() {
		return "VmNetIoStat [id=" + id + ", rate=" + rate + ", time=" + time
				+ "]";
	}

}