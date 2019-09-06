import java.util.ArrayList;
import java.util.List;

import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vm.CasRestImpl;
import com.vm.entity.RsCommList;
import com.vm.entity.RsDomain;
import com.vm.entity.RsDomainDeploy;
import com.vm.entity.RsDomainStorage;
import com.vm.entity.RsDomainSwitch;
import com.vm.entity.RsStore;
import com.vm.entity.RsTaskMsg;
import com.vm.util.AppException;


public class TempletRestTest {
	
	/** 日志记录对象。 */
    private static Log log = LogFactory.getLog(TempletRestTest.class);
    
    /**
	 * CT-63
	 * 从虚拟磁盘创建模板
	 */
    @Test
	public void diskTemplate () {
    	log.info("***********从虚拟磁盘创建模板begin************");
		try {
			long hostId = 18L;
			String poolName = "defaultpool";
			String volName = "sdk";
			String targetPool = "defaultpool";
			String targetVol = "kaka222";
			CasRestImpl.diskTemplate(hostId, poolName, volName, targetPool, targetVol);
			log.info("***********从虚拟磁盘创建模板end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * CT-64
	 * 从虚拟磁盘快照创建模板
	 */
    @Test
	public void diskSnapshotTemplate () {
    	log.info("***********从虚拟磁盘快照创建模板begin************");
		try {
			long vmId = 4451l; 
			String devName = "hda";
			String snapName = "hhh";
			String targetPool = "defaultpool"; 
			String targetVol = "ccc_snapshot_ggg3";
			RsTaskMsg task = CasRestImpl.diskSnapshotTemplate(vmId, devName, snapName, targetPool, targetVol);
			if (task != null) {
				log.info("从虚拟磁盘快照创建模板" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("***********从虚拟磁盘快照创建模板end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * IF_15/CT-65
	 * 删除虚拟机模板
	 * @param templateId 虚拟机模板的ID
	 */
    @Test
	public void deleteTemplate() {
    	log.info("*****删除虚拟机模板测试*****");
		try {
			Long templateId = 5132L;
			CasRestImpl.delVmTmp(templateId);
			log.info("*****删除虚拟机模板成功*****");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * CT-66
	 * 修改虚拟机模板
	 */
    @Test
	public void modifyVmTemplate() {
		log.info("***********修改虚拟机模板begin************");
		try {
			RsDomain domain = new RsDomain();
			domain.setId(5149L);
			domain.setName("template123");
			RsTaskMsg task = CasRestImpl.modifyVmTemplate(domain);
			if (task != null) {
				log.info("修改虚拟机模板" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("***********修改虚拟机模板end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * IF_14/CT-67
	 * 查询模板列表信息
	 * @param offset 查询起始位置
	 * @param limit 查询的记录数
	 */
    @Test
	public void getTemplatesInfoList() {
    	log.info("*****查询模板列表信息*****");

    	Integer offset = 0;
    	Integer limit = 50;
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
					log.info(sb.toString());
				}
			}
		}
	}
    
    /**
	 * IF_14/CT-68
	 * 查询模板信息
	 */
    @Test
	public void getTemplatesInfo() {
		try {
			Long tmpId = 5149L;
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
			log.info(sb.toString());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * IF_13/CT-69
	 * 虚拟机转为模板
	 * @param vmId 虚拟id
	 * @param templateName 模板名称
	 * @param opType 创建方式： 1克隆成模板， 2 转移成模板
	 * @param desc 模板描述
	 */
    @Test
	public void convertToVmTmp() {
    	log.info("*****虚拟机转为模板begin*****");
		try {
			Long vmId = 5146L; 
			String templateName = "template_" + System.currentTimeMillis();
			String desc = "testtmp";
			RsTaskMsg task = CasRestImpl.convertToVmTmp(vmId, templateName, desc);
			if (task != null) {
				log.info("虚拟机转为模板" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("*****虚拟机转为模板成功*****");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
     * 模板转为虚拟机
     */
    @Test
    public void convertToDomain() {
    	log.info("***********模板转化为虚拟机begin************");
		try {
			RsDomainDeploy domain = new RsDomainDeploy();
			List<RsStore> storages = new ArrayList<RsStore>();
			List<RsDomainSwitch> networks = new ArrayList<RsDomainSwitch>();
			domain.setStorages(storages);
			domain.setNetworks(networks);
			domain.setId(5149L);
			String name = "convertToDomain_" + System.currentTimeMillis();
			domain.setDomainName(name);
			domain.setTitle(name);
			domain.setTargetHostId(18L);
			domain.setDeployMode(1);
			domain.setProtectMode(0);
			domain.setStorages(storages);
			RsStore store = new RsStore();
			store.setSrc("tttSAME");
			store.setDest(name);
			store.setPool("isopool");
			storages.add(store);
			RsDomainSwitch switch1 = new RsDomainSwitch();
			switch1.setMac("0c:da:41:1d:47:9e");
			switch1.setvSwitchName("vswitch0");
			switch1.setProfileName("Default");
			switch1.setSysIpType(1);
			switch1.setIsBindIp(false);
			networks.add(switch1);
			domain.setVmNum(0);
			domain.setDeployType(0);
			CasRestImpl.convertToDomain(domain);
			
			log.info("***********模板转化为虚拟机end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * 测试导出模板
	 */
    @Test
	public void exportTemplate() {
    	log.info("***********导出模板begin************");
		try {
			String vmTemplateName = "ttr";
			String localPath = "/home/ttr.tar.gz";
			CasRestImpl.downLoadTempFile(vmTemplateName, localPath);
			log.info("***********导出模板end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
     * 部署模板成虚拟机
     */
    @Test
    public void deployVm() {
    	log.info("***********模板部署虚拟机begin************");
		try {
			RsDomainDeploy rs = new RsDomainDeploy();
			List<RsStore> storages = new ArrayList<RsStore>();
			List<RsDomainSwitch> networks = new ArrayList<RsDomainSwitch>();
			rs.setStorages(storages);
			rs.setNetworks(networks);
			rs.setId(5163L);
			String name = "convertToDomain_" + System.currentTimeMillis();
			rs.setDomainName(name);
			rs.setTargetHostId(18L);
			rs.setDeployMode(1);
			rs.setProtectMode(0);
			rs.setVmNum(0);
			rs.setDeployType(0);
			rs.setTitle(name);
			RsStore store = new RsStore();
			store.setSrc("KPI");
			store.setDest(name);
			store.setPool("isopool");
			storages.add(store);
			RsDomainSwitch switch1 = new RsDomainSwitch();
			switch1.setMac("0c:da:41:1d:ee:7a");
			switch1.setvSwitchName("vswitch0");
			switch1.setProfileName("Default");
			switch1.setSysIpType(1);
			switch1.setIsBindIp(false);
			networks.add(switch1);
			RsTaskMsg task = CasRestImpl.deployTemplate(rs);
			
			if (task != null) {
				log.info("模板部署虚拟机" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}

	/**
	 * 下载远程模板到共享存储（上传或导入虚拟机模板到主机已挂载的共享存储）
	 * @throws MagicException 
	 * @throws MagicMatchNotFoundException 
	 * @throws MagicParseException 
	 */
	@Test
	public void importTemple2ShareStoragePool() {
		log.info("**********测试下载远程模板到共享存储(上传或导入虚拟机模板到主机已挂载的共享存储) start*********");
		try {
			String templateFile = "/home/template.tar.gz";
			CasRestImpl.importTemple2ShareStoragePool(templateFile);
			log.info("**********测试下载远程模板到共享存储(上传或导入虚拟机模板到主机已挂载的共享存储) end*********");
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
