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
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services �ӿڷ��ص�������洢ʵ���ࡣ
 *
 * @author z01500
 */
@XmlRootElement(name="storagePool")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsStoragePool implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5732555124697253492L;
	
	
	/** ����ID*/
	private Long hostId;
	/** ��������*/
	private String hostName;
	private String srcHost;
	private String hostIp;
	private String remoteDir;
	private String pwd;
	private String srcName;
	private String fsType;
	private Long fsId;
	private String hpName;
	private String lun;
	private Boolean multipath;
	private String srcHostNew;
	private String naa;
	private String fcType;
	private String localPortName;
	private String fsName;
	
    @XmlElement(name = "rsFsLunInfo")
    private List<RsFsLunInfo> rsFsLunInfoList;
	
    /** �洢���ơ� **/
    private String name;
    
    /** �洢������ **/
    private String title;
    
    /** �洢·���� **/
    private String path;
    
    /** �洢���ͣ�ȡֵΪnetfs��iscsi��fs�� **/
    private String type;
    
    /** �洢��С����λΪMB�� **/
    private Long totalSize;
    
    /** �洢ʣ���С����λΪMB��**/
    private Long freeSize;
    
    /** ״̬ 1���,0:���*/
    private Integer status;
    
	/** Դ·����*/
	private String srcPath;
	
	/** Դ����IP��*/
	private String srcHostIP;
	
	/** �û�����*/
	private String userName;
    
    /** �Ƿ��Զ����� */
    private boolean autoStart;
    
    private String destName;
    
    private String poolName;
    
	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public String getSrcHost() {
		return srcHost;
	}

	public void setSrcHost(String srcHost) {
		this.srcHost = srcHost;
	}

	public String getHostIp() {
		return hostIp;
	}

	public void setHostIp(String hostIp) {
		this.hostIp = hostIp;
	}

	public String getRemoteDir() {
		return remoteDir;
	}

	public void setRemoteDir(String remoteDir) {
		this.remoteDir = remoteDir;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getSrcName() {
		return srcName;
	}

	public void setSrcName(String srcName) {
		this.srcName = srcName;
	}

	public String getFsType() {
		return fsType;
	}

	public void setFsType(String fsType) {
		this.fsType = fsType;
	}

	public Long getFsId() {
		return fsId;
	}

	public void setFsId(Long fsId) {
		this.fsId = fsId;
	}

	public String getHpName() {
		return hpName;
	}

	public void setHpName(String hpName) {
		this.hpName = hpName;
	}

	public String getLun() {
		return lun;
	}

	public void setLun(String lun) {
		this.lun = lun;
	}

	public Boolean getMultipath() {
		return multipath;
	}

	public void setMultipath(Boolean multipath) {
		this.multipath = multipath;
	}

	public String getSrcHostNew() {
		return srcHostNew;
	}

	public void setSrcHostNew(String srcHostNew) {
		this.srcHostNew = srcHostNew;
	}

	public String getNaa() {
		return naa;
	}

	public void setNaa(String naa) {
		this.naa = naa;
	}

	public String getFcType() {
		return fcType;
	}

	public void setFcType(String fcType) {
		this.fcType = fcType;
	}

	public String getLocalPortName() {
		return localPortName;
	}

	public void setLocalPortName(String localPortName) {
		this.localPortName = localPortName;
	}

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	public Long getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(Long totalSize) {
		this.totalSize = totalSize;
	}
	
	public String getSrcPath() {
		return srcPath;
	}

	public void setSrcPath(String srcPath) {
		this.srcPath = srcPath;
	}

	public String getSrcHostIP() {
		return srcHostIP;
	}

	public void setSrcHostIP(String srcHostIP) {
		this.srcHostIP = srcHostIP;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public boolean isAutoStart() {
		return autoStart;
	}

	public void setAutoStart(boolean autoStart) {
		this.autoStart = autoStart;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getFreeSize() {
		return freeSize;
	}

	public void setFreeSize(Long freeSize) {
		this.freeSize = freeSize;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

    public String getDestName() {
        return destName;
    }

    public void setDestName(String destName) {
        this.destName = destName;
    }

    public String getPoolName() {
        return poolName;
    }

    public void setPoolName(String poolName) {
        this.poolName = poolName;
    }

	public List<RsFsLunInfo> getRsFsLunInfoList() {
		return rsFsLunInfoList;
	}

	public void setRsFsLunInfoList(List<RsFsLunInfo> rsFsLunInfoList) {
		this.rsFsLunInfoList = rsFsLunInfoList;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}