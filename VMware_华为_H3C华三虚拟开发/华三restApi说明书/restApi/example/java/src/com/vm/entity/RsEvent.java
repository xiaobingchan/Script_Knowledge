package com.vm.entity;

import java.io.Serializable;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Restful Web Services �ӿڷ��صĸ澯����ʵ���ࡣ
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
	
	/** �澯��ϢID�� */
    private RsEventCatalog eventCatalog = null;
	
	/** �澯״̬   1 ȷ�� 2 δȷ��*/
	private Integer state;
	
	/** �澯����    1 ���� 2��Ҫ 3 ��Ҫ 4 ����*/
	private Integer eventLevel;
	
	/** �澯����  1 ���� 2�洢 3 ���� 4 CPU 5 �ڴ�*/
	private Integer eventType;
	
	/** �澯���� */
	private String eventName;
	
	/** �澯��Դ */
	private String eventSrc;
	
	/** �澯ʱ�� */
	private Date eventTime;
	
	/** �澯��ϸ���� */
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
