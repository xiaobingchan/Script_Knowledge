package com.vm.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 用于REST接口迁移虚拟机XML转换
 * @author mkf3563
 */
@XmlRootElement(name = "vmParamter")
@XmlAccessorType(XmlAccessType.FIELD)
public class DomainForMigrate {

    /** 离线 迁移 */
    public static final int OFFLINE = 0;

    /** 在线迁移 */
    public static final int ONLINE = 1;

    /** 只迁移主机 */
    public static final int ONLY_HOST = 0;

    /** 只迁移存储 */
    public static final int ONLY_STORE = 1;

    /** 主机和存储 */
    public static final int HOST_STORE = 2;

    /** 虚拟机id */
    private Long id;

    /** 虚拟机名称 */
    private String domainName;

    /** 目的主机id */
    private Long targetHostId;

    /** 在线、离线 系统判断 */
    private Integer onlineMigrate;

    /** 类型：0:迁移主机、1:存储、2:迁移主机和存储 */
    private Integer migrateType;

    /** 目标主机池路径 */
    private String targetPath;

    /** 目标主机池名称 */
    private String targetPool;

    /** 虚拟磁盘类型 */
    private String diskFormat;

    /** 是否在目的主机上创建配置文件 */
    private Boolean dstPersist;

    /** 目的主机暂停 */
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
