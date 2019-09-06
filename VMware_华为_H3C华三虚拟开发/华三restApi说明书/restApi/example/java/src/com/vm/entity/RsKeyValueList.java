package com.vm.entity;
import java.io.Serializable;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name="rsKeyValues")
@XmlAccessorType(XmlAccessType.FIELD)
public class RsKeyValueList<Data> implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5628149641647814125L;

	/** 数据集。 */
	@XmlAnyElement(lax = true)
    @XmlElementRefs({@XmlElementRef(type = RsKeyValue.class)})
    protected List<Data> data = null;

    /** 构造封装特定数据类型的结果集。 */
    public RsKeyValueList() {
    }

    /**
     * 构造封装特定数据类型的结果集。
     *
     * @param data 数据集。
     */
    public RsKeyValueList(List<Data> data) {
        this.data = data;        
    }
    
	public List<Data> getData() {
		return data;
	}

	public void setData(List<Data> data) {
		this.data = data;
	}       
}
