import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vm.CasRestImpl;
import com.vm.entity.RsCommList;
import com.vm.entity.RsHost;
import com.vm.entity.RsKeyValue;
import com.vm.entity.RsPhysicalNIC;
import com.vm.entity.RsTaskMsg;
import com.vm.entity.RsVSwitch;
import com.vm.util.AppException;

public class ComputeResRestTest {
	
	/** 日志记录对象。 */
    private static Log log = LogFactory.getLog(ComputeResRestTest.class);
	
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
	
	/**
	 * 纳管集群中全部计算节点
	 */
	@Test
	public void queryHostNodeByClusterId() {
		log.info("****************测试查询集群中所有主机*************");
		try {
			List<RsHost> hostList = CasRestImpl.queryHostNodeByClusterId(1L);
			if (hostList != null && hostList.size() > 0) {
				for (RsHost rsHost : hostList) {
					log.info("主机ID:"+rsHost.getId()+" | 主机名:"+rsHost.getName()+" | IP:"+rsHost.getIp());
				}
			} else {
				log.info("集群下没有主机。");
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 纳管单个计算节点
	 * 获取某个计算主机的基本信息（主机名、CPU、内存）
	 */
	@Test
	public void queryHostBaseInfoById() {
		log.info("****************测试根据主机ID查询主机基本信息（主机名、CPU、内存）*************");
		try {
			RsHost rsHost = CasRestImpl.queryHostById(18L);
			if (rsHost != null) {
				log.info("主机ID:"+rsHost.getId()+" | 主机名:"+rsHost.getName()
						+ " | CPU:" + rsHost.getCpuCount() + " | 内存:" + rsHost.getMemorySize() + "MB");
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 查询计算节点基本信息
	 *（CPU/磁盘/内存/网卡）
	 */
	@Test
	public void queryHostInfoById() {
		log.info("****************测试根据主机ID查询主机基本信息（CPU/磁盘/内存/网卡）*************");
		try {
			RsHost rsHost = CasRestImpl.queryHostById(23L);
			if (rsHost != null) {
				StringBuffer str = new StringBuffer();
				str.append("主机ID:"+rsHost.getId()+" | 主机名:"+rsHost.getName());
				str.append(" | CPU:" + rsHost.getCpuCount() + " | 磁盘:" + rsHost.getDiskSize() + " MB" + " | 内存:" + rsHost.getMemorySize() + " MB");
				
				if (rsHost.getPnicList() != null && rsHost.getPnicList().size() > 0) {
					str.append(" | 网卡:[");
					for (RsPhysicalNIC rsPhysicalNIC : rsHost.getPnicList()) {
						str.append(" {网卡名称:" + rsPhysicalNIC.getName() + ", MAC地址:" + rsPhysicalNIC.getMacAddr() + "},");
					}
					str.append("]");
				}
				log.info(str.toString());
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 查询计算节点状态信息
     *（电源/CPU/磁盘/内存/网卡/磁盘IO/网络IO）
	 */
	@Test
	public void queryHostStatusInfoById() {
		log.info("****************测试查询计算节点状态信息（电源/CPU/磁盘/内存/网卡/磁盘IO/网络IO）*************");
		try {
			Long hostId = 18L;
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
				log.info(str.toString());
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 配置计算节点网络设置
	 */
	@Test
	public void setHostNetwork() {
		log.info("****************测试配置计算节点网络设置开始*************");
		try {
			RsVSwitch rsVSwitch = new RsVSwitch();
			rsVSwitch.setId(60L);
			rsVSwitch.setAddress("192.168.50.146");
			rsVSwitch.setNetmask("255.255.255.0");
			CasRestImpl.setHostNetwork(rsVSwitch);
			log.info("****************测试配置计算节点网络设置成功*************");
		} catch (AppException ae) {
			log.error("虚拟交换机不存在。");
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 计算节点维护模式（启用）
	 */
	@Test
	public void intoMaintainStatus() {
		log.info("***********计算节点维护模式（启用）测试************");
		try {
			RsHost rsHost = new RsHost();
			rsHost.setId(23L);
			// 0:直接进入维护模式，,1:在线  2：离线  3：在线和离线	4:存储故障进入维护模式
			rsHost.setMaintainMode(0);
			RsTaskMsg task = CasRestImpl.intoMaintainStatus(rsHost);
			if (task != null) {
				log.info("计算节点维护模式（启用）" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 计算节点维护模式（退出）
	 */
	@Test
	public void exitMaintainStatus() {
		log.info("***********计算节点维护模式（退出）测试************");
		try {
			RsHost rsHost = new RsHost();
			rsHost.setId(23L);
			RsTaskMsg task = CasRestImpl.exitMaintainStatus(rsHost);
			if (task != null) {
				log.info("计算节点维护模式（退出）" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 计算节点心跳检查
	 */
	@Test
	public void checkHostServiceStatus() {
		log.info("****************测试计算节点心跳检查*************");
		try {
			List<Long> hostIds = new ArrayList<Long>();
			Long hostId = 23L;
			hostIds.add(hostId);
			hostId = 18L;
			hostIds.add(hostId);
			
			List<RsHost> rsHostList = CasRestImpl.queryHostListByIds(hostIds);
			if (rsHostList != null) {
				for (RsHost rsHost : rsHostList) {
					StringBuffer str = new StringBuffer();
					str.append("主机ID:"+rsHost.getId()+" | 主机名:"+rsHost.getName());
					str.append(" | 计算服务:" + (rsHost.getStatus() == 1 ? "正常" : "异常"));
					log.info(str.toString());
				}
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
}
