import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.vm.CasRestImpl;
import com.vm.entity.DomainForMigrate;
import com.vm.entity.Network;
import com.vm.entity.PortProfile;
import com.vm.entity.RsCluster;
import com.vm.entity.RsCommList;
import com.vm.entity.RsDomain;
import com.vm.entity.RsDomainDeploy;
import com.vm.entity.RsDomainForBackUp;
import com.vm.entity.RsDomainForModify;
import com.vm.entity.RsDomainForModify.BasicConfig;
import com.vm.entity.RsDomainForModify.CpuConfig;
import com.vm.entity.RsDomainForModify.CpuConfig.BindPhysicalCpu;
import com.vm.entity.RsDomainForModify.MemoryConfig;
import com.vm.entity.RsDomainForModify.NetworkConfig;
import com.vm.entity.RsDomainForModify.StorageConfig;
import com.vm.entity.RsDomainNetwork;
import com.vm.entity.RsDomainStorage;
import com.vm.entity.RsDomainSwitch;
import com.vm.entity.RsDomainVmClone;
import com.vm.entity.RsEvent;
import com.vm.entity.RsHost;
import com.vm.entity.RsKeyValue;
import com.vm.entity.RsMigrateVolume;
import com.vm.entity.RsMigrateVolume.Vol;
import com.vm.entity.RsPhysicalNIC;
import com.vm.entity.RsResult;
import com.vm.entity.RsSnapshot;
import com.vm.entity.RsStoragePool;
import com.vm.entity.RsStorageVolume;
import com.vm.entity.RsStore;
import com.vm.entity.RsTaskMsg;
import com.vm.entity.RsVPortTrafficInfo;
import com.vm.entity.RsVSwitch;
import com.vm.entity.RsVolume;
import com.vm.entity.Storage;
import com.vm.entity.VmCpuRate;
import com.vm.entity.VmMemoryRate;
import com.vm.util.RestUtil;
import com.vm.util.VmEnum;

public class CASRestClient {
    
	/**
	 * ���Բ�ѯ�����������
	 */
	public static void testQueryHostAndVm(){
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		
		if (hostList != null && hostList.size() > 0) {
			System.out.println("��ѯCAS�������������£�");
			for (RsHost host : hostList) {
				System.out.println("����ID��" + host.getId() + ", ��������" + host.getName() + ", ����IP��" + host.getIp());				
			}
			for (RsHost host : hostList) {
				System.out.println("");
				// ��ѯCAS������������ӿ�
				List<RsDomain>  vmList = CasRestImpl.queryAllVm(host.getId());
				System.out.println("���� " + hostList.get(0).getName() + " ��������������£�");
				if (vmList != null && vmList.size() > 0) {
					for (RsDomain vm : vmList) {
						System.out.println("�����:" + vm.getName() + "(" + vm.getId() + ")");				
					}
				}
			}
		}
	}
	
	/**
	 * IF_2
	 * ����ɾ�������
	 */
	public static void testDeleteVm(){
		System.out.println("**********����ɾ�������*********");
		RsTaskMsg task = CasRestImpl.deleteVm(5081l);
		System.out.println(task.getResult() == 0 ? "ɾ��������ɹ���" : "ɾ�������ʧ��,ʧ��ԭ��" + task.getFailMsg());
	}
	
	/**
	 * IF_3
	 * �������������-������Ϣ�޸�
	 */
	public static void testModifyVmBasic(){
		System.out.println("**********�����޸������������Ϣ*********");
		BasicConfig basicConfig = new BasicConfig();
		//������Ϣ
		basicConfig.setPae(0);
		basicConfig.setAcpi(0);
		basicConfig.setApic(0);
		basicConfig.setClock("utc");
		basicConfig.setBlkiotune(300);
		basicConfig.setStartPriority(0);
		basicConfig.setAcpi(0);
		basicConfig.setAutoMigrate(0);
		basicConfig.setDesc("test rest modify domain");

		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(115L);
		rsDFM.setName("test-01");
		rsDFM.setBasicConfig(basicConfig);
		CasRestImpl.editVm(rsDFM);
	}

	/**
	 * IF_3
	 * �������������-CPU�޸�
	 */
	public static void testModifyVmCpu() {
		System.out.println("**********�����޸�CPU����*********");
		//cpu
		CpuConfig cpu = new CpuConfig();
		cpu.setCpuCores(2);
		cpu.setCpuSockets(2);
		
		cpu.setCpuShares(256);
		BindPhysicalCpu bcpu = new BindPhysicalCpu();
		Integer[] i = {0,1};
		bcpu.setPcpu(i);
		bcpu.setVcpu(0);
		BindPhysicalCpu bcpu2 = new BindPhysicalCpu();
		Integer[] j = {0};
		bcpu2.setPcpu(j);
		bcpu2.setVcpu(1);
		List<BindPhysicalCpu> bindcpuList = new ArrayList<BindPhysicalCpu>();
		bindcpuList.add(bcpu);
		bindcpuList.add(bcpu2);
		cpu.setBindcpuList(bindcpuList);
		
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(5083L);
		rsDFM.setName("rest_add1441963418769");
		rsDFM.setCpuConfig(cpu);
		CasRestImpl.modifyCpuConfig(rsDFM);
	}

	/**
	 * IF_3
	 * �������������-�ڴ��޸�
	 */
	public static void testModifyVmMomory(){
		System.out.println("**********�����޸��ڴ�����*********");
		MemoryConfig memory = new MemoryConfig();
		memory.setSize(2048L);
		memory.setMemoryBacking(10);
		
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(115L);
		rsDFM.setName("test-01");
		rsDFM.setMemory(memory);
		CasRestImpl.editVm(rsDFM);
	}

	/**
	 * IF_3
	 * �������������-���������޸�
	 * @param rsclient 
	 */
	public static void testModifyVmNetwork() {
		System.out.println("**********�����޸���������*********");
		NetworkConfig network = new NetworkConfig();
		network.setMac("0c:da:41:1d:81:a1");
		network.setNewMac("0c:da:41:1d:81:a2");
		network.setIpAddr("192.168.200.15");
		network.setVsId(7L);
		network.setVsName("vswitch0");
		network.setDeviceModel("e1000");
		network.setMode("veb");
		network.setProfileId(1L);
		network.setLimitInBound(false);
		network.setLimitOutBound(false);
		network.setVirtualport(false);

		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(115L);
		rsDFM.setName("test-01");
		rsDFM.setNetwork(network);
		CasRestImpl.editVm(rsDFM);
	}

	/**
	 * IF_3
	 * �������������-Ӳ���޸�
	 */
	public static void testModifyVmDisk(){
		System.out.println("**********�����޸������Ӳ����Ϣ*********");
		StorageConfig storage = new StorageConfig();
		storage.setDevice("vda");
		storage.setFormat("qcow2");
		storage.setSize(1024L);
		storage.setCacheType("writeback");
		
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(5082L);
		rsDFM.setName("s");
		rsDFM.setStorage(storage);
		CasRestImpl.editVm(rsDFM);
		
	}
	
	/**
	 * IF_4
	 * ��������VLAN
	 */
	public void testVlanVm(){
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(33L);
		rsDFM.setName("test-01");

		//network
		NetworkConfig network = new NetworkConfig();
		network.setMac("0c:da:41:1d:61:39");
		network.setNewMac("0c:da:41:1d:61:36");
		network.setIpAddr("192.168.0.69");
		network.setVsId(1L);
		network.setVsName("vswitch0");
		network.setDeviceModel("e1000");
		network.setMode("veb");
		network.setVsiMngId("1080::8:800:200C:417A");
		network.setVsiTypeId("222");
		network.setVsiTypeVer("121");
		network.setVsiIdFormat("UUID");
		network.setProfileId(1L);
		network.setLimitInBound(false);
		network.setLimitOutBound(false);
		network.setVirtualport(false);
		rsDFM.setNetwork(network);
		CasRestImpl.editVmNet(rsDFM);
		
	}

	/**
	 * IF_5  ��ָ���洢��������ָ���洢��
	 * @param hostId  ָ���洢�����ڵ����� ID
	 * @param spName �洢������
	 * @param volName �洢������
	 * @param capacity �洢����������
	 * @param format  �洢��ĸ�ʽ��֧��raw��qcow2��ʽ
	 * @param baseFile  ���������ļ�������·��������ѡ
	 */
	public static void testAddVolume(Long hostId, String spName, String volName, Long capacity, String format, String baseFile) {
		RsVolume rsVolume = new RsVolume();
		rsVolume.setHostId(hostId);
		rsVolume.setSpName(spName);
		rsVolume.setVolName(volName);
		rsVolume.setCapacity(capacity);
		rsVolume.setFormat(format);
		rsVolume.setBaseFile(baseFile);
		System.out.println("��ʼ��������Ӳ��");
		CasRestImpl.addVol(rsVolume);
		System.out.println("�����ɹ���");
	}

	/**
	 * IF_7 ��ѯCASϵͳ��������ָ���洢���µĴ洢���б�
	 * @param hostId ����id
	 * @param poolName ����������
	 * @param offset ��ҳ��ѯ���ƫ��ֵ
	 * @param pageSize ��ҳ��ѯ�������
	 */
	public static List<RsStorageVolume> testGetVmDiskInfo(Long hostId, String poolName, Integer offset, Integer pageSize){
		System.out.println("��ѯCAS�д洢�����£�");
		List<RsStoragePool> rsStoragePoolList = CasRestImpl.queryStoragePool(hostId);
		if (rsStoragePoolList != null && rsStoragePoolList.size() > 0) {
			for (RsStoragePool rsStoragePool : rsStoragePoolList) {
				System.out.println(rsStoragePool.getName());				
			}
		}
		System.out.println("��ѯCAS�д洢�����£�");
		List<RsStorageVolume> rsVolumeList = CasRestImpl.getVolInfo(hostId, poolName, null, offset, pageSize);
		if (rsVolumeList != null && rsVolumeList.size() > 0) {
			for (RsStorageVolume rsStorageVolume : rsVolumeList) {
				System.out.println("�洢��" + rsStorageVolume.getName());				
			}
		}
		return rsVolumeList;
	}
	
	/**
	 * IF_9
	 * ɾ������Ӳ�̲���
	 */
	public static void delDeviceTest(){
		System.out.println("*****ɾ������Ӳ�̲���*****");
		CasRestImpl.delVol(5l, "defaultpool", "t5");
	}

	/**
	 * IF_10
	 * ��ѯָ���������ϸ��Ϣ
	 */
	public static void testQueryVmInfo(){
		// ��ѯCAS�����������ӿ�
		Long vmId = 115L;
		RsDomain rsDomain = CasRestImpl.getVmInfo(vmId);
		System.out.println("��ѯָ���������ϸ��Ϣ�ɹ�:" + rsDomain.getName());
	}

	/**
	 * IF_11
	 * ���������
	 */
	public static void runVmTest(){
		System.out.println("*****�������������*****");
		RsTaskMsg task = CasRestImpl.runVm(5081l, "rest_add1441940375038");
		System.out.println(task.getResult() == 0 ? "����������ɹ���" : "���������ʧ��,ʧ��ԭ��" + task.getFailMsg());
	}
	
	/**
	 * IF_11
	 * ��ͣ�����
	 */
	public static void pauseVmTest(){
		System.out.println("*****��ͣ���������*****");
		CasRestImpl.pauseVm(115l, "rest_add1");
	}
	
	/**
	 * IF_11
	 * �ָ������
	 */
	public static void restoreVmTest(){
		System.out.println("*****�ָ����������*****");
		CasRestImpl.restoreVm(115l, "rest_add1");
	}
	
	/**
	 * IF_11
	 * ���������
	 */
	public static void sleepVmTest(){
		System.out.println("*****�������������*****");
		CasRestImpl.sleepVm(115l, "rest_add1");
	}
	
	/**
	 * IF_11
	 * ���������
	 */
	public static void restartVmTest(){
		System.out.println("****�������������*****");
		CasRestImpl.restartVm(115l, "rest_add1");
	}
	
	/**
	 * IF_11
	 * �����ǿ�ƹرյ�Դ
	 */
	public static void powerOffVmTest(){
		System.out.println("*****�ر��������Դ����*****");
		RsTaskMsg task = CasRestImpl.powerOffVm(5081l, "rest_add1441940375038");
		System.out.println(task.getResult() == 0 ? "�ر�������ɹ���" : "�ر������ʧ��,ʧ��ԭ��" + task.getFailMsg());
	}
	
	/**
	 * IF_11
	 * �ر������
	 */
	public static void closeVmTest(){
		System.out.println("*****�ر����������*****");
		CasRestImpl.closeVm(115l, "rest_add1");
	}
	
	/**
	 * IF_12
	 * ��¡���������
	 */
	public static void cloneVmTest(){
		System.out.println("*****��¡���������*****");
		Storage storage=new Storage();
		storage.setSrc("RsBatchDeploy4");
		storage.setDest("rest_add1-clone5");
		storage.setPool("defaultpool");
		List<Storage> storageList=new ArrayList<Storage>();
		storageList.add(storage);
		Network network=new Network();
		network.setKey("0c:da:41:1d:81:a2");
		network.setValue("192.168.0.88");
		List<Network> networkList=new ArrayList<Network>();
		networkList.add(network);
		RsDomainVmClone params=new RsDomainVmClone();
		params.setId(115l);
		params.setTargetHostId(6l);
		params.setTitle("rest_add1-clone5");
		params.setDomainName("rest_add1-clone5");
		params.setCloneMode(VmEnum.CAS_VM_CLONEMODE_NORMAL);
		params.setCloneType(VmEnum.CAS_VM_CLONETYPE_LOCAL);
		params.setDiskFormat(VmEnum.CAS_VM_DISKFORMAT_QCOW2);
		params.setNetworks(networkList);
		params.setStorages(storageList);
		
		RsTaskMsg msg = CasRestImpl.cloneVM(params);
		if(msg!=null){
			System.out.println("��¡�����" + (msg.getResult() == 0 ? "�ɹ�" : "ʧ��"));
		}
	}

	/**
	 * IF_13/CT-69
	 * ���������ģ��
	 * @param vmId ����id
	 * @param templateName ģ������
	 * @param opType ������ʽ�� 1��¡��ģ�壬 2 ת�Ƴ�ģ��
	 * @param desc ģ������
	 */
	public static void creareTemplate(Long vmId, String templateName, String opType, String desc) {
		if (vmId == null) {
			System.out.println("���������ģ���������ģ��ID����Ϊnull");
			return;
		}
		
		if (templateName == null) {
			System.out.println("���������ģ���������ģ�����Ʋ���Ϊnull");
			return;
		}
		
		if (opType == null) {
			System.out.println("���������ģ���������ģ�崴����ʽ����Ϊnull");
			return;
		} else {
			if (!"1".equals(opType) && !"2".equals(opType)) {
				System.out.println("���������ģ���������ģ�崴����ʽ: 1��¡��ģ�壬 2 ת�Ƴ�ģ�塣");
			}
		}

		System.out.println("*****���������ģ�����*****");
		if (desc == null) {
			desc = "";
		}
		if ("1".equals(opType)) {
			CasRestImpl.cloneToVmTmp(vmId, templateName, desc);			
		} else {
			CasRestImpl.convertToVmTmp(vmId, templateName, desc);
		}
		System.out.println("*****���������ģ��ɹ�*****");
	}

	/**
	 * IF_14/CT-67
	 * ��ѯģ���б���Ϣ
	 * @param offset ��ѯ��ʼλ��
	 * @param limit ��ѯ�ļ�¼��
	 */
	public static void testGetTemplatesInfoList(Integer offset, Integer limit) {
		System.out.println("*****��ѯģ���б���Ϣ*****");
		if (offset == null || limit == null) {
			System.out.println("��ѯ�����ģ��������󣬲�ѯ��ʼλ�úͲ�ѯ��¼������Ϊnull��");
			return;
		}
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
					System.out.println(sb.toString());
				}
			}
		}
	}
	
	/**
	 * IF_14/CT-68
	 * ��ѯģ����Ϣ
	 */
	public static void testGetTemplatesInfo() {
		Long tmpId = 5122L;
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
		System.out.println(sb.toString());
	}
	
	/**
	 * IF_15/CT-65
	 * ɾ�������ģ��
	 * @param templateId �����ģ���ID
	 */
	public static void deleteTemplate(Long templateId) {
		System.out.println("*****ɾ�������ģ�����*****");
		if (templateId == null) {
			System.out.println("ɾ�������ģ���������ģ��ID����Ϊnull��");
			return;
		}
		
		CasRestImpl.delVmTmp(templateId);
		System.out.println("*****ɾ�������ģ��ɹ�*****");
	}
	
	/**
	 * IF_16
	 * �������������
	 * @param templateId ģ��id
	 * @param storeName �洢����
	 * @param showName �������ʾ����
	 * @param targetHostId Ŀ������id
	 */
	public static void batchDeployTemplate(Long templateId, String storeName, String showName, Long targetHostId) {
		System.out.println("*****�����������������*****");
		List<RsDomainDeploy> data = new ArrayList<RsDomainDeploy>();
		RsCommList<RsDomainDeploy> configs = new RsCommList<RsDomainDeploy>(data);
		for (int i = 1; i < 5; i++) {
			RsDomainDeploy config1 = new RsDomainDeploy();
			RsStore store1 = new RsStore();
			store1.setSrc(storeName);
			store1.setPool("defaultpool");
			store1.setDest("RsBatchDeploy" + i);
			List<RsStore> storages1 = new ArrayList<RsStore>();
			storages1.add(store1);
			config1.setId(templateId);
			config1.setTargetHostId(targetHostId);
			config1.setDomainName("batchDeploy_10_" + i);
			config1.setTitle(showName + i);
			config1.setDeployMode(0); //0������������1��ʾ���ٲ���
			config1.setDesc("batchDeploy1:0������������");
			config1.setStorages(storages1);
			RsDomainSwitch keyValue1 = new RsDomainSwitch();
			keyValue1.setMac("0c:da:41:1d:79:44");
			keyValue1.setvSwitchName("test");
			keyValue1.setProfileName("Default");
			keyValue1.setSysIpType(1);
			keyValue1.setIsBindIp(false);
			List<RsDomainSwitch> networks1 = new ArrayList<RsDomainSwitch>();
			networks1.add(keyValue1);
			config1.setNetworks(networks1);
			configs.getData().add(config1);
		}
		CasRestImpl.batchDeploy(configs);
	}

	/**
	 * �������ţ�IF_18 <br/>
	 * ���ܼ�� ����
	 */
	public static void testPerformanceMonitor() {
		System.out.println("****************�������ܼ��*************");
		Long hostId = null;
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		if (hostList != null && hostList.size() > 0) {
			hostId = hostList.get(0).getId();
		}
		if (hostId != null) {
			List<VmCpuRate> cpulist = CasRestImpl.queryVmCpuRate(hostId);
			if (cpulist != null) {
				System.out.println("****************CPU*************");
				for (VmCpuRate cpu : cpulist){			
					System.out.println(cpu);
				}
			}

			System.out.println("****************�ڴ�*************");
			List<VmMemoryRate> memlist = CasRestImpl.queryVmMemRate(hostId);
			if (memlist != null) {
				for (VmMemoryRate mem : memlist){			
					System.out.println(mem);
				}
			}
		}
	}

	/**
	 * �������ţ�IF_19 <br/>
	 * ���ϸ澯 ����
	 */
	public static void testWarn() {
		System.out.println("********���ϸ澯����********");

		List<RsEvent> evList = CasRestImpl.queryWarnInfo(0, 20, null, null);
		if (evList != null && evList.size() > 0) {
			System.out.println("********��ȡ��һҳ�澯��Ϣ����********");
			for (RsEvent ev : evList) {
				System.out.println(ev);
			}
		}
	}
	
	/**
	 * �������ţ�IF_20 <br/>
	 * CPU��Դ������  ����
	 */
	public static void testVmCpuBind() {
		System.out.println("****************�����޸�CPU��*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		RsDomain vm = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = vmList.get(0);
			}
		}
		if (vm != null) {
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(vm.getId());
			CpuConfig cpuConfig = new CpuConfig();
			cpuConfig.setCpuCores(2);
			cpuConfig.setCpuSockets(2);
			cpuConfig.setCpuMode("custom");
			cpuConfig.setCpuShares(512);
			
			List<BindPhysicalCpu> bindcpuList = new ArrayList<BindPhysicalCpu>();
			BindPhysicalCpu bind = new BindPhysicalCpu();
			bind.setVcpu(0);
			Integer[] pc = {0,2};
			bind.setPcpu(pc);
			bindcpuList.add(bind);
			cpuConfig.setBindcpuList(bindcpuList);
			
			rsDomain.setCpuConfig(cpuConfig );
			CasRestImpl.modifyCpuConfig(rsDomain);
		}
	}

	/**
	 * �������ţ�IF_22 <br/>
	 * �ڴ���Դר������  ����
	 */
	public static void testVmMemoryShared() {
		System.out.println("****************����������ڴ湲������*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		Long vmId = null;
		RsDomain vm = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = vmList.get(0);
				vmId = vm.getId();
			}
		}
		if (vmId != null) {
			CasRestImpl.setVmMemoryShared(vmId, vm.getMemory());
		}
	}

	/**
	 * �������ţ�IF_23 <br/>
	 * �����ڴ���ԴԤ��  ����
	 */
	public static void testVmMemoryBacking() {
		System.out.println("****************����������ڴ�Ԥ������*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		RsDomain vm = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = vmList.get(0);
			}
		}
		if (vm != null) {
			CasRestImpl.setVmMemoryBacking(vm.getId(), vm.getMemory(), 10);
		}
	}

	/**
	 * �������ţ�IF_24 <br/>
	 * ������Դ���ȼ�����  ����
	 */
	public static void testVmProrioty() {
		System.out.println("****************���������������Դ���ȼ�����*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		Long vmId = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				RsDomain vm = vmList.get(0);
				vmId = vm.getId();
			}
		}
		if (vmId != null) {
			CasRestImpl.setVmPriority(vmId, 300, 1);
		}
	}

	/**
	 * �������ţ�IF_25 <br/>
	 * ����CPU�������� ����
	 */
	public static void testVmCpuAdd() {
		System.out.println("****************���������CPU��������*************");
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		Long vmId = null;
		RsDomain vm = null;
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = vmList.get(0);
				vmId = vm.getId();
			}
		}
		if (vmId != null) {
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(vmId);
			CpuConfig cpuConfig = new CpuConfig();
			cpuConfig.setCpuCores(vm.getCpu() + 1);
			cpuConfig.setCpuSockets(1);
			cpuConfig.setCpuShares(512);
			cpuConfig.setBindcpuList(vm.getBindcpuList());
			
			rsDomain.setCpuConfig(cpuConfig);
			CasRestImpl.modifyCpuConfig(rsDomain);
		}
	}
	
	/**
	 * IF_29<br/>
	 * ������������ ����
	 */
	public static void testBandwidthOneWayLimit() {
		System.out.println("****************���� ������������:����������*************");
		RsDomain vm = null;
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = CasRestImpl.getVmInfo(vmList.get(0).getId());
			}
		}
		CasRestImpl.bandwidthOneWayLimit(vm, 512L, 1024L);
	}
	
	/**
	 * IF_30<br/>
	 * �������˫����� ����
	 */
	public static void testBandwidthTwoWayLimit() {
		System.out.println("****************���� �������˫�����*************");
		RsDomain vm = null;
		List<RsHost> hostList = CasRestImpl.queryAllHost();
		if (hostList != null && hostList.size() > 0) {
			Long hostId = hostList.get(0).getId();
			List<RsDomain>  vmList = CasRestImpl.queryAllVm(hostId);
			if (vmList != null && vmList.size() > 0) {
				vm = CasRestImpl.getVmInfo(vmList.get(0).getId());
			}
		}
		CasRestImpl.bandwidthTwoWayLimit(vm, 512L, 1024L, 2048L, 5000L);
	}
	
	/**
	 * IF_31 ���������Ǩ��
	 * @param id ��ҪǨ�Ƶ������ ID
	 * @param migrateType Ǩ�����ͣ�ö��:0��ʾֻǨ��������1��ʾֻǨ�ƴ洢��2��ʾǨ�������ʹ洢
	 * @param domainName ���������
	 * @param targetHostId Ŀ������id����ҪǨ������ʱ����
	 */
	public static void migrateVmHost(Long id, Integer migrateType, String domainName, Long targetHostId){
		if (DomainForMigrate.ONLY_HOST == migrateType) {
			System.out.println("��ʼǨ��������");
		} else if (DomainForMigrate.ONLY_STORE == migrateType){
			System.out.println("��ʼǨ�ƴ洢");
		} else if (DomainForMigrate.HOST_STORE == migrateType) {
			System.out.println("��ʼǨ�������ʹ洢");
		}
		DomainForMigrate domainForMigrate = new DomainForMigrate();
		domainForMigrate.setId(id);
		domainForMigrate.setMigrateType(migrateType);
		domainForMigrate.setDomainName(domainName);
		domainForMigrate.setTargetHostId(targetHostId);
		RsTaskMsg rs = CasRestImpl.vmMigrate(domainForMigrate);
		if (rs != null) {
			System.out.println("Ǩ�Ƴɹ���" + rs.getMsgId());
		} else {
			System.out.println("Ǩ��ʧ�ܣ�");
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
	public static void migrateVmHostAndStorage(Long id,Integer migrateType,String domainName,String targetPool,String targetPath,Long targetHostId){
		if (DomainForMigrate.ONLY_HOST == migrateType) {
			System.out.println("��ʼǨ��������");
		} else if (DomainForMigrate.ONLY_STORE == migrateType){
			System.out.println("��ʼǨ�ƴ洢");
		} else if (DomainForMigrate.HOST_STORE == migrateType) {
			System.out.println("��ʼǨ�������ʹ洢");
		}
		DomainForMigrate domainForMigrate = new DomainForMigrate();
		domainForMigrate.setId(id);
		domainForMigrate.setMigrateType(DomainForMigrate.HOST_STORE);
		domainForMigrate.setDomainName(domainName);
		domainForMigrate.setTargetPool(targetPool);
		domainForMigrate.setTargetPath(targetPath);
		domainForMigrate.setTargetHostId(targetHostId);
		RsTaskMsg rs = CasRestImpl.vmMigrate(domainForMigrate);
		if (rs != null) {
			System.out.println("Ǩ�Ƴɹ���" + rs.getMsgId());
		} else {
			System.out.println("Ǩ��ʧ�ܣ�");
		}
	}
	
	/**
	 * IF_33/CT-57
	 * ������������ղ���
	 */
	public static void createVMSnapshotTest(){
		System.out.println("***********������������ղ���************");
		RsSnapshot rs=new RsSnapshot();
		rs.setVmId(5082l);
		rs.setName("CreateSnapshotTest_04");
		rs.setDesc("test4 snapshot");
		CasRestImpl.createVMSnapshot(rs);
	}
	
	/**
	 * IF_33/CT-58
	 * ɾ����������ղ���
	 */
	public static void deleteVMSnapshotTest(){
		System.out.println("***********ɾ����������ղ���************");
		RsSnapshot rs=new RsSnapshot();
		rs.setVmId(5082l);
		rs.setName("CreateSnapshotTest_03");
		CasRestImpl.deleteVMSnapshot(rs);
	}
	
	/**
	 * CT-59
	 * �޸����������
	 */
	public static void testModifySnapshot() {
		System.out.println("***********�޸����������begin************");
		RsTaskMsg task = CasRestImpl.modifyVmSnapshot(5082L, "createsnapshot", "bbb", "sss");
		if (task != null) {
			System.out.println("�޸����������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
		}
		System.out.println("***********�޸����������end************");
	}
	
	/**
	 * CT-60
	 * �������������
	 */
	public static void testBackupVmSnapshot() {
		System.out.println("***********�������������begin************");
		RsSnapshot snapshot = new RsSnapshot();
		snapshot.setVmId(5082L);
		snapshot.setName("kaka");
		RsTaskMsg task = CasRestImpl.backupVmSnapshot(snapshot);
		if (task != null) {
			System.out.println("�������������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
		}
		System.out.println("***********�������������end************");
	}
	
	/**
	 * IF_33/CT-61
	 * ��ԭ��������ղ���
	 */
	public static void resumeVMSnapshotTest(){
		System.out.println("***********��ԭ��������ղ���************");
		RsSnapshot rs=new RsSnapshot();
		rs.setVmId(5082L);
		rs.setName("bbb");
		CasRestImpl.resumeVMSnapshot(rs);
	}
	
	/**
	 * IF_33/CT-62
	 * ��ѯָ�������������Ϣ����
	 */
	public static void loadVMSnapshotTest(){
		System.out.println("***********��ѯָ������������б�************");
		Long vmId = 5082L;
		List<RsSnapshot> result = CasRestImpl.loadVMSnapshot(vmId);
		if (result != null) {
			for (RsSnapshot snap : result) {
				printSnapshot(snap);
			}
		}
	}
	
	private static void printSnapshot(RsSnapshot snap) {
		System.out.println("�������ƣ�" + snap.getName() + "����:" + snap.getDesc());
		if (snap.getSnapshots() != null && snap.getSnapshots().getData() != null) {
			for (RsSnapshot child : snap.getSnapshots().getData()) {
				printSnapshot(child);
			}
		}
	}
	
	/**
	 * ���ر��������
	 * @param vmId �����id
	 * @param directory ����Ŀ¼
	 * @param backupType �������͡�0��ȫ�����ݣ�1���������� ��2�����챸��
	 */
	public static void backupDomainLocal(Long vmId, String directory, Integer backupType) {
		System.out.println("***********���ر��������************");
		RsDomainForBackUp backPrama = new RsDomainForBackUp();
		backPrama.setId(vmId);
		backPrama.setBackupType(backupType);			//0��ȫ�����ݣ�1���������� ��2�����챸��
		backPrama.setDirectory(directory);
		backPrama.setStoreMode(0);						//0����������Ŀ¼; 1��Զ�˷�����
		RsTaskMsg task = CasRestImpl.backupVm(backPrama);
		System.out.println("���������" + (task.getResult() == 0 ? "�ɹ�" : "ʧ��"));
	}
	
	/**
	 * ���ݵ�Զ������
	 * @param vmId �����id
	 * @param directory Զ�������ı���Ŀ¼
	 * @param backupType �������͡�1���������� ��2�����챸��
	 * @param targetAddr Զ��������ַ
	 * @param userName �û���
	 * @param password ����
	 * @param type ���ӷ�ʽ��0��ftp��ʽ��1��scp��ʽ 
	 */
	public static void backupDomainRemote(Long vmId, String directory, Integer backupType, String targetAddr, 
			String userName, String password, Integer type) {
		System.out.println("***********���ݵ�Զ������************");
		RsDomainForBackUp backPrama = new RsDomainForBackUp();
		backPrama.setId(vmId);
		backPrama.setBackupType(backupType);			//0��ȫ�����ݣ�1���������� ��2�����챸��
		backPrama.setDirectory(directory);
		backPrama.setStoreMode(1);						//0����������Ŀ¼; 1��Զ�˷�����
		backPrama.setTargetAddr(targetAddr);
		backPrama.setUserName(userName);
		backPrama.setPassword(password);
		backPrama.setType(type);
		CasRestImpl.backupVm(backPrama);
	}
	
	
	/*********************************************���Ų���**************************************************/
	/**
	 * HA(����/����/����)
	 * @param rsclient
	 */
	public static void testEditHa() {
		System.out.println("***********HA(����/����/����)����************");
		RsCluster rsCluster = new RsCluster();
		rsCluster.setId(1L);
		rsCluster.setEnableHA(1);
		rsCluster.setPriority(2);
		rsCluster.setHaMinHost(1);
		RsTaskMsg task = CasRestImpl.editHa(rsCluster);
		if (task != null) {
			System.out.println("HA(����/����/����)" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
		}
	}
	
	public static void testQueryHostNodeByClusterId() {
		System.out.println("****************���Բ�ѯ��Ⱥ����������*************");
		List<RsHost> hostList = CasRestImpl.queryHostNodeByClusterId(1L);
		if (hostList != null && hostList.size() > 0) {
			for (RsHost rsHost : hostList) {
				System.out.println("����ID:"+rsHost.getId()+" | ������:"+rsHost.getName()+" | IP:"+rsHost.getIp());
			}
		} else {
			System.out.println("��Ⱥ��û��������");
		}
	}
	
	/**
	 * �ɹܵ�������ڵ�
	 * ��ȡĳ�����������Ļ�����Ϣ����������CPU���ڴ棩
	 */
	public static void testQueryHostBaseInfoById() {
		System.out.println("****************���Ը�������ID��ѯ����������Ϣ����������CPU���ڴ棩*************");
		RsHost rsHost = CasRestImpl.queryHostById(20L);
		if (rsHost != null) {
			System.out.println("����ID:"+rsHost.getId()+" | ������:"+rsHost.getName()
			+ " | CPU:" + rsHost.getCpuCount() + " | �ڴ�:" + rsHost.getMemorySize() + "MB");
		}
	}
	
	/**
	 * ��ѯ����ڵ������Ϣ
	 *��CPU/����/�ڴ�/������
	 */
	public static void testQueryHostInfoById() {
		System.out.println("****************���Ը�������ID��ѯ����������Ϣ��CPU/����/�ڴ�/������*************");
		RsHost rsHost = CasRestImpl.queryHostById(20L);
		if (rsHost != null) {
			StringBuffer str = new StringBuffer();
			str.append("����ID:"+rsHost.getId()+" | ������:"+rsHost.getName());
			str.append(" | CPU:" + rsHost.getCpuCount() + " | ����:" + rsHost.getDiskSize() + " MB" + " | �ڴ�:" + rsHost.getMemorySize() + " MB");
			
			if (rsHost.getPnicList() != null && rsHost.getPnicList().size() > 0) {
				str.append(" | ����:[");
				for (RsPhysicalNIC rsPhysicalNIC : rsHost.getPnicList()) {
					str.append(" {name:" + rsPhysicalNIC.getName() + ", macAddr:" + rsPhysicalNIC.getMacAddr() + "},");
				}
				str.append("]");
			}
			System.out.println(str.toString());
		}
	}
	
	/**
	 * ��ѯ����ڵ�״̬��Ϣ
     *����Դ/CPU/����/�ڴ�/����/����IO/����IO��
	 */
	public static void testQueryHostStatusInfoById() {
		System.out.println("****************���Բ�ѯ����ڵ�״̬��Ϣ����Դ/CPU/����/�ڴ�/����/����IO/����IO��*************");
		
		Long hostId = 20L;
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
			
			System.out.println(str.toString());
		}
	}
	
	/**
	 * ���ü���ڵ���������
	 */
	public static void testSetHostNetwork() {
		System.out.println("****************�������ü���ڵ��������ÿ�ʼ*************");
		RsVSwitch rsVSwitch = new RsVSwitch();
		rsVSwitch.setId(49L);
		rsVSwitch.setAddress("192.168.50.146");
    	rsVSwitch.setNetmask("255.255.255.0");
		CasRestImpl.setHostNetwork(rsVSwitch);
		System.out.println("****************�������ü���ڵ��������óɹ�*************");
	}
	
	public static void sshStatus() {
		Long id = 5073L;
		// TODO Auto-generated method stub
		RsResult rs = CasRestImpl.sshStatus(id);
		System.out.println(rs.getData());
	}
	
	/**
	 * CT-49
	 * ������������̿���
	 */
	public static void testSnapshotDomainDisk() {
		System.out.println("***********������������̿���begin************");
		RsSnapshot snapshot = new RsSnapshot();
		snapshot.setVmId(5082L);
		snapshot.setName("testVm");
		snapshot.setDevice("vda");
		RsTaskMsg task = CasRestImpl.snapshotDomainDisk(snapshot);
		if (task != null) {
			System.out.println("������������̿���" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
		}
		System.out.println("***********������������̿���end************");
	}
	
	/**
	 * CT-50
	 * ɾ����������̿���
	 */
	public static void testDelSnapshotDomainDisk(long hostId, String poolName, String volName) {
		System.out.println("***********ɾ����������̿���begin************");
		CasRestImpl.delSnapshotDomainDisk (hostId, poolName, volName);
		System.out.println("***********ɾ����������̿���end************");
	}
	
	/**
	 * CT-51
	 * ����������̿��մ�������
	 * 
	 */
	public static void testResumeSnapshotDomainDisk(long vmId, String devName, String snapName, String targetPool, String targetVol) {
		System.out.println("***********����������̿��մ�������begin************");
		CasRestImpl.resumeSnapshotDomainDisk(vmId, devName, snapName, targetPool, targetVol);
		System.out.println("***********����������̿��մ�������end************");
	}
	
	/**
	 * CT-52
	 * �������������-Ӳ���޸Ĵ�С
	 */
	public static void testModifyVmDiskSize(){
		System.out.println("**********�����޸������Ӳ����Ϣ*********");
		StorageConfig storage = new StorageConfig();
		storage.setDevice("vda");
		storage.setSize(2048L);
		
		RsDomainForModify rsDFM = new RsDomainForModify();
		rsDFM.setId(5082L);
		rsDFM.setStorage(storage);
		CasRestImpl.editVmDisk(rsDFM);
		
	}
	
	/**
	 * CT-53
	 * Ǩ�����������
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void testMigrateVolume () {
		System.out.println("***********Ǩ�����������begin************");
		List<Map> volumeList = new ArrayList<Map>();
		Map volInfo = new HashMap();
		volInfo.put("name", "kaka");
		volInfo.put("size", 1000d);
		volumeList.add(volInfo);
		RsMigrateVolume rsMigrateVolume = new RsMigrateVolume();
		rsMigrateVolume.setDestPoolName("defaultpool");
		rsMigrateVolume.setDestPoolNamePath("/vms/images");
		rsMigrateVolume.setHostId(18L);
		rsMigrateVolume.setSrcPoolName("wfgt29");
		rsMigrateVolume.setSrcStoragePoolPath("/vms/wfgt29");
		List<Vol> volList = new ArrayList<Vol>();
		if (volumeList != null) {
			for (Map mapVol : volumeList) {
				String name = (String)mapVol.get("name");
				Double size = (Double)mapVol.get("size");
				Vol vol = new Vol();
				vol.setName(name);
				vol.setSize(size.longValue());
				volList.add(vol);
			}
		}
		rsMigrateVolume.setVolumeList(volList);
		
		CasRestImpl.batchMigrateVolume(rsMigrateVolume);
		System.out.println("***********Ǩ�����������end************");
	}
	
	/**
	 * CT-54
	 * �����������
	 */
	public static void testCopyVolume (long hostId, String poolName, String volName, String targetPool, String targetVol) {
		System.out.println("***********�����������begin************");
		CasRestImpl.copyVolume(hostId, poolName, volName, targetPool, targetVol);
		System.out.println("***********�����������end************");
	}	
	
	/**
	 * CT-55
	 * ��ѯ������̻�����Ϣ
	 */
	public static void testGetVolInfo(Long hostId, String poolName, String volumeName) {
		List<RsStorageVolume> rsVolumeList = CasRestImpl.getVolBaseInfo(hostId, poolName, volumeName);
		if (rsVolumeList != null && rsVolumeList.size() > 0) {
			for (RsStorageVolume rsStorageVolume : rsVolumeList) {
				StringBuffer sb = new StringBuffer();
				sb.append("] �洢���ʶ:").append(rsStorageVolume.getName()).
				append("���洢���С��").append(rsStorageVolume.getSize() + "MB").
				append("���洢���ʽ��").append(rsStorageVolume.getFormat()).
				append("�������豸�ڵ㣺").append(rsStorageVolume.getUsers());
				System.out.println(sb.toString());
			}
		}
	}
	
	/**
	 * CT-56
	 * ��ѯ�������״̬��Ϣ
	 */
	public static void testGetVolStatusInfo(Long vmId, String devName) {
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
				System.out.println(sb.toString());
			}
		}
	}
	
	/**
	 * CT-63
	 * ��������̴���ģ��
	 */
	public static void testDiskTemplate (long hostId, String poolName, String volName, String targetPool, String targetVol) {
		System.out.println("***********��������̴���ģ��begin************");
		CasRestImpl.diskTemplate(hostId, poolName, volName, targetPool, targetVol);
		System.out.println("***********��������̴���ģ��end************");
	}
	
	/**
	 * CT-64
	 * ��������̿��մ���ģ��
	 */
	public static void testDiskSnapshotTemplate (long vmId, String devName, String snapName, String targetPool, String targetVol) {
		System.out.println("***********��������̿��մ���ģ��begin************");
		CasRestImpl.diskSnapshotTemplate(vmId, devName, snapName, targetPool, targetVol);
		System.out.println("***********��������̿��մ���ģ��end************");
	}
	
	/**
	 * CT-66
	 * �޸������ģ��
	 */
	public static void modifyVmTemplate() {
		System.out.println("***********�޸������ģ��begin************");
		RsDomain domain = new RsDomain();
		domain.setId(5122L);
		domain.setName("template");
		RsTaskMsg task = CasRestImpl.modifyVmTemplate(domain);
		if (task != null) {
			System.out.println("�޸������ģ��" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
		}
		System.out.println("***********�޸������ģ��end************");
		
	}
	
	
	
	
	/**
	 * ����ڵ�ά��ģʽ�����ã�
	 */
	public static void testIntoMaintainStatus() {
		System.out.println("***********����ڵ�ά��ģʽ�����ã�����************");
		RsHost rsHost = new RsHost();
		rsHost.setId(20L);
		// 0:ֱ�ӽ���ά��ģʽ��,1:����  2������  3�����ߺ�����	4:�洢���Ͻ���ά��ģʽ
		rsHost.setMaintainMode(0);
		RsTaskMsg task = CasRestImpl.intoMaintainStatus(rsHost);
		if (task != null) {
			System.out.println("����ڵ�ά��ģʽ�����ã�" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
		}
	}
	
	/**
	 * ����ڵ�ά��ģʽ������/�˳���
	 */
	public static void testExitMaintainStatus() {
		System.out.println("***********����ڵ�ά��ģʽ���˳�������************");
		RsHost rsHost = new RsHost();
		rsHost.setId(20L);
		RsTaskMsg task = CasRestImpl.exitMaintainStatus(rsHost);
		if (task != null) {
			System.out.println("����ڵ�ά��ģʽ���˳���" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
		}
	}
	
	/**
	 * ����ڵ��������
	 */
	public static void testHostServiceStatus() {
		System.out.println("****************���Լ���ڵ��������*************");
		List<Long> hostIds = new ArrayList<Long>();
		Long hostId = 20L;
		hostIds.add(hostId);
		hostId = 18L;
		hostIds.add(hostId);
		
		List<RsHost> rsHostList = CasRestImpl.queryHostListByIds(hostIds);
		if (rsHostList != null) {
			for (RsHost rsHost : rsHostList) {
				StringBuffer str = new StringBuffer();
				str.append("����ID:"+rsHost.getId()+" | ������:"+rsHost.getName());
				str.append(" | �������:" + (rsHost.getStatus() == 1 ? "����" : "�쳣"));
				System.out.println(str.toString());
			}
		}
	}

	
	public static void testQueryMacPortTrafficData() {
		// TODO Auto-generated method stub
		Long id = 5073L;
		String mac = "0c:da:41:1d:6d:d1";
		RsVPortTrafficInfo result = CasRestImpl.getMacPortTrafficData(id, mac);
		String objXml = RestUtil.convertObjectToXml(result, RsVPortTrafficInfo.class);
		System.out.println(objXml);
	}
	
	/**
	 * IF_1
	 * ���Դ��������
	 */
	public static void testAddVM(){
		System.out.println("**********���Դ��������*********");
		RsTaskMsg task = CasRestImpl.createVm(getRsDomain());
		System.out.println(task.getResult() == 0 ? "����������ɹ�" : "���������ʧ��" + task.getFailMsg());
	}
	
	private static RsDomain getRsDomain() {
		String name = "rest_add" + System.currentTimeMillis();
		
		RsDomain rsDomain = new RsDomain();
    	rsDomain.setName(name);
    	rsDomain.setTitle(name);
    	rsDomain.setDescription("test create domain from rest api");
    	rsDomain.setHostId(20L);
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
    	rsDomain.setUuid("");
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
		rsDomainNetwork.setVsId(51L);
		rsDomainNetwork.setProfileId(1L);
		rsDomainNetwork.setDeviceModel("rtl8139");
		rsDomainNetwork.setKernelAccelerated(0);
		rsListNetWork.add(rsDomainNetwork);
		rsDomain.setNetworks(rsListNetWork);
    	
    	List<RsDomainStorage> rsListStorage = new ArrayList<RsDomainStorage>();
    	RsDomainStorage rsDomainStorage = new RsDomainStorage();
		rsDomainStorage.setStoreFile("/vms/isos/test");
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
	 * ���Ե���ģ��
	 */
	public static void testCompressTemplate() {
		System.out.println("***********����ģ��begin************");
		String vmTemplateName = "vxlan";
		System.out.println("���ص�url��" + CasRestImpl.getCompressTemplateUrl(vmTemplateName));
		
		System.out.println("***********����ģ��end************");
	}
	
	public static void testDelVswitch() {
		System.out.println("***********ɾ�����⽻����begin************");
		Long vsId = 17L;
		CasRestImpl.delVswitch(vsId);
		System.out.println("***********ɾ�����⽻����end************");
	}
	
	public static void queryVswitch() {
		System.out.println("***********��ѯ���⽻����begin************");
		Long vsId = 14L;
		RsVSwitch result = CasRestImpl.queryVswitch(vsId);
		System.out.println("name : " + result.getName());
		System.out.println("***********��ѯ���⽻����end************");
	}
	
	public static void addVswitch() {
		System.out.println("***********�������⽻����begin************");
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
		System.out.println("vsId:" + vsId);
		System.out.println("***********�������⽻����end************");
	}
	
	public static void modfiyVswitch() {
		System.out.println("***********�޸����⽻����begin************");
		RsVSwitch rs = new RsVSwitch();
		rs.setId(18L);
		rs.setHostId(2L);
		rs.setHostName("server14");
		rs.setName("sc");
		rs.setDescription("desdddd");
		rs.setPortNum(32);
		rs.setMode(0);
		rs.setPnic("eth3");
		rs.setAddress("55.5.5.5");
		rs.setNetmask("255.255.255.0");
		rs.setEnableLacp(false);
		CasRestImpl.modifyVswitch(rs);
		System.out.println("***********�޸����⽻����end************");
	}
	
	public static void queryNetwork() {
		System.out.println("***********��ѯ�������begin************");
		Long vmId = 49L;
		List<RsDomainNetwork> list = CasRestImpl.queryNetwork(vmId);
		for (RsDomainNetwork rs : list) {
			System.out.println("network's mac :" + rs.getMac() );
		}
		System.out.println("***********��ѯ�������end************");
	}
	
	public static void modfiyNetwork() {
		System.out.println("***********�޸��������begin************");
		NetworkConfig net = new NetworkConfig();
		net.setNetType(0);
		net.setMac("0c:da:41:1d:86:51");
		net.setIpAddr("5.5.5.55");
		net.setVsId(9L);
		net.setVsName("vswitch0");
		net.setDeviceModel("virtio");
		net.setKernelAccelerated(0);
		net.setMode("veb");
		net.setProfileId(1L);
		net.setNewMac("0c:da:41:1d:86:52");
		RsDomainForModify rsDomain = new RsDomainForModify();
		rsDomain.setId(49L);
		rsDomain.setName("aa");
		rsDomain.setNetwork(net);
		CasRestImpl.modifyNetwork(rsDomain);
		System.out.println("***********�޸��������end************");
	}
	
	public static void addNetwork() {
		System.out.println("***********�����������begin************");
		NetworkConfig net = new NetworkConfig();
		net.setVsId(19L);
		net.setVsName("vs_sc");
		net.setDeviceModel("rtl8139");
		net.setKernelAccelerated(1);
		net.setMode("veb");
		net.setProfileId(1L);
		net.setProfileName("Default");
		RsDomainForModify rsDomain = new RsDomainForModify();
		rsDomain.setId(49L);
		rsDomain.setName("aa");
		rsDomain.setNetwork(net);
		RsResult rr = CasRestImpl.addNetwork(rsDomain);
		System.out.println(rr.getMessage());
		System.out.println("***********�����������end************");
	}
	
	public static void deployTemplate() {
		System.out.println("***********ģ�岿�������begin************");
		RsDomainDeploy rs = new RsDomainDeploy();
		List<RsStore> storages = new ArrayList<RsStore>();
		List<RsDomainSwitch> networks = new ArrayList<RsDomainSwitch>();
		rs.setStorages(storages);
		rs.setNetworks(networks);
		rs.setId(113L);
		rs.setDomainName("test_api");
		rs.setTargetHostId(3L);
		rs.setDeployMode(1);
		rs.setProtectMode(0);
		rs.setVmNum(0);
		rs.setDeployType(0);
		rs.setTitle("show_name");
		RsStore store = new RsStore();
		store.setSrc("sc_test");
		store.setDest("test_api");
		store.setPool("defaultpool");
		storages.add(store);
		RsDomainSwitch switch1 = new RsDomainSwitch();
		switch1.setMac("0c:da:41:1d:86:52");
		switch1.setvSwitchName("vswitch0");
		switch1.setProfileName("Default");
		switch1.setSysIpType(1);
		switch1.setIsBindIp(false);
		networks.add(switch1);
		RsTaskMsg result = CasRestImpl.deployTemplate(rs);
		System.out.println(result.getMsgId());
		System.out.println("***********ģ�岿�������end************");
	}
	
	public static void addProfile() {
		System.out.println("***********����������ģ��begin************");
		PortProfile profile = new PortProfile();
		profile.setName("scttt");
		profile.setDescription("s");
		profile.setEnableVlan(0);
		profile.setVlanId(1);
		profile.setInbound(0);
		profile.setOutbound(0);
		profile.setEnableVsi(0);
		CasRestImpl.addProfile(profile);
		System.out.println("***********����������ģ��end************");
	}
	
	public static void modifyProfile() {
		System.out.println("***********�޸��������ģ��begin************");
		PortProfile profile = new PortProfile();
		profile.setId(12L);
		profile.setName("scttt");
		profile.setDescription("dddddd");
		profile.setEnableVlan(0);
		profile.setVlanId(1);
		profile.setInbound(0);
		profile.setOutbound(0);
		profile.setEnableVsi(0);
		CasRestImpl.modifyProfile(profile);
		System.out.println("***********�޸��������ģ��end************");
	}
	
	public static void queryProfile() {
		System.out.println("***********��ѯ�������ģ��begin************");
		Long profileId = 12L;
		PortProfile result = CasRestImpl.queryProfile(profileId);
		System.out.println(result.getName());
		System.out.println("***********��ѯ�������ģ��end************");
	}
	
	public static void deleteProfile() {
		System.out.println("***********ɾ���������ģ��begin************");
		Long profileId = 12L;
		String profileName = "scttt";
		CasRestImpl.deleteProfile(profileId, profileName);
		
		System.out.println("***********ɾ���������ģ��end************");

	}
	
	public static void deleteDomainNetwork() {
		System.out.println("***********ɾ����������begin************");
		RsDomainForModify domain = new RsDomainForModify();
		domain.setId(49L);
		domain.setName("aa");
		domain.setTitle("��1");
		domain.setDiskFlow(false);
		NetworkConfig network = new NetworkConfig();
		network.setMac("0c:da:41:1d:0a:59");
		domain.setNetwork(network);
		
		CasRestImpl.delDomainNetwork(domain);
		System.out.println("***********ɾ����������end************");
	}
	
	public static void isWorkVtep() {
		System.out.println("***********�����Ƿ�֧��VTEP�������ں�̬begin************");
		System.out.println(CasRestImpl.isWorkForVtep(1L));
		System.out.println("***********�����Ƿ�֧��VTEP�������ں�̬end************");

	}
	
	public static void testConvertToDomain() {
		System.out.println("***********ģ��ת��Ϊ�����begin************");
		RsDomainDeploy domain = new RsDomainDeploy();
		List<RsStore> storages = new ArrayList<RsStore>();
		List<RsDomainSwitch> networks = new ArrayList<RsDomainSwitch>();
		domain.setStorages(storages);
		domain.setNetworks(networks);
		domain.setId(57L);
		domain.setDomainName("scfi");
		domain.setTitle("scfi");
		domain.setTargetHostId(3L);
		domain.setDeployMode(1);
		domain.setProtectMode(0);
		domain.setStorages(storages);
		RsStore store = new RsStore();
		store.setSrc("mini_for_v_cvkzyb_test4");
		store.setDest("scfi");
		store.setPool("defaultpool");
		storages.add(store);
		RsDomainSwitch switch1 = new RsDomainSwitch();
		switch1.setMac("0c:da:41:1d:05:cb");
		switch1.setvSwitchName("vswitch0");
		switch1.setProfileName("Default");
		switch1.setSysIpType(1);
		switch1.setIsBindIp(false);
		networks.add(switch1);
		domain.setVmNum(0);
		domain.setDeployType(0);
		CasRestImpl.convertToDomain(domain);
		
		System.out.println("***********ģ��ת��Ϊ�����end************");
	}
	/**
	 * ��չ�������CPU/�ڴ棩
	 */
	public static void testExtendCpuAndMem() {
		System.out.println("***********������չ�������CPU/�ڴ棩��ʼ************");
		Long vmId = 5083L;
		Integer cpuSocket = 2;
		Integer cpuCore = 3;
		Double memoryInit = 6144D;
		String memUnit = "MB";
		CasRestImpl.extendCpuAndMem(vmId, cpuSocket, cpuCore, memoryInit, memUnit);
		System.out.println("***********������չ�������CPU/�ڴ棩����************");
	}
	
	public static void main(String[] args) {
		// CAS RESTful ������IP��Ϣ
		String ip = "192.168.10.3";
		// CAS RESTful ������Web�˿���Ϣ
		int port = 8080;
		// CAS RESTful ��������¼�û���
		String loginName = "admin";
		// CAS RESTful ��������¼����
		String password = "admin";
		CasRestImpl.login(ip, port, loginName, password);
		
//		testGetVmDiskInfo(rs5L, "defaultpool", 0, 20);//IF_7
//		testDetachVirtualDisk(rs115L, "vda"); //IF_8
//		delDeviceTest(rsclient); //IF_8
//		testQueryVmInfo(rsclient);
//		runVmTest(rsclient);
//		pauseVmTest(rsclient);
//		restoreVmTest(rsclient);
//		sleepVmTest(rsclient);
//		runVmTest(rsclient);
//		restartVmTest(rsclient);
//		powerOffVmTest(rsclient);
//		runVmTest(rsclient);
//		closeVmTest(rsclient);
//		cloneVmTest(rsclient);
//		creareTemplate(rs115L, "template_" + System.currentTimeMillis(), "1", "testtmp");
//		testGetTemplatesInfoList(rs0, 2);
//		deleteTemplate(rs127L);
//		batchDeployTemplate(rs128L, "template_1399799454763RsBatchDeploy4", "test_deploy", 6L);
//		testPerformanceMonitor(rsclient);
//		testWarn(rsclient);
//		testVmCpuBind(rsclient);
//		testVmMemoryShared(rsclient);
//		testVmMemoryBacking(rsclient);
//		testVmProrioty(rsclient);
//		testVmCpuAdd(rsclient);
//		testAddAndDeleteVmDisk(rs"/vms/images/test-04", 115L);
//		testBandwidthOneWayLimit(rsclient);
//		testBandwidthTwoWayLimit(rsclient);
//		migrateVmHost(rs115L, 0, "test-01", 6L);
//		migrateVmHostAndStorage(rs115L, 2, "test-04", "defaultpool", "/vms/images/", 5L);
//		createVMSnapshotTest(rsclient);
//		resumeVMSnapshotTest(rsclient);
//		deleteVMSnapshotTest(rsclient);
//		loadVMSnapshotTest(rsclient);
//		backupDomainLocal(rs115L, "/vms", 0);
//		backupDomainRemote(rs115L, "/vms", 0, "192.168.10.4", "root", "1q2w3e", 1);
		
//		testEditHa();
//		testQueryHostNodeByClusterId();
//		testQueryHostBaseInfoById();
//		testQueryHostInfoById();
//		testQueryHostStatusInfoById();
//		testSetHostNetwork();
//		sshStatus();
//		addAuthKeys();
//		testQueryHostNodeByClusterId();
		
		
		
//		testIntoMaintainStatus();
//		testExitMaintainStatus();
//		addProfile();
//		modifyProfile();
//		queryProfile();
//		deleteProfile();
//		deleteDomainNetwork();
//		testHostServiceStatus();
		
//		testHostServiceStatus();
		
//		testAddVM();
		
//		runVmTest();
//		powerOffVmTest();
		
//		testDeleteVm();
		
//		testQueryHostAndVm();
		
//		testExtendCpuAndMem();
		//49������������̿���
//		testSnapshotDomainDisk();
		//50ɾ����������̿���
//		testDelSnapshotDomainDisk(5l, "defaultpool", "t5");
		//51����������̿��մ�������
//		testResumeSnapshotDomainDisk(18L, "defaultpool", "testVm_s_vda", "defaultpool", "sdk");
		//52������������̴�С
		testModifyVmDiskSize();
//		//53Ǩ�����������
//		testMigrateVolume();
//		//54�����������
//		testCopyVolume(18L, "defaultpool", "testVm_s_vda", "defaultpool", "kkk");
//		//55��ѯ���̻�����Ϣ
//		testGetVolInfo(18L, "defaultpool", "ccc");
//		//56��ѯ�������״̬��Ϣ
//		testGetVolStatusInfo(5073L,"sda");
		//57�������������
//		createVMSnapshotTest();
		//58ɾ�����������
//		deleteVMSnapshotTest();
		//59�޸����������
//		testModifySnapshot();
		//60�������������
//		testBackupVmSnapshot();
		//61��ԭ���������
//		resumeVMSnapshotTest();
		//62��ѯ�����������Ϣ
//		loadVMSnapshotTest();
		//63��������̴���ģ��
//		testDiskTemplate(18L, "defaultpool", "sdk", "defaultpool", "kaka");
		//64��������̿��մ���ģ��
//		testDiskSnapshotTemplate(18L, "defaultpool", "sdk", "defaultpool", "kaka1");
		//65ɾ��ģ��
//		deleteTemplate(248L);
		//66�޸�ģ������
//		modifyVmTemplate();
		//67��ѯģ���б�
//		testGetTemplatesInfoList(0, 50);
		//68��ѯģ����Ϣ
//		testGetTemplatesInfo();
		//69�����תΪģ��
//		creareTemplate(5004L, "KPI", "2", "aaa");
		//70ģ��ת��Ϊ�����
//		testConvertToDomain();
		//71����ģ��
//		testCompressTemplate();
		//72����ģ��������
//		deployTemplate();
		//75�����������
//		addNetwork();
		//77ɾ����������
//		deleteDomainNetwork();
		//78�޸�������������
//		modfiyNetwork();
		//79,80��ѯ��������������Ϣ
//		queryNetwork();
		//81�������⽻����
//		addVswitch();
		//82�޸����⽻��������
//		modfiyVswitch();
		//83ɾ�����⽻����
//		testDelVswitch();
		//84��ѯ���⽻����
//		queryVswitch();
		//85�������˿���
//		addProfile();
		//86��87�޸�����˿�������
//		modifyProfile();
		//88ɾ������˿���
//		deleteProfile();
		//89��ѯ����˿���
//		queryProfile();
		//90 VTEP��װ���ں�̬
//		isWorkVtep();
		
		
		
		CasRestImpl.logout();
	}
}
