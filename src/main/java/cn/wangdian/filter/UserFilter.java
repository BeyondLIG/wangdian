package cn.wangdian.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户操作的过滤器
 */
public class UserFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if (session.getAttribute("ordinaryUser") != null) {
            filterChain.doFilter(servletRequest, servletResponse);
        }else {
            //用户还没有登录或者已经登录超时了，重定向到登录页面
            request.getRequestDispatcher("/login").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
