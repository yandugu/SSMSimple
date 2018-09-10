package ssm.blog.service.impl;

import org.springframework.stereotype.Service;
import ssm.blog.dao.BlogTypeDao;
import ssm.blog.pojo.BlogType;
import ssm.blog.pojo.PageBean;
import ssm.blog.service.BlogTypeService;

import javax.annotation.Resource;

/**
 * Created by xp on 2017/4/14.
 * @author xp
 * @Description �������service�ӿ�ʵ����
 */
@Service
public class BlogTypeServiceImpl implements BlogTypeService{

    @Resource
    private BlogTypeDao blogTypeDao;

    public PageBean<BlogType> listByPage(PageBean<BlogType> pageBean) {
        //��ѯ��ҳ���
        pageBean.setResult(blogTypeDao.listByPage(pageBean.getStart(),pageBean.getEnd()));
        //��ѯ��¼����
        pageBean.setTotal(blogTypeDao.getTotal());
        return pageBean;
    }
    
    public Long getTotal() {
        return blogTypeDao.getTotal();
    }

    public Integer addBlogType(BlogType blogType) {
        return blogTypeDao.addBlogType(blogType);
    }

    public Integer updateBlogType(BlogType blogType) {
        return blogTypeDao.updateBlogType(blogType);
    }

    public Integer deleteBlogType(Integer id) {
        return blogTypeDao.deleteBlogType(id);
    }
}
