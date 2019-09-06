package com.vm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ����ִ����Ϣ
 * 
 * @author mkf3563
 * 
 */
@XmlRootElement(name = "taskMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsTaskMsg implements Serializable {

	private static final long serialVersionUID = 3540764119043348688L;
	/** �ɹ� */
	public static final int SUCCESS = 0;
	/** ʧ�� */
	public static final int FAIL = 1;
	/** ���ֳɹ� */
	public static final int PART_SUCCESS = 2;

	/** ��ϢID */
	private Long msgId;
	/** ��Ϣ���� */
	private String name;

	/** ����ִ�ж���ID */
	private Long targetId;

	/** ����ִ�ж������� */
	private String targetName;
	/** ����������*/
	private String detail;

	/** �����Ƿ���� */
	private boolean completed;

    /** ִ�н����0-�ɹ���1-ʧ�ܣ�2-���ֳɹ����� */
	private Integer result;

	/** ���� */
    private int progress;

	/** ʧ����Ϣ */
	private String failMsg;
	
	/** �¼����͡�*/
    private int eventType;
    
    /**��ʼʱ��**/
    private Date start;
    
    /**����ʱ��**/
    private Date complete;
    
    /** ��Ҫˢ�µ����ݡ�*/
    private List<RsKeyValue> refreshData;
   
	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	public Long getTargetId() {
		return targetId;
	}

	public void setTargetId(Long targetId) {
		this.targetId = targetId;
	}

	public String getTargetName() {
		return targetName;
	}

	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean isCompleted) {
		this.completed = isCompleted;
	}

	public String getFailMsg() {
		return failMsg;
	}

	public void setFailMsg(String failMsg) {
		this.failMsg = failMsg;
	}

	public void setProgress(int progress) {
		this.progress = progress;
	}
	
	public int getProgress() {
		return progress;
	}

	public void setResult(Integer result) {
		this.result = result;
	}

	public Integer getResult() {
		if (result == null) {
			result = 1;
		}
		return result;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<RsKeyValue> getRefreshData() {
		return refreshData;
	}

	public void setRefreshData(List<RsKeyValue> refreshData) {
		this.refreshData = refreshData;
	}

	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	public Date getComplete() {
		return complete;
	}

	public void setComplete(Date complete) {
		this.complete = complete;
	}
	
}