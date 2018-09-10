package ssm.blog.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import ssm.blog.pojo.BlogType;
import ssm.blog.pojo.Blogger;

import java.util.List;

/**
 * Created by lmh on 2018/5/25.
 * @author lmh
 * @Description �������dao
 */
@Repository
public interface BlogTypeDao {

    /**
     * ��Ӳ��������Ϣ
     * @param blogType
     * @return
     */
    Integer addBlogType(BlogType blogType);

    /**
     * ɾ�����������Ϣ
     * @param id
     * @return
     */
    Integer deleteBlogType(Integer id);

    /**
     * ���²��������Ϣ
     * @param blogType
     * @return
     */
    Integer updateBlogType(BlogType blogType);

    /**
     * ����id��ѯ���������Ϣ
     * @param id
     * @return
     */
    BlogType getById(Integer id);

    /**
     * ��ҳ��ѯ���������Ϣ
     * @param start
     * @param end
     * @return
     */
    List<BlogType> listByPage(@Param("start") Integer start, @Param("end") Integer end);

    /**
     * ��ѯ�ܼ�¼��
     * @return
     */
    Long getTotal();

}

