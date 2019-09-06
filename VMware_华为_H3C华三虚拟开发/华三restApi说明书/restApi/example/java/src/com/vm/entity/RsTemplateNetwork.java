package com.vm.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services �ӿڷ��ص������ģ���µ�����ʵ���ࡣ
 *
 * @author l10191
 */
@XmlRootElement(name="vmNetwork")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsTemplateNetwork {

    private static final long serialVersionUID = 1L;

    /** ģ��id **/
    private Long id;
    
    /** �������ͣ�0�Ž�  1NAT 2��ʹ�����硣 **/
    private Integer netType;
    
    /** ������ַ�� **/
    private String mac;
    
    /** ����IP��ַ�� **/
    private String ipAddr;
    
    /** �������ơ� **/
    private String name;
    
    /**������ģ�ͣ�ȱʡΪrtl8139�� **/
    private String deviceModel;
    
    /**��vlan id�� **/
    private String vlan;
    
    /**��������ƽ������ **/
    private Long outAvgBandwidth;
    
    /**���뷽��ƽ������ **/
    private Long inAvgBandwidth;
    
    /**��ת��ģʽ�� **/
    private String mode;
    
    /** �������ģ�����ơ� **/
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
