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
	
	/** ��־��¼���� */
    private static Log log = LogFactory.getLog(TempletRestTest.class);
    
    /**
	 * CT-63
	 * ��������̴���ģ��
	 */
    @Test
	public void diskTemplate () {
    	log.info("***********��������̴���ģ��begin************");
		try {
			long hostId = 18L;
			String poolName = "defaultpool";
			String volName = "sdk";
			String targetPool = "defaultpool";
			String targetVol = "kaka222";
			CasRestImpl.diskTemplate(hostId, poolName, volName, targetPool, targetVol);
			log.info("***********��������̴���ģ��end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * CT-64
	 * ��������̿��մ���ģ��
	 */
    @Test
	public void diskSnapshotTemplate () {
    	log.info("***********��������̿��մ���ģ��begin************");
		try {
			long vmId = 4451l; 
			String devName = "hda";
			String snapName = "hhh";
			String targetPool = "defaultpool"; 
			String targetVol = "ccc_snapshot_ggg3";
			RsTaskMsg task = CasRestImpl.diskSnapshotTemplate(vmId, devName, snapName, targetPool, targetVol);
			if (task != null) {
				log.info("��������̿��մ���ģ��" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("***********��������̿��մ���ģ��end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * IF_15/CT-65
	 * ɾ�������ģ��
	 * @param templateId �����ģ���ID
	 */
    @Test
	public void deleteTemplate() {
    	log.info("*****ɾ�������ģ�����*****");
		try {
			Long templateId = 5132L;
			CasRestImpl.delVmTmp(templateId);
			log.info("*****ɾ�������ģ��ɹ�*****");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * CT-66
	 * �޸������ģ��
	 */
    @Test
	public void modifyVmTemplate() {
		log.info("***********�޸������ģ��begin************");
		try {
			RsDomain domain = new RsDomain();
			domain.setId(5149L);
			domain.setName("template123");
			RsTaskMsg task = CasRestImpl.modifyVmTemplate(domain);
			if (task != null) {
				log.info("�޸������ģ��" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("***********�޸������ģ��end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * IF_14/CT-67
	 * ��ѯģ���б���Ϣ
	 * @param offset ��ѯ��ʼλ��
	 * @param limit ��ѯ�ļ�¼��
	 */
    @Test
	public void getTemplatesInfoList() {
    	log.info("*****��ѯģ���б���Ϣ*****");

    	Integer offset = 0;
    	Integer limit = 50;
		RsCommList<RsDomain> templates = CasRestImpl.getTemplatesInfo(offset, limit);
		if (templates != null && templates.getData() != null) {
			if (templates.getData().size() == 0) {
				System.out.println("��ѯ��[" + 0 + "]��ģ�塣" );
			} else {
				System.out.println("��ѯ��[" + templates.getData().size() + "]��ģ�壬��Ϣ���£�" );
				for (int i = 0; i < templates.getData().size(); i++) {
					RsDomain template = templates.getData().get(i);
					StringBuffer sb = new StringBuffer();
					sb.append("��ţ�[").append(i).
					append("] ģ��ID:").append(template.getId()).
					append("��ģ�����ƣ�").append(template.getName()).
					append("��ģ��������").append(template.getDescription()).
					append("��ģ��CPU��").append(template.getCpu() + "��").
					append("��ģ���ڴ棺").append(template.getMemory() + "MB").
					append("��ģ��洢��").append((template.getStorageCapacity() / 1024) + "GB").
					append("��ģ�����ϵͳ").append(template.getSystem()==0?"Windowsϵͳ":"Linuxϵͳ").
					append("��ģ�崴������").append(template.getCreateDate());
					log.info(sb.toString());
				}
			}
		}
	}
    
    /**
	 * IF_14/CT-68
	 * ��ѯģ����Ϣ
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
			sb.append("] ģ��ID:").append(template.getId()).
			append("��ģ�����ƣ�").append(template.getName()).
			append("��ģ��������").append(template.getDescription()).
			append("��ģ��CPU��").append(template.getCpu() + "��").
			append("��ģ���ڴ棺").append(template.getMemory() + "MB").
			append("��ģ��洢��").append((capacity) + "MB").
			append("��ģ�����ϵͳ").append(template.getSystem()==0?"Windowsϵͳ":"Linuxϵͳ").
			append("��ģ�崴������").append(template.getCreateDate());
			log.info(sb.toString());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * IF_13/CT-69
	 * �����תΪģ��
	 * @param vmId ����id
	 * @param templateName ģ������
	 * @param opType ������ʽ�� 1��¡��ģ�壬 2 ת�Ƴ�ģ��
	 * @param desc ģ������
	 */
    @Test
	public void convertToVmTmp() {
    	log.info("*****�����תΪģ��begin*****");
		try {
			Long vmId = 5146L; 
			String templateName = "template_" + System.currentTimeMillis();
			String desc = "testtmp";
			RsTaskMsg task = CasRestImpl.convertToVmTmp(vmId, templateName, desc);
			if (task != null) {
				log.info("�����תΪģ��" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("*****�����תΪģ��ɹ�*****");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
     * ģ��תΪ�����
     */
    @Test
    public void convertToDomain() {
    	log.info("***********ģ��ת��Ϊ�����begin************");
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
			
			log.info("***********ģ��ת��Ϊ�����end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * ���Ե���ģ��
	 */
    @Test
	public void exportTemplate() {
    	log.info("***********����ģ��begin************");
		try {
			String vmTemplateName = "ttr";
			String localPath = "/home/ttr.tar.gz";
			CasRestImpl.downLoadTempFile(vmTemplateName, localPath);
			log.info("***********����ģ��end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
     * ����ģ��������
     */
    @Test
    public void deployVm() {
    	log.info("***********ģ�岿�������begin************");
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
				log.info("ģ�岿�������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}

	/**
	 * ����Զ��ģ�嵽����洢���ϴ����������ģ�嵽�����ѹ��صĹ���洢��
	 * @throws MagicException 
	 * @throws MagicMatchNotFoundException 
	 * @throws MagicParseException 
	 */
	@Test
	public void importTemple2ShareStoragePool() {
		log.info("**********��������Զ��ģ�嵽����洢(�ϴ����������ģ�嵽�����ѹ��صĹ���洢) start*********");
		try {
			String templateFile = "/home/template.tar.gz";
			CasRestImpl.importTemple2ShareStoragePool(templateFile);
			log.info("**********��������Զ��ģ�嵽����洢(�ϴ����������ģ�嵽�����ѹ��صĹ���洢) end*********");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}

	}
	
	@Before
	public void initClient() {
		// CAS RESTful ������IP��Ϣ
		String ip = "192.168.0.29";
		// CAS RESTful ������Web�˿���Ϣ
		int port = 8080;
		// CAS RESTful ��������¼�û���
		String loginName = "admin";
		// CAS RESTful ��������¼����
		String password = "admin";
		CasRestImpl.login(ip, port, loginName, password);
	}

	@After
	public void stopClient() {
		CasRestImpl.logout();
	}
}
