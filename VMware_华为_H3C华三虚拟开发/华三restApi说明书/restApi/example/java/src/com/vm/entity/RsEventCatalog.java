package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/**
 * Restful Web Services �ӿڷ��صĸ澯������Ϣʵ���ࡣ
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
	
	/** ID�� */
    private Long id = null;

    /** �澯���͡� **/
    private Integer eventType;
    
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
