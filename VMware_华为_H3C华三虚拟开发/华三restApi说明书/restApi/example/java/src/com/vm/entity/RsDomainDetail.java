package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.vm.entity.RsDomainForModify.AdvanceConfig;
import com.vm.entity.RsDomainForModify.BasicConfig;
import com.vm.entity.RsDomainForModify.BootDevConfig;
import com.vm.entity.RsDomainForModify.CAddress;
import com.vm.entity.RsDomainForModify.CdromConfig;
import com.vm.entity.RsDomainForModify.CharacterDeviceConfig;
import com.vm.entity.RsDomainForModify.CpuConfig;
import com.vm.entity.RsDomainForModify.GpuDev;
import com.vm.entity.RsDomainForModify.GraphicsConfig;
import com.vm.entity.RsDomainForModify.InputConfig;
import com.vm.entity.RsDomainForModify.MemoryConfig;
import com.vm.entity.RsDomainForModify.NetworkConfig;
import com.vm.entity.RsDomainForModify.NumaConfig;
import com.vm.entity.RsDomainForModify.PCIDevConfig;
import com.vm.entity.RsDomainForModify.ShmemDev;
import com.vm.entity.RsDomainForModify.StorageConfig;
import com.vm.entity.RsDomainForModify.TpmDev;
import com.vm.entity.RsDomainForModify.USBDevConfig;

/**
 * 虚拟机详细信息，用于修改虚拟机，对应虚拟机修改界面的数据
 * @author m10374
 *
 */
@XmlRootElement(name = "domain")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainDetail implements Serializable {

	private static final long serialVersionUID = 3062995209808100337L;
	
	/** 虚拟机id */
	private Long id;
	
	/** 虚拟机名称 */
	private String name;
	
	/** 虚拟机显示名称*/
	private String title;
	
	/** 显卡类型 */
	private List<String> videoType;
	
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
	
	/** 写字板 */
	@XmlElement(name = "input")
	private List<InputConfig> inputs;

	@XmlElement(name = "cdrom")
	private CdromConfig cdromConfig;

	@XmlElement(name = "storage")
	private List<StorageConfig> storages;

	@XmlElement(name = "network")
	private List<NetworkConfig> networks;
	
	@XmlElement(name = "usb")
	private List<USBDevConfig> usbs;

	@XmlElement(name = "pci")
	private List<PCIDevConfig> pcis;
    /** Serial ports. */
    @XmlElement(name = "serial")
	private List<CharacterDeviceConfig> serials;
    
    /** 图形化设备，如 "sdl", "vnc", "rdp", "desktop" */
    @XmlElement(name = "graphics")
    private List<GraphicsConfig> graphicses;
    
    /** numa */
    @XmlElement(name = "numa")
    private NumaConfig numaConfig;
    
    @XmlElement(name = "tpm")
    private TpmDev tpmDev;
    
    @XmlElement(name = "gpu")
    private GpuDev gpuDev;
    
    @XmlElement(name = "shmem")
    private ShmemDev shmemDev;
    
    public GpuDev getGpuDev() {
		return gpuDev;
	}

	public void setGpuDev(GpuDev gpuDev) {
		this.gpuDev = gpuDev;
	}

	public ShmemDev getShmemDev() {
		return shmemDev;
	}

	public void setShmemDev(ShmemDev shmemDev) {
		this.shmemDev = shmemDev;
	}


	/** USB Redirection */
    @XmlElement(name = "redirdev")
    private List<CAddress> usbRedirdev;
    
	@XmlElement(name = "advance")
	private AdvanceConfig advance;
	
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
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public void setCdromConfig(CdromConfig cdromConfig) {
		this.cdromConfig = cdromConfig;
	}

	public CdromConfig getCdromConfig() {
		return cdromConfig;
	}

	public List<String> getVideoType() {
		return videoType;
	}

	public void setVideoType(List<String> videoType) {
		this.videoType = videoType;
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

	public void setStorages(List<StorageConfig> storages) {
		this.storages = storages;
	}

	public List<StorageConfig> getStorages() {
		return storages;
	}

	public void setNetworks(List<NetworkConfig> networks) {
		this.networks = networks;
	}

	public List<NetworkConfig> getNetworks() {
		return networks;
	}

	public void setInputs(List<InputConfig> inputs) {
		this.inputs = inputs;
	}

	public List<InputConfig> getInputs() {
		return inputs;
	}

	public void setSoundType(String soundType) {
		this.soundType = soundType;
	}
	
	public String getSoundType() {
		return soundType;
	}

	public void setUsbs(List<USBDevConfig> usbs) {
		this.usbs = usbs;
	}

	public List<USBDevConfig> getUsbs() {
		return usbs;
	}

	public List<PCIDevConfig> getPcis() {
		return pcis;
	}

	public void setPcis(List<PCIDevConfig> pcis) {
		this.pcis = pcis;
	}
	public void setSerials(List<CharacterDeviceConfig> serials) {
		this.serials = serials;
	}

	public List<CharacterDeviceConfig> getSerials() {
		return serials;
	}

	public void setGraphicses(List<GraphicsConfig> graphicses) {
		this.graphicses = graphicses;
	}

	public List<GraphicsConfig> getGraphicses() {
		return graphicses;
	}

	public NumaConfig getNumaConfig() {
		return numaConfig;
	}

	public void setNumaConfig(NumaConfig numaConfig) {
		this.numaConfig = numaConfig;
	}
	public List<CAddress> getUsbRedirdev() {
		return usbRedirdev;
	}

	public void setUsbRedirdev(List<CAddress> usbRedirdev) {
		this.usbRedirdev = usbRedirdev;
	}
}