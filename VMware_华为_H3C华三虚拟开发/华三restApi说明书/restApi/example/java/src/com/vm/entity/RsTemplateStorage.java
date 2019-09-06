package com.vm.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services �ӿڷ��ص������ģ���µ�����ʵ���ࡣ
 *
 * @author l10191
 */
@XmlRootElement(name="vmStorage")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsTemplateStorage {

    private static final long serialVersionUID = 1L;
    
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

