package ssm.blog.service;

import java.util.List;

import ssm.blog.pojo.Menu;
import ssm.blog.pojo.PageBean;

public interface MenuService {
	//��ҳ��ѯ
    PageBean<Menu> listByPage(PageBean<Menu> pageBean);

    // ��Ӳ������
    public Integer addMenu(Menu menu);

    // ���²������
    public Integer updateMenu(Menu menu);

    // ɾ���������
    public Integer deleteMenu(Integer id);
    
    public List<Menu> getNotLeafMenu();
    
    /**
     * ��ѯ�ܼ�¼��
     * @return
     */
    public Long getTotal();
}
