package com.vm.util;

public class VmEnum {
	//�ر������
	public static final String CAS_VM_OPERRATE_CLOSE = "close";
	//���������
	public static final String CAS_VM_OPERRATE_START = "start";
	//ǿ�ƹرյ�Դ
	public static final String CAS_VM_OPERRATE_POWEROFF = "powerOff";
	//��ͣ�����
	public static final String CAS_VM_OPERRATE_PAUSE = "pause";
	//�ָ������
	public static final String CAS_VM_OPERRATE_RESTORE = "restore";
	//���������
	public static final String CAS_VM_OPERRATE_SLEEP = "sleep";
	//���������
	public static final String CAS_VM_OPERRATE_RESTART = "restart";
	
	//��¡��ʽ��0��ʾ��ͨ��¡��1��ʾ���ٿ�¡�� 
	public static final String CAS_VM_CLONEMODE_NORMAL = "0";
	public static final String CAS_VM_CLONEMODE_FAST = "1";
	
	//��¡Ŀ��λ�ã�ö��:0��ʾ���ؿ�¡��1��ʾ�������¡��
	public static final String CAS_VM_CLONETYPE_LOCAL = "0";
	public static final String CAS_VM_CLONETYPE_HOST = "1";
	
	
	//�������ͣ�֧��raw��qcow2��ʽ���粻��д�����޸Ĵ������͡�
	public static final String CAS_VM_DISKFORMAT_RAW = "raw";
	public static final String CAS_VM_DISKFORMAT_QCOW2 = "qcow2";
	public static final String CAS_VM_DISKFORMAT_Null = "";
	
	//��������ղ�������
	public static final String CAS_VM_SNAPSHOT_CREATE = "create";
	public static final String CAS_VM_SNAPSHOT_LOAD = "load";
	public static final String CAS_VM_SNAPSHOT_DELETE = "delete";
	public static final String CAS_VM_SNAPSHOT_RESUME = "resume";
	
}
