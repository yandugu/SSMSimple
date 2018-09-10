package ssm.blog.service;

import ssm.blog.pojo.User;

public interface UserService {
	Integer getUserInfo(User user);
	
	User findUserByName(String username);
	
	void updateUserById(User user);
}
