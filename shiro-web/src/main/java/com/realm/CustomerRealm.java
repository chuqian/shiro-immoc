package com.realm;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import com.dao.UserDao;
import com.vo.User;

public class CustomerRealm extends AuthorizingRealm {
	
	@Resource
	private UserDao userDao;

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String username = (String)principals.getPrimaryPrincipal();
		Set<String> roles = getRolesByUsername(username);
		Set<String> permissions = getPermissionsByUsername(username);
		
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		authorizationInfo.setRoles(roles);
		authorizationInfo.setStringPermissions(permissions);
		
		return authorizationInfo;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		String username = (String)token.getPrincipal();
		String password = getPasswordByUsername(username);

		if(password == null)
			return null;
		
		SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, password, "customRealm");
		authenticationInfo.setCredentialsSalt(ByteSource.Util.bytes("salt"));
		return authenticationInfo;
	}

	//从数据库获取凭证
	private String getPasswordByUsername(String username) {
		User user = userDao.getUserByUsername(username);
		if(user != null)
			return user.getPassword();
		return null;
	}

	//从数据库或缓存中获取角色 
	public Set<String> getRolesByUsername(String username) {
		System.out.println("从数据库中获取角色信息");
		Set<String> set = new HashSet<>();
		set = userDao.getRolesByUsername(username);
		if(set.isEmpty())
			return null;
		return set;
	}

	//用于模拟从数据库或缓存中获取权限信息
	public Set<String> getPermissionsByUsername(String username) {
		System.out.println("从数据库中获取权限信息");
		Set<String> set = new HashSet<>();
		set.add("user:add");
		set.add("user:update");
		return set;
	}

	
	public static void main(String[] args) {
		Md5Hash md5Hash = new Md5Hash("123", "salt");
		System.out.println(md5Hash.toString());
	}
}
