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

	/** ��ȺID�� */
    private Long id = 0l;
    
    /** ������ID��*/
	private Long hostPoolId = 0l;
    
    /** ��Ⱥ���ơ� **/
    private String name = "";
    
    /** ��Ⱥ������ **/
    private String description = "";
    
    /** �Ƿ�����HA  0:������HA 1:����HA��**/
    private Integer enableHA = 0;
    
    /** �������ȼ�  0:�ͼ�  1:�м� 2:�߼�**/
    private Integer priority = 1;
    
    /** �Ƿ�����LB 0:�����ø��ؾ��� 1:���ø��ؾ��⡣**/
    private Integer enableLB = 0;
    
    /** ����ʱ�䡣**/
    private Integer persistTime = 0;
    
    /** �������**/
    private Integer checkInterval = 0;
    
    /** �Ƿ����õ�Դ���ܹ��� 0:�����õ�Դ���ܹ��� 1:���õ�Դ���ܹ���**/
    private Integer enableIPM = 0;
    
    /** ����ʱ�䡣**/
    private Integer persistTimeIPM = 0;
    
    /** �������ռ�ز���ID��**/
    private Long ipmLowerMonitorId;
    /** �������ռ�ز������ơ�**/
    private String ipmLowerMonitorName;
    
    /** ������չ��ز���ID��**/
    private Long ipmUpperMonitorId;
    /** ������չ��ز������ơ�**/
    private String ipmUpperMonitorName;
    
    /** �������**/
    private Integer checkIntervalIPM = 0;
    
    /** ����Ա����ID��*/
	private Long operatorGroupId = 0l;
	
	/** ����Ա������롣 */
    private String operatorGroupCode = "";
    
    /** �鲥��ַ��*/
	private String broadcastAddr = null;
	
	/** �鲥�˿ڡ� */
    private Integer broadcastPort = null;
	
	/** ��̬��Դ�������õļ�ز���ID��*/
	private Long lbMonitorId;
	
	/** ��̬��Դ�������õļ�ز������ơ�*/
	private String lbMonitorName;
	
	/** �ӽ�������*/
	private Integer childNum = null;
	
	/** �����ṩha������������ */
    private Integer HaMinHost = null;
    

    /** �Ƿ�����SLB 0:�����ô洢���ؾ��� 1:���ô洢���ؾ��⡣**/
    private Integer enableSLB = 0;
    
    /** �洢���ؾ������ʱ�䡣**/
    private Integer slbPersistTime = 0;
    
    /** �洢���ؼ������**/
    private Integer slbCheckInterval = 0;
    
    /** �洢��̬��Դ�������õļ�ز���ID��*/
    private Long slbMonitorId;
    
    /** �洢��̬��Դ�������õļ�ز������ơ�*/
    private String slbMonitorName;
    
    /** �Ƿ����ñ��ش洢�߿ɿ���  0:������HA 1:����HA��**/
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
    
	/** ��Ⱥ�Ƿ�����HA */
	public boolean isEnableHA() {
		return enableHA != null && enableHA == 1;
	}
}
