package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cluster")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsCluster implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/** 集群ID。 */
    private Long id = 0l;
    
    /** 主机池ID。*/
	private Long hostPoolId = 0l;
    
    /** 集群名称。 **/
    private String name = "";
    
    /** 集群描述。 **/
    private String description = "";
    
    /** 是否启用HA  0:不启用HA 1:启用HA。**/
    private Integer enableHA = 0;
    
    /** 启动优先级  0:低级  1:中级 2:高级**/
    private Integer priority = 1;
    
    /** 是否启用LB 0:不启用负载均衡 1:启用负载均衡。**/
    private Integer enableLB = 0;
    
    /** 持续时间。**/
    private Integer persistTime = 0;
    
    /** 检查间隔。**/
    private Integer checkInterval = 0;
    
    /** 是否启用电源智能管理 0:不启用电源智能管理 1:启用电源智能管理。**/
    private Integer enableIPM = 0;
    
    /** 持续时间。**/
    private Integer persistTimeIPM = 0;
    
    /** 主机回收监控策略ID。**/
    private Long ipmLowerMonitorId;
    /** 主机回收监控策略名称。**/
    private String ipmLowerMonitorName;
    
    /** 主机扩展监控策略ID。**/
    private Long ipmUpperMonitorId;
    /** 主机扩展监控策略名称。**/
    private String ipmUpperMonitorName;
    
    /** 检查间隔。**/
    private Integer checkIntervalIPM = 0;
    
    /** 操作员分组ID。*/
	private Long operatorGroupId = 0l;
	
	/** 操作员分组编码。 */
    private String operatorGroupCode = "";
    
    /** 组播地址。*/
	private String broadcastAddr = null;
	
	/** 组播端口。 */
    private Integer broadcastPort = null;
	
	/** 动态资源调度引用的监控策略ID。*/
	private Long lbMonitorId;
	
	/** 动态资源调度引用的监控策略名称。*/
	private String lbMonitorName;
	
	/** 子结点个数。*/
	private Integer childNum = null;
	
	/** 最少提供ha服务主机数。 */
    private Integer HaMinHost = null;
    

    /** 是否启用SLB 0:不启用存储负载均衡 1:启用存储负载均衡。**/
    private Integer enableSLB = 0;
    
    /** 存储负载均衡持续时间。**/
    private Integer slbPersistTime = 0;
    
    /** 存储负载检查间隔。**/
    private Integer slbCheckInterval = 0;
    
    /** 存储动态资源调度引用的监控策略ID。*/
    private Long slbMonitorId;
    
    /** 存储动态资源调度引用的监控策略名称。*/
    private String slbMonitorName;
    
    /** 是否启用本地存储高可靠性  0:不启用HA 1:启用HA。**/
    private Integer enableStorageHA = 0;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHostPoolId() {
		return hostPoolId;
	}

	public void setHostPoolId(Long hostPoolId) {
		this.hostPoolId = hostPoolId;
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

	public Integer getEnableHA() {
		return enableHA;
	}

	public void setEnableHA(Integer enableHA) {
		this.enableHA = enableHA;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getEnableLB() {
		return enableLB;
	}

	public void setEnableLB(Integer enableLB) {
		this.enableLB = enableLB;
	}

	public Integer getPersistTime() {
		return persistTime;
	}

	public void setPersistTime(Integer persistTime) {
		this.persistTime = persistTime;
	}

	public Integer getCheckInterval() {
		return checkInterval;
	}

	public void setCheckInterval(Integer checkInterval) {
		this.checkInterval = checkInterval;
	}

	public Integer getEnableIPM() {
		return enableIPM;
	}

	public void setEnableIPM(Integer enableIPM) {
		this.enableIPM = enableIPM;
	}

	public Integer getPersistTimeIPM() {
		return persistTimeIPM;
	}

	public void setPersistTimeIPM(Integer persistTimeIPM) {
		this.persistTimeIPM = persistTimeIPM;
	}

	public Long getIpmLowerMonitorId() {
		return ipmLowerMonitorId;
	}

	public void setIpmLowerMonitorId(Long ipmLowerMonitorId) {
		this.ipmLowerMonitorId = ipmLowerMonitorId;
	}

	public Long getIpmUpperMonitorId() {
		return ipmUpperMonitorId;
	}

	public void setIpmUpperMonitorId(Long ipmUpperMonitorId) {
		this.ipmUpperMonitorId = ipmUpperMonitorId;
	}

	public Integer getCheckIntervalIPM() {
		return checkIntervalIPM;
	}

	public void setCheckIntervalIPM(Integer checkIntervalIPM) {
		this.checkIntervalIPM = checkIntervalIPM;
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

	public String getBroadcastAddr() {
		return broadcastAddr;
	}

	public void setBroadcastAddr(String broadcastAddr) {
		this.broadcastAddr = broadcastAddr;
	}

	public Integer getBroadcastPort() {
		return broadcastPort;
	}

	public void setBroadcastPort(Integer broadcastPort) {
		this.broadcastPort = broadcastPort;
	}

	public Long getLbMonitorId() {
		return lbMonitorId;
	}

	public void setLbMonitorId(Long lbMonitorId) {
		this.lbMonitorId = lbMonitorId;
	}

	public Integer getChildNum() {
		return childNum;
	}

	public void setChildNum(Integer childNum) {
		this.childNum = childNum;
	}

	public String getLbMonitorName() {
		return lbMonitorName;
	}

	public void setLbMonitorName(String lbMonitorName) {
		this.lbMonitorName = lbMonitorName;
	}

	public String getIpmLowerMonitorName() {
		return ipmLowerMonitorName;
	}

	public void setIpmLowerMonitorName(String ipmLowerMonitorName) {
		this.ipmLowerMonitorName = ipmLowerMonitorName;
	}

	public String getIpmUpperMonitorName() {
		return ipmUpperMonitorName;
	}

	public void setIpmUpperMonitorName(String ipmUpperMonitorName) {
		this.ipmUpperMonitorName = ipmUpperMonitorName;
	}

	public Integer getHaMinHost() {
		return HaMinHost;
	}

	public void setHaMinHost(Integer haMinHost) {
		HaMinHost = haMinHost;
	}

    public Integer getEnableSLB() {
        return enableSLB;
    }

    public void setEnableSLB(Integer enableSLB) {
        this.enableSLB = enableSLB;
    }

    public Integer getSlbPersistTime() {
        return slbPersistTime;
    }

    public void setSlbPersistTime(Integer slbPersistTime) {
        this.slbPersistTime = slbPersistTime;
    }

    public Integer getSlbCheckInterval() {
        return slbCheckInterval;
    }

    public void setSlbCheckInterval(Integer slbCheckInterval) {
        this.slbCheckInterval = slbCheckInterval;
    }

    public Long getSlbMonitorId() {
        return slbMonitorId;
    }

    public void setSlbMonitorId(Long slbMonitorId) {
        this.slbMonitorId = slbMonitorId;
    }

    public String getSlbMonitorName() {
        return slbMonitorName;
    }

    public void setSlbMonitorName(String slbMonitorName) {
        this.slbMonitorName = slbMonitorName;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	public Integer getEnableStorageHA() {
		return enableStorageHA;
	}

	public void setEnableStorageHA(Integer enableStorageHA) {
		this.enableStorageHA = enableStorageHA;
	}
    
	/** 集群是否启用HA */
	public boolean isEnableHA() {
		return enableHA != null && enableHA == 1;
	}
}
