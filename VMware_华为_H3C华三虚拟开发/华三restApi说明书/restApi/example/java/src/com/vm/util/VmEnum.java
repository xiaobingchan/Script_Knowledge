package com.vm.util;

public class VmEnum {
	//关闭虚拟机
	public static final String CAS_VM_OPERRATE_CLOSE = "close";
	//启动虚拟机
	public static final String CAS_VM_OPERRATE_START = "start";
	//强制关闭电源
	public static final String CAS_VM_OPERRATE_POWEROFF = "powerOff";
	//暂停虚拟机
	public static final String CAS_VM_OPERRATE_PAUSE = "pause";
	//恢复虚拟机
	public static final String CAS_VM_OPERRATE_RESTORE = "restore";
	//休眠虚拟机
	public static final String CAS_VM_OPERRATE_SLEEP = "sleep";
	//重启虚拟机
	public static final String CAS_VM_OPERRATE_RESTART = "restart";
	
	//克隆方式：0表示普通克隆，1表示快速克隆。 
	public static final String CAS_VM_CLONEMODE_NORMAL = "0";
	public static final String CAS_VM_CLONEMODE_FAST = "1";
	
	//克隆目的位置：枚举:0表示本地克隆，1表示主机间克隆。
	public static final String CAS_VM_CLONETYPE_LOCAL = "0";
	public static final String CAS_VM_CLONETYPE_HOST = "1";
	
	
	//磁盘类型，支持raw和qcow2格式，如不填写，则不修改磁盘类型。
	public static final String CAS_VM_DISKFORMAT_RAW = "raw";
	public static final String CAS_VM_DISKFORMAT_QCOW2 = "qcow2";
	public static final String CAS_VM_DISKFORMAT_Null = "";
	
	//虚拟机快照操作类型
	public static final String CAS_VM_SNAPSHOT_CREATE = "create";
	public static final String CAS_VM_SNAPSHOT_LOAD = "load";
	public static final String CAS_VM_SNAPSHOT_DELETE = "delete";
	public static final String CAS_VM_SNAPSHOT_RESUME = "resume";
	
}
