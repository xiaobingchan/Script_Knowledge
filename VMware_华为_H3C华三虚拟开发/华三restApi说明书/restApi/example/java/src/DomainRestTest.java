import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vm.CasRestImpl;
import com.vm.entity.DomainForMigrate;
import com.vm.entity.IpAddress;
import com.vm.entity.Network;
import com.vm.entity.OsNetwork;
import com.vm.entity.RsAuthKeys;
import com.vm.entity.RsCommList;
import com.vm.entity.RsDomain;
import com.vm.entity.RsDomainForModify;
import com.vm.entity.RsDomainForModify.CdromConfig;
import com.vm.entity.RsDomainNetwork;
import com.vm.entity.RsDomainStorage;
import com.vm.entity.RsDomainVmClone;
import com.vm.entity.RsKeyValue;
import com.vm.entity.RsResult;
import com.vm.entity.RsTaskMsg;
import com.vm.entity.RsVPortTrafficInfo;
import com.vm.entity.RsVncParam;
import com.vm.entity.Storage;
import com.vm.util.AppException;
import com.vm.util.RestUtil;
import com.vm.util.VmEnum;


public class DomainRestTest {
	
	/** 日志记录对象。 */
    private static Log log = LogFactory.getLog(DomainRestTest.class);
	
    @Test
    public void getOsNetwork() {
    	log.info("**********测试获取虚拟机网卡配置信息*********");
		try {
			List<OsNetwork> result = CasRestImpl.getOsNetwork(4450L);
			if (result != null) {				
				log.info("网络信息：");
				for (OsNetwork n : result) {					
					log.info(n.getName() + " " + n.getHardwareAddress());
					if (n.getIpAddresses() != null) {
						for (IpAddress ip : n.getIpAddresses()) {
							log.info(ip.getIp() + " " + ip.getType() + " " + ip.getPrefix());
						}
					}
				}
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
    }
	/**
	 * IF_1
	 * 测试创建虚拟机
	 */
	@Test
	public void addVM(){
		log.info("**********测试创建虚拟机*********");
		try {
			RsTaskMsg task = CasRestImpl.createVm(getRsDomain());
			log.info(task.getResult() == 0 ? "创建虚拟机成功" : "创建虚拟机失败" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	private RsDomain getRsDomain() {
		String name = "rest_add" + System.currentTimeMillis();
		
		RsDomain rsDomain = new RsDomain();
    	rsDomain.setName(name);
    	rsDomain.setTitle(name);
    	rsDomain.setDescription("test create domain from rest api");
    	rsDomain.setHostId(23L);
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
		rsDomainNetwork.setVsId(63L);
		rsDomainNetwork.setProfileId(1L);
		rsDomainNetwork.setDeviceModel("rtl8139");
		rsDomainNetwork.setKernelAccelerated(0);
		rsListNetWork.add(rsDomainNetwork);
		rsDomain.setNetworks(rsListNetWork);
    	
    	List<RsDomainStorage> rsListStorage = new ArrayList<RsDomainStorage>();
    	RsDomainStorage rsDomainStorage = new RsDomainStorage();
		rsDomainStorage.setStoreFile("/vms/isos/jjuntest");
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
	 * IF_11
	 * 启动虚拟机
	 */
	@Test
	public void runVm(){
		log.info("*****启动虚拟机测试*****");
		try {
			RsTaskMsg task = CasRestImpl.runVm(5140l, "rest_add1442303713024");
			log.info(task.getResult() == 0 ? "启动虚拟机成功。" : "启动虚拟机失败,失败原因：" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_11
	 * 关闭虚拟机
	 */
	@Test
	public void closeVm(){
		log.info("*****关闭虚拟机测试*****");
		try {
			RsTaskMsg task = CasRestImpl.powerOffVm(5140l, "rest_add1442303713024");
			log.info(task.getResult() == 0 ? "关闭虚拟机成功。" : "关闭虚拟机失败,失败原因：" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 扩展虚拟机（CPU/内存）
	 */
	@Test
	public void extendCpuAndMem() {
		log.info("***********测试扩展虚拟机（CPU/内存）开始************");
		try {
			Long vmId = 5140l;
			Integer cpuSocket = 2;
			Integer cpuCore = 3;
			Double memoryInit = 6144D;
			String memUnit = "MB";
			CasRestImpl.extendCpuAndMem(vmId, cpuSocket, cpuCore, memoryInit, memUnit);
			log.info("***********测试扩展虚拟机（CPU/内存）结束************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_2
	 * 测试注销虚拟机
	 */
	@Test
	public void cancelVm(){
		log.info("**********测试注销虚拟机*********");
		try {
			RsTaskMsg task = CasRestImpl.deleteVm(5143l);
			log.info(task.getResult() == 0 ? "注销虚拟机成功。" : "注销虚拟机失败,失败原因：" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_2
	 * 测试删除虚拟机
	 */
	@Test
	public void deleteVm(){
		log.info("**********测试删除虚拟机*********");
		try {
			Long vmId = 5143l;
			Integer delType = 3;
			Boolean isWipeVolume = false;
			RsTaskMsg task = CasRestImpl.deleteVm(vmId, delType, isWipeVolume);
			log.info(task.getResult() == 0 ? "删除虚拟机成功。" : "删除虚拟机失败,失败原因：" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_11
	 * 重启虚拟机
	 */
	@Test
	public void restartVm(){
		log.info("****重启虚拟机测试*****");
		try {
			RsTaskMsg task = CasRestImpl.restartVm(5143l, "rest_add1442317728795");
			log.info(task.getResult() == 0 ? "重启虚拟机成功。" : "重启虚拟机失败,失败原因：" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ssh 状态
	 */
	@Test
	public  void sshStatus() {
		try {
			Long id = 5073L;
			RsResult rs = CasRestImpl.sshStatus(id);
			log.info(rs.getData());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 获取用户IP的网络流量
	 */
	@Test
	public void queryMacPortTrafficData() {
		try {
			Long id = 5073L;
			String mac = "0c:da:41:1d:6d:d1";
			RsVPortTrafficInfo result = CasRestImpl.getMacPortTrafficData(id, mac);
			String objXml = RestUtil.convertObjectToXml(result, RsVPortTrafficInfo.class);
			log.info(objXml);
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_11
	 * 暂停虚拟机
	 */
	@Test
	public void pauseVm(){
		log.info("*****暂停虚拟机测试*****");
		try {
			RsTaskMsg task = CasRestImpl.pauseVm(5082l, "s");
			log.info(task.getResult() == 0 ? "暂停虚拟机成功。" : "暂停虚拟机失败,失败原因：" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_11
	 * 恢复虚拟机
	 */
	@Test
	public void restoreVm(){
		log.error("*****恢复虚拟机测试*****");
		try {
			RsTaskMsg task = CasRestImpl.restoreVm(5082l, "s");
			log.info(task.getResult() == 0 ? "恢复虚拟机成功。" : "恢复虚拟机失败,失败原因：" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}

	}	
	
	/**
	 * 增加免密钥登录信息
	 */
	@Test
	public void addAuthKeys() {
		try {
			Long id = 5073L;
			RsAuthKeys authKeys = new RsAuthKeys(); 
			authKeys.setDomainId(id);
			authKeys.setAuthKeys("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAABAQCxwVh32hQdfCKN2xTZVOHerKVJ2WTPRUWd7e9psKwfIM+K7d2+GB3EC52NDgimxsN+yFdb1kEmSpQ+Au539V5PoYDBhY+IKEeDpcmy0Nksw5fUXArhO+01jCe51P0e8rqwSxFzKc0rVh1fs/alFfXIKRVP39+QObWbNvwh8QWOnAPbmOwYePxje1THJ/C/GbJ2BREgLkWic0Uqm9NmfFSHbKncBRnRC1gS/E3xwA8RGd4/kSrjlWnfE1LBSZmqpodJlIJe/Zl9yC/ybsROpHqwdmUMxtXm7IQYPi4sWjwi1ObWQwdxBJh227ZO+nFctXnR3v52L0twbXiPYrGkB9ol root@ubuntu-jjun");
			RsResult rs = CasRestImpl.addAuthKeys(authKeys);
			log.info(rs.getErrorCode() + ":" + rs.getMessage());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 查询VNC信息
	 */
	@Test
	public void queryVncParam() {
		try {
			RsVncParam vnc = CasRestImpl.queryVncParam(5143l);
			String xml = RestUtil.convertObjectToXml(vnc, RsVncParam.class);
			log.info(xml);
			log.info("VNC端口:" + vnc.getPort());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 获取虚拟机基本信息（操作系统/CPU/磁盘/内存/网卡）
	 */
	@Test
	public  void queryDomainSummary() {
		Long domainId = 5073l;
		// 获取虚拟机 操作系统/cpu/内存信息
		RsCommList<RsKeyValue> summary = CasRestImpl.queryDomainSummary(domainId);
		if (summary != null && summary.getData() != null) {			
			for (RsKeyValue rv : summary.getData()) {
				log.info(rv.getKey() + " : " + rv.getValue());
			}
		} else {
			log.error("获取虚拟机信息失败");
		}
		// 获取虚拟机网卡信息
		List<RsDomainNetwork> rsnet = CasRestImpl.queryNetwork(domainId);
		log.info("=============网卡信息==============");
		if (rsnet != null) {
			for (RsDomainNetwork nt : rsnet) {
				log.info("网卡MAC地址 : " + nt.getMac());
				log.info("网卡网络信息 : ip:" + nt.getIpAddr() + " mask:" + nt.getMaskAddr() + " gateway:" + nt.getGateway());
				log.info("虚拟交换机 : " + nt.getVsName());
			}
		} else {
			log.error("获取虚拟机网卡失败");
		}
		// 获取虚拟机磁盘信息
		log.info("=============存储信息==============");
		RsCommList<RsDomainStorage> st = CasRestImpl.queryStorageInfo(domainId);
		if (st != null && st.getData() != null) {
			for (RsDomainStorage rs : st.getData()) {
				log.info("类型 : " + rs.getType());
				log.info("磁盘号 : " + rs.getDevice());
				log.info("存储文件名 : " + rs.getStoreFile());
				log.info("容量 : " + rs.getCapacity() + "MB");
				log.info("基础镜像存储文件路径 : " + rs.getBackingStore());
			}
		} else {
			log.error("获取虚拟机磁盘失败");
		}
	}
	
	/**
	 * 获取虚拟机状态信息
     *（电源/CPU/磁盘/内存/网卡/磁盘IO/网络IO）
	 */
	@Test
	public void queryDomainInfoAndDiskIOAndNetIO() {
		Long domainId = 5073l;
		RsDomain domain = CasRestImpl.getVmInfo(domainId);
		if (domain != null) {
			Integer status = domain.getStatus();
			log.info("虚拟机名称: " + domain.getName());
			log.info("虚拟机显示名称: " + domain.getTitle());
			log.info("操作系统: " + domain.getOsVersion());
			log.info("CPU: " + domain.getCpu());
			log.info("内存: " + domain.getMemory());
			if (status != null) {
				switch (status) {
				case 2:
					log.info("电源 : 运行");
					break;
				case 3:
					log.info("电源 : 关闭");
					break;
				case 4:
					log.info("电源 : 暂停");
					break;
				default:
					log.info("电源 : 关闭");
					break;
				}
				
			} else {
				log.info("电源 : 未知");
			}
			
			List<RsDomainNetwork> rsnet = domain.getNetworks();
			log.info("=============网卡信息==============");
			if (rsnet != null) {
				for (RsDomainNetwork nt : rsnet) {
					log.info("网卡MAC地址 : " + nt.getMac());
					log.info("网卡网络信息 : ip:" + nt.getIpAddr() + " mask:" + nt.getMaskAddr() + " gateway:" + nt.getGateway());
					log.info("虚拟交换机 : " + nt.getVsName());
				}
			} else {
				log.error("获取虚拟机网卡失败");
			}
			
			List<RsDomainStorage> st = domain.getStorages();
			log.info("=============存储信息==============");
			if (st != null) {
				for (RsDomainStorage rs : st) {
					log.info("类型 : " + rs.getType());
					log.info("磁盘号 : " + rs.getDevice());
					log.info("存储文件名 : " + rs.getStoreFile());
					log.info("容量 : " + rs.getCapacity() + "MB");
					log.info("基础镜像存储文件路径 : " + rs.getBackingStore());
				}
			} else {
				log.error("获取虚拟机磁盘失败");
			}
		}
		// 查询IO和NET
		log.info("磁盘IO/网络IO");
		RsCommList<RsKeyValue> rs = CasRestImpl.queryDomainDiskIOAndNetIO(domainId);
		for (RsKeyValue rv : rs.getData()) {
			log.info(rv.getKey() + " : " + rv.getValue());
		}
	}
	
	/**
	 * 查询虚拟机列表。
	 */
	@Test
	public void queryDomains() {
		RsCommList<RsDomain> result = CasRestImpl.queryDomains(0, 50);
		if (result != null && result.getData() != null) {
			for (RsDomain d : result.getData()) {
				log.info(d.getTitle());
			}
		}
	}
	/**
	 * 挂载光驱
	 */
	@Test
	public void connectCdrom() {
		try {
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(5143l);
			CdromConfig cdrom = new CdromConfig();
			cdrom.setDevice("hdd");
			cdrom.setOperation("connect");
			cdrom.setType("file");
			cdrom.setPath("/vms/isos/castools.iso");
			rsDomain.setCdromConfig(cdrom);
			CasRestImpl.editVm(rsDomain);
			log.info("挂载光驱成功");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	/**
	 * 卸载光驱
	 */
	@Test
	public void disConnectCdrom() {
		try {
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(5143l);
			CdromConfig cdrom = new CdromConfig();
			cdrom.setDevice("hdd");
			cdrom.setOperation("disconnect");
			rsDomain.setCdromConfig(cdrom);
			CasRestImpl.editVm(rsDomain);
			log.info("卸载光驱成功");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	/**
	 * 迁移准备检测
	 */
	@Test
	public void checkMigrateDomain() {
		try {
			DomainForMigrate domainMigrate = new DomainForMigrate(); 
			domainMigrate.setId(5143l);
			domainMigrate.setDomainName("rest_add1442317728795");
			domainMigrate.setTargetHostId(18l);
			domainMigrate.setMigrateType(0);
			CasRestImpl.checkMigrateDomain(domainMigrate);
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	/**
	 * IF_31 虚拟机主机迁移
	 * @param id 需要迁移的虚拟机 ID
	 * @param migrateType 迁移类型，枚举:0表示只迁移主机，1表示只迁移存储，2表示迁移主机和存储
	 * @param domainName 虚拟机名称
	 * @param targetHostId 目标主机id，需要迁移主机时必填
	 */
	@Test
	public  void migrateVmHost(){
		log.info("开始迁移主机：");
		try {
			DomainForMigrate domainForMigrate = new DomainForMigrate();
			domainForMigrate.setId(5143l);
			domainForMigrate.setMigrateType(0);
			domainForMigrate.setDomainName("rest_add1442317728795");
			domainForMigrate.setTargetHostId(18L);
			RsTaskMsg task = CasRestImpl.vmMigrate(domainForMigrate);
			if (task != null) {
				log.info("迁移主机" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
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
	@Test
	public void migrateVmHostAndStorage(){
		try {
			Long id = 5143l;
			Integer migrateType = 2;
			String domainName = "rest_add1442317728795";
			String targetPool = "isopool";
			String targetPath = "/vms/isos";
			Long targetHostId = 18L;
			
			if (DomainForMigrate.ONLY_HOST == migrateType) {
				log.info("开始迁移主机：");
			} else if (DomainForMigrate.ONLY_STORE == migrateType){
				log.info("开始迁移存储");
			} else if (DomainForMigrate.HOST_STORE == migrateType) {
				log.info("开始迁移主机和存储");
			}
			DomainForMigrate domainForMigrate = new DomainForMigrate();
			domainForMigrate.setId(id);
			domainForMigrate.setMigrateType(DomainForMigrate.HOST_STORE);
			domainForMigrate.setDomainName(domainName);
			domainForMigrate.setTargetPool(targetPool);
			domainForMigrate.setTargetPath(targetPath);
			domainForMigrate.setTargetHostId(targetHostId);
			RsTaskMsg task = CasRestImpl.vmMigrate(domainForMigrate);
			if (task != null) {
				log.info("迁移主机和存储" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 取消迁移虚拟机任务。
	 */
	@Test
	public void cancelMigrateVmTask() {
		try {
			Long msgId =1441874535397L;
			RsResult rr = CasRestImpl.cancelTask(msgId );
			if (rr != null) {
				log.info(rr.getMessage());
			} else {				
				log.info("取消迁移虚拟机失败");
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_12
	 * 克隆虚拟机测试
	 */
	@Test
	public void cloneVm(){
		log.info("*****克隆虚拟机测试*****");
		try {
			Storage storage=new Storage();
			storage.setSrc("rest");
			storage.setDest("rest_add1-clone5");
			storage.setPool("defaultpool");
			List<Storage> storageList=new ArrayList<Storage>();
			storageList.add(storage);
			Network network=new Network();
			network.setKey("0c:da:41:1d:35:93");
			network.setValue("192.168.80.88");
			List<Network> networkList=new ArrayList<Network>();
			networkList.add(network);
			RsDomainVmClone params=new RsDomainVmClone();
			params.setId(5143l);
			params.setTargetHostId(18l);
			params.setTitle("rest_add1-clone5");
			params.setDomainName("rest_add1-clone5");
			params.setCloneMode(VmEnum.CAS_VM_CLONEMODE_NORMAL);
			params.setCloneType(VmEnum.CAS_VM_CLONETYPE_LOCAL);
			params.setDiskFormat(VmEnum.CAS_VM_DISKFORMAT_QCOW2);
			params.setNetworks(networkList);
			params.setStorages(storageList);
			
			RsTaskMsg msg = CasRestImpl.cloneVM(params);
			if(msg!=null){
				log.info("克隆虚拟机" + (msg.getResult() == 0 ? "成功" : "失败"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	@Before
	public void initClient() {
		// CAS RESTful 服务器IP信息
		String ip = "192.168.0.29";
		// CAS RESTful 服务器Web端口信息
		int port = 8080;
		// CAS RESTful 服务器登录用户名
		String loginName = "admin";
		// CAS RESTful 服务器登录密码
		String password = "admin";
		CasRestImpl.login(ip, port, loginName, password);
	}
	
	@After
	public void stopClient() {
		CasRestImpl.logout();
	}
}
