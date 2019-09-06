package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import com.vm.entity.RsCommList;

/**
 * Restful Web Services �ӿڷ��ص����������ʵ���ࡣ
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
	
	/** �����ID�� */
    private Long vmId = null;

	/** �������ơ� **/
    private String name;
    
    /** ������ʾ���ơ� **/
    private String snapshotTitle;
    
    /** ���������� **/
    private String desc;
    
    /** ��ǰ���ա� **/
    private boolean isCurr = false;
    
    /** �����ڴ棺true �����ڴ棻false ���ղ����ڴ档 **/
    private boolean snapMem = false;
    
    /** ��ͣ�������ڴ棺true ��ͣ����false ͣ���� **/
    private boolean noStopVirtSnapMem = false;
    
    /** ��������ճ�ʱʱ�� */
    private Integer snapshotTimeOut;
    
    /** ��������մ���ʱ�� */
    private String creationTime;
    
    /** �ӿ����б� **/
    private RsCommList<RsSnapshot> snapshots;
    
    /** Ҫ���յ��豸����ΪREST API���ӣ�*/
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
