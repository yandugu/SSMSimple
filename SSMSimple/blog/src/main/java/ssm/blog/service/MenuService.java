package ssm.blog.service;

import java.util.List;

import ssm.blog.pojo.Menu;
import ssm.blog.pojo.PageBean;

public interface MenuService {
	//分页查询
    PageBean<Menu> listByPage(PageBean<Menu> pageBean);

    // 添加博客类别
    public Integer addMenu(Menu menu);

    // 更新博客类别
    public Integer updateMenu(Menu menu);

    // 删除博客类别
    public Integer deleteMenu(Integer id);
    
    public List<Menu> getNotLeafMenu();
    
    /**
     * 查询总记录数
     * @return
     */
    public Long getTotal();
}
