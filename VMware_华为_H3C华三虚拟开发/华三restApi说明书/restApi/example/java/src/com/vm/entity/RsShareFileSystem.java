package com.vm.entity;

import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Restful Web Services 接口返回的ISCSI存储实体类。
 *
 * @author l10191
 */
@XmlRootElement(name="rsShareFileSystem")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsShareFileSystem implements Serializable{

    /**
     * add description of field here
     */
    private static final long serialVersionUID = 1L;
    
	public static final int FS_TYPE_ISCSI = 1;
	public static final int FS_TYPE_FC = 2;
    
    private Long id;
    
    private String name;
    
    private String desc;
    
    private Integer type;
    
    private Long hpId ;
    
    private String typeStr;
    
    private String path;

    private String ip;
    
    private String target;
    
    private String lun;
    
    private String naa;

    private String naaName;

    private boolean readOnly;
    
    private Long maxSize;
    
    private Long remainSize;
    
    private double usAge;
    
    @XmlElement(name = "rsFsLunInfo")
    private List<RsFsLunInfo> rsFsLunInfoList;
    
    private Integer isMerge;
    
    private Integer isSpeed;
    
    /** 存储I/O控制，0：未启用，1：启用*/
    private Integer ioControl;
    /** I/O时延阈值*/
    private Integer delayThreshold;
    
    @XmlElement(name = "rsExpandFsLunInfo")
	private List<RsFsLunInfo> expandLunInfoList;
    
	/** 存储容量负载控制，0：未启用，1：启用 **/
    private Integer capacityControl;
       
    public Long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(Long maxSize) {
        this.maxSize = maxSize;
    }

    public Long getRemainSize() {
        return remainSize;
    }

    public void setRemainSize(Long remainSize) {
        this.remainSize = remainSize;
    }
    
    public boolean isReadOnly() {
        return readOnly;
    }

    public void setReadOnly(boolean readOnly) {
        this.readOnly = readOnly;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getLun() {
        return lun;
    }

    public void setLun(String lun) {
        this.lun = lun;
    }

    public String getNaa() {
        return naa;
    }

    public void setNaa(String naa) {
        this.naa = naa;
    }

    public String getNaaName() {
        return naaName;
    }

    public void setNaaName(String naaName) {
        this.naaName = naaName;
    }

    public String getTypeStr() {
        return typeStr;
    }

    public void setTypeStr(String typeStr) {
        this.typeStr = typeStr;
    }

	public List<RsFsLunInfo> getRsFsLunInfoList() {
		return rsFsLunInfoList;
	}

	public void setRsFsLunInfoList(List<RsFsLunInfo> rsFsLunInfoList) {
		this.rsFsLunInfoList = rsFsLunInfoList;
	}

	public Long getHpId() {
		return hpId;
	}

	public void setHpId(Long hpId) {
		this.hpId = hpId;
	}

	public double getUsAge() {
		return usAge;
	}

	public void setUsAge(double usAge) {
		this.usAge = usAge;
	}

	public List<RsFsLunInfo> getExpandLunInfoList() {
		return expandLunInfoList;
	}

	public void setExpandLunInfoList(List<RsFsLunInfo> expandLunInfoList) {
		this.expandLunInfoList = expandLunInfoList;
	}

	public Integer getIsMerge() {
		return isMerge;
	}

	public void setIsMerge(Integer isMerge) {
		this.isMerge = isMerge;
	}

	public Integer getIsSpeed() {
		return isSpeed;
	}

	public void setIsSpeed(Integer isSpeed) {
		this.isSpeed = isSpeed;
	}

	public Integer getIoControl() {
		return ioControl;
	}

	public void setIoControl(Integer ioControl) {
		this.ioControl = ioControl;
	}

	public Integer getDelayThreshold() {
		return delayThreshold;
	}

	public void setDelayThreshold(Integer delayThreshold) {
		this.delayThreshold = delayThreshold;
	}
	
	public void setCapacityControl(Integer capacityControl) {
		this.capacityControl = capacityControl;
	}
	
	public Integer getCapacityControl() {
		return capacityControl;
	}
}