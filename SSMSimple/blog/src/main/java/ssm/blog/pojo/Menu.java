package ssm.blog.pojo;

import java.io.Serializable;

public class Menu implements Serializable {
	private Integer id;
	private String menuName;
	private String menuUrl;
	private boolean isLeaf;
	private Integer parentId;
	private boolean status;
	private Integer sort;

	public Menu() {	
	}
	
	public Menu(String menuName, String menuUrl, boolean isLeaf, Integer parentId, boolean status, Integer sort) {
		this.menuName = menuName;
		this.menuUrl = menuUrl;
		this.isLeaf = isLeaf;
		this.parentId = parentId;
		this.status = status;
		this.sort = sort;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuUrl() {
		return menuUrl;
	}
	public void setMenuUrl(String menuUrl) {
		this.menuUrl = menuUrl;
	}
	public boolean getIsLeaf() {
		return isLeaf;
	}
	public void setIsLeaf(boolean isLeaf) {
		this.isLeaf = isLeaf;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
