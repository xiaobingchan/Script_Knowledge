package com.vm.entity;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="list")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsCommList<Data> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5628149641647814125L;

	/** 数据集。 */
	@XmlAnyElement(lax = true)
    @XmlElementRefs({@XmlElementRef(type = RsDomain.class), 
    				 @XmlElementRef(type = RsHost.class),
                     @XmlElementRef(type = RsDomainNetwork.class),
                     @XmlElementRef(type = RsDomainDeploy.class),
                     @XmlElementRef(type = RsStore.class),
                     @XmlElementRef(type = RsKeyValue.class),
    				 @XmlElementRef(type = RsEvent.class),
    				 @XmlElementRef(type = VmCpuRate.class),
    				 @XmlElementRef(type = VmIoStat.class),
    				 @XmlElementRef(type = VmMemoryRate.class),
    				 @XmlElementRef(type = VmNetIoStat.class),
    				 @XmlElementRef(type = RsStoragePool.class),
                     @XmlElementRef(type = RsStorageVolume.class),
                     @XmlElementRef(type = RsSnapshot.class),
                     @XmlElementRef(type = PortProfile.class),
                     @XmlElementRef(type = RsTemplateStorage.class),
                     @XmlElementRef(type = RsTemplateNetwork.class),
                     @XmlElementRef(type = RsTaskMsg.class),
                     @XmlElementRef(type = RsFsLunInfo.class),
                     @XmlElementRef(type = RsDomainStorage.class),
                     @XmlElementRef(type = OsNetwork.class)
    	             })
    protected List<Data> data = null;

    /** 构造封装特定数据类型的结果集。 */
    public RsCommList() {
    }

    /**
     * 构造封装特定数据类型的结果集。
     *
     * @param data 数据集。
     */
    public RsCommList(List<Data> data) {
        this.data = data;        
    }
    
	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}       
}
