package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services 接口返回的告警详细信息。
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
    private long eventTime;
    
    /** 告警详细描述 */
    private String eventDesc;
    
    /** 告警原因。 **/
    private String reason;
    
    /** 修复建议。 **/
    private String suggest;
    
    /** 维护经验。 **/
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
