package ssm.blog.dao;
import org.apache.ibatis.annotations.Param;

import ssm.blog.pojo.User;


public interface UserDao {
	/**
     * 更新博客类别信息
     * @param User
     * @return
     */
    Integer getUserInfo(User user);
    
    User findUserByName(String username);
    
    void updateUserById(User user);
}
