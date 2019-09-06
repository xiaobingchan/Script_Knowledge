import org.junit.Test;

import com.vm.CasRestImpl;

public class AuthRestTest {
	
	@Test
	public void login() {
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

	@Test
	public void logout() {
		CasRestImpl.logout();
	}
}