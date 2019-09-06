import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vm.CasRestImpl;
import com.vm.entity.RsCommList;
import com.vm.entity.RsDomainForModify;
import com.vm.entity.RsDomainForModify.StorageConfig;
import com.vm.entity.RsDomainStorage;
import com.vm.entity.RsMigrateVolume;
import com.vm.entity.RsMigrateVolume.Vol;
import com.vm.entity.RsKeyValue;
import com.vm.entity.RsResult;
import com.vm.entity.RsSnapshot;
import com.vm.entity.RsStorageVolume;
import com.vm.entity.RsTaskMsg;
import com.vm.entity.RsVolume;
import com.vm.util.AppException;
import com.vm.util.RestUtil;


public class VirDiskRestTest {
	
	/** 日志记录对象。 */
    private static Log log = LogFactory.getLog(VirDiskRestTest.class);
	
	/**
	 * IF_5  在指定存储池下增加指定存储卷
	 * @param hostId  指定存储池所在的主机 ID
	 * @param spName 存储池名称
	 * @param volName 存储卷名称
	 * @param capacity 存储卷的最大容量
	 * @param format  存储卷的格式：支持raw和qcow2格式
	 * @param baseFile  基础镜像文件（绝对路径），可选
	 */
	@Test
	public void addVolume() {
		log.info("开始创建虚拟硬盘");
		try {
			RsVolume rsVolume = new RsVolume();
			rsVolume.setHostId(23L);
			rsVolume.setSpName("isopool");
			rsVolume.setVolName("testCreateVolume1");
			rsVolume.setCapacity(512L);
			rsVolume.setFormat("qcow2");
			rsVolume.setBaseFile(null);
			CasRestImpl.addVol(rsVolume);
			log.info("创建虚拟硬盘成功。");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 *  为指定虚拟机添加虚拟硬盘。
	 * @param path 存储文件路径
	 * @param vmId  虚拟机id
	 */
	@Test
	public void addVirtualDisk() {
		log.info("开始为指定虚拟机添加虚拟硬盘");	
		try {
			RsDomainForModify rdm = new RsDomainForModify();
			rdm.setId(5115l);
			rdm.setName("a1");
			rdm.setTitle("a1");
			StorageConfig storage = new StorageConfig(); 
			storage.setPath("/vms/isos/testCreateVolume1");
			storage.setSize(1000L);
			storage.setTargetBus("virtio");
			storage.setFileType("file");
			storage.setDevice("disk");
			storage.setCacheType("directsync");
			rdm.setStorage(storage);
			RsResult rr = CasRestImpl.addVmDisk(rdm);
			log.info(rr != null? "添加成功！" : "添加失败！");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_8 卸载虚拟机的虚拟磁盘，包括光驱、软驱。
	 * @param vmId 虚拟机id
	 * @param deviceName 虚拟磁盘名称
	 */
	@Test
	public void detachVirtualDisk(){
		log.info("卸载虚拟机的虚拟磁盘，包括光驱、软驱");
		try {
			RsDomainForModify rdm = new RsDomainForModify();
			rdm.setId(5115l);
			rdm.setName("a1");
			rdm.setTitle("a1");
			StorageConfig storage = new StorageConfig();
			rdm.setStorage(storage);
			storage.setDeviceName("vdb");
			storage.setPath("/vms/isos/testCreateVolume1");
			CasRestImpl.delVmDisk(rdm);
			log.info("卸载成功！");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 查询虚拟机磁盘列表
	 */
	@Test
	public void queryStorageInfo() {
		try {
			RsCommList<RsDomainStorage> storages = CasRestImpl.queryStorageInfo(5115l);
			String xml = RestUtil.convertObjectToXml(storages, RsCommList.class);
			log.info(xml);
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 删除虚拟磁盘
	 */
	@Test
	public void delVol() {
		log.info("删除虚拟磁盘开始");
		try {
			long hostId = 23L;
			String poolName = "isopool";
			String volName = "testCreateVolume1";
			CasRestImpl.delVol(hostId, poolName, volName);
			log.info("删除虚拟磁盘成功");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-49
	 * 创建虚拟机磁盘快照
	 */
	@Test
	public void snapshotDomainDisk() {
		log.info("***********创建虚拟机磁盘快照begin************");
		try {
			RsSnapshot snapshot = new RsSnapshot();
			snapshot.setVmId(5115L);
			snapshot.setName("a1");
			snapshot.setDevice("vda");
			RsTaskMsg task = CasRestImpl.snapshotDomainDisk(snapshot);
			if (task != null) {
				log.info("创建虚拟机磁盘快照" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("***********创建虚拟机磁盘快照end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-50
	 * 删除虚拟机磁盘快照
	 */
	@Test
	public void delSnapshotDomainDisk() {
		long hostId = 23l;
		String poolName = "isopool";
		String volName = "a1_a1_vda";
		log.info("***********删除虚拟机磁盘快照begin************");
		try {
			CasRestImpl.delSnapshotDomainDisk (hostId, poolName, volName);
			log.info("***********删除虚拟机磁盘快照成功************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-51
	 * 从虚拟机磁盘快照创建磁盘
	 * 
	 */
	@Test
	public void resumeSnapshotDomainDisk() {
		long vmId = 4451l; 
		String devName = "hda";
		String snapName = "hhh";
		String targetPool = "defaultpool"; 
		String targetVol = "ccc_snapshot_ggg1";

		log.info("***********从虚拟机磁盘快照创建磁盘begin************");
		try {
			RsTaskMsg task = CasRestImpl.resumeSnapshotDomainDisk(vmId, devName, snapName, targetPool, targetVol);
			if (task != null) {
				log.info("从虚拟机磁盘快照创建磁盘" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("***********从虚拟机磁盘快照创建磁盘成功************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-52
	 * 测试配置虚拟机-硬盘修改大小
	 */
	@Test
	public void modifyVmDiskSize(){
		log.info("**********测试更改虚拟磁盘大小begin*********");
		try {
			StorageConfig storage = new StorageConfig();
			storage.setDevice("vda");
			storage.setSize(2048L);
			
			RsDomainForModify rsDFM = new RsDomainForModify();
			rsDFM.setId(5115L);
			rsDFM.setStorage(storage);
			CasRestImpl.editVmDisk(rsDFM);
			log.info("**********测试更改虚拟磁盘大小成功*********");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-53
	 * 迁移虚拟机磁盘
	 */
	@Test
	public void migrateVolume() {
		log.info("***********迁移虚拟机磁盘begin************");
		try {
			RsMigrateVolume rsMigrateVolume = new RsMigrateVolume();
			rsMigrateVolume.setDestPoolName("wfgt29");
			rsMigrateVolume.setDestPoolNamePath("/vms/wfgt29");
			rsMigrateVolume.setHostId(18L);
			rsMigrateVolume.setSrcPoolName("defaultpool");
			rsMigrateVolume.setSrcStoragePoolPath("/vms/images");
			List<Vol> volList = new ArrayList<Vol>();
			Vol vol = new Vol();
			vol.setName("kaka1");
			vol.setSize(1000l);
			volList.add(vol);
			rsMigrateVolume.setVolumeList(volList);
			
			CasRestImpl.batchMigrateVolume(rsMigrateVolume);
			log.info("***********迁移虚拟机磁盘成功************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-54
	 * 复制虚拟磁盘
	 */
	@Test
	public void copyVolume () {
		log.info("***********复制虚拟磁盘begin************");
		try {
			long hostId = 18l; 
			String poolName = "defaultpool";
			String volName = "testVm_s_vda";
			String targetPool = "defaultpool"; 
			String targetVol = "sdk333";
			CasRestImpl.copyVolume(hostId, poolName, volName, targetPool, targetVol);
			log.info("***********复制虚拟磁盘end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-55
	 * 查询虚拟磁盘基本信息
	 */
	@Test
	public void getVolInfo() {
		try {
			Long hostId = 18L;
			String poolName = "defaultpool";
			String volumeName = "ccc";
			List<RsStorageVolume> rsVolumeList = CasRestImpl.getVolBaseInfo(hostId, poolName, volumeName);
			if (rsVolumeList != null && rsVolumeList.size() > 0) {
				for (RsStorageVolume rsStorageVolume : rsVolumeList) {
					StringBuffer sb = new StringBuffer();
					sb.append("存储卷标识:").append(rsStorageVolume.getName()).
					append("；存储卷大小：").append(rsStorageVolume.getSize() + "MB").
					append("；存储卷格式：").append(rsStorageVolume.getFormat()).
					append("；虚拟设备节点：").append(rsStorageVolume.getUsers());
					log.info(sb.toString());
				}
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-56
	 * 查询虚拟磁盘状态信息
	 */
	@Test
	public void getVolStatusInfo() {
		Long vmId = 5193L;
		String devName = "hda";
		try {
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
					log.info(sb.toString());
				}
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
