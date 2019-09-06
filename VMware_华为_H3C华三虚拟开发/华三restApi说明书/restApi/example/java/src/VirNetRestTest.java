import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vm.CasRestImpl;
import com.vm.entity.IpAddress;
import com.vm.entity.OsNetwork;
import com.vm.entity.PortProfile;
import com.vm.entity.RsDomainForModify;
import com.vm.entity.RsKeyValue;
import com.vm.entity.RsDomainForModify.NetworkConfig;
import com.vm.entity.RsDomainNetwork;
import com.vm.entity.RsResult;
import com.vm.entity.RsVSwitch;
import com.vm.util.AppException;


public class VirNetRestTest {
	
	/** 日志记录对象。 */
    private static Log log = LogFactory.getLog(VirNetRestTest.class);
    
    /**
     * 卸载虚拟网卡
     */
	@Test
	public void downMac() {
		log.info("开始卸载虚拟网卡");
		try {
			Long id = 5073L;
			String mac = "0C:DA:41:1D:6D:D1";
			CasRestImpl.downMac(id, mac);
			log.info("卸载虚拟网卡成功。");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 添加虚拟网卡
	 */
	@Test
	public void addNetwork() {
		log.info("***********增加虚机网卡begin************");
		try {
			NetworkConfig net = new NetworkConfig();
			net.setVsId(41L);
			net.setVsName("teste");
			net.setDeviceModel("rtl8139");
			net.setKernelAccelerated(1);
			net.setMode("veb");
			net.setProfileId(1L);
			net.setProfileName("Default");
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(5165L);
			rsDomain.setName("vv");
			rsDomain.setNetwork(net);
			RsResult rr = CasRestImpl.addNetwork(rsDomain);
			log.info(rr.getMessage());
			log.info("***********增加虚机网卡end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 删除虚拟网卡
	 */
	@Test
	public void deleteDomainNetwork() {
		log.info("***********删除虚拟网络begin************");
		try {
			RsDomainForModify domain = new RsDomainForModify();
			domain.setId(5165L);
			domain.setName("vv");
			NetworkConfig network = new NetworkConfig();
			network.setMac("0c:da:41:1d:95:04");
			domain.setNetwork(network);
			
			CasRestImpl.delDomainNetwork(domain);
			log.info("***********删除虚拟网络end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 修改虚拟网卡的属性（MAC等）
	 */
	@Test
	public void modfiyNetwork() {
		log.info("***********修改虚机网卡begin************");
		try {
			NetworkConfig net = new NetworkConfig();
			net.setMac("0c:da:41:1d:86:55");
			net.setIpAddr("5.5.5.155");
			net.setVsId(41L);
			net.setVsName("teste");
			net.setDeviceModel("virtio");
			net.setKernelAccelerated(0);
			net.setMode("veb");
			net.setProfileId(1L);
			net.setProfileName("Default");
			net.setNewMac("0c:da:41:1d:86:55");
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(5165L);
			rsDomain.setName("vv");
			rsDomain.setNetwork(net);
			CasRestImpl.modifyNetwork(rsDomain);
			log.info("***********修改虚机网卡end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 查询虚拟网卡基本信息
    （IP/子网掩码/网关/MAC/DNS等）
	 */
	@Test
	public void queryNetworkBaseInfo() {
		log.info("***********查询虚机网卡begin************");
		try {
			Long vmId = 5073L;
			List<RsDomainNetwork> list = CasRestImpl.queryNetwork(vmId);
			for (RsDomainNetwork rs : list) {
				log.info("mac :" + rs.getMac() + ", ip:" + rs.getIpAddr());
			}
			List<OsNetwork> result = CasRestImpl.getOsNetwork(vmId);
			if (result != null) {				
				log.info("网络信息：");
				for (OsNetwork n : result) {					
					log.info("网口信息：" + n.getName() + " " + n.getHardwareAddress());
					if (n.getIpAddresses() != null) {
						for (IpAddress ip : n.getIpAddresses()) {
							log.info("配置信息" + ip.getIp() + " " + ip.getType() + " " + ip.getPrefix());
						}
					}
				}
			}
			log.info("***********查询虚机网卡end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 查询虚拟网卡状态信息
     （IO总数/平均IO速率/最大IO速率/实时IO速率/限速等）
	 */
	@Test
	public void queryNetworkStatusInfo() {
		Long vmId = 5193L;
		String mac = "0c:da:41:1d:60:d3";
		try {
			List<RsDomainNetwork> list = CasRestImpl.queryNetwork(vmId);
			for (RsDomainNetwork rs : list) {
				log.info("mac :" + rs.getMac() + ", ip:" + rs.getIpAddr() + ",  入方向平均带宽:"+rs.getInAvgBandwidth() + ",  出方向平均带宽:" + rs.getOutAvgBandwidth());
			}
			List<RsKeyValue> rsVolumeList = CasRestImpl.getNetStatusInfo(vmId, mac);
			if (rsVolumeList != null && rsVolumeList.size() > 0) {
				for (RsKeyValue rsStorageVolume : rsVolumeList) {
					StringBuffer sb = new StringBuffer();
					if (rsStorageVolume.getKey().equals("sumIO")) {
						sb.append("IO总数:").append(rsStorageVolume.getValue()).append(" Mb");
					}
					if (rsStorageVolume.getKey().equals("avgIO")) {
						sb.append("平均IO速率:").append(rsStorageVolume.getValue()).append(" Mbps");
					}
					if (rsStorageVolume.getKey().equals("maxIO")) {
						sb.append("最大IO速率:").append(rsStorageVolume.getValue()).append(" Mbps");
					}
					if (rsStorageVolume.getKey().equals("currentIO")) {
						sb.append("实时IO速率:").append(rsStorageVolume.getValue()).append(" Mbps");
					}
					log.info(sb.toString());
				}
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 创建虚拟交换机
	 */
	@Test
	public void addVswitch() {
		log.info("***********增加虚拟交换机begin************");
		try {
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
			log.info("vsId:" + vsId);
			log.info("***********增加虚拟交换机end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 修改虚拟交换机属性
	 */
	@Test
	public void modfiyVswitch() {
		log.info("***********修改虚拟交换机begin************");
		
		try {
			RsVSwitch rs = new RsVSwitch();
			rs.setId(66L);
			rs.setHostId(23L);
			rs.setHostName("cvk21");
			rs.setName("rs_test1");
			rs.setDescription("desdddd");
			rs.setPortNum(32);
			rs.setMode(0);
			rs.setPnic("eth2");
			rs.setAddress("55.5.5.55");
			rs.setNetmask("255.255.255.0");
			rs.setEnableLacp(false);
			CasRestImpl.modifyVswitch(rs);
			log.info("***********修改虚拟交换机end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 删除虚拟交换机
	 */
	@Test
	public void delVswitch() {
		log.info("***********删除虚拟交换机begin************");
		try {
			Long vsId = 66L;
			CasRestImpl.delVswitch(vsId);
			log.info("***********删除虚拟交换机end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * 查询虚拟交换机
	 */
	@Test
	public void queryVswitch() {
		log.info("***********查询虚拟交换机begin************");
		try {
			Long vsId = 43L;
			RsVSwitch result = CasRestImpl.queryVswitch(vsId);
			log.info("name : " + result.getName());
			log.info("***********查询虚拟交换机end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	@Test
	public void addProfile() {
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
	
	@Test
	public void modifyProfile() {
		System.out.println("***********修改网络策略模板begin************");
		PortProfile profile = new PortProfile();
		profile.setId(6L);
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
	
	@Test
	public void deleteProfile() {
		System.out.println("***********删除网络策略模板begin************");
		Long profileId = 6L;
		String profileName = "scttt";
		CasRestImpl.deleteProfile(profileId, profileName);
		
		System.out.println("***********删除网络策略模板end************");

	}
	
	@Test
	public void queryProfile() {
		System.out.println("***********查询网络策略模板begin************");
		Long profileId = 1L;
		PortProfile result = CasRestImpl.queryProfile(profileId);
		System.out.println(result.getName());
		System.out.println("***********查询网络策略模板end************");
	}
	
	@Test
	public void isWorkVtep() {
		System.out.println("***********主机是否支持VTEP工作在内核态begin************");
		System.out.println(CasRestImpl.isWorkForVtep(23L));
		System.out.println("***********主机是否支持VTEP工作在内核态end************");
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
