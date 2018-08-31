package com.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

public class IniRealmTest {

	@Test
	public void testIniRealm(){
		IniRealm iniRealm = new IniRealm("classpath:user.ini");
		
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(iniRealm);
		
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("chen", "123");
		subject.login(token);
		subject.checkRole("admin");
		subject.checkPermissions("user:update","user:delete");
	}
	
	@After
	public void tearDown(){
		ThreadContext.unbindSubject();
	}
}
