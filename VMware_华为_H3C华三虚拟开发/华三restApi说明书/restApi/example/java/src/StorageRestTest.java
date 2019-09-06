import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vm.CasRestImpl;
import com.vm.entity.RsCommList;
import com.vm.entity.RsFsLunInfo;
import com.vm.entity.RsResult;
import com.vm.entity.RsSFSRelationVm;
import com.vm.entity.RsSFSRelationVm.RsVm;
import com.vm.entity.RsShareFileSystem;
import com.vm.entity.RsStoragePool;
import com.vm.util.AppException;

public class StorageRestTest {
	
	/** 日志记录对象。 */
    private static Log log = LogFactory.getLog(StorageRestTest.class);
	
	/**
	 * 创建共享存储
	 */
	@Test
	public void createShareStoragePool() {
		log.info("**********测试创建共享存储*********");
		try {
			//查询LUN信息
			Long hpId = 1L;
			String hostIp = "192.168.0.21";
			
			RsCommList<RsFsLunInfo> rsFsLunInfoCommList = CasRestImpl.queryIscsiLunList(hpId, hostIp);
			//创建共享文件系统
			if (rsFsLunInfoCommList != null && rsFsLunInfoCommList.getData() != null && rsFsLunInfoCommList.getData().size() > 0) {
				RsFsLunInfo rsFsLunInfo = rsFsLunInfoCommList.getData().get(0);
				
				RsShareFileSystem rsShareFileSystem = convertModelDataToSfs(hpId, hostIp, rsFsLunInfo);
				CasRestImpl.addShareFileStorage(rsShareFileSystem);
				
				
				rsShareFileSystem = CasRestImpl.queryShareFileStorageByName(rsShareFileSystem.getName());
				
				Long hostId = 23L;
				String hostName = "cvk21";
				RsStoragePool rsStoragePool = getStoragePool(hostId, hostName, rsShareFileSystem);
				
				//创建共享存储池
				RsResult rsResult = CasRestImpl.addStoragePool(rsStoragePool);
				if (rsResult.getErrorCode() == 0) {
					log.info("在主机 " + hostName + " 上创建共享存储 " + rsShareFileSystem.getName()+ " 成功！");
				} else {
					log.info("Error code:" + rsResult.getErrorCode() + ",  Error-Message:" + rsResult.getMessage());
				}
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	public static RsShareFileSystem convertModelDataToSfs(Long hpId, String hostIp, RsFsLunInfo rsFsLunInfo) {
		String name = "rest_sfs" + System.currentTimeMillis();
		RsShareFileSystem shareFileSystem = new RsShareFileSystem();
		shareFileSystem.setName(name);
		shareFileSystem.setHpId(hpId);
		shareFileSystem.setType(1);
		String path = "/vms/" + name;
		shareFileSystem.setPath(path);
		shareFileSystem.setCapacityControl(1);
		
		List<RsFsLunInfo> fsLunInfoList = new ArrayList<RsFsLunInfo>();
		RsFsLunInfo ssLunInfo = new RsFsLunInfo();
		ssLunInfo.setTargetHost(hostIp);
		ssLunInfo.setNaa(rsFsLunInfo.getNaa());
		ssLunInfo.setLun(rsFsLunInfo.getLun());
		ssLunInfo.setCapacity(rsFsLunInfo.getCapacity());
		fsLunInfoList.add(ssLunInfo);
		shareFileSystem.setRsFsLunInfoList(fsLunInfoList);
		return shareFileSystem;
	}
	
	private RsStoragePool getStoragePool(Long hostId, String hostName, RsShareFileSystem rsShareFileSystem) {
		RsStoragePool rsStoragePool = new RsStoragePool();
		rsStoragePool.setHostId(hostId);
		rsStoragePool.setHostName(hostName);
		rsStoragePool.setName(rsShareFileSystem.getName());
		rsStoragePool.setTitle(rsShareFileSystem.getNaa());
		rsStoragePool.setType("fs");
		rsStoragePool.setPath(rsShareFileSystem.getPath());

		rsStoragePool.setFsType("iscsi");
		rsStoragePool.setFsId(rsShareFileSystem.getId());

		rsStoragePool.setFsName(rsShareFileSystem.getName());
		rsStoragePool.setHpName("hostpool");
		
		rsStoragePool.setRsFsLunInfoList(rsShareFileSystem.getRsFsLunInfoList());
		
		return rsStoragePool;
	}

	/**
	 * 挂载共享存储
	 */
	@Test
	public void startStoragePool() {
		System.out.println("**********测试挂载共享存储*********");
		try {
			Long hostId = 23L;
			String hostName = "cvk21";
			String poolName = "rest_sfs1442297176277";
			RsResult rsResult = CasRestImpl.startStoragePool(hostId, hostName, poolName);
			if (rsResult.getErrorCode() == 0) {
				System.out.println("在主机 " + hostName + " 上挂载共享存储 " + poolName+ " 成功！");
			} else {
				System.out.println("Error code:" + rsResult.getErrorCode() + ",  Error-Message:" + rsResult.getMessage());
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * 卸载共享存储
	 */
	@Test
	public void stopStoragePool() {
		System.out.println("**********测试卸载共享存储*********");
		try {
			Long hostId = 23L;
			String hostName = "cvk21";
			String poolName = "rest_sfs1442297176277";
			RsResult rsResult = CasRestImpl.stopStoragePool(hostId, hostName, poolName);
			if (rsResult.getErrorCode() == 0) {
				System.out.println("在主机 " + hostName + " 上卸载共享存储 " + poolName+ " 成功！");
			} else {
				System.out.println("Error code:" + rsResult.getErrorCode() + ",  Error-Message:" + rsResult.getMessage());
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * 查询共享存储基本信息（存储容量）
	 */
	@Test
	public void queryStorePoolBeseInfo() {
		System.out.println("**********测试查询共享存储基本信息（存储容量）*********");
		try {
			Long hostId = 23L;
			String poolName = "rest_sfs1442297176277";
			
			RsStoragePool rsStoragePool = CasRestImpl.queryStoragePoolInfo(hostId, poolName);
			if (rsStoragePool != null && rsStoragePool.getName() != null) {
				System.out.println("共享存储 " + rsStoragePool.getName() + " 的基本信息：");
				System.out.println("名称：" + rsStoragePool.getName() 
						+ ", 目标路径：" + rsStoragePool.getPath() 
						+ ", 最大存储：" + rsStoragePool.getTotalSize() + " MB"
						+ ", 可用存储：" + rsStoragePool.getFreeSize() + " MB");
			} else {
				System.out.println("共享存储 " + poolName + " 不存在。");
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * 查询存储资源状态（使用容量/制备容量）
	 */
	@Test
	public void queryStorePoolStatusInfo() {
		System.out.println("**********测试查询存储资源状态（使用容量/制备容量）*********");
		try {
			Long hostId = 23L;
			String poolName = "t29";
			
			RsStoragePool rsStoragePool = CasRestImpl.queryStoragePoolInfo(hostId, poolName);
			if (rsStoragePool != null) {
				System.out.println("共享存储 " + poolName + " 的基本信息：");
				System.out.println("名称：" + rsStoragePool.getName() 
						+ ", 目标路径：" + rsStoragePool.getPath() 
						+ ", 状态：" + (rsStoragePool.getStatus() == 1 ? "活动" : "不活动")
						+ ", 制备容量：" + rsStoragePool.getTotalSize() + " MB"
						+ ", 使用容量：" + rsStoragePool.getFreeSize() + " MB");
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * 删除共享存储
	 */
	@Test
	public void delStoragePool() {
		System.out.println("**********测试删除共享存储*********");
		try {
			Long hostId = 23L;
			String hostName = "cvk21";
			String poolName = "rest_sfs1442297176277";
			
			RsResult rsResult = CasRestImpl.delStoragePool(hostId, hostName, poolName);
			if (rsResult.getErrorCode() == 0) {
				System.out.println("在主机 " + hostName + " 上删除共享存储 " + poolName+ " 成功！");
			} else {
				System.out.println("Error code:" + rsResult.getErrorCode() + ",  Error-Message:" + rsResult.getMessage());
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * 查询共享存储关联的虚机
     *（包括虚机开关机状态）
	 */
	@Test
	public void queryRsSFSRelationVm() {
		System.out.println("**********测试查询共享存储关联的虚拟机*********");
		try {
			String poolName = "t29";
			RsSFSRelationVm rsSFSRelationVm = CasRestImpl.queryRsSFSRelationVm(poolName);
			if (rsSFSRelationVm != null) {
				
				if (rsSFSRelationVm.getRsVms() != null && rsSFSRelationVm.getRsVms().size() > 0) {
					System.out.println("共享存储 " + poolName + " 关联的虚拟机列表： ");
					for (RsVm rsVm : rsSFSRelationVm.getRsVms()) {
						System.out.println("虚拟机名称： " + rsVm.getDomainName() + "， 显示名称： " + rsVm.getDomainTitle() 
								+ ", 主机名称： " + rsVm.getHostName() + ", 虚拟机状态： " + getStatusView(rsVm.getStatus()) + ", 存储卷： " + rsVm.getFileName());
					}
				} else {
					System.out.println("没有与共享存储 " + poolName + " 关联的虚拟机。 ");
				}
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/** 虚拟机状态。取值： 0:模板 1:未知 2:运行 3:关闭 4 暂停。 **/
	public String getStatusView(Integer status) {
		switch(status){
		case 0:
			return "模板";
		case 2:
			return "运行";
		case 3:
			return "关闭";
		case 4:
			return "暂停";
		default: 
			return "未知";
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
