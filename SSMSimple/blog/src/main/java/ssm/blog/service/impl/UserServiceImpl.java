package ssm.blog.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssm.blog.dao.UserDao;
import ssm.blog.pojo.User;
import ssm.blog.service.UserService;
//@Service("userService")
@Service
public class UserServiceImpl implements UserService{

	//@Resource
	@Autowired
	private UserDao userDao;
	
	public Integer getUserInfo(User user) {
		// TODO Auto-generated method stub
		return userDao.getUserInfo(user);
	}

	public User findUserByName(String username) {
		// TODO Auto-generated method stub
		return userDao.findUserByName(username);
	}

	//@Override
	public void updateUserById(User user) {
		// TODO Auto-generated method stub
		userDao.updateUserById(user);
	}

}
