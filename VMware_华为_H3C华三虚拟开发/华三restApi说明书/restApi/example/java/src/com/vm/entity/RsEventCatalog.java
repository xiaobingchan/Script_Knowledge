package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Restful Web Services 接口返回的告警管理信息实体类。
 * @author z10350
 *
 */
@XmlRootElement(name="eventCatalog")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsEventCatalog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/** ID。 */
    private Long id = null;

    /** 告警类型。 **/
    private Integer eventType;
    
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

	public Integer getEventType() {
		return eventType;
	}

	public void setEventType(Integer eventType) {
		this.eventType = eventType;
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
}
