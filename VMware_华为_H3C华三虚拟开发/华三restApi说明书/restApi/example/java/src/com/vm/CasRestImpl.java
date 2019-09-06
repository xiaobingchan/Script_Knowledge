package com.vm;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import net.sf.jmimemagic.Magic;
import net.sf.jmimemagic.MagicException;
import net.sf.jmimemagic.MagicMatch;
import net.sf.jmimemagic.MagicMatchNotFoundException;
import net.sf.jmimemagic.MagicParseException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import com.vm.entity.DomainForMigrate;
import com.vm.entity.OsNetwork;
import com.vm.entity.PortProfile;
import com.vm.entity.RsAuthKeys;
import com.vm.entity.RsCluster;
import com.vm.entity.RsCommList;
import com.vm.entity.RsDomain;
import com.vm.entity.RsDomainDeploy;
import com.vm.entity.RsDomainDetail;
import com.vm.entity.RsDomainForBackUp;
import com.vm.entity.RsDomainForModify;
import com.vm.entity.RsDomainForModify.BasicConfig;
import com.vm.entity.RsDomainForModify.CpuConfig;
import com.vm.entity.RsDomainForModify.MemoryConfig;
import com.vm.entity.RsDomainForModify.NetworkConfig;
import com.vm.entity.RsDomainForModify.StorageConfig;
import com.vm.entity.RsDomainNetwork;
import com.vm.entity.RsDomainStorage;
import com.vm.entity.RsDomainVmClone;
import com.vm.entity.RsEvent;
import com.vm.entity.RsFsLunInfo;
import com.vm.entity.RsHost;
import com.vm.entity.RsKeyValue;
import com.vm.entity.RsKeyValueList;
import com.vm.entity.RsMigrateVolume;
import com.vm.entity.RsResult;
import com.vm.entity.RsSFSRelationVm;
import com.vm.entity.RsShareFileSystem;
import com.vm.entity.RsSnapshot;
import com.vm.entity.RsStoragePool;
import com.vm.entity.RsStorageVolume;
import com.vm.entity.RsTaskMsg;
import com.vm.entity.RsTemplateNetwork;
import com.vm.entity.RsTemplateStorage;
import com.vm.entity.RsVPortTrafficInfo;
import com.vm.entity.RsVSwitch;
import com.vm.entity.RsVncParam;
import com.vm.entity.RsVolume;
import com.vm.entity.RsWarnInfo;
import com.vm.entity.VmCpuRate;
import com.vm.entity.VmIoStat;
import com.vm.entity.VmMemoryRate;
import com.vm.entity.VmNetIoStat;
import com.vm.entity.VmParams;
import com.vm.util.DownLoadFileClient;
import com.vm.util.RestUtil;
import com.vm.util.VmEnum;

public class CasRestImpl {
	
	private static DefaultHttpClient client = null;
	
	/** ��־��¼���� */
    private static Log log = LogFactory.getLog(CasRestImpl.class);
	
	private static final String SERVER_UNKNOW_ERROR = "8000";
    
    /** �ϴ��ļ������������200G�� */
    private static final String UPLOAD_FILE_MORE_THAN_MAX_LIMIT = "8005";
    
    /** �ļ���ʽ����ȷ�� */
    private static final String UPLOAD_FILE_FORMAT_ERROR = "8006";

    /** ģ���Ѿ����ڡ� */
    private static final String TEMPLATE_EXISTS = "8020";

    /** ģ���ļ��Ѿ����ڡ� */
    private static final String TEMPLATE_FILE_EXISTS = "8021";

    private static final String SUCCESS = "SUCCESS";
	
	public static void login(String ip, Integer port, String loginName, String password) {
		login(ip, port, loginName, password, "http");
	}
	
	public static void login(String ip, Integer port, String loginName, String password, String protocol) {
		// Http ���ӿͻ��ˡ�
		client = new DefaultHttpClient();
    	HttpHost defaultHost = new HttpHost(ip, port, protocol);
        HttpParams params = client.getParams();
        HttpConnectionParams.setConnectionTimeout(params, 5 * 1000);
//        HttpConnectionParams.setSoTimeout(params, 10 * 1000);
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        params.setParameter(ClientPNames.DEFAULT_HOST, defaultHost);

        // ��֤��Ϣ
        client.getCredentialsProvider().setCredentials(
            new AuthScope(ip, port, "VMC RESTful Web Services"),
            new UsernamePasswordCredentials(loginName, password)); //�û���������
        log.info("�û���֤/��¼�ɹ���");
	}
	
	public static void logout() {
		if (client == null) {
			log.error("����û�е�¼��");
		}
		client.getConnectionManager().shutdown();
		log.info("�û�ע���ɹ���");
	}
	
	/**
     * ��ѯCAS�����������ӿ��б�
     *
     * @param client Http ���ӿͻ��ˡ�
     * @return ����ɹ����������������б�
     */
    public static List<RsHost> queryAllHost() {
    	List<RsHost> result = null;
    	String casuri = "/cas/casrs/host";
    	@SuppressWarnings("unchecked")
		RsCommList<RsHost> comlist = RestUtil.httpGet(client, casuri, RsCommList.class);
    	if (comlist != null) {
    		result = comlist.getData();
    	}
        return result;
    }
    
    /**
     * ��ѯCAS��ָ������������������ӿ��б�
     * 
     * @param client Http ���ӿͻ��ˡ�
     * @param hostId ����ID��
     * @return ����ɹ�����������������б�
     */
    public static List<RsDomain> queryAllVm(Long hostId) {
    	List<RsDomain> result = null;
    	String casuri = "/cas/casrs/host/id/" + hostId + "/vm";
    	@SuppressWarnings("unchecked")
		RsCommList<RsDomain> comlist = RestUtil.httpGet(client, casuri, RsCommList.class);
    	if (comlist != null) {
    		result = comlist.getData();
    	}
        return result;
    }
    
    /**
     * ���������
     * @param client
     * @param params
     */
    public static RsTaskMsg runVm(Long vmId, String name) {
    	VmParams params = new VmParams();
		params.setVmName(name);
		params.setVmId(vmId);
    	params.setOperateType(VmEnum.CAS_VM_OPERRATE_START);
    	return operateVm(params);
    }
    
    /**
     * ������ر�
     * @param client
     * @param params
     */
    public static RsTaskMsg closeVm(Long vmId, String name) {
    	VmParams params = new VmParams();
		params.setVmName(name);
		params.setVmId(vmId);
    	params.setOperateType(VmEnum.CAS_VM_OPERRATE_CLOSE);
    	return operateVm(params);
    }
    
    /**
     * �����ǿ�ƹرյ�Դ
     * @param client
     * @param params
     */
    public static RsTaskMsg powerOffVm(Long vmId, String name) {
    	VmParams params = new VmParams();
		params.setVmName(name);
		params.setVmId(vmId);
    	params.setOperateType(VmEnum.CAS_VM_OPERRATE_POWEROFF);
    	return operateVm(params);
    }
    
    /**
     * �������ͣ
     * @param client
     * @param params
     */
    public static RsTaskMsg pauseVm(Long vmId, String name) {
    	VmParams params = new VmParams();
		params.setVmName(name);
		params.setVmId(vmId);
    	params.setOperateType(VmEnum.CAS_VM_OPERRATE_PAUSE);
    	return operateVm(params);
    }
    
    /**
     * ������ָ�
     * @param client
     * @param params
     */
    public static RsTaskMsg restoreVm(Long vmId, String name) {
    	VmParams params = new VmParams();
		params.setVmName(name);
		params.setVmId(vmId);
    	params.setOperateType(VmEnum.CAS_VM_OPERRATE_RESTORE);
    	return operateVm(params);
    }
    
    /**
     * ���������
     * @param client
     * @param params
     */
    public static RsTaskMsg sleepVm(Long vmId, String name) {
    	VmParams params = new VmParams();
		params.setVmName(name);
		params.setVmId(vmId);
    	params.setOperateType(VmEnum.CAS_VM_OPERRATE_SLEEP);
    	return operateVm(params);
    }
    
    /**
     * ���������
     * @param client
     * @param params
     */
    public static RsTaskMsg restartVm(Long vmId, String name) {
    	VmParams params = new VmParams();
		params.setVmName(name);
		params.setVmId(vmId);
    	params.setOperateType(VmEnum.CAS_VM_OPERRATE_RESTART);
    	return operateVm(params);
    }
    
    /**
     * (����,��ͣ,�ָ�,����,����,�ر�,�رյ�Դ)�����������
     * 
     * @param client Http ���ӿͻ��ˡ�
     * @param vmId �����ID��
     * @param vmName ���������
     * @param operateType ��������
     * @return ����ɹ�������0�����ʧ�ܣ����ش����롣
     */
    public static RsTaskMsg operateVm(VmParams params) {
    	String uri = null;
    	String operateType = params.getOperateType();
    	if (operateType.equals("close")) {
    		uri = "/cas/casrs/vm/stop/" + params.getVmId();
    	} else {
    		uri = "/cas/casrs/vm/" + operateType + "/" + params.getVmId();
    	}
    	RsTaskMsg rsTaskMsg = RestUtil.httpPut(client, uri, null, RsTaskMsg.class);
    	return waitForComplete(rsTaskMsg.getMsgId());
    }
    
    /**
     * ���������
     * 
     * @param client Http ���ӿͻ��ˡ�
     * @param rsDomain ��Ϣʵ����
     * @return ����ɹ�������0�����ʧ�ܣ����ش����롣
     */
	public static RsTaskMsg createVm(RsDomain rsDomain) {
		rsDomain.setFormatEnable(1);
    	
		String uri = "/cas/casrs/vm/add";
		RsTaskMsg rsTaskMsg = RestUtil.httpPost(client, uri, rsDomain, RsTaskMsg.class);
		return waitForComplete(rsTaskMsg.getMsgId());
	}
    
    /**
     * ɾ�������
     * 
     * @param client Http ���ӿͻ��ˡ�
     * @param hostId �����id
     * @return ����ɹ�������0�����ʧ�ܣ����ش����롣
     */
    public static RsTaskMsg deleteVm(Long vmId, Integer delType, Boolean isWipeVolume){
    	String uri = "/cas/casrs/vm/deleteVm?id=" + vmId + "&type=" + delType +"&isWipeVolume="+ isWipeVolume;
    	RsTaskMsg rsTaskMsg = RestUtil.httpDelete(client, uri, RsTaskMsg.class);
    	return waitForComplete(rsTaskMsg.getMsgId());
    }
    
    /**
     * ɾ�������
     * 
     * @param client Http ���ӿͻ��ˡ�
     * @param hostId �����id
     * @return ����ɹ�������0�����ʧ�ܣ����ش����롣
     */
    public static RsTaskMsg deleteVm(Long vmId){
    	Integer delType = 0;
    	Boolean isWipeVolume = new Boolean(false);
    	return deleteVm(vmId, delType, isWipeVolume);
    }

    /**
	 * �ȴ�����ִ�У�ֱ��ʧ�ܻ���ɹ�
	 * @param client
	 * @param msgId
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static RsTaskMsg waitForComplete(Long msgId) {
		String uri = "/cas/casrs/message/" + msgId;
		RsTaskMsg task = RestUtil.httpGet(client, uri, RsTaskMsg.class);
		while(!task.isCompleted()) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			task = RestUtil.httpGet(client, uri, RsTaskMsg.class);
			StringBuffer sb = new StringBuffer();
			sb.append(task.getMsgId() == null ? "" : task.getMsgId()).append(" ").
			append(task.getProgress()).append("%").append(" ").
			append(task.getFailMsg() == null ? "" : task.getFailMsg());
			log.info(sb.toString());
		}
		return task;
	}
	
	/**
     * ɾ������Ӳ��
     * @param client
     * @return
     */
    public static void delVol(long hostId, String poolName, String volName) {
    	String uri = "/cas/casrs/storage/volume/delete/"+ hostId + "/" + poolName + "/" + volName;
    	RestUtil.httpDelete(client, uri);
    }
    
    /**
     * ��ȡ�������Ϣ
     * @param client
     * @return
     */
    public static RsDomain getVmInfo(Long vmId) {
        String casuri = "/cas/casrs/vm/" + vmId;
    	RsDomain result = RestUtil.httpGet(client, casuri, RsDomain.class);
        return result;
    }
    
    /**
     * ��¡�����
     * @param client
     * @param params
     * @return
     */
    public static RsTaskMsg cloneVM(RsDomainVmClone params){
    	String uri = "/cas/casrs/vm/clone";
    	
    	RsTaskMsg task = RestUtil.httpPut(client, uri, params, RsTaskMsg.class);
    	RsTaskMsg msg = waitForComplete(task.getMsgId());
		return msg;
    }
	
    
    /**
     * �������������
     * @param client
     * @return
     */
    public static RsTaskMsg createVMSnapshot(RsSnapshot rsDomain) {
    	String uri = "/cas/casrs/vm/snapshot";
    	RsTaskMsg task = RestUtil.httpPost(client, uri, rsDomain, RsTaskMsg.class);
    	RsTaskMsg msg = waitForComplete(task.getMsgId());
		return msg;
    }
    
    /**
     * ɾ�����������
     * @param client
     * @return
     */
    public static RsTaskMsg deleteVMSnapshot(RsSnapshot rsDomain) {
    	String uri = "/cas/casrs/vm/snapshot/" + rsDomain.getVmId() + "/" + rsDomain.getName();
    	RsTaskMsg task = RestUtil.httpDelete(client, uri, RsTaskMsg.class);
    	RsTaskMsg msg = waitForComplete(task.getMsgId());
		return msg;
    }
    
    /**
     * ��ԭ���������
     * @param client
     * @return
     */
    public static RsTaskMsg resumeVMSnapshot(RsSnapshot rsDomain) {
    	String uri = "/cas/casrs/vm/snapshot/resume";
    	RsTaskMsg task = RestUtil.httpPut(client, uri, rsDomain, RsTaskMsg.class);
    	RsTaskMsg msg = waitForComplete(task.getMsgId());
		return msg;
    }
    
    /**
     * �޸����������
     * @return
     */
    public static RsTaskMsg modifyVmSnapshot(Long vmId, String name, String snapName, String desc) {
    	String uri = "/cas/casrs/vm/snapshot/restore?vmId=" + vmId + "&name=" + name + 
    			"&snapName=" + snapName + "&desc=" + desc;
		RsTaskMsg rsTaskMsg = RestUtil.httpPut(client, uri, null, RsTaskMsg.class);
    	RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
		return task;
    }
    
    /**
     * ��ѯָ�������������Ϣ
     * @param client
     * @param hostId
     * @return
     */
    public static List<RsSnapshot> loadVMSnapshot(Long vmId) {
    	List<RsSnapshot> result = null;
    	String uri = "/cas/casrs/vm/snapshot/" + vmId;
    	@SuppressWarnings("unchecked")
		RsCommList<RsSnapshot> comList = RestUtil.httpGet(client, uri, RsCommList.class);
    	if (comList != null) {
    		result = comList.getData();
    	}
        return result;
    }

    /**
	 * �������¡Ϊģ��
	 * @param client Http ���ӿͻ��ˡ�
	 * @param vmId ����id
	 * @param templateName ģ������
	 * @param desc ģ������
	 */
    public static RsTaskMsg cloneToVmTmp (Long vmId, String templateName, String desc) {
    	return createTemplate(vmId, templateName, 1, desc);
    }

    /**
	 * �����ת��Ϊģ��
	 * @param client Http ���ӿͻ��ˡ�
	 * @param vmId ����id
	 * @param templateName ģ������
	 * @param desc ģ������
	 */
    public static RsTaskMsg convertToVmTmp(Long vmId, String templateName, String desc) {
    	return createTemplate(vmId, templateName, 2, desc);
    }
    
    /**
	 * ���������ģ��
	 * @param client Http ���ӿͻ��ˡ�
	 * @param vmId ����id
	 * @param templateName ģ������
	 * @param opType ������ʽ�� 1��¡��ģ�壬 2 ת�Ƴ�ģ��
	 * @param desc ģ������
	 */
	private static RsTaskMsg createTemplate(Long vmId, String templateName, Integer opType, String desc) {
	    String uri = "/cas/casrs/vmTemplate/addVmTemplate?id=" + vmId + "&domainName=" + templateName + "&desc=" + desc + "&opType=" + opType;
	    RsTaskMsg rsTaskMsg = RestUtil.httpPost(client, uri, null, RsTaskMsg.class);
	    RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
	    return task;
	}
	
	/**
	 * ��ѯģ���б���Ϣ
	 * @param client Http ���ӿͻ��ˡ�
	 * @param offset ��ѯ��ʼλ��
	 * @param limit ��ѯ�ļ�¼��
	 * @return �����ģ���б�
	 */
	public static RsCommList<RsDomain> getTemplatesInfo(Integer offset, Integer limit) {
    	String uri = "/cas/casrs/vmTemplate/vmTemplateList?offset=" + offset + "&limit=" + limit;
    	@SuppressWarnings("unchecked")
		RsCommList<RsDomain> templates = RestUtil.httpGet(client, uri, RsCommList.class);
    	return templates;
	}
	
    /**
     * ��ȡ�����ģ����Ϣ
     * @param client
     * @return
     */
    public static RsDomain getVmTmpInfo(Long vmId) {
        String casuri = "/cas/casrs/vm/" + vmId;
    	RsDomain result = RestUtil.httpGet(client, casuri, RsDomain.class);
        return result;
    }
	
	/**
	 * ɾ�������ģ��
	 * @param client Http ���ӿͻ��ˡ�
	 * @param templateId �����ģ��ID
	 */
	public static void delVmTmp(Long templateId) {
		String uri = "/cas/casrs/vmTemplate/deleteVmTemplate?id=" + templateId.toString();
		RestUtil.httpDelete(client, uri);
	}
	
	/**
	 * �޸������ģ��
	 */
	public static RsTaskMsg modifyVmTemplate (RsDomain domain) {
		String uri = "/cas/casrs/vmTemplate/vmTemplateInfo?id=" + domain.getId();
		RsDomain rsTemplate = RestUtil.httpGet(client, uri, RsDomain.class);
		uri = "/cas/casrs/vmTemplate/storage?vmId=" + domain.getId();
		@SuppressWarnings("unchecked")
		RsCommList<RsTemplateStorage> rsStorages = RestUtil.httpGet(client, uri, RsCommList.class);
		rsTemplate.setName(domain.getName());
		if (rsStorages != null) {
			List<RsTemplateStorage> listTempStorage = rsStorages.getData();
			List<RsDomainStorage> listStorage = new ArrayList<RsDomainStorage>();
			for (RsTemplateStorage rsTemStorage : listTempStorage) {
				RsDomainStorage rsDomainStorage = new RsDomainStorage();
				rsDomainStorage.setId(rsTemStorage.getId());
				rsDomainStorage.setStoreFile(rsTemStorage.getStoreFile());
				listStorage.add(rsDomainStorage);
				rsTemplate.setStorages(listStorage);
			}
		} 
		uri = "/cas/casrs/vmTemplate/modifyVmTemplate";
		RsTaskMsg rsTaskMsg = RestUtil.httpPut(client, uri, rsTemplate, RsTaskMsg.class);
    	RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
		return task;
	}
	
	
	/**
	 * �������������
	 * @param client  Http ���ӿͻ��ˡ�
	 * @param configs  �������������������б�
	 * @return
	 */
	public static void batchDeploy(RsCommList<RsDomainDeploy> configs) {
	    String uri = "/cas/casrs/vm/batchDeploy";
	    @SuppressWarnings("unchecked")
		RsKeyValueList<RsKeyValue> list = RestUtil.httpPost(client, uri, configs, RsKeyValueList.class);
	    if (list != null && list.getData() != null) {
        	if (list.getData().size() == configs.getData().size()) {
        		log.info("��������[" + list.getData().size() + "̨]�����"); 
        	}
        	for (int i = 0; i < list.getData().size(); i++) {
        		waitForComplete(new Long(list.getData().get(i).getValue()));
        	}
        	log.info("����������������"); 
        }
	}
	
	/**
	 * ���������
	 * @param client  Http ���ӿͻ��ˡ�
	 * @param backPrama ���ݲ���
	 * @return
	 */
	public static RsTaskMsg backupVm(RsDomainForBackUp backPrama) {
	    String uri = "/cas/casrs/vm/backUp";
	    Long taskId = RestUtil.httpPut(client, uri, backPrama, Long.class);
	    RsTaskMsg task = waitForComplete(taskId);
		return task;
	}
	
	/**
	 * 
     * ��ѯ�澯��Ϣ�б�
     * 
	 * @param client http client
	 * @param offset ��¼��ʼλ��
	 * @param limit  �������
	 * @param state ״̬
	 * @param eventLevel �澯����
 	 * @return
	 */
	public static List<RsEvent> queryWarnInfo(Integer offset, Integer limit, Integer state, Integer eventLevel) {
		StringBuilder casuri = new StringBuilder("/cas/casrs/warnManage/warnManageList");
		List<RsEvent> result = new ArrayList<RsEvent>();
		if (offset == null) {
			offset = 0;		
		}
		casuri.append("?offset=").append(offset);
		if (limit != null) {
			casuri.append("&limit=").append(limit);		
		}
		if (state != null) {
			casuri.append("&state=").append(state);			
		}
		if (eventLevel != null) {
			casuri.append("&eventLevel=").append(eventLevel);				
		}
		@SuppressWarnings("unchecked")
		RsCommList<RsEvent> commList = RestUtil.httpGet(client, casuri.toString(), RsCommList.class);
		if (commList != null) {
			result = commList.getData();
		}
		return result;
	}
	
	/**
	 * ��ѯ�澯��ϸ��Ϣ
	 * @param client http client
	 * @param id �澯��Ϣid
	 * @return
	 */
	public static RsWarnInfo queryWarnMessage(Long id) {
		if (id == null) {
			return null;
		}
		RsWarnInfo warn = null;
		String casuri = "/cas/casrs/warnManage/warnInfo?warnId=" + id;
		warn = RestUtil.httpGet(client, casuri, RsWarnInfo.class);
		return warn;
	}
	
	/**
	 * ȷ�ϸ澯��Ϣ
	 * @param client http client
	 * @param evList �澯��Ϣ�б�
	 * @return
	 */
	public static void updateEvent(List<RsEvent> evList) {
		String casuri = "/cas/casrs/warnManage/updateEvent";
		RsCommList<RsEvent> evRslist = new RsCommList<RsEvent>();
		evRslist.setData(evList);
		RestUtil.httpPut(client, casuri, evRslist, null);
	}
	
	public static void deleteWarn(Long id) {
		String casuri = "/cas/casrs/warnManage/deleteEvent?warnId=" + id;
		RestUtil.httpDelete(client, casuri);
	}
	
	/**
	 * �޸������CPU���ã�����CPU�����Ͱ���Ϣ
	 * @param client
	 * @param rsDomain
	 */
	public static void modifyCpuConfig(RsDomainForModify rsDomain) {
		editVm(rsDomain);
	}
	
	/**
	 * �޸����������
	 * @param client
	 * @param rsDomain
	 */
	public static RsTaskMsg editVm(RsDomainForModify rsDomain) {
		String casuri = "/cas/casrs/vm/modify";
		RsTaskMsg rsTaskMsg = RestUtil.httpPut(client, casuri, rsDomain, RsTaskMsg.class);
		RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
		return task;
	}
	
	/**
	 * �޸���������̴�С
	 * @param client
	 * @param rsDomain
	 */
	public static RsTaskMsg editVmDisk(RsDomainForModify rsDomainModify) {
		String casuri = "/cas/casrs/vm/detail/" + rsDomainModify.getId();
		RsDomainDetail rsDomain = RestUtil.httpGet(client, casuri, RsDomainDetail.class);
		casuri = "/cas/casrs/vm/modify";
		rsDomainModify.setName(rsDomain.getName());
		StorageConfig storage = rsDomainModify.getStorage();
		List<StorageConfig> listStorage = rsDomain.getStorages();
		if (listStorage != null) {
			for (StorageConfig storageConf : listStorage) {
				if (storageConf.getDeviceName().equals(rsDomainModify.getStorage().getDevice())) {
					storage.setFormat(storageConf.getFormat());
					storage.setCacheType(storageConf.getCacheType());
				}
			}
		}
		RsTaskMsg rsTaskMsg = RestUtil.httpPut(client, casuri, rsDomainModify, RsTaskMsg.class);
		RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
		return task;
	}
	
	/**
	 * ��ѯ���������������CPU������
	 * @param client
	 * @param hostId
	 * @return
	 */
	public static List<VmCpuRate> queryVmCpuRate(Long hostId) {
		String casuri = "/cas/casrs/host/id/" + hostId + "/vmcpu";
		@SuppressWarnings("unchecked")
		RsCommList<VmCpuRate> commList = RestUtil.httpGet(client, casuri, RsCommList.class);
		if (commList != null) {
			return commList.getData();
		}
		return null;
	}

	/**
	 * ��ѯ����������������ڴ�������
	 * @param client
	 * @param hostId
	 * @return
	 */
	public static List<VmMemoryRate> queryVmMemRate(Long hostId) {
		String casuri = "/cas/casrs/host/id/" + hostId + "/vmmem";
		@SuppressWarnings("unchecked")
		RsCommList<VmMemoryRate> commList = RestUtil.httpGet(client, casuri, RsCommList.class);
		if (commList != null) {
			return commList.getData();
		}
		return null;
	}
	
	/**
	 * ��ѯ���������������IO������
	 * @param client
	 * @param hostId
	 * @return
	 */
	public static List<VmIoStat> queryVmIoStat(Long hostId) {
		String casuri = "/cas/casrs/host/id/" + hostId + "/vmio";
		@SuppressWarnings("unchecked")
		RsCommList<VmIoStat> commList = RestUtil.httpGet(client, casuri, RsCommList.class);
		if (commList != null) {
			return commList.getData();
		}
		return null;
	}
	
	/**
	 * ��ѯ�������������������������
	 * @param client
	 * @param hostId
	 * @return
	 */
	public static List<VmNetIoStat> queryVmNetIo(Long hostId) {
		String casuri = "/cas/casrs/host/id/" + hostId + "/vmnetio";
		@SuppressWarnings("unchecked")
		RsCommList<VmNetIoStat> commList = RestUtil.httpGet(client, casuri, RsCommList.class);
		if (commList != null) {
			return commList.getData();
		}
		return null;
	}
	
	/**
	 * �ڴ���Դר�����ã���������ڴ�����Ϊ����ģʽ
	 * @param client
	 * @param hostId
	 */
	public static void setVmMemoryShared(Long vmId, Long size) {
		RsDomainForModify rsDomain = new RsDomainForModify();
		rsDomain.setId(vmId);
		MemoryConfig memory = new MemoryConfig();
		memory.setMemoryBacking(0);
		memory.setSize(size);
		rsDomain.setMemory(memory );
		editVm(rsDomain);
	}
	
	/**
	 * �ڴ���Դר�����ã���������ڴ�����Ϊ����ģʽ
	 * @param client
	 * @param hostId
	 */
	public static void setVmMemoryBacking(Long vmId, Long size, Integer memoryBacking) {
		RsDomainForModify rsDomain = new RsDomainForModify();
		rsDomain.setId(vmId);
		MemoryConfig memory = new MemoryConfig();
		memory.setMemoryBacking(memoryBacking);
		memory.setSize(size);
		rsDomain.setMemory(memory);
		editVm(rsDomain);
	}
	
	/**
	 * ������Դ���ȼ�����:����IO���ȼ����������ȼ�
	 * @param client
	 * @param vmId
	 * @param io
	 * @param start
	 */
	public static void setVmPriority(Long vmId, Integer io, Integer start) {
		RsDomainForModify rsDomain = new RsDomainForModify();
		rsDomain.setId(vmId);
		
		BasicConfig basicConfig = new BasicConfig();
		basicConfig.setAcpi(0);
		basicConfig.setApic(0);
		basicConfig.setPae(0);
		basicConfig.setBlkiotune(io);
		basicConfig.setAutoMigrate(0);
		basicConfig.setStartPriority(start);
		basicConfig.setClock("utc");
		rsDomain.setBasicConfig(basicConfig );
		editVm(rsDomain);
	}
	
	/**
	 * ��ѯ�����ģ���б�
	 * @param client
	 * @param vmId
	 * @param dev
	 * @return
	 */
	public static List<PortProfile> queryProfileList() {
		String casuri = "/cas/casrs/profile";
		@SuppressWarnings("unchecked")
		RsCommList<PortProfile> commList = RestUtil.httpGet(client, casuri, RsCommList.class);
		if (commList != null) {
			return commList.getData();
		}
		return null;
	}
	
	/**
	 * ��ѯָ���������ģ��
	 * @param client
	 * @param profileId
	 * @return
	 */
	public static PortProfile queryProfile(Long profileId) {
		String casuri = "/cas/casrs/profile/" + profileId;
		PortProfile profile = RestUtil.httpGet(client, casuri, PortProfile.class);
		return profile;
	}
	
	/**
	 * �޸��������ģ��
	 * @param client
	 * @param profile
	 */
	public static void modifyProfile(PortProfile profile) {
		String casuri = "/cas/casrs/profile";
		RestUtil.httpPut(client, casuri, profile, null);
	}
	
	/**
	 * �����������ģ��
	 * @param client
	 * @param profile
	 */
	public static void addProfile(PortProfile profile) {
		String casuri = "/cas/casrs/profile";
		RestUtil.httpPost(client, casuri, profile, null);
	}
	
	/**
	 * ɾ���������ģ��
	 * @param client
	 * @param profileId
	 * @param name
	 */
	public static void deleteProfile(Long profileId, String name) {
		String casuri = "/cas/casrs/profile/" + profileId +"/" + name;
		RestUtil.httpDelete(client, casuri);
	}
	
	public static Boolean isWorkForVtep(Long hostId) {
		String uri = "/cas/casrs/vswitch/isWorkForVtep?hostId=" + hostId;
		return RestUtil.httpGet(client, uri, Boolean.class);
	}

	/**
	 * ��ָ���洢��������ָ���洢��
	 * @return true ��ӳɹ� false ���ʧ��
	 */
	public static void addVol(RsVolume rsVolume) {
		String uri = "/cas/casrs/storage/volume/add";
		RestUtil.httpPost(client, uri, rsVolume, null);
	}

	/**
	 * Ϊָ��������������Ӳ��
	 * @return true ��ӳɹ� false ���ʧ��
	 */
	public static RsResult addVmDisk(RsDomainForModify rsDomainForModify) {
		String uri = "/cas/casrs/vm/addDevice";
		return RestUtil.httpPut(client, uri, rsDomainForModify, RsResult.class);
	}

	/**
	 * ж���������������̣���������������
	 * @return true ж�سɹ� false ж��ʧ��
	 */
	public static void delVmDisk(RsDomainForModify rsDomain) {
		String uri = "/cas/casrs/vm/delDevice";
		RestUtil.httpPut(client, uri, rsDomain, null);
	}


	/**
	 * ��ѯCASϵͳ�������µĴ洢���б�
	 * @param client
	 * @param hostId ����id
	 * @return ������洢ʵ����
	 */
	public static List<RsStoragePool> queryStoragePool(Long hostId) {
		List<RsStoragePool> result = null;
		String uri = "/cas/casrs/storage/pool?hostId=" + hostId;
		@SuppressWarnings("unchecked")
		RsCommList<RsStoragePool> comList = RestUtil.httpGet(client, uri, RsCommList.class);
		if (comList != null) {
			result = comList.getData();
		}
        return result;
	}

	/**
	 * ��ѯCASϵͳ��������ָ���洢���µĴ洢���б�
	 * @return  �洢��ʵ����
	 */
	public static List<RsStorageVolume> getVolInfo(Long hostId, String poolName, String volumeName, Integer offset, Integer pageSize) {
		List<RsStorageVolume> result = null;
		String uri = "/cas/casrs/storage/volume?hostId=" + hostId + "&poolName=" + poolName;
		if (volumeName != null) {
			uri = uri + "&volumeName=" + volumeName;
		}
		if (offset != null) {
			uri = uri + "&offset=" + offset;
		}
		if (pageSize != null) {
			uri = uri + "&pageSize=" + pageSize;
		}
		@SuppressWarnings("unchecked")
		RsCommList<RsStorageVolume> comList = RestUtil.httpGet(client, uri, RsCommList.class);
		if (comList != null) {
			result = comList.getData();
		}
        return result;
	}
	
	/**
	 * ��ѯCASϵͳ��������ָ���洢���µĴ洢���б�
	 * @return  �洢��ʵ����
	 */
	public static List<RsStorageVolume> getVolBaseInfo(Long hostId, String poolName, String volumeName) {
		List<RsStorageVolume> result = null;
		String uri = "/cas/casrs/storage/volume?hostId=" + hostId + "&poolName=" + poolName;
		if (volumeName != null) {
			uri = uri + "&volumeName=" + volumeName;
		}
		@SuppressWarnings("unchecked")
		RsCommList<RsStorageVolume> comList = RestUtil.httpGet(client, uri, RsCommList.class);
		if (comList != null) {
			result = comList.getData();
		}
        return result;
	}
	
	/**
	 * ��ѯ�������״̬��Ϣ
      ��IO������ƽ��IO���ʡ����IO���ʡ�ʵʱIO���ʣ�
	 * @return  �洢��ʵ����
	 */
	public static List<RsKeyValue> getVolStatusInfo(Long vmId, String devName) {
		List<RsKeyValue> result = null;
		String uri = "/cas/casrs/vm/diskIOStatus?id=" + vmId + "&dev=" + devName;
		@SuppressWarnings("unchecked")
		RsCommList<RsKeyValue> comList = RestUtil.httpGet(client, uri, RsCommList.class);
		if (comList != null) {
			result = comList.getData();
		}
        return result;
	}

	/**
	 * Ǩ��ָ�����������������ֻǨ��������ֻǨ�ƴ洢��Ǩ�������ʹ洢����Ǩ������
	 * @return true Ǩ�Ƴɹ� false Ǩ��ʧ��
	 */
	public static RsTaskMsg vmMigrate(DomainForMigrate domainForMigrate) {
		String uri = "/cas/casrs/vm/migrate";
		RsTaskMsg rsTaskMsg = RestUtil.httpPut(client, uri, domainForMigrate, RsTaskMsg.class);
		RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
        return task;
	}
	
	/**
	 * ����������������
	 * @param client
	 * @param vm
	 * @param inAvg
	 * @param inBurst
	 */
	public static void bandwidthOneWayLimit(RsDomain vm, Long inAvg, Long inBurst) {
		PortProfile profile = new PortProfile();
		profile.setName("oneway-" + System.currentTimeMillis());
		profile.setDescription("������Ʋ���");
		profile.setInbound(1);
		profile.setInAvgBandwidth(inAvg);
		profile.setInBurstSize(inBurst);
		profile.setInPeakBandwidth(inAvg);
		addProfile(profile);
		
		List<PortProfile> profileList = queryProfileList();
		if (profileList != null && profileList.size() > 0) {
			for (PortProfile p : profileList) {
				if (p.getName().equals(profile.getName())) {
					profile = p;
					break;
				}
			}
		}

		if (vm != null && profile.getId() != null) {
			RsDomainNetwork net = vm.getNetworks().get(0);
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(vm.getId());
			NetworkConfig network = new NetworkConfig();
			network.setMac(net.getMac());
			network.setProfileId(profile.getId());
			network.setVsName(net.getVsName());
			network.setVsId(6L);
			network.setMode("veb");
			network.setDeviceModel("virtio");
			rsDomain.setNetwork(network );
			editVm(rsDomain);
		}
	}
	
	/**
	 * ˫��������������
	 * @param client
	 * @param vm
	 * @param inAvg
	 * @param inBurst
	 * @param outAvg
	 * @param outBurst
	 */
	public static void bandwidthTwoWayLimit(RsDomain vm, Long inAvg, Long inBurst, Long outAvg, Long outBurst) {
		PortProfile profile = new PortProfile();
		profile.setName("twoway-" + System.currentTimeMillis());
		profile.setDescription("˫����Ʋ���");
		profile.setInbound(1);
		profile.setInAvgBandwidth(inAvg);
		profile.setInBurstSize(inBurst);
		profile.setInPeakBandwidth(inAvg);
		profile.setOutbound(1);
		profile.setOutAvgBandwidth(outAvg);
		profile.setOutBurstSize(outBurst);
		profile.setOutPeakBandWidth(outAvg);
		addProfile(profile);
		
		List<PortProfile> profileList = queryProfileList();
		if (profileList != null && profileList.size() > 0) {
			for (PortProfile p : profileList) {
				if (p.getName().equals(profile.getName())) {
					profile = p;
					break;
				}
			}
		}

		if (vm != null && profile.getId() != null) {
			RsDomainNetwork net = vm.getNetworks().get(0);
			RsDomainForModify rsDomain = new RsDomainForModify();
			rsDomain.setId(vm.getId());
			NetworkConfig network = new NetworkConfig();
			network.setMac(net.getMac());
			network.setProfileId(profile.getId());
			network.setVsName(net.getVsName());
			network.setVsId(6L); //TODO
			network.setMode("veb");
			network.setDeviceModel("virtio");
			rsDomain.setNetwork(network );
			editVm(rsDomain);
		}
	}

	/**
	 * �޸�������������ýӿ�
	 * @param client
	 * @param rsDomain
	 */
	public static void editVmNet(RsDomainForModify rsDomain) {
		editVm(rsDomain);
	}
	
	/**
	 * HA(����/����/����)
	 * @param client
	 * @param rsCluster
	 */
	public static RsTaskMsg editHa(RsCluster rsCluster) {
		String uri = "/cas/casrs/cluster/editHa";
		RsTaskMsg rsTaskMsg = RestUtil.httpPut(client, uri, rsCluster, RsTaskMsg.class);
	    RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
		return task;
	}
	
	/**
     * ��ѯCAS�����������ӿ��б�
     * @return ����ɹ����������������б�
     */
    public static List<RsHost> queryHostNodeByClusterId(Long clusterId) {
    	List<RsHost> result = null;
    	String uri = "/cas/casrs/cluster/hostNodes" + "?clusterId=" + clusterId;
    	@SuppressWarnings("unchecked")
		RsCommList<RsHost> comlist = RestUtil.httpGet(client, uri, RsCommList.class);
    	if (comlist != null) {
    		result = comlist.getData();
    	}
        return result;
    }
    /**
     * ��ѯ����״̬
     * @param id
     * @param command
     * @return
     */
    public static RsResult sshStatus(Long id) {
    	String uri = "/cas/casrs/castools/sshStatus/" + id;
    	RsResult rsResult = RestUtil.httpGet(client, uri, RsResult.class);
		return rsResult;
    }
    /**
     * ��������Կ��Ϣ
     * @param authKeys
     * @return
     */
    public static RsResult addAuthKeys(RsAuthKeys authKeys) {
    	String uri = "/cas/casrs/castools/authKeys";
    	RsResult rsResult = RestUtil.httpPut(client, uri, authKeys, RsResult.class);
		return rsResult;
    }
    
    
    /**
     * ������������� 
     * @param rsSnapshot
     * @return
     */
    public static RsTaskMsg snapshotDomainDisk (RsSnapshot rsSnapshot) {
    	String uri = "/cas/casrs/vm/snapshot/disk";
    	RsTaskMsg rsTaskMsg = RestUtil.httpPost(client, uri, rsSnapshot, RsTaskMsg.class);
    	RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
		return task;
    }
    
    /**
     * ������������� 
     * @param rsSnapshot
     * @return
     */
    public static RsTaskMsg backupVmSnapshot (RsSnapshot rsSnapshot) {
    	String uri = "/cas/casrs/vm/snapshot/disk";
    	RsTaskMsg rsTaskMsg = RestUtil.httpPost(client, uri, rsSnapshot, RsTaskMsg.class);
    	RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
		return task;
    }
    
    /**
     * ɾ��������������� 
     * @return
     */
    public static void delSnapshotDomainDisk (long hostId, String poolName, String snapName) {
    	String uri = "/cas/casrs/storage/volume/delete/"+ hostId + "/" + poolName + "/" + snapName;
    	RestUtil.httpDelete(client, uri);
    	
    }
    
    /**
     * ����������̿��մ������� 
     * @return
     */
    public static RsTaskMsg resumeSnapshotDomainDisk (long vmId, String devName, String snapName, String targetPool, String targetVol) {
    	String uri = "/cas/casrs/vm/snapshot/disk/resume?id=" + vmId + "&devName=" + devName
    		+ "&snapName=" + snapName + "&targetPool=" + targetPool + "&targetVol=" + targetVol;
    	RsTaskMsg rsTaskMsg = RestUtil.httpPost(client, uri, null, RsTaskMsg.class);
    	return waitForComplete(rsTaskMsg.getMsgId());
    }
    
    /**
     * �����������
     * @return
     */
    public static void copyVolume (long hostId, String poolName, String volName, String targetPool, String targetVol) {
    	String uri = "/cas/casrs/storage/volume/copy?id=" + hostId + "&poolName=" + poolName
    	+ "&volName=" + volName + "&targetPool=" + targetPool + "&targetVol=" + targetVol;
    	RestUtil.httpPost(client, uri, null, RsTaskMsg.class);
    }
    
    /**
     * ��������̴���ģ��
     * @return
     */
    public static void diskTemplate (long hostId, String poolName, String volName, String targetPool, String targetVol) {
    	String uri = "/cas/casrs/storage/volume/copy?id=" + hostId + "&poolName=" + poolName
    	+ "&volName=" + volName + "&targetPool=" + targetPool + "&targetVol=" + targetVol;
    	RestUtil.httpPost(client, uri, null, null);
    }
    
    /**
     * ��������̿��մ���ģ��
     * @return
     */
    public static RsTaskMsg diskSnapshotTemplate (long vmId, String devName, String snapName, String targetPool, String targetVol) {
    	String uri = "/cas/casrs/vm/snapshot/disk/resume?id=" + vmId + "&devName=" + devName
		+ "&snapName=" + snapName + "&targetPool=" + targetPool + "&targetVol=" + targetVol;
    	RsTaskMsg rsTaskMsg = RestUtil.httpPost(client, uri, null, RsTaskMsg.class);
    	return waitForComplete(rsTaskMsg.getMsgId());
    }
    
    /**
     * Ǩ�����������
     */
    @SuppressWarnings("unchecked")
	public static RsCommList<RsTaskMsg> batchMigrateVolume (RsMigrateVolume rsMigrateVolume) {
    	String uri = "/cas/casrs/vm/alterStoragePool";
    	RsCommList<RsTaskMsg> taskComlist = RestUtil.httpPut(client, uri, rsMigrateVolume, RsCommList.class);
        return taskComlist;
    }
    
    /**
     * ��ѯ����������Ϣ
     * @param hostId
     * @return
     */
    public static RsHost queryHostById(Long hostId) {
    	String uri = "/cas/casrs/host/id/" + hostId;
    	RsHost rsHost = RestUtil.httpGet(client, uri, RsHost.class);
    	return rsHost;
    }
    
    @SuppressWarnings("unchecked")
	public static RsCommList<RsKeyValue> queryHostDiskIOAndNetIO(Long hostId) {
    	String uri = "/cas/casrs/host/diskIOAndNetIO?id=" + hostId;
    	RsCommList<RsKeyValue> rsCommList = RestUtil.httpGet(client, uri, RsCommList.class);
    	return rsCommList;
    }
    
    /**
     * ���ü���ڵ���������
     * @param rsVSwitchTmp
     */
    public static void setHostNetwork(RsVSwitch rsVSwitchTmp) {
    	String uri = "/cas/casrs/vswitch/info?vsId=" + rsVSwitchTmp.getId();
    	RsVSwitch rsVSwitch = RestUtil.httpGet(client, uri, RsVSwitch.class);
    	if (rsVSwitch != null) {
    		rsVSwitch.setAddress(rsVSwitchTmp.getAddress());
    		rsVSwitch.setNetmask(rsVSwitchTmp.getNetmask());
    		rsVSwitch.setGateway(rsVSwitchTmp.getGateway());
    		uri = "/cas/casrs/vswitch";
    		RestUtil.httpPut(client, uri, rsVSwitch, RsVSwitch.class);
    	} else {
    		log.error("���⽻���������ڡ�");
    	}
    }
    
    // /macPortTraffic/{id}/{mac}
    public static RsVPortTrafficInfo getMacPortTrafficData(Long id, String mac) {
    	String uri = "/cas/casrs/vm/macPortTraffic/" + id + "/" + mac;
    	RsVPortTrafficInfo rsVPortTrafficInfo = RestUtil.httpGet(client, uri, RsVPortTrafficInfo.class);
    	return rsVPortTrafficInfo;
    }
    
    /**
     * ����ģ��
     * @param templateName
     * @param localPath
     */
    public static void downLoadTempFile(String templateName, String localPath) {
    	String url = getCompressTemplateUrl(templateName);
    	DownLoadFileClient.downLoadFile(url, localPath);
    }
    
    public static String getCompressTemplateUrl(String templateName) {
    	String uri = "/cas/casrs/vmTemplate/compress/" + templateName;
    	return RestUtil.httpPut(client, uri, null, String.class);
    }
    
    public static void delVswitch(Long vsId) {
    	String uri = "/cas/casrs/vswitch/" + vsId;
    	RestUtil.httpDelete(client, uri);
    }
    
    public static RsVSwitch queryVswitch(Long vsId) {
    	String uri = "/cas/casrs/vswitch/query?vsId=" + vsId;
    	return RestUtil.httpGet(client, uri, RsVSwitch.class);
    }
    
    public static Long addVswitch(RsVSwitch rs) {
    	String uri = "/cas/casrs/vswitch";
    	return RestUtil.httpPost(client, uri, rs, Long.class);
    }
    
    public static void modifyVswitch(RsVSwitch rs) {
    	String uri = "/cas/casrs/vswitch";
    	RestUtil.httpPut(client, uri, rs, null);
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<RsDomainNetwork> queryNetwork(Long vmId) {
    	List<RsDomainNetwork> result = null;
    	String uri = "/cas/casrs/vm/" + vmId + "/network";
    	RsCommList rs = RestUtil.httpGet(client, uri, RsCommList.class);
    	if (rs != null) {
    		result = rs.getData();
    	}
    	return result;
    }
    
    public static void modifyNetwork(RsDomainForModify rs) {
    	String uri = "/cas/casrs/vm/modify";
    	RestUtil.httpPut(client, uri, rs, RsTaskMsg.class);
    }
    
    public static RsResult addNetwork(RsDomainForModify rs) {
    	String uri = "/cas/casrs/vm/addDevice";
    	return RestUtil.httpPut(client, uri, rs, RsResult.class);
    }
    
    public static RsTaskMsg deployTemplate(RsDomainDeploy domain) {
    	String uri = "/cas/casrs/vm/deploy";
    	RsTaskMsg rsTaskMsg = RestUtil.httpPost(client, uri, domain, RsTaskMsg.class);
    	return waitForComplete(rsTaskMsg.getMsgId());
    }
    
    public static void convertToDomain(RsDomainDeploy domain) {
		log.info("deploy start.........");
    	String uri = "/cas/casrs/vm/deploy";
    	RsTaskMsg rsTaskMsg = RestUtil.httpPost(client, uri, domain, RsTaskMsg.class);
    	RsTaskMsg task = waitForComplete(rsTaskMsg.getMsgId());
    	
//    	String msgUri = "/cas/casrs/message/" + rs.getMsgId();
//    	log.info("msgId:" + rs.getMsgId());
//    	RsTaskMsg msg = null;
//    	while (true) {
//    		msg = RestUtil.httpGet(client, msgUri, RsTaskMsg.class);
//        	if (msg.isCompleted()) {
//        		break;
//        	} else {
//        		try {
//					Thread.sleep(1000 * 6);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
//        	}
//    	}
    	log.info("deploy end.........");
    	if (task != null && task.getResult() == RsTaskMsg.SUCCESS) {
    		String delUri = "/cas/casrs/vmTemplate/deleteVmTemplate?id=" + domain.getId();
    		log.info("delete start.........");
    		RestUtil.httpDelete(client, delUri);
    		log.info("delete end.........");
    	}
    }
    public static void delDomainNetwork(RsDomainForModify domain) {
    	String uri = "/cas/casrs/vm/delDevice";
    	RestUtil.httpPut(client, uri, domain, null);
    }
    
    public static void checkMigrateDomain(DomainForMigrate domain) {
    	String uri = "/cas/casrs/vm/checkMigrate";
    	RestUtil.httpPut(client, uri, domain, DomainForMigrate.class);
    }
    
    /**
     * ����ά��ģʽ
     * @param rsHost
     * @return
     */
    public static RsTaskMsg intoMaintainStatus(RsHost rsHost) {
    	String uri = "/cas/casrs/host/into";
    	RsTaskMsg taskMsg = RestUtil.httpPut(client, uri, rsHost, RsTaskMsg.class);
    	RsTaskMsg task = waitForComplete(taskMsg.getMsgId());
		return task;
    }
    
    /**
     * �˳�ά��ģʽ
     * @param rsHost
     * @return
     */
    public static RsTaskMsg exitMaintainStatus(RsHost rsHost) {
    	String uri = "/cas/casrs/host/exit";
    	RsTaskMsg taskMsg = RestUtil.httpPut(client, uri, rsHost, RsTaskMsg.class);
    	RsTaskMsg task = waitForComplete(taskMsg.getMsgId());
		return task;
    }
    
    /**
     * ��ѯ������Ϣ
     * @param hostId
     * @return
     */
    public static List<RsHost> queryHostListByIds(List<Long> hostIds) {
    	if(hostIds == null) {
    		return null;
    	}
    	List<RsHost> rsHostList = new ArrayList<RsHost>();
    	for (Long hostId : hostIds) {
    		String uri = "/cas/casrs/host/id/" + hostId;
    		RsHost rsHost = RestUtil.httpGet(client, uri, RsHost.class);
    		rsHostList.add(rsHost);
    	}
    	return rsHostList;
    }
    /**
     * ȡ������
     * @param msgId
     * @return
     */
    public static RsResult cancelTask(Long msgId) {
    	String uri = "/cas/casrs/message/cancel/" + msgId;
    	RsResult result = RestUtil.httpPut(client, uri, null, RsResult.class);
		return result;
    }
    
    /**
     * �����洢��
     * @param hostId
     * @param hostName
     * @param poolName
     * @return
     */
    public static RsResult startStoragePool(Long hostId, String hostName, String poolName) {
    	String uri = "/cas/casrs/storage/start?id=" + hostId + "&hostName=" + hostName + "&poolName=" + poolName;
    	RsResult rsResult = RestUtil.httpGet(client, uri, RsResult.class);
    	return rsResult;
    }
    
    /**
     * ��ͣ�洢��
     * @param hostId
     * @param hostName
     * @param poolName
     * @param type
     * @return
     */
    public static RsResult stopStoragePool(Long hostId, String hostName, String poolName) {
    	String uri = "/cas/casrs/storage/stop?id=" + hostId + "&hostName=" + hostName + "&poolName=" + poolName;
    	RsResult rsResult = RestUtil.httpGet(client, uri, RsResult.class);
    	return rsResult;
    }
    
    /**
     * ɾ���洢��
     * @param hostId
     * @param hostName
     * @param poolName
     * @return
     */
    public static RsResult delStoragePool(Long hostId, String hostName, String poolName) {
    	String uri = "/cas/casrs/storage/delete?id=" + hostId + "&hostName=" + hostName + "&poolName=" + poolName;
    	RsResult rsResult = RestUtil.httpDelete(client, uri, RsResult.class);
    	return rsResult;
    }
    
    public static RsCommList<RsDomain> queryDomains(Integer offset, Integer limit) {
    	String casuri = "/cas/casrs/vm/domains?offset=" + offset + "&limit=" + limit;
    	@SuppressWarnings("unchecked")
		RsCommList<RsDomain> comlist = RestUtil.httpGet(client, casuri, RsCommList.class);
		return comlist;
    }
    
    /**
     * ��ѯ�洢�ػ�����Ϣ
     * @param hostId
     * @param poolName
     * @return
     */
    public static RsStoragePool queryStoragePoolInfo(Long hostId, String poolName) {
    	String uri = "/cas/casrs/storage/info?hostId=" + hostId + "&poolName=" + poolName;
    	RsStoragePool rsStoragePool = RestUtil.httpGet(client, uri, RsStoragePool.class);
    	return rsStoragePool;
    }
    /**
     * ��ѯ�����״̬��Ϣ
     * @param id
     * @return
     */
    public static RsDomainDetail queryDomainDetailInfo(Long id) {
    	String uri = "/cas/casrs/vm/detail/" + id;
    	RsDomainDetail rsStoragePool = RestUtil.httpGet(client, uri, RsDomainDetail.class);
    	return rsStoragePool;
    }
    /**
     * ��ѯ�������Ҫ��Ϣ
     * @param id
     * @return
     */
    @SuppressWarnings("unchecked")
	public static RsCommList<RsKeyValue> queryDomainSummary(Long id) {
    	String uri = "/cas/casrs/vm/" + id + "/summary";
    	RsCommList<RsKeyValue> rsStoragePool = RestUtil.httpGet(client, uri, RsCommList.class);
    	return rsStoragePool;
    }
    
    public static RsVncParam queryVncParam(Long id) {
    	String uri = "/cas/casrs/vmvnc/vnc/" + id;
    	RsVncParam vncp = RestUtil.httpGet(client, uri, RsVncParam.class);
    	return vncp;
    }
    
    /**
     * ��ȡLun��Ϣ
     * @return
     */
	public static RsCommList<RsFsLunInfo> queryIscsiLunList(Long hpId, String hostIp) {
    	Integer dataType = 0;
    	return queryIscsiLunList(hpId, hostIp, dataType);
    }
    
    /**
     * ��ȡLun��Ϣ
     * @return
     */
    @SuppressWarnings("unchecked")
	public static RsCommList<RsFsLunInfo> queryIscsiLunList(Long hpId, String hostIp, Integer dataType) {
    	String uri = "/cas/casrs/storage/iscsiLunList?hpId=" + hpId + "&hostIp=" + hostIp + "&dataType=" + dataType;
    	RsCommList<RsFsLunInfo> rsFsLunInfoCommList = RestUtil.httpGet(client, uri, RsCommList.class);
    	return rsFsLunInfoCommList;
    }
    
    /**
     * ��ѯ������洢����Ϣ
     */
    @SuppressWarnings("unchecked")
    public static RsCommList<RsDomainStorage> queryStorageInfo(Long vmId) {
    	String uri = "/cas/casrs/vm/" + vmId + "/storage";
    	RsCommList<RsDomainStorage> storages = RestUtil.httpGet(client, uri, RsCommList.class);
    	return storages;
    }
    /**
     * ���������ļ�ϵͳ
     * @param shareFile
     */
    public static void addShareFileStorage(RsShareFileSystem shareFile) {
    	String uri = "/cas/casrs/storage/shareFile/add";
    	RestUtil.httpPost(client, uri, shareFile, null);
    }
    
    /**
     * ���Ӵ洢��
     * @param rsStoragePool
     * @return
     */
    public static RsResult addStoragePool(RsStoragePool rsStoragePool) {
    	String uri = "/cas/casrs/storage/add";
    	RsResult rsResult = RestUtil.httpPost(client, uri, rsStoragePool, RsResult.class);
    	return rsResult;
    }
    
    /**
     * ����NAME��ѯ�����ļ�ϵͳ
     * @param rsStoragePool
     * @return
     */
    public static RsShareFileSystem queryShareFileStorageByName(String name) {
    	String uri = "/cas/casrs/storage/shareFile/queryByName?name=" + name;
    	RsShareFileSystem rsShareFileSystem = RestUtil.httpGet(client, uri, RsShareFileSystem.class);
    	return rsShareFileSystem;
    }
    
	/**
	 * ��չ�������CPU/�ڴ棩
	 * @param client
	 * @param rsDomain
	 */
	public static void extendCpuAndMem(Long vmId, Integer cpuSocket, Integer cpuCore, Double memoryInit, String memUnit) {
		RsDomainDetail rsDomainDetail = CasRestImpl.queryDomainDetailInfo(vmId);

		// �޸�CPU
		RsDomainForModify rsDomain = new RsDomainForModify();
		rsDomain.setId(vmId);
		rsDomain.setName(rsDomainDetail.getName());
		CpuConfig cpuConfig = rsDomainDetail.getCpuConfig();
		rsDomain.setCpuConfig(cpuConfig);
		
		cpuConfig.setCpuSockets(cpuSocket);
		cpuConfig.setCpuCores(cpuCore);
		editVm(rsDomain);
		
		// �޸��ڴ�
		rsDomain = new RsDomainForModify();
		rsDomain.setId(vmId);
		rsDomain.setName(rsDomainDetail.getName());
    	MemoryConfig memory = rsDomainDetail.getMemory();
    	rsDomain.setMemory(memory);
    	memory.setMemoryInit(memoryInit);
    	memory.setMemoryUnit(memUnit);
    	
    	Long memorySize = 0L;
        if ("MB".equals(memUnit)) {
        	memorySize = memoryInit.longValue();
        } else {
        	memorySize = (new Double(memoryInit * 1024)).longValue();
        }
    	
    	memory.setSize(memorySize);
    	editVm(rsDomain);
	}
	
	/**
	 * ���ݹ����ļ�ϵͳNAME��ѯ����洢�����������
	 * @param name
	 * @return
	 */
	public static RsSFSRelationVm queryRsSFSRelationVm(String name) {
		String uri = "/cas/casrs/storage/SFSRelationVm?name=" + name;
		RsSFSRelationVm rsSFSRelationVm = RestUtil.httpGet(client, uri, RsSFSRelationVm.class);
    	return rsSFSRelationVm;
    }
	
	/**
	 * ����Զ��ģ�嵽����洢���ϴ����������ģ�嵽�����ѹ��صĹ���洢��
	 */
	@SuppressWarnings("unchecked")
	public static void importTemple2ShareStoragePool(String templateFile){
		//�ϴ�ģ��
		String result = uploadVmTemple(templateFile);
		log.info(result);
		if (result.startsWith(SUCCESS)) {
			
			String templateName = result.substring(SUCCESS.length(), result.length() - 1);
			log.info("templateName:"+templateName);
			//��ѯģ��������Ϣ
			String uri = "/cas/casrs/vmTemplate/network/" + templateName;
			RsCommList<RsTemplateNetwork> list = RestUtil.httpGet(client, uri, RsCommList.class);
			if (list != null && list.getData() != null && list.getData().size() > 0) {
				//��ѯcvm���������ģ����Ϣ
				List<PortProfile> portProfileList = queryProfileList();
				PortProfile portProfile = portProfileList.get(0);
				
				List<RsDomainNetwork> networks = new ArrayList<RsDomainNetwork>();
				for (RsTemplateNetwork rsTemplateNetwork : list.getData()) {
					RsDomainNetwork network = new RsDomainNetwork();
					network.setMac(rsTemplateNetwork.getMac());
					network.setMode(rsTemplateNetwork.getMode());
					if (portProfile != null && portProfile.getId() != null) {
						network.setProfileId(portProfile.getId());
					} else {
						network.setProfileId(1L);
					}
					if (portProfile != null && portProfile.getName() != null) {
						network.setProfileName(portProfile.getName());
					} else {
						network.setProfileName("Default");
					}
					networks.add(network);
				}
				RsCommList<RsDomainNetwork> commnList = new RsCommList<RsDomainNetwork>(networks);
				uri = "/cas/casrs/vmTemplate/modifyTemplateProfile/" + templateName;
				RestUtil.httpPut(client, uri, commnList, null);
			}
		} else {
			if (result.startsWith(UPLOAD_FILE_MORE_THAN_MAX_LIMIT)) {
				log.error("�ϴ��ļ������������200G��");
			} else if (result.startsWith(UPLOAD_FILE_FORMAT_ERROR)) {
				log.error("�ļ���ʽ����ȷ��");
			} else if (result.startsWith(TEMPLATE_EXISTS)) {
				log.error("ģ���Ѿ����ڡ�");
			} else if (result.startsWith(TEMPLATE_FILE_EXISTS)) {
				log.error("ģ���ļ��Ѿ����ڡ�");
			} else if (result.startsWith(SERVER_UNKNOW_ERROR)) {
				log.error("����������");
			}
		}
	}
	
	private static String uploadVmTemple(String localFile) {
		String result = "";
		HttpParams params = client.getParams();
		HttpHost defaultHost = (HttpHost)params.getParameter(ClientPNames.DEFAULT_HOST);
		String protocol = defaultHost.getSchemeName();
		String host = defaultHost.getHostName();
		int port = defaultHost.getPort();
		HttpURLConnection conn = null;
		String urlPart = "/cas/vmTempateUpload?type=cic";
		String BOUNDARY = "---------------------------123821742118716";
		try {
			StringBuilder urlStr = new StringBuilder();
			urlStr.append(protocol).append("://");
			urlStr.append(host).append(":").append(port);
			urlStr.append(urlPart);
			
			URL url = new URL(urlStr.toString());  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(30000);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY); 
			
            OutputStream out = new DataOutputStream(conn.getOutputStream()); 
            File file = new File(localFile);  
            String filename = file.getName();  
            MagicMatch match = Magic.getMagicMatch(file, false, true);  
            String contentType = match.getMimeType();  

            StringBuffer strBuf = new StringBuffer();  
            strBuf.append("\r\n").append("--").append(BOUNDARY).append("\r\n");  
            strBuf.append("Content-Disposition: form-data; name=\"file\"; filename=\"" + filename + "\"\r\n");  
            strBuf.append("Content-Type:" + contentType + "\r\n\r\n");  
            out.write(strBuf.toString().getBytes());  
            
            DataInputStream in = new DataInputStream(new FileInputStream(file));  
            int bytes = 0;  
            byte[] bufferOut = new byte[1024];  
            while ((bytes = in.read(bufferOut)) != -1) {  
                out.write(bufferOut, 0, bytes);  
            }  
            in.close();
			
			byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
            out.write(endData);  
            out.flush();  
            out.close();
			
            // ��ȡ��������    
            strBuf = new StringBuffer();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
                strBuf.append(line).append("\n");  
            }  
            result = strBuf.toString();
            reader.close();  
            reader = null; 
		} catch (MalformedURLException e) {
			log.error(e);
		} catch (IOException e) {
			log.error(e);
		} catch (MagicParseException e) {
			log.error(e);
		} catch (MagicMatchNotFoundException e) {
			log.error(e);
		} catch (MagicException e) {
			log.error(e);
		} finally {  
            if (conn != null) {  
                conn.disconnect();  
                conn = null;  
            }  
        }
		return result;
	}

	/**
	 * ifconfig eth down
	 * @param id
	 * @param mac
	 * @return
	 */
	public static RsResult downMac(Long id, String mac) {
		String uri = "/cas/casrs/castools/downMac/" + id + "/" + mac;
    	RsResult rsResult = RestUtil.httpGet(client, uri, RsResult.class);
		return rsResult;
	}
	/**
	 * ��ѯ������Ϣ
	 * @param domainId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static RsCommList<RsKeyValue> queryDomainDiskIOAndNetIO(Long domainId) {
    	String uri = "/cas/casrs/vm/diskIOAndNetIO/" + domainId;
    	RsCommList<RsKeyValue> rsCommList = RestUtil.httpGet(client, uri, RsCommList.class);
    	return rsCommList;
    }
	
	/**
	 * ��ѯ������Ϣ
	 * @param domainId
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<OsNetwork> getOsNetwork(Long domainId) {
		List<OsNetwork> result = null;
		String uri = "/cas/casrs/castools/onNetwork/" + domainId;
    	RsCommList<OsNetwork> rsCommList = RestUtil.httpGet(client, uri, RsCommList.class);
    	if (rsCommList != null) {
    		result = rsCommList.getData();
    	}
    	return result;
    }
	
	/**
	 * ��ѯ�������״̬��Ϣ
      ��IO������ƽ��IO���ʡ����IO���ʡ�ʵʱIO���ʣ�
	 * @return  �洢��ʵ����
	 */
	public static List<RsKeyValue> getNetStatusInfo(Long vmId, String mac) {
		List<RsKeyValue> result = null;
		String uri = "/cas/casrs/vm/netIOStatus?id=" + vmId + "&mac=" + mac;
		@SuppressWarnings("unchecked")
		RsCommList<RsKeyValue> comList = RestUtil.httpGet(client, uri, RsCommList.class);
		if (comList != null) {
			result = comList.getData();
		}
        return result;
	}
}
