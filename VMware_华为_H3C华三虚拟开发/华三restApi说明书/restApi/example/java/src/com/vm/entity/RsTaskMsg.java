package com.vm.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 任务执行信息
 * 
 * @author mkf3563
 * 
 */
@XmlRootElement(name = "taskMsg")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsTaskMsg implements Serializable {

	private static final long serialVersionUID = 3540764119043348688L;
	/** 成功 */
	public static final int SUCCESS = 0;
	/** 失败 */
	public static final int FAIL = 1;
	/** 部分成功 */
	public static final int PART_SUCCESS = 2;

	/** 消息ID */
	private Long msgId;
	/** 消息标题 */
	private String name;

	/** 任务执行对象ID */
	private Long targetId;

	/** 任务执行对象名称 */
	private String targetName;
	/** 任务描述。*/
	private String detail;

	/** 任务是否完成 */
	private boolean completed;

    /** 执行结果（0-成功；1-失败；2-部分成功）。 */
	private Integer result;

	/** 进度 */
    private int progress;

	/** 失败信息 */
	private String failMsg;
	
	/** 事件类型。*/
    private int eventType;
    
    /**开始时间**/
    private Date start;
    
    /**结束时间**/
    private Date complete;
    
    /** 需要刷新的数据。*/
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