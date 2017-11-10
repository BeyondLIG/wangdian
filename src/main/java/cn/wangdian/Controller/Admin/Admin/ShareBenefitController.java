package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.Benefit;
import cn.wangdian.Service.BenefitService;
import cn.wangdian.utils.ExecuteResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 关于分润的控制器
 */
@Controller
@RequestMapping("/admin/shareBenefit")
public class ShareBenefitController {
    @Autowired
    private BenefitService benefitService;

    private ExecuteResult executeResult = new ExecuteResult(); //用来返回 json 信息

    /**
     * 展示年费分润系数
     */
    @RequestMapping(value = "/annualFeeShareBenefit", method = RequestMethod.GET)
    public String showAnnualFeeShareBenefitParam(Model model) {
        Benefit annualFeeShareBenfitParam = benefitService.findAnnualFeeShareBenefitParam();
        model.addAttribute("annualFeeParam", annualFeeShareBenfitParam);
        //TODO 展示年费分润系统的界面
        return "backPage/admin/shopkeeperPercentage/shopkeeperPercentage";
    }

    /**
     * 更新店主的年费分润参数
     */
    @ResponseBody
    @RequestMapping(value = "/annualFeeShareBenefit/update", method = RequestMethod.POST)
    public Object updateAnnualFeeShareBenefitParam(Benefit benefit) {
        try {
            benefitService.updateAnnualFeeShareBenefitParam(benefit);
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300, e.getMessage());
        }

    }

    /**
     * 展示商品分润系数
     */
    @RequestMapping(value = "/goodsShareBenefit", method = RequestMethod.GET)
    public String showGoodsShareBenefitParam(Model model) {
        Benefit goodsShareBenefitParam = benefitService.findGoodsShareBenefitParam();
        model.addAttribute("goodsShareBenefitParam", goodsShareBenefitParam);
        //TODO 展示商品分润系数的界面
        return "backPage/admin/shopPercentage/shopPercentage";
    }

    /**
     * 更新商品分润系数
     */
    @ResponseBody
    @RequestMapping(value = "/goodsShareBenefit/update", method = RequestMethod.POST)
    public Object updateGoodsShareBenefitParam(Benefit benefit) {
        try {
            benefitService.updateGoodsShareBenefitParam(benefit);
            return executeResult.jsonReturn(200);
        } catch (Exception e) {
            return executeResult.jsonReturn(300, e.getMessage());
        }
    }
}
