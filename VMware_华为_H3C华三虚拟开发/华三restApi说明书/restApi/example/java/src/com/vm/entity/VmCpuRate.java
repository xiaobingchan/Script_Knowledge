package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用于REST接口返回虚拟机id和虚拟机cpu利用率
 * 
 * @author mkf3563
 * 
 */
public @XmlRootElement(name = "vmcpu")
@XmlAccessorType(XmlAccessType.FIELD)
class VmCpuRate implements Serializable {

	private static final long serialVersionUID = 1L;

	/** 虚拟机id */
	private String id;

	/** 虚拟机cpu利用率 */
	private String usage;
	
	/** 时间戳 **/
	private String time;

	public VmCpuRate() {
	}

	public VmCpuRate(String id, String us, String time) {
		this.setId(id);
		this.setUsage(us);
		this.setTime(time);
	}

	public void setUsage(String usage) {
		this.usage = usage;
	}

	public String getUsage() {
		return usage;
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
		return "VmCpuRate [id=" + id + ", usage=" + usage + ", time=" + time
				+ "]";
	}

}