package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="list")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsDomainSnapshot implements Serializable{
	private static final long serialVersionUID = -204526734005547690L;
	@XmlElement(name = "snapshot")
	private List<Snapshot> list;
	
	public List<Snapshot> getList() {
		return list;
	}

	public void setList(List<Snapshot> list) {
		this.list = list;
	}
	
	@XmlRootElement(name="snapshot")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Snapshot implements Serializable{
		private static final long serialVersionUID = 7715958110452802176L;
		private String name;
		private String desc;
		private Boolean isCurr;
		@XmlElement(name = "snapshots")
		private List<Snapshot> snapshots;
		
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDesc() {
			return desc;
		}
		public void setDesc(String desc) {
			this.desc = desc;
		}
		public Boolean getIsCurr() {
			return isCurr;
		}
		public void setIsCurr(Boolean isCurr) {
			this.isCurr = isCurr;
		}
		public List<Snapshot> getSnapshots() {
			return snapshots;
		}
		public void setSnapshots(List<Snapshot> snapshots) {
			this.snapshots = snapshots;
		}
	}
}
