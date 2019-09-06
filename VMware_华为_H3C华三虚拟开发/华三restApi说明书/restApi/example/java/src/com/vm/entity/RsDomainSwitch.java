package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用于REST接口虚拟网卡XML转换
 * @author f10574
 */
@XmlRootElement(name = "domainSwitch")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainSwitch implements Serializable{

    private static final long serialVersionUID = 2513985029435449811L;

    private String mac;

    private String vSwitchName;

    private String ip;

    private String profileName;

    private String sysMask;

    private String sysGateway;

    private String sysDns;

    private String secondaryDns;

    private Boolean isBindIp;

    private Integer sysIpType;

    public String getMac() {
        return mac;
    }

    public void setMac(String mac) {
        this.mac = mac;
    }

    public String getvSwitchName() {
        return vSwitchName;
    }

    public void setvSwitchName(String vSwitchName) {
        this.vSwitchName = vSwitchName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getSysMask() {
        return sysMask;
    }

    public void setSysMask(String sysMask) {
        this.sysMask = sysMask;
    }

    public String getSysGateway() {
        return sysGateway;
    }

    public void setSysGateway(String sysGateway) {
        this.sysGateway = sysGateway;
    }

    public String getSysDns() {
        return sysDns;
    }

    public void setSysDns(String sysDns) {
        this.sysDns = sysDns;
    }

    public String getSecondaryDns() {
        return secondaryDns;
    }

    public void setSecondaryDns(String secondaryDns) {
        this.secondaryDns = secondaryDns;
    }

    
    public Boolean getIsBindIp() {
        return isBindIp;
    }

    
    public void setIsBindIp(Boolean isBindIp) {
        this.isBindIp = isBindIp;
    }

    
    public Integer getSysIpType() {
        return sysIpType;
    }

    
    public void setSysIpType(Integer sysIpType) {
        this.sysIpType = sysIpType;
    }
}
