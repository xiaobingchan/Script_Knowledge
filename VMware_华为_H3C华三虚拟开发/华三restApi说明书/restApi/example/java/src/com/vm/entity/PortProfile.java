package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * 模板信息实体。
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="portProfile")
public class PortProfile implements Serializable{

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 4992800501041444894L;
	/** ID。 */
    @XmlElement
    private Long id = null;
    
    /** 模板名。 **/
    @XmlElement
    private String name;
    
    /** 模板描述。 **/
    @XmlElement
    private String description;
    
    /** 启用VLAN, 1 :启用，0:不启用**/
    @XmlElement
    private Integer enableVlan;
    
    /** VLAN ID。 **/
    @XmlElement
    private Integer vlanId;
    
    /** 流的入方向流限制。 0：不启用，1：启用**/
    @XmlElement
    private Integer inbound;
    
    /** 入方向平均带宽**/
    @XmlElement
    private Long inAvgBandwidth; 
    
    /** 入方向峰值带宽**/
    @XmlElement
    private Long inPeakBandwidth;
    
    /** 突发缓冲大小**/
    @XmlElement
    private Long inBurstSize;
    
    /** 启用出方向流量限制, 1 :启用，0:启用**/
    @XmlElement
    private Integer outbound;
    
    /** 出方向平均带宽**/
    @XmlElement
    private Long outAvgBandwidth; 
    
    /** 出方向平均带宽**/
    @XmlElement
    private Long outPeakBandWidth;
    
    /** 突发缓冲大小**/
    @XmlElement
    private Long outBurstSize;
    
    /** 启用VSI, 1 :启用，0:不启用**/
    @XmlElement
    private Integer enableVsi;
    
    /** VSI管理ID。 **/
    @XmlElement
    private String vsiMngId;
    
    /** 网络资源。 **/
    @XmlElement
    private Long vsiNetResource;
    
    /** VSI类型ID。 **/
    @XmlElement
    private Long vsiTypeId;
    
    /** VSI类型版本。 **/
    @XmlElement
    private Long vsiTypeVer;
    
    /** VSI类型版本ID。 **/
    @XmlElement
    private Long vsiTypeVerId;

    
    /** VSI ID格式。 **/
    @XmlElement
    private String vsiIdFormat;
    
    /** 操作员分组ID。*/
	@XmlTransient
	private Long operatorGroupId = null;
	
	/** 操作员分组编码。 */
    @XmlTransient
    private String operatorGroupCode = null;
    
    /** ACL策略 */
    private Long aclStrategyId;
   
    /** ACL 名称 */
    @XmlElement
    private String aclName;
    
	public void setId(Long id) {
		this.id = id;
	}
	public Long getId() {
		return id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setVlanId(Integer vlanId) {
		this.vlanId = vlanId;
	}
	public Integer getVlanId() {
		return vlanId;
	}
	public void setInbound(Integer inbound) {
		this.inbound = inbound;
	}
	public Integer getInbound() {
		return inbound;
	}
	public void setInAvgBandwidth(Long inAvgBandwidth) {
		this.inAvgBandwidth = inAvgBandwidth;
	}
	public Long getInAvgBandwidth() {
		return inAvgBandwidth;
	}
	public void setInPeakBandwidth(Long inPeakBandwidth) {
		this.inPeakBandwidth = inPeakBandwidth;
	}
	public Long getInPeakBandwidth() {
		return inPeakBandwidth;
	}
	public void setInBurstSize(Long inBurstSize) {
		this.inBurstSize = inBurstSize;
	}
	public Long getInBurstSize() {
		return inBurstSize;
	}
	public void setOutbound(Integer outbound) {
		this.outbound = outbound;
	}
	public Integer getOutbound() {
		return outbound;
	}
	public void setOutPeakBandWidth(Long outPeakBandWidth) {
		this.outPeakBandWidth = outPeakBandWidth;
	}
	public Long getOutPeakBandWidth() {
		return outPeakBandWidth;
	}
	public void setOutBurstSize(Long outBurstSize) {
		this.outBurstSize = outBurstSize;
	}
	public Long getOutBurstSize() {
		return outBurstSize;
	}
	
	public Long getOperatorGroupId() {
		return operatorGroupId;
	}
	public void setOperatorGroupId(Long operatorGroupId) {
		this.operatorGroupId = operatorGroupId;
	}
	public String getOperatorGroupCode() {
		return operatorGroupCode;
	}
	public void setOperatorGroupCode(String operatorGroupCode) {
		this.operatorGroupCode = operatorGroupCode;
	}
	public Long getOutAvgBandwidth() {
		return outAvgBandwidth;
	}
	public void setOutAvgBandwidth(Long outAvgBandwidth) {
		this.outAvgBandwidth = outAvgBandwidth;
	}
	public Integer getEnableVsi() {
		return enableVsi;
	}
	public void setEnableVsi(Integer enableVsi) {
		this.enableVsi = enableVsi;
	}
	public String getVsiMngId() {
		return vsiMngId;
	}
	public void setVsiMngId(String vsiMngId) {
		this.vsiMngId = vsiMngId;
	}
	public Long getVsiTypeId() {
		return vsiTypeId;
	}
	public void setVsiTypeId(Long vsiTypeId) {
		this.vsiTypeId = vsiTypeId;
	}
	public Long getVsiTypeVer() {
		return vsiTypeVer;
	}
	public void setVsiTypeVer(Long vsiTypeVer) {
		this.vsiTypeVer = vsiTypeVer;
	}
	public String getVsiIdFormat() {
		return vsiIdFormat;
	}
	public void setVsiIdFormat(String vsiIdFormat) {
		this.vsiIdFormat = vsiIdFormat;
	}
	public void setVsiNetResource(Long vsiNetResource) {
		this.vsiNetResource = vsiNetResource;
	}
	public Long getVsiNetResource() {
		return vsiNetResource;
	}
	public void setVsiTypeVerId(Long vsiTypeVerId) {
		this.vsiTypeVerId = vsiTypeVerId;
	}
	public Long getVsiTypeVerId() {
		return vsiTypeVerId;
	}
    
	public Long getAclStrategyId() {
		return aclStrategyId;
	}
	public void setAclStrategyId(Long aclStrategyId) {
		this.aclStrategyId = aclStrategyId;
	}
	public Integer getEnableVlan() {
		return enableVlan;
	}
	public void setEnableVlan(Integer enableVlan) {
		this.enableVlan = enableVlan;
	}
	public void setAclName(String aclName) {
		this.aclName = aclName;
	}
	public String getAclName() {
		return aclName;
	}
    
	/**
	 * 是否启用 VSI
	 * @return true-是
	 */
	public boolean isEnableVsi() {
		return getEnableVsi() != null && getEnableVsi()== 1;
	}
    
}
