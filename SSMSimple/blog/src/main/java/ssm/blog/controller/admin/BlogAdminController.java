package ssm.blog.controller.admin;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ssm.blog.pojo.Blog;
import ssm.blog.pojo.PageBean;
//import ssm.blog.lucene.BlogIndex;
import ssm.blog.service.BlogService;
//import ssm.blog.service.CommentService;
import ssm.blog.util.ResponseUtil;

/**
 * @author xp
 * @Description ����Ա����Controller��
 * 
 * ������Ҫע��һ��json���л���ʱ��
 * //����json���л����ڸ�ʽ
 * JSON.DEFFAULT_DATE_FORMAT = ��yyyy-MM-dd��;
 * �������л�ʱdate���л��ĸ�ʽ
 * ��������� ʱ����ĸ�ʽ��
 * �������ʽ�����ڸ�ʽ�� ��ʹ�øø�ʽ���л�����
 * 
 * 
 */
@Controller
@RequestMapping("/admin/blog")
public class BlogAdminController {

    @Resource
    private BlogService blogService;
//    @Resource
//    private CommentService commentService;
//    @Resource
//    private BlogIndex blogIndex;

    //��̨��ҳ��ѯ������Ϣ
    @RequestMapping("/listBlog")
    public String listBlog(
            @RequestParam(value = "page", required = false) String page,
            @RequestParam(value = "rows", required = false) String rows,
            Blog s_blog,
            HttpServletResponse response) throws Exception {

        PageBean<Blog> pageBean = new PageBean<Blog>(Integer.parseInt(page), Integer.parseInt(rows));

        pageBean = blogService.listBlog(s_blog.getTitle(), pageBean);

        //����json����
        JSONObject result = new JSONObject();
        //����json���л����ڸ�ʽ
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd";
        //��ֹ����ѭ������
        //ʹ��Ĭ�����ڸ�ʽ��
        String jsonStr = JSONObject.toJSONString(pageBean.getResult(),
                SerializerFeature.DisableCircularReferenceDetect,
                SerializerFeature.WriteDateUseDateFormat);
        //�õ�json����
        JSONArray array = JSON.parseArray(jsonStr);
        //�ѽ������json
        result.put("rows", array);
        result.put("total", pageBean.getTotal());
        //����
        ResponseUtil.write(response, result);
        return null;
    }

    //���»�����������
    @RequestMapping(value = "/save")
    public String saveBlog(Blog blog,HttpServletResponse response) throws Exception {
        int resultTotal = 0;
        if(blog.getId()!=null){
            //���²���
            resultTotal = blogService.updateBlog(blog);
            //��������
            //blogIndex.updateIndex(blog);
        }else{
            //��������
            resultTotal = blogService.saveBlog(blog);
            //�������
            //blogIndex.addIndex(blog);
        }
        JSONObject result = new JSONObject();
        if(resultTotal > 0) {
            result.put("success", true);
        } else {
            result.put("success", false);
        }
        ResponseUtil.write(response, result);
        return null;
    }

    //ɾ������
    @RequestMapping(value = "delete")
    public String deleteBlog(@RequestParam("ids")String ids,HttpServletResponse response) throws Exception {
        String[] idsStr = ids.split(",");
        for(int i = 0; i < idsStr.length; i++) {
            int id = Integer.parseInt(idsStr[i]);
            //��ɾ������������������ ����û��������۵Ĺ��� ��ע��
            //commentService.deleteCommentByBlogId(id);
            blogService.deleteBlog(id);
        }
        JSONObject result = new JSONObject();
        result.put("success", true);
        ResponseUtil.write(response, result);
        return null;
    }

    //ͨ��id��ȡ����
    @RequestMapping(value = "get")
    public String getById(@RequestParam("id") String id,HttpServletResponse response) throws Exception {

        Blog blog = blogService.getById(Integer.parseInt(id));
        String jsonStr = JSONObject.toJSONString(blog);
        JSONObject result = JSONObject.parseObject(jsonStr);
        ResponseUtil.write(response, result);
        return null;
    }

}
