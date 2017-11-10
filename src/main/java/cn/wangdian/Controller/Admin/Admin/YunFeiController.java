package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.YunFei;
import cn.wangdian.Service.YunFeiService;
import cn.wangdian.utils.ExecuteResult;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by dushang on 2016/9/26.
 */
@Controller
@RequestMapping("/admin")
public class YunFeiController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(YunFeiController.class);

    @Autowired
    private YunFeiService yunFeiService;


    @RequestMapping("/profit/yunFei")
    public String yunFei(Model model){
        YunFei yunFei= null;
        try {
            yunFei = yunFeiService.onlyOne();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("yunFei",yunFei);
        return "/backPage/admin/yunFei/list";
    }

    @ResponseBody
    @RequestMapping(value = "/profit/yunFei/save",method = RequestMethod.POST)
    public Object save(YunFei yunFei){

        try {
            if (yunFei.getId()==null||yunFei.getId().equals("")){
                //添加
                yunFeiService.save(yunFei);
            }else {
                //编辑
                yunFeiService.update(yunFei);
            }
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
    }
}
