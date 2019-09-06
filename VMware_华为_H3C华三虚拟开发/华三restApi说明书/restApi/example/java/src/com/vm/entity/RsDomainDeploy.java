package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * ����REST�ӿ�ģ�岿�������
 * @author j09585
 */
@XmlRootElement(name = "domainConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainDeploy implements Serializable {
	private static final long serialVersionUID = 8721819263959341335L;

	/** ģ��ID*/
    private Long id;
    /** ������������� **/
    private String domainName;
    /**����ʾ����*/
    private String title;
    /** ���� */
    private String desc;
    
    /** Ŀ������id */
    private Long targetHostId;
    /** Ŀ�ļ�Ⱥid������Ⱥ����ʹ�á�*/
    private Long targetClusterId;
    /** 1:���ٲ���,0:�������� */
    private int deployMode = 0;
    /** 1:����ģʽ��0:�Ǳ���ģʽ*/
    private int protectMode = 0;
    /** CPU��Ϣ*/
    private Integer cpuSocket = null;
    private Integer cpuCore = null;
    /** �ڴ���Ϣ*/
    private Integer memory = null;
    /** �ڴ���ʾ��Ϣ*/
    private Double memoryInit = null;
    /** �ڴ浥λ */
    private String memoryUnit = null;
    /** Ŀ�������洢������,��֯����ʱ�á�*/
    private String poolName = null;
    /** ����ʱ����������������� */
    private List<String> poolNames = null;
    /** Ŀ�����⽻�������֣���֯����ʱʹ�á�*/
    private List<String> vswitchNames = null;
    /** Ŀ��������洢 ��Ϣ*/
    @XmlElement(name = "storage")
    private List<RsStore> storages;
    /** Ŀ������� ������Ϣ*/
    @XmlElement(name = "network")
    private List<RsDomainSwitch> networks;
    /** Ŀ�Ĺ���洢�� */
    @XmlElement(name= "sharePool")
    private List<RsShareFileSystem> sharePools;
    
	/** raw,qcow2*/
    private String diskFormat;
    /** ϵͳ������Ϣ */
    private RsDeployVmOsInfo osInfo = null;
    
    /** the vmNum should be deploied */
    private int vmNum;
    /** 1����ʾ��ģ���б��в���2����ʾ����֯�ڲ��� */
    private Integer deployType = 0;
    
    /**
	 * �û�����,�����û����ƽű�/�����
	 */
	private String userData;
    public Integer getDeployType() {
		return deployType;
	}
	public void setDeployType(Integer deployType) {
		this.deployType = deployType;
	}
    public int getVmNum() {
		return vmNum;
	}
	public void setVmNum(int vmNum) {
		this.vmNum = vmNum;
	}
    
    public Double getMemoryInit() {
		return memoryInit;
	}
	public void setMemoryInit(Double memoryInit) {
		this.memoryInit = memoryInit;
	}
	public String getMemoryUnit() {
		return memoryUnit;
	}
	public void setMemoryUnit(String memoryUnit) {
		this.memoryUnit = memoryUnit;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTargetHostId() {
		return targetHostId;
	}
	public void setTargetHostId(Long targetHostId) {
		this.targetHostId = targetHostId;
	}
	public List<RsStore> getStorages() {
		return storages;
	}
	public void setStorages(List<RsStore> storages) {
		this.storages = storages;
	}
	public List<RsDomainSwitch> getNetworks() {
		return networks;
	}
	public void setNetworks(List<RsDomainSwitch> networks) {
		this.networks = networks;
	}
	public String getDiskFormat() {
		return diskFormat;
	}
	public void setDiskFormat(String diskFormat) {
		this.diskFormat = diskFormat;
	}
	public String getDomainName() {
		return domainName;
	}
	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public int getDeployMode() {
		return deployMode;
	}
	public void setDeployMode(int deployMode) {
		this.deployMode = deployMode;
	}
	public Integer getCpuSocket() {
		return cpuSocket;
	}
	public void setCpuSocket(Integer cpuSocket) {
		this.cpuSocket = cpuSocket;
	}
	public Integer getCpuCore() {
		return cpuCore;
	}
	public void setCpuCore(Integer cpuCore) {
		this.cpuCore = cpuCore;
	}
	public Integer getMemory() {
		return memory;
	}
	public void setMemory(Integer memory) {
		this.memory = memory;
	}
	public RsDeployVmOsInfo getOsInfo() {
		return osInfo;
	}
	public void setOsInfo(RsDeployVmOsInfo osInfo) {
		this.osInfo = osInfo;
	}
	public String getPoolName() {
		return poolName;
	}
	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}
	public List<String> getPoolNames() {
		return poolNames;
	}
	public void setPoolNames(List<String> poolNames) {
		this.poolNames = poolNames;
	}
	public List<String> getVswitchNames() {
		return vswitchNames;
	}
	public void setVswitchNames(List<String> vswitchNames) {
		this.vswitchNames = vswitchNames;
	}
	public int getProtectMode() {
		return protectMode;
	}
	public void setProtectMode(int protectMode) {
		this.protectMode = protectMode;
	}
    public Long getTargetClusterId() {
        return targetClusterId;
    }
    public void setTargetClusterId(Long targetClusterId) {
        this.targetClusterId = targetClusterId;
    }
    public List<RsShareFileSystem> getSharePools() {
        return sharePools;
    }
    public void setSharePools(List<RsShareFileSystem> sharePools) {
        this.sharePools = sharePools;
    }
	public String getUserData() {
		return userData;
	}
	public void setUserData(String userData) {
		this.userData = userData;
	}
    
}

