package com.test;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AuthenticationTest {
	
	SimpleAccountRealm realm = new SimpleAccountRealm();
	
	@Before
	public void addUser(){
		realm.addAccount("chen", "123", "admin", "user");
	}
	
	@Test
	public void testAuthentication(){
		//构建SecurityManager环境
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(realm);
		
		//主体提交认证请求
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("chen", "123");
		subject.login(token);
		System.out.println(subject.isAuthenticated());
		
		subject.checkRoles("admin", "user");
		
		subject.logout();
		System.out.println(subject.isAuthenticated());
	}
	
	@After
	public void tearDown(){
		ThreadContext.unbindSubject();
	}
}
