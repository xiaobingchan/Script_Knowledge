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
	
	/** ��־��¼���� */
    private static Log log = LogFactory.getLog(ComputeResRestTest.class);
	
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
	
	/**
	 * �ɹܼ�Ⱥ��ȫ������ڵ�
	 */
	@Test
	public void queryHostNodeByClusterId() {
		log.info("****************���Բ�ѯ��Ⱥ����������*************");
		try {
			List<RsHost> hostList = CasRestImpl.queryHostNodeByClusterId(1L);
			if (hostList != null && hostList.size() > 0) {
				for (RsHost rsHost : hostList) {
					log.info("����ID:"+rsHost.getId()+" | ������:"+rsHost.getName()+" | IP:"+rsHost.getIp());
				}
			} else {
				log.info("��Ⱥ��û��������");
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * �ɹܵ�������ڵ�
	 * ��ȡĳ�����������Ļ�����Ϣ����������CPU���ڴ棩
	 */
	@Test
	public void queryHostBaseInfoById() {
		log.info("****************���Ը�������ID��ѯ����������Ϣ����������CPU���ڴ棩*************");
		try {
			RsHost rsHost = CasRestImpl.queryHostById(18L);
			if (rsHost != null) {
				log.info("����ID:"+rsHost.getId()+" | ������:"+rsHost.getName()
						+ " | CPU:" + rsHost.getCpuCount() + " | �ڴ�:" + rsHost.getMemorySize() + "MB");
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ��ѯ����ڵ������Ϣ
	 *��CPU/����/�ڴ�/������
	 */
	@Test
	public void queryHostInfoById() {
		log.info("****************���Ը�������ID��ѯ����������Ϣ��CPU/����/�ڴ�/������*************");
		try {
			RsHost rsHost = CasRestImpl.queryHostById(23L);
			if (rsHost != null) {
				StringBuffer str = new StringBuffer();
				str.append("����ID:"+rsHost.getId()+" | ������:"+rsHost.getName());
				str.append(" | CPU:" + rsHost.getCpuCount() + " | ����:" + rsHost.getDiskSize() + " MB" + " | �ڴ�:" + rsHost.getMemorySize() + " MB");
				
				if (rsHost.getPnicList() != null && rsHost.getPnicList().size() > 0) {
					str.append(" | ����:[");
					for (RsPhysicalNIC rsPhysicalNIC : rsHost.getPnicList()) {
						str.append(" {��������:" + rsPhysicalNIC.getName() + ", MAC��ַ:" + rsPhysicalNIC.getMacAddr() + "},");
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
	 * ��ѯ����ڵ�״̬��Ϣ
     *����Դ/CPU/����/�ڴ�/����/����IO/����IO��
	 */
	@Test
	public void queryHostStatusInfoById() {
		log.info("****************���Բ�ѯ����ڵ�״̬��Ϣ����Դ/CPU/����/�ڴ�/����/����IO/����IO��*************");
		try {
			Long hostId = 18L;
			RsHost rsHost = CasRestImpl.queryHostById(hostId);
			if (rsHost != null) {
				StringBuffer str = new StringBuffer();
				str.append("����ID:"+rsHost.getId()+" | ������:"+rsHost.getName());
				str.append(" | ��Դ:" + (rsHost.getStatus() == 1 ? "����" : "�ر�"));
				str.append(" | CPU:" + rsHost.getCpuCount() + " | ����:" + rsHost.getDiskSize() + " MB" + " | �ڴ�:" + rsHost.getMemorySize() + " MB");
				
				if (rsHost.getPnicList() != null && rsHost.getPnicList().size() > 0) {
					str.append(" | ����:[");
					for (RsPhysicalNIC rsPhysicalNIC : rsHost.getPnicList()) {
						str.append(" {name:" + rsPhysicalNIC.getName() + ", macAddr:" + rsPhysicalNIC.getMacAddr() + "},");
					}
					str.append("]");
				}
				RsCommList<RsKeyValue> rsCommList = CasRestImpl.queryHostDiskIOAndNetIO(hostId);
				if (rsCommList != null && rsCommList.getData() != null && rsCommList.getData().size() > 0) {
					str.append(" | ����IO:");
					for (RsKeyValue rsKeyValue : rsCommList.getData()) {
						if ("io".equals(rsKeyValue.getKey())) {
							str.append(rsKeyValue.getValue() + " KBps");
							break;
						}
					}
					str.append(" | ����IO:");
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
	 * ���ü���ڵ���������
	 */
	@Test
	public void setHostNetwork() {
		log.info("****************�������ü���ڵ��������ÿ�ʼ*************");
		try {
			RsVSwitch rsVSwitch = new RsVSwitch();
			rsVSwitch.setId(60L);
			rsVSwitch.setAddress("192.168.50.146");
			rsVSwitch.setNetmask("255.255.255.0");
			CasRestImpl.setHostNetwork(rsVSwitch);
			log.info("****************�������ü���ڵ��������óɹ�*************");
		} catch (AppException ae) {
			log.error("���⽻���������ڡ�");
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ����ڵ�ά��ģʽ�����ã�
	 */
	@Test
	public void intoMaintainStatus() {
		log.info("***********����ڵ�ά��ģʽ�����ã�����************");
		try {
			RsHost rsHost = new RsHost();
			rsHost.setId(23L);
			// 0:ֱ�ӽ���ά��ģʽ��,1:����  2������  3�����ߺ�����	4:�洢���Ͻ���ά��ģʽ
			rsHost.setMaintainMode(0);
			RsTaskMsg task = CasRestImpl.intoMaintainStatus(rsHost);
			if (task != null) {
				log.info("����ڵ�ά��ģʽ�����ã�" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ����ڵ�ά��ģʽ���˳���
	 */
	@Test
	public void exitMaintainStatus() {
		log.info("***********����ڵ�ά��ģʽ���˳�������************");
		try {
			RsHost rsHost = new RsHost();
			rsHost.setId(23L);
			RsTaskMsg task = CasRestImpl.exitMaintainStatus(rsHost);
			if (task != null) {
				log.info("����ڵ�ά��ģʽ���˳���" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ����ڵ��������
	 */
	@Test
	public void checkHostServiceStatus() {
		log.info("****************���Լ���ڵ��������*************");
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
					str.append("����ID:"+rsHost.getId()+" | ������:"+rsHost.getName());
					str.append(" | �������:" + (rsHost.getStatus() == 1 ? "����" : "�쳣"));
					log.info(str.toString());
				}
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
}
