<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <div id="bjui-leftside">
        <div id="bjui-sidebar-s">
            <div class="collapse"></div>
        </div>
        <div id="bjui-sidebar">
            <div class="toggleCollapse">
                <h2><i class="fa fa-bars"></i> 导航栏 <i class="fa fa-bars"></i></h2><a href="javascript:;" class="lock"><i class="fa fa-lock"></i></a></div>
            <div class="panel-group panel-main" data-toggle="accordion" id="bjui-accordionmenu" data-heightbox="#bjui-sidebar" data-offsety="26">


                <%
               String peopleType=(String) request.getAttribute("peopleType");
               if (peopleType.equals("shopKeeper")){
           %>
                    <%--店主列表 start--%>
                        <!--业务管理-->
                        <div class="panel panel-default">
                            <div class="panel-heading panelContent">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse0" class="active"><i class="fa fa-caret-square-o-down"></i>&nbsp;业务管理</a>
                                </h4>
                            </div>
                            <div id="bjui-collapse0" class="panel-collapse panelContent collapse in">
                                <div class="panel-body">
                                    <ul id="bjui-tree0" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                        <li data-id="Shop" data-pid="0">商品管理</li>
                                        <li data-id="shop_0" data-pid="Shop" data-url="<%=request.getContextPath()%>/admin/shopKeeper/shop/list" data-tabid="shop_0"
                                            data-fresh="true" data-reloadWarn="true">商品查看</li>
                                        <li data-id="shop_1" data-pid="Shop" data-url="<%=request.getContextPath()%>/admin/shopKeeper/shunShop/list" data-tabid="shop_1"
                                            data-fresh="true" data-reloadWarn="true">瞬时秒杀</li>
                                        <li data-id="shopKeeperOrder" data-pid="0">订单管理</li>
                                        <li data-id="shopKeeperOrder_0" data-pid="shopKeeperOrder" data-url="<%=request.getContextPath()%>/admin/shopKeeper/orders/list"
                                            data-tabid="shopKeeperOrder_0" data-fresh="true" data-reloadWarn="true">订单列表</li>
                                        <%--<li data-id="mutant_1" data-pid="mutant" data-url="<%=request.getContextPath()%>/Admin/mutant/search" data-tabid="mutant_1" data-fresh="true" data-reloadWarn="true">诱变类型与品种</li>--%>
                                            <li data-id="shopKeeperProfit" data-pid="0">收益管理</li>
                                            <li data-id="shopKeeperProfit_0" data-pid="shopKeeperProfit" data-url="<%=request.getContextPath()%>/admin/shopKeeper/profit/list"
                                                data-tabid="shopKeeperProfit_0" data-fresh="true" data-reloadWarn="true">统计与收益</li>
                                            <%--<li data-id="trait_0" data-pid="trait" data-url="<%=request.getContextPath()%>/Admin/lab/message/basic" data-tabid="trait_0" data-fresh="true" data-reloadWarn="true">添加特性</li>--%>
                                    </ul>
                                </div>
                            </div>
                            <div class="panelFooter">
                                <div class="panelFooterContent"></div>
                            </div>
                        </div>
                        <!--人员管理-->
                        <div class="panel panel-default">
                            <div class="panel-heading panelContent">
                                <h4 class="panel-title">
                                    <a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse1" class=""><i class="fa fa-caret-square-o-down"></i>&nbsp;人员管理</a>
                                </h4>
                            </div>
                            <div id="bjui-collapse1" class="panel-collapse panelContent collapse">
                                <div class="panel-body">
                                    <ul id="bjui-tree1" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                        <li data-id="shopKeeperDevelop" data-pid="1">店主管理</li>
                                        <!--新增/修改data_url要改-->
                                        <li class="addBelong" data-id="shopKeeperDevelop_1" data-pid="shopKeeperDevelop" data-url="<%=request.getContextPath()%>/admin/shopKeeper/shopkeeperList" data-tabid="shopKeeperDevelop_1"
                                            data-fresh="true" data-reloadWarn="true">我的店主</li>

                                        <!--新增/修改data_url要改-->
                                        <li data-id="shopKeeperUser" data-pid="1">用户管理</li>
                                        <li class="addId" data-id="shopKeeperUser_1" data-pid="shopKeeperUser" data-url="<%=request.getContextPath()%>/admin/shopKeeper/userList" data-tabid="user_normal"
                                            data-fresh="true" data-reloadWarn="true">我的用户</li>
                                    </ul>
                                </div>
                            </div>
                            <div class="panelFooter">
                                <div class="panelFooterContent"></div>
                            </div>
                        </div>
                        <%--店主列表 end--%>
                            <%
                }
            %>

                                <%
                if (peopleType.equals("admin")){
            %>
                                    <%--管理员列表 start--%>
                                        <!--业务管理-->
                                        <div class="panel panel-default">
                                            <div class="panel-heading panelContent">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse2"
                                                        class=""><i class="fa fa-caret-square-o-down"></i>&nbsp;业务管理</a></h4>
                                            </div>
                                            <div id="bjui-collapse2" class="panel-collapse panelContent collapse in">
                                                <div class="panel-body">
                                                    <ul id="bjui-tree2" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                                        <li data-id="shop" data-pid="2">商品管理</li>
                                                        <li data-id="shop_0" data-pid="shop" data-url="<%=request.getContextPath()%>/admin/shop/list" data-tabid="shop_0" data-fresh="true"
                                                            data-reloadWarn="true">商品操作</li>
                                                        <li data-id="shop_1" data-pid="shop" data-url="<%=request.getContextPath()%>/admin/shunShop/list" data-tabid="shop_1" data-fresh="true"
                                                            data-reloadWarn="true">瞬时秒杀</li>

                                                        <li data-id="adminOrder" data-pid="2">订单管理</li>
                                                        <li data-id="adminOrder_0" data-pid="adminOrder" data-url="<%=request.getContextPath()%>/admin/orders/list" data-tabid="adminOrder_0"
                                                            data-fresh="true" data-reloadWarn="true">订单列表</li>

                                                        <li data-id="adminProfit" data-pid="2">收益管理</li>
                                                        <li data-id="adminProfit_0" data-pid="adminProfit" data-url="<%=request.getContextPath()%>/admin/profit/list" data-tabid="adminProfit_0"
                                                            data-fresh="true" data-reloadWarn="true">统计收益</li>
                                                        <li data-id="adminProfit_1" data-pid="adminProfit" data-url="<%=request.getContextPath()%>/admin/annualFeeOrder/list" data-tabid="adminProfit_1"
                                                            data-fresh="true" data-reloadWarn="true">年费缴纳</li>
                                                        <li data-id="adminProfit_2" data-pid="adminProfit" data-url="<%=request.getContextPath()%>/admin/profit/tiXian" data-tabid="adminProfit_2"
                                                            data-fresh="true" data-reloadWarn="true">查看店主提现</li>
                                                        <li data-id="adminProfit_3" data-pid="adminProfit" data-url="<%=request.getContextPath()%>/admin/profit/yunFei" data-tabid="adminProfit_3"
                                                            data-fresh="true" data-reloadWarn="true">运费</li>
                                                        <li data-id="adminProfit_4" data-pid="adminProfit" data-url="<%=request.getContextPath()%>/admin/profit/contact" data-tabid="adminProfit_4"
                                                            data-fresh="true" data-reloadWarn="true">商家联系管理</li>

                                                        <li data-id="systemManage" data-pid="2">分润体系管理</li>
                                                        <li data-id="systemManage_0" data-pid="systemManage" data-url="<%=request.getContextPath()%>/admin/annualFee" data-tabid="systemManage_0"
                                                            data-fresh="true" data-reloadWarn="true">年费设置</li>
                                                        <li data-id="systemManage_1" data-pid="systemManage" data-url="<%=request.getContextPath()%>/admin/shareBenefit/annualFeeShareBenefit" data-tabid="systemManage_1"
                                                            data-fresh="true" data-reloadWarn="true">店主体系提成</li>
                                                        <li data-id="systemManage_2" data-pid="systemManage" data-url="<%=request.getContextPath()%>/admin/shareBenefit/goodsShareBenefit" data-tabid="systemManage_2"
                                                            data-fresh="true" data-reloadWarn="true">商品销售分润</li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="panelFooter">
                                                <div class="panelFooterContent"></div>
                                            </div>
                                        </div>
                                        <!--人员管理-->
                                        <div class="panel panel-default">
                                            <div class="panel-heading panelContent">
                                                <h4 class="panel-title"><a data-toggle="collapse" data-parent="#bjui-accordionmenu" href="#bjui-collapse3"
                                                        class=""><i class="fa fa-caret-square-o-down"></i>&nbsp;人员管理</a></h4>
                                            </div>
                                            <div id="bjui-collapse3" class="panel-collapse panelContent collapse">
                                                <div class="panel-body">
                                                    <ul id="bjui-tree3" class="ztree ztree_main" data-toggle="ztree" data-on-click="MainMenuClick" data-expand-all="true">
                                                        <li data-id="admin" data-pid="3">管理员管理</li>
                                                        <li data-id="admin_0" data-pid="admin" data-url="<%=request.getContextPath()%>/admin/admin/list" data-tabid="admin_0" data-fresh="true"
                                                            data-reloadWarn="true">管理员操作</li>

                                                        <li data-id="shopKeeper" data-pid="3">店主管理</li>
                                                        <!--新增/修改data_url要改-->
                                                        <li data-id="shopKeeper_1" data-pid="shopKeeper" data-url="<%=request.getContextPath()%>/admin/shopKeeper/list" data-tabid="shopKeeper_1"
                                                            data-fresh="true" data-reloadWarn="true">所有店主</li>
                                                        <li data-id="shopKeeper_1" data-pid="shopKeeper" data-url="<%=request.getContextPath()%>/admin/shopKeeper/list?level=0" data-tabid="shopKeeper_1"
                                                            data-fresh="true" data-reloadWarn="true">LV0店主</li>
                                                        <li data-id="shopKeeper_2" data-pid="shopKeeper" data-url="<%=request.getContextPath()%>/admin/shopKeeper/list?level=1" data-tabid="shopKeeper_2"
                                                            data-fresh="true" data-reloadWarn="true">LV1店主</li>
                                                        <li data-id="shopKeeper_3" data-pid="shopKeeper" data-url="<%=request.getContextPath()%>/admin/shopKeeper/list?level=2" data-tabid="shopKeeper_3"
                                                            data-fresh="true" data-reloadWarn="true">LV2店主</li>

                                                        <!--新增/修改data_url要改-->
                                                        <li data-id="user" data-pid="3">用户管理</li>
                                                        <li data-id="user_vip" data-pid="user" data-url="<%=request.getContextPath()%>/admin/user/list?vip=1" data-tabid="user_vip" data-fresh="true"
                                                            data-reloadWarn="true">VIP用户</li>
                                                        <!--
                                                        <li data-id="user_normal" data-pid="user" data-url="<%=request.getContextPath()%>/admin/user/list?vip=0" data-tabid="user_normal"
                                                            data-fresh="true" data-reloadWarn="true">普通用户</li>
                                                        -->
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="panelFooter">
                                                <div class="panelFooterContent"></div>
                                            </div>
                                        </div>
                                        <%--管理员列表 end--%>
                                            <%
                }
            %>
            </div>
        </div>
    </div>
<script>
    var $url = $(".addBelong").attr("data-url")+"?belong=${user.id}"
    $(".addBelong").attr("data-url",$url);
    var $url = $(".addId").attr("data-url")+"?shopKeeperId=${user.id}"
    $(".addId").attr("data-url",$url);
</script>