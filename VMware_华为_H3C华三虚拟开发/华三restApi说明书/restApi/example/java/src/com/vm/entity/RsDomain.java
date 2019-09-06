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
 * Restful Web Services �ӿڷ��ص������ʵ���ࡣ
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

	/** �����ID�� */
	private Long id = null;

	/** �����ID�� **/
	private Long hostId;

	/** ��ȺID�� */
	private Long clusterId = null;

	/** ������ID�� */
	private Long hostPoolId;

	/** ��������ơ� **/
	private String name;
	
	/** �������ʾ���ơ� **/
    private String title;
	
	/** ��������ڼ�Ⱥ�Ƿ�����HA*/
	private Integer enableHA;
	
	/**cpu�������ֵ*/
	private Integer maxCpuSocket;
	/**����������ļ���*/
	private String imgFileName;
	/**����������ļ�����*/
	private String imgFileType;
	
	/**�ڴ�ֵ*/
	private Double memoryInit;
	/**�ڴ浥λ*/
	private String memoryUnit;
	
	/** ģʽ */
	private String cpuMode;
	
	/**���⽻����ID*/
	private Long vSwitchId;
	/**���⽻��������*/
	private String vSwitchName;
	
	/**�˿ڲ���ģ��ID*/
	private Long profileId;
	/**�˿ڲ���ģ������*/
	private String profileName;
	
	/** �������ơ� **/
    private String hostName;

	/** ����������� **/
	private String description;

	/** ������ڴ档�ס� **/
	private Long memory;
	
	/** �ڴ�Ԥ���ٷֱ�, 0--100��(0%--100%),0��ʾ��Ԥ����100��ʾȫ��Ԥ��������Ϊ����Ԥ��. */
	private Integer memoryBacking;
	/** �ڴ�����*/
    private Double memoryLimit;
    
    private String memoryLimitUnit;
    /** �ڴ���Դ���ȼ��� **/
    private Integer memoryPriority;
	
	/** �����CPU������ **/
    private Double cpuRate;
    
    /** ������ڴ������� **/
    private Double memRate;

	/** ���������CPU������CPU���� * CPU�������� **/
	private Integer cpu;
	
	/** �����CPU���� */
	private Integer cpuSockets;
	
	/** �����CPU���� */
	private Integer cpuCores;
	
	/** �����CPU������ */
	private Integer cpuMax;
	
	/** ����CPU������CPU ���� */
	@XmlElement(name = "bindcpu")
	private List<BindPhysicalCpu> bindcpuList;
	
	/** ����������Զ���������*/
	private Boolean autoLoadVirtio;

	/**
	 * �����ռ������CPU���ȼ��� ��=2048����=1024����=512
	 */
	private Integer cpuShares;

	/**
	 * �����I/OȨ�ء�
	 * 
	 */
	private Integer blkiotune;

	/** �����״̬��ȡֵ�� 0:ģ�� 1:δ֪ 2:���� 3:�ر� 4 ��ͣ�� **/
	private Integer status;
	
	/** �����״̬ **/
    private String vmStatus;

	/** �����UUID�� **/
	private String uuid;

	/** �����BIT��ȡֵ�� x86_64 x86�� **/
	private String osBit;

	/** �������װ�Ĳ���ϵͳ��ȡֵ��0:Windows;1:Linux�� **/
	private Integer system;
	/** �������װ�Ĳ���ϵͳ������ **/
	private String osDesc;
	/** �������װ�Ĳ���ϵͳ�汾�� **/
	private String osVersion;
	
	/** ����ϵͳ��װ��ʽ: cd image none */
	private String osInstallMode;
	
	/** ��װ����ϵͳ�����ļ�·�� */
	private String imagePath;

	/** ��ʾ���͡� **/
	private String viewType;

	/** ��ʾ���� vga;cirrus;vmvga�� **/
	private String drive;

	/** ��ʾ�˿ڡ� **/
	private Integer viewPort;

	/** ��ʾ������ַ�� **/
	private String monitorAddr;

	/** �����豸��1 disk 2 cdrom�� **/
	private Integer bootingDevice;

	/** �Ƿ��Զ�������0:���Զ�����1:�Զ������� **/
	private Integer autoBooting;

	/** ��������硣 */
	@XmlElement(name = "network")
	private List<RsDomainNetwork> networks = null;

	/** ������洢�� */
	@XmlElement(name = "storage")
	private List<RsDomainStorage> storages = null;

	/** ������������ȼ� 0:�ͼ� 1:�м� 2:�߼��� */
	private Integer priority = null;

	/** ������Ƿ������Զ�Ǩ�ơ� */
	private Integer autoMigrate = null;

	/** ������Ƿ�����VNC����1�����ã�0���Ϊ�����á� */
	private Integer enableVncProxy = null;

	/** ������������ڡ� */
	private Date createDate;

	/** �洢�������� **/
	private Long storageCapacity;

	/** ��¼IP��ַ�� **/
	private String ipAddr;

	/** �����������־ 0:�������û��Լ��û��� 1:�����û� 2:�����û��顣 */
	private Integer flag = 1;

	/** ��������� 0:CAS����� 1:VMware������� */
	private Integer type = 0;
	
	/** �Ƿ���ٲ������ */
    private Boolean deployed = false;
    
	/** VNC �˿� �Զ�����0���Զ�1���ֶ��� **/
	private Integer auto;
	
    /**�Ƿ����ת����ʽ **/
    private Integer formatEnable;
    
    /**�Ƿ����raw��ʽ**/
    private boolean existRaw;
    
    /** �����HA�쳣״̬ */
    private Integer haStatus;
    
    /** �����HA����״̬ */
    private Integer haManage;
    
    /**CPU��������ֵ*/
    private Double cpuQuota;
    
    /**CPU�������Ƶ�λ*/
    private String cpuQuotaUnit;
    
    /**spice����uri**/
    private String spiceUri;
    
    /** ������߼�����ʱ�� */
    private Date delTime;
    /** ��Ⱥ���� */
    private String clusterName;
    /** ���������� */
    private String hpName;
    /** ������߼�����ʱ�� */
    private Date destroyTime;
    
    /** ������Ƿ����ñ���ģʽ�� 1�����ã�0�������� **/
    private Integer protectModel;
    /**cpuԤ��**/
    private Long cpuGurantee;
    
    /**�Ƿ���ڿ��豸**/
    private boolean existBlock;
    
    /**�Ƿ����PCI��USB�豸**/
    private boolean existPciOrUsb;
    
    /**�Ƿ���ڹ���������**/
    private boolean existCdromOrFloppy;
    
    /**������󶨵�NUMA�ڵ�**/
    private String numaNodeSet;
    
    /**�������NUMA�ڵ��CPU����**/
    private Integer numaBingCpus;
    
    /**�������NUMA�ڵ���ڴ��С**/
    private Long numaBingMen;
    
    /** ������Ƿ�ʱ��ͬ����1��ʱ��ͬ����0ʱ�䲻ͬ���� */
	private Integer timeSync;
	
	/** ��������� */
	private Integer enable;
	
	/** ��������п����ļ���ռ�����Ĵ�С */
    private Long snapshotFilesSize;
	
	/** �������������״̬*/
	private Integer hoststatus = 0;
	
    /**castool״̬*/
    private Integer castoolsStatus;
    
    /**castool�汾*/
    private String castoolsVersion;
    
    private Integer startStatus = null;
    
    /** ��������硣 */
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
	 * ��ȡ cpu �ܺ���
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
