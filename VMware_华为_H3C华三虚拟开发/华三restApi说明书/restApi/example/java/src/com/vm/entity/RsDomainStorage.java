package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services 接口返回的虚拟机存储信息实体类。
 *
 * @author z01500
 */
@XmlRootElement(name="storage")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainStorage implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = -2123951666312182862L;
    /** 记录ID。 */
    private Long id = null;
    
    /** 存储文件名。 **/
    private String storeFile;
    
    /** 容量。 **/
    private Long capacity;    
    
    /** 取值为：ide scsi virtio usb    SCSI("scsi"),\r\n\r\n    VIRTIO("virtio"),\r\n\r\n    USB("usb");。 **/
    private String targetBus;
    
    /**　类型。如file block **/
    private String type;
    
    /**　取值为：hda hdb hdc　。 **/
    private String device;
    
    /**　取值为：Read-Write  Read-ForceWrite  Read-Only。 **/
    private String format;
    
    /**　存储卷类型。如raw **/
    private String driveType;
    
    /** 分配类型:0指定 1动态分配。 **/
    private Integer assignType;
    
    /**　取值为：disk cdrom floppy　。 **/
    private String diskDevice;
    
    /** 基础镜像存储文件路径。 */
    private String backingStore;
    
    /** 磁盘缓存方式 */
    private String cacheType;
    
    /** 镜像存储文件所在的存储池名称。 */
    private String poolName;
    
    /** 镜像存储文件所在的存储池类型 */
    private String poolType;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStoreFile() {
        return storeFile;
    }

    public void setStoreFile(String storeFile) {
        this.storeFile = storeFile;
    }

    public Long getCapacity() {
		return capacity;
	}

	public void setCapacity(Long capacity) {
		this.capacity = capacity;
	}

	public String getTargetBus() {
        return targetBus;
    }

    public void setTargetBus(String targetBus) {
        this.targetBus = targetBus;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

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

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public Integer getAssignType() {
        return assignType;
    }

    public void setAssignType(Integer assignType) {
        this.assignType = assignType;
    }

    public String getDiskDevice() {
        return diskDevice;
    }

    public void setDiskDevice(String diskDevice) {
        this.diskDevice = diskDevice;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
    
    public String getBackingStore() {
		return backingStore;
	}

	public void setBackingStore(String backingStore) {
		this.backingStore = backingStore;
	}

	public String getCacheType() {
		return cacheType;
	}

	public void setCacheType(String cacheType) {
		this.cacheType = cacheType;
	}
	
	public String getPoolName() {
		return poolName;
	}

	public void setPoolName(String poolName) {
		this.poolName = poolName;
	}

	public String getPoolType() {
		return poolType;
	}

	public void setPoolType(String poolType) {
		this.poolType = poolType;
	}

	@Override
    public String toString() {
        return String.format("VmStorage {id=%d, mac=%s}", id, storeFile);
    }
}
