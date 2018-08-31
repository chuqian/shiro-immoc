package com.dao;

import java.util.Set;

import com.vo.User;

public interface UserDao {

	User getUserByUsername(String username);

	Set<String> getRolesByUsername(String username);
}
