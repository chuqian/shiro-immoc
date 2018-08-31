package com.realm;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

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

public class CustomRealm extends AuthorizingRealm {
	
	Map<String, String> map = new HashMap<>();
	{
		map.put("chen", new Md5Hash("123", "salt").toString());
		super.setName("customRealm");
	}

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

	//用于模拟从数据库获取凭证操作 
	private String getPasswordByUsername(String username) {
		return map.get(username);
	}

	//用于模拟从数据库或缓存中获取角色 
	public Set<String> getRolesByUsername(String username) {
		Set<String> set = new HashSet<>();
		set.add("admin");
		set.add("user");
		return set;
	}

	//用于模拟从数据库或缓存中获取权限信息
	public Set<String> getPermissionsByUsername(String username) {
		Set<String> set = new HashSet<>();
		set.add("user:add");
		set.add("user:update");
		return set;
	}

	
	/*public static void main(String[] args) {
		Md5Hash md5Hash = new Md5Hash("123", "salt");
		System.out.println(md5Hash.toString());
	}*/
}
