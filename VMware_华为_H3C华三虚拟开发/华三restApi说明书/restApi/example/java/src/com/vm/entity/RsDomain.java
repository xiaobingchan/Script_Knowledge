/*
 * Copyright (c) 2007-2010, Hangzhou H3C Technologies Co., Ltd. All rights reserved.
 * <http://www.h3c.com/>
 *------------------------------------------------------------------------------
 * Product     : 
 * Module Name :
 * Date Created: 
 * Creator     : z01500
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
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.vm.entity.RsDomainForModify.CpuConfig.BindPhysicalCpu;

/**
 * Restful Web Services 接口返回的虚拟机实体类。
 * 
 * @author z01500
 */
@XmlRootElement(name = "domain")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomain implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7630696172783821368L;

	/** 虚拟机ID。 */
	private Long id = null;

	/** 物理机ID。 **/
	private Long hostId;

	/** 集群ID。 */
	private Long clusterId = null;

	/** 主机池ID。 */
	private Long hostPoolId;

	/** 虚拟机名称。 **/
	private String name;
	
	/** 虚拟机显示名称。 **/
    private String title;
	
	/** 虚拟机所在集群是否启用HA*/
	private Integer enableHA;
	
	/**cpu数量最大值*/
	private Integer maxCpuSocket;
	/**虚拟机镜像文件名*/
	private String imgFileName;
	/**虚拟机镜像文件类型*/
	private String imgFileType;
	
	/**内存值*/
	private Double memoryInit;
	/**内存单位*/
	private String memoryUnit;
	
	/** 模式 */
	private String cpuMode;
	
	/**虚拟交换机ID*/
	private Long vSwitchId;
	/**虚拟交换机名称*/
	private String vSwitchName;
	
	/**端口策略模板ID*/
	private Long profileId;
	/**端口策略模板名称*/
	private String profileName;
	
	/** 主机名称。 **/
    private String hostName;

	/** 虚拟机描述。 **/
	private String description;

	/** 虚拟机内存。兆。 **/
	private Long memory;
	
	/** 内存预留百分比, 0--100即(0%--100%),0表示不预留，100表示全部预留。其他为部分预留. */
	private Integer memoryBacking;
	/** 内存限制*/
    private Double memoryLimit;
    
    private String memoryLimitUnit;
    /** 内存资源优先级。 **/
    private Integer memoryPriority;
	
	/** 虚拟机CPU利用率 **/
    private Double cpuRate;
    
    /** 虚拟机内存利用率 **/
    private Double memRate;

	/** 虚拟机虚拟CPU个数（CPU个数 * CPU核数）。 **/
	private Integer cpu;
	
	/** 虚拟机CPU个数 */
	private Integer cpuSockets;
	
	/** 虚拟机CPU核数 */
	private Integer cpuCores;
	
	/** 虚拟机CPU最大核数 */
	private Integer cpuMax;
	
	/** 虚拟CPU绑定物理CPU 配置 */
	@XmlElement(name = "bindcpu")
	private List<BindPhysicalCpu> bindcpuList;
	
	/** 增加虚拟机自动加载软驱*/
	private Boolean autoLoadVirtio;

	/**
	 * 虚拟机占用物理CPU优先级。 高=2048，中=1024，低=512
	 */
	private Integer cpuShares;

	/**
	 * 虚拟机I/O权重。
	 * 
	 */
	private Integer blkiotune;

	/** 虚拟机状态。取值： 0:模板 1:未知 2:运行 3:关闭 4 暂停。 **/
	private Integer status;
	
	/** 虚拟机状态 **/
    private String vmStatus;

	/** 虚拟机UUID。 **/
	private String uuid;

	/** 虚拟机BIT。取值： x86_64 x86。 **/
	private String osBit;

	/** 虚拟机安装的操作系统。取值：0:Windows;1:Linux。 **/
	private Integer system;
	/** 虚拟机安装的操作系统描述。 **/
	private String osDesc;
	/** 虚拟机安装的操作系统版本。 **/
	private String osVersion;
	
	/** 操作系统安装方式: cd image none */
	private String osInstallMode;
	
	/** 安装操作系统镜像文件路径 */
	private String imagePath;

	/** 显示类型。 **/
	private String viewType;

	/** 显示驱动 vga;cirrus;vmvga。 **/
	private String drive;

	/** 显示端口。 **/
	private Integer viewPort;

	/** 显示监听地址。 **/
	private String monitorAddr;

	/** 引导设备：1 disk 2 cdrom。 **/
	private Integer bootingDevice;

	/** 是否自动启动：0:不自动启动1:自动启动。 **/
	private Integer autoBooting;

	/** 虚拟机网络。 */
	@XmlElement(name = "network")
	private List<RsDomainNetwork> networks = null;

	/** 虚拟机存储。 */
	@XmlElement(name = "storage")
	private List<RsDomainStorage> storages = null;

	/** 虚拟机启动优先级 0:低级 1:中级 2:高级。 */
	private Integer priority = null;

	/** 虚拟机是否允许自动迁移。 */
	private Integer autoMigrate = null;

	/** 虚拟机是否启用VNC代理。1：启用，0或空为不启用。 */
	private Integer enableVncProxy = null;

	/** 虚拟机创建日期。 */
	private Date createDate;

	/** 存储总容量。 **/
	private Long storageCapacity;

	/** 登录IP地址。 **/
	private String ipAddr;

	/** 虚拟机所属标志 0:不属于用户以及用户组 1:属于用户 2:属于用户组。 */
	private Integer flag = 1;

	/** 虚拟机类型 0:CAS虚拟机 1:VMware虚拟机。 */
	private Integer type = 0;
	
	/** 是否快速部署过。 */
    private Boolean deployed = false;
    
	/** VNC 端口 自动配置0：自动1：手动。 **/
	private Integer auto;
	
    /**是否可以转换格式 **/
    private Integer formatEnable;
    
    /**是否存在raw格式**/
    private boolean existRaw;
    
    /** 虚拟机HA异常状态 */
    private Integer haStatus;
    
    /** 虚拟机HA管理状态 */
    private Integer haManage;
    
    /**CPU周期限制值*/
    private Double cpuQuota;
    
    /**CPU周期限制单位*/
    private String cpuQuotaUnit;
    
    /**spice访问uri**/
    private String spiceUri;
    
    /** 虚拟机逻辑回收时间 */
    private Date delTime;
    /** 集群名称 */
    private String clusterName;
    /** 主机池名称 */
    private String hpName;
    /** 虚拟机逻辑销毁时间 */
    private Date destroyTime;
    
    /** 虚拟机是否启用保护模式。 1：启用，0：不启用 **/
    private Integer protectModel;
    /**cpu预留**/
    private Long cpuGurantee;
    
    /**是否存在块设备**/
    private boolean existBlock;
    
    /**是否存在PCI或USB设备**/
    private boolean existPciOrUsb;
    
    /**是否存在光驱或软驱**/
    private boolean existCdromOrFloppy;
    
    /**虚拟机绑定的NUMA节点**/
    private String numaNodeSet;
    
    /**虚拟机绑定NUMA节点的CPU核数**/
    private Integer numaBingCpus;
    
    /**虚拟机绑定NUMA节点的内存大小**/
    private Long numaBingMen;
    
    /** 虚拟机是否时间同步。1：时间同步，0时间不同步。 */
	private Integer timeSync;
	
	/** 虚拟机类型 */
	private Integer enable;
	
	/** 虚拟机所有快照文件所占容量的大小 */
    private Long snapshotFilesSize;
	
	/** 虚拟机所在主机状态*/
	private Integer hoststatus = 0;
	
    /**castool状态*/
    private Integer castoolsStatus;
    
    /**castool版本*/
    private String castoolsVersion;
    
    private Integer startStatus = null;
    
    /** 虚拟机网络。 */
    @XmlElement(name = "ipv4Attribute")
    private List<DomainIPv4Attribute>ipv4Attributes;

    public void setCpuGurantee(Long cpuGurantee) {
		this.cpuGurantee = cpuGurantee;
	}
    
    public Long getCpuGurantee() {
		return cpuGurantee;
	}
    
	public Double getCpuQuota() {
		return cpuQuota;
	}

	public void setCpuQuota(Double cpuQuota) {
		this.cpuQuota = cpuQuota;
	}

	public String getCpuQuotaUnit() {
		return cpuQuotaUnit;
	}

	public void setCpuQuotaUnit(String cpuQuotaUnit) {
		this.cpuQuotaUnit = cpuQuotaUnit;
	}
    
	public Boolean getAutoLoadVirtio() {
		return autoLoadVirtio;
	}

	public void setAutoLoadVirtio(Boolean autoLoadVirtio) {
		this.autoLoadVirtio = autoLoadVirtio;
	}

	public Integer getEnableHA() {
		return enableHA;
	}

	public void setEnableHA(Integer enableHA) {
		this.enableHA = enableHA;
	}

	public Integer getMaxCpuSocket() {
		return maxCpuSocket;
	}

	public void setMaxCpuSocket(Integer maxCpuSocket) {
		this.maxCpuSocket = maxCpuSocket;
	}

	public String getImgFileName() {
		return imgFileName;
	}

	public void setImgFileName(String imgFileName) {
		this.imgFileName = imgFileName;
	}

	public String getImgFileType() {
		return imgFileType;
	}

	public void setImgFileType(String imgFileType) {
		this.imgFileType = imgFileType;
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

	public String getCpuMode() {
		return cpuMode;
	}

	public void setCpuMode(String cpuMode) {
		this.cpuMode = cpuMode;
	}

	public Long getvSwitchId() {
		return vSwitchId;
	}

	public void setvSwitchId(Long vSwitchId) {
		this.vSwitchId = vSwitchId;
	}

	public String getvSwitchName() {
		return vSwitchName;
	}

	public void setvSwitchName(String vSwitchName) {
		this.vSwitchName = vSwitchName;
	}

	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}

	public Long getHostPoolId() {
		return hostPoolId;
	}

	public void setHostPoolId(Long hostPoolId) {
		this.hostPoolId = hostPoolId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
	
	public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getMemory() {
		return memory;
	}

	public void setMemory(Long memory) {
		this.memory = memory;
	}

	public Integer getMemoryBacking() {
		return memoryBacking;
	}
	
	public void setMemoryBacking(Integer memoryBacking) {
		this.memoryBacking = memoryBacking;
	}
	
	public Double getMemoryLimit() {
		return memoryLimit;
	}

	public void setMemoryLimit(Double memoryLimit) {
		this.memoryLimit = memoryLimit;
	}

	public String getMemoryLimitUnit() {
		return memoryLimitUnit;
	}

	public void setMemoryLimitUnit(String memoryLimitUnit) {
		this.memoryLimitUnit = memoryLimitUnit;
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
	
	/**
	 * 获取 cpu 总核数
	 * */
	public Integer getCpu() {
		if (getCpuSockets() != null && getCpuCores() != null) {
			return getCpuSockets() *  getCpuCores();
		} 
		return cpu;
	}

	public void setCpu(Integer cpu) {
		this.cpu = cpu;
	}

	public void setCpuSockets(Integer cpuSockets) {
		this.cpuSockets = cpuSockets;
	}

	public Integer getCpuSockets() {
		return cpuSockets;
	}

	public void setCpuCores(Integer cpuCores) {
		this.cpuCores = cpuCores;
	}

	public Integer getCpuCores() {
		return cpuCores;
	}
	
	public Integer getCpuShares() {
		return cpuShares;
	}

	public void setBindcpuList(List<BindPhysicalCpu> bindcpuList) {
		this.bindcpuList = bindcpuList;
	}

	public List<BindPhysicalCpu> getBindcpuList() {
		return bindcpuList;
	}
	
	public void setCpuShares(Integer cpuShares) {
		this.cpuShares = cpuShares;
	}

	public Integer getBlkiotune() {
		return blkiotune;
	}

	public void setBlkiotune(Integer blkiotune) {
		this.blkiotune = blkiotune;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public String getVmStatus() {
        return vmStatus;
    }

    public void setVmStatus(String vmStatus) {
        this.vmStatus = vmStatus;
    }

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getOsBit() {
		return osBit;
	}

	public void setOsBit(String osBit) {
		this.osBit = osBit;
	}

	public Integer getSystem() {
		return system;
	}

	public void setSystem(Integer system) {
		this.system = system;
	}

	public String getOsDesc() {
		return osDesc;
	}

	public void setOsDesc(String osDesc) {
		this.osDesc = osDesc;
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		this.osVersion = osVersion;
	}

	public String getViewType() {
		return viewType;
	}

	public void setViewType(String viewType) {
		this.viewType = viewType;
	}

	public String getDrive() {
		return drive;
	}

	public void setDrive(String drive) {
		this.drive = drive;
	}

	public Integer getViewPort() {
		return viewPort;
	}

	public void setViewPort(Integer viewPort) {
		this.viewPort = viewPort;
	}

	public String getMonitorAddr() {
		return monitorAddr;
	}

	public void setMonitorAddr(String monitorAddr) {
		this.monitorAddr = monitorAddr;
	}

	public Integer getBootingDevice() {
		return bootingDevice;
	}

	public void setBootingDevice(Integer bootingDevice) {
		this.bootingDevice = bootingDevice;
	}

	public Integer getAutoBooting() {
		return autoBooting;
	}

	public void setAutoBooting(Integer autoBooting) {
		this.autoBooting = autoBooting;
	}

	public List<RsDomainNetwork> getNetworks() {
		return networks;
	}

	public void setNetworks(List<RsDomainNetwork> networks) {
		this.networks = networks;
	}

	public List<RsDomainStorage> getStorages() {
		return storages;
	}

	public void setStorages(List<RsDomainStorage> storages) {
		this.storages = storages;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getAutoMigrate() {
		return autoMigrate;
	}

	public void setAutoMigrate(Integer autoMigrate) {
		this.autoMigrate = autoMigrate;
	}

	public Integer getEnableVncProxy() {
		return enableVncProxy;
	}

	public void setEnableVncProxy(Integer enableVncProxy) {
		this.enableVncProxy = enableVncProxy;
	}

	public Date getCreateDate() {
		return createDate != null ? new Date(createDate.getTime()) : null;
	}

	public void setCreateDate(Date createDate) {
		if (createDate != null) {
			if (this.createDate != null) {
				this.createDate.setTime(createDate.getTime());
			} else {
				this.createDate = new Date(createDate.getTime());
			}
		} else {
			this.createDate = null;
		}
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getStorageCapacity() {
		return storageCapacity;
	}

	public void setStorageCapacity(Long storageCapacity) {
		this.storageCapacity = storageCapacity;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Integer getAuto() {
		return auto;
	}

	public void setAuto(Integer auto) {
		this.auto = auto;
	}

	public void setOsInstallMode(String osInstallMode) {
		this.osInstallMode = osInstallMode;
	}

	public String getOsInstallMode() {
		return osInstallMode;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public String getImagePath() {
		return imagePath;
	}
	
	@Override
	public String toString() {
		return String.format("VmVncInfo {vmName=%d, hostIp=%s}", id, name);
	}

    public Integer getFormatEnable() {
        return formatEnable;
    }

    public void setFormatEnable(Integer formatEnable) {
        this.formatEnable = formatEnable;
    }

    public boolean isExistRaw() {
        return existRaw;
    }

    public void setExistRaw(boolean existRaw) {
        this.existRaw = existRaw;
    }

	public void setHaStatus(Integer haStatus) {
		this.haStatus = haStatus;
	}

	public Integer getHaStatus() {
		return haStatus;
	}

	public Integer getMemoryPriority() {
		return memoryPriority;
	}

	public void setMemoryPriority(Integer memoryPriority) {
		this.memoryPriority = memoryPriority;
	}

    public Boolean getDeployed() {
        return deployed;
    }

    public void setDeployed(Boolean deployed) {
        this.deployed = deployed;
    }

    public Integer getCpuMax() {
        return cpuMax;
    }

    public void setCpuMax(Integer cpuMax) {
        this.cpuMax = cpuMax;
    }

	public String getSpiceUri() {
		return spiceUri;
	}

	public void setSpiceUri(String spiceUri) {
		this.spiceUri = spiceUri;
	}

	public Integer getProtectModel() {
		return protectModel;
	}

	public void setProtectModel(Integer protectModel) {
		this.protectModel = protectModel;
	}

	public Integer getHaManage() {
		return haManage;
	}

	public void setHaManage(Integer haManage) {
		this.haManage = haManage;
	}

    public Date getDelTime() {
        return delTime;
    }

    public void setDelTime(Date delTime) {
        this.delTime = delTime;
    }

    public String getClusterName() {
        return clusterName;
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public String getHpName() {
        return hpName;
    }

    public void setHpName(String hpName) {
        this.hpName = hpName;
    }

    public Date getDestroyTime() {
        return destroyTime;
    }

    public void setDestroyTime(Date destroyTime) {
        this.destroyTime = destroyTime;
    }

	public boolean isExistBlock() {
		return existBlock;
	}

	public void setExistBlock(boolean existBlock) {
		this.existBlock = existBlock;
	}

	public boolean isExistPciOrUsb() {
		return existPciOrUsb;
	}

	public void setExistPciOrUsb(boolean existPciOrUsb) {
		this.existPciOrUsb = existPciOrUsb;
	}

	public boolean isExistCdromOrFloppy() {
		return existCdromOrFloppy;
	}

	public void setExistCdromOrFloppy(boolean existCdromOrFloppy) {
		this.existCdromOrFloppy = existCdromOrFloppy;
	}

	public String getNumaNodeSet() {
		return numaNodeSet;
	}

	public void setNumaNodeSet(String numaNodeSet) {
		this.numaNodeSet = numaNodeSet;
	}

	public Integer getNumaBingCpus() {
		return numaBingCpus;
	}

	public void setNumaBingCpus(Integer numaBingCpus) {
		this.numaBingCpus = numaBingCpus;
	}

	public Long getNumaBingMen() {
		return numaBingMen;
	}

	public void setNumaBingMen(Long numaBingMen) {
		this.numaBingMen = numaBingMen;
	}

	public Integer getTimeSync() {
		return timeSync;
	}

	public void setTimeSync(Integer timeSync) {
		this.timeSync = timeSync;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

    public Long getSnapshotFilesSize() {
        return snapshotFilesSize;
    }

    public void setSnapshotFilesSize(Long snapshotFilesSize) {
        this.snapshotFilesSize = snapshotFilesSize;
    }

	public Integer getHoststatus() {
		return hoststatus;
	}

	public void setHoststatus(Integer hoststatus) {
		this.hoststatus = hoststatus;
	}

	public Integer getCastoolsStatus() {
		return castoolsStatus;
	}

	public void setCastoolsStatus(Integer castoolsStatus) {
		this.castoolsStatus = castoolsStatus;
	}

	public String getCastoolsVersion() {
		return castoolsVersion;
	}

	public void setCastoolsVersion(String castoolsVersion) {
		this.castoolsVersion = castoolsVersion;
	}

	public Integer getStartStatus() {
		return startStatus;
	}

	public void setStartStatus(Integer startStatus) {
		this.startStatus = startStatus;
	}

	public List<DomainIPv4Attribute> getIpv4Attributes() {
		return ipv4Attributes;
	}

	public void setIpv4Attributes(List<DomainIPv4Attribute> ipv4Attributes) {
		this.ipv4Attributes = ipv4Attributes;
	}
	
}
