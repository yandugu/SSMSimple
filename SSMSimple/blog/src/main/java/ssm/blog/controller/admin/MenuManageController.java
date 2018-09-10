package ssm.blog.controller.admin;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ssm.blog.pojo.AjaxJsonResult;
import ssm.blog.pojo.Menu;
import ssm.blog.pojo.PageBean;
import ssm.blog.service.MenuService;
//import ssm.blog.service.MenuService;
import ssm.blog.util.ResponseUtil;

@Controller
@RequestMapping(value ="/admin/menu")
public class MenuManageController {
	@Resource
	private MenuService menuService;
	
	@RequestMapping(value ="/list")
	public String listMenu(@RequestParam(value = "page", required = false) String page,
	        @RequestParam(value = "rows", required = false) String rows, 
	        HttpServletResponse response) throws Exception {
		
		//定义分页bean
	    PageBean<Menu> pageBean = new PageBean<Menu>(Integer.parseInt(page)
	            ,Integer.parseInt(rows));
	    //拿到分页结果已经记录总数的pageBean
	    pageBean = menuService.listByPage(pageBean);
	    //使用阿里巴巴的fastJson创建JSONObject
	    JSONObject result = new JSONObject();
	    //通过fastJson序列化list为jsonArray
	    String jsonArray = JSON.toJSONString(pageBean.getResult());
	    JSONArray array = JSONArray.parseArray(jsonArray);
	    //将序列化结果放入json对象中
	    result.put("rows", array);
	    result.put("total", pageBean.getTotal());

	    //使用自定义工具类向response中写入数据
	    ResponseUtil.write(response, result);
	    return null;
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	public AjaxJsonResult saveMenu(Menu menu, HttpServletResponse response, HttpServletRequest request)
            throws Exception {
		AjaxJsonResult result = new AjaxJsonResult(1, "The menu save fail");
		int resultTotal = 0; // 接收返回结果记录数
		String isLeaf = (String)request.getParameter("isLeaf");
		//Integer sort = Integer.valueOf(request.getParameter("sort"));
		boolean leaf=isLeaf.equals("true")?true:false;
		menu.setIsLeaf(leaf);
		//menu.setSort(sort);
		menu.setStatus(true);
		
        if (menu.getId() == null) { // 说明是第一次插入
            resultTotal = menuService.addMenu(menu);
        } else { // 有id表示修改
            resultTotal = menuService.updateMenu(menu);
        }

        //JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            //result.put("success", true);
        	result.setError(0);
        	result.setMessage("Menu add success!");
        } else {
        	result.setError(1);
        	result.setMessage("Menu add fail!");
        }
        //ResponseUtil.write(response, result);
        return result;
	}
	
	@RequestMapping(value = "/delete")
    public String deleteMenu(
            @RequestParam(value = "ids", required = false) String ids,
            HttpServletResponse response) throws Exception {
        //分割字符串得到id数组
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            //其实在这里我们要判断该博客类别下面是否有博客 如果有就禁止删除博客类别 ，等我们完成博客对应的操作再来完善
            menuService.deleteMenu(id);
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }
	
	
	@RequestMapping(value="/getNotLeafMenu")
	@ResponseBody
	public List<Menu> getNotLeafMenu(){
		List<Menu> list = menuService.getNotLeafMenu();
		return list;
	}
}
