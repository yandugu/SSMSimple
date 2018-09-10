package ssm.blog.dao;

import org.springframework.stereotype.Repository;
import ssm.blog.pojo.Blogger;

/**
 * Created by lmh on 2018/5/25.
 * ����dao�ӿ�
 */
@Repository //ע��Ϊ�־ò��bean
public interface BloggerDao {
    /**
     * ��ѯ������Ϣ
     * @return
     */
    Blogger getBloggerData();
    
    /**
     * ͨ���û�����ѯ������Ϣ
     * @param username
     * @return
     */
    Blogger getBloggerByName(String username);

    /**
     * ���²�����Ϣ
     * @param blogger
     * @return
     */
    Integer updateBlogger(Blogger blogger);
}
