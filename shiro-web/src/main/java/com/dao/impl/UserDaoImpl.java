package com.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import com.dao.UserDao;
import com.vo.User;

@Repository
public class UserDaoImpl implements UserDao {

	@Resource
	JdbcTemplate jdbcTemplate;
	
	@Override
	public User getUserByUsername(String username) {
		String sql = "select username,password from user where username = ?";
		List<User> list = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<User>(){
			@Override
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				return user;
			}
		});
		if(CollectionUtils.isEmpty(list))
			return null;
		return list.get(0);
	}

	@Override
	public Set<String> getRolesByUsername(String username) {
		String sql = "select  roleName from user_role where username = ?";
		List<String> list = jdbcTemplate.query(sql, new String[]{username}, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				return rs.getString("roleName");
			}
		});
		return new HashSet<String>(list);
	}

}
