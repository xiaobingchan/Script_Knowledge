import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.vm.CasRestImpl;
import com.vm.entity.RsCluster;
import com.vm.entity.RsTaskMsg;
import com.vm.util.AppException;


public class ClusterRestTest {

	/** ��־��¼���� */
    private static Log log = LogFactory.getLog(ClusterRestTest.class);
	/**
	 * HA(����/����/����)
	 * @param rsclient
	 */
	@Test
	public void editHa() {
		log.info("***********HA(����/����/����)����************");
		try {
			RsCluster rsCluster = new RsCluster();
			rsCluster.setId(1L);
			rsCluster.setEnableHA(0);
			rsCluster.setPriority(2);
			rsCluster.setHaMinHost(1);
			RsTaskMsg task = CasRestImpl.editHa(rsCluster);
			if (task != null) {
				log.info("HA(����/����/����)" + (task.getResult() == RsTaskMsg.SUCCESS ? "�ɹ�" : "ʧ��"));
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
