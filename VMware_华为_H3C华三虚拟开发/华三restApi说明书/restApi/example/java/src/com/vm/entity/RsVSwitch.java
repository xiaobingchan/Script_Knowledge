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
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services 接口返回的虚拟交换机实体类。
 *
 * @author z01500
 */
@XmlRootElement(name="vSwitch")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsVSwitch implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5732555124697253492L;

	/** 虚拟交换机ID。 */
    private Long id = null;
    
    /** 物理机ID。 **/
    private Long hostId;
    private String hostName;
    
    /** 虚拟交换机名称。 **/
    private String name;
    
    /** 描述。 **/
    private String description;

    /** 虚拟交换机端口个数。 **/
    private Integer portNum;
    
    /** 转发模式。0 : veb; 1: vepa; 2:多通道 **/
    private Integer mode;
    
    /** 物理接口。 **/
    private String pnic;
    
    /** IP地址。 **/
    private String address;
    
    /** 网络掩码。 **/
    private String netmask;
    
    /** 网关。**/
    private String gateway;
    
    /** 是否启用LACP(启用：true，未启用：false) **/
    private boolean enableLacp;
    
    private String bondMode;
    
    /** 获取虚拟交换机VLANID*/
    private Integer vlanId;
    
    /** 是否是 管理网卡 1：是*/
    private Integer isManage;
    
    /** 状态。取值：  0:不活动   1:活动 。**/
    private Integer status;
    
    /** 集群ID。 **/
    private Long clusterId;
    private String clusterName;
    
    /** 判断虚拟交换机是否被使用*/
    private boolean isRuningVmUseVSwitch;
    
    /** 虚拟交换机的增加方式，0：主机下增加。1：集群下增加**/
    private int flag;
    
    private List<String> pnicList;  
    
	public String getClusterName() {
		return clusterName;
	}

	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}

	public String getHostName() {
		return hostName;
	}

	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	public Long getClusterId() {
		return clusterId;
	}

	public void setClusterId(Long clusterId) {
		this.clusterId = clusterId;
	}

	public boolean isRuningVmUseVSwitch() {
		return isRuningVmUseVSwitch;
	}

	public void setRuningVmUseVSwitch(boolean isRuningVmUseVSwitch) {
		this.isRuningVmUseVSwitch = isRuningVmUseVSwitch;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public List<String> getPnicList() {
		return pnicList;
	}

	public void setPnicList(List<String> pnicList) {
		this.pnicList = pnicList;
	}

	public String getBondMode() {
		return bondMode;
	}

	public void setBondMode(String bondMode) {
		this.bondMode = bondMode;
	}

	public Integer getVlanId() {
		return vlanId;
	}

	public void setVlanId(Integer vlanId) {
		this.vlanId = vlanId;
	}

	public Integer getIsManage() {
		return isManage;
	}

	public void setIsManage(Integer isManage) {
		this.isManage = isManage;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getPortNum() {
		return portNum;
	}

	public void setPortNum(Integer portNum) {
		this.portNum = portNum;
	}

	public Integer getMode() {
		return mode;
	}

	public void setMode(Integer mode) {
		this.mode = mode;
	}

	public String getPnic() {
		return pnic;
	}

	public void setPnic(String pnic) {
		this.pnic = pnic;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getNetmask() {
		return netmask;
	}

	public void setNetmask(String netmask) {
		this.netmask = netmask;
	}

	public String getGateway() {
		return gateway;
	}

	public void setGateway(String gateway) {
		this.gateway = gateway;
	}

	public boolean isEnableLacp() {
		return enableLacp;
	}

	public void setEnableLacp(boolean enableLacp) {
		this.enableLacp = enableLacp;
	}
    
    
}
