package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 虚拟机，用于修改虚拟机，对应虚拟机界面修改
 * @author m10374
 *
 */
@XmlRootElement(name = "domain")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainForModify implements Serializable {

	private static final long serialVersionUID = 3062995209808100337L;
	
	/** 虚拟机id */
	private Long id;
	
	/** 虚拟机名称 */
	private String name;
	
	/** 虚拟机显示名称 */
	private String title;
	
	/** 显卡类型 */
	private String videoType;

	/** 声卡类型 */
	private String soundType;
	
	/** 是否启用VNC 代理*/
	private Integer enableVncProxy;
	
	/** 虚拟机基本信息 */
	@XmlElement(name = "basic")
	private BasicConfig basicConfig;
	
	/** 虚拟机cpu配置信息*/
	@XmlElement(name = "cpu")
	private CpuConfig cpuConfig;

	/** 虚拟机内存。兆。 **/
	@XmlElement(name = "memory")
	private MemoryConfig memory;
	
	@XmlElement(name = "bootDev")
	private BootDevConfig bootDevConfig;
	
	@XmlElement(name = "storage")
	private StorageConfig storage;

	@XmlElement(name = "cdrom")
	private CdromConfig cdromConfig;
	
	@XmlElement(name = "network")
	private NetworkConfig network;
	
	@XmlElement(name = "input")
	private InputConfig input;
	
	@XmlElement(name = "vnc")
	private GraphicsConfig vncConfig;
	
	@XmlElement(name = "usb")
	private USBDevConfig usb;

	@XmlElement(name = "pci")
	private PCIDevConfig pci;
	@XmlElement(name = "serial")
	private CharacterDeviceConfig serial;
	
	@XmlElement(name = "numa")
	private NumaConfig numaConfig;
	
	@XmlElement(name = "tpm")
	private TpmDev tpmDev;
	
	@XmlElement(name = "advance")
	private AdvanceConfig advance;
	
	@XmlElement(name = "gpu")
	private GpuDev gpuDev;
	
	@XmlElement(name = "shmem")
	private ShmemDev shmem;
	
	private boolean diskFlow;
	
	public boolean isDiskFlow() {
		return diskFlow;
	}

	public void setDiskFlow(boolean diskFlow) {
		this.diskFlow = diskFlow;
	}

	public GpuDev getGpuDev() {
		return gpuDev;
	}

	public void setGpuDev(GpuDev gpuDev) {
		this.gpuDev = gpuDev;
	}

	public AdvanceConfig getAdvance() {
		return advance;
	}

	public void setAdvance(AdvanceConfig advance) {
		this.advance = advance;
	}

	public TpmDev getTpmDev() {
		return tpmDev;
	}

	public void setTpmDev(TpmDev tpmDev) {
		this.tpmDev = tpmDev;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	public void setBasicConfig(BasicConfig basicConfig) {
		this.basicConfig = basicConfig;
	}

	/** 获取虚拟机基本配置信息 */
	public BasicConfig getBasicConfig() {
		return basicConfig;
	}
	
	public void setCpuConfig(CpuConfig cpuConfig) {
		this.cpuConfig = cpuConfig;
	}

	/** 获取 cpu配置信息 */
	public CpuConfig getCpuConfig() {
		return cpuConfig;
	}

	public void setBootDevConfig(BootDevConfig bootDevConfig) {
		this.bootDevConfig = bootDevConfig;
	}

	/** 获取引导设备配置信息 */
	public BootDevConfig getBootDevConfig() {
		return bootDevConfig;
	}

	public void setStorage(StorageConfig storage) {
		this.storage = storage;
	}

	public StorageConfig getStorage() {
		return storage;
	}

	public void setCdromConfig(CdromConfig cdromConfig) {
		this.cdromConfig = cdromConfig;
	}

	public CdromConfig getCdromConfig() {
		return cdromConfig;
	}

	public NetworkConfig getNetwork() {
		return network;
	}

	public void setNetwork(NetworkConfig network) {
		this.network = network;
	}
	
	public void setVideoType(String videoType) {
		this.videoType = videoType;
	}

	public String getVideoType() {
		return videoType;
	}

	public void setEnableVncProxy(Integer enableVncProxy) {
		this.enableVncProxy = enableVncProxy;
	}

	public Integer getEnableVncProxy() {
		return enableVncProxy;
	}

	public void setMemory(MemoryConfig memory) {
		this.memory = memory;
	}

	public MemoryConfig getMemory() {
		return memory;
	}

	public void setInput(InputConfig input) {
		this.input = input;
	}

	public InputConfig getInput() {
		return input;
	}

	public void setVncConfig(GraphicsConfig vncConfig) {
		this.vncConfig = vncConfig;
	}

	public GraphicsConfig getVncConfig() {
		return vncConfig;
	}

	public void setSoundType(String soundType) {
		this.soundType = soundType;
	}

	public String getSoundType() {
		return soundType;
	}

	public void setUsb(USBDevConfig usb) {
		this.usb = usb;
	}

	public USBDevConfig getUsb() {
		return usb;
	}

	public PCIDevConfig getPci() {
		return pci;
	}

	public void setPci(PCIDevConfig pci) {
		this.pci = pci;
	}
	public void setSerial(CharacterDeviceConfig serial) {
		this.serial = serial;
	}

	public CharacterDeviceConfig getSerial() {
		return serial;
	}

	public NumaConfig getNumaConfig() {
		return numaConfig;
	}

	public void setNumaConfig(NumaConfig numaConfig) {
		this.numaConfig = numaConfig;
	}

	/**
	 * 虚拟机基本信息，对应界面概要修改
	 */
	@XmlRootElement(name = "basic")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class BasicConfig implements Serializable {

		private static final long serialVersionUID = 5299057594321798763L;
		
		/** 是否启用PAE：1-启用，0-不启用  */
		private Integer pae;

		/** 是否启用ACPI：1-启用，0-不启用  */
		private Integer acpi;

		/** 是否启用APIC：1-启用，0-不启用  */
		private Integer apic;
		
		/** 时钟设置：本地：localtime，世界时钟 ：utc*/
		private String clock;
		
		/** IO优先级 高=500，中=300，低=200 */
		private Integer blkiotune;
		
		/** 虚拟机启动优先级 0:低级 1:中级 2:高级。 */
		private Integer startPriority;

		/** 虚拟机是否允许自动迁移。1:允许，0：否 */
		private Integer autoMigrate = null;
		
		/** 系统故障策略 0:不处理 1:故障重启 2:故障迁移。 */
		private Integer osha = 0;
		
		/** 同步虚拟机时间与主机时间  1:同步，0：不同步*/
		private Integer timeSync = 0;
		
		/** 虚拟机HA管理标志 1:开启高可靠性，0：关闭高可靠性 */
		private Integer haManage = null;
		
		/**虚拟机操作系统信息*/
		private String osVersion;
		
		/** 虚拟机描述信息 */
		private String desc;
		
		/** 管理程序*/
		private String manager;

		/** 系统架构 如：X86_64*/
		private String architecture;
		
		/** 模拟器 */
		private String emulator;
		
		/** 已经存在的ControllerList */
        @XmlElement(name = "controller")
        private List<String> controllerList;

		public String getOsVersion() {
			return osVersion;
		}

		public void setOsVersion(String osVersion) {
			this.osVersion = osVersion;
		}

		public Integer getPae() {
			return pae;
		}
		
		public void setPae(Integer pae) {
			this.pae = pae;
		}

		public Integer getAcpi() {
			return acpi;
		}

		public void setAcpi(Integer acpi) {
			this.acpi = acpi;
		}

		public Integer getApic() {
			return apic;
		}

		public void setApic(Integer apic) {
			this.apic = apic;
		}

		public String getClock() {
			return clock;
		}

		public void setClock(String clock) {
			this.clock = clock;
		}

		public Integer getBlkiotune() {
			return blkiotune;
		}

		public void setBlkiotune(Integer blkiotune) {
			this.blkiotune = blkiotune;
		}

		public Integer getStartPriority() {
			return startPriority;
		}

		/** 设置虚拟机启动优先级 0:低级 1:中级 2:高级。 */
		public void setStartPriority(Integer startPriority) {
			this.startPriority = startPriority;
		}

		public Integer getAutoMigrate() {
			return autoMigrate;
		}

		public void setAutoMigrate(Integer autoMigrate) {
			this.autoMigrate = autoMigrate;
		}
		
		public Integer getOsha() {
			return osha;
		}

		public void setOsha(Integer osha) {
			this.osha = osha;
		}

		public Integer getTimeSync() {
			return timeSync;
		}

		public void setTimeSync(Integer timeSync) {
			this.timeSync = timeSync;
		}

		public String getDesc() {
			return desc;
		}

		public void setDesc(String desc) {
			this.desc = desc;
		}

		public void setArchitecture(String architecture) {
			this.architecture = architecture;
		}

		public String getArchitecture() {
			return architecture;
		}

		public void setEmulator(String emulator) {
			this.emulator = emulator;
		}

		public String getEmulator() {
			return emulator;
		}

		public String getManager() {
			return manager;
		}

		public void setManager(String manager) {
			this.manager = manager;
		}

		public Integer getHaManage() {
			return haManage;
		}

		public void setHaManage(Integer haManage) {
			this.haManage = haManage;
		}

        public List<String> getControllerList() {
            return controllerList;
        }

        public void setControllerList(List<String> controllerList) {
            this.controllerList = controllerList;
        }

	}
	
	/**
	 * 虚拟机CPU配置信息
	 *
	 */
	@XmlRootElement(name = "cpu")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CpuConfig  implements Serializable {
		
		private static final long serialVersionUID = 579524840502801969L;

		/** CPU个数  */
		private Integer cpuSockets;
		
		/** CPU核数 */
		private Integer cpuCores;

		/** 虚拟机PU优调度先级。 高=1024，中=512，低=256 */
		private Integer cpuShares;

		/** CPU最大核数（主机CPU核数） */
		private Integer maxCpuNum;
		
		/** CPU工作模式 */
		private String cpuMode;
		/** x86_64 or i686*/
		private String cpuArch;
		@XmlElement(name = "bindcpu")
		private List<BindPhysicalCpu> bindcpuList;
		
	    /**CPU周期限制值*/
	    private Double cpuQuota;
	    /**CPU周期限制单位*/
	    private String cpuQuotaUnit;
	    /**CPU周期最小限制*/
	    private Long cpuMinRate;
	    /**CPU周期最大限制*/
	    private Long cpuMaxRate;
	    
        private Long cpuGurantee;
	    
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

		public Long getCpuMinRate() {
			return cpuMinRate;
		}

		public void setCpuMinRate(Long cpuMinRate) {
			this.cpuMinRate = cpuMinRate;
		}

		public Long getCpuMaxRate() {
			return cpuMaxRate;
		}

		public void setCpuMaxRate(Long cpuMaxRate) {
			this.cpuMaxRate = cpuMaxRate;
		}

		public Integer getCpuSockets() {
			return cpuSockets;
		}

		public void setCpuSockets(Integer cpuSockets) {
			this.cpuSockets = cpuSockets;
		}

		public Integer getCpuCores() {
			return cpuCores;
		}

		public void setCpuCores(Integer cpuCores) {
			this.cpuCores = cpuCores;
		}

		public Integer getCpuShares() {
			return cpuShares;
		}

		public void setCpuShares(Integer cpuShares) {
			this.cpuShares = cpuShares;
		}

		public void setBindcpuList(List<BindPhysicalCpu> bindcpuList) {
			this.bindcpuList = bindcpuList;
		}

		public List<BindPhysicalCpu> getBindcpuList() {
			return bindcpuList;
		}

		public void setMaxCpuNum(Integer maxCpuNum) {
			this.maxCpuNum = maxCpuNum;
		}

		public Integer getMaxCpuNum() {
			return maxCpuNum;
		}

		public void setCpuMode(String cpuMode) {
			this.cpuMode = cpuMode;
		}

		public String getCpuMode() {
			return cpuMode;
		}

		public String getCpuArch() {
			return cpuArch;
		}

		public void setCpuArch(String cpuArch) {
			this.cpuArch = cpuArch;
		}

		/** cpu 绑定 物理cpu配置 */
		@XmlRootElement(name = "bindcpu")
		@XmlAccessorType(XmlAccessType.FIELD)
		public static class BindPhysicalCpu implements Serializable {

			private static final long serialVersionUID = -3922163940243834465L;
			
			private Integer vcpu;
			
			private Integer[] pcpu;

			public Integer getVcpu() {
				return vcpu;
			}

			public void setVcpu(Integer vcpu) {
				this.vcpu = vcpu;
			}

			public Integer[] getPcpu() {
				return pcpu;
			}

			public void setPcpu(Integer[] pcpu) {
				this.pcpu = pcpu;
			}
		}

	}

	/**
	 * 虚拟机内存配置
	 *
	 */
	@XmlRootElement(name = "memory")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class MemoryConfig implements Serializable {

		private static final long serialVersionUID = -8355993751581288127L;
		
		/** 大小 */
		private Long size;
		
		/** 内存预留百分比 0-100 */
		private Integer memoryBacking;
		
		/** 内存资源优先级 */
		private Integer memoryPriority;
		
		/** 内存限制*/
	    private Double memoryLimit;
	    
	    private String memoryLimitUnit;

		/** 内存单位 */
		private String memoryUnit;
		
		/** 显示内存 */
		private Double memoryInit;
		
	    
		/** 最大分配*/
		private Long maxMemory;
		
		/** 主机内存 */
        private Long hostMemory;
		
		public Long getSize() {
			return size;
		}

		public void setSize(Long size) {
			this.size = size;
		}

		public Integer getMemoryBacking() {
			return memoryBacking;
		}

		public void setMemoryBacking(Integer memoryBacking) {
			this.memoryBacking = memoryBacking;
		}

		public void setMemoryUnit(String memoryUnit) {
			this.memoryUnit = memoryUnit;
		}

		public String getMemoryUnit() {
			return memoryUnit;
		}

		public void setMemoryInit(Double memoryInit) {
			this.memoryInit = memoryInit;
		}

		public Double getMemoryInit() {
			return memoryInit;
		}

		public void setMaxMemory(Long maxMemory) {
			this.maxMemory = maxMemory;
		}

		public Long getMaxMemory() {
			return maxMemory;
		}

		public Integer getMemoryPriority() {
			return memoryPriority;
		}
		public void setMemoryPriority(Integer memoryPriority) {
			this.memoryPriority = memoryPriority;
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

        public void setHostMemory(Long hostMemory) {
            this.hostMemory = hostMemory;
        }

        public Long getHostMemory() {
            return hostMemory;
        }
	}
	
	/**
	 * 虚拟机引导设备配置
	 */
	@XmlRootElement(name = "bootdev")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class BootDevConfig implements Serializable {

		private static final long serialVersionUID = 5244197474590131195L;
		
		/** 自动启动 */
		private Integer autoStart;
		
		/** 虚拟机引导设备及顺序，形式：dev1,dev2,net1...*/
		private String bootdevs;
		
		private String unuseddevs;

		public Integer getAutoStart() {
			return autoStart;
		}

		public void setAutoStart(Integer autoStart) {
			this.autoStart = autoStart;
		}

		public String getBootdevs() {
			return bootdevs;
		}

		public void setBootdevs(String bootdevs) {
			this.bootdevs = bootdevs;
		}

		public void setUnuseddevs(String unuseddevs) {
			this.unuseddevs = unuseddevs;
		}

		public String getUnuseddevs() {
			return unuseddevs;
		}
	}

	/**
	 * 虚拟机存储配置
	 * 
	 */
	@XmlRootElement(name = "storage")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class StorageConfig implements Serializable {

		private static final long serialVersionUID = -3042963799948646719L;
		
		/** 设备驱动类型: disk, cdrom, floppy */
		private String device;
		
		/** 设备名称 */
		private String deviceName;
		
		/** 存储卷类型 file block */
		private String fileType;
		
		/** 磁盘类型：IDE 硬盘、SCSI 硬盘、USB硬盘、Virtio硬盘、IDE光驱、软驱 */
		private String type;
		
		/** 存储卷路径 */
		private String path;
		
		/** 存储格式  raw qcow2*/
		private String format;

	    /** 设备类型  取值为：ide scsi virtio usb    SCSI("scsi"), VIRTIO("virtio"), USB("usb");  **/
	    private String targetBus;
		
		/** 分配存储容量 */
		private Long size;
		
		/** 已使用大小 */
		private Long allocation;
		
		/** 磁盘缓存方式 */
		private String cacheType;

		/** 为了照应界面参数，无用 */
		@XmlTransient
		private boolean shareable = false;
		
		/** 为了照应界面参数，无用 */
		@XmlTransient
		private boolean readonly = false;
		
		/** 包含快照: 1 包含；0 不包含 */
		private Integer snapShot;
		
		/** max size (pool size)*/
		private Long maxSize;
		
		/** 基础镜像文件 */
		private String backingFile;
		
		private Long readBytesSec = null;
		private Long writeBytesSec = null;
		private Long readIopsSec = null;
		private Long writeIopsSec = null;
		
		/** 前台指定的Controller*/
        private String controller = null;
		
		public String getDevice() {
			return device;
		}

		public void setDevice(String device) {
			this.device = device;
		}

		public String getFormat() {
			return format;
		}

		public void setFormat(String format) {
			this.format = format;
		}

		public Long getSize() {
			return size;
		}

		public void setSize(Long size) {
			this.size = size;
		}

		public boolean isShareable() {
			return shareable;
		}

		/**
		 * shareable 参数暂时无用，和界面照应，永远为false
		 * @param shareable
		 */
		public void setShareable(boolean shareable) {
			this.shareable = shareable;
		}

		public boolean isReadonly() {
			return readonly;
		}

		/**
		 * readonly 参数暂时无用，和界面照应，永远为false
		 * @param readonly
		 */
		public void setReadonly(boolean readonly) {
			this.readonly = readonly;
		}

		public void setCacheType(String cache) {
			this.cacheType = cache;
		}
		
		/**
		 * 获取磁盘缓存方式
		 */
		public String getCacheType() {
			return cacheType;
		}

		public void setPath(String path) {
			this.path = path;
		}

		public String getPath() {
			return path;
		}

		public void setTargetBus(String targetBus) {
			this.targetBus = targetBus;
		}

		public String getTargetBus() {
			return targetBus;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}

		public void setDeviceName(String deviceName) {
			this.deviceName = deviceName;
		}

		public String getDeviceName() {
			return deviceName;
		}

		public void setSnapShot(Integer snapShot) {
			this.snapShot = snapShot;
		}

		public Integer getSnapShot() {
			return snapShot;
		}

		public void setFileType(String fileType) {
			this.fileType = fileType;
		}

		public String getFileType() {
			return fileType;
		}

		public void setAllocation(Long allocation) {
			this.allocation = allocation;
		}

		public Long getAllocation() {
			return allocation;
		}

		public void setMaxSize(Long maxSize) {
			this.maxSize = maxSize;
		}

		public Long getMaxSize() {
			return maxSize;
		}

		public void setBackingFile(String backingFile) {
			this.backingFile = backingFile;
		}

		public String getBackingFile() {
			return backingFile;
		}

		public Long getReadBytesSec() {
			return readBytesSec;
		}

		public void setReadBytesSec(Long readBytesSec) {
			this.readBytesSec = readBytesSec;
		}

		public Long getWriteBytesSec() {
			return writeBytesSec;
		}

		public void setWriteBytesSec(Long writeBytesSec) {
			this.writeBytesSec = writeBytesSec;
		}

		public Long getReadIopsSec() {
			return readIopsSec;
		}

		public void setReadIopsSec(Long readIopsSec) {
			this.readIopsSec = readIopsSec;
		}

		public Long getWriteIopsSec() {
			return writeIopsSec;
		}

		public void setWriteIopsSec(Long writeIopsSec) {
			this.writeIopsSec = writeIopsSec;
		}

        public String getController() {
            return controller;
        }

        public void setController(String controller) {
            this.controller = controller;
        }

	}
	
	/**
	 * 光驱配置信息
	 *
	 */
	public static class CdromConfig implements Serializable {

		private static final long serialVersionUID = -3011173664105311201L;
		
		/** 设备名称 */
		private String device;

		/** 操作 ：connect/disconnect*/
		private String operation;
		
		/** 原设备类型 ：block，file, tool*/
		private String type;
		
		/** 路径 */
		private String path;

		public String getDevice() {
			return device;
		}

		public void setDevice(String device) {
			this.device = device;
		}

		public String getOperation() {
			return operation;
		}

		public void setOperation(String operation) {
			this.operation = operation;
		}

		/** 设备类型：ide:IDE 硬盘、scsi:SCSI 硬盘、usb:USB硬盘、virtio:Virtio硬盘、cdrom:IDE光驱、floppy:软驱 */
		public String getType() {
			return type;
		}

		/** 设备类型：ide:IDE 硬盘、scsi:SCSI 硬盘、usb:USB硬盘、virtio:Virtio硬盘、cdrom:IDE光驱、floppy:软驱 */
		public void setType(String type) {
			this.type = type;
		}

		public String getPath() {
			return path;
		}

		public void setPath(String path) {
			this.path = path;
		}
	}
	
	/**
	 * 网络配置，在{@link RsDomainNetwork}基础上增加了newMac属性，用于修改mac地址
	 * 增加了 location属性， 直通网卡的pci地址。
	 * 增加了 devtype属性，区分直通网卡：11 和 普通网卡：2
	 */
	public static class NetworkConfig extends RsDomainNetwork {

		private static final long serialVersionUID = -3975137725063289178L;
		
		private String newMac;
		
		private String location;
		
		/** 普通网卡：2；直通网卡：11 */
		private Integer devtype;

		/** SR-IOV对应主机网卡的名称 */
		private String ethName;
		
		/** SR-IOV驱动类型  */
		private String driverType;
		
		public String getLocation() {
			return location;
		}

		public void setLocation(String location) {
			this.location = location;
		}

		public void setNewMac(String newMac) {
			this.newMac = newMac;
		}

		public String getNewMac() {
			return newMac;
		}

		public Integer getDevtype() {
			return devtype;
		}

		public void setDevtype(Integer devtype) {
			this.devtype = devtype;
		}

		public String getEthName() {
			return ethName;
		}

		public void setEthName(String ethName) {
			this.ethName = ethName;
		}

		public String getDriverType() {
			return driverType;
		}

		public void setDriverType(String driverType) {
			this.driverType = driverType;
		}
	}
	
	/**
	 * 虚拟机输入设备：鼠标，写字板等
	 *
	 */
	@XmlRootElement(name = "input")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class InputConfig implements Serializable {

		private static final long serialVersionUID = -6466490097238188474L;
		
		/**类型 ：写字板，鼠标等*/
		private String type;
		
		private String model;
		
		private String bus;

		public String getBus() {
			return bus;
		}

		public void setBus(String bus) {
			this.bus = bus;
		}

		public void setModel(String model) {
			this.model = model;
		}

		public String getModel() {
			return model;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getType() {
			return type;
		}
	}
	
	/**
	 * USB设备
	 *
	 */
	@XmlRootElement(name = "usb")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class USBDevConfig implements Serializable {

		private static final long serialVersionUID = -6466490097238188474L;

		private String devName;
		private String vendorId;
		
		private String vendor;
		
		private String productId;
		
		private String product;
		
		private String bus;
		
		private String device;
		
		private String controller;
		public String getDevName() {
			return devName;
		}

		public void setDevName(String devName) {
			this.devName = devName;
		}

		public String getVendorId() {
			return vendorId;
		}

		public void setVendorId(String vendorId) {
			this.vendorId = vendorId;
		}

		public String getVendor() {
			return vendor;
		}

		public void setVendor(String vendor) {
			this.vendor = vendor;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getProduct() {
			return product;
		}

		public void setProduct(String product) {
			this.product = product;
		}

		public String getBus() {
			return bus;
		}

		public void setBus(String bus) {
			this.bus = bus;
		}

		public String getDevice() {
			return device;
		}

		public void setDevice(String device) {
			this.device = device;
		}

		public void setController(String controller) {
			this.controller = controller;
		}

		public String getController() {
			return controller;
		}
		
	}
	
	/**
	 * PCI设备
	 *
	 */
	@XmlRootElement(name = "pci")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class PCIDevConfig implements Serializable {
		private static final long serialVersionUID = -4036080037565365990L;
		private String devName;
		private String driver;
		private String bus;
		
		private String slot;
		
		private String function;
		private String vendorId;
		private String vendor;
		private String productId;
		private String product;
		private String ethName;
		public String getBus() {
			return bus;
		}

		public void setBus(String bus) {
			this.bus = bus;
		}

		public String getSlot() {
			return slot;
		}

		public void setSlot(String slot) {
			this.slot = slot;
		}

		public String getFunction() {
			return function;
		}

		public void setFunction(String function) {
			this.function = function;
		}	
		public String getDevName() {
			return devName;
		}

		public void setDevName(String devName) {
			this.devName = devName;
		}

		public String getDriver() {
			return driver;
		}

		public void setDriver(String driver) {
			this.driver = driver;
		}

		public String getVendorId() {
			return vendorId;
		}

		public void setVendorId(String vendorId) {
			this.vendorId = vendorId;
		}

		public String getVendor() {
			return vendor;
		}

		public void setVendor(String vendor) {
			this.vendor = vendor;
		}

		public String getProductId() {
			return productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public String getProduct() {
			return product;
		}

		public void setProduct(String product) {
			this.product = product;
		}

		public String getEthName() {
			return ethName;
		}

		public void setEthName(String ethName) {
			this.ethName = ethName;
		}	
		
	}
	/**
	 * 串口设备
	 *
	 */
	@XmlRootElement(name = "serial")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CharacterDeviceConfig implements Serializable {

		private static final long serialVersionUID = -6466490097238188474L;

		private Integer port;
		
		private String type;
		
		private String mode;
		
		private String devPath;
		
		private String connHost;
		
		private String connPort;
		
		private String bindIp;
		
		private String bindPort;

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public String getMode() {
			return mode;
		}

		public void setMode(String mode) {
			this.mode = mode;
		}

		public String getDevPath() {
			return devPath;
		}

		public void setDevPath(String devPath) {
			this.devPath = devPath;
		}

		public String getConnHost() {
			return connHost;
		}

		public void setConnHost(String connHost) {
			this.connHost = connHost;
		}

		public String getConnPort() {
			return connPort;
		}

		public void setConnPort(String connPort) {
			this.connPort = connPort;
		}

		public String getBindIp() {
			return bindIp;
		}

		public void setBindIp(String bindIp) {
			this.bindIp = bindIp;
		}

		public String getBindPort() {
			return bindPort;
		}

		public void setBindPort(String bindPort) {
			this.bindPort = bindPort;
		}

	}
	
	/**
	 * 图形化设备，如 "sdl", "vnc", "rdp", "desktop"
	 *
	 */
	@XmlRootElement(name = "graphics")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class GraphicsConfig implements Serializable {

		private static final long serialVersionUID = -6466490097238188474L;

		private String type;
		
		private Integer port;
		
		private String address;
		
		private String password;
		
		private String kayboardMap;
		
		private Integer enableVncProxy;
		
		private List<String> spiceTlsChannel;

		public List<String> getSpiceTlsChannel() {
			return spiceTlsChannel;
		}

		public void setSpiceTlsChannel(List<String> spiceTlsChannel) {
			this.spiceTlsChannel = spiceTlsChannel;
		}

		public String getType() {
			return type;
		}

		public void setType(String type) {
			this.type = type;
		}

		public Integer getPort() {
			return port;
		}

		public void setPort(Integer port) {
			this.port = port;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getKayboardMap() {
			return kayboardMap;
		}

		public void setKayboardMap(String kayboardMap) {
			this.kayboardMap = kayboardMap;
		}

		public Integer getEnableVncProxy() {
			return enableVncProxy;
		}

		public void setEnableVncProxy(Integer enableVncProxy) {
			this.enableVncProxy = enableVncProxy;
		}
		
	}
	
	@XmlRootElement(name = "numa")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class NumaConfig implements Serializable{  
		private static final long serialVersionUID = 7393744615464645002L;

		/** 主机numa节点数*/
		private Integer nodeSize;
		
		/** 虚拟机选用的numa节点*/
		private String nodeSet;
		
		/** 虚拟机NUMA关联关系，0：不关联；1：关联*/
		private Integer relevance;

		public Integer getNodeSize() {
			return nodeSize;
		}

		public void setNodeSize(Integer nodeSize) {
			this.nodeSize = nodeSize;
		}

		public String getNodeSet() {
			return nodeSet;
		}

		public void setNodeSet(String nodeSet) {
			this.nodeSet = nodeSet;
		}

		public Integer getRelevance() {
			return relevance;
		}

		public void setRelevance(Integer relevance) {
			this.relevance = relevance;
		}
		
	}
	
	@XmlRootElement(name = "tpm")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class TpmDev implements Serializable{
		private static final long serialVersionUID = 7393744615464645002L;

		/** 设备位置 */
		private String tpmPath;

		public String getTpmPath() {
			return tpmPath;
		}

		public void setTpmPath(String tpmPath) {
			this.tpmPath = tpmPath;
		}
	}
	
	@XmlRootElement(name = "redirdev")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class CAddress {
        
        private String type;
        
        private String bus;
        
        private String slot;
        
        private String function;
        
        private String domain;
        
        private String controller;
        
        private String unit;
        
        private String device;
        
        private String target;

        public String getType() {
            return type;
        }
        public void setType(String type) {
            this.type = type;
        }
        public String getBus() {
            return bus;
        }
        public void setBus(String bus) {
            this.bus = bus;
        }
        public String getSlot() {
            return slot;
        }
        public void setSlot(String slot) {
            this.slot = slot;
        }
        public String getFunction() {
            return function;
        }
        public void setFunction(String function) {
            this.function = function;
        }
        public String getDomain() {
            return domain;
        }
        public void setDomain(String domain) {
            this.domain = domain;
        }
        public String getController() {
            return controller;
        }
        public void setController(String controller) {
            this.controller = controller;
        }
        public String getUnit() {
            return unit;
        }
        public void setUnit(String unit) {
            this.unit = unit;
        }
        public String getDevice() {
            return device;
        }
        public void setDevice(String device) {
            this.device = device;
        }
        public String getTarget() {
            return target;
        }
        public void setTarget(String target) {
            this.target = target;
        }
        
    }
	/**
	 * 高级配置 
	 *  1、USB 重定向测策略
	 *  2、SSL通道 
	 *  null表示步修改。
	 */
	@XmlRootElement(name = "advance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class AdvanceConfig implements Serializable {
		private static final long serialVersionUID = 1L;
		
		/** USB 重定向 0表示没有，其他表示增加个数*/
		private Integer redirUsb;
		/** 是否设置ssl通道*/
		private Boolean spiceTls; 
		/** ssl通道 */
		@XmlElement(name = "spiceChannel")
		private List<String> spiceChannels;
		
		/**set the spice console*/
		private Boolean spiceSet;
		
		public Boolean getSpiceSet() {
			return spiceSet;
		}
		public void setSpiceSet(Boolean spiceSet) {
			this.spiceSet = spiceSet;
		}
		public Integer getRedirUsb() {
			return redirUsb;
		}
		public void setRedirUsb(Integer redirUsb) {
			this.redirUsb = redirUsb;
		}
		
		public Boolean getSpiceTls() {
			return spiceTls;
		}
		public void setSpiceTls(Boolean spiceTls) {
			this.spiceTls = spiceTls;
		}
		public List<String> getSpiceChannels() {
			return spiceChannels;
		}
		public void setSpiceChannels(List<String> spiceChannels) {
			this.spiceChannels = spiceChannels;
		}
	}
	@XmlRootElement(name = "gpu")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class GpuDev implements Serializable{  
		private static final long serialVersionUID = -7158212427028531420L;

		/** */
		private Long resPoolId;

		private String resPool;
		
		private Long businessTemId;
		
		private String businessTem;
		
		public String getResPool() {
			return resPool;
		}

		public void setResPool(String resPool) {
			this.resPool = resPool;
		}

		public String getBusinessTem() {
			return businessTem;
		}

		public void setBusinessTem(String businessTem) {
			this.businessTem = businessTem;
		}

		public Long getResPoolId() {
			return resPoolId;
		}
		
		public void setResPoolId(Long resPoolId) {
			this.resPoolId = resPoolId;
		}
		
		public Long getBusinessTemId() {
			return businessTemId;
		}
		
		public void setBusinessTemId(Long businessTemId) {
			this.businessTemId = businessTemId;
		}
		
	}
	@XmlRootElement(name = "shmem")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class ShmemDev implements Serializable{  
		private static final long serialVersionUID = -7158212427028531420L;

		private Integer size;

		public Integer getSize() {
			return size;
		}

		public void setSize(Integer size) {
			this.size = size;
		}
	}
	public ShmemDev getShmem() {
		return shmem;
	}

	public void setShmem(ShmemDev shmem) {
		this.shmem = shmem;
	}
}
