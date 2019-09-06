package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * 用于REST接口增加XML转换
 * @author l10191
 */
@XmlRootElement(name = "migrateVolume")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsMigrateVolume implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7125943669692590733L;

	@XmlElements(
			@XmlElement(name = "vol", type = Vol.class)
	)
	private List<Vol> volumeList;
	
	private Long hostId;
	
	//源存储池路径
	private String srcStoragePoolPath = null;
	
	//源存储池名称
	private String srcPoolName = null;
	
	//目的存储池路径
	private String destPoolNamePath = null;
	
	//目的存储池名称
	private String destPoolName = null;

	public List<Vol> getVolumeList() {
		return volumeList;
	}

	public void setVolumeList(List<Vol> volumeList) {
		this.volumeList = volumeList;
	}

	public Long getHostId() {
		return hostId;
	}

	public void setHostId(Long hostId) {
		this.hostId = hostId;
	}

	public String getSrcStoragePoolPath() {
		return srcStoragePoolPath;
	}

	public void setSrcStoragePoolPath(String srcStoragePoolPath) {
		this.srcStoragePoolPath = srcStoragePoolPath;
	}

	public String getSrcPoolName() {
		return srcPoolName;
	}

	public void setSrcPoolName(String srcPoolName) {
		this.srcPoolName = srcPoolName;
	}

	public String getDestPoolNamePath() {
		return destPoolNamePath;
	}

	public void setDestPoolNamePath(String destPoolNamePath) {
		this.destPoolNamePath = destPoolNamePath;
	}

	public String getDestPoolName() {
		return destPoolName;
	}

	public void setDestPoolName(String destPoolName) {
		this.destPoolName = destPoolName;
	}
	/**存储卷 */
	@XmlRootElement(name = "vol")
	@XmlAccessorType(XmlAccessType.FIELD)
	public static class Vol implements Serializable {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private String name;
		private Long size;
		public Long getSize() {
			return size;
		}

		public void setSize(Long size) {
			this.size = size;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
		
	}
//	public static void main(String[] args) {
//		RsMigrateVolume a = new RsMigrateVolume();
//		a.setDestPoolName("destPoolName");
//		a.setHostId(1l);
//		List<Vol> list = new ArrayList<Vol>();
//		Vol b = new Vol();
//		b.setName("aa");
//		list.add(b);
//		list.add(b);
//		a.setVolumeList(list);
//		String xml = FuncUtil.convertObjectToXml(a, RsMigrateVolume.class);
//		System.out.println(xml);
//		
//		RsMigrateVolume aa= (RsMigrateVolume) FuncUtil.convertXmlToObject(xml, RsMigrateVolume.class);
//		System.out.println(aa.getVolumeList());
//		List<Vol> c= aa.getVolumeList();
//		for (Vol cc : c) {
//			System.out.println(cc.getName());
//		}
//	}
}
