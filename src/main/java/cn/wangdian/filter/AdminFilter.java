package cn.wangdian.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 超级管理员过滤器，只有在
 */
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String peopleType = (String) session.getAttribute("peopleType");
        if ("admin".equals(peopleType)) {
            //只有在用户是在管理员权限下才允许用户进行相应的操作，否则不对该请求做任何操作
            filterChain.doFilter(servletRequest, servletResponse);
        }else{
            //重定向到首页
            request.getRequestDispatcher("/index").forward(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
