package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ����REST�ӿڷ��������id��������ڴ�������
 * 
 * @author mkf3563
 * 
 */
public @XmlRootElement(name = "vmmem")
@XmlAccessorType(XmlAccessType.FIELD)
class VmMemoryRate implements Serializable {

	private static final long serialVersionUID = 1L;

	/** �����id */
	private String id;

	/** �ڴ������� */
	private String usage;

    /** ʱ��� **/
    private String time;

	public VmMemoryRate() {
	}

	public VmMemoryRate(String id, String us, String time) {
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
		return "VmMemoryRate [id=" + id + ", usage=" + usage + ", time=" + time
				+ "]";
	}

}