package com.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

import com.realm.CustomRealm;

public class CustomRealmTest {

	@Test
	public void testCustomRealm(){
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("md5");
		
		CustomRealm customRealm = new CustomRealm();
		customRealm.setCredentialsMatcher(matcher);
		
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(customRealm);
		
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("chen", "123");
		subject.login(token);
		subject.checkRoles("admin", "user");
		subject.checkPermissions("user:add", "user:update");
		
		subject.logout();
	}
	
	@After
	public void tearDown(){
		ThreadContext.unbindSubject();
	}
}
