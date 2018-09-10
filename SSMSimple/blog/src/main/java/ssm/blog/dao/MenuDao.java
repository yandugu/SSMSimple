package ssm.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm.blog.pojo.Menu;

public interface MenuDao {
	/**
	 * 
	 * 添加新菜单
	 * */
	Integer addMenu(Menu menu);
	
	/**
	 * 
	 * 删除菜单(菜单状态置为不可用)
	 * */
	Integer deleteMenu(Integer id);
	
	/**
     * 更新菜单信息
     * @param menu
     * @return
     */
    Integer updateMenu(Menu menu);

    /**
     * 根据id查询菜单信息
     * @param id
     * @return
     */
    Menu getMenuById(Integer id);
    
    /**
     * 获取所有非叶子结点菜单
     * 
     * */
    List<Menu> getNotLeafMenu();

    /**
     * 分页查询菜单信息
     * @param start
     * @param end
     * @return
     */
    List<Menu> listByPage(@Param("start") Integer start, @Param("end") Integer end);

    /**
     * 查询总记录数
     * @return
     */
    Long getTotal();
}
