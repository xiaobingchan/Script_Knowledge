package com.vm.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services 接口返回的虚拟机模板下的网络实体类。
 *
 * @author l10191
 */
@XmlRootElement(name="vmNetwork")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsTemplateNetwork {

    private static final long serialVersionUID = 1L;

    /** 模板id **/
    private Long id;
    
    /** 网卡类型：0桥接  1NAT 2不使用网络。 **/
    private Integer netType;
    
    /** 网卡地址。 **/
    private String mac;
    
    /** 网卡IP地址。 **/
    private String ipAddr;
    
    /** 网络名称。 **/
    private String name;
    
    /**　驱动模型，缺省为rtl8139。 **/
    private String deviceModel;
    
    /**　vlan id。 **/
    private String vlan;
    
    /**　出方向平均带宽。 **/
    private Long outAvgBandwidth;
    
    /**　入方向平均带宽。 **/
    private Long inAvgBandwidth;
    
    /**　转发模式。 **/
    private String mode;
    
    /** 网络策略模板名称。 **/
    private String profileName;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getNetType() {
        return netType;
    }

    public void setNetType(Integer netType) {
        this.netType = netType;
    }
    
    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel;
    }
    
    public String getVlan() {
        return vlan;
    }

    public void setVlan(String vlan) {
        this.vlan = vlan;
    }
    
    public Long getOutAvgBandwidth() {
        return outAvgBandwidth;
    }

    public void setOutAvgBandwidth(Long outAvgBandwidth) {
        this.outAvgBandwidth = outAvgBandwidth;
    }

    public Long getInAvgBandwidth() {
        return inAvgBandwidth;
    }

    public void setInAvgBandwidth(Long inAvgBandwidth) {
        this.inAvgBandwidth = inAvgBandwidth;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
    
}
