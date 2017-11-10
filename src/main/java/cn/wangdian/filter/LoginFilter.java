package cn.wangdian.filter;

import com.alibaba.druid.filter.logging.LogFilter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用来过滤用户是否已经登录
 * 防止恶意用户修改后台管理数据
 */
public class LoginFilter implements Filter{
    private static Log log = LogFactory.getLog(LogFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 判断用户是否已经登录 且 session 还未失效
     * 通过判断 session 中 "login" 属性是否是 "success"
     * 如果是则说明用户已经通过登录，filterChain.doFilter(request, response); 允许用户继续执行相应操作
     * 否则不反悔任何东西，防止撞库
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(true);
        if ("success".equals(session.getAttribute("login"))) {
            filterChain.doFilter(request, servletResponse);
        } else {
//            什么都不回应
            log.info("捕捉到恶意用户撞库");
        }
    }

    @Override
    public void destroy() {

    }
}
