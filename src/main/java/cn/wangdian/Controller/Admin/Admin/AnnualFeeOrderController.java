package cn.wangdian.Controller.Admin.Admin;

import cn.wangdian.Model.AnnualFeeOrder;
import cn.wangdian.Service.AnnualFeeOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 管理员查看年费订单
 */
@Controller
@RequestMapping("/admin/annualFeeOrder")
public class AnnualFeeOrderController {
    @Autowired
    private AnnualFeeOrderService annualFeeOrderService;

    /**
     * 管理员后台查看所有年费订单的提交情况
     *
     * @param model
     * @param orderField     用于排序的字段
     * @param orderDirection 排序的方向
     * @param pageSize       一页请求多少条数据
     * @param pageCurrent    当前处于第几页
     * @param orderId        订单号
     * @param shopKeeper     提交年费的店主的用户名
     * @param submitTime     店主提交年费订单的时间
     * @param status         订单是否已经被支付的状态
     * @return
     */
    @RequestMapping("/list")
    public String annualFeeOrders(Model model, String orderField, String orderDirection, Integer pageSize, Integer pageCurrent,
                                  String orderId, String shopKeeper, String submitTime, Integer status) {
        Page<AnnualFeeOrder> annualFeeOrdersList;
        if (orderId != null && !orderId.equals("")) {
            model.addAttribute("orderId", orderId);
        }
        if (shopKeeper != null && !shopKeeper.equals("")) {
            model.addAttribute("shopkeeper", shopKeeper);
        }
        if (submitTime != null && !submitTime.equals("")) {
            model.addAttribute("submitTime", submitTime);
        }
        if (status != null) {
            model.addAttribute("status", status);
        }
        if (pageSize == null) {
            pageSize = 5;
        }
        //符合条件的记录数目
        int count = annualFeeOrderService.countAllByCondition(orderId, shopKeeper, submitTime, status);
        int pageNumber = (count + pageSize - 1) / pageSize; //计算记录的数目一共可以支持多少页
        if (pageCurrent == null) {
            pageCurrent = 0;
        } else if (pageCurrent > pageNumber) {
            pageCurrent = 0;
        } else {
            pageCurrent = pageCurrent - 1;
        }

        PageRequest pageRequest = new PageRequest(pageCurrent, pageSize);
        annualFeeOrdersList = annualFeeOrderService.findAllByCondition(orderId, shopKeeper, submitTime, status, orderField, orderDirection, pageRequest);
        model.addAttribute("annualFeeOrdersList", annualFeeOrdersList);
        //TODO 需要返回一个用来显示查询年费订单结果的页面
        return "backPage/admin/profit/annualFeeDetail";
    }
}
