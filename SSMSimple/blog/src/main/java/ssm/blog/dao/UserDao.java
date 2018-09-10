package ssm.blog.dao;
import org.apache.ibatis.annotations.Param;

import ssm.blog.pojo.User;


public interface UserDao {
	/**
     * ���²��������Ϣ
     * @param User
     * @return
     */
    Integer getUserInfo(User user);
    
    User findUserByName(String username);
    
    void updateUserById(User user);
}
