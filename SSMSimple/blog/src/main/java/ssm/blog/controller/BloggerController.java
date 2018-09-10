package ssm.blog.controller;

import ssm.blog.pojo.AjaxJsonResult;
import ssm.blog.pojo.Menu;
//import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import ssm.blog.pojo.User;
import ssm.blog.service.MenuService;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Created by lmh on 2018/5/25.
 * ����������
 */
@Controller  //ע��Ϊ������bean
@RequestMapping(value = "/admin")    //����·��
public class BloggerController {

	@Autowired
	private MenuService menuService;

	@RequestMapping(value="/menu")
	public ModelAndView menu(HttpSession session)
	{
		ModelAndView modelAndView = new ModelAndView(); 
		if(session.getAttribute("userinfo") == null)
		{
			modelAndView.setViewName("index");
		}
		else
		{
			User userinfo = (User)session.getAttribute("userinfo");
			modelAndView.addObject("userinfo", userinfo);
			modelAndView.setViewName("admin/menu");
			//mav.addObject("userinfo", (User)session.getAttribute("userinfo"));
		}

        return modelAndView; 
	}
	
	@RequestMapping(value="/save")
	@ResponseBody
	public AjaxJsonResult saveMenu(String menuName, Menu menu) {
		AjaxJsonResult result = new AjaxJsonResult(1, "The menu save fail");
		
		try {
			menu.setStatus(true);
			menuService.addMenu(menu);
			result.setError(0);
			result.setMessage("The menu save success");
		}catch(Exception ex) {
			
		}
		
		return result;
	}

}
