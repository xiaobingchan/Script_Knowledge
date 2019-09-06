package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "storageVolume")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RsStorageVolume implements Serializable {

	private static final long serialVersionUID = 6719013255424092751L;

	/** 存储卷文件名称。*/
    private String name;
    
    /** 存储卷文件容量大小, 单位为MB。*/
    private Double size;
    
    /** 存储已使用空间, 单位为MB。 */
    private Double allocation;
    
    /** 存储卷文件格式。*/
    private String format;
    
    /** 存储卷类型, 如file, block。*/
    private String type;

    /** 该存储卷使用者(虚拟机名称)列表。*/
    private String users;
    
    /** 基础存储卷列表。*/
    private String backingStore;
    
    /** 级别，0级增量文件，大于0表示基础文件。 **/
    private Integer level;
    
    private boolean isTempImg;

	public boolean isTempImg() {
		return isTempImg;
	}

	public void setTempImg(boolean isTempImg) {
		this.isTempImg = isTempImg;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getSize() {
		return size;
	}

	public void setSize(Double size) {
		this.size = size;
	}

	public Double getAllocation() {
		return allocation;
	}

	public void setAllocation(Double allocation) {
		this.allocation = allocation;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsers() {
		return users;
	}

	public void setUsers(String users) {
		this.users = users;
	}

	public String getBackingStore() {
		return backingStore;
	}

	public void setBackingStore(String backingStore) {
		this.backingStore = backingStore;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}
}
