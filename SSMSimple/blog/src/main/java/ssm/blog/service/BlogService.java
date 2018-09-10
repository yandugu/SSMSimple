package ssm.blog.service;

import java.util.List;
import java.util.Map;

import ssm.blog.pojo.Blog;
import ssm.blog.pojo.PageBean;

/**
 * @author xp
 * @Description ����Service�ӿ�
 */
public interface BlogService {


    // ��ҳ��ѯ����
    public List<Blog> listBlog(Map<String,Object> map);

    // ��ҳ��ѯ����
    public PageBean<Blog> listBlog(String title,PageBean<Blog> pageBean);

    // ���ݲ������͵�id��ѯ�������µĲ�������
    public Integer getBlogByTypeId(Integer typeId);

    //��Ӳ���
    public Integer saveBlog(Blog blog);

    //���²���
    public Integer updateBlog(Blog blog);

    //ͨ��idɾ������
    public Integer deleteBlog(Integer id);

    //ͨ��id��ȡ����
    public Blog getById(Integer id);

    long getTotal(Map<String, Object> map);

}
