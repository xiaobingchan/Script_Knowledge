package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用于REST接口返回虚拟机id和虚拟机IO吞吐量
 * 
 * @author mkf3563
 * 
 */
public @XmlRootElement(name = "vmio")
@XmlAccessorType(XmlAccessType.FIELD)
class VmIoStat implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 虚拟机id */
	private String id;

	/** io吞吐量 */
	private String rate;

    /** 时间戳 **/
    private String time;

	public VmIoStat() {
	}

	public VmIoStat(String id, String rate, String time) {
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
		return "VmIoStat [id=" + id + ", rate=" + rate + ", time=" + time + "]";
	}

}