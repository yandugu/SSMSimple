package ssm.blog.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ssm.blog.dao.MenuDao;
import ssm.blog.pojo.Menu;
import ssm.blog.pojo.PageBean;
import ssm.blog.service.MenuService;


@Service
public class MenuServiceImpl implements MenuService {
	
	@Resource
	private MenuDao menuDao;

	//@Override
	public PageBean<Menu> listByPage(PageBean<Menu> pageBean) {
		// TODO Auto-generated method stub
		//查询分页结果
        pageBean.setResult(menuDao.listByPage(pageBean.getStart(),pageBean.getEnd()));
        //查询记录总数
        pageBean.setTotal(menuDao.getTotal());
        return pageBean;
	}

	//@Override
	public Integer addMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.addMenu(menu);
	}

	//@Override
	public Integer updateMenu(Menu menu) {
		// TODO Auto-generated method stub
		return menuDao.updateMenu(menu);
	}

	//@Override
	public Integer deleteMenu(Integer id) {
		// TODO Auto-generated method stub
		return menuDao.deleteMenu(id);
	}

	//@Override
	public Long getTotal() {
		// TODO Auto-generated method stub
		return menuDao.getTotal();
	}

	//@Override
	public List<Menu> getNotLeafMenu() {
		// TODO Auto-generated method stub
		return menuDao.getNotLeafMenu();
	}

}
