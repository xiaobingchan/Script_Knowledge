package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用于REST接口返回虚拟机id和虚拟机 网络吞吐量 
 * 
 * @author mkf3563
 * 
 */
public @XmlRootElement(name = "vmnetio")
@XmlAccessorType(XmlAccessType.FIELD)
class VmNetIoStat implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 虚拟机id */
	private String id;

	/** 网络吞吐量 */
	private String rate;

    /** 时间戳 **/
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