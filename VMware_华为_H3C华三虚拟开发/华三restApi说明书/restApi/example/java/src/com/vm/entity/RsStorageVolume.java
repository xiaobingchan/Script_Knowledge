package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "storageVolume")
@XmlAccessorType(XmlAccessType.PROPERTY)
public class RsStorageVolume implements Serializable {

	private static final long serialVersionUID = 6719013255424092751L;

	/** �洢���ļ����ơ�*/
    private String name;
    
    /** �洢���ļ�������С, ��λΪMB��*/
    private Double size;
    
    /** �洢��ʹ�ÿռ�, ��λΪMB�� */
    private Double allocation;
    
    /** �洢���ļ���ʽ��*/
    private String format;
    
    /** �洢������, ��file, block��*/
    private String type;

    /** �ô洢��ʹ����(���������)�б�*/
    private String users;
    
    /** �����洢���б�*/
    private String backingStore;
    
    /** ����0�������ļ�������0��ʾ�����ļ��� **/
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
