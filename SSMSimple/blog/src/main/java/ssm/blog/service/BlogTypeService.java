package ssm.blog.service;

import org.springframework.stereotype.Service;
import ssm.blog.pojo.BlogType;
import ssm.blog.pojo.PageBean;

/**
 * Created by xp on 2017/4/14.
 * @author xp
 * @Description �������service�ӿ�
 */
public interface BlogTypeService {

    //��ҳ��ѯ
    PageBean<BlogType> listByPage(PageBean<BlogType> pageBean);

    // ��Ӳ������
    public Integer addBlogType(BlogType blogType);

    // ���²������
    public Integer updateBlogType(BlogType blogType);

    // ɾ���������
    public Integer deleteBlogType(Integer id);
}