package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * ������������޸����������Ӧ����������޸�
 * @author m10374
 *
 */
@XmlRootElement(name = "domain")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainForModify implements Serializable {

	private static final long serialVersionUID = 3062995209808100337L;
	
	/** �����id */
	private Long id;
	
	/** ��������� */
	private String name;
	
	/** �������ʾ���� */
	private String title;
	
	/** �Կ����� */
	private String videoType;

	/** �������� */
	private String soundType;
	
	/** �Ƿ�����VNC ����*/
	private Integer enableVncProxy;
	
	/** �����������Ϣ */
	@XmlElement(name = "basic")
	private BasicConfig basicConfig;
	
	/** �����cpu������Ϣ*/
	@XmlElement(name = "cpu")
	private CpuConfig cpuConfig;

	/** ������ڴ档�ס� **/
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

	/** ��ȡ���������������Ϣ */
	public BasicConfig getBasicConfig() {
		return basicConfig;
	}
	
	public void setCpuConfig(CpuConfig cpuConfig) {
		this.cpuConfig = cpuConfig;
	}

	/** ��ȡ cpu������Ϣ */
	public CpuConfig getCpuConfig() {
		return cpuConfig;
	}

	public void setBootDevConfig(BootDevConfig bootDevConfig) {
		this.bootDevConfig = bootDevConfig;
	}

	/** ��ȡ�����豸������Ϣ */
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
	 * �����������Ϣ����Ӧ�����Ҫ�޸�
	 */
	@XmlRootElement(name = "basic")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class BasicConfig implements Serializable {

		private static final long serialVersionUID = 5299057594321798763L;
		
		/** �Ƿ�����PAE��1-���ã�0-������  */
		private Integer pae;

		/** �Ƿ�����ACPI��1-���ã�0-������  */
		private Integer acpi;

		/** �Ƿ�����APIC��1-���ã�0-������  */
		private Integer apic;
		
		/** ʱ�����ã����أ�localtime������ʱ�� ��utc*/
		private String clock;
		
		/** IO���ȼ� ��=500����=300����=200 */
		private Integer blkiotune;
		
		/** ������������ȼ� 0:�ͼ� 1:�м� 2:�߼��� */
		private Integer startPriority;

		/** ������Ƿ������Զ�Ǩ�ơ�1:����0���� */
		private Integer autoMigrate = null;
		
		/** ϵͳ���ϲ��� 0:������ 1:�������� 2:����Ǩ�ơ� */
		private Integer osha = 0;
		
		/** ͬ�������ʱ��������ʱ��  1:ͬ����0����ͬ��*/
		private Integer timeSync = 0;
		
		/** �����HA�����־ 1:�����߿ɿ��ԣ�0���رո߿ɿ��� */
		private Integer haManage = null;
		
		/**���������ϵͳ��Ϣ*/
		private String osVersion;
		
		/** �����������Ϣ */
		private String desc;
		
		/** �������*/
		private String manager;

		/** ϵͳ�ܹ� �磺X86_64*/
		private String architecture;
		
		/** ģ���� */
		private String emulator;
		
		/** �Ѿ����ڵ�ControllerList */
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

		/** ����������������ȼ� 0:�ͼ� 1:�м� 2:�߼��� */
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
	 * �����CPU������Ϣ
	 *
	 */
	@XmlRootElement(name = "cpu")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class CpuConfig  implements Serializable {
		
		private static final long serialVersionUID = 579524840502801969L;

		/** CPU����  */
		private Integer cpuSockets;
		
		/** CPU���� */
		private Integer cpuCores;

		/** �����PU�ŵ����ȼ��� ��=1024����=512����=256 */
		private Integer cpuShares;

		/** CPU������������CPU������ */
		private Integer maxCpuNum;
		
		/** CPU����ģʽ */
		private String cpuMode;
		/** x86_64 or i686*/
		private String cpuArch;
		@XmlElement(name = "bindcpu")
		private List<BindPhysicalCpu> bindcpuList;
		
	    /**CPU��������ֵ*/
	    private Double cpuQuota;
	    /**CPU�������Ƶ�λ*/
	    private String cpuQuotaUnit;
	    /**CPU������С����*/
	    private Long cpuMinRate;
	    /**CPU�����������*/
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

		/** cpu �� ����cpu���� */
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
	 * ������ڴ�����
	 *
	 */
	@XmlRootElement(name = "memory")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class MemoryConfig implements Serializable {

		private static final long serialVersionUID = -8355993751581288127L;
		
		/** ��С */
		private Long size;
		
		/** �ڴ�Ԥ���ٷֱ� 0-100 */
		private Integer memoryBacking;
		
		/** �ڴ���Դ���ȼ� */
		private Integer memoryPriority;
		
		/** �ڴ�����*/
	    private Double memoryLimit;
	    
	    private String memoryLimitUnit;

		/** �ڴ浥λ */
		private String memoryUnit;
		
		/** ��ʾ�ڴ� */
		private Double memoryInit;
		
	    
		/** ������*/
		private Long maxMemory;
		
		/** �����ڴ� */
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
	 * ����������豸����
	 */
	@XmlRootElement(name = "bootdev")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class BootDevConfig implements Serializable {

		private static final long serialVersionUID = 5244197474590131195L;
		
		/** �Զ����� */
		private Integer autoStart;
		
		/** ����������豸��˳����ʽ��dev1,dev2,net1...*/
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
	 * ������洢����
	 * 
	 */
	@XmlRootElement(name = "storage")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class StorageConfig implements Serializable {

		private static final long serialVersionUID = -3042963799948646719L;
		
		/** �豸��������: disk, cdrom, floppy */
		private String device;
		
		/** �豸���� */
		private String deviceName;
		
		/** �洢������ file block */
		private String fileType;
		
		/** �������ͣ�IDE Ӳ�̡�SCSI Ӳ�̡�USBӲ�̡�VirtioӲ�̡�IDE���������� */
		private String type;
		
		/** �洢��·�� */
		private String path;
		
		/** �洢��ʽ  raw qcow2*/
		private String format;

	    /** �豸����  ȡֵΪ��ide scsi virtio usb    SCSI("scsi"), VIRTIO("virtio"), USB("usb");  **/
	    private String targetBus;
		
		/** ����洢���� */
		private Long size;
		
		/** ��ʹ�ô�С */
		private Long allocation;
		
		/** ���̻��淽ʽ */
		private String cacheType;

		/** Ϊ����Ӧ������������� */
		@XmlTransient
		private boolean shareable = false;
		
		/** Ϊ����Ӧ������������� */
		@XmlTransient
		private boolean readonly = false;
		
		/** ��������: 1 ������0 ������ */
		private Integer snapShot;
		
		/** max size (pool size)*/
		private Long maxSize;
		
		/** ���������ļ� */
		private String backingFile;
		
		private Long readBytesSec = null;
		private Long writeBytesSec = null;
		private Long readIopsSec = null;
		private Long writeIopsSec = null;
		
		/** ǰָ̨����Controller*/
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
		 * shareable ������ʱ���ã��ͽ�����Ӧ����ԶΪfalse
		 * @param shareable
		 */
		public void setShareable(boolean shareable) {
			this.shareable = shareable;
		}

		public boolean isReadonly() {
			return readonly;
		}

		/**
		 * readonly ������ʱ���ã��ͽ�����Ӧ����ԶΪfalse
		 * @param readonly
		 */
		public void setReadonly(boolean readonly) {
			this.readonly = readonly;
		}

		public void setCacheType(String cache) {
			this.cacheType = cache;
		}
		
		/**
		 * ��ȡ���̻��淽ʽ
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
	 * ����������Ϣ
	 *
	 */
	public static class CdromConfig implements Serializable {

		private static final long serialVersionUID = -3011173664105311201L;
		
		/** �豸���� */
		private String device;

		/** ���� ��connect/disconnect*/
		private String operation;
		
		/** ԭ�豸���� ��block��file, tool*/
		private String type;
		
		/** ·�� */
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

		/** �豸���ͣ�ide:IDE Ӳ�̡�scsi:SCSI Ӳ�̡�usb:USBӲ�̡�virtio:VirtioӲ�̡�cdrom:IDE������floppy:���� */
		public String getType() {
			return type;
		}

		/** �豸���ͣ�ide:IDE Ӳ�̡�scsi:SCSI Ӳ�̡�usb:USBӲ�̡�virtio:VirtioӲ�̡�cdrom:IDE������floppy:���� */
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
	 * �������ã���{@link RsDomainNetwork}������������newMac���ԣ������޸�mac��ַ
	 * ������ location���ԣ� ֱͨ������pci��ַ��
	 * ������ devtype���ԣ�����ֱͨ������11 �� ��ͨ������2
	 */
	public static class NetworkConfig extends RsDomainNetwork {

		private static final long serialVersionUID = -3975137725063289178L;
		
		private String newMac;
		
		private String location;
		
		/** ��ͨ������2��ֱͨ������11 */
		private Integer devtype;

		/** SR-IOV��Ӧ�������������� */
		private String ethName;
		
		/** SR-IOV��������  */
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
	 * ����������豸����꣬д�ְ��
	 *
	 */
	@XmlRootElement(name = "input")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class InputConfig implements Serializable {

		private static final long serialVersionUID = -6466490097238188474L;
		
		/**���� ��д�ְ壬����*/
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
	 * USB�豸
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
	 * PCI�豸
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
	 * �����豸
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
	 * ͼ�λ��豸���� "sdl", "vnc", "rdp", "desktop"
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

		/** ����numa�ڵ���*/
		private Integer nodeSize;
		
		/** �����ѡ�õ�numa�ڵ�*/
		private String nodeSet;
		
		/** �����NUMA������ϵ��0����������1������*/
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

		/** �豸λ�� */
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
	 * �߼����� 
	 *  1��USB �ض�������
	 *  2��SSLͨ�� 
	 *  null��ʾ���޸ġ�
	 */
	@XmlRootElement(name = "advance")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class AdvanceConfig implements Serializable {
		private static final long serialVersionUID = 1L;
		
		/** USB �ض��� 0��ʾû�У�������ʾ���Ӹ���*/
		private Integer redirUsb;
		/** �Ƿ�����sslͨ��*/
		private Boolean spiceTls; 
		/** sslͨ�� */
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
