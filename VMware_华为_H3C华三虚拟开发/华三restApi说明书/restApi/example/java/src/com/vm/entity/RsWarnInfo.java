package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services �ӿڷ��صĸ澯��ϸ��Ϣ��
 * @author ckf3801
 *
 */
@XmlRootElement(name="warnInfo")   
@XmlAccessorType(XmlAccessType.FIELD)
public class RsWarnInfo implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8435639125705813174L;
    
    /** the ID */
    private Long id;
    
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
    private long eventTime;
    
    /** �澯��ϸ���� */
    private String eventDesc;
    
    /** �澯ԭ�� **/
    private String reason;
    
    /** �޸����顣 **/
    private String suggest;
    
    /** ά�����顣 **/
    private String experience;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getEventTime() {
        return eventTime;
    }

    public void setEventTime(long eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventDesc() {
        return eventDesc;
    }

    public void setEventDesc(String eventDesc) {
        this.eventDesc = eventDesc;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getSuggest() {
        return suggest;
    }

    public void setSuggest(String suggest) {
        this.suggest = suggest;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

	@Override
	public String toString() {
		return "RsWarnInfo [id=" + id + ", state=" + state + ", eventLevel="
				+ eventLevel + ", eventType=" + eventType + ", eventName="
				+ eventName + ", eventSrc=" + eventSrc + ", eventTime="
				+ eventTime + ", eventDesc=" + eventDesc + ", reason=" + reason
				+ ", suggest=" + suggest + ", experience=" + experience + "]";
	}
    
}
