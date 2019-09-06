/*
 * Copyright (c) 20012, Hangzhou H3C Technologies Co., Ltd. All rights reserved.
 * <http://www.h3c.com/>
 *------------------------------------------------------------------------------
 * Product     : 
 * Module Name :
 * Date Created: 
 * Creator     : ֣�ۿ�/z01500
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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services �ӿڷ��ص����������ʵ���ࡣ
 *
 * @author z01500
 */
@XmlRootElement(name="host")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsHost implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5732555124697253492L;
	
	/** ���绽�� �� */
	public static final int WEAK_UP_ON_LAN = 0;
	/** IPMI���� �� */
	public static final int WEAK_UP_ON_IPMI = 1;

	/** �����ID�� */
    private Long id = null;
    /** ���������û�����*/
    private String user;
    /** �����������롣*/
    private String pwd;
    
    /** �����ػ�ID�� */
    private Long hostPoolId = null;
    
    /** ��ȺID�� */
    private Long clusterId = null;
    
    /** ��������ơ� **/
    private String name;
    
    /** ������֧�ֵ�Hypervisor���ͣ��硰kvm������xen������esx���� **/
    @XmlAttribute
    private String type;
    
    /** ������IP��ַ�� **/
    private String ip;
    
    /** �������ͺš� **/
    private String model;

    /** �����������̡� **/
    private String vendor;
    
    /** CPU������32λ�������ͣ�int�� **/
    private Integer cpuCount;
    
    /** CPU�ͺš� **/
    private String cpuModel;
    
    /** CPUƵ�ʣ���λΪMHz��**/
    private Integer cpuFrequence;
    
    /** ���̴�С����λΪMB�� **/
    private Long diskSize;
    
    /** �ڴ��С����λΪMB�� **/
    private Long memorySize;
    
    /** CPU���� ����*���� */
    private String cpuNum;
    
    /** ����������� �����ر���Ϣ*/
    private Integer vmNum;
    private Integer vmRunCount;
    private Integer vmShutoff;
    
    /** CPU������*/
    private Double cpuRate;
    /** �ڴ�������*/
    private Double memRate;
    /** ״̬ */
    private Integer status;
    /** ����ʱ��*/
    private String runTime;
    /** �����汾*/
    private String version;
    
    /** CPUƵ�ʣ���λΪGHz������ǰ̨��ʾ��Ҫ��Ϣ**/
    private String cpuFrequenceGhz;
    
    /** �ڴ��С ����λ ����ǰ̨��ʾ��Ҫ��Ϣ*/
    private String memory;
    /** ���̴�С ����λ ����ǰ̨��ʾ��Ҫ��Ϣ*/
    private String storage;
    
    /** HA��ʶ*/
    private Integer enableHA;
    /** �Ƿ�����ظ�����*/
    private Boolean ignore;
    /** ���������ε�ַ*/
    private String netAddr;
    /**�鲥��ַ*/
    private String mcNetAddr;
    /**�˿�*/
    private String mcPort;
    /** cpu ����*/
    private Integer cpuSockets;
    
    /**CPU������С����*/
    private Integer cpuMinRate;
    /**CPU�����������*/
    private Integer cpuMaxRate;
    /**������������������*/
    private String hostPool;
    
    private String platForm;
    
    private Integer wakeCategory = null;
    
    private String ipmiIpaddr = null;
    
    private String ipmiUser = null;
    
    private String ipmiPw = null;
    
	/** ���������б� **/
    @XmlElement(name = "pNIC")
    private List<RsPhysicalNIC> pnicList;
    
    /** ����ά��ģʽ�ķ�ʽ */
    private Integer maintainMode;
    
    private Integer haEnable;
    
    public String getPlatForm() {
		return platForm;
	}
	public void setPlatForm(String platForm) {
		this.platForm = platForm;
	}
    
	public Integer getCpuSockets() {
		return cpuSockets;
	}
	public void setCpuSockets(Integer cpuSockets) {
		this.cpuSockets = cpuSockets;
	}
	public String getNetAddr() {
		return netAddr;
	}
	public void setNetAddr(String netAddr) {
		this.netAddr = netAddr;
	}
	public String getMcNetAddr() {
		return mcNetAddr;
	}
	public void setMcNetAddr(String mcNetAddr) {
		this.mcNetAddr = mcNetAddr;
	}
	public String getMcPort() {
		return mcPort;
	}
	public void setMcPort(String mcPort) {
		this.mcPort = mcPort;
	}
    
    public Integer getEnableHA() {
		return enableHA;
	}
	public void setEnableHA(Integer enableHA) {
		this.enableHA = enableHA;
	}

	public Boolean getIgnore() {
		return ignore;
	}

	public void setIgnore(Boolean ignore) {
		this.ignore = ignore;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getMemory() {
		return memory;
	}

	public void setMemory(String memory) {
		this.memory = memory;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}
    
	public String getCpuFrequenceGhz() {
		return cpuFrequenceGhz;
	}

	public void setCpuFrequenceGhz(String cpuFrequenceGhz) {
		this.cpuFrequenceGhz = cpuFrequenceGhz;
	}

	public String getCpuNum() {
		return cpuNum;
	}

	public void setCpuNum(String cpuNum) {
		this.cpuNum = cpuNum;
	}

	public Integer getVmNum() {
		return vmNum;
	}

	public void setVmNum(Integer vmNum) {
		this.vmNum = vmNum;
	}

	public Integer getVmRunCount() {
		return vmRunCount;
	}

	public void setVmRunCount(Integer vmRunCount) {
		this.vmRunCount = vmRunCount;
	}

	public Integer getVmShutoff() {
		return vmShutoff;
	}

	public void setVmShutoff(Integer vmShutoff) {
		this.vmShutoff = vmShutoff;
	}

	public Double getCpuRate() {
		return cpuRate;
	}

	public void setCpuRate(Double cpuRate) {
		this.cpuRate = cpuRate;
	}

	public Double getMemRate() {
		return memRate;
	}

	public void setMemRate(Double memRate) {
		this.memRate = memRate;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getRunTime() {
		return runTime;
	}

	public void setRunTime(String runTime) {
		this.runTime = runTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public Integer getCpuCount() {
		return cpuCount;
	}

	public void setCpuCount(Integer cpuCount) {
		this.cpuCount = cpuCount;
	}

	public String getCpuModel() {
		return cpuModel;
	}

	public void setCpuModel(String cpuModel) {
		this.cpuModel = cpuModel;
	}

	public Integer getCpuFrequence() {
		return cpuFrequence;
	}

	public void setCpuFrequence(Integer cpuFrequence) {
		this.cpuFrequence = cpuFrequence;
	}

	public Long getDiskSize() {
		return diskSize;
	}

	public void setDiskSize(Long diskSize) {
		this.diskSize = diskSize;
	}

	public Long getMemorySize() {
		return memorySize;
	}

	public void setMemorySize(Long memorySize) {
		this.memorySize = memorySize;
	}

	public List<RsPhysicalNIC> getPnicList() {
		return pnicList;
	}

	public void setPnicList(List<RsPhysicalNIC> pnicList) {
		this.pnicList = pnicList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getHostPoolId() {
		return hostPoolId;
	}

	public void setHostPoolId(Long hostPoolId) {
		this.hostPoolId = hostPoolId;
	}

	public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}
	public Integer getCpuMinRate() {
		return cpuMinRate;
	}
	public void setCpuMinRate(Integer cpuMinRate) {
		this.cpuMinRate = cpuMinRate;
	}
	public Integer getCpuMaxRate() {
		return cpuMaxRate;
	}
	public void setCpuMaxRate(Integer cpuMaxRate) {
		this.cpuMaxRate = cpuMaxRate;
	}
	
	public Integer getWakeCategory() {
		return wakeCategory;
	}
	public void setWakeCategory(Integer wakeCategory) {
		this.wakeCategory = wakeCategory;
	}
	public String getIpmiIpaddr() {
		return ipmiIpaddr;
	}
	public void setIpmiIpaddr(String ipmiIpaddr) {
		this.ipmiIpaddr = ipmiIpaddr;
	}
	public String getIpmiUser() {
		return ipmiUser;
	}
	public void setIpmiUser(String ipmiUser) {
		this.ipmiUser = ipmiUser;
	}
	public String getIpmiPw() {
		return ipmiPw;
	}
	public void setIpmiPw(String ipmiPw) {
		this.ipmiPw = ipmiPw;
	}
	public String getHostPool() {
		return hostPool;
	}
	public void setHostPool(String hostPool) {
		this.hostPool = hostPool;
	}
	
	
	public void setMaintainMode(Integer maintainMode) {
		this.maintainMode = maintainMode;
	}
	public Integer getMaintainMode() {
		return maintainMode;
	}
	public Integer getHaEnable() {
		return haEnable;
	}
	public void setHaEnable(Integer haEnable) {
		this.haEnable = haEnable;
	} 
}