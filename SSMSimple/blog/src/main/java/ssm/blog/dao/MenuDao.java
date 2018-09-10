package ssm.blog.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import ssm.blog.pojo.Menu;

public interface MenuDao {
	/**
	 * 
	 * ����²˵�
	 * */
	Integer addMenu(Menu menu);
	
	/**
	 * 
	 * ɾ���˵�(�˵�״̬��Ϊ������)
	 * */
	Integer deleteMenu(Integer id);
	
	/**
     * ���²˵���Ϣ
     * @param menu
     * @return
     */
    Integer updateMenu(Menu menu);

    /**
     * ����id��ѯ�˵���Ϣ
     * @param id
     * @return
     */
    Menu getMenuById(Integer id);
    
    /**
     * ��ȡ���з�Ҷ�ӽ��˵�
     * 
     * */
    List<Menu> getNotLeafMenu();

    /**
     * ��ҳ��ѯ�˵���Ϣ
     * @param start
     * @param end
     * @return
     */
    List<Menu> listByPage(@Param("start") Integer start, @Param("end") Integer end);

    /**
     * ��ѯ�ܼ�¼��
     * @return
     */
    Long getTotal();
}
