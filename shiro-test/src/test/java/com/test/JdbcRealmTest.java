package com.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Test;

import com.alibaba.druid.pool.DruidDataSource;

public class JdbcRealmTest {

	DruidDataSource dataSource = new DruidDataSource();
	{
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://localhost:3306/shiro");
		dataSource.setUsername("root");
		dataSource.setPassword("123456");
	}
	
	@Test
	public void testAuthentication(){
		JdbcRealm jdbcRealm = new JdbcRealm();
		jdbcRealm.setDataSource(dataSource);
		jdbcRealm.setPermissionsLookupEnabled(true);  //设置权限开关
		
		/*String authentication_query = "select password from users_test where username = ?";
		jdbcRealm.setAuthenticationQuery(authentication_query);
		
		String user_role_query = "select role_name from user_roles_test where username = ?";
		jdbcRealm.setUserRolesQuery(user_role_query);
		
		String permission_query = "select permission from roles_permissions_test where role_name = ?";
		jdbcRealm.setPermissionsQuery(permission_query);*/
		
		DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
		defaultSecurityManager.setRealm(jdbcRealm);
		
		SecurityUtils.setSecurityManager(defaultSecurityManager);
		Subject subject = SecurityUtils.getSubject();
		
		UsernamePasswordToken token = new UsernamePasswordToken("chen", "123");
		subject.login(token);
		subject.checkRole("admin");
		subject.checkPermission("user:update");
	}
	
	@After
	public void tearDown(){
		ThreadContext.unbindSubject();
	}
}
