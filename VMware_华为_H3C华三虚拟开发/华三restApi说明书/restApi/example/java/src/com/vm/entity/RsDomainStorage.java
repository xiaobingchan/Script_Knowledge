package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services �ӿڷ��ص�������洢��Ϣʵ���ࡣ
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
    /** ��¼ID�� */
    private Long id = null;
    
    /** �洢�ļ����� **/
    private String storeFile;
    
    /** ������ **/
    private Long capacity;    
    
    /** ȡֵΪ��ide scsi virtio usb    SCSI("scsi"),\r\n\r\n    VIRTIO("virtio"),\r\n\r\n    USB("usb");�� **/
    private String targetBus;
    
    /**�����͡���file block **/
    private String type;
    
    /**��ȡֵΪ��hda hdb hdc���� **/
    private String device;
    
    /**��ȡֵΪ��Read-Write  Read-ForceWrite  Read-Only�� **/
    private String format;
    
    /**���洢�����͡���raw **/
    private String driveType;
    
    /** ��������:0ָ�� 1��̬���䡣 **/
    private Integer assignType;
    
    /**��ȡֵΪ��disk cdrom floppy���� **/
    private String diskDevice;
    
    /** ��������洢�ļ�·���� */
    private String backingStore;
    
    /** ���̻��淽ʽ */
    private String cacheType;
    
    /** ����洢�ļ����ڵĴ洢�����ơ� */
    private String poolName;
    
    /** ����洢�ļ����ڵĴ洢������ */
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
