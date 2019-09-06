package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * 用于REST接口模板部署虚拟机
 * @author j09585
 */
@XmlRootElement(name = "domainConfig")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainDeploy implements Serializable {
	private static final long serialVersionUID = 8721819263959341335L;

	/** 模板ID*/
    private Long id;
    /** 部署虚拟机名称 **/
    private String domainName;
    /**　显示名称*/
    private String title;
    /** 描述 */
    private String desc;
    
    /** 目的主机id */
    private Long targetHostId;
    /** 目的集群id，面向集群部署使用。*/
    private Long targetClusterId;
    /** 1:快速部署,0:正常部署 */
    private int deployMode = 0;
    /** 1:保护模式，0:非保护模式*/
    private int protectMode = 0;
    /** CPU信息*/
    private Integer cpuSocket = null;
    private Integer cpuCore = null;
    /** 内存信息*/
    private Integer memory = null;
    /** 内存显示信息*/
    private Double memoryInit = null;
    /** 内存单位 */
    private String memoryUnit = null;
    /** 目标主机存储池名字,组织部署时用。*/
    private String poolName = null;
    /** 部署时允许部署的主机池名称 */
    private List<String> poolNames = null;
    /** 目标虚拟交换机名字，组织部署时使用。*/
    private List<String> vswitchNames = null;
    /** 目的虚拟机存储 信息*/
    @XmlElement(name = "storage")
    private List<RsStore> storages;
    /** 目的虚拟机 网络信息*/
    @XmlElement(name = "network")
    private List<RsDomainSwitch> networks;
    /** 目的共享存储池 */
    @XmlElement(name= "sharePool")
    private List<RsShareFileSystem> sharePools;
    
	/** raw,qcow2*/
    private String diskFormat;
    /** 系统配置信息 */
    private RsDeployVmOsInfo osInfo = null;
    
    /** the vmNum should be deploied */
    private int vmNum;
    /** 1：表示从模板列表中部署，2：表示从组织内部署 */
    private Integer deployType = 0;
    
    /**
	 * 用户数据,用于用户定制脚本/命令等
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

