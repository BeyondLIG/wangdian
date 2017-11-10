package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.Contact;
import cn.wangdian.Model.YunFei;
import cn.wangdian.Service.ContactService;
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
 * Created by 25065 on 2016/10/30.
 */
@Controller
@RequestMapping("/admin")
public class ContactController {

    private ExecuteResult executeResult=new ExecuteResult();

    private Log logger= LogFactory.getLog(ContactController.class);

    @Autowired
    private ContactService contactService;

    @RequestMapping("/profit/contact")
    public String contact(Model model){
        Contact contact= null;
        try {
            contact = contactService.onlyOne();
        } catch (Exception e) {
            e.printStackTrace();
        }
        model.addAttribute("contact",contact);
        return "/backPage/admin/contact/list";
    }

    @ResponseBody
    @RequestMapping(value = "/profit/contact/save",method = RequestMethod.POST)
    public Object save(Contact contact){

        try {
            if (contact.getId()==null||contact.getId().equals("")){
                //添加
                contactService.save(contact);
            }else {
                //编辑
                contactService.update(contact);
            }
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300,e.getMessage());
        }
    }
}
