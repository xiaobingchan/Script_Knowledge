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

	/** ���ݼ��� */
	@XmlAnyElement(lax = true)
    @XmlElementRefs({@XmlElementRef(type = RsKeyValue.class)})
    protected List<Data> data = null;

    /** �����װ�ض��������͵Ľ������ */
    public RsKeyValueList() {
    }

    /**
     * �����װ�ض��������͵Ľ������
     *
     * @param data ���ݼ���
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
