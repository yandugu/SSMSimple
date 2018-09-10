package ssm.blog.service;

import org.springframework.stereotype.Service;
import ssm.blog.pojo.Blogger;

/**
 * Created by xp on 2017/4/13.
 */

public interface BloggerService {
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
