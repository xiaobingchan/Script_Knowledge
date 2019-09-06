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
	
	/** ��־��¼���� */
    private static Log log = LogFactory.getLog(StorageRestTest.class);
	
	/**
	 * ��������洢
	 */
	@Test
	public void createShareStoragePool() {
		log.info("**********���Դ�������洢*********");
		try {
			//��ѯLUN��Ϣ
			Long hpId = 1L;
			String hostIp = "192.168.0.21";
			
			RsCommList<RsFsLunInfo> rsFsLunInfoCommList = CasRestImpl.queryIscsiLunList(hpId, hostIp);
			//���������ļ�ϵͳ
			if (rsFsLunInfoCommList != null && rsFsLunInfoCommList.getData() != null && rsFsLunInfoCommList.getData().size() > 0) {
				RsFsLunInfo rsFsLunInfo = rsFsLunInfoCommList.getData().get(0);
				
				RsShareFileSystem rsShareFileSystem = convertModelDataToSfs(hpId, hostIp, rsFsLunInfo);
				CasRestImpl.addShareFileStorage(rsShareFileSystem);
				
				
				rsShareFileSystem = CasRestImpl.queryShareFileStorageByName(rsShareFileSystem.getName());
				
				Long hostId = 23L;
				String hostName = "cvk21";
				RsStoragePool rsStoragePool = getStoragePool(hostId, hostName, rsShareFileSystem);
				
				//��������洢��
				RsResult rsResult = CasRestImpl.addStoragePool(rsStoragePool);
				if (rsResult.getErrorCode() == 0) {
					log.info("������ " + hostName + " �ϴ�������洢 " + rsShareFileSystem.getName()+ " �ɹ���");
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
	 * ���ع���洢
	 */
	@Test
	public void startStoragePool() {
		System.out.println("**********���Թ��ع���洢*********");
		try {
			Long hostId = 23L;
			String hostName = "cvk21";
			String poolName = "rest_sfs1442297176277";
			RsResult rsResult = CasRestImpl.startStoragePool(hostId, hostName, poolName);
			if (rsResult.getErrorCode() == 0) {
				System.out.println("������ " + hostName + " �Ϲ��ع���洢 " + poolName+ " �ɹ���");
			} else {
				System.out.println("Error code:" + rsResult.getErrorCode() + ",  Error-Message:" + rsResult.getMessage());
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * ж�ع���洢
	 */
	@Test
	public void stopStoragePool() {
		System.out.println("**********����ж�ع���洢*********");
		try {
			Long hostId = 23L;
			String hostName = "cvk21";
			String poolName = "rest_sfs1442297176277";
			RsResult rsResult = CasRestImpl.stopStoragePool(hostId, hostName, poolName);
			if (rsResult.getErrorCode() == 0) {
				System.out.println("������ " + hostName + " ��ж�ع���洢 " + poolName+ " �ɹ���");
			} else {
				System.out.println("Error code:" + rsResult.getErrorCode() + ",  Error-Message:" + rsResult.getMessage());
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * ��ѯ����洢������Ϣ���洢������
	 */
	@Test
	public void queryStorePoolBeseInfo() {
		System.out.println("**********���Բ�ѯ����洢������Ϣ���洢������*********");
		try {
			Long hostId = 23L;
			String poolName = "rest_sfs1442297176277";
			
			RsStoragePool rsStoragePool = CasRestImpl.queryStoragePoolInfo(hostId, poolName);
			if (rsStoragePool != null && rsStoragePool.getName() != null) {
				System.out.println("����洢 " + rsStoragePool.getName() + " �Ļ�����Ϣ��");
				System.out.println("���ƣ�" + rsStoragePool.getName() 
						+ ", Ŀ��·����" + rsStoragePool.getPath() 
						+ ", ���洢��" + rsStoragePool.getTotalSize() + " MB"
						+ ", ���ô洢��" + rsStoragePool.getFreeSize() + " MB");
			} else {
				System.out.println("����洢 " + poolName + " �����ڡ�");
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * ��ѯ�洢��Դ״̬��ʹ������/�Ʊ�������
	 */
	@Test
	public void queryStorePoolStatusInfo() {
		System.out.println("**********���Բ�ѯ�洢��Դ״̬��ʹ������/�Ʊ�������*********");
		try {
			Long hostId = 23L;
			String poolName = "t29";
			
			RsStoragePool rsStoragePool = CasRestImpl.queryStoragePoolInfo(hostId, poolName);
			if (rsStoragePool != null) {
				System.out.println("����洢 " + poolName + " �Ļ�����Ϣ��");
				System.out.println("���ƣ�" + rsStoragePool.getName() 
						+ ", Ŀ��·����" + rsStoragePool.getPath() 
						+ ", ״̬��" + (rsStoragePool.getStatus() == 1 ? "�" : "���")
						+ ", �Ʊ�������" + rsStoragePool.getTotalSize() + " MB"
						+ ", ʹ��������" + rsStoragePool.getFreeSize() + " MB");
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * ɾ������洢
	 */
	@Test
	public void delStoragePool() {
		System.out.println("**********����ɾ������洢*********");
		try {
			Long hostId = 23L;
			String hostName = "cvk21";
			String poolName = "rest_sfs1442297176277";
			
			RsResult rsResult = CasRestImpl.delStoragePool(hostId, hostName, poolName);
			if (rsResult.getErrorCode() == 0) {
				System.out.println("������ " + hostName + " ��ɾ������洢 " + poolName+ " �ɹ���");
			} else {
				System.out.println("Error code:" + rsResult.getErrorCode() + ",  Error-Message:" + rsResult.getMessage());
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/**
	 * ��ѯ����洢���������
     *������������ػ�״̬��
	 */
	@Test
	public void queryRsSFSRelationVm() {
		System.out.println("**********���Բ�ѯ����洢�����������*********");
		try {
			String poolName = "t29";
			RsSFSRelationVm rsSFSRelationVm = CasRestImpl.queryRsSFSRelationVm(poolName);
			if (rsSFSRelationVm != null) {
				
				if (rsSFSRelationVm.getRsVms() != null && rsSFSRelationVm.getRsVms().size() > 0) {
					System.out.println("����洢 " + poolName + " ������������б� ");
					for (RsVm rsVm : rsSFSRelationVm.getRsVms()) {
						System.out.println("��������ƣ� " + rsVm.getDomainName() + "�� ��ʾ���ƣ� " + rsVm.getDomainTitle() 
								+ ", �������ƣ� " + rsVm.getHostName() + ", �����״̬�� " + getStatusView(rsVm.getStatus()) + ", �洢�� " + rsVm.getFileName());
					}
				} else {
					System.out.println("û���빲��洢 " + poolName + " ������������� ");
				}
			}
		} catch (AppException ae) {
			System.out.println(ae.getMessage());
		}
	}
	
	/** �����״̬��ȡֵ�� 0:ģ�� 1:δ֪ 2:���� 3:�ر� 4 ��ͣ�� **/
	public String getStatusView(Integer status) {
		switch(status){
		case 0:
			return "ģ��";
		case 2:
			return "����";
		case 3:
			return "�ر�";
		case 4:
			return "��ͣ";
		default: 
			return "δ֪";
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
