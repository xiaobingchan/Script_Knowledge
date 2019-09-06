package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.vm.entity.RsCommList;

/**
 * Restful Web Services 接口返回的虚拟机快照实体类。
 *
 * @author z01500
 */
@XmlRootElement(name="snapshot")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsSnapshot implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8181077704267079260L;
	
	/** 虚拟机ID。 */
    private Long vmId = null;

	/** 快照名称。 **/
    private String name;
    
    /** 快照显示名称。 **/
    private String snapshotTitle;
    
    /** 快照描述。 **/
    private String desc;
    
    /** 当前快照。 **/
    private boolean isCurr = false;
    
    /** 快照内存：true 快照内存；false 快照不带内存。 **/
    private boolean snapMem = false;
    
    /** 不停机快照内存：true 不停机；false 停机。 **/
    private boolean noStopVirtSnapMem = false;
    
    /** 虚拟机快照超时时间 */
    private Integer snapshotTimeOut;
    
    /** 虚拟机快照创建时间 */
    private String creationTime;
    
    /** 子快照列表。 **/
    private RsCommList<RsSnapshot> snapshots;
    
    /** 要快照的设备名（为REST API增加）*/
    private String device;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public RsCommList<RsSnapshot>  getSnapshots() {
		return snapshots;
	}

	public void setSnapshots(RsCommList<RsSnapshot> snapshots) {
		this.snapshots = snapshots;
	}

	public boolean isCurr() {
		return isCurr;
	}

	public void setCurr(boolean isCurr) {
		this.isCurr = isCurr;
	}

	public Long getVmId() {
		return vmId;
	}

	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}

	public boolean isSnapMem() {
        return snapMem;
    }

    public void setSnapMem(boolean snapMem) {
        this.snapMem = snapMem;
    }

    public boolean isNoStopVirtSnapMem() {
        return noStopVirtSnapMem;
    }

    public void setNoStopVirtSnapMem(boolean noStopVirtSnapMem) {
        this.noStopVirtSnapMem = noStopVirtSnapMem;
    }

    public Integer getSnapshotTimeOut() {
        return snapshotTimeOut;
    }

    public void setSnapshotTimeOut(Integer snapshotTimeOut) {
        this.snapshotTimeOut = snapshotTimeOut;
    }

    public void setDevice(String device) {
        this.device = device;
    }

    public String getDevice() {
        return device;
    }

	public String getSnapshotTitle() {
		return snapshotTitle;
	}

	public void setSnapshotTitle(String snapshotTitle) {
		this.snapshotTitle = snapshotTitle;
	}

	public String getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(String creationTime) {
		this.creationTime = creationTime;
	}
	
}
