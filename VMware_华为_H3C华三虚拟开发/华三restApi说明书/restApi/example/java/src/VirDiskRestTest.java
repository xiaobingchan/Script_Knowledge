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
	
	/** ��־��¼���� */
    private static Log log = LogFactory.getLog(VirDiskRestTest.class);
	
	/**
	 * IF_5  ��ָ���洢��������ָ���洢��
	 * @param hostId  ָ���洢�����ڵ����� ID
	 * @param spName �洢������
	 * @param volName �洢������
	 * @param capacity �洢����������
	 * @param format  �洢��ĸ�ʽ��֧��raw��qcow2��ʽ
	 * @param baseFile  ���������ļ�������·��������ѡ
	 */
	@Test
	public void addVolume() {
		log.info("��ʼ��������Ӳ��");
		try {
			RsVolume rsVolume = new RsVolume();
			rsVolume.setHostId(23L);
			rsVolume.setSpName("isopool");
			rsVolume.setVolName("testCreateVolume1");
			rsVolume.setCapacity(512L);
			rsVolume.setFormat("qcow2");
			rsVolume.setBaseFile(null);
			CasRestImpl.addVol(rsVolume);
			log.info("��������Ӳ�̳ɹ���");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 *  Ϊָ��������������Ӳ�̡�
	 * @param path �洢�ļ�·��
	 * @param vmId  �����id
	 */
	@Test
	public void addVirtualDisk() {
		log.info("��ʼΪָ��������������Ӳ��");	
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
			log.info(rr != null? "��ӳɹ���" : "���ʧ�ܣ�");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_8 ж���������������̣�����������������
	 * @param vmId �����id
	 * @param deviceName �����������
	 */
	@Test
	public void detachVirtualDisk(){
		log.info("ж���������������̣���������������");
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
			log.info("ж�سɹ���");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ��ѯ����������б�
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
	 * ɾ���������
	 */
	@Test
	public void delVol() {
		log.info("ɾ��������̿�ʼ");
		try {
			long hostId = 23L;
			String poolName = "isopool";
			String volName = "testCreateVolume1";
			CasRestImpl.delVol(hostId, poolName, volName);
			log.info("ɾ��������̳ɹ�");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-49
	 * ������������̿���
	 */
	@Test
	public void snapshotDomainDisk() {
		log.info("***********������������̿���begin************");
		try {
			RsSnapshot snapshot = new RsSnapshot();
			snapshot.setVmId(5115L);
			snapshot.setName("a1");
			snapshot.setDevice("vda");
			RsTaskMsg task = CasRestImpl.snapshotDomainDisk(snapshot);
			if (task != null) {
				log.info("������������̿���" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("***********������������̿���end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-50
	 * ɾ����������̿���
	 */
	@Test
	public void delSnapshotDomainDisk() {
		long hostId = 23l;
		String poolName = "isopool";
		String volName = "a1_a1_vda";
		log.info("***********ɾ����������̿���begin************");
		try {
			CasRestImpl.delSnapshotDomainDisk (hostId, poolName, volName);
			log.info("***********ɾ����������̿��ճɹ�************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-51
	 * ����������̿��մ�������
	 * 
	 */
	@Test
	public void resumeSnapshotDomainDisk() {
		long vmId = 4451l; 
		String devName = "hda";
		String snapName = "hhh";
		String targetPool = "defaultpool"; 
		String targetVol = "ccc_snapshot_ggg1";

		log.info("***********����������̿��մ�������begin************");
		try {
			RsTaskMsg task = CasRestImpl.resumeSnapshotDomainDisk(vmId, devName, snapName, targetPool, targetVol);
			if (task != null) {
				log.info("����������̿��մ�������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("***********����������̿��մ������̳ɹ�************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-52
	 * �������������-Ӳ���޸Ĵ�С
	 */
	@Test
	public void modifyVmDiskSize(){
		log.info("**********���Ը���������̴�Сbegin*********");
		try {
			StorageConfig storage = new StorageConfig();
			storage.setDevice("vda");
			storage.setSize(2048L);
			
			RsDomainForModify rsDFM = new RsDomainForModify();
			rsDFM.setId(5115L);
			rsDFM.setStorage(storage);
			CasRestImpl.editVmDisk(rsDFM);
			log.info("**********���Ը���������̴�С�ɹ�*********");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-53
	 * Ǩ�����������
	 */
	@Test
	public void migrateVolume() {
		log.info("***********Ǩ�����������begin************");
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
			log.info("***********Ǩ����������̳ɹ�************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-54
	 * �����������
	 */
	@Test
	public void copyVolume () {
		log.info("***********�����������begin************");
		try {
			long hostId = 18l; 
			String poolName = "defaultpool";
			String volName = "testVm_s_vda";
			String targetPool = "defaultpool"; 
			String targetVol = "sdk333";
			CasRestImpl.copyVolume(hostId, poolName, volName, targetPool, targetVol);
			log.info("***********�����������end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-55
	 * ��ѯ������̻�����Ϣ
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
					sb.append("�洢���ʶ:").append(rsStorageVolume.getName()).
					append("���洢���С��").append(rsStorageVolume.getSize() + "MB").
					append("���洢���ʽ��").append(rsStorageVolume.getFormat()).
					append("�������豸�ڵ㣺").append(rsStorageVolume.getUsers());
					log.info(sb.toString());
				}
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * CT-56
	 * ��ѯ�������״̬��Ϣ
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
						sb.append("IO����:").append(rsStorageVolume.getValue()).append(" KB");
					}
					if (rsStorageVolume.getKey().equals("avgIO")) {
						sb.append("ƽ��IO����:").append(rsStorageVolume.getValue()).append(" KBps");
					}
					if (rsStorageVolume.getKey().equals("maxIO")) {
						sb.append("���IO����:").append(rsStorageVolume.getValue()).append(" KBps");
					}
					if (rsStorageVolume.getKey().equals("currentIO")) {
						sb.append("ʵʱIO����:").append(rsStorageVolume.getValue()).append(" KBps");
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
