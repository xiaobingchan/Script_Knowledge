import org.junit.Test;

import com.vm.CasRestImpl;

public class AuthRestTest {
	
	@Test
	public void login() {
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

	@Test
	public void logout() {
		CasRestImpl.logout();
	}
}