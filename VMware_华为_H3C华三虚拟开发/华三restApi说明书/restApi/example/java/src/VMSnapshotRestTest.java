import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vm.CasRestImpl;
import com.vm.entity.RsSnapshot;
import com.vm.entity.RsTaskMsg;
import com.vm.util.AppException;


public class VMSnapshotRestTest {
	/** ��־��¼���� */
    private static Log log = LogFactory.getLog(VMSnapshotRestTest.class);
    
	/**
	 * IF_33/CT-57
	 * ������������ղ���
	 */
    @Test
	public void createVMSnapshot(){
		log.info("***********������������ղ���************");
		
		try {
			RsSnapshot rs=new RsSnapshot();
			rs.setVmId(5082l);
			rs.setName("CreateSnapshotTest_04");
			rs.setDesc("test4 snapshot");
			RsTaskMsg task = CasRestImpl.createVMSnapshot(rs);
			if (task != null) {
				log.info("�������������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("***********�������������end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * IF_33/CT-58
	 * ɾ����������ղ���
	 */
    @Test
	public void deleteVMSnapshot(){
    	log.info("***********ɾ����������ղ���begin************");
		try {
			RsSnapshot rs=new RsSnapshot();
			rs.setVmId(5082l);
			rs.setName("CreateSnapshotTest_04");
			RsTaskMsg task = CasRestImpl.deleteVMSnapshot(rs);
			if (task != null) {
				log.info("ɾ�����������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("***********ɾ����������ղ���end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * CT-59
	 * �޸����������
	 */
    @Test
	public void modifySnapshot() {
    	log.info("***********�޸����������begin************");
		try {
			Long vmId = 5082L;
			String snapNameOld = "a123";
			String snapNameNew = "a345";
			String desc = "sss";
			RsTaskMsg task = CasRestImpl.modifyVmSnapshot(vmId, snapNameOld, snapNameNew, desc);
			if (task != null) {
				log.info("�޸����������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("***********�޸����������end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * CT-60
	 * �������������
	 */
    @Test
	public void backupVmSnapshot() {
    	log.info("***********�������������begin************");
		try {
			RsSnapshot snapshot = new RsSnapshot();
			snapshot.setVmId(5082L);
			snapshot.setName("kaka666");
			RsTaskMsg task = CasRestImpl.backupVmSnapshot(snapshot);
			if (task != null) {
				log.info("�������������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("***********�������������end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * IF_33/CT-61
	 * ��ԭ��������ղ���
	 */
    @Test
	public void resumeVMSnapshot(){
		try {
			log.info("***********��ԭ��������ղ���begin************");
			RsSnapshot rs=new RsSnapshot();
			rs.setVmId(5082L);
			rs.setName("a345");
			RsTaskMsg task = CasRestImpl.resumeVMSnapshot(rs);
			if (task != null) {
				log.info("��ԭ���������" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
			}
			log.info("***********��ԭ���������end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * IF_33/CT-62
	 * ��ѯָ�������������Ϣ����
	 */
    @Test
	public void loadVMSnapshotTest(){
    	log.info("***********��ѯָ������������б�************");
		try {
			Long vmId = 5082L;
			List<RsSnapshot> result = CasRestImpl.loadVMSnapshot(vmId);
			if (result != null) {
				for (RsSnapshot snap : result) {
					printSnapshot(snap);
				}
			}
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
	
	private void printSnapshot(RsSnapshot snap) {
		log.info("�������ƣ�" + snap.getName() + "����:" + snap.getDesc());
		if (snap.getSnapshots() != null && snap.getSnapshots().getData() != null) {
			for (RsSnapshot child : snap.getSnapshots().getData()) {
				printSnapshot(child);
			}
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
