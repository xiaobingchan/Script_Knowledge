package com.vm.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="taskmsg")
public class TaskMsg implements Serializable{
	private static final long serialVersionUID = 5913513828618724269L;
	private long msdId;
	private long targetId;
	private String targetName;
	private String completed;
	private String progress;
	public long getMsdId() {
		return msdId;
	}
	public void setMsdId(long msdId) {
		this.msdId = msdId;
	}
	public long getTargetId() {
		return targetId;
	}
	public void setTargetId(long targetId) {
		this.targetId = targetId;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getCompleted() {
		return completed;
	}
	public void setCompleted(String completed) {
		this.completed = completed;
	}
	public String getProgress() {
		return progress;
	}
	public void setProgress(String progress) {
		this.progress = progress;
	}
	
	
}
