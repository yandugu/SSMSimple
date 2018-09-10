package ssm.blog.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ssm.blog.pojo.AjaxJsonResult;
import ssm.blog.pojo.User;
import ssm.blog.service.UserService;
import ssm.blog.util.Md5Util;

@Controller  //注册为控制器bean
@RequestMapping(value = "/userCenter")    //请求路径
public class UserCenterController {
	
	//@Resource(name = "userService")
	@Autowired
	private UserService userService;
	
	
	//@ResponseBody  //返回json数据
	@RequestMapping(value = "/login",method=RequestMethod.POST)
    public String login(User user, HttpServletRequest request){
		//int result=userService.getUserInfo(user);
		
		Subject subject = SecurityUtils.getSubject(); 
		
		//String password = Md5Util.md5(user.getPassword());
		user.setPassword(Md5Util.md5(user.getPassword()));
		
		UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword());
		try
		{
			subject.login(token);
			if(subject.isAuthenticated())
			{
				request.getSession().setAttribute("userinfo", user);
				return "redirect:/admin/menu.do";
			}
			else 
			{
				return "redirect:/index.jsp";
			}
		}
		catch(AuthenticationException e) 
		{
			//return e.getMessage();
			return "redirect:/index.jsp";
		}
        
    }
	
	@RequestMapping("/modifyPassword")
	@ResponseBody
	public AjaxJsonResult modifyPassword(String password, HttpServletRequest request) {
		String pwd = request.getParameter("password");
		AjaxJsonResult result = new AjaxJsonResult(0, "密码修改成功");
		if(password!=null && password!="") {
			User userinfo = (User)request.getSession().getAttribute("userinfo");
			User user = userService.findUserByName(userinfo.getUserName());
			user.setPassword(Md5Util.md5(password));
			userService.updateUserById(user);
		}else {
			result.setError(100);
			result.setMessage("密码不能设置为空");
		}
		
		return result;
	}
 
	
	
	@RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        //request.getSession().invalidate();
        //return "redirect:/index.jsp";
		Subject subject = SecurityUtils.getSubject();  
	    if (subject.isAuthenticated()) {  
	        subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存   
	    }  
	    return "redirect:/index.jsp";
    } 
}
