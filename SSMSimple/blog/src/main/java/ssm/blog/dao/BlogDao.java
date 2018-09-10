package ssm.blog.dao;

import java.util.List;
import java.util.Map;

import ssm.blog.pojo.Blog;

/**
 * @Description ����Dao�ӿ�
 * @author xp
 *
 */
public interface BlogDao {


    // ��ҳ��ѯ����
    public List<Blog> listBlog(Map<String, Object> map);

    // ��ȡ�ܼ�¼��
    public Long getTotal(Map<String ,Object> map);

    // ���ݲ������͵�id��ѯ�������µĲ�������
    public Integer getBlogByTypeId(Integer typeId);

    //��Ӳ���
    public Integer saveBlog(Blog blog);

    //���²���
    public Integer updateBlog(Blog blog);

      //ɾ������
    public Integer deleteBlog(Integer id);

    //ͨ��id��ȡ����
    public Blog getById(Integer id);
}