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

	/** 日志记录对象。 */
    private static Log log = LogFactory.getLog(ClusterRestTest.class);
	/**
	 * HA(启用/禁用/配置)
	 * @param rsclient
	 */
	@Test
	public void editHa() {
		log.info("***********HA(启用/禁用/配置)测试************");
		try {
			RsCluster rsCluster = new RsCluster();
			rsCluster.setId(1L);
			rsCluster.setEnableHA(0);
			rsCluster.setPriority(2);
			rsCluster.setHaMinHost(1);
			RsTaskMsg task = CasRestImpl.editHa(rsCluster);
			if (task != null) {
				log.info("HA(启用/禁用/配置)" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
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
