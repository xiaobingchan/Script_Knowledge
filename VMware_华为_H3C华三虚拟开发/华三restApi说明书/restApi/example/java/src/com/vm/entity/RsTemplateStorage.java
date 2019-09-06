package com.vm.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services 接口返回的虚拟机模板下的网络实体类。
 *
 * @author l10191
 */
@XmlRootElement(name="vmStorage")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsTemplateStorage {

    private static final long serialVersionUID = 1L;
    
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

}

