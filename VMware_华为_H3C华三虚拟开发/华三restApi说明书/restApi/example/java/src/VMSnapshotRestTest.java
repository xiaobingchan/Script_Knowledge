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
	/** 日志记录对象。 */
    private static Log log = LogFactory.getLog(VMSnapshotRestTest.class);
    
	/**
	 * IF_33/CT-57
	 * 创建虚拟机快照测试
	 */
    @Test
	public void createVMSnapshot(){
		log.info("***********创建虚拟机快照测试************");
		
		try {
			RsSnapshot rs=new RsSnapshot();
			rs.setVmId(5082l);
			rs.setName("CreateSnapshotTest_04");
			rs.setDesc("test4 snapshot");
			RsTaskMsg task = CasRestImpl.createVMSnapshot(rs);
			if (task != null) {
				log.info("创建虚拟机快照" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("***********创建虚拟机快照end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * IF_33/CT-58
	 * 删除虚拟机快照测试
	 */
    @Test
	public void deleteVMSnapshot(){
    	log.info("***********删除虚拟机快照测试begin************");
		try {
			RsSnapshot rs=new RsSnapshot();
			rs.setVmId(5082l);
			rs.setName("CreateSnapshotTest_04");
			RsTaskMsg task = CasRestImpl.deleteVMSnapshot(rs);
			if (task != null) {
				log.info("删除虚拟机快照" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("***********删除虚拟机快照测试end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * CT-59
	 * 修改虚拟机快照
	 */
    @Test
	public void modifySnapshot() {
    	log.info("***********修改虚拟机快照begin************");
		try {
			Long vmId = 5082L;
			String snapNameOld = "a123";
			String snapNameNew = "a345";
			String desc = "sss";
			RsTaskMsg task = CasRestImpl.modifyVmSnapshot(vmId, snapNameOld, snapNameNew, desc);
			if (task != null) {
				log.info("修改虚拟机快照" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("***********修改虚拟机快照end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * CT-60
	 * 备份虚拟机快照
	 */
    @Test
	public void backupVmSnapshot() {
    	log.info("***********备份虚拟机快照begin************");
		try {
			RsSnapshot snapshot = new RsSnapshot();
			snapshot.setVmId(5082L);
			snapshot.setName("kaka666");
			RsTaskMsg task = CasRestImpl.backupVmSnapshot(snapshot);
			if (task != null) {
				log.info("备份虚拟机快照" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("***********备份虚拟机快照end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
    /**
	 * IF_33/CT-61
	 * 还原虚拟机快照测试
	 */
    @Test
	public void resumeVMSnapshot(){
		try {
			log.info("***********还原虚拟机快照测试begin************");
			RsSnapshot rs=new RsSnapshot();
			rs.setVmId(5082L);
			rs.setName("a345");
			RsTaskMsg task = CasRestImpl.resumeVMSnapshot(rs);
			if (task != null) {
				log.info("还原虚拟机快照" + (task.getResult() == RsTaskMsg.SUCCESS ? "成功" : "失败"));
			}
			log.info("***********还原虚拟机快照end************");
		} catch (AppException ae) {
			log.error(ae.getMessage());
		}
	}
    
	/**
	 * IF_33/CT-62
	 * 查询指定虚拟机快照信息测试
	 */
    @Test
	public void loadVMSnapshotTest(){
    	log.info("***********查询指定虚拟机快照列表************");
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
		log.info("快照名称：" + snap.getName() + "描述:" + snap.getDesc());
		if (snap.getSnapshots() != null && snap.getSnapshots().getData() != null) {
			for (RsSnapshot child : snap.getSnapshots().getData()) {
				printSnapshot(child);
			}
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
