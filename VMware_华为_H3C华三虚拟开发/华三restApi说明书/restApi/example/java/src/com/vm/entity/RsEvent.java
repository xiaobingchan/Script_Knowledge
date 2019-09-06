package com.vm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Restful Web Services 接口返回的告警管理实体类。
 * @author z10350
 *
 */
@XmlRootElement(name="event")	
@XmlAccessorType(XmlAccessType.FIELD)
public class RsEvent implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** the ID */
	private Long id;
	
	/** 告警信息ID。 */
    private RsEventCatalog eventCatalog = null;
	
	/** 告警状态   1 确认 2 未确认*/
	private Integer state;
	
	/** 告警级别    1 紧急 2重要 3 次要 4 警告*/
	private Integer eventLevel;
	
	/** 告警类型  1 主机 2存储 3 网络 4 CPU 5 内存*/
	private Integer eventType;
	
	/** 告警名称 */
	private String eventName;
	
	/** 告警来源 */
	private String eventSrc;
	
	/** 告警时间 */
	private Date eventTime;
	
	/** 告警详细描述 */
	private String eventDesc;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public RsEventCatalog getEventCatalog() {
		return eventCatalog;
	}

	public void setEventCatalog(RsEventCatalog eventCatalog) {
		this.eventCatalog = eventCatalog;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getEventLevel() {
		return eventLevel;
	}

	public void setEventLevel(Integer eventLevel) {
		this.eventLevel = eventLevel;
	}

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventSrc() {
		return eventSrc;
	}

	public void setEventSrc(String eventSrc) {
		this.eventSrc = eventSrc;
	}

	public Date getEventTime() {
		return eventTime;
	}

	public void setEventTime(Date eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventDesc() {
		return eventDesc;
	}

	public void setEventDesc(String eventDesc) {
		this.eventDesc = eventDesc;
	}

	@Override
	public String toString() {
		return "RsEvent [id=" + id + ", eventCatalog=" + eventCatalog
				+ ", state=" + state + ", eventLevel=" + eventLevel
				+ ", eventType=" + eventType + ", eventName=" + eventName
				+ ", eventSrc=" + eventSrc + ", eventTime=" + eventTime
				+ ", eventDesc=" + eventDesc + "]";
	}
}
