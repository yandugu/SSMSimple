package ssm.blog.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ssm.blog.pojo.BlogType;
import ssm.blog.pojo.PageBean;
//import ssm.blog.service.BlogService;
import ssm.blog.service.BlogTypeService;
import ssm.blog.util.ResponseUtil;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

/**
* Created by xp on 2017/4/14.
* @author xp
* @Description ���ͷ��������
*/
@Controller
@RequestMapping(value ="/admin/blogType")
public class BlogTypeController {
	@Resource
	private BlogTypeService blogTypeService;

	// ��ҳ��ѯ�������
	@RequestMapping("/list")
	public String listBlogType(
	        @RequestParam(value = "page", required = false) String page,
	        @RequestParam(value = "rows", required = false) String rows,
	        HttpServletResponse response) throws Exception {
	    //�����ҳbean
	    PageBean<BlogType> pageBean = new PageBean<BlogType>(Integer.parseInt(page)
	            ,Integer.parseInt(rows));
	    //�õ���ҳ����Ѿ���¼������pageBean
	    pageBean = blogTypeService.listByPage(pageBean);
	    //ʹ�ð���Ͱ͵�fastJson����JSONObject
	    JSONObject result = new JSONObject();
	    //ͨ��fastJson���л�listΪjsonArray
	    String jsonArray = JSON.toJSONString(pageBean.getResult());
	    JSONArray array = JSONArray.parseArray(jsonArray);
	    //�����л��������json������
	    result.put("rows", array);
	    result.put("total", pageBean.getTotal());

	    //ʹ���Զ��幤������response��д������
	    ResponseUtil.write(response, result);
	    return null;
	}
	
	// ��Ӻ͸��²������
    @RequestMapping("/save")
    public String save(BlogType blogType, HttpServletResponse response)
            throws Exception {

        int resultTotal = 0; // ���շ��ؽ����¼��
        if (blogType.getId() == null) { // ˵���ǵ�һ�β���
            resultTotal = blogTypeService.addBlogType(blogType);
        } else { // ��id��ʾ�޸�
            resultTotal = blogTypeService.updateBlogType(blogType);
        }

        JSONObject result = new JSONObject();
        if (resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    // ���������Ϣɾ��
    @RequestMapping(value = "/delete")
    public String deleteBlog(
            @RequestParam(value = "ids", required = false) String ids,
            HttpServletResponse response) throws Exception {
        //�ָ��ַ����õ�id����
        String[] idsStr = ids.split(",");
        JSONObject result = new JSONObject();
        for (int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
             //��ʵ����������Ҫ�жϸò�����������Ƿ��в��� ����оͽ�ֹɾ��������� ����������ɲ��Ͷ�Ӧ�Ĳ�����������
                blogTypeService.deleteBlogType(id);
        }
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }

}
