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
	
	/** ��־��¼���� */
    private static Log log = LogFactory.getLog(DomainRestTest.class);
	
    @Test
    public void getOsNetwork() {
    	log.info("**********���Ի�ȡ���������������Ϣ*********");
		try {
			List<OsNetwork> result = CasRestImpl.getOsNetwork(4450L);
			if (result != null) {				
				log.info("������Ϣ��");
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
	 * ���Դ��������
	 */
	@Test
	public void addVM(){
		log.info("**********���Դ��������*********");
		try {
			RsTaskMsg task = CasRestImpl.createVm(getRsDomain());
			log.info(task.getResult() == 0 ? "����������ɹ�" : "���������ʧ��" + task.getFailMsg());
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
    	// ��ʾ����
    	rsDomain.setDrive("cirrus");
    	rsDomain.setOsVersion("Red Hat Enterprise Linux7(64-bit)");
    	rsDomain.setMemoryInit(1024D);
    	rsDomain.setMemoryUnit("MB");
		// ��ʾ����
    	rsDomain.setViewType("vnc");
		//�Զ�����
    	rsDomain.setAuto(0);
		// �����豸
    	rsDomain.setBootingDevice(1);
		// �Ƿ��Զ�����
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
		/** ��������:0ָ�� 1��̬���䡣 **/
		rsDomainStorage.setAssignType(0);
		rsListStorage.add(rsDomainStorage);
		rsDomain.setStorages(rsListStorage);

    	return rsDomain;
	}
	
	/**
	 * IF_11
	 * ���������
	 */
	@Test
	public void runVm(){
		log.info("*****�������������*****");
		try {
			RsTaskMsg task = CasRestImpl.runVm(5140l, "rest_add1442303713024");
			log.info(task.getResult() == 0 ? "����������ɹ���" : "���������ʧ��,ʧ��ԭ��" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_11
	 * �ر������
	 */
	@Test
	public void closeVm(){
		log.info("*****�ر����������*****");
		try {
			RsTaskMsg task = CasRestImpl.powerOffVm(5140l, "rest_add1442303713024");
			log.info(task.getResult() == 0 ? "�ر�������ɹ���" : "�ر������ʧ��,ʧ��ԭ��" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ��չ�������CPU/�ڴ棩
	 */
	@Test
	public void extendCpuAndMem() {
		log.info("***********������չ�������CPU/�ڴ棩��ʼ************");
		try {
			Long vmId = 5140l;
			Integer cpuSocket = 2;
			Integer cpuCore = 3;
			Double memoryInit = 6144D;
			String memUnit = "MB";
			CasRestImpl.extendCpuAndMem(vmId, cpuSocket, cpuCore, memoryInit, memUnit);
			log.info("***********������չ�������CPU/�ڴ棩����************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_2
	 * ����ע�������
	 */
	@Test
	public void cancelVm(){
		log.info("**********����ע�������*********");
		try {
			RsTaskMsg task = CasRestImpl.deleteVm(5143l);
			log.info(task.getResult() == 0 ? "ע��������ɹ���" : "ע�������ʧ��,ʧ��ԭ��" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_2
	 * ����ɾ�������
	 */
	@Test
	public void deleteVm(){
		log.info("**********����ɾ�������*********");
		try {
			Long vmId = 5143l;
			Integer delType = 3;
			Boolean isWipeVolume = false;
			RsTaskMsg task = CasRestImpl.deleteVm(vmId, delType, isWipeVolume);
			log.info(task.getResult() == 0 ? "ɾ��������ɹ���" : "ɾ�������ʧ��,ʧ��ԭ��" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_11
	 * ���������
	 */
	@Test
	public void restartVm(){
		log.info("****�������������*****");
		try {
			RsTaskMsg task = CasRestImpl.restartVm(5143l, "rest_add1442317728795");
			log.info(task.getResult() == 0 ? "����������ɹ���" : "���������ʧ��,ʧ��ԭ��" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ssh ״̬
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
	 * ��ȡ�û�IP����������
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
	 * ��ͣ�����
	 */
	@Test
	public void pauseVm(){
		log.info("*****��ͣ���������*****");
		try {
			RsTaskMsg task = CasRestImpl.pauseVm(5082l, "s");
			log.info(task.getResult() == 0 ? "��ͣ������ɹ���" : "��ͣ�����ʧ��,ʧ��ԭ��" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_11
	 * �ָ������
	 */
	@Test
	public void restoreVm(){
		log.error("*****�ָ����������*****");
		try {
			RsTaskMsg task = CasRestImpl.restoreVm(5082l, "s");
			log.info(task.getResult() == 0 ? "�ָ�������ɹ���" : "�ָ������ʧ��,ʧ��ԭ��" + task.getFailMsg());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}

	}	
	
	/**
	 * ��������Կ��¼��Ϣ
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
	 * ��ѯVNC��Ϣ
	 */
	@Test
	public void queryVncParam() {
		try {
			RsVncParam vnc = CasRestImpl.queryVncParam(5143l);
			String xml = RestUtil.convertObjectToXml(vnc, RsVncParam.class);
			log.info(xml);
			log.info("VNC�˿�:" + vnc.getPort());
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ��ȡ�����������Ϣ������ϵͳ/CPU/����/�ڴ�/������
	 */
	@Test
	public  void queryDomainSummary() {
		Long domainId = 5073l;
		// ��ȡ����� ����ϵͳ/cpu/�ڴ���Ϣ
		RsCommList<RsKeyValue> summary = CasRestImpl.queryDomainSummary(domainId);
		if (summary != null && summary.getData() != null) {			
			for (RsKeyValue rv : summary.getData()) {
				log.info(rv.getKey() + " : " + rv.getValue());
			}
		} else {
			log.error("��ȡ�������Ϣʧ��");
		}
		// ��ȡ�����������Ϣ
		List<RsDomainNetwork> rsnet = CasRestImpl.queryNetwork(domainId);
		log.info("=============������Ϣ==============");
		if (rsnet != null) {
			for (RsDomainNetwork nt : rsnet) {
				log.info("����MAC��ַ : " + nt.getMac());
				log.info("����������Ϣ : ip:" + nt.getIpAddr() + " mask:" + nt.getMaskAddr() + " gateway:" + nt.getGateway());
				log.info("���⽻���� : " + nt.getVsName());
			}
		} else {
			log.error("��ȡ���������ʧ��");
		}
		// ��ȡ�����������Ϣ
		log.info("=============�洢��Ϣ==============");
		RsCommList<RsDomainStorage> st = CasRestImpl.queryStorageInfo(domainId);
		if (st != null && st.getData() != null) {
			for (RsDomainStorage rs : st.getData()) {
				log.info("���� : " + rs.getType());
				log.info("���̺� : " + rs.getDevice());
				log.info("�洢�ļ��� : " + rs.getStoreFile());
				log.info("���� : " + rs.getCapacity() + "MB");
				log.info("��������洢�ļ�·�� : " + rs.getBackingStore());
			}
		} else {
			log.error("��ȡ���������ʧ��");
		}
	}
	
	/**
	 * ��ȡ�����״̬��Ϣ
     *����Դ/CPU/����/�ڴ�/����/����IO/����IO��
	 */
	@Test
	public void queryDomainInfoAndDiskIOAndNetIO() {
		Long domainId = 5073l;
		RsDomain domain = CasRestImpl.getVmInfo(domainId);
		if (domain != null) {
			Integer status = domain.getStatus();
			log.info("���������: " + domain.getName());
			log.info("�������ʾ����: " + domain.getTitle());
			log.info("����ϵͳ: " + domain.getOsVersion());
			log.info("CPU: " + domain.getCpu());
			log.info("�ڴ�: " + domain.getMemory());
			if (status != null) {
				switch (status) {
				case 2:
					log.info("��Դ : ����");
					break;
				case 3:
					log.info("��Դ : �ر�");
					break;
				case 4:
					log.info("��Դ : ��ͣ");
					break;
				default:
					log.info("��Դ : �ر�");
					break;
				}
				
			} else {
				log.info("��Դ : δ֪");
			}
			
			List<RsDomainNetwork> rsnet = domain.getNetworks();
			log.info("=============������Ϣ==============");
			if (rsnet != null) {
				for (RsDomainNetwork nt : rsnet) {
					log.info("����MAC��ַ : " + nt.getMac());
					log.info("����������Ϣ : ip:" + nt.getIpAddr() + " mask:" + nt.getMaskAddr() + " gateway:" + nt.getGateway());
					log.info("���⽻���� : " + nt.getVsName());
				}
			} else {
				log.error("��ȡ���������ʧ��");
			}
			
			List<RsDomainStorage> st = domain.getStorages();
			log.info("=============�洢��Ϣ==============");
			if (st != null) {
				for (RsDomainStorage rs : st) {
					log.info("���� : " + rs.getType());
					log.info("���̺� : " + rs.getDevice());
					log.info("�洢�ļ��� : " + rs.getStoreFile());
					log.info("���� : " + rs.getCapacity() + "MB");
					log.info("��������洢�ļ�·�� : " + rs.getBackingStore());
				}
			} else {
				log.error("��ȡ���������ʧ��");
			}
		}
		// ��ѯIO��NET
		log.info("����IO/����IO");
		RsCommList<RsKeyValue> rs = CasRestImpl.queryDomainDiskIOAndNetIO(domainId);
		for (RsKeyValue rv : rs.getData()) {
			log.info(rv.getKey() + " : " + rv.getValue());
		}
	}
	
	/**
	 * ��ѯ������б�
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
	 * ���ع���
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
			log.info("���ع����ɹ�");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	/**
	 * ж�ع���
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
			log.info("ж�ع����ɹ�");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	/**
	 * Ǩ��׼�����
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
	 * IF_31 ���������Ǩ��
	 * @param id ��ҪǨ�Ƶ������ ID
	 * @param migrateType Ǩ�����ͣ�ö��:0��ʾֻǨ��������1��ʾֻǨ�ƴ洢��2��ʾǨ�������ʹ洢
	 * @param domainName ���������
	 * @param targetHostId Ŀ������id����ҪǨ������ʱ����
	 */
	@Test
	public  void migrateVmHost(){
		log.info("��ʼǨ��������");
		try {
			DomainForMigrate domainForMigrate = new DomainForMigrate();
			domainForMigrate.setId(5143l);
			domainForMigrate.setMigrateType(0);
			domainForMigrate.setDomainName("rest_add1442317728795");
			domainForMigrate.setTargetHostId(18L);
			RsTaskMsg task = CasRestImpl.vmMigrate(domainForMigrate);
			if (task != null) {
				log.info("Ǩ������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_32 ���ڱ��ش洢�������Ǩ��
	 * @param id ��ҪǨ�Ƶ������ ID
	 * @param migrateType Ǩ�����ͣ�ö��:0��ʾֻǨ��������1��ʾֻǨ�ƴ洢��2��ʾǨ�������ʹ洢
	 * @param domainName ���������
	 * @param targetPool Ŀ�����������ƣ�Ǩ�ƴ洢ʱ����
	 * @param targetPath Ŀ��������·����Ǩ�ƴ洢ʱ����
	 * @param targetHostId Ŀ������id����ҪǨ������ʱ����
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
				log.info("��ʼǨ��������");
			} else if (DomainForMigrate.ONLY_STORE == migrateType){
				log.info("��ʼǨ�ƴ洢");
			} else if (DomainForMigrate.HOST_STORE == migrateType) {
				log.info("��ʼǨ�������ʹ洢");
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
				log.info("Ǩ�������ʹ洢" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * ȡ��Ǩ�����������
	 */
	@Test
	public void cancelMigrateVmTask() {
		try {
			Long msgId =1441874535397L;
			RsResult rr = CasRestImpl.cancelTask(msgId );
			if (rr != null) {
				log.info(rr.getMessage());
			} else {				
				log.info("ȡ��Ǩ�������ʧ��");
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	/**
	 * IF_12
	 * ��¡���������
	 */
	@Test
	public void cloneVm(){
		log.info("*****��¡���������*****");
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
				log.info("��¡�����" + (msg.getResult() == 0 ? "�ɹ�" : "ʧ��"));
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
