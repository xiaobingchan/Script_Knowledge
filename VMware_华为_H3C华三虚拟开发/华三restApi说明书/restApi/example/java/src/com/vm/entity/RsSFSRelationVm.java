package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="rsSFSRelationVm")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsSFSRelationVm implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 4519772829804094069L;

	private Long fsId;
    
    private String fsName;
    
    private Long hpId;
    
    private String path;

    @XmlElement(name = "rsVm")
	private List<RsVm> rsVms = null;

    public Long getFsId() {
		return fsId;
	}

	public void setFsId(Long fsId) {
		this.fsId = fsId;
	}

	public String getFsName() {
		return fsName;
	}

	public void setFsName(String fsName) {
		this.fsName = fsName;
	}

	public Long getHpId() {
		return hpId;
	}
	
	public void setHpId(Long hpId) {
		this.hpId = hpId;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List<RsVm> getRsVms() {
		return rsVms;
	}

	public void setRsVms(List<RsVm> rsVms) {
		this.rsVms = rsVms;
	}

	@XmlRootElement(name="rsVm")
    @XmlAccessorType(XmlAccessType.FIELD)
    public static class RsVm implements Serializable {
    	/**
		 * 
		 */
		private static final long serialVersionUID = -376045828923522635L;

		/** �����ID�� */
    	private Long vmId = null;

    	/** �����ID�� **/
    	private Long hostId;
    	
    	/** ��������ơ� **/
    	private String domainName;
    	
    	/** �������ʾ���ơ� **/
        private String domainTitle;
        
    	/** �������ơ� **/
        private String hostName;
        
        /** �����״̬��ȡֵ�� 0:ģ�� 1:δ֪ 2:���� 3:�ر� 4 ��ͣ�� **/
    	private Integer status;
    	
    	private String fileName;

		public Long getVmId() {
			return vmId;
		}

		public void setVmId(Long vmId) {
			this.vmId = vmId;
		}

		public Long getHostId() {
			return hostId;
		}

		public void setHostId(Long hostId) {
			this.hostId = hostId;
		}

		public String getDomainName() {
			return domainName;
		}

		public void setDomainName(String domainName) {
			this.domainName = domainName;
		}

		public String getDomainTitle() {
			return domainTitle;
		}

		public void setDomainTitle(String domainTitle) {
			this.domainTitle = domainTitle;
		}

		public String getHostName() {
			return hostName;
		}

		public void setHostName(String hostName) {
			this.hostName = hostName;
		}

		public Integer getStatus() {
			return status;
		}

		public void setStatus(Integer status) {
			this.status = status;
		}

		public String getFileName() {
			return fileName;
		}

		public void setFileName(String fileName) {
			this.fileName = fileName;
		}

    }
}
