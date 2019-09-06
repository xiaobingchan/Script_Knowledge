import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vm.CasRestImpl;
import com.vm.entity.DomainForMigrate;
import com.vm.entity.Network;
import com.vm.entity.PortProfile;
import com.vm.entity.RsCluster;
import com.vm.entity.RsCommList;
import com.vm.entity.RsDomain;
import com.vm.entity.RsDomainDeploy;
import com.vm.entity.RsDomainForBackUp;
import com.vm.entity.RsDomainForModify;
import com.vm.entity.RsDomainForModify.BasicConfig;
import com.vm.entity.RsDomainForModify.CpuConfig;
import com.vm.entity.RsDomainForModify.CpuConfig.BindPhysicalCpu;
import com.vm.entity.RsDomainForModify.MemoryConfig;
import com.vm.entity.RsDomainForModify.NetworkConfig;
import com.vm.entity.RsDomainForModify.StorageConfig;
import com.vm.entity.RsDomainNetwork;
import com.vm.entity.RsDomainStorage;
import com.vm.entity.RsDomainSwitch;
import com.vm.entity.RsDomainVmClone;
import com.vm.entity.RsEvent;
import com.vm.entity.RsHost;
import com.vm.entity.RsKeyValue;
import com.vm.entity.RsMigrateVolume;
import com.vm.entity.RsMigrateVolume.Vol;
import com.vm.entity.RsPhysicalNIC;
import com.vm.entity.RsResult;
import com.vm.entity.RsSnapshot;
import com.vm.entity.RsStoragePool;
import com.vm.entity.RsStorageVolume;
import com.vm.entity.RsStore;
import com.vm.entity.RsTaskMsg;
import com.vm.entity.RsVPortTrafficInfo;
import com.vm.entity.RsVSwitch;
import com.vm.entity.RsVolume;
import com.vm.entity.Storage;
import com.vm.entity.VmCpuRate;
import com.vm.entity.VmMemoryRate;
import com.vm.util.RestUtil;
import com.vm.util.VmEnum;

public class CASRestClient {
    
	/**
	 * 测试查询主机和虚拟机
	 */
	public static void testQueryHostAndVm(){
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		
		if (hostList != null && hostList.size() > 0) {
			System.out.println("查询CAS中所有主机如下：");
			for (RsHost host : hostList) {
				System.out.println("主机ID：" + host.getId() + ", 主机名：" + host.getName() + ", 主机IP：" + host.getIp());				
			}
			for (RsHost host : hostList) {
				System.out.println("");
				// 查询CAS中所有虚拟机接口
				List<RsDomain>  vmList = CasRestImpl.queryAllVm(host.getId());
				System.out.println("主机 " + hostList.get(0).getName() + " 中所有虚拟机如下：");
				if (vmList != null && vmList.size() > 0) {
					for (RsDomain vm : vmList) {
						System.out.println("虚拟机:" + vm.getName() + "(" + vm.getId() + ")");				
					}
				}
			}
		}
	}
	
	/**
	 * IF_2
	 * 测试删除虚拟机
	 */
	public static void testDeleteVm(){
		System.out.println("**********测试删除虚拟机*********");
		RsTaskMsg task = CasRestImpl.deleteVm(5081l);
		System.out.println(task.getResult() == 0 ? "删除虚拟机成功。" : "删除虚拟机失败,失败原因：" + task.getFailMsg());
	}
	
	/**
	 * IF_3
	 * 测试配置虚拟机-基本信息修改
	 */
	public static void testModifyVmBasic(){
		System.out.println("**********测试修改虚拟机基本信息*********");
		BasicConfig basicConfig = new BasicConfig();
		//基本信息
		basicConfig.setPae(0);
		basicConfig.setAcpi(0);
		basicConfig.setApic(0);
		basicConfig.setClock("utc");
		basicConfig.setBlkiotune(300);
		basicConfig.setStartPriority(0);
		basicConfig.setAcpi(0);
		basicConfig.setAutoMigrate(0);
		basicConfig.setDesc("test rest modify domain");

		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(115L);
		rsDFM.setName("test-01");
		rsDFM.setBasicConfig(basicConfig);
		CasRestImpl.editVm(rsDFM);
	}

	/**
	 * IF_3
	 * 测试配置虚拟机-CPU修改
	 */
	public static void testModifyVmCpu() {
		System.out.println("**********测试修改CPU配置*********");
		//cpu
		CpuConfig cpu = new CpuConfig();
		cpu.setCpuCores(2);
		cpu.setCpuSockets(2);
		
		cpu.setCpuShares(256);
		BindPhysicalCpu bcpu = new BindPhysicalCpu();
		Integer[] i = {0,1};
		bcpu.setPcpu(i);
		bcpu.setVcpu(0);
		BindPhysicalCpu bcpu2 = new BindPhysicalCpu();
		Integer[] j = {0};
		bcpu2.setPcpu(j);
		bcpu2.setVcpu(1);
		List<BindPhysicalCpu> bindcpuList = new ArrayList<BindPhysicalCpu>();
		bindcpuList.add(bcpu);
		bindcpuList.add(bcpu2);
		cpu.setBindcpuList(bindcpuList);
		
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(5083L);
		rsDFM.setName("rest_add1441963418769");
		rsDFM.setCpuConfig(cpu);
		CasRestImpl.modifyCpuConfig(rsDFM);
	}

	/**
	 * IF_3
	 * 测试配置虚拟机-内存修改
	 */
	public static void testModifyVmMomory(){
		System.out.println("**********测试修改内存配置*********");
		MemoryConfig memory = new MemoryConfig();
		memory.setSize(2048L);
		memory.setMemoryBacking(10);
		
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(115L);
		rsDFM.setName("test-01");
		rsDFM.setMemory(memory);
		CasRestImpl.editVm(rsDFM);
	}

	/**
	 * IF_3
	 * 测试配置虚拟机-虚拟网卡修改
	 * @param rsclient 
	 */
	public static void testModifyVmNetwork() {
		System.out.println("**********测试修改网络配置*********");
		NetworkConfig network = new NetworkConfig();
		network.setMac("0c:da:41:1d:81:a1");
		network.setNewMac("0c:da:41:1d:81:a2");
		network.setIpAddr("192.168.200.15");
		network.setVsId(7L);
		network.setVsName("vswitch0");
		network.setDeviceModel("e1000");
		network.setMode("veb");
		network.setProfileId(1L);
		network.setLimitInBound(false);
		network.setLimitOutBound(false);
		network.setVirtualport(false);

		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(115L);
		rsDFM.setName("test-01");
		rsDFM.setNetwork(network);
		CasRestImpl.editVm(rsDFM);
	}

	/**
	 * IF_3
	 * 测试配置虚拟机-硬盘修改
	 */
	public static void testModifyVmDisk(){
		System.out.println("**********测试修改虚拟机硬盘信息*********");
		StorageConfig storage = new StorageConfig();
		storage.setDevice("vda");
		storage.setFormat("qcow2");
		storage.setSize(1024L);
		storage.setCacheType("writeback");
		
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(5082L);
		rsDFM.setName("s");
		rsDFM.setStorage(storage);
		CasRestImpl.editVm(rsDFM);
		
	}
	
	/**
	 * IF_4
	 * 测试配置VLAN
	 */
	public void testVlanVm(){
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(33L);
		rsDFM.setName("test-01");

		//network
		NetworkConfig network = new NetworkConfig();
		network.setMac("0c:da:41:1d:61:39");
		network.setNewMac("0c:da:41:1d:61:36");
		network.setIpAddr("192.168.0.69");
		network.setVsId(1L);
		network.setVsName("vswitch0");
		network.setDeviceModel("e1000");
		network.setMode("veb");
		network.setVsiMngId("1080::8:800:200C:417A");
		network.setVsiTypeId("222");
		network.setVsiTypeVer("121");
		network.setVsiIdFormat("UUID");
		network.setProfileId(1L);
		network.setLimitInBound(false);
		network.setLimitOutBound(false);
		network.setVirtualport(false);
		rsDFM.setNetwork(network);
		CasRestImpl.editVmNet(rsDFM);
		
	}

	/**
	 * IF_5  在指定存储池下增加指定存储卷
	 * @param hostId  指定存储池所在的主机 ID
	 * @param spName 存储池名称
	 * @param volName 存储卷名称
	 * @param capacity 存储卷的最大容量
	 * @param format  存储卷的格式：支持raw和qcow2格式
	 * @param baseFile  基础镜像文件（绝对路径），可选
	 */
	public static void testAddVolume(Long hostId, String spName, String volName, Long capacity, String format, String baseFile) {
		RsVolume rsVolume = new RsVolume();
		rsVolume.setHostId(hostId);
		rsVolume.setSpName(spName);
		rsVolume.setVolName(volName);
		rsVolume.setCapacity(capacity);
		rsVolume.setFormat(format);
		rsVolume.setBaseFile(baseFile);
		System.out.println("开始创建虚拟硬盘");
		CasRestImpl.addVol(rsVolume);
		System.out.println("创建成功！");
	}

	/**
	 * IF_7 查询CAS系统中主机下指定存储池下的存储卷列表。
	 * @param hostId 主机id
	 * @param poolName 主机池名称
	 * @param offset 分页查询结果偏移值
	 * @param pageSize 分页查询结果数量
	 */
	public static List<RsStorageVolume> testGetVmDiskInfo(Long hostId, String poolName, Integer offset, Integer pageSize){
		System.out.println("查询CAS中存储池如下：");
		List<RsStoragePool> rsStoragePoolList = CasRestImpl.queryStoragePool(hostId);
		if (rsStoragePoolList != null && rsStoragePoolList.size() > 0) {
			for (RsStoragePool rsStoragePool : rsStoragePoolList) {
				System.out.println(rsStoragePool.getName());				
			}
		}
		System.out.println("查询CAS中存储卷如下：");
		List<RsStorageVolume> rsVolumeList = CasRestImpl.getVolInfo(hostId, poolName, null, offset, pageSize);
		if (rsVolumeList != null && rsVolumeList.size() > 0) {
			for (RsStorageVolume rsStorageVolume : rsVolumeList) {
				System.out.println("存储卷" + rsStorageVolume.getName());				
			}
		}
		return rsVolumeList;
	}
	
	/**
	 * IF_9
	 * 删除虚拟硬盘测试
	 */
	public static void delDeviceTest(){
		System.out.println("*****删除虚拟硬盘测试*****");
		CasRestImpl.delVol(5l, "defaultpool", "t5");
	}

	/**
	 * IF_10
	 * 查询指定虚拟机详细信息
	 */
	public static void testQueryVmInfo(){
		// 查询CAS中所有主机接口
		Long vmId = 115L;
		RsDomain rsDomain = CasRestImpl.getVmInfo(vmId);
		System.out.println("查询指定虚拟机详细信息成功:" + rsDomain.getName());
	}

	/**
	 * IF_11
	 * 启动虚拟机
	 */
	public static void runVmTest(){
		System.out.println("*****启动虚拟机测试*****");
		RsTaskMsg task = CasRestImpl.runVm(5081l, "rest_add1441940375038");
		System.out.println(task.getResult() == 0 ? "启动虚拟机成功。" : "启动虚拟机失败,失败原因：" + task.getFailMsg());
	}
	
	/**
	 * IF_11
	 * 暂停虚拟机
	 */
	public static void pauseVmTest(){
		System.out.println("*****暂停虚拟机测试*****");
		CasRestImpl.pauseVm(115l, "rest_add1");
	}
	
	/**
	 * IF_11
	 * 恢复虚拟机
	 */
	public static void restoreVmTest(){
		System.out.println("*****恢复虚拟机测试*****");
		CasRestImpl.restoreVm(115l, "rest_add1");
	}
	
	/**
	 * IF_11
	 * 休眠虚拟机
	 */
	public static void sleepVmTest(){
		System.out.println("*****休眠虚拟机测试*****");
		CasRestImpl.sleepVm(115l, "rest_add1");
	}
	
	/**
	 * IF_11
	 * 重启虚拟机
	 */
	public static void restartVmTest(){
		System.out.println("****重启虚拟机测试*****");
		CasRestImpl.restartVm(115l, "rest_add1");
	}
	
	/**
	 * IF_11
	 * 虚拟机强制关闭电源
	 */
	public static void powerOffVmTest(){
		System.out.println("*****关闭虚拟机电源测试*****");
		RsTaskMsg task = CasRestImpl.powerOffVm(5081l, "rest_add1441940375038");
		System.out.println(task.getResult() == 0 ? "关闭虚拟机成功。" : "关闭虚拟机失败,失败原因：" + task.getFailMsg());
	}
	
	/**
	 * IF_11
	 * 关闭虚拟机
	 */
	public static void closeVmTest(){
		System.out.println("*****关闭虚拟机测试*****");
		CasRestImpl.closeVm(115l, "rest_add1");
	}
	
	/**
	 * IF_12
	 * 克隆虚拟机测试
	 */
	public static void cloneVmTest(){
		System.out.println("*****克隆虚拟机测试*****");
		Storage storage=new Storage();
		storage.setSrc("RsBatchDeploy4");
		storage.setDest("rest_add1-clone5");
		storage.setPool("defaultpool");
		List<Storage> storageList=new ArrayList<Storage>();
		storageList.add(storage);
		Network network=new Network();
		network.setKey("0c:da:41:1d:81:a2");
		network.setValue("192.168.0.88");
		List<Network> networkList=new ArrayList<Network>();
		networkList.add(network);
		RsDomainVmClone params=new RsDomainVmClone();
		params.setId(115l);
		params.setTargetHostId(6l);
		params.setTitle("rest_add1-clone5");
		params.setDomainName("rest_add1-clone5");
		params.setCloneMode(VmEnum.CAS_VM_CLONEMODE_NORMAL);
		params.setCloneType(VmEnum.CAS_VM_CLONETYPE_LOCAL);
		params.setDiskFormat(VmEnum.CAS_VM_DISKFORMAT_QCOW2);
		params.setNetworks(networkList);
		params.setStorages(storageList);
		
		RsTaskMsg msg = CasRestImpl.cloneVM(params);
		if(msg!=null){
			System.out.println("克隆虚拟机" + (msg.getResult() == 0 ? "成功" : "失败"));
		}
	}

	/**
	 * IF_13/CT-69
	 * 创建虚拟机模板
	 * @param vmId 虚拟id
	 * @param templateName 模板名称
	 * @param opType 创建方式： 1克隆成模板， 2 转移成模板
	 * @param desc 模板描述
	 */
	public static void creareTemplate(Long vmId, String templateName, String opType, String desc) {
		if (vmId == null) {
			System.out.println("创建虚拟机模板参数错误，模板ID不能为null");
			return;
		}
		
		if (templateName == null) {
			System.out.println("创建虚拟机模板参数错误，模板名称不能为null");
			return;
		}
		
		if (opType == null) {
			System.out.println("创建虚拟机模板参数错误，模板创建方式不能为null");
			return;
		} else {
			if (!"1".equals(opType) && !"2".equals(opType)) {
				System.out.println("创建虚拟机模板参数错误，模板创建方式: 1克隆成模板， 2 转移成模板。");
			}
		}

		System.out.println("*****创建虚拟机模板测试*****");
		if (desc == null) {
			desc = "";
		}
		if ("1".equals(opType)) {
			CasRestImpl.cloneToVmTmp(vmId, templateName, desc);			
		} else {
			CasRestImpl.convertToVmTmp(vmId, templateName, desc);
		}
		System.out.println("*****创建虚拟机模板成功*****");
	}

	/**
	 * IF_14/CT-67
	 * 查询模板列表信息
	 * @param offset 查询起始位置
	 * @param limit 查询的记录数
	 */
	public static void testGetTemplatesInfoList(Integer offset, Integer limit) {
		System.out.println("*****查询模板列表信息*****");
		if (offset == null || limit == null) {
			System.out.println("查询虚拟机模板参数错误，查询起始位置和查询记录数不能为null。");
			return;
		}
		RsCommList<RsDomain> templates = CasRestImpl.getTemplatesInfo(offset, limit);
		if (templates != null && templates.getData() != null) {
			if (templates.getData().size() == 0) {
				System.out.println("查询到[" + 0 + "]个模板。" );
			} else {
				System.out.println("查询到[" + templates.getData().size() + "]个模板，信息如下：" );
				for (int i = 0; i < templates.getData().size(); i++) {
					RsDomain template = templates.getData().get(i);
					StringBuffer sb = new StringBuffer();
					sb.append("序号：[").append(i).
					append("] 模板ID:").append(template.getId()).
					append("；模板名称：").append(template.getName()).
					append("；模板描述：").append(template.getDescription()).
					append("；模板CPU：").append(template.getCpu() + "核").
					append("；模板内存：").append(template.getMemory() + "MB").
					append("；模板存储：").append((template.getStorageCapacity() / 1024) + "GB").
					append("；模板操作系统").append(template.getSystem()==0?"Windows系统":"Linux系统").
					append("；模板创建日期").append(template.getCreateDate());
					System.out.println(sb.toString());
				}
			}
		}
	}
	
	/**
	 * IF_14/CT-68
	 * 查询模板信息
	 */
	public static void testGetTemplatesInfo() {
		Long tmpId = 5122L;
		RsDomain template = CasRestImpl.getVmTmpInfo(tmpId);
		List<RsDomainStorage> list = template.getStorages();
		Long capacity = 0l;
		if (list != null) {
			for (RsDomainStorage rs : list) {
				capacity += rs.getCapacity();
			}
		}
		StringBuffer sb = new StringBuffer();
		sb.append("] 模板ID:").append(template.getId()).
		append("；模板名称：").append(template.getName()).
		append("；模板描述：").append(template.getDescription()).
		append("；模板CPU：").append(template.getCpu() + "核").
		append("；模板内存：").append(template.getMemory() + "MB").
		append("；模板存储：").append((capacity) + "MB").
		append("；模板操作系统").append(template.getSystem()==0?"Windows系统":"Linux系统").
		append("；模板创建日期").append(template.getCreateDate());
		System.out.println(sb.toString());
	}
	
	/**
	 * IF_15/CT-65
	 * 删除虚拟机模板
	 * @param templateId 虚拟机模板的ID
	 */
	public static void deleteTemplate(Long templateId) {
		System.out.println("*****删除虚拟机模板测试*****");
		if (templateId == null) {
			System.out.println("删除虚拟机模板参数错误，模板ID不能为null。");
			return;
		}
		
		CasRestImpl.delVmTmp(templateId);
		System.out.println("*****删除虚拟机模板成功*****");
	}
	
	/**
	 * IF_16
	 * 批量部署虚拟机
	 * @param templateId 模板id
	 * @param storeName 存储名称
	 * @param showName 虚拟机显示名称
	 * @param targetHostId 目标主机id
	 */
	public static void batchDeployTemplate(Long templateId, String storeName, String showName, Long targetHostId) {
		System.out.println("*****批量部署虚拟机测试*****");
		List<RsDomainDeploy> data = new ArrayList<RsDomainDeploy>();
		RsCommList<RsDomainDeploy> configs = new RsCommList<RsDomainDeploy>(data);
		for (int i = 1; i < 5; i++) {
			RsDomainDeploy config1 = new RsDomainDeploy();
			RsStore store1 = new RsStore();
			store1.setSrc(storeName);
			store1.setPool("defaultpool");
			store1.setDest("RsBatchDeploy" + i);
			List<RsStore> storages1 = new ArrayList<RsStore>();
			storages1.add(store1);
			config1.setId(templateId);
			config1.setTargetHostId(targetHostId);
			config1.setDomainName("batchDeploy_10_" + i);
			config1.setTitle(showName + i);
			config1.setDeployMode(0); //0表述正常部署，1表示快速部署
			config1.setDesc("batchDeploy1:0表述正常部署");
			config1.setStorages(storages1);
			RsDomainSwitch keyValue1 = new RsDomainSwitch();
			keyValue1.setMac("0c:da:41:1d:79:44");
			keyValue1.setvSwitchName("test");
			keyValue1.setProfileName("Default");
			keyValue1.setSysIpType(1);
			keyValue1.setIsBindIp(false);
			List<RsDomainSwitch> networks1 = new ArrayList<RsDomainSwitch>();
			networks1.add(keyValue1);
			config1.setNetworks(networks1);
			configs.getData().add(config1);
		}
		CasRestImpl.batchDeploy(configs);
	}

	/**
	 * 测试项编号：IF_18 <br/>
	 * 性能监控 测试
	 */
	public static void testPerformanceMonitor() {
		System.out.println("****************测试性能监控*************");
		Long hostId = null;
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		if (hostList != null && hostList.size() > 0) {
			hostId = hostList.get(0).getId();
		}
		if (hostId != null) {
			List<VmCpuRate> cpulist = CasRestImpl.queryVmCpuRate(hostId);
			if (cpulist != null) {
				System.out.println("****************CPU*************");
				for (VmCpuRate cpu : cpulist){			
					System.out.println(cpu);
				}
			}

			System.out.println("****************内存*************");
			List<VmMemoryRate> memlist = CasRestImpl.queryVmMemRate(hostId);
			if (memlist != null) {
				for (VmMemoryRate mem : memlist){			
					System.out.println(mem);
				}
			}
		}
	}

	/**
	 * 测试项编号：IF_19 <br/>
	 * 故障告警 测试
	 */
	public static void testWarn() {
		System.out.println("********故障告警测试********");

		List<RsEvent> evList = CasRestImpl.queryWarnInfo(0, 20, null, null);
		if (evList != null && evList.size() > 0) {
			System.out.println("********获取第一页告警信息如下********");
			for (RsEvent ev : evList) {
				System.out.println(ev);
			}
		}
	}
	
	/**
	 * 测试项编号：IF_20 <br/>
	 * CPU资源绑定设置  测试
	 */
	public static void testVmCpuBind() {
		System.out.println("****************测试修改CPU绑定*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		RsDomain vm = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = vmList.get(0);
			}
		}
		if (vm != null) {
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(vm.getId());
			CpuConfig cpuConfig = new CpuConfig();
			cpuConfig.setCpuCores(2);
			cpuConfig.setCpuSockets(2);
			cpuConfig.setCpuMode("custom");
			cpuConfig.setCpuShares(512);
			
			List<BindPhysicalCpu> bindcpuList = new ArrayList<BindPhysicalCpu>();
			BindPhysicalCpu bind = new BindPhysicalCpu();
			bind.setVcpu(0);
			Integer[] pc = {0,2};
			bind.setPcpu(pc);
			bindcpuList.add(bind);
			cpuConfig.setBindcpuList(bindcpuList);
			
			rsDomain.setCpuConfig(cpuConfig );
			CasRestImpl.modifyCpuConfig(rsDomain);
		}
	}

	/**
	 * 测试项编号：IF_22 <br/>
	 * 内存资源专享设置  测试
	 */
	public static void testVmMemoryShared() {
		System.out.println("****************测试虚拟机内存共享设置*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		Long vmId = null;
		RsDomain vm = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = vmList.get(0);
				vmId = vm.getId();
			}
		}
		if (vmId != null) {
			CasRestImpl.setVmMemoryShared(vmId, vm.getMemory());
		}
	}

	/**
	 * 测试项编号：IF_23 <br/>
	 * 虚拟内存资源预留  测试
	 */
	public static void testVmMemoryBacking() {
		System.out.println("****************测试虚拟机内存预留设置*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		RsDomain vm = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = vmList.get(0);
			}
		}
		if (vm != null) {
			CasRestImpl.setVmMemoryBacking(vm.getId(), vm.getMemory(), 10);
		}
	}

	/**
	 * 测试项编号：IF_24 <br/>
	 * 虚拟资源优先级设置  测试
	 */
	public static void testVmProrioty() {
		System.out.println("****************测试虚拟机虚拟资源优先级设置*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		Long vmId = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				RsDomain vm = vmList.get(0);
				vmId = vm.getId();
			}
		}
		if (vmId != null) {
			CasRestImpl.setVmPriority(vmId, 300, 1);
		}
	}

	/**
	 * 测试项编号：IF_25 <br/>
	 * 虚拟CPU在线增加 测试
	 */
	public static void testVmCpuAdd() {
		System.out.println("****************测试虚拟机CPU在线增加*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		Long vmId = null;
		RsDomain vm = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = vmList.get(0);
				vmId = vm.getId();
			}
		}
		if (vmId != null) {
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(vmId);
			CpuConfig cpuConfig = new CpuConfig();
			cpuConfig.setCpuCores(vm.getCpu() + 1);
			cpuConfig.setCpuSockets(1);
			cpuConfig.setCpuShares(512);
			cpuConfig.setBindcpuList(vm.getBindcpuList());
			
			rsDomain.setCpuConfig(cpuConfig);
			CasRestImpl.modifyCpuConfig(rsDomain);
		}
	}
	
	/**
	 * IF_29<br/>
	 * 网络带宽单向控制 测试
	 */
	public static void testBandwidthOneWayLimit() {
		System.out.println("****************测试 网络带宽单向控制:入流量控制*************");
		RsDomain vm = null;
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = CasRestImpl.getVmInfo(vmList.get(0).getId());
			}
		}
		CasRestImpl.bandwidthOneWayLimit(vm, 512L, 1024L);
	}
	
	/**
	 * IF_30<br/>
	 * 网络带宽双向控制 测试
	 */
	public static void testBandwidthTwoWayLimit() {
		System.out.println("****************测试 网络带宽双向控制*************");
		RsDomain vm = null;
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = CasRestImpl.getVmInfo(vmList.get(0).getId());
			}
		}
		CasRestImpl.bandwidthTwoWayLimit(vm, 512L, 1024L, 2048L, 5000L);
	}
	
	/**
	 * IF_31 虚拟机主机迁移
	 * @param id 需要迁移的虚拟机 ID
	 * @param migrateType 迁移类型，枚举:0表示只迁移主机，1表示只迁移存储，2表示迁移主机和存储
	 * @param domainName 虚拟机名称
	 * @param targetHostId 目标主机id，需要迁移主机时必填
	 */
	public static void migrateVmHost(Long id, Integer migrateType, String domainName, Long targetHostId){
		if (DomainForMigrate.ONLY_HOST == migrateType) {
			System.out.println("开始迁移主机：");
		} else if (DomainForMigrate.ONLY_STORE == migrateType){
			System.out.println("开始迁移存储");
		} else if (DomainForMigrate.HOST_STORE == migrateType) {
			System.out.println("开始迁移主机和存储");
		}
		DomainForMigrate domainForMigrate = new DomainForMigrate();
		domainForMigrate.setId(id);
		domainForMigrate.setMigrateType(migrateType);
		domainForMigrate.setDomainName(domainName);
		domainForMigrate.setTargetHostId(targetHostId);
		RsTaskMsg rs = CasRestImpl.vmMigrate(domainForMigrate);
		if (rs != null) {
			System.out.println("迁移成功！" + rs.getMsgId());
		} else {
			System.out.println("迁移失败！");
		}
	}

	/**
	 * IF_32 基于本地存储的虚拟机迁移
	 * @param id 需要迁移的虚拟机 ID
	 * @param migrateType 迁移类型，枚举:0表示只迁移主机，1表示只迁移存储，2表示迁移主机和存储
	 * @param domainName 虚拟机名称
	 * @param targetPool 目标主机池名称，迁移存储时必填
	 * @param targetPath 目标主机池路径，迁移存储时必填
	 * @param targetHostId 目标主机id，需要迁移主机时必填
	 */
	public static void migrateVmHostAndStorage(Long id,Integer migrateType,String domainName,String targetPool,String targetPath,Long targetHostId){
		if (DomainForMigrate.ONLY_HOST == migrateType) {
			System.out.println("开始迁移主机：");
		} else if (DomainForMigrate.ONLY_STORE == migrateType){
			System.out.println("开始迁移存储");
		} else if (DomainForMigrate.HOST_STORE == migrateType) {
			System.out.println("开始迁移主机和存储");
		}
		DomainForMigrate domainForMigrate = new DomainForMigrate();
		domainForMigrate.setId(id);
		domainForMigrate.setMigrateType(DomainForMigrate.HOST_STORE);
		domainForMigrate.setDomainName(domainName);
		domainForMigrate.setTargetPool(targetPool);
		domainForMigrate.setTargetPath(targetPath);
		domainForMigrate.setTargetHostId(targetHostId);
		RsTaskMsg rs = CasRestImpl.vmMigrate(domainForMigrate);
		if (rs != null) {
			System.out.println("迁移成功！" + rs.getMsgId());
		} else {
			System.out.println("迁移失败！");
		}
	}
	
	/**
	 * IF_33/CT-57
	 * 创建虚拟机快照测试
	 */
	public static void createVMSnapshotTest(){
		System.out.println("***********创建虚拟机快照测试************");
		RsSnapshot rs=new RsSnapshot();
		rs.setVmId(5082l);
		rs.setName("CreateSnapshotTest_04");
		rs.setDesc("test4 snapshot");
		CasRestImpl.createVMSnapshot(rs);
	}
	
	/**
	 * IF_33/CT-58
	 * 删除虚拟机快照测试
	 */
	public static void deleteVMSnapshotTest(){
		System.out.println("***********删除虚拟机快照测试************");
		RsSnapshot rs=new RsSnapshot();
		rs.setVmId(5082l);
		rs.setName("CreateSnapshotTest_03");
		CasRestImpl.deleteVMSnapshot(rs);
	}
	
	/**
	 * CT-59
	 * 修改虚拟机快照
	 */
	public static void testModifySnapshot() {
		System.out.println("***********修改虚拟机快照begin************");
		RsTaskMsg task = CasRestImpl.modifyVmSnapshot(5082L, "createsnapshot", "bbb", "sss");
		if (task != null) {
			System.out.println("修改虚拟机快照" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
		}
		System.out.println("***********修改虚拟机快照end************");
	}
	
	/**
	 * CT-60
	 * 备份虚拟机快照
	 */
	public static void testBackupVmSnapshot() {
		System.out.println("***********备份虚拟机快照begin************");
		RsSnapshot snapshot = new RsSnapshot();
		snapshot.setVmId(5082L);
		snapshot.setName("kaka");
		RsTaskMsg task = CasRestImpl.backupVmSnapshot(snapshot);
		if (task != null) {
			System.out.println("备份虚拟机快照" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
		}
		System.out.println("***********备份虚拟机快照end************");
	}
	
	/**
	 * IF_33/CT-61
	 * 还原虚拟机快照测试
	 */
	public static void resumeVMSnapshotTest(){
		System.out.println("***********还原虚拟机快照测试************");
		RsSnapshot rs=new RsSnapshot();
		rs.setVmId(5082L);
		rs.setName("bbb");
		CasRestImpl.resumeVMSnapshot(rs);
	}
	
	/**
	 * IF_33/CT-62
	 * 查询指定虚拟机快照信息测试
	 */
	public static void loadVMSnapshotTest(){
		System.out.println("***********查询指定虚拟机快照列表************");
		Long vmId = 5082L;
		List<RsSnapshot> result = CasRestImpl.loadVMSnapshot(vmId);
		if (result != null) {
			for (RsSnapshot snap : result) {
				printSnapshot(snap);
			}
		}
	}
	
	private static void printSnapshot(RsSnapshot snap) {
		System.out.println("快照名称：" + snap.getName() + "描述:" + snap.getDesc());
		if (snap.getSnapshots() != null && snap.getSnapshots().getData() != null) {
			for (RsSnapshot child : snap.getSnapshots().getData()) {
				printSnapshot(child);
			}
		}
	}
	
	/**
	 * 本地备份虚拟机
	 * @param vmId 虚拟机id
	 * @param directory 备份目录
	 * @param backupType 备份类型。0：全量备份，1：增量备份 ，2：差异备份
	 */
	public static void backupDomainLocal(Long vmId, String directory, Integer backupType) {
		System.out.println("***********本地备份虚拟机************");
		RsDomainForBackUp backPrama = new RsDomainForBackUp();
		backPrama.setId(vmId);
		backPrama.setBackupType(backupType);			//0：全量备份，1：增量备份 ，2：差异备份
		backPrama.setDirectory(directory);
		backPrama.setStoreMode(0);						//0：主机本地目录; 1：远端服务器
		RsTaskMsg task = CasRestImpl.backupVm(backPrama);
		System.out.println("备份虚拟机" + (task.getResult() == 0 ? "成功" : "失败"));
	}
	
	/**
	 * 备份到远程主机
	 * @param vmId 虚拟机id
	 * @param directory 远程主机的备份目录
	 * @param backupType 备份类型。1：增量备份 ，2：差异备份
	 * @param targetAddr 远程主机地址
	 * @param userName 用户名
	 * @param password 密码
	 * @param type 连接方式。0：ftp方式，1：scp方式 
	 */
	public static void backupDomainRemote(Long vmId, String directory, Integer backupType, String targetAddr, 
			String userName, String password, Integer type) {
		System.out.println("***********备份到远程主机************");
		RsDomainForBackUp backPrama = new RsDomainForBackUp();
		backPrama.setId(vmId);
		backPrama.setBackupType(backupType);			//0：全量备份，1：增量备份 ，2：差异备份
		backPrama.setDirectory(directory);
		backPrama.setStoreMode(1);						//0：主机本地目录; 1：远端服务器
		backPrama.setTargetAddr(targetAddr);
		backPrama.setUserName(userName);
		backPrama.setPassword(password);
		backPrama.setType(type);
		CasRestImpl.backupVm(backPrama);
	}
	
	
	/*********************************************电信测试**************************************************/
	/**
	 * HA(启用/禁用/配置)
	 * @param rsclient
	 */
	public static void testEditHa() {
		System.out.println("***********HA(启用/禁用/配置)测试************");
		RsCluster rsCluster = new RsCluster();
		rsCluster.setId(1L);
		rsCluster.setEnableHA(1);
		rsCluster.setPriority(2);
		rsCluster.setHaMinHost(1);
		RsTaskMsg task = CasRestImpl.editHa(rsCluster);
		if (task != null) {
			System.out.println("HA(启用/禁用/配置)" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
		}
	}
	
	public static void testQueryHostNodeByClusterId() {
		System.out.println("****************测试查询集群中所有主机*************");
		List<RsHost> hostList = CasRestImpl.queryHostNodeByClusterId(1L);
		if (hostList != null && hostList.size() > 0) {
			for (RsHost rsHost : hostList) {
				System.out.println("主机ID:"+rsHost.getId()+" | 主机名:"+rsHost.getName()+" | IP:"+rsHost.getIp());
			}
		} else {
			System.out.println("集群下没有主机。");
		}
	}
	
	/**
	 * 纳管单个计算节点
	 * 获取某个计算主机的基本信息（主机名、CPU、内存）
	 */
	public static void testQueryHostBaseInfoById() {
		System.out.println("****************测试根据主机ID查询主机基本信息（主机名、CPU、内存）*************");
		RsHost rsHost = CasRestImpl.queryHostById(20L);
		if (rsHost != null) {
			System.out.println("主机ID:"+rsHost.getId()+" | 主机名:"+rsHost.getName()
			+ " | CPU:" + rsHost.getCpuCount() + " | 内存:" + rsHost.getMemorySize() + "MB");
		}
	}
	
	/**
	 * 查询计算节点基本信息
	 *（CPU/磁盘/内存/网卡）
	 */
	public static void testQueryHostInfoById() {
		System.out.println("****************测试根据主机ID查询主机基本信息（CPU/磁盘/内存/网卡）*************");
		RsHost rsHost = CasRestImpl.queryHostById(20L);
		if (rsHost != null) {
			StringBuffer str = new StringBuffer();
			str.append("主机ID:"+rsHost.getId()+" | 主机名:"+rsHost.getName());
			str.append(" | CPU:" + rsHost.getCpuCount() + " | 磁盘:" + rsHost.getDiskSize() + " MB" + " | 内存:" + rsHost.getMemorySize() + " MB");
			
			if (rsHost.getPnicList() != null && rsHost.getPnicList().size() > 0) {
				str.append(" | 网卡:[");
				for (RsPhysicalNIC rsPhysicalNIC : rsHost.getPnicList()) {
					str.append(" {name:" + rsPhysicalNIC.getName() + ", macAddr:" + rsPhysicalNIC.getMacAddr() + "},");
				}
				str.append("]");
			}
			System.out.println(str.toString());
		}
	}
	
	/**
	 * 查询计算节点状态信息
     *（电源/CPU/磁盘/内存/网卡/磁盘IO/网络IO）
	 */
	public static void testQueryHostStatusInfoById() {
		System.out.println("****************测试查询计算节点状态信息（电源/CPU/磁盘/内存/网卡/磁盘IO/网络IO）*************");
		
		Long hostId = 20L;
		RsHost rsHost = CasRestImpl.queryHostById(hostId);
		if (rsHost != null) {
			StringBuffer str = new StringBuffer();
			str.append("主机ID:"+rsHost.getId()+" | 主机名:"+rsHost.getName());
			str.append(" | 电源:" + (rsHost.getStatus() == 1 ? "开启" : "关闭"));
			str.append(" | CPU:" + rsHost.getCpuCount() + " | 磁盘:" + rsHost.getDiskSize() + " MB" + " | 内存:" + rsHost.getMemorySize() + " MB");
			
			if (rsHost.getPnicList() != null && rsHost.getPnicList().size() > 0) {
				str.append(" | 网卡:[");
				for (RsPhysicalNIC rsPhysicalNIC : rsHost.getPnicList()) {
					str.append(" {name:" + rsPhysicalNIC.getName() + ", macAddr:" + rsPhysicalNIC.getMacAddr() + "},");
				}
				str.append("]");
			}
			RsCommList<RsKeyValue> rsCommList = CasRestImpl.queryHostDiskIOAndNetIO(hostId);
			if (rsCommList != null && rsCommList.getData() != null && rsCommList.getData().size() > 0) {
				str.append(" | 磁盘IO:");
				for (RsKeyValue rsKeyValue : rsCommList.getData()) {
					if ("io".equals(rsKeyValue.getKey())) {
						str.append(rsKeyValue.getValue() + " KBps");
						break;
					}
				}
				str.append(" | 网络IO:");
				for (RsKeyValue rsKeyValue : rsCommList.getData()) {
					if ("net".equals(rsKeyValue.getKey())) {
						str.append(rsKeyValue.getValue() + " Mbps");
						break;
					}
				}
			}
			
			System.out.println(str.toString());
		}
	}
	
	/**
	 * 配置计算节点网络设置
	 */
	public static void testSetHostNetwork() {
		System.out.println("****************测试配置计算节点网络设置开始*************");
		RsVSwitch rsVSwitch = new RsVSwitch();
		rsVSwitch.setId(49L);
		rsVSwitch.setAddress("192.168.50.146");
    	rsVSwitch.setNetmask("255.255.255.0");
		CasRestImpl.setHostNetwork(rsVSwitch);
		System.out.println("****************测试配置计算节点网络设置成功*************");
	}
	
	public static void sshStatus() {
		Long id = 5073L;
		// TODO Auto-generated method stub
		RsResult rs = CasRestImpl.sshStatus(id);
		System.out.println(rs.getData());
	}
	
	/**
	 * CT-49
	 * 创建虚拟机磁盘快照
	 */
	public static void testSnapshotDomainDisk() {
		System.out.println("***********创建虚拟机磁盘快照begin************");
		RsSnapshot snapshot = new RsSnapshot();
		snapshot.setVmId(5082L);
		snapshot.setName("testVm");
		snapshot.setDevice("vda");
		RsTaskMsg task = CasRestImpl.snapshotDomainDisk(snapshot);
		if (task != null) {
			System.out.println("创建虚拟机磁盘快照" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
		}
		System.out.println("***********创建虚拟机磁盘快照end************");
	}
	
	/**
	 * CT-50
	 * 删除虚拟机磁盘快照
	 */
	public static void testDelSnapshotDomainDisk(long hostId, String poolName, String volName) {
		System.out.println("***********删除虚拟机磁盘快照begin************");
		CasRestImpl.delSnapshotDomainDisk (hostId, poolName, volName);
		System.out.println("***********删除虚拟机磁盘快照end************");
	}
	
	/**
	 * CT-51
	 * 从虚拟机磁盘快照创建磁盘
	 * 
	 */
	public static void testResumeSnapshotDomainDisk(long vmId, String devName, String snapName, String targetPool, String targetVol) {
		System.out.println("***********从虚拟机磁盘快照创建磁盘begin************");
		CasRestImpl.resumeSnapshotDomainDisk(vmId, devName, snapName, targetPool, targetVol);
		System.out.println("***********从虚拟机磁盘快照创建磁盘end************");
	}
	
	/**
	 * CT-52
	 * 测试配置虚拟机-硬盘修改大小
	 */
	public static void testModifyVmDiskSize(){
		System.out.println("**********测试修改虚拟机硬盘信息*********");
		StorageConfig storage = new StorageConfig();
		storage.setDevice("vda");
		storage.setSize(2048L);
		
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(5082L);
		rsDFM.setStorage(storage);
		CasRestImpl.editVmDisk(rsDFM);
		
	}
	
	/**
	 * CT-53
	 * 迁移虚拟机磁盘
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void testMigrateVolume () {
		System.out.println("***********迁移虚拟机磁盘begin************");
		List<Map> volumeList = new ArrayList<Map>();
		Map volInfo = new HashMap();
		volInfo.put("name", "kaka");
		volInfo.put("size", 1000d);
		volumeList.add(volInfo);
		RsMigrateVolume rsMigrateVolume = new RsMigrateVolume();
		rsMigrateVolume.setDestPoolName("defaultpool");
		rsMigrateVolume.setDestPoolNamePath("/vms/images");
		rsMigrateVolume.setHostId(18L);
		rsMigrateVolume.setSrcPoolName("wfgt29");
		rsMigrateVolume.setSrcStoragePoolPath("/vms/wfgt29");
		List<Vol> volList = new ArrayList<Vol>();
		if (volumeList != null) {
			for (Map mapVol : volumeList) {
				String name = (String)mapVol.get("name");
				Double size = (Double)mapVol.get("size");
				Vol vol = new Vol();
				vol.setName(name);
				vol.setSize(size.longValue());
				volList.add(vol);
			}
		}
		rsMigrateVolume.setVolumeList(volList);
		
		CasRestImpl.batchMigrateVolume(rsMigrateVolume);
		System.out.println("***********迁移虚拟机磁盘end************");
	}
	
	/**
	 * CT-54
	 * 复制虚拟磁盘
	 */
	public static void testCopyVolume (long hostId, String poolName, String volName, String targetPool, String targetVol) {
		System.out.println("***********复制虚拟磁盘begin************");
		CasRestImpl.copyVolume(hostId, poolName, volName, targetPool, targetVol);
		System.out.println("***********复制虚拟磁盘end************");
	}	
	
	/**
	 * CT-55
	 * 查询虚拟磁盘基本信息
	 */
	public static void testGetVolInfo(Long hostId, String poolName, String volumeName) {
		List<RsStorageVolume> rsVolumeList = CasRestImpl.getVolBaseInfo(hostId, poolName, volumeName);
		if (rsVolumeList != null && rsVolumeList.size() > 0) {
			for (RsStorageVolume rsStorageVolume : rsVolumeList) {
				StringBuffer sb = new StringBuffer();
				sb.append("] 存储卷标识:").append(rsStorageVolume.getName()).
				append("；存储卷大小：").append(rsStorageVolume.getSize() + "MB").
				append("；存储卷格式：").append(rsStorageVolume.getFormat()).
				append("；虚拟设备节点：").append(rsStorageVolume.getUsers());
				System.out.println(sb.toString());
			}
		}
	}
	
	/**
	 * CT-56
	 * 查询虚拟磁盘状态信息
	 */
	public static void testGetVolStatusInfo(Long vmId, String devName) {
		List<RsKeyValue> rsVolumeList = CasRestImpl.getVolStatusInfo(vmId, devName);
		if (rsVolumeList != null && rsVolumeList.size() > 0) {
			for (RsKeyValue rsStorageVolume : rsVolumeList) {
				StringBuffer sb = new StringBuffer();
				if (rsStorageVolume.getKey().equals("sumIO")) {
					sb.append("IO总数:").append(rsStorageVolume.getValue()).append(" KB");
				}
				if (rsStorageVolume.getKey().equals("avgIO")) {
					sb.append("平均IO速率:").append(rsStorageVolume.getValue()).append(" KBps");
				}
				if (rsStorageVolume.getKey().equals("maxIO")) {
					sb.append("最大IO速率:").append(rsStorageVolume.getValue()).append(" KBps");
				}
				if (rsStorageVolume.getKey().equals("currentIO")) {
					sb.append("实时IO速率:").append(rsStorageVolume.getValue()).append(" KBps");
				}
				System.out.println(sb.toString());
			}
		}
	}
	
	/**
	 * CT-63
	 * 从虚拟磁盘创建模板
	 */
	public static void testDiskTemplate (long hostId, String poolName, String volName, String targetPool, String targetVol) {
		System.out.println("***********从虚拟磁盘创建模板begin************");
		CasRestImpl.diskTemplate(hostId, poolName, volName, targetPool, targetVol);
		System.out.println("***********从虚拟磁盘创建模板end************");
	}
	
	/**
	 * CT-64
	 * 从虚拟磁盘快照创建模板
	 */
	public static void testDiskSnapshotTemplate (long vmId, String devName, String snapName, String targetPool, String targetVol) {
		System.out.println("***********从虚拟磁盘快照创建模板begin************");
		CasRestImpl.diskSnapshotTemplate(vmId, devName, snapName, targetPool, targetVol);
		System.out.println("***********从虚拟磁盘快照创建模板end************");
	}
	
	/**
	 * CT-66
	 * 修改虚拟机模板
	 */
	public static void modifyVmTemplate() {
		System.out.println("***********修改虚拟机模板begin************");
		RsDomain domain = new RsDomain();
		domain.setId(5122L);
		domain.setName("template");
		RsTaskMsg task = CasRestImpl.modifyVmTemplate(domain);
		if (task != null) {
			System.out.println("修改虚拟机模板" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
		}
		System.out.println("***********修改虚拟机模板end************");
		
	}
	
	
	
	
	/**
	 * 计算节点维护模式（启用）
	 */
	public static void testIntoMaintainStatus() {
		System.out.println("***********计算节点维护模式（启用）测试************");
		RsHost rsHost = new RsHost();
		rsHost.setId(20L);
		// 0:直接进入维护模式，,1:在线  2：离线  3：在线和离线	4:存储故障进入维护模式
		rsHost.setMaintainMode(0);
		RsTaskMsg task = CasRestImpl.intoMaintainStatus(rsHost);
		if (task != null) {
			System.out.println("计算节点维护模式（启用）" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
		}
	}
	
	/**
	 * 计算节点维护模式（启用/退出）
	 */
	public static void testExitMaintainStatus() {
		System.out.println("***********计算节点维护模式（退出）测试************");
		RsHost rsHost = new RsHost();
		rsHost.setId(20L);
		RsTaskMsg task = CasRestImpl.exitMaintainStatus(rsHost);
		if (task != null) {
			System.out.println("计算节点维护模式（退出）" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
		}
	}
	
	/**
	 * 计算节点心跳检查
	 */
	public static void testHostServiceStatus() {
		System.out.println("****************测试计算节点心跳检查*************");
		List<Long> hostIds = new ArrayList<Long>();
		Long hostId = 20L;
		hostIds.add(hostId);
		hostId = 18L;
		hostIds.add(hostId);
		
		List<RsHost> rsHostList = CasRestImpl.queryHostListByIds(hostIds);
		if (rsHostList != null) {
			for (RsHost rsHost : rsHostList) {
				StringBuffer str = new StringBuffer();
				str.append("主机ID:"+rsHost.getId()+" | 主机名:"+rsHost.getName());
				str.append(" | 计算服务:" + (rsHost.getStatus() == 1 ? "正常" : "异常"));
				System.out.println(str.toString());
			}
		}
	}

	
	public static void testQueryMacPortTrafficData() {
		// TODO Auto-generated method stub
		Long id = 5073L;
		String mac = "0c:da:41:1d:6d:d1";
		RsVPortTrafficInfo result = CasRestImpl.getMacPortTrafficData(id, mac);
		String objXml = RestUtil.convertObjectToXml(result, RsVPortTrafficInfo.class);
		System.out.println(objXml);
	}
	
	/**
	 * IF_1
	 * 测试创建虚拟机
	 */
	public static void testAddVM(){
		System.out.println("**********测试创建虚拟机*********");
		RsTaskMsg task = CasRestImpl.createVm(getRsDomain());
		System.out.println(task.getResult() == 0 ? "创建虚拟机成功" : "创建虚拟机失败" + task.getFailMsg());
	}
	
	private static RsDomain getRsDomain() {
		String name = "rest_add" + System.currentTimeMillis();
		
		RsDomain rsDomain = new RsDomain();
    	rsDomain.setName(name);
    	rsDomain.setTitle(name);
    	rsDomain.setDescription("test create domain from rest api");
    	rsDomain.setHostId(20L);
    	rsDomain.setClusterId(1L);

    	rsDomain.setEnableHA(0);
    	rsDomain.setPriority(1);
    	rsDomain.setAutoMigrate(0);
    	rsDomain.setHostPoolId(1L);
    	rsDomain.setMemory(1024L);
    	rsDomain.setMemoryBacking(10);

    	rsDomain.setMemoryPriority(0);
    	
    	rsDomain.setCpu(2);
    	rsDomain.setCpuSockets(1);
    	rsDomain.setCpuCores(2);

    	rsDomain.setFormatEnable(1);
    	rsDomain.setMaxCpuSocket(24);
    	rsDomain.setCpuMode("custom");
    	rsDomain.setCpuShares(512);
    	rsDomain.setBlkiotune(300);
    	rsDomain.setUuid("");
    	rsDomain.setOsBit("x86_64");
//    	rsDomain.setImgFileName((String)md.get("imgFileName"));
    	rsDomain.setImgFileType("raw");
    	rsDomain.setSystem(1);
    	// 显示驱动
    	rsDomain.setDrive("cirrus");
    	rsDomain.setOsVersion("Red Hat Enterprise Linux7(64-bit)");
    	rsDomain.setMemoryInit(1024D);
    	rsDomain.setMemoryUnit("MB");
		// 显示类型
    	rsDomain.setViewType("vnc");
		//自动配置
    	rsDomain.setAuto(0);
		// 引导设备
    	rsDomain.setBootingDevice(1);
		// 是否自动启动
    	rsDomain.setAutoBooting(0);
    	rsDomain.setAutoLoadVirtio(false);

    	List<RsDomainNetwork> rsListNetWork = new ArrayList<RsDomainNetwork>();
    	RsDomainNetwork rsDomainNetwork = new RsDomainNetwork();
		rsDomainNetwork.setVsName("vswitch0");
		rsDomainNetwork.setVsId(51L);
		rsDomainNetwork.setProfileId(1L);
		rsDomainNetwork.setDeviceModel("rtl8139");
		rsDomainNetwork.setKernelAccelerated(0);
		rsListNetWork.add(rsDomainNetwork);
		rsDomain.setNetworks(rsListNetWork);
    	
    	List<RsDomainStorage> rsListStorage = new ArrayList<RsDomainStorage>();
    	RsDomainStorage rsDomainStorage = new RsDomainStorage();
		rsDomainStorage.setStoreFile("/vms/isos/test");
		rsDomainStorage.setCacheType("writethrough");
		rsDomainStorage.setTargetBus("ide");
		rsDomainStorage.setType("file");
		rsDomainStorage.setDriveType("qcow2");
		rsDomainStorage.setPoolName("isopool");
		/** 分配类型:0指定 1动态分配。 **/
		rsDomainStorage.setAssignType(0);
		rsListStorage.add(rsDomainStorage);
		rsDomain.setStorages(rsListStorage);

    	return rsDomain;
	}
	
	/**
	 * 测试导出模板
	 */
	public static void testCompressTemplate() {
		System.out.println("***********导出模板begin************");
		String vmTemplateName = "vxlan";
		System.out.println("下载的url：" + CasRestImpl.getCompressTemplateUrl(vmTemplateName));
		
		System.out.println("***********导出模板end************");
	}
	
	public static void testDelVswitch() {
		System.out.println("***********删除虚拟交换机begin************");
		Long vsId = 17L;
		CasRestImpl.delVswitch(vsId);
		System.out.println("***********删除虚拟交换机end************");
	}
	
	public static void queryVswitch() {
		System.out.println("***********查询虚拟交换机begin************");
		Long vsId = 14L;
		RsVSwitch result = CasRestImpl.queryVswitch(vsId);
		System.out.println("name : " + result.getName());
		System.out.println("***********查询虚拟交换机end************");
	}
	
	public static void addVswitch() {
		System.out.println("***********增加虚拟交换机begin************");
		RsVSwitch rs = new RsVSwitch();
		rs.setHostId(23L);
		rs.setHostName("cvk21");
		rs.setName("rs_test1");
		rs.setDescription("desc");
		rs.setPortNum(32);
		rs.setMode(0);
		rs.setPnic("eth2");
		rs.setAddress("55.5.5.5");
		rs.setNetmask("255.255.255.0");
		rs.setEnableLacp(false);
		Long vsId = CasRestImpl.addVswitch(rs);
		System.out.println("vsId:" + vsId);
		System.out.println("***********增加虚拟交换机end************");
	}
	
	public static void modfiyVswitch() {
		System.out.println("***********修改虚拟交换机begin************");
		RsVSwitch rs = new RsVSwitch();
		rs.setId(18L);
		rs.setHostId(2L);
		rs.setHostName("server14");
		rs.setName("sc");
		rs.setDescription("desdddd");
		rs.setPortNum(32);
		rs.setMode(0);
		rs.setPnic("eth3");
		rs.setAddress("55.5.5.5");
		rs.setNetmask("255.255.255.0");
		rs.setEnableLacp(false);
		CasRestImpl.modifyVswitch(rs);
		System.out.println("***********修改虚拟交换机end************");
	}
	
	public static void queryNetwork() {
		System.out.println("***********查询虚机网卡begin************");
		Long vmId = 49L;
		List<RsDomainNetwork> list = CasRestImpl.queryNetwork(vmId);
		for (RsDomainNetwork rs : list) {
			System.out.println("network's mac :" + rs.getMac() );
		}
		System.out.println("***********查询虚机网卡end************");
	}
	
	public static void modfiyNetwork() {
		System.out.println("***********修改虚机网卡begin************");
		NetworkConfig net = new NetworkConfig();
		net.setNetType(0);
		net.setMac("0c:da:41:1d:86:51");
		net.setIpAddr("5.5.5.55");
		net.setVsId(9L);
		net.setVsName("vswitch0");
		net.setDeviceModel("virtio");
		net.setKernelAccelerated(0);
		net.setMode("veb");
		net.setProfileId(1L);
		net.setNewMac("0c:da:41:1d:86:52");
		RsDomainForModify rsDomain = new RsDomainForModify();
		rsDomain.setId(49L);
		rsDomain.setName("aa");
		rsDomain.setNetwork(net);
		CasRestImpl.modifyNetwork(rsDomain);
		System.out.println("***********修改虚机网卡end************");
	}
	
	public static void addNetwork() {
		System.out.println("***********增加虚机网卡begin************");
		NetworkConfig net = new NetworkConfig();
		net.setVsId(19L);
		net.setVsName("vs_sc");
		net.setDeviceModel("rtl8139");
		net.setKernelAccelerated(1);
		net.setMode("veb");
		net.setProfileId(1L);
		net.setProfileName("Default");
		RsDomainForModify rsDomain = new RsDomainForModify();
		rsDomain.setId(49L);
		rsDomain.setName("aa");
		rsDomain.setNetwork(net);
		RsResult rr = CasRestImpl.addNetwork(rsDomain);
		System.out.println(rr.getMessage());
		System.out.println("***********增加虚机网卡end************");
	}
	
	public static void deployTemplate() {
		System.out.println("***********模板部署虚拟机begin************");
		RsDomainDeploy rs = new RsDomainDeploy();
		List<RsStore> storages = new ArrayList<RsStore>();
		List<RsDomainSwitch> networks = new ArrayList<RsDomainSwitch>();
		rs.setStorages(storages);
		rs.setNetworks(networks);
		rs.setId(113L);
		rs.setDomainName("test_api");
		rs.setTargetHostId(3L);
		rs.setDeployMode(1);
		rs.setProtectMode(0);
		rs.setVmNum(0);
		rs.setDeployType(0);
		rs.setTitle("show_name");
		RsStore store = new RsStore();
		store.setSrc("sc_test");
		store.setDest("test_api");
		store.setPool("defaultpool");
		storages.add(store);
		RsDomainSwitch switch1 = new RsDomainSwitch();
		switch1.setMac("0c:da:41:1d:86:52");
		switch1.setvSwitchName("vswitch0");
		switch1.setProfileName("Default");
		switch1.setSysIpType(1);
		switch1.setIsBindIp(false);
		networks.add(switch1);
		RsTaskMsg result = CasRestImpl.deployTemplate(rs);
		System.out.println(result.getMsgId());
		System.out.println("***********模板部署虚拟机end************");
	}
	
	public static void addProfile() {
		System.out.println("***********添加网络策略模板begin************");
		PortProfile profile = new PortProfile();
		profile.setName("scttt");
		profile.setDescription("s");
		profile.setEnableVlan(0);
		profile.setVlanId(1);
		profile.setInbound(0);
		profile.setOutbound(0);
		profile.setEnableVsi(0);
		CasRestImpl.addProfile(profile);
		System.out.println("***********添加网络策略模板end************");
	}
	
	public static void modifyProfile() {
		System.out.println("***********修改网络策略模板begin************");
		PortProfile profile = new PortProfile();
		profile.setId(12L);
		profile.setName("scttt");
		profile.setDescription("dddddd");
		profile.setEnableVlan(0);
		profile.setVlanId(1);
		profile.setInbound(0);
		profile.setOutbound(0);
		profile.setEnableVsi(0);
		CasRestImpl.modifyProfile(profile);
		System.out.println("***********修改网络策略模板end************");
	}
	
	public static void queryProfile() {
		System.out.println("***********查询网络策略模板begin************");
		Long profileId = 12L;
		PortProfile result = CasRestImpl.queryProfile(profileId);
		System.out.println(result.getName());
		System.out.println("***********查询网络策略模板end************");
	}
	
	public static void deleteProfile() {
		System.out.println("***********删除网络策略模板begin************");
		Long profileId = 12L;
		String profileName = "scttt";
		CasRestImpl.deleteProfile(profileId, profileName);
		
		System.out.println("***********删除网络策略模板end************");

	}
	
	public static void deleteDomainNetwork() {
		System.out.println("***********删除虚拟网络begin************");
		RsDomainForModify domain = new RsDomainForModify();
		domain.setId(49L);
		domain.setName("aa");
		domain.setTitle("中1");
		domain.setDiskFlow(false);
		NetworkConfig network = new NetworkConfig();
		network.setMac("0c:da:41:1d:0a:59");
		domain.setNetwork(network);
		
		CasRestImpl.delDomainNetwork(domain);
		System.out.println("***********删除虚拟网络end************");
	}
	
	public static void isWorkVtep() {
		System.out.println("***********主机是否支持VTEP工作在内核态begin************");
		System.out.println(CasRestImpl.isWorkForVtep(1L));
		System.out.println("***********主机是否支持VTEP工作在内核态end************");

	}
	
	public static void testConvertToDomain() {
		System.out.println("***********模板转化为虚拟机begin************");
		RsDomainDeploy domain = new RsDomainDeploy();
		List<RsStore> storages = new ArrayList<RsStore>();
		List<RsDomainSwitch> networks = new ArrayList<RsDomainSwitch>();
		domain.setStorages(storages);
		domain.setNetworks(networks);
		domain.setId(57L);
		domain.setDomainName("scfi");
		domain.setTitle("scfi");
		domain.setTargetHostId(3L);
		domain.setDeployMode(1);
		domain.setProtectMode(0);
		domain.setStorages(storages);
		RsStore store = new RsStore();
		store.setSrc("mini_for_v_cvkzyb_test4");
		store.setDest("scfi");
		store.setPool("defaultpool");
		storages.add(store);
		RsDomainSwitch switch1 = new RsDomainSwitch();
		switch1.setMac("0c:da:41:1d:05:cb");
		switch1.setvSwitchName("vswitch0");
		switch1.setProfileName("Default");
		switch1.setSysIpType(1);
		switch1.setIsBindIp(false);
		networks.add(switch1);
		domain.setVmNum(0);
		domain.setDeployType(0);
		CasRestImpl.convertToDomain(domain);
		
		System.out.println("***********模板转化为虚拟机end************");
	}
	/**
	 * 扩展虚拟机（CPU/内存）
	 */
	public static void testExtendCpuAndMem() {
		System.out.println("***********测试扩展虚拟机（CPU/内存）开始************");
		Long vmId = 5083L;
		Integer cpuSocket = 2;
		Integer cpuCore = 3;
		Double memoryInit = 6144D;
		String memUnit = "MB";
		CasRestImpl.extendCpuAndMem(vmId, cpuSocket, cpuCore, memoryInit, memUnit);
		System.out.println("***********测试扩展虚拟机（CPU/内存）结束************");
	}
	
	public static void main(String[] args) {
		// CAS RESTful 服务器IP信息
		String ip = "192.168.10.3";
		// CAS RESTful 服务器Web端口信息
		int port = 8080;
		// CAS RESTful 服务器登录用户名
		String loginName = "admin";
		// CAS RESTful 服务器登录密码
		String password = "admin";
		CasRestImpl.login(ip, port, loginName, password);
		
//		testGetVmDiskInfo(rs5L, "defaultpool", 0, 20);//IF_7
//		testDetachVirtualDisk(rs115L, "vda"); //IF_8
//		delDeviceTest(rsclient); //IF_8
//		testQueryVmInfo(rsclient);
//		runVmTest(rsclient);
//		pauseVmTest(rsclient);
//		restoreVmTest(rsclient);
//		sleepVmTest(rsclient);
//		runVmTest(rsclient);
//		restartVmTest(rsclient);
//		powerOffVmTest(rsclient);
//		runVmTest(rsclient);
//		closeVmTest(rsclient);
//		cloneVmTest(rsclient);
//		creareTemplate(rs115L, "template_" + System.currentTimeMillis(), "1", "testtmp");
//		testGetTemplatesInfoList(rs0, 2);
//		deleteTemplate(rs127L);
//		batchDeployTemplate(rs128L, "template_1399799454763RsBatchDeploy4", "test_deploy", 6L);
//		testPerformanceMonitor(rsclient);
//		testWarn(rsclient);
//		testVmCpuBind(rsclient);
//		testVmMemoryShared(rsclient);
//		testVmMemoryBacking(rsclient);
//		testVmProrioty(rsclient);
//		testVmCpuAdd(rsclient);
//		testAddAndDeleteVmDisk(rs"/vms/images/test-04", 115L);
//		testBandwidthOneWayLimit(rsclient);
//		testBandwidthTwoWayLimit(rsclient);
//		migrateVmHost(rs115L, 0, "test-01", 6L);
//		migrateVmHostAndStorage(rs115L, 2, "test-04", "defaultpool", "/vms/images/", 5L);
//		createVMSnapshotTest(rsclient);
//		resumeVMSnapshotTest(rsclient);
//		deleteVMSnapshotTest(rsclient);
//		loadVMSnapshotTest(rsclient);
//		backupDomainLocal(rs115L, "/vms", 0);
//		backupDomainRemote(rs115L, "/vms", 0, "192.168.10.4", "root", "1q2w3e", 1);
		
//		testEditHa();
//		testQueryHostNodeByClusterId();
//		testQueryHostBaseInfoById();
//		testQueryHostInfoById();
//		testQueryHostStatusInfoById();
//		testSetHostNetwork();
//		sshStatus();
//		addAuthKeys();
//		testQueryHostNodeByClusterId();
		
		
		
//		testIntoMaintainStatus();
//		testExitMaintainStatus();
//		addProfile();
//		modifyProfile();
//		queryProfile();
//		deleteProfile();
//		deleteDomainNetwork();
//		testHostServiceStatus();
		
//		testHostServiceStatus();
		
//		testAddVM();
		
//		runVmTest();
//		powerOffVmTest();
		
//		testDeleteVm();
		
//		testQueryHostAndVm();
		
//		testExtendCpuAndMem();
		//49创建虚拟机磁盘快照
//		testSnapshotDomainDisk();
		//50删除虚拟机磁盘快照
//		testDelSnapshotDomainDisk(5l, "defaultpool", "t5");
		//51从虚拟机磁盘快照创建磁盘
//		testResumeSnapshotDomainDisk(18L, "defaultpool", "testVm_s_vda", "defaultpool", "sdk");
		//52更改虚拟机磁盘大小
		testModifyVmDiskSize();
//		//53迁移虚拟机磁盘
//		testMigrateVolume();
//		//54复制虚拟磁盘
//		testCopyVolume(18L, "defaultpool", "testVm_s_vda", "defaultpool", "kkk");
//		//55查询磁盘基本信息
//		testGetVolInfo(18L, "defaultpool", "ccc");
//		//56查询虚拟磁盘状态信息
//		testGetVolStatusInfo(5073L,"sda");
		//57创建虚拟机快照
//		createVMSnapshotTest();
		//58删除虚拟机快照
//		deleteVMSnapshotTest();
		//59修改虚拟机快照
//		testModifySnapshot();
		//60备份虚拟机快照
//		testBackupVmSnapshot();
		//61还原虚拟机快照
//		resumeVMSnapshotTest();
		//62查询虚拟机快照信息
//		loadVMSnapshotTest();
		//63从虚拟磁盘创建模板
//		testDiskTemplate(18L, "defaultpool", "sdk", "defaultpool", "kaka");
		//64从虚拟磁盘快照创建模板
//		testDiskSnapshotTemplate(18L, "defaultpool", "sdk", "defaultpool", "kaka1");
		//65删除模板
//		deleteTemplate(248L);
		//66修改模板名称
//		modifyVmTemplate();
		//67查询模板列表
//		testGetTemplatesInfoList(0, 50);
		//68查询模板信息
//		testGetTemplatesInfo();
		//69虚拟机转为模板
//		creareTemplate(5004L, "KPI", "2", "aaa");
		//70模板转换为虚拟机
//		testConvertToDomain();
		//71导出模板
//		testCompressTemplate();
		//72不是模板成虚拟机
//		deployTemplate();
		//75添加虚拟网卡
//		addNetwork();
		//77删除虚拟网卡
//		deleteDomainNetwork();
		//78修改虚拟网卡属性
//		modfiyNetwork();
		//79,80查询虚拟网卡基本信息
//		queryNetwork();
		//81创建虚拟交换机
//		addVswitch();
		//82修改虚拟交换机属性
//		modfiyVswitch();
		//83删除虚拟交换机
//		testDelVswitch();
		//84查询虚拟交换机
//		queryVswitch();
		//85添加虚拟端口组
//		addProfile();
		//86，87修改虚拟端口组属性
//		modifyProfile();
		//88删除虚拟端口组
//		deleteProfile();
		//89查询虚拟端口组
//		queryProfile();
		//90 VTEP安装到内核态
//		isWorkVtep();
		
		
		
		CasRestImpl.logout();
	}
}
