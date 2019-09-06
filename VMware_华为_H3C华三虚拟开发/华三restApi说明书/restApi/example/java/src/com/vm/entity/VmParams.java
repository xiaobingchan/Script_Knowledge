package com.vm.entity;

public class VmParams {
	private Long vmId; //虚拟机ID
	private String vmName;//虚拟机名称
	private String operateType;//操作类型
	public Long getVmId() {
		return vmId;
	}
	public void setVmId(Long vmId) {
		this.vmId = vmId;
	}
	public String getVmName() {
		return vmName;
	}
	public void setVmName(String vmName) {
		this.vmName = vmName;
	}
	public String getOperateType() {
		return operateType;
	}
	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}
	

	
	
}
