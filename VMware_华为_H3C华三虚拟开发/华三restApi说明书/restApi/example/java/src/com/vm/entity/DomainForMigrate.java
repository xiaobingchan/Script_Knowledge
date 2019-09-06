package com.vm.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * ����REST�ӿ�Ǩ�������XMLת��
 * @author mkf3563
 */
@XmlRootElement(name = "vmParamter")
@XmlAccessorType(XmlAccessType.FIELD)
public class DomainForMigrate {

    /** ���� Ǩ�� */
    public static final int OFFLINE = 0;

    /** ����Ǩ�� */
    public static final int ONLINE = 1;

    /** ֻǨ������ */
    public static final int ONLY_HOST = 0;

    /** ֻǨ�ƴ洢 */
    public static final int ONLY_STORE = 1;

    /** �����ʹ洢 */
    public static final int HOST_STORE = 2;

    /** �����id */
    private Long id;

    /** ��������� */
    private String domainName;

    /** Ŀ������id */
    private Long targetHostId;

    /** ���ߡ����� ϵͳ�ж� */
    private Integer onlineMigrate;

    /** ���ͣ�0:Ǩ��������1:�洢��2:Ǩ�������ʹ洢 */
    private Integer migrateType;

    /** Ŀ��������·�� */
    private String targetPath;

    /** Ŀ������������ */
    private String targetPool;

    /** ����������� */
    private String diskFormat;

    /** �Ƿ���Ŀ�������ϴ��������ļ� */
    private Boolean dstPersist;

    /** Ŀ��������ͣ */
    private Boolean dstSuspend;

    // unknown
    private Boolean srcUndefine;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public Long getTargetHostId() {
        return targetHostId;
    }

    public void setTargetHostId(Long targetHostId) {
        this.targetHostId = targetHostId;
    }

    public Integer getOnlineMigrate() {
        return onlineMigrate;
    }

    public void setOnlineMigrate(Integer onlineMigrate) {
        this.onlineMigrate = onlineMigrate;
    }

    public Integer getMigrateType() {
        return migrateType;
    }

    public void setMigrateType(Integer migrateType) {
        this.migrateType = migrateType;
    }

    public String getTargetPath() {
        return targetPath;
    }

    public void setTargetPath(String targetPath) {
        this.targetPath = targetPath;
    }

    public String getTargetPool() {
        return targetPool;
    }

    public void setTargetPool(String targetPool) {
        this.targetPool = targetPool;
    }

    public String getDiskFormat() {
        return diskFormat;
    }

    public void setDiskFormat(String diskFormat) {
        this.diskFormat = diskFormat;
    }

    public Boolean getDstPersist() {
        return dstPersist;
    }

    public void setDstPersist(Boolean dstPersist) {
        this.dstPersist = dstPersist;
    }

    public Boolean getDstSuspend() {
        return dstSuspend;
    }

    public void setDstSuspend(Boolean dstSuspend) {
        this.dstSuspend = dstSuspend;
    }

    public Boolean getSrcUndefine() {
        return srcUndefine;
    }

    public void setSrcUndefine(Boolean srcUndefine) {
        this.srcUndefine = srcUndefine;
    }

}
