package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.AnnualFee;
import cn.wangdian.Service.AnnualFeeService;
import cn.wangdian.utils.ExecuteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 关于店主应该提交多少年费的页面
 */
@Controller
@RequestMapping("/admin/annualFee")
public class AdminAnnualFeeController {
    @Autowired
    private AnnualFeeService annualFeeService;

    private ExecuteResult executeResult = new ExecuteResult(); //用于返回 json 结果

    /**
     * 展示年费
     */
    @RequestMapping("")
    public String showAnnualFee(Model model) {
        AnnualFee annualFee = annualFeeService.onlyOne();
        model.addAttribute("annualFee", annualFee);
        //TODO 展示年费信息
        return "backPage/admin/annualFee/annualFee";
    }

    /**
     * 修改年费
     */
    @ResponseBody
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Object updateAnnualFee(AnnualFee annualFee) {
        try {
            annualFeeService.update(annualFee);
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300, e.getMessage());
        }
    }
}
